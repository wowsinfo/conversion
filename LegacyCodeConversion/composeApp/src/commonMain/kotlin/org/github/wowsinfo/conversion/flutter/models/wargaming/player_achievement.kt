
@Serializable
data class PlayerAchievement(
    val battle: Int? = null,
    val progress: Int? = null
)

    val battle: Map<String, Int>? = null,
    val progress: Map<String, Int>? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any>): PlayerAchievement {
            if (json.isEmpty()) return PlayerAchievement()

            val player = json.values.firstOrNull()
            if (player is Map<*, *>) {
                return PlayerAchievement(
                    battle = (player["battle"] as? Map<*, *>)?.mapKeys { it.key as String }?.mapValues { it.value as Int },
                    progress = (player["progress"] as? Map<*, *>)?.mapKeys { it.key as String }?.mapValues { it.value as Int }
                )
            }

            error("Not a hidden account but data is missing")
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
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state) {
                is MyState.Loading -> CircularProgressIndicator()
                is MyState.Success -> SuccessContent(state.data)
                is MyState.Error -> ErrorContent(state.message)
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

    init {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
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