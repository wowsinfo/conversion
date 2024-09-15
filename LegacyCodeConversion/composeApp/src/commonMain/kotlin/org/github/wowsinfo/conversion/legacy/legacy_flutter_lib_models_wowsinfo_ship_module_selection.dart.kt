import 'package:logging/logging.dart';
import 'package:wowsinfo/models/wowsinfo/ship_modules.dart';

class ShipModuleSelection {
  private val logger = Logger("ShipModuleSelection")

  // selected modules by default 0
  var _selectedHull = 0
  var _selectedGun = 0
  var _selectedSecondary = 0
  var _selectedTorp = 0
  var _selectedPinger = 0
  var _selectedFireControl = 0
  var _selectedEngine = 0
  var _selectedAirSupport = 0
  var _selectedAirDefense = 0
  var _selectedDepthCharge = 0
  var _selectedSpecial = 0

  var _selectedFighter = 0
  var _selectedSkipBomber = 0
  var _selectedTorpedoBomber = 0
  var _selectedDiveBomber = 0

  val hullIndex: Int get() = _selectedHull
  val gunIndex: Int get() = _selectedGun
  val secondaryIndex: Int get() = _selectedSecondary
  val torpIndex: Int get() = _selectedTorp
  val pingerIndex: Int get() = _selectedPinger
  val fireControlIndex: Int get() = _selectedFireControl
  val engineIndex: Int get() = _selectedEngine
  val airSupportIndex: Int get() = _selectedAirSupport
  val airDefenseIndex: Int get() = _selectedAirDefense
  val depthChargeIndex: Int get() = _selectedDepthCharge
  val specialIndex: Int get() = _selectedSpecial

  val fighterIndex: Int get() = _selectedFighter
  val skipBomberIndex: Int get() = _selectedSkipBomber
  val torpedoBomberIndex: Int get() = _selectedTorpedoBomber
  val diveBomberIndex: Int get() = _selectedDiveBomber

  constructor()

  // Copy from another [selection]
  constructor.fromSelection(selection: ShipModuleSelection) {
    _selectedHull = selection.hullIndex
    _selectedGun = selection.gunIndex
    _selectedSecondary = selection.secondaryIndex
    _selectedTorp = selection.torpIndex
    _selectedPinger = selection.pingerIndex
    _selectedFireControl = selection.fireControlIndex
    _selectedEngine = selection.engineIndex
    _selectedAirSupport = selection.airSupportIndex
    _selectedAirDefense = selection.airDefenseIndex
    _selectedDepthCharge = selection.depthChargeIndex
    _selectedSpecial = selection.specialIndex

    _selectedFighter = selection.fighterIndex
    _selectedSkipBomber = selection.skipBomberIndex
    _selectedTorpedoBomber = selection.torpedoBomberIndex
    _selectedDiveBomber = selection.diveBomberIndex
  }

  // Check if the module of [type] at [index] is selected
  fun isSelected(type: ShipModuleType, index: Int): Boolean {
    return when (type) {
      ShipModuleType.hull -> _selectedHull == index
      ShipModuleType.gun -> _selectedGun == index
      ShipModuleType.secondary -> _selectedSecondary == index
      ShipModuleType.torpedo -> _selectedTorp == index
      ShipModuleType.pinger -> _selectedPinger == index
      ShipModuleType.fireControl -> _selectedFireControl == index
      ShipModuleType.engine -> _selectedEngine == index
      ShipModuleType.airSupport -> _selectedAirSupport == index
      ShipModuleType.airDefense -> _selectedAirDefense == index
      ShipModuleType.depthCharge -> _selectedDepthCharge == index

      ShipModuleType.fighter -> _selectedFighter == index
      ShipModuleType.skipBomber -> _selectedSkipBomber == index
      ShipModuleType.torpedoBomber -> _selectedTorpedoBomber == index
      ShipModuleType.diveBomber -> _selectedDiveBomber == index

      else -> {
        logger.severe("Unknown module type: $type")
        false
      }
    }
  }

  // Update selected module [index] based on [type].
  // Same selection will be ignored.
  fun updateSelection(type: ShipModuleType, index: Int) {
    when (type) {
      ShipModuleType.hull -> {
        if (_selectedHull == index) return
        _selectedHull = index
        logger.fine("updateHull: $index")
      }
      ShipModuleType.gun -> {
        if (_selectedGun == index) return
        _selectedGun = index
        logger.fine("updateGun: $index")
      }
      ShipModuleType.secondary -> {
        if (_selectedSecondary == index) return
        _selectedSecondary = index
        logger.fine("updateSecondary: $index")
      }
      ShipModuleType.torpedo -> {
        if (_selectedTorp == index) return
        _selectedTorp = index
        logger.fine("updateTorp: $index")
      }
      ShipModuleType.pinger -> {
        if (_selectedPinger == index) return
        _selectedPinger = index
        logger.fine("updatePinger: $index")
      }
      ShipModuleType.fireControl -> {
        if (_selectedFireControl == index) return
        _selectedFireControl = index
        logger.fine("updateFireControl: $index")
      }
      ShipModuleType.engine -> {
        if (_selectedEngine == index) return
        _selectedEngine = index
        logger.fine("updateEngine: $index")
      }
      ShipModuleType.airSupport -> {
        if (_selectedAirSupport == index) return
        _selectedAirSupport = index
        logger.fine("updateAirSupport: $index")
      }
      ShipModuleType.airDefense -> {
        if (_selectedAirDefense == index) return
        _selectedAirDefense = index
        logger.fine("updateAirDefense: $index")
      }
      ShipModuleType.depthCharge -> {
        if (_selectedDepthCharge == index) return
        _selectedDepthCharge = index
        logger.fine("updateDepthCharge: $index")
      }

      ShipModuleType.fighter -> {
        if (_selectedFighter == index) return
        _selectedFighter = index
        logger.fine("updateFighter: $index")
      }
      ShipModuleType.skipBomber -> {
        if (_selectedSkipBomber == index) return
        _selectedSkipBomber = index
        logger.fine("updateSkipBomber: $index")
      }
      ShipModuleType.torpedoBomber -> {
        if (_selectedTorpedoBomber == index) return
        _selectedTorpedoBomber = index
        logger.fine("updateTorpedoBomber: $index")
      }
      ShipModuleType.diveBomber -> {
        if (_selectedDiveBomber == index) return
        _selectedDiveBomber = index
        logger.fine("updateDiveBomber: $index")
      }

      else -> {
        logger.severe("Unknown module type: $type")
      }
    }
  }
}