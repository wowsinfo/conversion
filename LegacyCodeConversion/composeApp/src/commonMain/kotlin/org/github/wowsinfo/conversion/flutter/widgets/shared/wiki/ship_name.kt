
@Composable
fun ShipName(name: String, isPremium: Boolean, isSpecial: Boolean) {
    Text(
        text = name,
        fontSize = 20.sp,
        fontWeight = if (isPremium) FontWeight.Bold else FontWeight.Normal
    )
}

@Preview
@Composable
fun PreviewShipName() {
    ShipName(name = "Example Ship", isPremium = true, isSpecial = false)
}

fun ShipNameText(name: String, isPremium: Boolean, isSpecial: Boolean) {
    val style = when {
        isPremium -> TextStyle(
            fontSize = 14.sp,
            color = WoWsColours.premiumShip,
            fontWeight = FontWeight.W500
        )
        isSpecial -> TextStyle(
            fontSize = 14.sp,
            color = WoWsColours.specialShip,
            fontWeight = FontWeight.W500
        )
        else -> TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W300
        )
    }

    Text(
        text = name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        style = style
    )
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