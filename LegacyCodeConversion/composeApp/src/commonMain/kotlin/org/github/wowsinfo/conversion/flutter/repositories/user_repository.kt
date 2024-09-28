    companion object {
        private const val appLanguageKey = "wowsinfo@app_language"
        private const val dataLanguageKey = "wowsinfo@data_language"
        private const val usernameKey = "wowsinfo@username"
        private const val accountIDKey = "wowsinfo@account_id"
        private const val gameServerKey = "wowsinfo@game_server"
        private const val darkModeKey = "wowsinfo@dark_mode"
        private const val themeColourKey = "wowsinfo@theme_colour"

        val instance: UserRepository by lazy { UserRepository() }
    }

    private lateinit var store: StoreInterface

    fun inject(store: StoreInterface) {
        this.store = store
    }
}


    private val defaultLanguage = Localisation.decideLang()

    var appLanguage: String
        get() = store.get(appLanguageKey) ?: defaultLanguage
        set(value) {
            store.set(appLanguageKey, value)
        }

    var dataLanguage: String
        get() = store.get(dataLanguageKey) ?: defaultLanguage
        set(value) {
            store.set(dataLanguageKey, value)
        }

    val isFavourite: Boolean
        get() = store.has(accountIDKey)

    var username: String
        get() = store.get(usernameKey) ?: ""
        set(value) {
            store.set(usernameKey, value)
        }

    var accountID: String
        get() = store.get(accountIDKey) ?: ""
        set(value) {
            store.set(accountIDKey, value)
        }

    var gameServer: Int
        get() = store.get(gameServerKey) ?: 3
        set(value) {
            store.set(gameServerKey, value)
        }

    var themeColour: Int
        get() = store.get(themeColourKey) ?: AppThemeColour.defaultIndex
        set(index) {
            store.set(themeColourKey, index)
        }

    var darkMode: Boolean
        get() = store.get(darkModeKey) ?: false
        set(value) {
            store.set(darkModeKey, value)
        }
}

object AppThemeColour {
    val colourList = listOf(
        Color.Red,
        Color.Pink,
        Color.Purple,
        Color.DeepPurple,
        Color.Indigo,
        Color.Blue,
        Color.LightBlue,
        Color.Cyan,
        Color.Teal,
        Color.Green,
        Color.LightGreen,
        Color.Lime,
        Color.DeepOrange,
        Color.Brown,
        Color.Gray,
        Color.BlueGray
    )

    const val defaultIndex = 0
}

    private val colourList = listOf(/* Add your color values here */)
    private var _colour = colourList[index]

    val index: Int
        get() = colourList.indexOf(_colour)
}

    return AppThemeColour(index = colourList.indexOf(colour))
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Greeting("World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Hello, $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}