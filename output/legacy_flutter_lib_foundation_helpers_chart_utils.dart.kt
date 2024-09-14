import 'dart:math'

class ChartValue<T, V> {
    val name: T
    val value: V

    constructor(this.name, this.value)
}

class ChartUtils {
    companion object {
        private val colours = listOf(
            MaterialPalette.blue.shadeDefault,
            MaterialPalette.red.shadeDefault,
            MaterialPalette.yellow.shadeDefault,
            MaterialPalette.green.shadeDefault,
            MaterialPalette.purple.shadeDefault,
            MaterialPalette.cyan.shadeDefault,
            MaterialPalette.deepOrange.shadeDefault,
            MaterialPalette.indigo.shadeDefault,
            MaterialPalette.lime.shadeDefault,
            MaterialPalette.pink.shadeDefault,
            MaterialPalette.teal.shadeDefault,
            MaterialPalette.gray.shadeDefault
        )

        fun from(index: Int): Color = colours[(index % colours.size)]

        val damageColour = Color.fromHex(code = "#D32F2F")
        val fragsColour = Color.fromHex(code = "#2196F3")
        val winrateColour = Color.fromHex(code = "#4CAF50")
        val battleColour = Color.fromHex(code = "#FF9800")

        fun <T, V> convert(
            id: String,
            values: List<ChartValue<T, V>>,
            color: Color,
            domainFn: (ChartValue<T, V>, Int?) -> T,
            measureFn: (ChartValue<T, V>, Int?) -> Number?,
            labelFormatter: ((ChartValue<T, V>, Int?) -> String)? = null
        ): Series<ChartValue<T, V>, T> {
            return Series(
                id = id,
                data = values,
                domainFn = domainFn,
                measureFn = { v, _ -> measureFn(v, _) },
                labelAccessorFn = labelFormatter,
                colorFn = { _, _ -> color }
            )
        }

        fun <T> convertDefault(
            id: String,
            values: List<ChartValue<T, Number>>,
            color: Color,
            labelFormatter: ((ChartValue<T, Number>, Int?) -> String)? = null
        ): Series<ChartValue<T, Number>, T> {
            return convert(
                id = id,
                values = values,
                color = color,
                domainFn = { v, _ -> v.name },
                measureFn = { v, _ -> v.value },
                labelFormatter = labelFormatter
            )
        }
    }
}