
object ChartUtils {
    private val colours = listOf(
        Color(0xFF2196F3),
        Color(0xFFD32F2F),
        Color(0xFFFFEB3B),
        Color(0xFF4CAF50),
        Color(0xFF9C27B0),
        Color(0xFF00BCD4),
        Color(0xFFFF9800),
        Color(0xFF3F51B5),
        Color(0xFFCDDC39),
        Color(0xFFE91E63),
        Color(0xFF009688),
        Color(0xFF9E9E9E)
    )

    fun from(index: Int): Color = colours[index % colours.size]

    val damageColour = Color(0xFFD32F2F)
    val fragsColour = Color(0xFF2196F3)
    val winrateColour = Color(0xFF4CAF50)
    val battleColour = Color(0xFFFF9800)

    fun <T, V> convert(
        id: String,
        values: List<ChartValue<T, V>>,
        color: Color,
        domainFn: (ChartValue<T, V>, Int?) -> T,
        measureFn: (ChartValue<T, V>, Int?) -> Number?,
        labelFormatter: ((ChartValue<T, V>, Int?) -> String?)? = null
    ): Series<ChartValue<T, V>, T> {
        // Implementation of the conversion logic to create a Series
    }
}

    id: String,
    values: List<Any>,
    domainFn: (Any) -> Any,
    measureFn: (Any) -> Any,
    labelFormatter: (Any) -> String,
    color: Color
): Series {
    return Series(
        id = id,
        data = values,
        domainFn = domainFn,
        measureFn = measureFn,
        labelAccessorFn = labelFormatter,
        colorFn = { v, _ -> color }
    )
}

    id: String,
    values: List<ChartValue<T, Double>>,
    color: Color,
    labelFormatter: ((ChartValue<T, Double>, Int?) -> String)? = null
): List<ChartValue<T, Double>> {
    // Implementation of the conversion logic
    return values.mapIndexed { index, chartValue ->
        ChartValue(
            id = chartValue.id,
            value = chartValue.value,
            color = color,
            label = labelFormatter?.invoke(chartValue, index) ?: ""
        )
    }
}

data class ChartValue<T, V>(
    val id: T,
    val value: V,
    val color: Color,
    val label: String
)

data class Color(val red: Int, val green: Int, val blue: Int)

    id: String,
    values: List<Value>,
    color: Color,
    labelFormatter: (String) -> String
): Chart {
    return Chart(
        id = id,
        values = values,
        color = color,
        domainFn = { v -> v.name },
        measureFn = { v -> v.value },
        labelFormatter = labelFormatter
    )
}

data class Value(val name: String, val value: Float)

data class Chart(
    val id: String,
    val values: List<Value>,
    val color: Color,
    val domainFn: (Value) -> String,
    val measureFn: (Value) -> Float,
    val labelFormatter: (String) -> String
)

data class Color(val hex: String)


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
            Text("Click Me")
        }
    }
}