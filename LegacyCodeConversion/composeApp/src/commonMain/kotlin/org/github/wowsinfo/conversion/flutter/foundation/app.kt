
object App {
    const val appVersion = "1.7.0"
    const val githubLink = "https://github.com/WoWs-Info/WoWs-Info-Seven"
    const val appstoreLink = "https://itunes.apple.com/app/id1202750166"
    const val playstoreLink = "https://play.google.com/store/apps/details?id=com.yihengquan.wowsinfo"
    const val latestRelease = "$githubLink/releases/latest"
    const val newIssueLink = "$githubLink/issues/new"
    const val emailToLink = "mailto:development.henryquan@gmail.com?subject=[WoWs Info $appVersion]"

    private var context: Any? = null

    fun inject(context: Any) {
        this.context = context
    }
}

@Composable
fun AppScreen() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    // Example usage of the links
    // uriHandler.openUri(App.githubLink)
    // uriHandler.openUri(App.appstoreLink)
    // uriHandler.openUri(App.playstoreLink)
}

@Preview
@Composable
fun PreviewAppScreen() {
    AppScreen()
}


object PlatformUtils {
    val isWeb: Boolean = false

    val isIOS: Boolean = UIDevice.currentDevice.systemName == "iOS"

    val isAndroid: Boolean = false // Android detection is not available in Kotlin Multiplatform

    val isMobile: Boolean = !isWeb && (isIOS || isAndroid)

    val isDesktop: Boolean = !isWeb && !isMobile

    val isApple: Boolean = !isWeb && (isIOS || NSProcessInfo.processInfo.environment["OS"] == "macOS")

    fun platformPageRoute(
        builder: @Composable (context: Context) -> Unit,
        settings: RouteSettings? = null,
        maintainState: Boolean = true,
        fullscreenDialog: Boolean = false
    ): NavHostController {
        // Implement navigation logic based on platform
    }
}

    return if (isMobile) {
        NavOptions.Builder()
            .setLaunchSingleTop(maintainState)
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
            .build()
    } else {
        object : NavOptions {
            override fun createTransition(): Transition {
                return FadeTransition(
                    duration = 300,
                    content = {
                        builder(it)
                    }
                )
            }
        }
    }
}


suspend fun launch(url: String) {
    val canLaunch = withContext(Dispatchers.Main) {
        val nsUrl = NSURL.URLWithString(url)
        NSWorkspace.sharedWorkspace().openURL(nsUrl)
    }
    if (!canLaunch) {
        throw IllegalArgumentException("Could not launch $url")
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
            Text("Click me")
        }
    }
}