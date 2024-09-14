@Composable
fun SimpleRating(info: Info) {
    val horizontal = Modifier.fillMaxWidth().padding(16.dp)
    var ratingColour = getColour(info.rating)

    val nothing = info.pvp == null || info.pvp.battles == 0

    val iconStyle = ImagePainter(
        painterResource("Battle"),
        contentDescription = null,
        modifier = Modifier.size(24.dp).padding(end = 8.dp)
    ).apply {
        this.colorFilter = ColorFilter.tint(Color(ratingColour))
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = horizontal) {
            Image(
                painter = iconStyle.painter,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(if (nothing) "0" else info.pvp?.battles.toString(), style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Light))
        }

        Row(modifier = horizontal) {
            Image(
                painter = iconStyle.painter,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(if (nothing) "0.0%" else "${roundTo((info.pvp?.wins!! / info.pvp.battles) * 100, 2)}%", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Light))
        }

        Row(modifier = horizontal) {
            Image(
                painter = iconStyle.painter,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(if (nothing) "0" else roundTo(info.pvp?.damage_dealt!! / info.pvp.battles).toString(), style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Light))
        }

        Spacer(modifier = Modifier.height(12.dp).background(Color(ratingColour)))
    }
}