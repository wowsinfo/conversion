
@Composable
fun PlayerCell(item: Any, player: Boolean, clan: Boolean, width: Int) {
    val playerId = if (player && item is Player) item.account_id else null
    val clanId = if (clan && item is Clan) item.clan_id else null
    Column(
        modifier = Modifier
            .clickable { /* Handle click */ }
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = item.nickname ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            if (playerId != null || clanId != null) {
                Caption(
                    text = playerId?.toString() ?: clanId.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

data class Player(val nickname: String, val account_id: Int)

data class Clan(val tag: String, val clan_id: Int)