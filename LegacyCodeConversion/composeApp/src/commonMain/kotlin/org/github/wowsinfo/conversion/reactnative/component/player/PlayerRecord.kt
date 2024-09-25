
@Composable
fun PlayerRecord() {
    val goodWidth = remember { bestWidth(400).toDp() }

    Box(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(text = lang.sectionTitle)
        // Other UI components can be added here
    }
}

fun bestWidth(maxWidth: Int): Int {
    // Implement the logic to calculate the best width
    return round(maxWidth * 0.8).toInt() // Example logic
}

fun RecordsView(data: Data?) {
    if (data == null) {
        return
    }

    val max = listOf(
        RecordItem(lang.record_max_damage_dealt, data.max_damage_dealt, data.max_damage_dealt_ship_id),
        RecordItem(lang.record_max_frags_battle, data.max_frags_battle, data.max_frags_ship_id),
        RecordItem(lang.record_max_planes_killed, data.max_planes_killed, data.max_planes_killed_ship_id),
        RecordItem(lang.record_max_xp, data.max_xp, data.max_xp_ship_id),
        RecordItem(lang.record_max_ships_spotted, data.max_ships_spotted, data.max_ships_spotted_ship_id),
        RecordItem(lang.record_max_total_agro, data.max_total_agro, data.max_total_agro_ship_id),
        RecordItem(lang.record_max_damage_scouting, data.max_damage_scouting, data.max_scouting_damage_ship_id)
    )

    val records = listOf(
        RecordDetail(lang.warship_artillery_main, data.main_battery),
        RecordDetail(lang.warship_artillery_secondary, data.second_battery),
        RecordDetail(lang.warship_torpedoes, data.torpedoes),
        RecordDetail(lang.warship_aircraft, data.aircraft),
        RecordDetail(lang.warship_ramming, data.ramming)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        SectionTitle(title = lang.record_title)
        LazyColumn {
            items(max) { item -> RenderMax(item) }
        }
        LazyColumn {
            items(records) { item -> RenderRecord(item) }
        }
    }
}

    val newWidth = event.layout.width
    setState { goodWidth = bestWidth(400, newWidth) }
}

fun RenderMax(data: Data) {
    val recordStyle = styles.record
    val containerStyle = styles.container
    val (num, id, name) = data

    if (id == null) {
        return
    }

    val ship = AppGlobalData.get(SAVED.warship)[id]
    
    Column(
        modifier = Modifier.width(goodWidth).then(recordStyle)
    ) {
        Box(modifier = containerStyle) {
            WarshipCell(
                item = ship,
                scale = 2,
                onClick = { SafeAction("WarshipDetail", mapOf("item" to ship)) }
            )
        }
        Box(modifier = containerStyle) {
            InfoLabel(title = name, info = num)
        }
    }
}

fun RenderRecord(item: Item) {
    val recordStyle = styles.record
    val containerStyle = styles.container
    val name = item.name
    val data = item.data
    val frags = data.frags
    val maxFragsBattle = data.max_frags_battle
    val maxFragsShipId = data.max_frags_ship_id
    val hits = data.hits
    val shots = data.shots

    if (maxFragsShipId == null) {
        return
    }

    val bestShip = AppGlobalData.get(SAVED.warship)[maxFragsShipId]

    Column(modifier = Modifier.width(goodWidth)) {
        SectionTitle(title = name, center = true)
        Row(modifier = recordStyle) {
            Column(modifier = containerStyle) {
                Paragraph(text = lang.record_best_ship)
                WarshipCell(
                    item = bestShip,
                    scale = 2,
                    onClick = { SafeAction("WarshipDetail", mapOf("item" to bestShip)) }
                )
            }
            Column(modifier = containerStyle) {
                InfoLabel(title = lang.weapon_total_frags, info = frags.toString())
                InfoLabel(title = lang.weapon_max_frags, info = maxFragsBattle.toString())
                if (hits != null) {
                    InfoLabel(
                        title = lang.weapon_hit_ratio,
                        info = "${roundTo((hits.toDouble() / shots) * 100, 2)}%"
                    )
                }
            }
        }
    }
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}

    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    horizontalArrangement = Arrangement.SpaceAround
) {
    // Add your content here
}

    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    // Add your content here
}


@Serializable
data class PlayerRecord(val id: String, val name: String, val score: Int)

@Composable
fun PlayerRecordView(playerRecord: PlayerRecord) {
    val playerName = remember { playerRecord.name }
    val playerScore = remember { playerRecord.score }

    // Jetpack Compose UI elements to display player information
    // Example: Text(playerName)
    // Example: Text(playerScore.toString())
}