    fun add(value: Number)
}

class AverageCounter : Counter {
    private var total: Double = 0.0
    private var count: Int = 0
    private var average: Double = 0.0

    val averageValue: Double
        get() = average

    override fun add(value: Number) {
        total += value.toDouble()
        count++
        average = total / count
    }
}

    private var _total: Double = 0.0
    val total: Double
        get() = _total

    override fun add(value: Double) {
        _total += value
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

    fun fetchData() {
        coroutineScope.launch {
            try {
                // Simulate network call
                state = MyState.Success("Data loaded successfully")
            } catch (e: Exception) {
                state = MyState.Error("Failed to load data")
            }
        }
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: String) : MyState()
    data class Error(val message: String) : MyState()
}