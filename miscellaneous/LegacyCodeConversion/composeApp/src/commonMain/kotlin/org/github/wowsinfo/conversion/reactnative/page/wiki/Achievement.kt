
@Composable
fun Achievement() {
    setLastLocation("Achievement")
    val achievement = AppGlobalData.get(SAVED.achievement)
    val sorted = remember(achievement) {
        achievement.entries.sortedWith(compareBy({ it.value.hidden }, { it.key })).map { it.value }
    }

    Surface {
        LazyColumn {
            items(sorted) { item ->
                Text(text = item.name) // Assuming item has a 'name' property
            }
        }
    }
}

fun WoWsInfoScreen(viewModel: YourViewModel) {
    val data by viewModel.data.collectAsState()

    WoWsInfo {
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


@Composable
fun Achievement() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Achievement Title", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Achievement Description", style = MaterialTheme.typography.body1)
    }
}