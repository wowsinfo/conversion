
@Composable
fun Setup() {
    var serverHeight by remember { mutableStateOf(0.dp) }
    var logoOpacity by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        logoOpacity = 1f
        delay(2000)
        serverHeight = 0.dp
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(alpha = logoOpacity)) {
            // Add your logo or content here
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetupPreview() {
    MaterialTheme {
        Setup()
    }
}

fun ServerSelectionScreen(logoOpacity: Float, serverHeight: Dp) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AnimatedVisibility(visible = logoOpacity > 0) {
                    AppLogoWithText(logoOpacity)
                }
                AnimatedContent(targetState = serverHeight) { height ->
                    Column(modifier = Modifier.height(height)) {
                        Text(
                            text = "Please select your server",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(8.dp)
                        )
                        ServerButton("Russia")
                        ServerButton("Europe")
                        ServerButton("North America")
                        ServerButton("Asia")
                    }
                }
            }
        }
    }
}

@Composable
fun AppLogoWithText(opacity: Float) {
    // Implement your logo with text here
}

@Composable
fun ServerButton(serverName: String) {
    Button(onClick = { /* Handle server selection */ }) {
        Text(text = serverName)
    }
}

fun AppLogoWithText(onLogoClick: () -> Unit) {
    var serverHeight by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .clickable { 
                serverHeight = if (serverHeight == 0) {
                    null
                } else {
                    0
                }
                onLogoClick()
            }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .background(Color.Blue)
        )
        Text(
            text = "WoWs Info",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun ServerButton(title: String) {
    Padding(modifier = Modifier.padding(bottom = 8.dp)) {
        Box(
            modifier = Modifier
                .width(148.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = title)
            }
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
        Text(text = "You have clicked the button $count times.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}