
@Composable
fun ShipIcon(
    icon: Painter,
    height: Dp? = null,
    width: Dp? = null,
    hero: String? = null,
    isNew: Boolean? = null
) {
    Surface(
        modifier = Modifier.size(
            width = width ?: 50.dp,
            height = height ?: 50.dp
        )
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(
                width = width ?: 50.dp,
                height = height ?: 50.dp
            )
        )
        if (isNew == true) {
            // Implement New Item Indicator
        }
    }
}

fun ShipIcon(
    icon: String,
    height: Dp? = 80.dp,
    width: Dp? = null,
    hero: Boolean? = null,
    isNew: Boolean? = null
) {
    val fullIcon = "data/live/app/assets/ships/$icon.png"
    val box = Box(
        modifier = Modifier
            .height(height ?: 80.dp)
            .width(width ?: Dp.Unspecified)
    ) {
        AssetImageLoader(
            name = fullIcon,
            placeholder = "data/live/app/assets/ships/_default.png"
        )
        if (isNew == true) {
            NewItemIndicator()
        }
    }

    if (hero == true) {
        Hero(
            tag = icon,
            content = { box }
        )
    } else {
        box
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
    val coroutineScope = rememberCoroutineScope()
    var count by remember { mutableStateOf(0) }

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
            Text("Click me")
        }
    }
}