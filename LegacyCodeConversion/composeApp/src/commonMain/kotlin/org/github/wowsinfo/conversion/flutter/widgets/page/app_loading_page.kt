
@Composable
fun AppLoadingPage(navController: NavController) {
    Box {
        CircularProgressIndicator()
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            App.instance.inject()
            GameRepository.instance.initialise()
        }
        navController.navigate("upgrade_page")
    }
}


@Composable
fun LoadingScreen() {
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Blue)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = 0.9f,
                    modifier = Modifier.height(8.dp)
                )
                Text("Loading...")
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
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Data Loaded: ${state.data}")
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

    init {
        loadData()
    }

    private fun loadData() {
        coroutineScope.launch {
            try {
                // Simulate network call
                state = MyState.Success("Sample Data")
            } catch (e: Exception) {
                state = MyState.Error("Failed to load data")
            }
        }
    }

    fun onButtonClick() {
        // Handle button click
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: String) : MyState()
    data class Error(val message: String) : MyState()
}