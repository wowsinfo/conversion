import 'package:flutter/material.dart'
import 'package:logging/logging.dart'
import 'package:wowsinfo/models/gamedata/ship.dart'
import 'package:wowsinfo/models/wowsinfo/ship_filter.dart'
import 'package:wowsinfo/repositories/game_repository.dart'
import 'package:wowsinfo/widgets/shared/filter_ship_dialog.dart'

/// Provides the entire ship list and filter it with the given [ShipFilter]
class ShipProvider with ChangeNotifier {
    private val _context: BuildContext
    private val _special: Boolean
    constructor(context: BuildContext, special: Boolean) {
        this._context = context
        this._special = special
    }

    private val _ships = GameRepository.instance.shipList.filter { it.isSpecialGroup == _special }
    private val _logger = Logger("ShipProvider")
    private var _filteredShips: List<Ship>? = null

    val shipList: List<Ship>
        get() = _filteredShips ?: _ships
    val shipCount: Int
        get() = shipList.size
    val filterString: String
        get() = "-  $shipCount"

    fun resetFilter() {
        _filteredShips = null
        notifyListeners()
    }

    fun showFilter() {
        showFilterShipDialog(_context) { filter ->
            _logger.fine("Filter: $filter")
            if (filter.isEmpty()) {
                _filteredShips = _ships
            } else {
                _filteredShips =
                    _ships.filter { filter.shouldDisplay(it) }
            }

            notifyListeners()
        }
    }
}