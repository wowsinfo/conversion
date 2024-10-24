
class ShipProvider(private val special: Boolean) : ViewModel() {
    private val _ships = MutableStateFlow<List<Ship>>(emptyList())
    val ships: StateFlow<List<Ship>> get() = _ships

    init {
        filterShips()
    }

    private fun filterShips() {
        _ships.value = GameRepository.instance.shipList.filter { it.isSpecialGroup == special }
    }
}


class ShipProvider {
    private val _logger = Logger("ShipProvider")
    private var _filteredShips: List<Ship>? = null
    private val _ships: List<Ship> = listOf() // Initialize with your ship data

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

    private fun notifyListeners() {
        // Implement your listener notification logic here
    }
}

    showFilterShipDialog(context) { filter ->
        logger.fine("Filter: $filter")
        _filteredShips = if (filter.isEmpty) {
            _ships
        } else {
            _ships.filter { filter.shouldDisplay(it) }
        }

        notifyListeners()
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}