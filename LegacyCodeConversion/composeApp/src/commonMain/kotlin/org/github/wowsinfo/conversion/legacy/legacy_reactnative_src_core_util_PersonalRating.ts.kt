
class RatingCalculator {
    companion object {
        fun getOverall(id: String): Int? = AppGlobalData.get(SAVED.pr)[id]

        private fun calRating(
            actualDmg: Double,
            expectedDmg: Double,
            actualWins: Double,
            expectedWins: Double,
            actualFrags: Double,
            expectedFrags: Double
        ): Double {
            // From https://wows-numbers.com/personal/rating by Wiochi
            val rDmg = actualDmg / expectedDmg
            val rWins = actualWins / expectedWins
            val rFrags = actualFrags / expectedFrags

            val nDmg = Math.max(0, (rDmg - 0.4) / (1 - 0.4))
            val nFrags = Math.max(0, (rFrags - 0.1) / (1 - 0.1))
            val nWins = Math.max(0, (rWins - 0.7) / (1 - 0.7))

            return roundTo((700 * nDmg + 300 * nFrags + 150 * nWins).coerceAtMost(9999.0))
        }

        fun getOverallRating(ships: List<Ship>): Double {
            if (ships.isEmpty()) return -1

            var actualDmg = 0.0
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
                    val overall = getOverall(ship.shipId)
                    if (overall == null) continue

                    val battles = pvp.battles
                    val damageDealt = pvp.damageDealt
                    val frags = pvp.frags
                    val wins = pvp.wins

                    if (battles === 0) continue

                    val averageDamageDealt = overall.averageDamageDealt
                    val winRate = overall.winRate
                    val averageFrags = overall.averageFrags

                    ship.avgDmg = damageDealt.toDouble() / battles
                    ship.avgWinrate = (wins.toDouble() / battles) * 100
                    ship.avgFrags = frags.toDouble() / battles

                    // Accumulate data
                    actualDmg += ship.avgDmg
                    actualWins += ship.avgWinrate
                    actualFrags += ship.avgFrags
                    expectedDmg += averageDamageDealt
                    expectedWins += winRate
                    expectedFrags += averageFrags

                    // Calculate rating and ap
                    val rating = calRating(
                        ship.avgDmg,
                        averageDamageDealt,
                        ship.avgWinrate.toDouble(),
                        winRate,
                        ship.avgFrags,
                        averageFrags
                    )
                    ship.rating = rating.toLong()
                    ship.ap = getAP(rating, battles)
                }
            }

            return calRating(actualDmg, expectedDmg, actualWins, expectedWins, actualFrags, expectedFrags)
        }

        fun getAP(rating: Double, battle: Int): Int {
            if (rating == -1 || battle <= 0) return 0

            val ap = log10(battle.coerceAtLeast(10)).times(rating)
            return roundTo(ap).toInt()
        }
    }
}