@Composable
fun Friend() {
    val all = AppGlobalData.get(LOCAL.friendList)

    val player = getPlayer(all)
    val clan = getClan(all)

    var goodWidth by remember { mutableStateOf(bestWidth(400)) }

    LaunchedEffect(Unit) {
        goodWidth = bestWidth(400, LocalDensity.current.density)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionTitle(title = "${lang.friend_clan_title} - ${player.size}")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            clan.forEach { item ->
                ListItem(
                    modifier = Modifier.width(goodWidth.value),
                    title = { Text(text = item.tag) },
                    description = { Text(text = item.clan_id.toString()) },
                    trailing = {
                        IconButton(
                            icon = Icons.Default.Close,
                            onClick = { removeClan(item) }
                        )
                    }
                )
            }
        }

        SectionTitle(title = "${lang.friend_player_title} - ${player.size}")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            player.forEach { item ->
                ListItem(
                    modifier = Modifier.width(goodWidth.value),
                    title = { Text(text = item.nickname) },
                    description = { Text(text = item.account_id.toString()) },
                    trailing = {
                        IconButton(
                            icon = Icons.Default.Close,
                            onClick = { removeFriend(item) }
                        )
                    }
                )
            }
        }
    }

    fun removeFriend(info: FriendInfo) {
        val str = LOCAL.friendList
        all.player.remove(info.account_id)
        AppGlobalData.set(str, all)
        SafeStorage.set(str, all)
        player = getPlayer(AppGlobalData.get(str))
    }

    fun removeClan(info: ClanInfo) {
        val str = LOCAL.friendList
        all.clan.remove(info.clan_id)
        AppGlobalData.set(str, all)
        SafeStorage.set(str, all)
        clan = getClan(AppGlobalData.get(str))
    }
}

@Composable
private fun getPlayer(all: FriendList): List<FriendInfo> {
    return all.player.values.toList()
}

@Composable
private fun getClan(all: FriendList): List<ClanInfo> {
    return all.clan.values.toList()
}