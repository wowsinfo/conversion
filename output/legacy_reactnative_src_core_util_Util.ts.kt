import android.util.Log
import androidx.compose.runtime.Composable

/**
 * Deep clone an object
 */
fun copy(value: Any): Any {
    return try {
        org.json.JSONObject(org.json.JSONTokener(value.toString())).toString(2)
    } catch (e: Exception) {
        Log.e("Utils", "Failed to deep clone the object", e)
        value
    }
}

/**
 * Return a number between 0 to range - 1
 */
fun random(range: Int): Int {
    return (Math.random() * range).toInt()
}

/**
 * Get date difference in days
 */
fun dayDifference(time: Long): Int {
    val timeDiff = Math.abs(System.currentTimeMillis() / 1000 - time)
    return ((timeDiff / (3600 * 24)).toInt())
}

/**
 * Get a readable date string
 */
fun humanTimeString(time: Long?): String {
    return if (time == null) "Unknown" else {
        if (time == 0L) "---"
        else {
            val obj = Date(time * 1000)
            "${obj.toISOString().substring(0, 10).replace("-", ".")} ${obj.toLocaleTimeString()}"
        }
    }
}

/**
 * Get the best cell width so that there won't be more than 6 items per row
 */
fun bestCellWidth(target: Int): Float {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    val usualCount = deviceWidth.toFloat() / target
    return if (usualCount > 6f) deviceWidth.toFloat() / 6f else target.toFloat()
}

/**
 * Get the cell width so that it fits the entire device width evenly
 */
fun bestCellWidthEven(target: Int): Float {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    val usualCount = deviceWidth.toFloat() / target
    return deviceWidth.toFloat() / Math.max(1, Math.floor(usualCount).toInt())
}

/**
 * Make sure the item isn't longer than the device,
 * if 2 items cannot be place just do one
 */
fun bestWidth(width: Int): Float {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    val maxCount = Math.round(deviceWidth / width)
    return deviceWidth.toFloat() / Math.max(1, maxCount)
}

/**
 * Get the current device width
 */
fun currDeviceWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}