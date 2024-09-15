
/// This is a fixed value if a secondary axis is needed
const _secondaryMeasureAxisId = 'secondaryMeasureAxisId';

class ShipPenetrationProvider {
  private val logger: Logger = Logger("ShipPenetrationProvider")
  private val context: BuildContext
  private val ship: Ship
  private lateinit var shipModules: ShipModules

  init {
    TimeTracker().use { tracker ->
      // TODO: how to possibily get ship main gun info without doing the full unpacking???
      shipModules = ShipModules(ship).apply {
        unpackModules()
      }
      tracker.log(message = "Unpacked modules") // 3ms on a i5-9400
    }

    _mainGunInfo = shipModules.gunInfo?.data
  }

  private var _penInfo: ApPenetrationInfo? = getCurrentPenetration()

  private fun getCurrentPenetration(): ApPenetrationInfo? {
    val gun = _mainGunInfo?.guns.firstOrNull() ?: return null

    armorPiecingInfo?.let { apInfo ->
      projectile?.let { ap ->
        if (ap.ammoType == "AP") {
          return ApPenetration(info = apInfo, range = _mainGunInfo?.range.toDouble() ?: 0.0, verticalAngle = 40).calculatePenetration()
        }
      }
    }

    return null
  }

  // provide the series data
  private val penetrationSeries: List<Series<ChartValue, Int>> get() = _penetrationSeries

  private val _penetrationSeries: List<Series<ChartValue, Int>> by lazy { getPenetrationSeries() }

  private fun getPenetrationSeries(): List<Series<ChartValue, Int>> {
    val penInfo = _penInfo ?: return emptyList()
    val penValues = penInfo.penetration
    val penDists = penInfo.distance
    val shellTime = penInfo.time

    val pValues = mutableListOf<ChartValue<Int, Double>>()
    val tValues = mutableListOf<ChartValue<Int, Double>>()
    for (i in penValues.indices) {
      pValues.add(ChartValue(penDists[i].toInt(), penValues[i]))
      tValues.add(ChartValue(penDists[i].toInt(), shellTime[i]))
    }

    return listOf(
      ChartUtils.convertDefault(
        "penetration",
        values = pValues,
        color = ChartUtils.damageColour,
        labelFormatter = { value, index -> value.name.toString() }
      ),
      ChartUtils.convertDefault(
        "time",
        values = tValues,
        color = ChartUtils.winrateColour,
        labelFormatter = { value, index -> value.name.toString() }
      ).setAttribute(measureAxisIdKey, _secondaryMeasureAxisId) // a fixed key
    )
  }

  // customise the penetration axis
  fun buildPenetrationSpec(count: Int): NumericTickProviderSpec? {
    logger.info("buildPenetrationSpec: $count")
    val penList = _penInfo?.penetration ?: return null

    // round min and max to nearest 10
    val minimum = (penList.last / 10).round() * 10
    val maximum = (penList.first / 10).round() * 10

    val segment = (maximum - minimum) / count
    logger.fine("segment: $segment, minimum: $minimum, maximum: $maximum")
    if (segment <= 0) return null

    return StaticNumericTickProviderSpec(
      List.generate(count + 2) { index ->
        TickSpec(minimum + segment * index)
      }
    )
  }

  // The domain (distance) spec is based on the device with
  fun buildDistanceSpec(): NumericTickProviderSpec? {
    val deviceWidth = MediaQuery.of(context).size.width
    logger.info("deviceWidth: $deviceWidth")

    // only needed for smaller screens
    if (deviceWidth > 900) return null

    val distanceList = _penInfo?.distance ?: return null
    val minimum = 0
    val maximum = distanceList.last
    val count = (deviceWidth / 120).round()

    logger.info("count $count, minimum $minimum, maximum $maximum")
    val segment = (((maximum - minimum) / count) / 1000).roundToDouble() * 1000
    logger.info("segment $segment")

    return StaticNumericTickProviderSpec(
      List.generate(count + 1) { index ->
        TickSpec((minimum + segment * index).roundToDouble())
      }
    )
  }

  fun getThemePalette(): Color {
    val theme = Theme.of(context)
    return if (theme.brightness == Brightness.light) MaterialPalette.black else MaterialPalette.white
  }
}