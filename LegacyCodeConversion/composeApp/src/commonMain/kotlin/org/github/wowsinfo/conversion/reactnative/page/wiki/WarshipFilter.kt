
enum class Mode {
    TIER, NATION, TYPE
}

@Composable
fun WarshipFilter() {
    var premium by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    val nation = remember { mutableStateListOf<String>() }
    val type = remember { mutableStateListOf<String>() }
    val tier = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text("Premium")
            Checkbox(checked = premium, onCheckedChange = { premium = it })
        }
        // Add more UI elements for nation, type, and tier filters
        Button(onClick = { /* Handle filter action */ }) {
            Text("Filter")
        }
    }
}


@Composable
fun MyComponent() {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        scrollState.scrollTo(128)
    }

    Box(modifier = Modifier.verticalScroll(scrollState)) {
        // Your content here
    }
}

fun WoWsInfoScreen(viewModel: YourViewModel) {
    val state = viewModel.state.collectAsState()
    val tierList = getTierList()
    val nations = AppGlobalData.get(SAVED.encyclopedia).ship_nations
    val nationList = nations.values.toList()
    val types = AppGlobalData.get(SAVED.encyclopedia).ship_types
    val typeList = types.values.toList()

    Column {
        WoWsInfo(
            hideAds = true,
            title = lang.wiki_warship_filter_placeholder,
            onPress = { focusRequester.requestFocus() }
        ) {
            TextField(
                value = state.value.name,
                onValueChange = { viewModel.setName(it) },
                label = { Text(lang.wiki_warship_filter_placeholder) },
                modifier = Modifier.focusRequester(focusRequester),
                singleLine = true,
                keyboardOptions = KeyboardOptions(autoCorrect = false, capitalization = KeyboardCapitalization.None)
            )
            LazyColumn {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Section(title = lang.wiki_warship_filter_tier) {
                        Text(text = state.value.tier.joinToString(" | "))
                        Row(modifier = Modifier.wrapContentWidth()) {
                            tierList.forEach { item ->
                                Button(onClick = { viewModel.addData(item, MODE.TIER) }) {
                                    Text(item)
                                }
                            }
                        }
                    }
                }
                item {
                    Section(title = lang.wiki_warship_filter_nation) {
                        Text(text = state.value.nation.joinToString(" | "))
                        Row(modifier = Modifier.wrapContentWidth()) {
                            nationList.forEach { item ->
                                Button(onClick = { viewModel.addData(item, MODE.NATION) }) {
                                    Text(item)
                                }
                            }
                        }
                    }
                }
                item {
                    Section(title = lang.wiki_warship_filter_type) {
                        Text(text = state.value.type.joinToString(" | "))
                        Row(modifier = Modifier.wrapContentWidth()) {
                            typeList.forEach { item ->
                                Button(onClick = { viewModel.addData(item, MODE.TYPE) }) {
                                    Text(item)
                                }
                            }
                        }
                    }
                }
            }
            FooterPlus {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = { viewModel.resetAll() }) {
                        Text(lang.wiki_warship_reset_btn)
                    }
                    TextButton(onClick = { viewModel.applyAll() }) {
                        Text(lang.wiki_warship_filter_btn)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(lang.wiki_warship_filter_premium)
                    Checkbox(
                        checked = state.value.premium,
                        onCheckedChange = { viewModel.setPremium(it) }
                    )
                }
            }
        }
    }
}

    var premium by mutableStateOf(false)
    var name by mutableStateOf("")
    var nation by mutableStateOf(listOf<String>())
    var type by mutableStateOf(listOf<String>())
    var tier by mutableStateOf(listOf<String>())

    fun resetAll() {
        premium = false
        name = ""
        nation = emptyList()
        type = emptyList()
        tier = emptyList()
    }

    fun applyAll(navController: NavController) {
        navController.popBackStack()
        LaunchedEffect(Unit) {
            navController.currentBackStackEntry?.savedStateHandle?.set("filter", this)
        }
    }
}

fun RenderButton(item: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = item)
    }
}

    val tier = state.tier.toMutableList()
    val nation = state.nation.toMutableList()
    val type = state.type.toMutableList()
    var arr: MutableList<ItemType>? = null

    when (mode) {
        Mode.TIER -> arr = tier
        Mode.NATION -> arr = nation
        Mode.TYPE -> arr = type
    }

    if (arr?.lastOrNull() == item) {
        return
    }
    arr?.add(item)

    when (mode) {
        Mode.TIER -> setState { copy(tier = arr) }
        Mode.NATION -> setState { copy(nation = arr) }
        Mode.TYPE -> setState { copy(type = arr) }
    }
}


@Composable
fun HorizontalLayout(content: @Composable () -> Unit) {
    Row {
        content()
    }
}


@Composable
fun MyButton() {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = { /* Handle click */ }
        ) {
            // Button content goes here
        }
    }
}


val selectionTextModifier = Modifier
    .padding(start = 16.dp, end = 16.dp)

fun WrapView(content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        content()
    }
}


@Composable
fun WarshipFilter() {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select a Warship Option")
        options.forEach { option ->
            Row(modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option }
                )
                Text(option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}