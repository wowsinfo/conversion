
@Composable
fun Info6Icon(data: Any? = null, compact: Boolean = false, topOnly: Boolean = false) {
    var cellWidth by remember { mutableStateOf(bestWidth(100)) }
    var bestItemWidth by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                val goodWidth = layoutCoordinates.size.width
                bestItemWidth = bestWidth(400, goodWidth)
            }
            .width(cellWidth.dp)
    ) {
        // Add your IconLabel components here based on the data
    }
}

    return null
}

    val battles: Int,
    val wins: Int,
    val damageDealt: Int,
    val frags: Int,
    val xp: Int,
    val survivedBattles: Int,
    val mainBattery: Int
)

@Composable
fun PlayerStatsView(playerStats: PlayerStats) {
    Column {
        Text("Battles: ${playerStats.battles}")
        Text("Wins: ${playerStats.wins}")
        Text("Damage Dealt: ${playerStats.damageDealt}")
        Text("Frags: ${playerStats.frags}")
        Text("XP: ${playerStats.xp}")
        Text("Survived Battles: ${playerStats.survivedBattles}")
        Text("Main Battery: ${playerStats.mainBattery}")
    }
}

fun StatsView(
    data: StatsData,
    compact: Boolean,
    topOnly: Boolean,
    cellWidth: Dp,
    updateBestWidth: () -> Unit
) {
    val (battles, survivedBattles, wins, damageDealt, xp, frags, hits, shots) = data
    val death = battles - survivedBattles

    val labelStyle = Modifier.width(cellWidth)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = if (compact) 0.dp else 16.dp)
            .onGloballyPositioned { updateBestWidth() }
    ) {
        IconLabel(icon = "Battle", info = battles.toString(), modifier = labelStyle)
        IconLabel(icon = "WinRate", info = "${roundTo((wins.toFloat() / battles) * 100, 2)}%", modifier = labelStyle)
        IconLabel(icon = "Damage", info = roundTo(damageDealt / battles.toFloat()).toString(), modifier = labelStyle)

        if (!topOnly) {
            IconLabel(icon = "EXP", info = roundTo(xp / battles.toFloat()).toString(), modifier = labelStyle)
            IconLabel(icon = "KillDeathRatio", info = roundTo(frags.toFloat() / death, 2).toString(), modifier = labelStyle)
            IconLabel(icon = "HitRatio", info = "${roundTo((hits.toFloat() / shots) * 100, 2)}%", modifier = labelStyle)
        }
    }
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { state = !state }) {
            Text(text = if (state) "ON" else "OFF")
        }
        if (state) {
            Text(text = "The state is ON")
        } else {
            Text(text = "The state is OFF")
        }
    }
}