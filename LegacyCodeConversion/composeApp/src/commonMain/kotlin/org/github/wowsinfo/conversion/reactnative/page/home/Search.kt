
@Composable
fun SearchScreen() {
    var search by remember { mutableStateOf("") }
    var online by remember { mutableStateOf("???") }
    var result by remember { mutableStateOf(PlayerClanResult(emptyList(), emptyList())) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val domain = getCurrDomain()
        val prefix = getCurrPrefix()
        coroutineScope.launch {
            val num = SafeFetch.get(WoWsAPI.PlayerOnline, domain)
            online = Guard(num, "data.wows.0.players_online", "???")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Players Online: $online")
        // Add logic to display results here
    }
}

data class PlayerClanResult(val player: List<Player>, val clan: List<Clan>)

    val newWidth = event.layout.width
    setState { goodWidth = bestWidth(400, newWidth) }
}

fun SearchScreen(viewModel: YourViewModel) {
    val search by viewModel.search.collectAsState()
    val online by viewModel.online.collectAsState()
    val searchBarStyle = // define your search bar style
    val scrollStyle = // define your scroll style

    WoWsInfo(
        hideAds = true,
        title = lang.menu_footer,
        onPress = { focusRequester.requestFocus() }
    ) {
        val focusRequester = remember { FocusRequester() }
        KeyboardAvoidingBox {
            Searchbar(
                value = search,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(searchBarStyle),
                placeholder = "${viewModel.prefix.uppercase()} - $online ${lang.search_player_online}",
                onValueChange = { viewModel.searchAll(it) },
                autoCorrect = false,
                autoCapitalize = AutoCapitalize.None
            )
            ScrollableColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .then(scrollStyle),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                viewModel.renderContent()
            }
        }
    }
}

fun RenderContent(state: YourStateType) {
    val search = state.search
    val result = state.result
    val showFriend = state.showFriend

    if (showFriend && search.length < 2) {
        Friend()
    } else {
        val playerLen = result.player.size
        val clanLen = result.clan.size
        Column {
            SectionTitle(title = "${lang.menu_search_clan} - $clanLen")
            RenderClan(result.clan)
            SectionTitle(title = "${lang.menu_search_player} - $playerLen")
            RenderPlayer(result.player)
        }
    }
}

fun RenderClan(clan: List<ClanItem>, goodWidth: Dp) {
    if (clan.isNotEmpty()) {
        Column(modifier = Modifier.wrapContentSize()) {
            clan.forEach { item ->
                PlayerCell(item = item, clan = true, width = goodWidth)
            }
        }
    }
}

fun RenderPlayer(player: List<Player>) {
    if (player.isNotEmpty()) {
        Column(modifier = Modifier.wrapContentSize()) {
            player.forEach { item ->
                PlayerCell(
                    key = item.accountId,
                    item = item,
                    player = true,
                    width = goodWidth
                )
            }
        }
    }
}


class SearchViewModel : ViewModel() {
    private var searchJob: Job? = null
    var result = mutableStateOf(SearchResult(emptyList(), emptyList()))
    var search = mutableStateOf("")

    fun searchAll(text: String) {
        if (text.length < 2) {
            result.value = SearchResult(emptyList(), emptyList())
        }
        search.value = text

        searchJob?.cancel()
        searchJob = CoroutineScope(Dispatchers.IO).launch {
            delay(500)
            val domain = getCurrDomain()
            val all = SearchResult(mutableListOf(), mutableListOf())
            val length = text.length

            if (length > 1 && length < 6) {
                val clanData = SafeFetch.get(WoWsAPI.ClanSearch, domain, text)
                clanData?.let {
                    it.forEach { v -> v.server = getCurrServer() }
                    all.clan.addAll(it)
                    withContext(Dispatchers.Main) {
                        result.value = all
                    }
                }
            }

            if (length > 2) {
                val playerData = SafeFetch.get(WoWsAPI.PlayerSearch, domain, text)
                playerData?.let {
                    it.forEach { v -> v.server = getCurrServer() }
                    all.player.addAll(it)
                    withContext(Dispatchers.Main) {
                        result.value = all
                    }
                }
            }
        }
    }
}

data class SearchResult(val player: List<Player>, val clan: List<Clan>)


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}

fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(100.dp))
            .zIndex(2f)
            .align(Alignment.TopStart)
    ) {
        // Add your search bar content here
    }
}


@Composable
fun ScrollableContent() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = 64.dp)
    ) {
        // Your content goes here
    }
}

    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .horizontalScroll(rememberScrollState())
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        // Add your items here
    }
}


@Composable
fun Search() {
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Handle search action */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Search")
        }
    }
}