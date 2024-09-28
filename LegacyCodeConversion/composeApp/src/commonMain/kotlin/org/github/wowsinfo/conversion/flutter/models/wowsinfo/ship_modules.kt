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
    special,
    unknown;

    val key: String
        get() = when (this) {
            hull -> "hull"
            gun -> "artillery"
            secondary -> "atba"
            torpedo -> "torpedoes"
            pinger -> "pinger"
            fireControl -> "fireControl"
            engine -> "engine"
            airSupport -> "airSupport"
            airDefense -> "airDefense"
            depthCharge -> "depthCharges"
            fighter, skipBomber, torpedoBomber, diveBomber -> ""
            special -> "specials"
            unknown -> throw IllegalArgumentException("Unknown module type: $this")
        }
}

typealias ShipModuleMap<T> = Map<ShipModuleType, List<ShipModuleHolder<T>>>

    hull,
    gun,
    secondary,
    torpedo,
    pinger,
    fireControl,
    engine,
    depthCharge,
    airSupport,
    airDefense,
    fighter,
    skipBomber,
    torpedoBomber,
    diveBomber,
    special,
    unknown
}

object Localisation {
    val instance = this
    val hull: String = "Hull"
    val artillery: String = "Artillery"
    val secondaries: String = "Secondaries"
    val torpedoes: String = "Torpedoes"
    val sonar: String = "Sonar"
    val fireControl: String = "Fire Control"
    val engine: String = "Engine"
    val airDefense: String = "Air Defense"
    val fighter: String = "Fighter"
    val skipBomber: String = "Skip Bomber"
    val torpedoBomber: String = "Torpedo Bomber"
    val diveBomber: String = "Dive Bomber"
}

