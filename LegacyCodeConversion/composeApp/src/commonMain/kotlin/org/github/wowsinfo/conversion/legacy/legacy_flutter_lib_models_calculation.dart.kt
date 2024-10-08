
class Calculation {
    fun average(value: Double?, base: Double?): Double {
        if (value == null) return double.nan
        if (base == null) return double.nan
        return value / base
    }

    fun rate(value: Double?, base: Double?): Double {
        if (value == null) return double.nan
        if (base == null) return double.nan
        return (value * 10000 / base) / 100.0
    }
}