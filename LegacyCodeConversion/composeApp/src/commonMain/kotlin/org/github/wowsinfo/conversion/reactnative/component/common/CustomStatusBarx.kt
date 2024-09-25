
@Composable
fun CustomStatusBar(
    backgroundColor: Color = Color.Green,
    dark: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(color = backgroundColor, modifier = Modifier.statusBarsPadding()) {
            // Background color for the status bar
        }
        content()
        val statusBarColor = if (dark) Color.White else Color.Black
        AndroidView(factory = { context ->
            val window = (context as? Activity)?.window
            window?.statusBarColor = ContextCompat.getColor(context, backgroundColor.toArgb())
            window?.decorView?.systemUiVisibility = if (dark) {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                View.SYSTEM_UI_FLAG_VISIBLE
            }
            View(context)
        })
    }
}


@Composable
fun CustomBar() {
    Box(
        modifier = Modifier
            .background(Color.Green)
    ) {
        // Additional content can be added here
    }
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf("Initial State") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = state)
        Button(onClick = { state = "Button Clicked" }) {
            Text("Click Me")
        }
    }
}