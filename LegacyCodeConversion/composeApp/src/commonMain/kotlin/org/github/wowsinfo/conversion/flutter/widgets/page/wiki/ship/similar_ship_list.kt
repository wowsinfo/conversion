
@Composable
fun SimilarShipList(source: String, ships: List<Ship>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(ships) { ship ->
            ShipCell(ship = ship, onClick = { 
                // Navigate to Ship Info Page
            })
        }
    }
}

@Composable
fun ShipCell(ship: Ship, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Text(text = ship.name, fontSize = 20.sp)
        // Add other ship details here
    }
}

fun ShipComparisonView(source: Ship, ships: List<Ship>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            navController.navigate("shipSimilar/${source.id}")
        }) {
            Text(text = Localisation.current.warship_compare_similar)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(ships) { ship ->
                val name = Localisation.instance.stringOf(ship.name) ?: ""
                ShipCell(
                    icon = ship.index,
                    name = name,
                    isPremium = ship.isPremium,
                    isSpecial = ship.isSpecial,
                    onClick = {
                        navController.navigate("shipInfo/${ship.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ShipCell(icon: Int, name: String, isPremium: Boolean, isSpecial: Boolean, onClick: () -> Unit) {
    // Implementation of ShipCell
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