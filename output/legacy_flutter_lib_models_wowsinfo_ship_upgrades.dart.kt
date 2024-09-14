import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShipUpgrades(private val ship: Ship) {
    private val logger = Logger("ShipUpgrades")

    var modernizations by mutableStateOf<List<Modernization>>(emptyList())
        private set
    var modernizationsBySlot by mutableStateOf(emptyList<List<Modernization>>())
        private set

    fun unpackUpgrades(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val upgrades = GameRepository.instance.modernizationList
            val shipUpgrades = mutableListOf<Modernization>()
            var maxSlot = 0
            for (upgrade in upgrades) {
                if (upgrade.isFor(ship)) {
                    shipUpgrades.add(upgrade)
                    val slot = upgrade.slot
                    if (slot > maxSlot) maxSlot = slot
                }
            }

            logger.d("Unpacked ${shipUpgrades.size} upgrades for ${ship.name}")
            assert(shipUpgrades.size < 30, "Too many upgrades for ${ship.name}")

            modernizations = shipUpgrades
            // slot is from 0
            modernizationsBySlot = unpackUpgradesBySlot(max = maxSlot + 1)
        }
    }

    private fun unpackUpgradesBySlot(max: Int): List<List<Modernization>> {
        val modernizationsBySlot = mutableListOf<List<Modernization>>()
        for (slot in 0 until max) {
            logger.d("Unpacking slot $slot")
            modernizationsBySlot.add(
                modernizations.filter { it.slot == slot }.toMutableList()
            )
        }
        return modernizationsBySlot
    }

    companion object {
        fun Logger(tag: String): Logger = LoggerFactory.getLogger(tag)
    }
}