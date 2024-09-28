
data class TimeStampDate(val timeStamp: Long) {
    init {
        require(timeStamp >= 0) { "timeStamp must be at least 0" }
    }

    val date: Instant = Instant.fromEpochSeconds(timeStamp)
    val dateTimeString: String = makeDateTimeString(date.toKotlinLocalDateTime())
    val dateString: String = dateTimeString.split(" ").first()

    private fun makeDateTimeString(dateTime: kotlinx.datetime.LocalDateTime): String {
        return dateTime.toString() // Customize this format as needed
    }
}

    val component = listOf(
        date.year,
        date.month,
        date.day,
        date.hour,
        date.minute,
        date.second
    ).map { it.toString().padStart(2, '0') }
    
    return "${component[0]}.${component[1]}.${component[2]} ${component[3]}:${component[4]}:${component[5]}"
}

    val timeStamp: Long
        get() = date.time

    fun compareTo(other: TimeStampDate): Int = (timeStamp - other.timeStamp).toInt()

    fun isWithinDurationFromNow(duration: Long): Boolean =
        isWithinDurationFrom(Date(), duration)

    fun isWithinDurationFrom(begin: Date, duration: Long): Boolean =
        date.after(Date(begin.time - duration))

    fun isBefore(other: TimeStampDate): Boolean = date.before(other.date)
}