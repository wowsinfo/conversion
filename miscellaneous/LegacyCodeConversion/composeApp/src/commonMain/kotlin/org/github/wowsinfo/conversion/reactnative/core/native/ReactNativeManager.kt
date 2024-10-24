
object QuickAction

expect object NativeModules {
    val ReactNativeManager: ReactNativeManager
}

class ReactNativeManager private constructor() {
    companion object {
        private var _instance: ReactNativeManager? = null

        val instance: ReactNativeManager
            get() = _instance ?: ReactNativeManager().also { _instance = it }
    }
}

private val nativeEvents = NativeEvents()

fun setup() {
    quickActionManager = QuickAction()
}

    fun appHasLoaded() {
        Manager.reactNativeHasLoaded()
    }
}


@Composable
fun MyComponent() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $count")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }
        }) {
            Text("Increment")
        }
    }
}