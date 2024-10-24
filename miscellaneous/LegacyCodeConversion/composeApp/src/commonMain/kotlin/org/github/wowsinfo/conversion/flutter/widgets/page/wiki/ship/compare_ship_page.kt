
@Composable
fun CompareShipPage() {
    val provider: CompareShipProvider = viewModel()
    val ships by provider.ships.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Compare Ship") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showFilterShipDialog { filter ->
                    provider.filter = filter
                }
            }) {
                Icon(Icons.Default.FilterAlt, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(ships) { ship ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(ship.shipName ?: "", modifier = Modifier.weight(1f))
                        Text(ship.health, modifier = Modifier.weight(1f))
                        Text(ship.gunReloadTime, modifier = Modifier.weight(1f))
                        Text(ship.gunRange, modifier = Modifier.weight(1f))
                        Text(ship.gunConfiguration, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My App") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is MyState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is MyState.Success -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(text = state.data)
                        Button(onClick = { viewModel.onButtonClick() }) {
                            Text("Click Me")
                        }
                    }
                }
                is MyState.Error -> {
                    Text(text = "Error: ${state.message}", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

class MyViewModel(private val coroutineScope: CoroutineScope) {
    var state: MyState = MyState.Loading
        private set

    fun onButtonClick() {
        coroutineScope.launch {
            // Simulate a network call
            state = MyState.Success("Data loaded successfully")
        }
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: String) : MyState()
    data class Error(val message: String) : MyState()
}