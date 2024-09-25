
@Composable
fun Graph(data: List<ShipData>) {
    val (tierInfo, nationInfo, typeInfo, totalBattle) = remember(data) {
        val tierInfo = mutableMapOf<Int, Int>()
        val nationInfo = mutableMapOf<String, Int>()
        val typeInfo = mutableMapOf<String, Int>()
        var totalBattle = 0

        for (ship in data) {
            val pvp = ship.pvp
            val battles = pvp.battles
            val curr = AppGlobalData.get(SAVED.warship)[ship.ship_id] ?: continue

            val nation = curr.nation
            val tier = curr.tier
            val type = curr.type

            tierInfo[tier] = (tierInfo[tier] ?: 0) + battles
            nationInfo[nation] = (nationInfo[nation] ?: 0) + battles
            typeInfo[type] = (typeInfo[type] ?: 0) + battles
            totalBattle += battles
        }

        Pair(tierInfo, nationInfo, typeInfo, totalBattle)
    }

    val tierChart = objToChart(tierInfo)
    val avgTier = getAvgTier(tierInfo)
    val nationChart = objToChart(nationInfo, AppGlobalData.get(SAVED.encyclopedia).ship_nations, 10)
    val typeChart = objToChart(typeInfo, AppGlobalData.get(SAVED.encyclopedia).ship_types)

    ScrollableColumn(modifier = Modifier.padding(16.dp)) {
        BarChart(data = tierChart)
        BarChart(data = nationChart)
        PieChart(data = typeChart)
    }
}

    val chart = Chart(mutableListOf(), mutableListOf())
    for ((key, value) in obj) {
        if (value == 0 || value < min) {
            continue
        }

        val label = name?.get(key) ?: key
        chart.x.add(label)
        chart.y.add(value)
    }
    println(chart)
    return chart
}

data class Chart(val x: MutableList<String>, val y: MutableList<Int>)

    var weight = 0.0
    var total = 0.0
    for ((key, curr) in tier) {
        weight += curr * key
        total += curr
    }
    return roundTo(weight / total, 1)
}

fun roundTo(value: Double, decimalPlaces: Int): Double {
    val factor = Math.pow(10.0, decimalPlaces.toDouble())
    return Math.round(value * factor) / factor
}

fun MyChartScreen(viewModel: MyViewModel) {
    val tier by viewModel.tier.observeAsState()
    val nation by viewModel.nation.observeAsState()
    val type by viewModel.type.observeAsState()

    WoWsInfo(hideAds = true) {
        ScrollableColumn {
            BarChart(
                modifier = Modifier.height(300.dp),
                darkMode = AppGlobalData.isDarkMode,
                themeColor = TintColour()[500],
                chartData = tier?.y ?: emptyList(),
                xAxisLabels = tier?.x ?: emptyList()
            )
            PieChart(
                modifier = Modifier.height(300.dp),
                darkMode = AppGlobalData.isDarkMode,
                chartData = nation?.y ?: emptyList(),
                dataLabels = nation?.x ?: emptyList()
            )
            PieChart(
                modifier = Modifier.height(300.dp),
                darkMode = AppGlobalData.isDarkMode,
                chartData = type?.y ?: emptyList(),
                dataLabels = type?.x ?: emptyList()
            )
        }
    }
}


@Composable
fun Graph() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Your graph drawing logic here
    }
}