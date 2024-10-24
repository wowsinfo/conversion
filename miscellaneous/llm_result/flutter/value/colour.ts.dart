
class AppGlobalData {
  static bool isDarkMode = false;

  static Future<void> toggleDarkMode() async {
    isDarkMode = !isDarkMode;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setBool('darkMode', isDarkMode);
  }

  static Color getThemeColor() {
    return isDarkMode ? Colors.grey[900]! : Colors.grey[100]!;
  }

  static Color getViewBackColor() {
    return isDarkMode ? Colors.black : Colors.white;
  }

  static Color getTintColor() {
    // Assuming LOCAL.theme is a string key for the theme color
    // Replace with actual implementation to retrieve the theme color
    return Colors.blue; // Placeholder for actual theme color retrieval
  }

  static Color getTintTextColor() {
    Color color = getTintColor();
    if (color == null) {
      color = Colors.blue; // Default color if no tint color is set
    }
    return color;
  }
}


class AppGlobalData {
  static Future<void> set(String key, String value) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }
}

class LOCAL {
  static const String theme = 'theme';
}

class SafeStorage {
  static Future<void> set(String key, String value) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }
}

Color getTintColor(String tint) {
  Map<String, Color> colour = {
    '500': Colors.blue, // Example color, replace with actual color mapping
  };
  return colour['500']!;
}

Future<void> updateTintColour(String tint) async {
  await AppGlobalData.set(LOCAL.theme, tint);
  await SafeStorage.set(LOCAL.theme, tint);
}