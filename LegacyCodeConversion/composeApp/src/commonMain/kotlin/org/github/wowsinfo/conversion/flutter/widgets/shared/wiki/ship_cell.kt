
@Composable
fun ShipCell(
    icon: @Composable () -> Unit,
    name: @Composable () -> Unit,
    isPremium: Boolean,
    isSpecial: Boolean,
    height: Int? = null,
    width: Int? = null,
    hero: String? = null,
    onTap: (() -> Unit)? = null,
    isNew: Boolean? = null
) {
    Box(
        modifier = Modifier
            .clickable { onTap?.invoke() }
            .height(height?.dp ?: 50.dp)
            .width(width?.dp ?: 100.dp)
    ) {
        icon()
        name()
    }
}

fun ShipCell(
    icon: String,
    height: Dp? = null,
    width: Dp? = null,
    hero: Boolean? = null,
    isNew: Boolean? = null,
    name: String,
    isPremium: Boolean,
    isSpecial: Boolean,
    onTap: (() -> Unit)? = null
) {
    val cell = Column {
        ShipIcon(icon = icon, height = height, width = width, hero = hero, isNew = isNew)
        ShipName(name = name, isPremium = isPremium, isSpecial = isSpecial)
    }

    if (onTap == null) {
        cell
    } else {
        Box(modifier = Modifier.clickable(onClick = onTap)) {
            cell
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
        CoroutineScope(Dispatchers.Main).launch {
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