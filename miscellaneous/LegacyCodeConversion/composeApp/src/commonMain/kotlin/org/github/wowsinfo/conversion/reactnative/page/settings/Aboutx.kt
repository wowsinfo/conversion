
@Composable
fun About() {
    var animation by remember { mutableStateOf("pulse") }
    val width = LocalConfiguration.current.screenWidthDp.dp
    val height = LocalConfiguration.current.screenHeightDp.dp
    val imageWidth = if (width > height) height * 0.5f else width * 0.5f

    LaunchedEffect(Unit) {
        while (true) {
            animation = getRandomAnimation()
            delay(2000)
        }
    }

    Box(modifier = Modifier.size(imageWidth)) {
        // Replace with your image loading logic
        Image(bitmap = loadImage(animation).asImageBitmap(), contentDescription = null)
    }
}

fun getRandomAnimation(): String {
    val animations = listOf("pulse", "fade", "bounce") // Add your animations here
    return animations[Random.nextInt(animations.size)]
}

fun loadImage(animation: String): Bitmap {
    // Load your image based on the animation
}

fun WoWsInfoComponent() {
    val imageWidth = 100.dp // Set your desired image width
    val animation = rememberInfiniteTransition()
    val tintColor = TintColour()[500]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { SimpleViewHandler.openURL(lang.about_github_link) }
            .wrapContentSize(Alignment.Center)
    ) {
        AnimatedVisibility(visible = true) {
            Image(
                painter = rememberImagePainter("Logo"),
                contentDescription = null,
                modifier = Modifier
                    .size(imageWidth)
                    .graphicsLayer {
                        // Apply animation here
                    }
                    .colorFilter(ColorFilter.tint(tintColor))
            )
        }
    }
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { state = !state }) {
            Text(text = if (state) "Active" else "Inactive")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current state: ${if (state) "Active" else "Inactive"}")
    }
}