
class ScreenSize private constructor(private val context: android.content.Context) {
    companion object {
        const val maxDialogWidth: Dp = 500.dp

        fun of(context: android.content.Context): ScreenSize {
            return ScreenSize(context)
        }
    }

    val screenSize: androidx.compose.ui.unit.IntSize
        get() = androidx.compose.ui.platform.LocalDensity.current.run {
            androidx.compose.ui.unit.IntSize(
                context.resources.displayMetrics.widthPixels.toInt(),
                context.resources.displayMetrics.heightPixels.toInt()
            )
        }

    fun isPhone(): Boolean {
        return screenSize.width < 600
    }
}

@Composable
fun getScreenSize(): ScreenSize {
    val context = LocalContext.current
    return ScreenSize.of(context)
}


@Composable
fun isTablet(): Boolean {
    val context = LocalContext.current
    val screenSize = context.resources.displayMetrics.run {
        android.util.Size(widthPixels / densityDpi, heightPixels / densityDpi)
    }
    return screenSize.width >= 600
}

    val width = screenSize.width
    return width >= 600 && width < 840
}

data class Size(val width: Int, val height: Int)

    return screenSize.width >= 840
}

data class ScreenSize(val width: Int, val height: Int)


@Composable
fun isPhone8(): Boolean {
    val context = LocalContext.current
    val density = LocalDensity.current
    val screenWidth = with(density) { context.resources.displayMetrics.widthPixels.toDp() }
    return screenWidth.value <= 375
}


@Composable
fun isPhoneSE(): Boolean {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width: Dp = displayMetrics.widthPixels.dp
    return width.value <= 320
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