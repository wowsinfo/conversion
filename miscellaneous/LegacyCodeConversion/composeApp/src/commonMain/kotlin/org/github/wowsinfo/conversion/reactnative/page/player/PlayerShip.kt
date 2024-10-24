
@Composable
fun PlayerShip(data: List<Ship>, rating: Float?) {
    var ships by remember { mutableStateOf(data.sortedByDescending { it.lastBattleTime }) }
    var overallRating by remember { mutableStateOf(rating ?: getOverallRating(ships)) }
    var filter by remember { mutableStateOf(Filter()) }
    var sortStr by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(ships) { ship ->
            WarshipCell(ship = ship, rating = overallRating)
        }
    }
}

fun getOverallRating(ships: List<Ship>): Float {
    // Implementation for calculating overall rating
}

data class Ship(val lastBattleTime: Long)

@Composable
fun WarshipCell(ship: Ship, rating: Float) {
    // Implementation for displaying a ship cell
}

    private var filter: String? = null
    private var rating: Float = 0f
    private var data: List<DataType> = listOf()

    fun componentDidUpdate(newFilter: String?) {
        if (newFilter != null) {
            if (newFilter == filter) {
                rating = getOverallRating(data)
                return
            }
            filter = newFilter
            updateShip(filter)
        }
    }

    private fun getOverallRating(data: List<DataType>): Float {
        // Implementation for calculating overall rating
    }

    private fun updateShip(filter: String) {
        // Implementation for updating ship based on filter
    }
}

fun ShipInfoScreen(viewModel: ShipViewModel) {
    val state by viewModel.state.collectAsState()
    val data = state.data
    val rating = state.rating
    val sortingMethod = listOf(
        SortingMethod(lang.ship_sort_battle, "pvp.battles"),
        SortingMethod(lang.warship_avg_damage, "avgDmg"),
        SortingMethod(lang.warship_avg_winrate, "avgWinrate"),
        SortingMethod(lang.warship_avg_frag, "avgFrags"),
        SortingMethod(lang.ship_sort_colour, "rating"),
        SortingMethod("AP", "ap"),
        SortingMethod(lang.basic_last_battle, "last_battle_time"),
        SortingMethod(lang.record_max_damage_dealt, "pvp.max_damage_dealt"),
        SortingMethod(lang.record_max_xp, "pvp.max_xp"),
        SortingMethod(lang.record_max_frags_battle, "pvp.max_frags_battle")
    )

    val ratingColor = getColour(rating)
    MaterialTheme(colors = MaterialTheme.colors.copy(primary = ratingColor)) {
        Column {
            WoWsInfo(
                hideAds = true,
                title = "${lang.wiki_warship_footer} - ${data.size}",
                onPress = { viewModel.updateShip() }
            ) {
                RatingButton(rating = rating)
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    content = {
                        items(data) { item ->
                            RenderShip(item)
                        }
                    }
                )
                FooterPlus {
                    LazyRow {
                        items(sortingMethod) { item ->
                            Button(
                                onClick = { viewModel.sortData(item.value) },
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(item.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

    val (data, sortStr) = state
    println("$sortStr $v")
    if (v == sortStr) {
        // Simply reverse it
        state = state.copy(data = data.reversed(), sortStr = "")
    } else {
        state = state.copy(
            data = data.sortedByDescending { guard(it, v) },
            sortStr = v
        )
    }
}

fun guard(item: YourDataType, v: String): Int {
    // Implement your Guard logic here
}

fun RenderShip(item: ShipItem) {
    val ship = AppGlobalData.get(SAVED.warship)[item.shipId]
    Box(modifier = Modifier.clickable { SafeAction("PlayerShipDetail", data = item) }) {
        WarshipCell(ship = ship, scale = 2)
        SimpleRating(info = item)
    }
}

    val sorted = filterShip(data, original)
    if (sorted == null) {
        setState { this.data = original }
    } else {
        val rating = getOverallRating(sorted)
        setState { 
            this.data = sorted
            this.rating = rating 
        }
    }
}


@Composable
fun PlayerShip() {
    Surface(color = MaterialTheme.colorScheme.background) {
        // Your PlayerShip UI implementation here
    }
}

@Preview
@Composable
fun PreviewPlayerShip() {
    PlayerShip()
}