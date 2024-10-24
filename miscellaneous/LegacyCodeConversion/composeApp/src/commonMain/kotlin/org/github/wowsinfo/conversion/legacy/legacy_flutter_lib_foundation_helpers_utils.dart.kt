
class Utils {
    private val logger: Logger = Logger("Utils")

    fun delay(duration: Int): kotlinx.coroutines.Job {
        return kotlinx.coroutines.delay(duration.toLong())
    }

    fun getItemCount(context: Context, maxItem: Int, minItem: Int, itemWidth: Int): Int {
        val width = context.resources.displayMetrics.widthPixels
        val count = min(maxItem, (width / itemWidth).toInt()).coerceAtLeast(minItem)
        logger.fine("Item count: $count ($width)")
        return count
    }

    fun getItemWidth(context: Context, itemWidth: Int, maxCount: Int = 0, margin: Int = 0): Float {
        val width = context.resources.displayMetrics.widthPixels
        // This is the max count so scale the width up
        val count = (width / itemWidth).toInt().coerceAtMost(maxCount)

        // Max count is to make sure it fills the entire width
        if (maxCount != 0 && count > maxCount) return ((width / maxCount) - margin).toFloat()
        return ((width / count) - margin).toFloat()
    }
}