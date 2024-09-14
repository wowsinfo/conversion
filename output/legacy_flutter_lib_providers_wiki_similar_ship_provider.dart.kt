import 'dart:math'

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wowsinfo.extensions.NumberExtensions.toDecimalString
import com.example.wowsinfo.extensions.NumberExtensions.toPercentString
import com.example.wowsinfo.foundation.helpers.ChartUtils
import com.example.wowsinfo.models.gamedata.Ship
import com.example.wowsinfo.repository.GameRepository
import com.example.wowsinfo.ui.theme.WowsInfoTheme

class SimilarShipProvider {
    private val _logger = Logger("SimilarShipProvider")
    private val _ship: Ship
    private var _similarShips: List<Ship> = emptyList()
    private var _chartHeight: Double = 0.0
    private var _averageDamage: Double = 0.0
    private var _averageWinrate: Double = 0.0
    private var _averageFrags: Double = 0.0
    private var _totalBattles: Int = 0

    init {
        _similarShips = _getSimilarShips()
        _chartHeight = 25.0 * max(_similarShips.size, 5).toDouble()
    }

    fun hasSimilarShips(): Boolean = _similarShips.isNotEmpty
    fun similarShips(): List<Ship> = _similarShips

    private fun _getSimilarShips(): List<Ship> {
        val ships = GameRepository.instance.shipList
        return ships.filter {
            it.id != _ship.id &&
                    it.type == _ship.type &&
                    it.tier == _ship.tier &&
                    it.isSpecialGroup == _ship.isSpecialGroup
        }.toList()
    }

    fun chartHeight(): Double = _chartHeight

    fun averageDamage(): String = _averageDamage.round().toDecimalString()

    fun averageWinrate(): String = _averageWinrate.toPercentString()

    fun averageFrags(): String = _averageFrags.toDecimalString()

    fun totalBattles(): String = _totalBattles.toDecimalString()

    fun winrateSeries(): List<Series<ChartValue, String>> {
        return listOf(Series(
            data: _winrateSeries,
            domainAxisName = "Winrate",
            color = ChartUtils.winrateColour,
            labelFormatter = { value, _ ->
                value.value.asPercentString()
            }
        ))
    }

    fun fragsSeries(): List<Series<ChartValue, String>> {
        return listOf(Series(
            data: _fragsSeries,
            domainAxisName = "Frags",
            color = ChartUtils.fragsColour,
            labelFormatter = { value, _ ->
                value.value.toDecimalString()
            }
        ))
    }

    fun damageSeries(): List<Series<ChartValue, String>> {
        return listOf(Series(
            data: _damageSeries,
            domainAxisName = "Damage",
            color = ChartUtils.damageColour,
            labelFormatter = { value, _ ->
                value.value.toDecimalString()
            }
        ))
    }

    fun battleSeries(): List<Series<ChartValue, String>> {
        return listOf(Series(
            data: _battleSeries,
            domainAxisName = "Battles",
            color = ChartUtils.battleColour,
            labelFormatter = { value, _ ->
                value.value.toDecimalString()
            }
        ))
    }

    fun extractShipAdditional() {
        val damageCounter = AverageCounter()
        val fragsCounter = AverageCounter()
        val winrateCounter = AverageCounter()
        val battlesCounter = TotalCounter()

        val allShips = _similarShips.plus(_ship)

        for (ship in allShips) {
            val shipAdditional = GameRepository.instance.shipAdditionalOf(ship.id.toString())
            if (shipAdditional == null) {
                _logger.warning("No ship additional info for ${ship.name}")
                continue
            }

            damageCounter.add(shipAdditional.damage)
            fragsCounter.add(shipAdditional.frags)
            winrateCounter.add(shipAdditional.winrate)
            val shipBattleCount = shipAdditional.battles ?: 0
            battlesCounter.add(shipBattleCount)

            val shipName = Localisation.instance.stringOf(ship.name) ?: continue

            _damageSeries.add(ChartValue(
                shipName,
                shipAdditional.damage
            ))

            _fragsSeries.add(ChartValue(
                shipName,
                shipAdditional.frags
            ))

            _winrateSeries.add(ChartValue(
                shipName,
                shipAdditional.winrate
            ))

            _battleSeries.add(ChartValue(
                shipName,
                shipBattleCount
            ))
        }

        _averageDamage = damageCounter.average
        _averageFrags = fragsCounter.average
        _averageWinrate = winrateCounter.average
        _totalBattles = battlesCounter.total.toInt()
    }
}

@Composable
fun SimilarShipStats(
    modifier: Modifier = Modifier,
    shipProvider: SimilarShipProvider
) {
    WowsInfoTheme {
        Column(modifier) {
            // TODO: Implement similar ship stats UI
        }
    }
}