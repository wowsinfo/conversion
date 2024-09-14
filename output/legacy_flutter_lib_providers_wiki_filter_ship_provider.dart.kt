import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import com.example.wowsinfo.models.gamedata.GameInfo
import com.example.wowsinfo.models.wowsinfo.ShipFilter
import com.example.wowsinfo.repositories.game.GameRepository
import com.example.wowsinfo.utils.Localisation
import io.github.aakira.napier.Napier

class FilterShipProvider(private val context: Context) {
    private var _regions = GameRepository.instance.shipRegionList
    private var _types = GameRepository.instance.shipTypeList
    private val logger = Napier Logger("FilterShipProvider")

    // selected filters
    private var _selectedRegion: MutableSet<Int> = mutableSetOf()
    private var _selectedType: MutableSet<Int> = mutableSetOf()
    private var _selectedTier: MutableSet<Int> = mutableSetOf()

    fun updateRegion(key: String) {
        val index = regionList.indexOf(key)
        if (_selectedRegion.contains(index)) {
            _selectedRegion.remove(index)
            logger.info("$key is removed from region list")
        } else {
            _selectedRegion.add(index)
            logger.info("$key is added to region list")
        }
        _selectedRegion = _selectedRegion
        notifyListeners()
    }

    fun updateType(key: String) {
        val index = typeList.indexOf(key)
        if (_selectedType.contains(index)) {
            _selectedType.remove(index)
            logger.info("$key is removed from type list")
        } else {
            _selectedType.add(index)
            logger.info("$key is added to type list")
        }
        _selectedType = _selectedType
        notifyListeners()
    }

    fun updateTier(key: String) {
        val index = GameInfo.tiers.indexOf(key)
        if (_selectedTier.contains(index)) {
            _selectedTier.remove(index)
            logger.info("$key is removed from tier list")
        } else {
            _selectedTier.add(index)
            logger.info("$key is added to tier list")
        }
        _selectedTier = _selectedTier
        notifyListeners()
    }

    fun resetAll() {
        _selectedRegion.clear()
        _selectedType.clear()
        _selectedTier.clear()
        notifyListeners()
    }

    fun onFilter(name: String): ShipFilter {
        val tiers = _selectedTier.map { it + 1 }.toList()
        val types = _selectedType.map { _types[it] }.toList()
        val regions = _selectedRegion.map { _regions[it] }.toList()

        return ShipFilter(name = name, tiers = tiers, regions = regions, types = types)
    }
}