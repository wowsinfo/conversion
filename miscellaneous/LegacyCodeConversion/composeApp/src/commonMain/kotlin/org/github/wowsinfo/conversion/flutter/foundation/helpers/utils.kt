
class Utils(private val context: Any) {
    companion object {
        fun of(context: Any): Utils {
            return Utils(context)
        }
    }

    private val logger: Logger = LoggerFactory.getLogger(Utils::class.java)

    suspend fun delay(duration: Int) {
        delay(duration.toLong())
    }
}

    val width = context.resources.displayMetrics.widthPixels.toFloat()
    val count = minOf(maxItem, maxOf(width / itemWidth, minItem)).toInt()
    Log.d("ItemCount", "Item count: $count ($width)")
    return count
}

    val width = context.resources.displayMetrics.widthPixels.toDouble()
    val count = (width / itemWidth).toInt()

    return if (maxCount != 0 && count > maxCount) {
        (width / maxCount) - margin.toDouble()
    } else {
        (width / count) - margin.toDouble()
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
            count++
        }) {
            Text("Click me")
        }
    }
}