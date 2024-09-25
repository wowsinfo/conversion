    return value as? Map<*, *> ?: value
}

fun random(range: Int): Int {
    return (0 until range).random()
}

fun roundTo(num: Double?, digit: Int = 0): Double {
    return num?.let {
        String.format("%.${digit}f", it).toDouble()
    } ?: -1.0
}

    return if (num == null || num.isInfinite() || num.isNaN()) {
        -1.0
    } else {
        num
    }
}

    return String.format("%.${digit}f", num).toDouble()
}

fun dayDifference(time: Long): Int {
    val timeDiff = Math.abs(System.currentTimeMillis() / 1000 - time)
    return Math.ceil(timeDiff.toDouble() / (3600 * 24)).toInt()
}

fun humanTimeString(time: Long?): String {
    return time?.let { it.toString() } ?: "Unknown"
}

    return "---"
}


fun formatDate(time: Long): String {
    val obj = Instant.fromEpochSeconds(time).toLocalDateTime(TimeZone.currentSystemDefault())
    return "${obj.date} ${obj.hour}:${obj.minute}:${obj.second}"
}

fun getRandomAnimation(): String {
    val list = listOf(
        "bounce",
        "flash",
        "pulse",
        "rotate",
        "rubberBand",
        "shake",
        "swing",
        "tada",
        "wobble"
    )
    return list[Random.nextInt(list.size)]
}

fun bestCellWidth(target: Int): Float {
    val deviceWidth = getDeviceWidth() // Implement this function to get device width
    val usualCount = deviceWidth / target
    return if (usualCount > 6) {
        deviceWidth / 6
    } else {
        target.toFloat()
    }
}


fun bestCellWidthEven(target: Dp): Dp {
    val deviceWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp
    val usualCount = deviceWidth.value / target.value
    val result = deviceWidth.value / Math.floor(usualCount).toInt()
    return result.dp
}

fun bestWidth(width: Dp, deviceWidth: Dp = currDeviceWidth()): Dp {
    val maxCount = (deviceWidth.value / width.value).toInt()
    return (deviceWidth.value / maxOf(1, maxCount)).dp
}

fun currDeviceWidth(): Dp {
    return LocalContext.current.resources.displayMetrics.widthPixels.dp
}