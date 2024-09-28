
class ScrollProvider(
    private val scrollState: ScrollState,
    private val animationSpec: AnimationSpec<Float>,
    private val height: Dp
) {
    private var _display by mutableStateOf(true)
    val display: Boolean get() = _display

    private var _offset by mutableStateOf(0f)
    val offset: Float get() = if (display) _offset else 0f

    init {
        LaunchedEffect(scrollState) {
            snapshotFlow { scrollState.value }
                .collect { value ->
                    if (value > height.value) {
                        _display = false
                    } else {
                        _display = true
                    }
                    _offset = value.toFloat()
                }
        }
    }
}

@Composable
fun ScrollableContent(scrollProvider: ScrollProvider) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Your content here
    }
}


@Composable
fun ScrollableWidget(scrollState: ScrollState) {
    var display by remember { mutableStateOf(true) }
    val animation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value }
            .collect { value ->
                val direction = if (value > 0) ScrollDirection.REVERSE else ScrollDirection.FORWARD
                when (direction) {
                    ScrollDirection.REVERSE -> {
                        if (display) {
                            coroutineScope.launch {
                                animation.animateTo(-height, animationSpec = tween())
                                display = false
                            }
                        }
                    }
                    ScrollDirection.FORWARD -> {
                        if (!display) {
                            coroutineScope.launch {
                                animation.animateTo(0f, animationSpec = tween())
                                display = true
                            }
                        }
                    }
                }
            }
    }

    Box(modifier = Modifier.scrollable(scrollState, orientation = Orientation.Vertical)) {
        if (display) {
            // Your content here
        }
    }
}

enum class ScrollDirection {
    REVERSE, FORWARD
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