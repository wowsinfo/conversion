
@Composable
fun Rank(data: Map<String, ShipData>, ship: Ship) {
    val list = remember(data) {
        data.entries.map { entry ->
            entry.value.apply { season = entry.key.toInt() }
        }.reversed()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list.size) { index ->
            val curr = list[index]
            Box(modifier = Modifier.padding(8.dp)) {
                Text(text = "Season: ${curr.season}", fontSize = 20.sp)
                // Additional UI elements can be added here
            }
        }
    }

    // Request ship info
    // CoroutineScope(Dispatchers.IO).launch {
    //     val shipInfo = SafeFetch.get(WoWsAPI.RankShipInfo, ship)
    // }
}

fun RankScreen(viewModel: RankViewModel) {
    val data by viewModel.data.collectAsState()
    val ship by viewModel.ship.collectAsState()

    if (data.isNullOrEmpty()) {
        return
    }

    Log.d("RankScreen", data.toString())

    Column {
        Text(text = "${lang.tab_rank_title} - ${data.size}", style = MaterialTheme.typography.h6)

        LazyColumn {
            items(data) { item ->
                val season = item.season
                val shipData = ship[season]
                Log.d("ShipData", shipData.toString())

                val modifier = Modifier.padding(8.dp)

                if (shipData != null && shipData.isNotEmpty()) {
                    Box(modifier = modifier.clickable { SafeAction("PlayerShip", shipData) }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "- ${lang.rank_season_title} $season -", style = MaterialTheme.typography.h6)
                            // Text(text = "$emoji $rank $emoji", style = MaterialTheme.typography.body1)
                            renderSeasonInfo(item)
                        }
                    }
                } else {
                    Box(modifier = modifier) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "- ${lang.rank_season_title} $season -", style = MaterialTheme.typography.h6)
                            renderSeasonInfo(item)
                        }
                    }
                }
            }
        }
    }
}

    if (data == null) {
        return null
    }
    val rankKey = data.keys.firstOrNull { it != "season" } ?: return null
    val rankData = data[rankKey] as? Map<String, Any> ?: return null
    val rankSolo = rankData["rank_solo"]
    val rankDiv2 = rankData["rank_div2"]
    val rankDiv3 = rankData["rank_div3"]
    
    val info = rankSolo ?: rankDiv2 ?: rankDiv3 ?: return null
    
    return { Info6Icon(data = info, compact = true) }
}


@Composable
fun Container() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        // Content goes here
    }
}


@Composable
fun CenteredText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}


@Composable
fun Rank() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Rank", style = MaterialTheme.typography.h4)
        // Additional UI elements can be added here
    }
}