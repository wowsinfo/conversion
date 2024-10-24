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
        onInfoClick = {
            SafeAction(
                "Statistics",
                info = Info(nickname = "Wiochi", accountId = 503367319, server = 1),
                1
            )
        }
    ) {
        Column {
            SectionTitle(title = lang.rating_title)
            Box(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = lang.rating_description)
                Caption(text = lang.rating_warning)
            }

            SectionTitle(title = lang.rating_scale)

            ratingRange.forEachIndexed { index, v ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ratingComments[index],
                        style = MaterialTheme.typography.body2.copy(color = Color(ratingColours[index]))
                    )
                    Text(
                        text = v,
                        style = MaterialTheme.typography.body2.copy(color = Color(ratingColours[index]), fontWeight = FontWeight.Bold)
                    )
                }
            }

            Button(onClick = {
                SimpleViewHandler.openURL(APP.PersonalRating)
            }) {
                Text(text = lang.rating_read_more)
            }
        }
    }
}