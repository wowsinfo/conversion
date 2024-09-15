
@Composable
fun ShipName(name: String, isPremium: Boolean = false, isSpecial: Boolean = false) {
    /// Use a different colour for premium or special ships
    val style = when {
        isPremium -> TextStyle(
            fontSize = 14.sp,
            color = WoWsColours.premiumShip,
            fontWeight = FontWeight.Bold
        )
        isSpecial -> TextStyle(
            fontSize = 14.sp,
            color = WoWsColours.specialShip,
            fontWeight = FontWeight.Bold
        )
        else -> TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)
    }

    Text(
        text = name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = style
    )
}