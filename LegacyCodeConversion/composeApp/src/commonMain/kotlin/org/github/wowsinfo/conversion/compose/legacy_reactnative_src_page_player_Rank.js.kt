
@Composable
fun RankScreen(data: Map<Int, Any>, shipData: Map<Int, List<Any>>) {
    val list = data.values.reversed().toMutableList()
    val sortedData = list.map { it as Map<String, Any> }.sortedByDescending { it["season"] }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Rank - ${data.size}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            sortedData.forEach { season ->
                val seasonNumber = season["season"] as Int
                val shipInfo = shipData[seasonNumber]
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable {
                            if (shipInfo != null && shipInfo.isNotEmpty()) {
                                SafeAction("PlayerShip", mapOf("data" to shipInfo))
                            }
                        }
                ) {
                    Text(
                        text = "- Season $seasonNumber -",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (shipInfo != null && shipInfo.isNotEmpty()) {
                        Icon(
                            painter = painterResource(R.drawable.ic_chevron_right_24px),
                            contentDescription = "Navigate",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}