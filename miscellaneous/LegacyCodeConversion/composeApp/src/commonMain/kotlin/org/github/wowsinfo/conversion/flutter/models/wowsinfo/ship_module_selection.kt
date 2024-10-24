    private val logger = Logger.getLogger("ShipModuleSelection")

    private var selectedHull = 0
    private var selectedGun = 0
    private var selectedSecondary = 0
    private var selectedTorp = 0
    private var selectedPinger = 0
    private var selectedFireControl = 0
    private var selectedEngine = 0
    private var selectedAirSupport = 0
    private var selectedAirDefense = 0
    private var selectedDepthCharge = 0
    private var selectedSpecial = 0

    private var selectedFighter = 0
    private var selectedSkipBomber = 0
    private var selectedTorpedoBomber = 0
    private var selectedDiveBomber = 0

    val hullIndex: Int get() = selectedHull
    val gunIndex: Int get() = selectedGun
    val secondaryIndex: Int get() = selectedSecondary
    val torpIndex: Int get() = selectedTorp
    val pingerIndex: Int get() = selectedPinger
    val fireControlIndex: Int get() = selectedFireControl
    val engineIndex: Int get() = selectedEngine
    val airSupportIndex: Int get() = selectedAirSupport
    val airDefenseIndex: Int get() = selectedAirDefense
    val depthChargeIndex: Int get() = selectedDepthCharge
    val specialIndex: Int get() = selectedSpecial

    val fighterIndex: Int get() = selectedFighter
    val skipBomberIndex: Int get() = selectedSkipBomber
    val torpedoBomberIndex: Int get() = selectedTorpedoBomber
    val diveBomberIndex: Int get() = selectedDiveBomber

    constructor()

    constructor(selection: ShipModuleSelection) {
        selectedHull = selection.hullIndex
        selectedGun = selection.gunIndex
        selectedSecondary = selection.secondaryIndex
        selectedTorp = selection.torpIndex
        selectedPinger = selection.pingerIndex
        selectedFireControl = selection.fireControlIndex
        selectedEngine = selection.engineIndex
        selectedAirSupport = selection.airSupportIndex
        selectedAirDefense = selection.airDefenseIndex
        selectedDepthCharge = selection.depthChargeIndex
        selectedSpecial = selection.specialIndex

        selectedFighter = selection.fighterIndex
        selectedSkipBomber = selection.skipBomberIndex
        selectedTorpedoBomber = selection.torpedoBomberIndex
        selectedDiveBomber = selection.diveBomberIndex
    }
}

    return when (type) {
        ShipModuleType.hull -> selectedHull == index
        ShipModuleType.gun -> selectedGun == index
        ShipModuleType.secondary -> selectedSecondary == index
        ShipModuleType.torpedo -> selectedTorp == index
        ShipModuleType.pinger -> selectedPinger == index
        ShipModuleType.fireControl -> selectedFireControl == index
        ShipModuleType.engine -> selectedEngine == index
        ShipModuleType.airSupport -> selectedAirSupport == index
        ShipModuleType.airDefense -> selectedAirDefense == index
        ShipModuleType.depthCharge -> selectedDepthCharge == index
        ShipModuleType.fighter -> selectedFighter == index
        ShipModuleType.skipBomber -> selectedSkipBomber == index
        ShipModuleType.torpedoBomber -> selectedTorpedoBomber == index
        ShipModuleType.diveBomber -> selectedDiveBomber == index
        ShipModuleType.unknown -> {
            logger.severe("Unknown module type: $type")
            false
        }
    }
}

    private var _selectedHull: Int? = null
    private var _selectedGun: Int? = null
    private var _selectedSecondary: Int? = null
    private var _selectedTorp: Int? = null
    private var _selectedPinger: Int? = null
    private var _selectedFireControl: Int? = null
    private var _selectedEngine: Int? = null
    private var _selectedAirSupport: Int? = null
    private var _selectedAirDefense: Int? = null
    private var _selectedDepthCharge: Int? = null
    private var _selectedFighter: Int? = null
    private var _selectedSkipBomber: Int? = null
    private var _selectedTorpedoBomber: Int? = null
    private var _selectedDiveBomber: Int? = null

    private val logger = Logger.getLogger(ShipModuleManager::class.java.name)

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
            ShipModuleType.unknown -> {
                logger.severe("Unknown module type: $type")
            }
        }
    }
}

enum class ShipModuleType {
    hull,
    gun,
    secondary,
    torpedo,
    pinger,
    fireControl,
    engine,
    airSupport,
    airDefense,
    depthCharge,
    fighter,
    skipBomber,
    torpedoBomber,
    diveBomber,
    unknown
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Greeting("World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Hello, $name!")
        Button(onClick = { /* Handle button click */ }) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}