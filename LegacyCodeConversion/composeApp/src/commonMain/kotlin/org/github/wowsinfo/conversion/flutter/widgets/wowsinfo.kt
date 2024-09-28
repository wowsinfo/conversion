
val appModule = module {
    single { AppProvider() }
}

@Composable
fun WoWsInfoApp() {
    val appProvider: AppProvider = getViewModel()
    MaterialTheme(colors = appProvider.themeData) {
        Surface(modifier = Modifier.fillMaxSize()) {
            GlobalShortcuts {
                DebugPage()
            }
        }
    }
}

@Composable
fun GlobalShortcuts(content: @Composable () -> Unit) {
    // Implement global shortcuts logic here
    content()
}

@Composable
fun DebugPage() {
    // Implement DebugPage UI here
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WoWsInfoApp()
}

fun main() {
    startKoin {
        modules(appModule)
    }
}


@Composable
fun SetupListeners() {
    val logger = LoggerFactory.getLogger("WoWsInfoApp")
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        val localeObserver = { newLocale: Locale ->
            logger.info("Locale changed to $newLocale")
        }
        val brightnessObserver = { newBrightness: Int ->
            logger.info("Platform brightness changed to $newBrightness")
        }

        // Assuming you have a way to observe locale and brightness changes
        observeLocaleChanges(localeObserver)
        observeBrightnessChanges(brightnessObserver)
    }
}

fun observeLocaleChanges(observer: (Locale) -> Unit) {
    // Implementation to observe locale changes
}

fun observeBrightnessChanges(observer: (Int) -> Unit) {
    // Implementation to observe brightness changes
}


class WoWsInfocrollBehavior {
    @Composable
    fun DragableBox(content: @Composable () -> Unit) {
        val context = LocalContext.current
        Box(
            modifier = Modifier.pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    // Handle drag gestures
                }
            }
        ) {
            content()
        }
    }
}

class LoggingActionDispatcher {
    private val logger = LoggerFactory.getLogger(LoggingActionDispatcher::class.java)

    fun invokeAction(action: Action<Intent>, intent: Intent, context: Any?) {
        logger.info("Action invoked: $action($intent) from $context")
        // Implement action invocation logic here
    }
}

interface Action<T> {
    fun execute(intent: T)
}

interface Intent


@Composable
fun GlobalShortcuts(child: @Composable () -> Unit) {
    val shortcuts = remember { /* Initialize your shortcuts here */ }
    // Implement your global shortcuts logic here
    child()
}


@Composable
fun ShortcutsExample(child: @Composable () -> Unit) {
    val context = LocalContext.current
    val view = LocalView.current

    Box(modifier = Modifier.onKeyEvent { keyEvent ->
        if (keyEvent.key == Key.Escape) {
            GoBackAction(context)
            true
        } else {
            false
        }
    }) {
        child()
    }
}

fun GoBackAction(context: Any) {
    // Implement the logic to go back in the navigation stack
}


data class GoBackIntent()

class GoBackAction(private val navController: NavController) {
    private val logger: Logger = LoggerFactory.getLogger(GoBackAction::class.java)

    fun invoke(intent: GoBackIntent) {
        logger.info("Go back")
        navController.popBackStack()
    }
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