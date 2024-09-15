@Composable
fun RatingButton(rating: Float?, number: Boolean = false) {
    if (number) {
        Button(
            onClick = { /* No action needed */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = getColour(rating)),
            contentPadding = PaddingValues(12.dp),
        ) {
            Text(text = rating.toString())
        }
    } else {
        if (rating == null || rating == 0f) {
            // return a place holder button
            Button(
                onClick = { /* No action needed */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            )
        } else {
            Button(
                onClick = { SafeAction("Rating") },
                colors = ButtonDefaults.buttonColors(backgroundColor = getColour(rating)),
                contentPadding = PaddingValues(12.dp),
            ) {
                Text(text = getComment(rating))
            }
        }
    }
}

@Composable
fun getColour(rating: Float?): Color {
    return when (rating) {
        in 0f..3f -> Color.Red
        in 4f..6f -> Color.Orange
        in 7f..8f -> Color.Yellow
        else -> Color.Green
    }
}

@Composable
fun getComment(rating: Float): String {
    return when (rating) {
        in 0f..2f -> "Very Poor"
        in 3f..4f -> "Poor"
        in 5f..6f -> "Average"
        in 7f..8f -> "Good"
        else -> "Excellent"
    }
}

@Composable
fun SafeAction(action: String) {
    // Implement the action handling logic here
}