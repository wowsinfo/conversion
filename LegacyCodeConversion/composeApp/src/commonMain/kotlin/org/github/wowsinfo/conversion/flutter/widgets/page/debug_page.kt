
@Composable
fun DebugPage() {
    var loaded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        loadAppData()
        loaded = true
    }

    if (loaded) {
        // Your UI components go here
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Debug Page Loaded")
            // Add more UI elements as needed
        }
    } else {
        CircularProgressIndicator()
    }
}

suspend fun loadAppData() {
    // Implement your data loading logic here
}


@Composable
fun DebugPage(navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Debug Page") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("search")
            }) {
                Icon(Icons.Filled.Search, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            renderTest()
        }
    }
}

@Composable
fun renderTest() {
    // Implement your test rendering logic here
}

fun RenderTest(loadde: Boolean, context: Context) {
    if (!loadde) {
        Center {
            CircularProgressIndicator()
        }
    } else {
        val elbing = GameRepository.instance.shipOf("4074649392")

        Center {
            Wrap(
                alignment = WrapAlignment.Center,
                children = listOf(
                    Column(
                        mainAxisSize = MainAxisSize.Min,
                        children = listOf(
                            Text("Pages"),
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, UpgradePage::class.java)
                                )
                            }) {
                                Text(Localisation.of(context).wiki_upgrades)
                            },
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, ShipPage::class.java)
                                )
                            }) {
                                Text(Localisation.of(context).wiki_warships)
                            },
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, CompareShipPage::class.java)
                                )
                            }) {
                                Text("Compare")
                            },
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, ShipPage::class.java).apply {
                                        putExtra("special", true)
                                    }
                                )
                            }) {
                                Text("Ships (Special)")
                            },
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, AchievementPage::class.java)
                                )
                            }) {
                                Text("Achievements")
                            },
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, CommanderSkillPage::class.java)
                                )
                            }) {
                                Text(Localisation.of(context).wiki_skills)
                            },
                            TextButton(onClick = {
                                context.startActivity(
                                    Intent(context, AppSettingsPage::class.java)
                                )
                            }) {
                                Text(Localisation.of(context).settings_app_settings)
                            }
                        )
                    ),
                    Column(
                        mainAxisSize = MainAxisSize.Min,
                        children = listOf(
                            Text("Dialogs"),
                            TextButton(onClick = {
                                showFilterShipDialog(context) { }
                            }) {
                                Text("Ship Filter Dialog")
                            },
                            TextButton(onClick = {
                                showShipPenetrationDialog(context, elbing!!)
                            }) {
                                Text("Elbing Penetration Dialog")
                            }
                        )
                    ),
                    Column(
                        mainAxisSize = MainAxisSize.Min,
                        children = listOf(
                            Text("Game Languages"),
                            *Localisation.instance.supportedGameLanguages.map { lang ->
                                TextButton(onClick = {
                                    Localisation.instance.updateDataLanguage(lang)
                                }) {
                                    Text(lang)
                                }
                            }.toTypedArray()
                        )
                    )
                )
            )
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
            Text("Click me")
        }
    }
}