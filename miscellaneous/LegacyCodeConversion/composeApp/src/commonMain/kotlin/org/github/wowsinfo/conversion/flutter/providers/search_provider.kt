
class SearchProvider : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onTextChanged(newText: String) {
        _searchText.value = newText
    }
}

@Composable
fun SearchView(searchProvider: SearchProvider = viewModel()) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            searchProvider.onTextChanged(it)
        },
        label = { androidx.compose.material.Text("Search") }
    )
}

    private val service = WargamingService(GameServer.fromIndex(userRepository.gameServer))

    private var input: String = ""
    val canClear: Boolean
        get() = input.isNotEmpty()

    private var timer: Timer? = null

    private var numOfPlayers: Int = 0
    private var numOfClans: Int = 0

    val players: List<PlayerResult>
        get() = _players
    private val _players: MutableList<PlayerResult> = mutableListOf()

    val clans: List<ClanResult>
        get() = _clans
    private val _clans: MutableList<ClanResult> = mutableListOf()

    private fun isSameInput(input: String): Boolean {
        return input == this.input
    }
}


class SearchViewModel {
    private val scope = MainScope()
    private var searchJob: Job? = null
    private var previousInput: String? = null

    var searchController = SearchController()

    fun onTextChanged() {
        searchJob?.cancel()
        val input = searchController.text

        if (isSameInput(input)) {
            return
        }

        searchJob = scope.launch {
            delay(500)
            search(input)
        }
    }

    private fun isSameInput(input: String): Boolean {
        return previousInput == input
    }

    private fun search(input: String) {
        previousInput = input
        // Implement search logic here
    }
}

class SearchController {
    var text: String = ""
}

    if (isSameInput(query)) {
        logger.fine("input is the same as previous one")
        return
    }
    input = query

    val length = query.length
    logger.info("Searching for $query")
    if (length <= 1) {
        logger.info("Search query is too short")
        resetAll()
        return
    }

    if (length > 1 && length <= 5) {
        searchClan(query)
    } else {
        resetClans()
    }

    if (length > 2) {
        searchPlayer(query)
    }
}

    when (result) {
        is ClanResult -> {
            val intent = Intent(context, ClanPage::class.java).apply {
                putExtra("clan", result)
            }
            context.startActivity(intent)
        }
        is PlayerResult -> {
            val intent = Intent(context, PlayerPage::class.java).apply {
                putExtra("accountId", result.accountId)
            }
            context.startActivity(intent)
        }
    }
}

    logger.info("Searching for clan $query")
    val result = service.searchClan(query)
    if (result.isNotEmpty()) {
        val clanList = result.data
        if (clanList != null) {
            numOfClans = clanList.size
            clans = clanList
            notifyListeners()
        }
    }
}

    logger.info("Searching for player $query")
    val result = service.searchPlayer(query)
    if (result.isNotEmpty()) {
        val playerList = result.data
        if (playerList != null) {
            numOfPlayers = playerList.size
            players = playerList
            notifyListeners()
        }
    }
}

    numOfPlayers = 0
    players.clear()
    notifyListeners()
}

    numOfClans = 0
    clans.clear()
    notifyListeners()
}

    resetPlayers()
    resetClans()
    searchController.text = ""
}

fun resetPlayers() {
    // Implementation for resetting players
}

fun resetClans() {
    // Implementation for resetting clans
}

class SearchController {
    var text: String = ""
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