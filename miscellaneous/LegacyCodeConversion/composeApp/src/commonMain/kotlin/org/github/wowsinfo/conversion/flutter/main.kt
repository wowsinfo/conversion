
@ThreadLocal
object Logger {
    private val logger = LoggerFactory.getLogger("AppLogger")

    fun log(level: String, message: String) {
        if (BuildConfig.DEBUG) {
            println("$level|$message")
        }
    }
}

suspend fun setup() {
    withContext(Dispatchers.Default) {
        Logger.log("INFO", "Setting up application")
    }
}


fun main() = runBlocking {
    setup()
    // Launch the app
    WoWsInfoApp()
}

suspend fun setup() {
    val store = SharedStore()
    store.load()

    AppRepository.instance.inject(store)
    UserRepository.instance.inject(store)

    loadAppData()
}

suspend fun loadAppData() {
    Localisation.instance.initialise()
    GameRepository.instance.initialise()
}

@Composable
fun WoWsInfoApp() {
    val context = LocalContext.current
    MaterialTheme {
        Surface {
            // Your main app content goes here
        }
    }
}