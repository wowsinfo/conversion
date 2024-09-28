
class TimeTracker {
    private val logger = Logger.getLogger("TimeTracking")
    private val timer: Instant = Clock.System.now()

    init {
        logger.info("Timer started at: $timer")
    }
}


private val logger = KotlinLogging.logger {}
private var timer: Instant = Clock.System.now()

fun log(message: String? = null) {
    val duration = Clock.System.now().minus(timer).toEpochMilliseconds()
    logger.info("${message ?: "Time"} $duration ms")
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