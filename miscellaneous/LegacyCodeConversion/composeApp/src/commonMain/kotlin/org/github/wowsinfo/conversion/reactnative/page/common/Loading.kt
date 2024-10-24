
@Composable
fun Loading() {
    var text by remember { mutableStateOf("") }
    val charArray = lang.setup_loading.toCharArray()

    LaunchedEffect(Unit) {
        while (true) {
            for (char in charArray) {
                text += char
                delay(50)
                if (text.length == charArray.size) {
                    text = ""
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.loading_image), contentDescription = null)
        Text(text = text, fontSize = 24.sp, color = Color.Red)
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}

fun LoadingScreen() {
    val context = LocalContext.current
    val animation = remember { getRandomAnimation() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        StatusBar(color = Color.Red) // Replace with appropriate color
        AnimatedVisibility(visible = true) {
            Image(
                painter = rememberImagePainter("Logo"),
                contentDescription = null,
                modifier = Modifier.size(100.dp) // Adjust size as needed
            )
        }
        Text(text = lang.setup_loading, style = MaterialTheme.typography.h6)
    }
}


@Composable
fun MyScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        // Your content goes here
    }
}

val RED = Color(0xFFF44336) // Example color definition


@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        modifier = Modifier
            .size(128.dp)
            .background(Color.White)
    )
}

    text = "Your Label",
    color = Color.White
)


@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}