
@Composable
fun Consumable(upgrade: Boolean) {
    val loc = if (upgrade) "Upgrade" else "Consumable"
    setLastLocation(loc)

    val consumable = AppGlobalData.get(SAVED.consumable)
    val data = remember { mutableStateOf(listOf<ConsumableItem>()) }

    data.value = consumable.filter { curr ->
        if (upgrade) curr.type == "Modernization" else curr.type != "Modernization"
    }.sortedBy { curr ->
        if (upgrade) {
            if (curr.price_gold == 0) curr.price_credit else curr.price_gold
        } else {
            if (curr.type == "Flags") -1 else 1
        }
    }

    if (data.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(data.value) { item ->
                Text(text = item.name)
            }
        }
    }
}

fun WoWsInfoScreen() {
    WoWsInfo {
        renderGrid()
    }
}

@Composable
fun renderGrid() {
    // Implementation of the grid goes here
}

fun RenderGrid(data: List<Item>?) {
    if (data == null) {
        LoadingModal()
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                content = {
                    items(data) { item ->
                        WikiIcon(
                            item = item,
                            onClick = { SafeAction("BasicDetail", item) }
                        )
                    }
                }
            )
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


@Composable
fun Consumable() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Consumable Component")
        Button(onClick = { /* Handle click */ }) {
            Text("Click Me")
        }
    }
}