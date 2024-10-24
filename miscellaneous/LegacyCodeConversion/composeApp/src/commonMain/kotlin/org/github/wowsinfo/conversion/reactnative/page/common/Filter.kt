
@Composable
fun Filter() {
    var filter by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var premium by remember { mutableStateOf(false) }
    var accordion by remember { mutableStateOf(0) }

    val tier = lang.wiki_warship_filter_tier
    val nation = lang.wiki_warship_filter_nation
    val type = lang.wiki_warship_filter_type

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Row {
            Checkbox(
                checked = premium,
                onCheckedChange = { premium = it }
            )
            Text("Premium")
        }
        // Additional UI components for tier, nation, and type can be added here
    }
}

fun WikiFilterScreen(
    applyFunc: () -> Unit,
    resetFunc: () -> Unit,
    wiki: Boolean,
    viewModel: WikiFilterViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value

    if (wiki) {
        Column(modifier = Modifier.fillMaxSize().background(ThemeColour())) {
            TextField(
                value = state.name,
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = { Text(lang.wiki_warship_filter_placeholder) },
                modifier = Modifier.fillMaxWidth()
            )
            ListItem(
                modifier = Modifier.clickable { viewModel.onPremiumToggle() },
                text = { Text(lang.wiki_warship_filter_premium) },
                trailing = {
                    Checkbox(
                        checked = state.premium,
                        onCheckedChange = { viewModel.onPremiumToggle() }
                    )
                }
            )
            Accordion(
                title = state.tier,
                expanded = state.accordion == 1,
                onClick = { viewModel.hideAccordion(1) }
            ) {
                LazyColumn {
                    items(viewModel.tierList) { item ->
                        Button(
                            onClick = { viewModel.onTierSelect(item) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(item)
                        }
                    }
                }
            }
            Accordion(
                title = state.nation,
                expanded = state.accordion == 2,
                onClick = { viewModel.hideAccordion(2) }
            ) {
                LazyColumn {
                    items(viewModel.nationList) { item ->
                        Button(
                            onClick = { viewModel.onNationSelect(item) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(item)
                        }
                    }
                }
            }
            Accordion(
                title = state.type,
                expanded = state.accordion == 3,
                onClick = { viewModel.hideAccordion(3) }
            ) {
                LazyColumn {
                    items(viewModel.typeList) { item ->
                        Button(
                            onClick = { viewModel.onTypeSelect(item) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(item)
                        }
                    }
                }
            }
            Button(onClick = { resetFunc() }, modifier = Modifier.padding(8.dp)) {
                Text(lang.wiki_warship_reset_btn)
            }
            Button(onClick = { applyFunc() }, modifier = Modifier.padding(8.dp)) {
                Text(lang.wiki_warship_filter_btn)
            }
        }
    }
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}


val padding = PaddingValues(4.dp)


val paddingModifier = Modifier.padding(8.dp)


@Composable
fun Filter() {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select an option:")
        options.forEach { option ->
            Row(modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = (selectedOption == option),
                    onClick = { selectedOption = option }
                )
                Text(option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}