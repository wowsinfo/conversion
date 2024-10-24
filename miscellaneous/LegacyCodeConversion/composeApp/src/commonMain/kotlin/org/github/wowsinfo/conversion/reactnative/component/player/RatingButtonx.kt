
@Composable
fun RatingButton(rating: Int? = null, number: Boolean = false) {
    if (number) {
        Button(onClick = { /* Handle click */ }, colors = ButtonDefaults.buttonColors(backgroundColor = getColour(rating))) {
            Text(text = rating.toString())
        }
    }
}

fun getColour(rating: Int?): Color {
    // Implement your color logic based on the rating
}

fun RatingButton(rating: Int?) {
    if (rating == null || rating == 0) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            shape = RoundedCornerShape(0.dp)
        ) {
            // Placeholder button
        }
    } else {
        Button(
            onClick = { SafeAction("Rating") },
            colors = ButtonDefaults.buttonColors(backgroundColor = getColour(rating)),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = getComment(rating))
        }
    }
}


@Composable
fun MyComponent() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $count")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }
        }) {
            Text("Increment")
        }
    }
}