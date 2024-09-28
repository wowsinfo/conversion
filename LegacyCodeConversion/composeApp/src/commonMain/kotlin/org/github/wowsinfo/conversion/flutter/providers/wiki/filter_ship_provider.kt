
class FilterShipProvider(private val context: Any) {
    private val regions = GameRepository.instance.shipRegionList
    private val types = GameRepository.instance.shipTypeList
    private val logger = LoggerFactory.getLogger(FilterShipProvider::class.java)

    private var selectedRegion = mutableSetOf<Int>()
    private var selectedType = mutableSetOf<Int>()
    private var selectedTier = mutableSetOf<Int>()

    private fun get(key: String): String {
        val name = Localisation.instance.stringOf(key, prefix = "IDS_")

        if (name == null) {
            logger.error("$key is invalid!")
            throw Exception("Failed to get filter name: $key")
        }

        return name
    }
}

val typeList: List<Type> = _types.map { getType(it) }
val tierList: List<Tier> = GameInfo.tiers

fun isRegionSelected(index: Int): Boolean = _selectedRegion.contains(index)
fun isTypeSelected(index: Int): Boolean = _selectedType.contains(index)
fun isTierSelected(index: Int): Boolean = _selectedTier.contains(index)

val regionFilterName: String
    get() {
        val name = Localisation.instance.regionFilterName
        return "$name (${Localisation.of(context).region})"
    }

    val index = regionList.indexOf(key)
    if (_selectedRegion.contains(index)) {
        _selectedRegion.remove(index)
        _logger.fine("$key is removed from region list")
    } else {
        _selectedRegion.add(index)
        _logger.fine("$key is added to region list")
    }
    _selectedRegion = _selectedRegion
    notifyListeners()
}

    val index = typeList.indexOf(key)
    if (_selectedType.contains(index)) {
        _selectedType.remove(index)
        _logger.fine("$key is removed from type list")
    } else {
        _selectedType.add(index)
        _logger.fine("$key is added to type list")
    }
    _selectedType = _selectedType
    notifyListeners()
}

    val index = GameInfo.tiers.indexOf(key)
    if (_selectedTier.contains(index)) {
        _selectedTier.remove(index)
        _logger.fine("$key is removed from tier list")
    } else {
        _selectedTier.add(index)
        _logger.fine("$key is added to tier list")
    }
    _selectedTier = _selectedTier
    notifyListeners()
}

    selectedRegion.clear()
    selectedType.clear()
    selectedTier.clear()
    notifyListeners()
}

    val tiers = _selectedTier.map { it + 1 }
    val types = _selectedType.map { _types[it] }
    val regions = _selectedRegion.map { _regions[it] }

    return ShipFilter(name = name, tiers = tiers, regions = regions, types = types)
}


@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My App") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is MyState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is MyState.Success -> {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
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