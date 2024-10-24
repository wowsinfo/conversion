
@Composable
fun ShipIcon(
    icon: String,
    height: Dp = 80.dp,
    width: Dp? = null,
    hero: Boolean = false,
    isNew: Boolean = false
) {
    val fullIcon = "data/live/app/assets/ships/$icon.png"
    Box(modifier = Modifier.size(height, width)) {
        StackBox(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            children = {
                Image(
                    painter = painterResource(id = fullIcon),
                    contentDescription = null
                )
                if (isNew) NewItemIndicator()
            }
        )
    }

    if (hero) {
        Hero(tag = icon, child = ShipIcon(icon = icon, height = height, width = width, hero = false, isNew = isNew))
    }
}