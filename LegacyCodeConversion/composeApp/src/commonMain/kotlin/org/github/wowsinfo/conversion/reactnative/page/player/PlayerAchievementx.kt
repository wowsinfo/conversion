
@Composable
fun PlayerAchievement(data: Map<String, Int>) {
    val displayData = remember { mutableStateListOf<Pair<Any, Int>>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(data) {
        coroutineScope.launch {
            val saved = AppGlobalData.get(SAVED.achievement)
            val formatted = mutableListOf<Pair<Any, Int>>()

            for ((key, value) in data) {
                val obj = saved[key]
                if (obj != null) {
                    formatted.add(Pair(obj, value))
                }
            }

            formatted.sortByDescending { it.second }
            displayData.clear()
            displayData.addAll(formatted)
        }
    }

    LazyColumn {
        items(displayData) { item ->
            AchievementItem(item.first, item.second)
        }
    }
}

@Composable
fun AchievementItem(data: Any, num: Int) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = data.toString())
            Text(text = "Number: $num")
        }
    }
}

fun AchievementScreen(displayData: List<AchievementItem>, lang: Language) {
    WoWsInfo(title = "${lang.tab_achievement_title} - ${displayData.size}") {
        AnimatedVisibility(visible = true) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                content = {
                    items(displayData) { item ->
                        Box(modifier = Modifier
                            .padding(4.dp)
                            .clickable { SafeAction("BasicDetail", item.data) }) {
                            WikiIcon(item = item.data)
                            Text(text = item.num.toString(), style = MaterialTheme.typography.body1)
                        }
                    }
                }
            )
        }
    }
}

data class AchievementItem(val data: YourDataType, val num: Int)


@Composable
fun NumberView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Your Number",
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { state = !state }) {
            Text(text = if (state) "Active" else "Inactive")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current state: ${if (state) "Active" else "Inactive"}")
    }
}