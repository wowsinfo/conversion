
private var isDarkMode = false

@Composable
fun themeBackColour(): Color {
  return if (isDarkMode) Color(GREY[900]) else Color(GREY[100])
}

@Composable
fun viewBackColour(): Color {
  return if (isDarkMode) Color.Black else Color.White
}

@Composable
fun themeColour(): Color {
  return if (isDarkMode) Color(GREY[900]) else Color(GREY[100])
}

fun updateDarkMode() {
  isDarkMode = !isDarkMode
}

@Composable
fun tintColour(): Color {
  val colour = AppGlobalData.get(LOCAL.theme)
  return if (colour != null) Color(colour[500]) else BLUE
}

@Composable
fun tintTextColour(): Color {
  val colour = AppGlobalData.get(LOCAL.theme)
  return if (colour != null) Color(colour[500]) else Color.BLUE
}

fun updateTintColour(tint: Any) {
  AppGlobalData.set(LOCAL.theme, tint)
}