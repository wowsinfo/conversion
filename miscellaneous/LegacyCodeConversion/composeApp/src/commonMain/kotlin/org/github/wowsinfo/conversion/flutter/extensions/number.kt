    return if (this.isNaN() || this.isInfinite()) "-" else String.format("%.${fractionDigits}f", this)
}


fun Double.toDecimalString(): String {
    val formatter = NumberFormat.getInstance(Locale.getDefault())
    formatter.minimumFractionDigits = 0
    formatter.maximumFractionDigits = 2
    return formatter.format(this)
}

    return "${this.toDecimalString()}%"
}

fun Double.toDecimalString(): String {
    return String.format("%.2f", this)
}

    return String.format("%.2f%%", this * 100)
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
        Text(text = "You have clicked the button $count times.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}