
class SettingsViewModel : ViewModel() {
    private val _logger = Logger("SettingsViewModel")
    private val userRepository = UserRepository.instance
    private val _server = userRepository.gameServer

    private val _servers = MutableStateFlow<List<DropdownValue>>(emptyList())
    val servers: StateFlow<List<DropdownValue>> = _servers

    init {
        val localised = Localisation.current
        _servers.value = listOf(
            DropdownValue(0, localised.server_russia),
            DropdownValue(1, localised.server_europe),
            DropdownValue(2, localised.server_north_america),
            DropdownValue(3, localised.server_asia)
        )
    }
}

data class DropdownValue(val value: Int, val title: String)

    private var _server: Int = 0
    val serverValue: Int get() = _server

    private val servers: List<DropdownValue<Int>> = listOf() // Initialize with actual values
    private val colours = AppThemeColour.colourList

    fun updateServer(index: Int) {
        Log.i("ServerViewModel", "Updating server to ${servers[index].title}")
        _server = index
        userRepository.gameServer = _server
        notifyListeners()
    }

    private fun notifyListeners() {
        // Implement listener notification logic
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
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to My App")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                // Handle button click
            }
        }) {
            Text("Click Me")
        }
    }
}