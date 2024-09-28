
@Composable
fun ShipAdditionalBox(shipAdditional: ShipAdditional) {
    Column(modifier = Modifier) {
        TextWithCaption(
            caption = Localisation.getString("some_caption"),
            text = shipAdditional.someValue.toFormattedString()
        )
        // Add more UI elements as needed based on shipAdditional properties
    }
}

fun ShipAdditionalStats(shipAdditional: ShipAdditional) {
    val battles = shipAdditional.battles
    val localised = Localisation.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextWithCaption(
            title = localised.warship_avg_damage,
            value = shipAdditional.damage.toDecimalString()
        )
        TextWithCaption(
            title = localised.warship_avg_winrate,
            value = shipAdditional.winrate.asPercentString()
        )
        TextWithCaption(
            title = localised.warship_avg_frag,
            value = shipAdditional.frags.toDecimalString()
        )
        battles?.let {
            TextWithCaption(
                title = Localisation.instance.battles,
                value = it.toDecimalString()
            )
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

    fun fetchData() {
        CoroutineScope(Dispatchers.Main).launch {
            state = try {
                MyState.Success(repository.getData())
            } catch (e: Exception) {
                MyState.Error(e.message ?: "Unknown error")
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