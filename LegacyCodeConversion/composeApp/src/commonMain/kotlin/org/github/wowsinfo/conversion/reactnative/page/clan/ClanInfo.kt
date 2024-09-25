
@Composable
fun ClanInfo(info: ClanInfoProps) {
    val coroutineScope = rememberCoroutineScope()
    var clanId by remember { mutableStateOf("???") }
    var clanTag by remember { mutableStateOf("???") }
    var clanInfo by remember { mutableStateOf<ClanInfoData?>(null) }
    var isValid by remember { mutableStateOf(true) }
    var canBeFriend by remember { mutableStateOf(true) }

    LaunchedEffect(info) {
        val (clanIdProp, tag, server) = info
        if (clanIdProp == null) {
            clanId = "???"
            clanTag = SafeValue(tag, "???")
        } else {
            clanId = clanIdProp
            clanTag = tag
            val friendList = AppGlobalData.get(LOCAL.friendList)
            canBeFriend = friendList.clan[clanId] == null

            val domain = getDomain(server)
            val prefix = getPrefix(server)

            SafeFetch.get(WoWsAPI.ClanInfo, domain, clanId).let { data ->
                val clanInfoData = Guard(data, "data.$clanId", null)
                if (clanInfoData != null) {
                    clanInfo = clanInfoData
                } else {
                    isValid = false
                }
            }
        }
    }

    if (clanInfo != null && isValid) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Clan ID: $clanId", style = MaterialTheme.typography.h6)
            Text(text = "Clan Tag: $clanTag", style = MaterialTheme.typography.subtitle1)
            // Display clan information here
        }
    } else {
        Text(text = "Invalid Clan Information", color = MaterialTheme.colors.error)
    }
}

data class ClanInfoProps(val clan_id: String?, val tag: String, val server: String)
data class ClanInfoData(val someData: String) // Define according to your data structure

fun ClanInfoView(state: ClanInfoState) {
    val (clanTagStyle, containerStyle) = styles
    val (info, tag, id, valid) = state

    if (valid) {
        WoWsInfo(
            title = "- $id -",
            onClick = {
                SimpleViewHandler.openURL("https://${prefix}.wows-numbers.com/clan/$id, $tag/")
            }
        ) {
            renderClanInfo(info)
        }
    } else {
        WoWsInfo(title = "- $id -", style = containerStyle) {
            Text(text = tag, style = clanTagStyle)
        }
    }
}

fun RenderClanInfo(data: ClanData?) {
    if (data != null) {
        val memberInfo = data.members.values.sortedBy { it.joinedAt }
        Column {
            Text(text = data.tag, style = MaterialTheme.typography.h6)
            Text(
                text = data.name,
                color = TintColour()[500],
                textAlign = TextAlign.Center
            )
            InfoLabel(title = lang.clan_created_date, info = humanTimeString(data.createdAt))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                InfoLabel(
                    title = lang.clan_creator_name,
                    info = data.creatorName,
                    onClick = { pushToMaster(data.creatorName, data.creatorId) }
                )
                InfoLabel(
                    title = lang.clan_leader_name,
                    info = data.leaderName,
                    onClick = { pushToMaster(data.leaderName, data.leaderId) }
                )
            }
            if (canBeFriend) {
                Button(onClick = { addFriend() }, modifier = Modifier.padding(4.dp)) {
                    Text(text = lang.basic_add_friend)
                }
            }
            Text(text = data.description, modifier = Modifier.padding(16.dp))
            Text(text = "${lang.clan_member_title} - ${data.membersCount}", style = MaterialTheme.typography.h6)
            LazyColumn {
                items(memberInfo) { item ->
                    ListItem(
                        modifier = Modifier.clickable { pushToPlayer(item) },
                        headlineContent = { Text(text = item.accountName) },
                        supportingContent = { Text(text = humanTimeString(item.joinedAt)) },
                        trailingContent = {
                            Text(text = item.accountId.toString(), modifier = Modifier.padding(end = 8.dp))
                        }
                    )
                }
            }
        }
    } else {
        LoadingIndicator()
    }
}

    val (clanId, tag, server) = info
    val str = LOCAL.friendList
    val currentData = AppGlobalData.get(str)
    currentData.clan[clanId] = ClanData(clanId, tag, server)
    SafeStorage.set(str, currentData)
    setState { canBeFriend = false }
}

    val item = mapOf("nickname" to name, "account_id" to id, "server" to server)
    SafeAction("Statistics", mapOf("info" to item))
}

    item.nickname = item.accountName
    item.server = this.server
    SafeAction("Statistics", mapOf("info" to item))
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}

    text = "Clan Tag",
    fontSize = 36.sp,
    fontWeight = FontWeight.Medium,
    modifier = Modifier
        .padding(top = 16.dp)
        .align(Alignment.CenterHorizontally),
    textAlign = TextAlign.Center
)

    // Add your composables here
}


@Composable
fun ClanInfo(clanName: String, clanMembers: List<String>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Clan Name: $clanName", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Members:", style = MaterialTheme.typography.body1)
        clanMembers.forEach { member ->
            Text(text = member, style = MaterialTheme.typography.body2)
        }
    }
}