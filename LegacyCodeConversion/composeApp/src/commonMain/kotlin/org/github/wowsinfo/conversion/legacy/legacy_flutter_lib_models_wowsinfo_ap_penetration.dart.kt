
class ApPenetration(private val info: ArmorPiecingInfo, private val range: Double, private val verticalAngle: Double) {
    fun calculatePenetration(): ApPenetrationInfo {
        val weight = info.weight
        val diameter = info.diameter
        val drag = info.drag
        val velocity = info.velocity
        val krupp = info.krupp

        // linear drag coefficient
        val cwLinear = 100.0 + 1000.0 / 3.0 * diameter
        val penetrationValue = _penetrationValue * krupp / 2400.0
        val dragConstant = 0.5 * drag * (diameter / 2.0).pow(2.0) * PI / weight

        val penInfo = ApPenetrationInfo()

        // for each alpha angle do
        angles().forEach { angle ->
            var vX = cos(angle) * velocity
            var vY = sin(angle) * velocity

            var x = 0.0
            var y = 0.0
            var t = 0.0 // time taken

            // follow flight path until shell hits ground again
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
                        (_cwQuadratic * vX.pow(2.0) + cwLinear * vX)
                vY -= (_step * _gravity) -
                        (_step * dragConstant * density) *
                                (_cwQuadratic * vY.pow(2.0) + cwLinear * abs(vY)) * sign(vY)

                t += _step
            }

            if (x > range * 1.1) return@forEach
            // TODO: consider ricochet and also maybe we should set a max distance

            val distance = sqrt(vX.pow(2.0) + vY.pow(2.0))
            // penetration formula
            val penetration = penetrationValue *
                    distance.pow(1.1) *
                    weight.pow(0.55) /
                    (diameter * 1000).pow(0.65)
            // impact angle on belt armour
            val impactAngle = atan(abs(vY) / vX)

            penInfo.penetration.add(penetration * cos(impactAngle))
            penInfo.distance.add(x)
            // scaling of in-game time
            penInfo.time.add(t / 3.0)
        }

        return penInfo
    }

    private fun angles() = (0..(verticalAngle / _angleInterval).toInt()).map { i -> i / 10 * PI / 180 }
}

class ApPenetrationInfo {
    val penetration: MutableList<Double> = mutableListOf()
    val distance: MutableList<Double> = mutableListOf()
    val time: MutableList<Double> = mutableListOf()
}