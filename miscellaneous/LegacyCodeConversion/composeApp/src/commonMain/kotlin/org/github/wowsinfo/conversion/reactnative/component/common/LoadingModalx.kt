
@Composable
fun LoadingModal() {
    Modal(
        onDismissRequest = {},
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicator()
                Text("Loading...")
            }
        }
    )
}


Text(
    text = "Your text here",
    color = Color.White,
    modifier = Modifier.padding(16.dp)
)


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
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current state: ${if (state) "ON" else "OFF"}")
    }
}