import org.github.wowsinfo.conversion.legacy.toFixedString

kotlinx.serialization.Serializable

@Serializable
data class ModeStatistics(
    val maxFragsBattle: Int? = null,
    val draws: Int? = null,
    val maxXp: Int? = null,
    val wins: Int? = null,
    val planesKilled: Int? = null,
    val losses: Int? = null,
    val battles: Int? = null,
    val maxDamageDealt: Int? = null,
    val damageDealt: Int? = null,
    val maxPlanesKilled: Int? = null,
    val torpedoes: Torpedoe? = null,
    val aircraft: AttackAircraft? = null,
    val ramming: RamAttack? = null,
    val mainBattery: MainBattery? = null,
    val secondaries: SecondaryBattery? = null,
    val survivedWins: Int? = null,
    val frags: Int? = null,
    val xp: Int? = null,
    val survivedBattles: Int? = null,
    val damageToBuildings: Int? = null,
    val maxShipsSpottedShipId: Int? = null,
    val maxDamageScouting: Int? = null,
    val artAgro: Int? = null,
    val maxXpShipId: Int? = null,
    val shipsSpotted: Int? = null,
    val maxFragsShipId: Int? = null,
    val droppedCapturePoints: Int? = null,
    val maxDamageDealtToBuildings: Int? = null,
    val torpedoAgro: Int? = null,
    val controlCapturedPoints: Int? = null,
    val battlesSince510: Int? = null,
    val maxTotalAgroShipId: Int? = null,
    val maxShipsSpotted: Int? = null,
    val maxSuppressionsShipId: Int? = null,
    val damageScouting: Int? = null,
    val maxTotalAgro: Int? = null,
    val capturePoints: Int? = null,
    val suppressionsCount: Int? = null,
    val maxSuppressionsCount: Int? = null,
    val maxPlanesKilledShipId: Int? = null,
    val teamCapturePoints: Int? = null,
    val controlDroppedPoints: Int? = null,
    val maxDamageDealtToBuildingsShipId: Int? = null,
    val maxDamageDealtShipId: Int? = null,
    val maxScoutingDamageShipId: Int? = null,
    val teamDroppedCapturePoint: Int? = null,
    val battlesSince512: Int? = null
) {

    fun hasBattle(): Boolean {
        return battles != null && battles != 0
    }

    fun hasHit(): Boolean {
        return (mainBattery?.shots ?: 0) > 0
    }

    fun sunkBattle(): Int {
        if (survivedBattles == null) return 0
        if (battles == null) return 0
        return battles!! - survivedBattles!!
    }

    fun killDeath(): Double {
        if (frags == null) return java.lang.Double.NaN
        return frags!!.toDouble() / sunkBattle().toDouble()
    }

    val totalPotentialDamage: Int
        get() = (artAgro ?: 0) + (torpedoAgro ?: 0)

    fun winrate(): Double {
        return rate(wins, battles)
    }

    fun survivedWinrate(): Double {
        return rate(survivedWins, battles)
    }

    fun survivedRate(): Double {
        return rate(survivedBattles, battles)
    }

    fun avgExp(): Double {
        return average(xp, battles)
    }

    fun avgDamage(): Double {
        return average(damageDealt, battles)
    }

    fun avgFrag(): Double {
        return average(frags, battles)
    }

    fun avgPlaneDestroyed(): Double {
        return average(planesKilled, battles)
    }

    fun avgSpottingDamage(): Double {
        return average(damageScouting, battles)
    }

    fun avgSpottedShips(): Double {
        return average(shipsSpotted, battles)
    }

    fun avgPotentialDamage(): Double {
        return average(totalPotentialDamage, battles)
    }

    val battleString: String
        get() = battles.toString()

    val winrateString: String
        get() = "${winrate().toFixedString(1)}%"

    val avgDamageString: String
        get() = avgDamage().toFixedString(0)

    val avgExpString: String
        get() = avgExp().toFixedString(0)

    val killDeathString: String
        get() = killDeath().toFixedString(2)

    val mainHitRatio: Double
        get() = mainBattery?.hitRatio ?: java.lang.Double.NaN

    val mainHitRatioString: String
        get() = "${mainHitRatio.toFixedString(2)}%"

    val mostDamage: String
        get() = maxDamageDealt.toString()

    val mostExp: String
        get() = maxXp.toString()

    val mostFrag: String
        get() = maxFragsBattle.toString()

    val mostPlane: String
        get() = maxPlanesKilled.toString()

    val mostDamageToBuilding: String
        get() = maxDamageDealtToBuildings.toString()

    val mostSpottingDamage: String
        get() = maxDamageScouting.toString()

    val mostSpotted: String
        get() = maxShipsSpotted.toString()

    val mostSupression: String
        get() = maxSuppressionsCount.toString()

    val mostPotential: String
        get() = maxTotalAgro.toString()
}