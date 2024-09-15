/**
 * ClanInfo.kt
 *
 * Display Clan information and can access each member's data
 */

@Composable
fun ClanInfo(info: Info) {
    val { clan_id, tag, server } = info
    val { id, tag, info, valid, canBeFriend } = remember {
        if (clan_id == null) {
            // This should never happen but just in case
            Triple("???", SafeValue(tag, "???"), false, true, false)
        } else {
            // When everything is Valid
            val friend = AppGlobalData.get(LOCAL.friendList)
            val state = remember {
                mutableStateOf(
                    Triple(clan_id, tag, false),
                )
            }
            val domain = getDomain(server)
            val prefix = getPrefix(server)

            SafeFetch.get(WoWsAPI.ClanInfo, domain, clan_id).then { data ->
                val clanInfo = Guard(data, "data.$clan_id", null)
                if (clanInfo != null) {
                    state.value = Triple(clan_id, tag, clanInfo)
                } else {
                    state.value = Triple(clan_id, tag, false)
                }
            }

            Triple(
                clan_id,
                tag,
                state.value.third,
                true,
                friend.clan[clan_id] == null,
            )
        }
    }

    if (valid) {
        WoWsInfo(title = "- $id -") {
            renderClanInfo(info)
        }.clickable {
            SimpleViewHandler.openURL(
                "https://${prefix}.wows-numbers.com/clan/${id}, $tag/",
            )
        }
    } else {
        WoWsInfo(title = "- $id -") {
            Title(text = tag, modifier = Modifier.padding(16))
        }
    }
}

@Composable
fun renderClanInfo(data: Any) {
    if (data != null) {
        val { created_at, creator_name, creator_id, leader_name, leader_id, description, name, members, members_count, tag } =
            data as Map<*, *>
        val memberInfo = remember { mutableListOf<Map<*, *>?>() }
        for (ID in members.keys) {
            memberInfo.add(members[ID] as Map<*, *>)
        }

        // Sorting the memberInfo by joined_at
        memberInfo.sortBy { it["joined_at"] as Long }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Title(text = tag, style = TextStyle(fontWeight = FontWeight.Bold))
            Subheading(
                text = name,
                color = TintColour()[500],
                modifier = Modifier.padding(8.dp)
            )
            InfoLabel(title = lang.clan_created_date, info = humanTimeString(created_at))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                InfoLabel(
                    title = lang.clan_creator_name,
                    info = creator_name,
                    modifier = Modifier.clickable { pushToMaster(creator_name, creator_id.toString()) }
                )
                InfoLabel(
                    title = lang.clan_leader_name,
                    info = leader_name,
                    modifier = Modifier.clickable { pushToMaster(leader_name, leader_id.toString()) }
                )
            }

            if (canBeFriend) {
                Button(onClick = addFriend) {
                    Text(text = lang.basic_add_friend)
                }
            }

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )

            SectionTitle(title = "${lang.clan_member_title} - $members_count")
            LazyColumn {
                items(memberInfo) { item ->
                    ListItem(
                        text = item["account_name"] as String,
                        secondaryText = humanTimeString(item["joined_at"] as Long),
                        modifier = Modifier.clickable { pushToPlayer(item) },
                        trailing = {
                            Text(text = (item["account_id"] as Long).toString())
                        }
                    )
                }
            }
        }
    } else {
        CircularProgressIndicator()
    }
}

fun addFriend() {
    val { clan_id, tag, server } = props.info
    val friendListStr = LOCAL.friendList
    val friendList = AppGlobalData.get(friendListStr)
    friendList.clan[clan_id] = mapOf("clan_id" to clan_id, "tag" to tag, "server" to server)
    SafeStorage.set(friendListStr, friendList)
    setState { canBeFriend(false) }
}

fun pushToMaster(name: String, id: String) {
    val item = mapOf("nickname" to name, "account_id" to id.toLong(), "server" to this.server)
    SafeAction("Statistics", mapOf("info" to item))
}

fun pushToPlayer(item: Map<*, *>) {
    val item = mapOf(
        "nickname" to item["account_name"],
        "account_id" to (item["account_id"] as Long),
        "server" to this.server
    )
    SafeAction("Statistics", mapOf("info" to item))
}