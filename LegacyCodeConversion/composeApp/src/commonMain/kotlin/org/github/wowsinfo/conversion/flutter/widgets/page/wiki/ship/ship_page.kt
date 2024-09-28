
@Composable
fun ShipPage(special: Boolean = false) {
    val shipProvider: ShipProvider = getViewModel { ShipProvider(special) }
    val itemCount = Utils.getItemCount(8, 2, 119)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Localisation.wiki_warships) }
            )
        },
        floatingActionButton = { FloatingActionButton(onClick = { /* Handle click */ }) { /* FAB content */ } },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        ScrollableColumn(modifier = Modifier.fillMaxSize()) {
            buildGridView(itemCount)
        }
    }
}

@Composable
fun buildGridView(itemCount: Int) {
    // Implement grid view logic here
}

@Preview
@Composable
fun PreviewShipPage() {
    ShipPage()
}

fun BuildFAB(shipProvider: ShipProvider) {
    val filterString = shipProvider.filterString
    FloatingActionButton(
        onClick = { shipProvider.showFilter() },
        content = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.FilterAlt, contentDescription = null)
                Text(filterString)
            }
        }
    )
}

fun BuildGridView(itemCount: Int, shipProvider: ShipProvider) {
    val scrollState = rememberScrollState()
    LazyVerticalGrid(
        cells = GridCells.Fixed(itemCount),
        contentPadding = PaddingValues(bottom = 64.dp),
        state = scrollState
    ) {
        items(shipProvider.shipCount) { index ->
            val curr = shipProvider.shipList[index]
            val imageName = curr.index
            val shipName = Localisation.instance.stringOf(curr.name) ?: ""
            PopupBox {
                Box(modifier = Modifier.fillMaxSize()) {
                    ShipCell(
                        icon = imageName,
                        hero = true,
                        name = "${curr.tierString} $shipName",
                        isPremium = curr.isPremium,
                        isSpecial = curr.isSpecial,
                        isNew = curr.added == 1,
                        onClick = {
                            navController.navigate("shipInfo/${curr.id}")
                        }
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