
@Composable
fun Rating() {
    val ratingRange = listOf(
        "0",
        "1 - 750",
        "750 - 1100",
        "1100 - 1350",
        "1350 - 1550",
        "1550 - 1750",
        "1750 - 2100",
        "2100 - 2450",
        "2450 - 9999"
    )
    val ratingComments = getRatingList()
    val ratingColours = getColourList()

    WoWsInfo(
        title = lang.rating_author,
        onClick = {
            SafeAction(
                "Statistics",
                mapOf("info" to mapOf("nickname" to "Wiochi", "account_id" to 503367319, "server" to 1)),
                1
            )
        }
    ) {
        ScrollableColumn {
            SectionTitle(title = lang.rating_title)
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = lang.rating_description)
                Text(text = lang.rating_warning, style = MaterialTheme.typography.bodySmall)
            }
            SectionTitle(title = lang.rating_scale)
            ratingRange.forEachIndexed { index, value ->
                val color = ratingColours[index]
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(text = ratingComments[index], color = color, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
                    Text(text = value, color = color, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
                }
            }
            Button(onClick = { SimpleViewHandler.openURL(APP.PersonalRating) }) {
                Text(text = lang.rating_read_more)
            }
        }
    }
}

@Preview
@Composable
fun PreviewRating() {
    Rating()
}

    modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
) {
    // Add your content here
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