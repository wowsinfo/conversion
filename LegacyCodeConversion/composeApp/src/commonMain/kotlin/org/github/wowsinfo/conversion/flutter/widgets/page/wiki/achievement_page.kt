
class AchievementPage : KoinComponent {
    private val achievements: List<Achievement> by inject()

    @Composable
    fun AchievementScreen() {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Achievement Page") })
            }
        ) {
            SafeArea {
                ScrollableColumn(modifier = Modifier.fillMaxSize()) {
                    buildGridView(achievements.size)
                }
            }
        }
    }

    @Composable
    fun buildGridView(itemCount: Int) {
        // Implement your grid view logic here
    }
}

@Preview
@Composable
fun PreviewAchievementPage() {
    AchievementPage().AchievementScreen()
}

fun BuildGridView(itemCount: Int, achievements: List<Achievement>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(itemCount),
        content = {
            items(achievements) { curr ->
                val imageName = curr.icon
                PopupBox {
                    Box(modifier = Modifier.clickable { showInfo(curr) }) {
                        Image(
                            painter = rememberImagePainter("data/live/app/assets/achievements/$imageName.png"),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                        if (curr.added == 1) {
                            NewItemIndicator()
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun showInfo(achievement: Achievement) {
    val context = LocalContext.current
    val title = remember { Localisation.instance.stringOf(achievement.name) ?: "" }
    val description = remember { Localisation.instance.stringOf(achievement.description, constants = achievement.constants) ?: "" }

    AlertDialog(
        onDismissRequest = { /* Handle dismiss */ },
        title = null,
        text = {
            MaxWidthBox {
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = description)
                }
            }
        },
        confirmButton = { /* Add confirm button if needed */ },
        dismissButton = { /* Add dismiss button if needed */ }
    )
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
            Text("Click me")
        }
    }
}