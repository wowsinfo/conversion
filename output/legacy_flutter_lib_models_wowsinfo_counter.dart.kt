abstract class Counter {
    fun add(value: Number) {}
}

class AverageCounter : Counter() {
    private var _total = 0.0
    private var _count = 0
    var average: Double = 0.0

    override fun add(value: Number) {
        _total += value.toDouble()
        _count++
        average = _total / _count
    }
}

class TotalCounter : Counter() {
    private var _total = 0.0
    val total: Double
        get() = _total

    override fun add(value: Number) {
        _total += value.toDouble()
    }
}