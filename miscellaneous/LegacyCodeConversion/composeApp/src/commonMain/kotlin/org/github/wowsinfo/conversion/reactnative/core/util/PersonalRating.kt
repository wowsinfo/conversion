
fun getOverall(id: String): Any? {
    return AppGlobalData.get(SAVED.pr)[id]
}

fun calRating(
    actualDmg: Double,
    expectedDmg: Double,
    actualWins: Double,
    expectedWins: Double,
    actualFrags: Double,
    expectedFrags: Double
): Double {
    val rDmg = actualDmg / expectedDmg
    val rWins = actualWins / expectedWins
    val rFrags = actualFrags / expectedFrags

    val nDmg = max(0.0, (rDmg - 0.4) / (1 - 0.4))
    val nFrags = max(0.0, (rFrags - 0.1) / (1 - 0.1))
    val nWins = max(0.0, (rWins - 0.7) / (1 - 0.7))

    val rating = roundTo(700 * nDmg + 300 * nFrags + 150 * nWins)
    return min(rating, 9999.0)
}

fun getOverallRating(ships: Any?): Double {
    if (ships == null) {
        return -1.0
    }
    // Additional logic to calculate overall rating for ships
}

var expectedDmg = 0.0
var actualWins = 0.0
var expectedWins = 0.0
var actualFrags = 0.0
var expectedFrags = 0.0

for (ship in ships) {
    ship.rating = -1
    ship.ap = 0

    val pvp = SafeValue(ship.pvp, null)
    if (pvp != null) {
        val overall = getOverall(ship.ship_id) ?: continue

        val (battles, damage_dealt, frags, wins) = pvp
        val (average_damage_dealt, average_frags, win_rate) = overall

        if (battles == 0) {
            continue
        }
        val currAvgDmg = damage_dealt / battles
        val currWinrate = (wins / battles) * 100
        val currFrags = frags / battles
        ship.avgDmg = currAvgDmg
        ship.avgWinrate = currWinrate
        ship.avgFrags = currFrags

        actualDmg += currAvgDmg
        actualWins += currWinrate
        actualFrags += currFrags
        expectedDmg += average_damage_dealt
        expectedWins += win_rate
        expectedFrags += average_frags

        val rating = calRating(
            currAvgDmg,
            average_damage_dealt,
            currWinrate,
            win_rate,
            currFrags,
            average_frags
        )
        ship.rating = rating
        ship.ap = getAP(rating, battles)
    }
}

    actualDmg: Double,
    expectedDmg: Double,
    actualWins: Int,
    expectedWins: Int,
    actualFrags: Int,
    expectedFrags: Int
): Double {
    return calRating(actualDmg, expectedDmg, actualWins, expectedWins, actualFrags, expectedFrags)
}

fun getAP(rating: Double, battle: Int): Double {
    return if (rating == -1.0 || battle == 0) {
        0.0
    } else {
        // Add your logic here for calculating AP
    }
}

    return roundTo(Math.log10(max(10.0, battle.toDouble())) * rating)
}

    0, 750, 1100, 1350, 1550, 1750, 2100, 2450, 9999
)

fun getRatingIndex(rating: Int?): Int {
    if (rating == null) {
        return 0
    }
    val ranges = getRatingRange()
    for (i in ranges.indices) {
        if (rating < ranges[i]) {
            return i
        }
    }
    return ranges.size - 1
}

    return listOf(0, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9999)
}

fun getRatingIndex(rating: Int?): Int {
    val range = getRatingRange()
    val index = range.indexOfFirst { rating ?: 0 < it }
    return if (index == -1) 0 else index
}

fun getColourList(): List<String> {
    return listOf(
        "#607D8B",
        "#D32F2F",
        "#FF9800",
        "#FFB300",
        "#7CB342",
        "#388E3C",
        "#03A9F4",
        "#9C27B0",
        "#673AB7",
        "black"
    )
}

fun getColour(rating: Int?): String {
    val colours = getColourList()
    return colours[getRatingIndex(rating)].takeIf { it.isNotEmpty() } ?: "#607D8B"
}

fun getRatingList(): List<String> {
    return listOf(
        lang.rating_unknown,
        lang.rating_bad,
        lang.rating_below_average,
        lang.rating_average,
        lang.rating_good,
        lang.rating_very_good,
        lang.rating_great,
        lang.rating_unicum,
        lang.rating_super_unicum
    )
}

fun getComment(rating: Int): String {
    val comments = getRatingList()
    val index = getRatingIndex(rating)
    var comment = comments[index]
    val range = getRatingRange()[index]

    var diff = range - rating
    if (range == 9999) {
        diff = rating - 2450
    }
    return comment
}

    return "$comment (+$diff)"
}