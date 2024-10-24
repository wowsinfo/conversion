
@Composable
fun PlayerCell(item: Item, player: Boolean, clan: Boolean, width: Int) {
    if (player) {
        ListItem(
            modifier = Modifier.width(width.dp),
            headlineContent = { Text(item.nickname) },
            trailingContent = { renderPlayerRight(item.accountId) },
            modifier = Modifier.clickable { pushPlayer(item) }
        )
    } else if (clan) {
        ListItem(
            modifier = Modifier.width(width.dp),
            headlineContent = { Text(item.tag) },
            trailingContent = { renderClanRight(item.clanId) },
            modifier = Modifier.clickable { pushClan(item) }
        )
    } else {
        Text("???")
    }
}

fun RenderPlayerRight(accountId: String) {
    Text(text = accountId, style = MaterialTheme.typography.body1)
}

fun RenderClanRight(clanId: String) {
    Text(text = clanId, style = MaterialTheme.typography.body1)
}

    SafeAction("Statistics", mapOf("info" to item))
}

    SafeAction("ClanInfo", mapOf("info" to item))
}


@Composable
fun IDComponent() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("ID")
    }
}


@Composable
fun PlayerCell() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Add your player cell content here
    }
}