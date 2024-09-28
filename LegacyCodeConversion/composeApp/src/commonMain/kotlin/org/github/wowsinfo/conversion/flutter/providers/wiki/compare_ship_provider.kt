
class CompareShipProvider {
    private val logger = LoggerFactory.getLogger(CompareShipProvider::class.java)

    private var _filter: ShipFilter? = null
    val filter: StateFlow<ShipFilter?> get() = _filterFlow
    private val _filterFlow = MutableStateFlow<ShipFilter?>(null)

    var filterValue: ShipFilter?
        get() = _filter
        set(value) {
            if (_filter == value) return
            _compareShip(value)
        }

    private fun _compareShip(value: ShipFilter?) {
        _filter = value
        _filterFlow.value = value
        // Additional logic for comparing ships can be added here
    }
}


class ShipFilter {
    fun shouldDisplay(ship: Ship): Boolean {
        // Implement your filtering logic here
    }
}

class ShipViewModel : ViewModel() {
    private var _ships: List<ShipInfoProvider> = emptyList()
    val ships: List<ShipInfoProvider> get() = _ships

    private var _filter: ShipFilter? = null

    fun compareShip(filter: ShipFilter) {
        _filter = filter
        val ships = GameRepository.instance.shipList
        _ships = ships.filter { filter.shouldDisplay(it) }
            .map { ShipInfoProvider(it) }
        Log.i("ShipViewModel", "CompareShipProvider updated with ${_ships.size} ships")
        notifyListeners()
    }

    private fun notifyListeners() {
        // Implement your listener notification logic here
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
            Text("Click Me")
        }
    }
}