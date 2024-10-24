
@Composable
fun AppName() {
    val context = LocalContext.current
    val appVersion = getVersion()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = getAppLogo().asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "App Name", style = MaterialTheme.typography.h6)
            Text(text = appVersion, style = MaterialTheme.typography.body2)
        }
    }
}

fun getVersion(): String {
    val appVersion = if (Platform.isIos) {
        APP.IOSVersion
    } else {
        APP.Version
    }
    return "$appVersion (${AppGlobalData.get(LOCAL.gameVersion)})"
}

fun getAppLogo(): SkiaImage {
    // Load your app logo here
}

fun AppHeader() {
    val isProVersion = isProVersion()
    val tintColor = TintColour()[500]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .weight(1)
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = lang.app_name,
                style = TextStyle(color = if (isProVersion) Color(0xFFFFA500) else Color.Unspecified)
            )
            Text(
                text = getVersion(),
                style = TextStyle(fontSize = 12.sp)
            )
        }
        AnimatedVisibility(visible = true) {
            Image(
                painter = rememberImagePainter("Logo"),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .graphicsLayer {
                        alpha = 0.5f
                        scaleX = 1.1f
                        scaleY = 1.1f
                    }
                    .animateContentSize()
            )
        }
    }
}

fun GameView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (-8).dp)
    ) {
        // Add your game content here
    }
}

fun AppName() {
    Text(
        text = "Your App Name",
        fontWeight = FontWeight.Bold
    )
}

    modifier = Modifier
        .padding(start = 8.dp, bottom = 0.dp)
) {
    // Add your content here
}


@Composable
fun MyComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello, Kotlin Multiplatform!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text(text = "Click Me")
        }
    }
}