
@Composable
fun SafeView(theme: Any?, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(modifier = modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            val safeAreaView = SafeAreaView(context)
            safeAreaView.setPadding(0, 0, 0, 0)
            safeAreaView
        }) {
            it.addView(content())
        }
    }
}

@Preview
@Composable
fun PreviewSafeView() {
    SafeView(theme = null) {
        // Your content here
    }
}


@Composable
fun Container() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Content goes here
    }
}


@Composable
fun SafeView(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MaterialTheme {
            content()
        }
    }
}