
fun LocalDate.toHumanString(): String {
    return listOf(year, monthNumber, dayOfMonth)
        .joinToString("-") { it.toString().padStart(2, '0') }
}

    val duration = other - System.currentTimeMillis()
    return Math.abs((duration / (1000 * 60 * 60 * 24)).toInt())
}


fun fromTimeStamp(timeStamp: Int): kotlinx.datetime.LocalDateTime {
    return Instant.fromEpochSeconds(timeStamp.toLong()).toKotlinLocalDateTime()
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
                    SuccessContent(state.data)
                }
                is MyState.Error -> {
                    ErrorContent(state.message)
                }
            }
        }
    }
}

@Composable
fun SuccessContent(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Text(item, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun ErrorContent(message: String) {
    Text(message, modifier = Modifier.padding(16.dp))
}

class MyViewModel(private val repository: MyRepository) {
    var state: MyState = MyState.Loading
        private set

    fun fetchData(scope: CoroutineScope) {
        scope.launch {
            try {
                val data = repository.getData()
                state = MyState.Success(data)
            } catch (e: Exception) {
                state = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: List<String>) : MyState()
    data class Error(val message: String) : MyState()
}

interface MyRepository {
    suspend fun getData(): List<String>
}