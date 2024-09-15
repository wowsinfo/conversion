
class WoWsInfoApp {
    @Composable
    fun Main() {
        setupListeners()
        val appProvider = remember { AppProvider() }
        ChangeNotifierProvider(
            viewModel = appProvider,
            content = { child ->
                SupaBaseClient().initialize()
                MaterialTheme(
                    colorScheme = appProvider.themeData.colorScheme,
                    shapes = appProvider.shapes,
                    content = {
                        ScrollBehavior(
                            dragDevices = DragDevices.defaults
                                + setOf(PointerInputDeviceKind.mouse),
                            scrollbarsEnabled = true,
                            overscrollIndicatorAlwaysVisible = false,
                        )
                    }
                ) { child }
            }
        )
    }

    private fun setupListeners() {
        val logger = Logger.getLogger("WoWsInfoApp")
        val window = LocalContext.current.window
        window.onLocaleChanged = { logger.info("Locale changed to ${window.locale}") }
        window.onPlatformBrightnessChanged = {
            logger.info(
                "Platform brightness changed to ${window.platformBrightness}"
            )
        }
    }

    private fun goBack() {
        Logger.getLogger("GoBackAction").info("Go back")
        LocalContext.current.navigator.pop()
    }

    @Composable
    fun GlobalShortcuts(child: @Composable () -> Unit) {
        val context = LocalContext.current
        Shortcuts(
            shortcuts = mapOf(
                LogicalKeySet(LogicalKeyboardKey.escape) to GoBackIntent(),
            ),
            actions = mapOf(GoBackIntent() to GoBackAction(context)),
            content = child,
        )
    }
}

class AppProvider : ChangeNotifier {
    private var _themeData: MaterialTheme = MaterialTheme(colorScheme = ColorScheme.Light)
    val themeData: MaterialTheme
        get() = _themeData

    fun updateTheme(theme: MaterialTheme) {
        _themeData = theme
        notifyListeners()
    }

    // Add other state variables and methods as needed
}

class GoBackIntent : Intent
class GoBackAction(private val context: Context?) : Action<GoBackIntent>() {
    override fun invoke(intent: GoBackIntent): Any? {
        Logger.getLogger("GoBackAction").info("Go back")
        return if (context?.navigator != null) {
            context.navigator.pop()
            null
        } else {
            null
        }
    }
}