
@Composable
fun FooterPlus(children: @Composable () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .then(Modifier.background(ThemeBackColour()))
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        children()
    }
}

fun ThemeBackColour(): Color {
    // Define your theme background color here
    return Color.White // Example color
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
            Text("Toggle State")
        }
        if (state) {
            Text("State is true")
        } else {
            Text("State is false")
        }
    }
}