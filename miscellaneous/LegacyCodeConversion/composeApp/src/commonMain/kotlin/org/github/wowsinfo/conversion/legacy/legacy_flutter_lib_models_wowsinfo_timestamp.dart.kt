
class TimeStampDate(val timeStamp: Long) {
    private val date = Clock.System.now().toEpochMilliseconds()
    private val dateTimeString = makeDateTimeString(date)
    private val dateString = dateTimeString.split(" ").first()

    fun compareTo(other: TimeStampDate): Int = timeStamp.compareTo(other.timeStamp)

    fun isWithinDurationFromNow(duration: Duration): Boolean =
        isWithinDurationFrom(Clock.System.now(), duration)

    fun isWithinDurationFrom(begin: DateTime, duration: Duration): Boolean =
        date.isAfter(begin.minus(duration))

    fun isBefore(other: TimeStampDate): Boolean = date.isBefore(other.date)

    private fun makeDateTimeString(date: Long): String {
        val component = listOf(
            date.year,
            date.monthNumber,
            date.dayOfMonth,
            date.hour,
            date.minute,
            date.second
        ).map { it.toString().padStart(2, '0') }
        return "${component[0]}.${component[1]}.${component[2]} " +
                "${component[3]}:${component[4]}:${component[5]}"
    }

}