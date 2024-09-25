
@ThreadLocal
object TintColour {
    fun getTintColour(): Color? {
        // Implement your logic to get the tint color based on the theme
        return null // Placeholder
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    val appTheme = TintColour.getTintColour() ?: Color.Blue
    Box(modifier = modifier) {
        CircularProgressIndicator(
            color = appTheme,
            modifier = Modifier.size(50.dp)
        )
    }
}

fun LoadingIndicator(style: Modifier = Modifier, isIos: Boolean, appTheme: Color, Grey: Color) {
    CircularProgressIndicator(
        modifier = style.then(Modifier.padding(top = 8.dp)),
        strokeWidth = if (isIos) 2.dp else 4.dp,
        color = if (!isIos) appTheme else Grey
    )
}