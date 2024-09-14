@Composable
fun LoadingIndicator(style: Modifier) {
    val appTheme = TintColour()
    if (appTheme == null) {
        appTheme = Blue
    }

    Box(modifier = style.padding(8.dp)) {
        CircularProgressIndicator(
            color = if (!isIos) appTheme[500] else Grey,
            strokeWidth = 3.dp,
        )
    }
}