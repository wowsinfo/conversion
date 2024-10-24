
const val secondaryMeasureAxisId = "secondaryMeasureAxisId"

class ShipPenetrationProvider(private val ship: Ship) {
    private val logger = LoggerFactory.getLogger(ShipPenetrationProvider::class.java)
    private val shipModules = ShipModules(ship)
    private var mainGunInfo: Any? = null

    init {
        val tracker = TimeTracker()
        shipModules.unpackModules()
        mainGunInfo = shipModules.gunInfo?.data
        tracker.log("Unpacked modules") // 3ms on a i5-9400###
    }
}

@Composable
fun ShipPenetrationView(ship: Ship) {
    val context = LocalContext.current
    val provider = ShipPenetrationProvider(ship)
    // Implement your UI using Jetpack Compose here
}


val penInfo: ApPenetrationInfo? by lazy { getCurrentPenetration() }

private fun getCurrentPenetration(): ApPenetrationInfo? {
    val gun = mainGunInfo?.guns?.firstOrNull() ?: return null

    var apInfo: ArmorPiecingInfo? = null
    var ap: Projectile? = null
    for (ammo in gun.ammo) {
        val ammoInfo = GameRepository.instance.projectileOf(ammo) ?: continue

        if (ammoInfo.ammoType != "AP") continue
        Logger.info(ammoInfo.ammoType)
        ap = ammoInfo
        apInfo = ap.ap
        break
    }

    if (ap == null || apInfo == null) return null

    return ApPenetration(
        info = apInfo,
        range = mainGunInfo?.range?.toDouble() ?: 0.0,
        verticalAngle = 40
    ).calculatePenetration()
}


data class ChartValue<T, U>(val name: T, val value: U)

class PenInfo(val penetration: List<Double>, val distance: List<Double>, val time: List<Double>)

object ChartUtils {
    val damageColour = "color1"
    val winrateColour = "color2"

    fun convertDefault(name: String, values: List<ChartValue<Int, Double>>, color: String, labelFormatter: (ChartValue<Int, Double>, Int) -> String): Series<ChartValue<Int, Double>, Int> {
        return Series(name, values, color, labelFormatter)
    }
}

data class Series<T, U>(val name: String, val values: List<T>, val color: String, val labelFormatter: (T, Int) -> String)

class PenetrationViewModel(private val penInfo: PenInfo?) {
    private val _penetrationSeries = MutableStateFlow<List<Series<ChartValue<Int, Double>, Int>>>(emptyList())
    val penetrationSeries: StateFlow<List<Series<ChartValue<Int, Double>, Int>>> = _penetrationSeries

    init {
        _penetrationSeries.value = getPenetrationSeries()
    }

    private fun getPenetrationSeries(): List<Series<ChartValue<Int, Double>, Int>> {
        val penInfo = penInfo ?: return emptyList()
        val penValues = penInfo.penetration
        val penDists = penInfo.distance
        val shellTime = penInfo.time

        val pValues = mutableListOf<ChartValue<Int, Double>>()
        val tValues = mutableListOf<ChartValue<Int, Double>>()
        for (i in penValues.indices) {
            pValues.add(ChartValue(penDists[i].toInt(), penValues[i]))
            tValues.add(ChartValue(penDists[i].toInt(), shellTime[i]))
        }

        val penSeries = ChartUtils.convertDefault(
            "penetration",
            values = pValues,
            color = ChartUtils.damageColour,
            labelFormatter = { value, _ -> value.name.toString() }
        )

        val timeSeries = ChartUtils.convertDefault(
            "time",
            values = tValues,
            color = ChartUtils.winrateColour,
            labelFormatter = { value, _ -> value.name.toString() }
        ).apply { setAttribute("measureAxisIdKey", "fixedKey") }

        return listOf(penSeries, timeSeries)
    }

    private fun Series<ChartValue<Int, Double>, Int>.setAttribute(key: String, value: String) {
        // Implementation for setting attribute
    }
}

    val penList = penInfo?.penetration ?: return null
    val minimum = (penList.last() / 10).roundToInt() * 10
    val maximum = (penList.first() / 10).roundToInt() * 10

    val segment = (maximum - minimum) / count.toDouble()
    if (segment <= 0) return null

    val ticks = List(count + 2) { index -> TickSpec(minimum + segment * index) }

    return StaticNumericTickProviderSpec(ticks)
}

    val deviceWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    Logger.info("deviceWidth: $deviceWidth")
    if (deviceWidth > 900) return null
    val distanceList = penInfo?.distance ?: return null
    val minimum = 0.0
    val maximum = distanceList.last().toDouble()
    val count = (deviceWidth / 120).toInt()
    Logger.info("count $count, minimum $minimum, maximum $maximum")
    val segment = (((maximum - minimum) / count) / 1000).roundToInt() * 1000.0
    Logger.info("segment $segment")

    return StaticNumericTickProviderSpec(
        List(count + 1) { index -> 
            TickSpec((minimum + segment * index).roundToInt().toDouble())
        }
    )
}


@Composable
fun getThemePalette(): Color {
    val colors: MaterialColors = MaterialTheme.colors
    return if (colors.isLight) {
        Color.Black
    } else {
        Color.White
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
            count++
        }) {
            Text("Click me")
        }
    }
}