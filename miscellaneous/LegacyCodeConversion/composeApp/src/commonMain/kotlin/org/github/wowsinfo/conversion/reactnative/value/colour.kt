
object AppGlobalData {
    var isDarkMode = mutableStateOf(false)
    fun get(theme: String): Color? {
        // Implementation to get the theme color
        return null
    }
}

object LOCAL {
    const val darkMode = "darkMode"
    const val theme = "theme"
}

object SafeStorage {
    fun set(key: String, value: Boolean) {
        // Implementation to save the value
    }
}

fun ThemeBackColour(): Color {
    return if (AppGlobalData.isDarkMode.value) Color(0xFF212121) else Color(0xFFF5F5F5)
}

fun ViewBackColour(): Color {
    return if (AppGlobalData.isDarkMode.value) Color.Black else Color.White
}

fun ThemeColour(): Color {
    return if (AppGlobalData.isDarkMode.value) Color(0xFF212121) else Color(0xFFF5F5F5)
}

fun UpdateDarkMode() {
    AppGlobalData.isDarkMode.value = !AppGlobalData.isDarkMode.value
    SafeStorage.set(LOCAL.darkMode, AppGlobalData.isDarkMode.value)
}

fun TintColour(): Color? {
    return AppGlobalData.get(LOCAL.theme)
}

fun TintTextColour(): Color {
    var colour = TintColour()
    if (colour == null) {
        colour = Color.Blue
    }
    return colour
}

    return mapOf("color" to colour["500"]!!)
}

fun updateTintColour(tint: Any) {
    AppGlobalData.set(LOCAL.theme, tint)
    SafeStorage.set(LOCAL.theme, tint)
}