@Composable
fun SearchScreen() {
    val search = remember { mutableStateOf("") }
    val online = remember { mutableStateOf("???") }
    val result = remember { mutableStateOf(PlayerAndClanResult(player = emptyList(), clan = emptyList())) }
    val showFriend = remember { mutableStateOf(true) }
    val goodWidth by remember { mutableStateOf(400.dp) }

    LaunchedEffect(Unit) {
        online.value = getPlayerOnline()
        setLastLocation("Search")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            value = search.value,
            onValueChange = { text ->
                if (text.length < 2) {
                    result.value = PlayerAndClanResult(player = emptyList(), clan = emptyList())
                }
                search.value = text
                searchAll(text)
            },
            placeholder = stringResource(R.string.player_online),
        )
        LazyColumn(modifier = Modifier.padding(top = 64.dp)) {
            if (showFriend.get() && search.value.length < 2) {
                items(friendList) { friend ->
                    FriendItem(friend = friend, modifier = Modifier.padding(horizontal = 16.dp))
                }
            } else {
                item {
                    SectionTitle(
                        title = stringResource(R.string.search_clan),
                        itemCount = result.value.clan.size
                    )
                }
                items(result.value.clan) { clan ->
                    PlayerCell(
                        item = clan,
                        isClan = true,
                        width = goodWidth,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                item {
                    SectionTitle(
                        title = stringResource(R.string.search_player),
                        itemCount = result.value.player.size
                    )
                }
                items(result.value.player) { player ->
                    PlayerCell(
                        item = player,
                        isClan = false,
                        width = goodWidth,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, placeholder: StringResource) {
    val searchBar by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth()) {
        if (searchBar) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.close),
                        tint = MaterialTheme.colors.onSurface
                    )
                },
            )
        }
    }
}

private suspend fun getPlayerOnline(): String {
    val domain = getCurrDomain()
    return SafeFetch.get(WoWsAPI.PlayerOnline, domain).data.wows.firstOrNull()?.players_online ?: "???"
}

@Composable
fun searchAll(text: String) {
    if (text.length > 1 && text.length < 6) {
        // For clan, only 2 - 5
        val domain = getCurrDomain()
        SafeFetch.get(WoWsAPI.ClanSearch, domain, text).data.forEach { it.server = getCurrServer() }
        result.value = PlayerAndClanResult(clan = it.data)
    }

    if (text.length > 2) {
        // For player, 3+
        val domain = getCurrDomain()
        SafeFetch.get(WoWsAPI.PlayerSearch, domain, text).data.forEach { it.server = getCurrServer() }
        result.value = PlayerAndClanResult(player = it.data)
    }
}