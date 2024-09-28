    fun average(value: Number?, base: Number?): Double {
        if (value == null) return Double.NaN
        if (base == null) return Double.NaN
        return value.toDouble() / base.toDouble()
    }
}

    if (value == null || base == null) return null
    return (value.toDouble() * 10000 / base.toDouble()) / 100.0
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
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click Me")
        }
    }
}