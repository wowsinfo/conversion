
@Composable
fun DetailedScreen(data: YourDataType, navController: NavController) {
    var state by remember { mutableStateOf(data) }

    ScrollableColumn(modifier = Modifier.fillMaxSize()) {
        WoWsInfo(data = state)
        WarshipCell(data = state)
        InfoLabel(data = state)
        DetailedInfo(data = state)
        RatingButton(onClick = { /* Handle click */ })
    }
}

fun WarshipInfoScreen(viewModel: WarshipViewModel) {
    val data by viewModel.data.collectAsState()

    if (data == null) {
        // Navigate back
        navController.popBackStack()
        return
    }

    val (pvp, shipId, rating) = data
    val ship = AppGlobalData.get(SAVED.warship)[shipId]
    val overall = AppGlobalData.get(SAVED.pr)[shipId]

    // Set the theme color
    MaterialTheme(colors = MaterialTheme.colors.copy(primary = getColour(rating))) {
        WoWsInfo(
            onClick = if (ship == null) null else { 
                SafeAction("WarshipDetail", ship) 
            },
            title = lang.wiki_section_title
        ) {
            RatingButton(rating = rating)
            ScrollableColumn(
                contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp)
            ) {
                WarshipCell(ship = ship, scale = 3)
                renderNumberDiff(pvp, overall)
                DetailedInfo(data = data)
            }
        }
    }
}

fun RenderNumberDiff(data: Data?, overall: Overall?) {
    if (overall == null || data == null) {
        return
    }
    val horizontal = styles.horizontal
    val battles = data.battles
    val wins = data.wins
    val damageDealt = data.damage_dealt
    val frags = data.frags
    val averageDamageDealt = overall.average_damage_dealt
    val averageFrags = overall.average_frags
    val winRate = overall.win_rate

    val dmgDiff = normalise(damageDealt / battles - averageDamageDealt, 0)
    val winrateDiff = normalise((wins / battles) * 100 - winRate, 2)
    val fragDiff = normalise(frags / battles - averageFrags, 2)

    Row(modifier = horizontal) {
        InfoLabel(
            style = getColor(dmgDiff),
            info = dmgDiff.toString(),
            title = lang.ship_detail_damage
        )
        InfoLabel(
            style = getColor(winrateDiff),
            info = "${winrateDiff}%",
            title = lang.ship_detail_winrate
        )
        InfoLabel(
            style = getColor(fragDiff),
            info = fragDiff.toString(),
            title = lang.ship_detail_frag
        )
    }
}

    return when {
        diff == 0 -> null
        diff > 0 -> Color.Green
        else -> Color.Red
    }
}

    val rounded = roundTo(diff, digit)
    return if (rounded <= 0) {
        rounded.toString()
    } else {
        "+$rounded"
    }
}

fun roundTo(value: Double, places: Int): Double {
    val factor = Math.pow(10.0, places.toDouble())
    return Math.round(value * factor) / factor
}


@Composable
fun Container() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        // Content goes here
    }
}


@Composable
fun HorizontalLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Add your composable items here
    }
}


@Composable
fun Detailed() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Your UI components go here
        }
    }
}

@Preview
@Composable
fun PreviewDetailed() {
    Detailed()
}