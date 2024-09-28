
@Composable
fun NewItemIndicator(size: Dp? = null, color: Color? = null) {
    val indicatorSize = size ?: 8.dp
    val indicatorColor = color ?: MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier
            .size(indicatorSize)
            .background(indicatorColor, CircleShape)
    )
}


@Composable
fun CustomCircle(size: Dp? = 10.dp, color: Color? = null) {
    val circleSize = size ?: 10.dp
    Box(
        modifier = Modifier
            .size(circleSize)
            .align(Alignment.BottomCenter),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(circleSize)
                .background(color ?: MaterialTheme.colorScheme.primary, CircleShape)
        )
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