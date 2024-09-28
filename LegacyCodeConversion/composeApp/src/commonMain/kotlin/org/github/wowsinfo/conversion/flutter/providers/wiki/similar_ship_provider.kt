
data class Ship(val id: String, val type: String, val tier: Int, val isSpecialGroup: Boolean)

class GameRepository {
    companion object {
        val instance = GameRepository()
    }

    val shipList: List<Ship> = listOf() // Populate with actual ship data
}

class SimilarShipProvider(private val ship: Ship) {
    private val _similarShips = MutableStateFlow<List<Ship>>(emptyList())
    val similarShips: StateFlow<List<Ship>> = _similarShips.asStateFlow()

    init {
        getSimilarShips()
    }

    private fun getSimilarShips() {
        val ships = GameRepository.instance.shipList
        val similar = ships.filter { 
            it.id != ship.id && 
            it.type == ship.type && 
            it.tier == ship.tier && 
            it.isSpecialGroup == ship.isSpecialGroup 
        }
        _similarShips.value = similar
    }

    val hasSimilarShips: Boolean
        get() = _similarShips.value.isNotEmpty()
}

    private val _chartHeight = 25.0 * maxOf(similarShips.size, 5).toDouble()
    val chartHeight: Double get() = _chartHeight

    private var _averageDamage: Double = 0.0
    val averageDamage: String get() = _averageDamage.roundToInt().toDecimalString()

    private var _averageWinrate: Double = 0.0
    val averageWinrate: String get() = _averageWinrate.asPercentString()

    private var _averageFrags: Double = 0.0
    val averageFrags: String get() = _averageFrags.toDecimalString()

    private var _totalBattles: Int = 0
    val totalBattles: String get() = _totalBattles.toDecimalString()

    private lateinit var _winrateSeries: List<Series<ChartValue, String>>
    val winrateSeries: List<Series<ChartValue, String>> get() = _winrateSeries

    private lateinit var _fragsSeries: List<Series<ChartValue, String>>
    val fragsSeries: List<Series<ChartValue, String>> get() = _fragsSeries

    private lateinit var _damageSeries: List<Series<ChartValue, String>>
    val damageSeries: List<Series<ChartValue, String>> get() = _damageSeries

    private lateinit var _battleSeries: List<Series<ChartValue, String>>
    val battleSeries: List<Series<ChartValue, String>> get() = _battleSeries

    fun extractShipAdditional() {
        val damage = AverageCounter()
        val frags = AverageCounter()
        val winrate = AverageCounter()
        val battles = TotalCounter()
        val damageChart = mutableListOf<SimilarChartValue>()
        val fragsChart = mutableListOf<SimilarChartValue>()
        val winrateChart = mutableListOf<SimilarChartValue>()
        val battlesChart = mutableListOf<SimilarChartValue>()

        val allShips = similarShips + ship
        for (ship in allShips) {
            val info = GameRepository.instance.shipAdditionalOf(ship.id.toString())
            if (info == null) {
                Logger.warning("No ship additional info for ${ship.name}")
                continue
            }

            damage.add(info.damage)
            frags.add(info.frags)
            winrate.add(info.winrate)
            val shipBattleCount = info.battles ?: 0
            battles.add(shipBattleCount)

            val shipName = Localisation.instance.stringOf(ship.name)
            if (shipName == null) {
                assert(false) { "No localisation for ${ship.name}, unknown ship" }
                continue
            }

            damageChart.add(ChartValue(shipName, info.damage))
            fragsChart.add(ChartValue(shipName, info.frags))
            winrateChart.add(ChartValue(shipName, info.winrate))
            battlesChart.add(ChartValue(shipName, shipBattleCount))
        }

        _averageDamage = damage.average
        _averageFrags = frags.average
        _averageWinrate = winrate.average
        _totalBattles = battles.total.toInt()

        damageChart.sortByDescending { it.value }
        _damageSeries = listOf(
            ChartUtils.convertDefault(
                "damage",
                values = damageChart,
                color = ChartUtils.damageColour,
                labelFormatter = { v, _ -> v.value.toDecimalString() }
            )
        )

        fragsChart.sortByDescending { it.value }
        _fragsSeries = listOf(
            ChartUtils.convertDefault(
                "frags",
                values = fragsChart,
                color = ChartUtils.fragsColour,
                labelFormatter = { v, _ -> v.value.toDecimalString() }
            )
        )

        winrateChart.sortByDescending { it.value }
        _winrateSeries = listOf(
            ChartUtils.convertDefault(
                "winrate",
                values = winrateChart,
                color = ChartUtils.winrateColour,
                labelFormatter = { v, _ -> v.value.asPercentString() }
            )
        )

        battlesChart.sortByDescending { it.value }
        _battleSeries = listOf(
            ChartUtils.convertDefault(
                "battles",
                values = battlesChart,
                color = ChartUtils.battleColour,
                labelFormatter = { v, _ -> v.value.toDecimalString() }
            )
        )
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click Me")
        }
    }
}