fun ShipModuleType.name(): String? {
    return when (this) {
        ShipModuleType.hull -> Localisation.instance.hull
        ShipModuleType.gun -> Localisation.instance.artillery
        ShipModuleType.secondary -> Localisation.instance.secondaries
        ShipModuleType.torpedo -> Localisation.instance.torpedoes
        ShipModuleType.pinger -> Localisation.instance.sonar
        ShipModuleType.fireControl -> Localisation.instance.fireControl
        ShipModuleType.engine -> Localisation.instance.engine
        ShipModuleType.depthCharge, ShipModuleType.airSupport -> ""
        ShipModuleType.airDefense -> Localisation.instance.airDefense
        ShipModuleType.fighter -> Localisation.instance.fighter
        ShipModuleType.skipBomber -> Localisation.instance.skipBomber
        ShipModuleType.torpedoBomber -> Localisation.instance.torpedoBomber
        ShipModuleType.diveBomber -> Localisation.instance.diveBomber
        ShipModuleType.special -> ""
        ShipModuleType.unknown -> throw IllegalArgumentException("Unknown module type: $this")
    }
}

    private val logger = Logger("ShipModules")

    private var gunCount = 0
    private var torpCount = 0
    private val hullInfo = mutableListOf<ShipModuleHolder<HullInfo>>()
    private val gunInfo = mutableListOf<ShipModuleHolder<GunInfo>>()
    private val secondaryInfo = mutableListOf<ShipModuleHolder<GunInfo>>()
    private val torpInfo = mutableListOf<ShipModuleHolder<TorpedoInfo>>()
    private val pingerInfo = mutableListOf<ShipModuleHolder<PingerInfo>>()
    private val fireControlInfo = mutableListOf<ShipModuleHolder<FireControlInfo>>()
    private val engineInfo = mutableListOf<ShipModuleHolder<EngineInfo>>()
    private val airSupportInfo = mutableListOf<ShipModuleHolder<AirSupportInfo>>()
    private val airDefenseInfo = mutableListOf<ShipModuleHolder<AirDefense>>()
    private val depthChargeInfo = mutableListOf<ShipModuleHolder<DepthChargeInfo>>()
    private val specialsInfo = mutableListOf<ShipModuleHolder<SpecialsInfo>>()

    private val fighterInfo = mutableListOf<ShipModuleHolder<Aircraft>>()
    private val skipBomberInfo = mutableListOf<ShipModuleHolder<Aircraft>>()
    private val torpedoBomberInfo = mutableListOf<ShipModuleHolder<Aircraft>>()
    private val diveBomberInfo = mutableListOf<ShipModuleHolder<Aircraft>>()

    private var moduleSelection = ShipModuleSelection()
    val selection: ShipModuleSelection
        get() = moduleSelection

    fun updateSelection(selection: ShipModuleSelection) {
        moduleSelection = selection
    }
}

    return if (index < 0 || index >= list.size) null else list[index]
}

    private var _gunCount: Int = 0
    private var _torpCount: Int = 0
    private var _hullInfo: List<Any> = emptyList()
    private var _fireControlInfo: List<Any> = emptyList()
    private var _engineInfo: List<Any> = emptyList()
    private var _skipBomberInfo: List<Any> = emptyList()
    private var _torpedoBomberInfo: List<Any> = emptyList()
    private var _diveBomberInfo: List<Any> = emptyList()
    private var _fighterInfo: List<Any> = emptyList()

    val canChangeModules: Boolean
        get() = _gunCount > 1 ||
                _torpCount > 1 ||
                _hullInfo.size > 1 ||
                _fireControlInfo.size > 1 ||
                _engineInfo.size > 1 ||
                _skipBomberInfo.size > 1 ||
                _torpedoBomberInfo.size > 1 ||
                _diveBomberInfo.size > 1 ||
                _fighterInfo.size > 1

    val moduleList: ShipModuleMap
        get() = makeModuleList()

    private fun makeModuleList(): ShipModuleMap {
        val moduleMap: ShipModuleMap = mutableMapOf()
        if (_hullInfo.size > 1) moduleMap[ShipModuleType.hull] = _hullInfo
        if (_gunCount > 1) moduleMap[ShipModuleType.gun] = _gunInfo
        if (_torpCount > 1) moduleMap[ShipModuleType.torpedo] = _torpInfo
        if (_fireControlInfo.size > 1) {
            moduleMap[ShipModuleType.fireControl] = _fireControlInfo
        }
        if (_engineInfo.size > 1) moduleMap[ShipModuleType.engine] = _engineInfo
        if (_fighterInfo.size > 1) {
            moduleMap[ShipModuleType.fighter] = _fighterInfo
        }
        if (_skipBomberInfo.size > 1) {
            moduleMap[ShipModuleType.skipBomber] = _skipBomberInfo
        }
        if (_torpedoBomberInfo.size > 1) {
            moduleMap[ShipModuleType.torpedoBomber] = _torpedoBomberInfo
        }
        if (_diveBomberInfo.size > 1) {
            moduleMap[ShipModuleType.diveBomber] = _diveBomberInfo
        }
        return moduleMap
    }
}

typealias ShipModuleMap = MutableMap<ShipModuleType, List<Any>>

enum class ShipModuleType {
    hull,
    gun,
    torpedo,
    fireControl,
    engine,
    fighter,
    skipBomber,
    torpedoBomber,
    diveBomber
}

val hullInfo: ShipModuleHolder<HullInfo>? 
    get() = _valueAt(_hullInfo, _moduleSelection.hullIndex)

val gunInfo: ShipModuleHolder<GunInfo>? 
    get() = _valueAt(_gunInfo, _moduleSelection.gunIndex)

val secondaryInfo: ShipModuleHolder<GunInfo>? 
    get() = _valueAt(_secondaryInfo, _moduleSelection.secondaryIndex)

val torpedoInfo: ShipModuleHolder<TorpedoInfo>? 
    get() = _valueAt(_torpInfo, _moduleSelection.torpIndex)

