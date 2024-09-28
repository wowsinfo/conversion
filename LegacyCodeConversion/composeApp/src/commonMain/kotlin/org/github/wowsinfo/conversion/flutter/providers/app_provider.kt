
class AppProvider {
    private var _darkMode by mutableStateOf(false)
    val darkMode: Boolean get() = _darkMode

    private var _themeColour by mutableStateOf(AppThemeColour(0))
    val themeColour: MaterialColor get() = _themeColour.colour

    private var _themeData by mutableStateOf(generateThemeData())
    val themeData: ThemeData get() = _themeData

    private var _locale by mutableStateOf(Locale.getDefault())
    val locale: Locale get() = _locale

    private val userRepository = UserRepository.instance
    private val logger = LoggerFactory.getLogger(AppProvider::class.java)

    init {
        _darkMode = userRepository.darkMode
        _themeColour = AppThemeColour(userRepository.themeColour)
        _locale = Locale(userRepository.appLanguage)
        logger.info("AppProvider created successfully.")
    }

    private fun generateThemeData(): ThemeData {
        // Implement theme data generation logic here
    }
}

data class AppThemeColour(val index: Int) {
    val colour: MaterialColor
        get() = // Implement logic to return MaterialColor based on index
}

class UserRepository {
    companion object {
        val instance = UserRepository()
    }

    val darkMode: Boolean
        get() = // Implement logic to retrieve dark mode preference

    val themeColour: Int
        get() = // Implement logic to retrieve theme colour index

    val appLanguage: String
        get() = // Implement logic to retrieve app language
}


@Composable
fun generateThemeData(themeColour: ThemeColour, darkMode: Boolean): MaterialTheme {
    val color = themeColour.colour
    return MaterialTheme(
        colorScheme = if (darkMode) {
            darkColorScheme(primary = color)
        } else {
            lightColorScheme(primary = color)
        },
        typography = Typography(),
        shapes = Shapes(),
        content = {
            // Your content goes here
        }
    )
}

data class ThemeColour(val colour: Color)

    logger.info("updated DarkMode to $darkMode")
    userRepository.darkMode = darkMode
    this.darkMode = darkMode

    themeData = generateThemeData()
    notifyListeners()
}

    private var _locale: Locale = Locale.getDefault()
    
    fun updateLocale(locale: Locale) {
        println("updated Locale to ${locale.language}")
        _locale = locale
        notifyObservers()
    }
    
    private fun notifyObservers() {
        // Notify observers about the change
    }
}

    logger.info("updated ThemeColour to ${colour.toString()}")
    themeColour = AppThemeColour.fromColour(colour)
    userRepository.themeColour = themeColour.index

    themeData = generateThemeData()
    notifyListeners()
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click Me")
        }
    }
}