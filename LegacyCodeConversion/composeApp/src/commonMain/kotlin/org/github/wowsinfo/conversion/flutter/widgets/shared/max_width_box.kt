
@Immutable
data class MaxWidthBox(
    val maxWidth: Dp? = null,
    val child: @Composable () -> Unit
) {
    @Composable
    fun Content() {
        Box(
            modifier = Modifier.widthIn(max = maxWidth)
        ) {
            child()
        }
    }
}

fun ConstrainedBoxExample(maxWidth: Dp? = null, content: @Composable () -> Unit) {
    BoxWithConstraints {
        val width = maxWidth ?: ScreenSize.maxDialogWidth
        Box(modifier = Modifier.widthIn(max = width)) {
            content()
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
            Text("Click Me")
        }
    }
}