val engineInfo: ShipModuleHolder<EngineInfo>? 
    get() = _valueAt(_engineInfo, _moduleSelection.engineIndex)

val pingerInfo: ShipModuleHolder<PingerInfo>? 
    get() = _valueAt(_pingerInfo, _moduleSelection.pingerIndex)

val fireControlInfo: ShipModuleHolder<FireControlInfo>? 
    get() = _valueAt(_fireControlInfo, _moduleSelection.fireControlIndex)

val airSupportInfo: ShipModuleHolder<AirSupportInfo>? 
    get() = _valueAt(_airSupportInfo, _moduleSelection.airSupportIndex)

val airDefenseInfo: ShipModuleHolder<AirDefense>? 
    get() = _valueAt(_airDefenseInfo, _moduleSelection.airDefenseIndex)

val depthChargeInfo: ShipModuleHolder<DepthChargeInfo>? 
    get() = _valueAt(_depthChargeInfo, _moduleSelection.depthChargeIndex)

val specialsInfo: ShipModuleHolder<SpecialsInfo>? 
    get() = _valueAt(_specialsInfo, _moduleSelection.specialIndex)

val fighterInfo: ShipModuleHolder<Aircraft>? 
    get() = _valueAt(_fighterInfo, _moduleSelection.fighterIndex)

val skipBomberInfo: ShipModuleHolder<Aircraft>? 
    get() = _valueAt(_skipBomberInfo, _moduleSelection.skipBomberIndex)

val torpedoBomberInfo: ShipModuleHolder<Aircraft>? 
    get() = _valueAt(_torpedoBomberInfo, _moduleSelection.torpedoBomberIndex)

val diveBomberInfo: ShipModuleHolder<Aircraft>? 
    get() = _valueAt(_diveBomberInfo, _moduleSelection.diveBomberIndex)

