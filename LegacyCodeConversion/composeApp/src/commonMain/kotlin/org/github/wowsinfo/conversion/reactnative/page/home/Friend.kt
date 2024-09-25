
@Composable
fun Friend() {
    val all = remember { AppGlobalData.get(LOCAL.friendList) }
    val player = getPlayer(all)
    val clan = getClan(all)
    val goodWidth = bestWidth(400)

    Box(modifier = Modifier.fillMaxWidth().width(goodWidth.dp)) {
        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineContent = { Text(text = player) },
            trailingContent = {
                IconButton(onClick = { /* Handle click */ }) {
                    // Icon here
                }
            }
        )
        // Additional UI components for clan can be added here
    }
}

    val newWidth = event.layout.width
    setState { goodWidth = bestWidth(400, newWidth) }
}

    val player = mutableListOf<Player>()
    for (ID in all.player.keys) {
        player.add(all.player[ID]!!)
    }
    return player
}

    val clan = mutableListOf<Clan>()
    for (ID in all.clan.keys) {
        clan.add(all.clan[ID]!!)
    }
    return clan
}

fun ClanAndPlayerList(player: List<Player>, clan: List<Clan>, goodWidth: Dp, updateBestWidth: () -> Unit) {
    LaunchedEffect(Unit) {
        updateBestWidth()
    }

    Column {
        SectionTitle(title = "${lang.friend_clan_title} - ${clan.size}")

        LazyColumn {
            items(clan) { item ->
                ListItem(
                    modifier = Modifier.width(goodWidth),
                    text = { Text(item.tag) },
                    secondaryText = { Text(item.clan_id.toString()) },
                    trailing = {
                        IconButton(onClick = { removeClan(item) }) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = Color.Gray)
                        }
                    },
                    modifier = Modifier.clickable { pushToClan(item) }
                )
            }
        }

        SectionTitle(title = "${lang.friend_player_title} - ${player.size}")

        LazyColumn {
            items(player) { item ->
                ListItem(
                    modifier = Modifier.width(goodWidth),
                    text = { Text(item.nickname) },
                    secondaryText = { Text(item.account_id.toString()) },
                    trailing = {
                        IconButton(onClick = { removeFriend(item) }) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = Color.Gray)
                        }
                    },
                    modifier = Modifier.clickable { pushToPlayer(item) }
                )
            }
        }
    }
}

    val str = LOCAL.friendList
    val playerData = AppGlobalData.get(str)
    playerData.player.remove(info.accountId)
    SafeStorage.set(str, playerData)
    setState { player = getPlayer(AppGlobalData.get(str)) }
}

    val str = LOCAL.friendList
    val appData = AppGlobalData.get(str)
    appData.clan.remove(info.clanId)
    SafeStorage.set(str, appData)
    setState { clan = getClan(appData) }
}

    SafeAction("Statistics", mapOf("info" to info))
}

    SafeAction("ClanInfo", mapOf("info" to info))
}


@Composable
fun Container() {
    Box(modifier = Modifier.fillMaxHeight()) {
        // Your content goes here
    }
}

    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .horizontalScroll(rememberScrollState())
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        // Add your items here
    }
}


@Composable
fun Friend() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Friend Component")
        // Add more UI elements as needed
    }
}