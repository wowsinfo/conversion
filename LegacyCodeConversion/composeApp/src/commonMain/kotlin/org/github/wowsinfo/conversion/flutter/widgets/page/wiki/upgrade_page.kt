
class UpgradePage : KoinComponent {
    private val modernizationList by inject<GameRepository>().modernizationList

    @Composable
    fun UpgradePageContent() {
        val itemCount = Utils.getItemCount(8, 2, 100)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Upgrade Page") }
                )
            }
        ) {
            ScrollableColumn(modifier = Modifier.fillMaxSize()) {
                buildGridView(itemCount)
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
fun PreviewUpgradePage() {
    UpgradePage().UpgradePageContent()
}

fun BuildGridView(itemCount: Int, modernization: List<Modernization>, onItemClick: (Modernization) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(itemCount),
        content = {
            items(modernization) { curr ->
                val imageName = curr.icon
                Box(modifier = Modifier
                    .padding(4.dp)
                    .clickable { onItemClick(curr) }) {
                    Image(
                        painter = painterResource("data/live/app/assets/upgrades/$imageName.png"),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}


@Composable
fun showInfo(upgrade: Modernization) {
    val context = LocalContext.current
    val additionalString = upgrade.toString()

    Dialog(onDismissRequest = { /* Handle dismiss */ }, properties = DialogProperties()) {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = null,
            text = {
                ListItem(
                    modifier = Modifier.padding(2.dp),
                    headlineContent = {
                        Text(
                            text = upgrade.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "${Localisation.instance.stringOf(upgrade.description) ?: ""}\n\n$additionalString"
                        )
                    }
                )
            },
            confirmButton = {},
            dismissButton = {}
        )
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