fun unpackModules() {
    val shipModules = _ship.components
    for ((moduleType, modules) in _ship.modules) {
        for (module in modules) {
            val components = module.components

            fun <T> addModule(
                type: ShipModuleType,
                creator: (Map<String, Any>) -> T,
                add: (ShipModuleHolder<T>) -> Unit
            ) {
                val moduleList = components[type.key]
                if (moduleList.isNullOrEmpty()) return
                val info = shipModules[moduleList.first()]
                if (info == null) {
                    _logger.severe("Module $moduleList not found")
                    return
                }
                val holder = ShipModuleHolder(
                    module = module,
                    type = type,
                    data = creator(info)
                )
                add(holder)
            }

            when (moduleType) {
                "_Hull" -> {
                    addModule(ShipModuleType.hull, { HullInfo.fromJson(it) }, _hullInfo::add)
                    addModule(ShipModuleType.depthCharge, { DepthChargeInfo.fromJson(it) }, _depthChargeInfo::add)
                    addModule(ShipModuleType.airSupport, { AirSupportInfo.fromJson(it) }, _airSupportInfo::add)
                    addModule(ShipModuleType.airDefense, { AirDefense.fromJson(it) }, _airDefenseInfo::add)
                    addModule(ShipModuleType.secondary, { GunInfo.fromJson(it) }, _secondaryInfo::add)
                    addModule(ShipModuleType.pinger, { PingerInfo.fromJson(it) }, _pingerInfo::add)
                    addModule(ShipModuleType.special, { SpecialsInfo.fromJson(it) }, _specialsInfo::add)

                    _gunCount = components["artillery"]?.size ?: 0
                    _torpCount = components["torpedoes"]?.size ?: 0
                }
                "_Artillery" -> {
                    addModule(ShipModuleType.gun, { GunInfo.fromJson(it) }, _gunInfo::add)
                }
                "_Torpedoes" -> {
                    addModule(ShipModuleType.torpedo, { TorpedoInfo.fromJson(it) }, _torpInfo::add)
                }
                "_Suo" -> {
                    addModule(ShipModuleType.fireControl, { FireControlInfo.fromJson(it) }, _fireControlInfo::add)
                }
                "_Engine" -> {
                    val moduleList = components[ShipModuleType.engine.key]
                    if (moduleList.isNullOrEmpty()) return
                    val info = shipModules[moduleList.first()]
                    val holder = ShipModuleHolder(
                        type = ShipModuleType.engine,
                        module = module,
                        data = EngineInfo.fromJson(info)
                    )
                    _engineInfo.add(holder)
                }
                "_SkipBomber", "_TorpedoBomber", "_DiveBomber", "_Fighter" -> {
                    val plane = components.values.first().first()
                    val key = shipModules[plane]?.firstOrNull()
                    val info = GameRepository.instance.aircraftOf(key)
                    if (info == null) {
                        _logger.warning("Cannot find aircraft info of $key")
                        continue
                    } else {
                        _logger.fine("Found aircraft ($moduleType) info of $key")
                    }

                    when (moduleType) {
                        "_SkipBomber" -> {
                            val holder = ShipModuleHolder(
                                type = ShipModuleType.skipBomber,
                                module = module,
                                data = info
                            )
                            _skipBomberInfo.add(holder)
                        }
                        "_TorpedoBomber" -> {
                            val holder = ShipModuleHolder(
                                type = ShipModuleType.torpedoBomber,
                                module = module,
                                data = info
                            )
                            _torpedoBomberInfo.add(holder)
                        }
                        "_DiveBomber" -> {
                            val holder = ShipModuleHolder(
                                type = ShipModuleType.diveBomber,
                                module = module,
                                data = info
                            )
                            _diveBomberInfo.add(holder)
                        }
                        "_Fighter" -> {
                            val holder = ShipModuleHolder(
                                type = ShipModuleType.fighter,
                                module = module,
                                data = info
                            )
                            _fighterInfo.add(holder)
                        }
                    }
                }
                "_Sonar", "_Abilities", "_SecondaryWeapons", "_PrimaryWeapons", "_FlightControl" -> {
                    // ignore these for now
                }
                else -> {
                    throw UnimplementedError("Unknown module - $moduleType")
                }
            }
        }
    }

    if (canChangeModules) _sortModuleLists()
    _logger.fine("Unpacked modules of ${_ship.index}")
}

    hullInfo.sort()
    secondaryInfo.sort()
    pingerInfo.sort()
    torpInfo.sort()
    fireControlInfo.sort()
    engineInfo.sort()
    skipBomberInfo.sort()
    torpedoBomberInfo.sort()
    diveBomberInfo.sort()
    fighterInfo.sort()
    airSupportInfo.sort()
    depthChargeInfo.sort()
    logger.fine("Sorted all module lists")
}

    val module: ShipModuleInfo? = null,
    val data: T,
    val type: String
)

    val module: ShipModuleInfo?,
    val data: T,
    val type: ShipModuleType
) : Comparable<ShipModuleHolder<T>> {
    override fun compareTo(other: ShipModuleHolder<T>): Int {
        if (other.module == null) return 0
        if (module == null) return 0
        return module.compareTo(other.module)
    }
}


@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is MyState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is MyState.Success -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = state.data)
                        Button(onClick = { viewModel.onButtonClick() }) {
                            Text("Click Me")
                        }
                    }
                }
                is MyState.Error -> {
                    Text(text = "Error: ${state.message}", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

class MyViewModel(private val coroutineScope: CoroutineScope) {
    var state: MyState = MyState.Loading
        private set

    fun onButtonClick() {
        coroutineScope.launch {
            // Simulate a network call
            state = MyState.Loading
            try {
                val data = fetchData()
                state = MyState.Success(data)
            } catch (e: Exception) {
                state = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun fetchData(): String {
        // Simulate a delay
        kotlinx.coroutines.delay(2000)
        return "Hello, World!"
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: String) : MyState()
    data class Error(val message: String) : MyState()
}