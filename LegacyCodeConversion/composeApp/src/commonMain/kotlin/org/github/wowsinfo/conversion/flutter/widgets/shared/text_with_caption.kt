
@Composable
fun TextWithCaption(
    title: String = "",
    value: String = "",
    titleWidget: @Composable (() -> Unit)? = null,
    valueWidget: @Composable (() -> Unit)? = null
) {
    Column(modifier = Modifier.wrapContentSize()) {
        titleWidget?.invoke() ?: Text(text = title)
        valueWidget?.invoke() ?: Text(text = value)
    }
}


@Composable
fun CustomComponent(
    title: String,
    titleWidget: @Composable (() -> Unit)? = null,
    value: String,
    valueWidget: @Composable (() -> Unit)? = null
) {
    Column(modifier = Modifier.padding(top = 4.dp)) {
        titleWidget?.invoke() ?: Text(text = title)
        valueWidget?.invoke() ?: Text(text = value)
    }
}

fun BuildValue(valueWidget: @Composable (() -> Unit)?, value: String) {
    if (valueWidget != null) {
        valueWidget()
    } else {
        Text(
            text = value,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

fun BuildTitle(title: String, titleWidget: @Composable (() -> Unit)?) {
    if (titleWidget != null) {
        titleWidget()
    } else {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            maxLines = 2
        )
    }
}


@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") }
            )
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

    fun onButtonClick() {
        coroutineScope.launch {
            // Simulate a network call
            state = MyState.Success("Hello, World!")
        }
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: String) : MyState()
    data class Error(val message: String) : MyState()
}