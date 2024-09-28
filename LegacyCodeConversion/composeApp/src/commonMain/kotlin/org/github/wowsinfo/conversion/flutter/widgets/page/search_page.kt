
@Composable
fun SearchPage() {
    val searchController = remember { mutableStateOf(TextFieldValue()) }
    val provider: SearchProvider = viewModel(factory = SearchProviderFactory(searchController))

    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            renderSearchBar(searchController.value, { searchController.value = it }, provider::search)
            Spacer(modifier = Modifier.height(8.dp))
            val coroutineScope = rememberCoroutineScope()
            LazyColumn {
                item { renderClan() }
                item { renderPlayer() }
            }
        }
    }
}

@Composable
fun renderSearchBar(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, onSearch: () -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Search") },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = onSearch) {
                Icon(Icons.Default.Search, contentDescription = null)
            }
        }
    )
}

@Composable
fun renderClan() {
    // Implement clan rendering logic
}

@Composable
fun renderPlayer() {
    // Implement player rendering logic
}

fun RenderSearchBar(
    context: Context,
    controller: TextFieldValue,
    onSubmitted: (String) -> Unit,
    searchProvider: SearchProvider
) {
    val canClear = searchProvider.canClear
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { (context as Activity).finish() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
                TextField(
                    value = controller.text,
                    onValueChange = { controller = TextFieldValue(it) },
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester),
                    placeholder = { Text("Search") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
                )
                AnimatedVisibility(visible = canClear) {
                    IconButton(onClick = { searchProvider.resetAll() }) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        }
    }
}

fun RenderTitle(title: String, count: Int) {
    Text(
        text = "$title ($count)",
        style = MaterialTheme.typography.h6
    )
}

fun RenderClan(searchProvider: SearchProvider) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = Localisation.current.menu_search_clan,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.h6
        )
        RenderWrap(searchProvider.clans)
    }
}

@Composable
fun RenderWrap(clans: List<Clan>) {
    // Implementation for rendering the list of clans
}

fun RenderPlayer(searchProvider: SearchProvider) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Padding(paddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            RenderTitle(
                title = Localisation.current.menu_search_player,
                numOfPlayers = searchProvider.numOfPlayers
            )
        }
        RenderWrap(players = searchProvider.players)
    }
}

fun RenderWrap(context: Context, result: List<SearchResult>) {
    val count = Utils(context).getItemCount(6, 1, 300)
    val width = LocalConfiguration.current.screenWidthDp.dp
    val provider = LocalContext.current.get<SearchProvider>()
    val itemSize = result.size.toDouble()

    Wrap(
        crossAxisAlignment = WrapCrossAlignment.Center
    ) {
        result.forEachIndexed { index, value ->
            DebutEffect(
                keepAlive = false,
                intervalStart = index / itemSize
            ) {
                Box(
                    modifier = Modifier.width(width / count)
                ) {
                    ListTile(
                        title = { Text(value.displayName) },
                        trailing = { Text(value.id) },
                        modifier = Modifier.clickable { provider.onResultSelected(context, value) }
                    )
                }
            }
        }
    }
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