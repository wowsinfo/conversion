
@Composable
fun DividerPlus() {
    Divider(modifier = Modifier.height(8.dp))
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
            Text(text = if (state) "ON" else "OFF")
        }
        if (state) {
            Text(text = "The state is ON")
        } else {
            Text(text = "The state is OFF")
        }
    }
}