
@Composable
fun SimpleRating(info: Info) {
    val ratingColour = getColour(info.rating)

    val nothing = info.pvp == null

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (nothing) {
                Text("No rating available", color = ratingColour)
            } else {
                Text("PVP: ${info.pvp}", color = ratingColour)
                Text("Rating: ${info.rating}", color = ratingColour)
            }
        }
    }
}

data class Info(val pvp: String?, val rating: Float)

fun getColour(rating: Float): Color {
    // Implement your color logic based on rating
}

    nothing = true
}

fun PvpStatsView(pvp: PvpData, nothing: Boolean, ratingColour: Color) {
    val iconSize = 24.dp
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter("Battle"),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize).tint(ratingColour)
                )
                Text(text = if (nothing) "0" else pvp.battles.toString())
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter("WinRate"),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize).tint(ratingColour)
                )
                Text(text = if (nothing) "0.0%" else "${roundTo((pvp.wins.toFloat() / pvp.battles) * 100, 2)}%")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter("Damage"),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize).tint(ratingColour)
                )
                Text(text = if (nothing) "0" else roundTo(pvp.damageDealt.toFloat() / pvp.battles).toString())
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(ratingColour)
        )
    }
}


@Composable
fun CenteredText() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Your Text Here",
            fontSize = 14.sp,
            fontWeight = FontWeight.W300
        )
    }
}


@Composable
fun CenterView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Your content goes here
    }
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
            Text(text = if (state) "Active" else "Inactive")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current state: ${if (state) "Active" else "Inactive"}")
    }
}