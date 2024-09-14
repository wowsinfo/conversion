import kotlin.time.DurationUnit
import kotlin.time.toDuration

class DateTimeExtension {
    fun DateTime.toHumanString(): String =
        listOf(year, month, day).joinToString("-") { it.toString().padStart(2, '0') }

    fun DateTime.diffInDay(other: DateTime): Int {
        val duration = other.difference(this).toDuration(DurationUnit.DAYS)
        return duration.toInt()
    }

    companion object {
        fun fromTimeStamp(timeStamp: Int) = DateTime.fromMillisecondsSinceEpoch(timeStamp.toLong() * 1000L)
    }
}