@Composable
fun Graph(data: List<Ship>) {
    val tierInfo = mutableMapOf<String, Int>()
    val nationInfo = mutableMapOf<String, Int>()
    val typeInfo = mutableMapOf<String, Int>()
    var totalBattle = 0

    data.forEach { ship ->
        val pvp = ship.pvp
        val battles = pvp.battles
        val shipId = ship.ship_id

        val currShip = AppGlobalData.get(SAVED.warship)[shipId]
        if (currShip == null) {
            return@forEach
        }

        val { nation, tier, type } = currShip

        tierInfo[tier] = tierInfo.getOrElse(tier, 0) + battles
        nationInfo[nation] = nationInfo.getOrElse(nation, 0) + battles
        typeInfo[type] = typeInfo.getOrElse(type, 0) + battles
        totalBattle += battles
    }

    val tierChart = objToChart(tierInfo)
    val avgTier = getAvgTier(tierInfo)
    val nationChart = objToChart(
        nationInfo,
        AppGlobalData.get(SAVED.encyclopedia).ship_nations,
        10
    )
    val typeChart = objToChart(typeInfo, AppGlobalData.get(SAVED.encyclopedia).ship_types)

    WoWsInfo(hideAds = true) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Title(
            //     text = "Average Tier - ${avgTier}",
            //     modifier = Modifier.align(Alignment.CenterHorizontally)
            // )
            BarChart(
                style = remember { MaterialTheme.shapes.medium },
                chartData = tierChart.y,
                xAxisLabels = tierChart.x,
                darkMode = AppGlobalData.isDarkMode,
                themeColor = TintColour().get(500) ?: Color.Unspecified
            )
            PieChart(
                style = remember { MaterialTheme.shapes.medium },
                chartData = nationChart.y,
                dataLabels = nationChart.x,
                darkMode = AppGlobalData.isDarkMode,
                themeColor = TintColour().get(500) ?: Color.Unspecified
            )
            PieChart(
                style = remember { MaterialTheme.shapes.medium },
                chartData = typeChart.y,
                dataLabels = typeChart.x,
                darkMode = AppGlobalData.isDarkMode,
                themeColor = TintColour().get(500) ?: Color.Unspecified
            )
        }
    }
}

@Composable
fun objToChart(obj: Map<String, Int>, name: Map<String, String>? = null, min: Int = 0): Pair<List<String>, List<Int>> {
    val chartX = mutableListOf<String>()
    val chartY = mutableListOf<Int>()

    for (key in obj.keys) {
        val value = obj[key] ?: continue
        if (value <= min) {
            continue
        }

        val label = name?.get(key) ?: key
        chartX.add(label)
        chartY.add(value)
    }
    return Pair(chartX, chartY)
}

@Composable
fun getAvgTier(tierInfo: Map<String, Int>): Double {
    var weight = 0.0
    var total = 0
    for ((key, value) in tierInfo.entries) {
        weight += value * key.toInt()
        total += value
    }
    return roundTo(weight / total, 1)
}