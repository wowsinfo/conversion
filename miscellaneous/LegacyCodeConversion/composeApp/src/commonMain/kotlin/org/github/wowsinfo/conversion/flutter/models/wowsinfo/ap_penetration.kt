
const val penetrationValue = 0.5561613
const val gravity = 9.80665
const val seaLevelTemperature = 288.15
const val temperatureLapseRate = 0.0065
const val seaLevelPressure = 101325.0
const val univGasConstant = 8.31447
const val massAir = 0.0289644
const val cwQuadratic = 1
const val angleInterval = 0.1
const val step = 0.1

data class ApPenetration(
    val info: ProjectileInfo,
    val range: Double,
    val verticalAngle: Double
) {
    fun calculatePenetration(): Double {
        // Implement the penetration calculation logic here
        // This is a placeholder for the actual implementation
        return 0.0
    }
}

data class ProjectileInfo(
    val caliber: Double,
    val mass: Double,
    val velocity: Double
)


data class ArmorPiecingInfo(val weight: Double, val diameter: Double, val drag: Double, val velocity: Double, val krupp: Double)

data class ApPenetrationInfo(var penetration: MutableList<Double> = mutableListOf(), var distance: MutableList<Double> = mutableListOf(), var time: MutableList<Double> = mutableListOf())

class ApPenetrationCalculator(private val info: ArmorPiecingInfo, private val range: Double, private val verticalAngle: Double) {

    private val _penetrationValue = 100.0
    private val _step = 0.1
    private val _seaLevelTemperature = 288.15
    private val _temperatureLapseRate = 0.0065
    private val _seaLevelPressure = 101325.0
    private val _gravity = 9.81
    private val _massAir = 0.029
    private val _univGasConstant = 8.314
    private val _cwQuadratic = 0.5

    private fun angles(): List<Double> {
        return listOf(0.0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2)
    }

    fun calculatePenetration(): ApPenetrationInfo {
        val weight = info.weight
        val diameter = info.diameter
        val drag = info.drag
        val velocity = info.velocity
        val krupp = info.krupp

        val cwLinear = 100.0 + 1000.0 / 3.0 * diameter
        val penetrationValue = _penetrationValue * krupp / 2400.0
        val dragConstant = 0.5 * drag * pow((diameter / 2.0), 2.0) * Math.PI / weight

        val penInfo = ApPenetrationInfo()

        for (angle in angles()) {
            var vX = cos(angle) * velocity
            var vY = sin(angle) * velocity

            var x = 0.0
            var y = 0.0
            var t = 0.0

            while (y >= 0) {
                x += vX * _step
                y += vY * _step

                val temperature = _seaLevelTemperature - _temperatureLapseRate * y
                val pressure = _seaLevelPressure *
                        pow(
                            1 - _temperatureLapseRate * y / _seaLevelTemperature,
                            _gravity * _massAir / (_univGasConstant * _temperatureLapseRate)
                        )
                val density = pressure * _massAir / (_univGasConstant * temperature)

                vX -= (_step * dragConstant * density) *
                        (_cwQuadratic * pow(vX, 2.0) + cwLinear * vX)
                vY -= (_step * _gravity) -
                        (_step * dragConstant * density) *
                                (_cwQuadratic * pow(vY, 2.0) + cwLinear * vY.absoluteValue) *
                                vY.sign

                t += _step
            }

            if (x > range * 1.1) break

            val distance = sqrt(pow(vX, 2.0) + pow(vY, 2.0))
            val penetration = penetrationValue *
                    pow(distance, 1.1) *
                    pow(weight, 0.55) /
                    pow(diameter * 1000, 0.65)
            val impactAngle = atan(vY.absoluteValue / vX.absoluteValue)

            penInfo.penetration.add(penetration * cos(impactAngle))
            penInfo.distance.add(x)
            penInfo.time.add(t / 3.0)
        }

        return penInfo
    }
}

    val count = Math.ceil(verticalAngle / angleInterval).toInt()
    return (0 until count).map { it * Math.PI / 180 / 10 }
}


@Serializable
data class ApPenetrationInfo(
    val penetration: List<Double> = emptyList(),
    val distance: List<Double> = emptyList(),
    val time: List<Double> = emptyList()
)