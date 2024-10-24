
typealias PvP = ModeStatistics
typealias PvE = ModeStatistics
typealias ClanClan = ModeStatistics
typealias PvPDiv2 = ModeStatistics
typealias PvPDiv3 = ModeStatistics
typealias Rank = ModeStatistics

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
    val torpedoes: Int? = null,
    val aircraft: Int? = null,
    val ramming: Int? = null,
    val mainBattery: Int? = null,
    val secondaries: Int? = null,
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
)

    val maxFragsBattle: Int?,
    val draws: Int?,
    val maxXp: Int?,
    val wins: Int?,
    val planesKilled: Int?,
    val losses: Int?,
    val battles: Int?,
    val maxDamageDealt: Int?,
    val damageDealt: Int?,
    val maxPlanesKilled: Int?,
    val torpedoes: Torpedoe?,
    val aircraft: AttackAircraft?,
    val ramming: RamAttack?,
    val mainBattery: MainBattery?,
    val secondaries: SecondaryBattery?,
    val survivedWins: Int?,
    val frags: Int?,
    val xp: Int?,
    val survivedBattles: Int?,
    val damageToBuildings: Int?,
    val maxShipsSpottedShipId: Int?,
    val maxDamageScouting: Int?,
    val artAgro: Int?,
    val maxXpShipId: Int?,
    val shipsSpotted: Int?,
    val maxFragsShipId: Int?,
    val droppedCapturePoints: Int?,
    val maxDamageDealtToBuildings: Int?,
    val torpedoAgro: Int?,
    val controlCapturedPoints: Int?,
    val battlesSince510: Int?,
    val maxTotalAgroShipId: Int?,
    val maxShipsSpotted: Int?,
    val maxSuppressionsShipId: Int?,
    val damageScouting: Int?,
    val maxTotalAgro: Int?,
    val capturePoints: Int?,
    val suppressionsCount: Int?,
    val maxSuppressionsCount: Int?,
    val maxPlanesKilledShipId: Int?,
    val teamCapturePoints: Int?,
    val controlDroppedPoints: Int?,
    val maxDamageDealtToBuildingsShipId: Int?,
    val maxDamageDealtShipId: Int?,
    val maxScoutingDamageShipId: Int?,
    val teamDroppedCapturePoint: Int?,
    val battlesSince512: Int?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): ModeStatistics {
            return ModeStatistics(
                maxFragsBattle = json["max_frags_battle"] as? Int,
                draws = json["draws"] as? Int,
                maxXp = json["max_xp"] as? Int,
                wins = json["wins"] as? Int,
                planesKilled = json["planes_killed"] as? Int,
                losses = json["losses"] as? Int,
                battles = json["battles"] as? Int,
                maxDamageDealt = json["max_damage_dealt"] as? Int,
                damageDealt = json["damage_dealt"] as? Int,
                maxPlanesKilled = json["max_planes_killed"] as? Int,
                torpedoes = (json["torpedoes"] as? Map<String, Any?>)?.let { Torpedoe.fromJson(it) },
                aircraft = (json["aircraft"] as? Map<String, Any?>)?.let { AttackAircraft.fromJson(it) },
                ramming = (json["ramming"] as? Map<String, Any?>)?.let { RamAttack.fromJson(it) },
                mainBattery = (json["main_battery"] as? Map<String, Any?>)?.let { MainBattery.fromJson(it) },
                secondaries = (json["second_battery"] as? Map<String, Any?>)?.let { SecondaryBattery.fromJson(it) },
                survivedWins = json["survived_wins"] as? Int,
                frags = json["frags"] as? Int,
                xp = json["xp"] as? Int,
                survivedBattles = json["survived_battles"] as? Int,
                damageToBuildings = json["damage_to_buildings"] as? Int,
                maxShipsSpottedShipId = json["max_ships_spotted_ship_id"] as? Int,
                maxDamageScouting = json["max_damage_scouting"] as? Int,
                artAgro = json["art_agro"] as? Int,
                maxXpShipId = json["max_xp_ship_id"] as? Int,
                shipsSpotted = json["ships_spotted"] as? Int,
                maxFragsShipId = json["max_frags_ship_id"] as? Int,
                droppedCapturePoints = json["dropped_capture_points"] as? Int,
                maxDamageDealtToBuildings = json["max_damage_dealt_to_buildings"] as? Int,
                torpedoAgro = json["torpedo_agro"] as? Int,
                controlCapturedPoints = json["control_captured_points"] as? Int,
                battlesSince510 = json["battles_since_510"] as? Int,
                maxTotalAgroShipId = json["max_total_agro_ship_id"] as? Int,
                maxShipsSpotted = json["max_ships_spotted"] as? Int,
                maxSuppressionsShipId = json["max_suppressions_ship_id"] as? Int,
                damageScouting = json["damage_scouting"] as? Int,
                maxTotalAgro = json["max_total_agro"] as? Int,
                capturePoints = json["capture_points"] as? Int,
                suppressionsCount = json["suppressions_count"] as? Int,
                maxSuppressionsCount = json["max_suppressions_count"] as? Int,
                maxPlanesKilledShipId = json["max_planes_killed_ship_id"] as? Int,
                teamCapturePoints = json["team_capture_points"] as? Int,
                controlDroppedPoints = json["control_dropped_points"] as? Int,
                maxDamageDealtToBuildingsShipId = json["max_damage_dealt_to_buildings_ship_id"] as? Int,
                maxDamageDealtShipId = json["max_damage_dealt_ship_id"] as? Int,
                maxScoutingDamageShipId = json["max_scouting_damage_ship_id"] as? Int,
                teamDroppedCapturePoint = json["team_dropped_capture_points"] as? Int,
                battlesSince512 = json["battles_since_512"] as? Int
            )
        }
    }

    val hasBattle: Boolean
        get() = battles != null && battles != 0

    val hasHit: Boolean
        get() = (mainBattery?.shots ?: 0) > 0

    val sunkBattle: Int
        get() {
            if (survivedBattles == null) return 0
            if (battles == null) return 0
            return battles!! - survivedBattles!!
        }
}

    get() {
        if (frags == null) return Double.NaN
        return frags / sunkBattle
    }

    private val artAgro: Double? = null,
    private val torpedoAgro: Double? = null,
    private val wins: Int = 0,
    private val battles: Int = 0,
    private val survivedWins: Int = 0,
    private val survivedBattles: Int = 0,
    private val xp: Double = 0.0,
    private val damageDealt: Double = 0.0,
    private val frags: Int = 0,
    private val planesKilled: Int = 0,
    private val damageScouting: Double = 0.0,
    private val shipsSpotted: Int = 0,
    private val totalPotentialDamage: Double = 0.0,
    private val maxDamageDealt: Double = 0.0,
    private val maxXp: Double = 0.0,
    private val maxFragsBattle: Int = 0,
    private val maxPlanesKilled: Int = 0,
    private val maxDamageDealtToBuildings: Double = 0.0,
    private val maxDamageScouting: Double = 0.0,
    private val maxShipsSpotted: Int = 0,
    private val maxSuppressionsCount: Int = 0,
    private val maxTotalAgro: Double = 0.0,
    private val mainBattery: MainBattery? = null
) {
    val totalPotentialDamage: Double
        get() = (artAgro ?: 0.0) + (torpedoAgro ?: 0.0)

    private fun rate(numerator: Int, denominator: Int): Double {
        return if (denominator > 0) (numerator.toDouble() / denominator) * 100 else 0.0
    }

    val winrate: Double
        get() = rate(wins, battles)

    val survivedWinrate: Double
        get() = rate(survivedWins, battles)

    val survivedRate: Double
        get() = rate(survivedBattles, battles)

    private fun average(value: Double, count: Int): Double {
        return if (count > 0) value / count else 0.0
    }

    val avgExp: Double
        get() = average(xp, battles)

    val avgDamage: Double
        get() = average(damageDealt, battles)

    val avgFrag: Double
        get() = average(frags.toDouble(), battles)

    val avgPlaneDestroyed: Double
        get() = average(planesKilled.toDouble(), battles)

    val avgSpottingDamage: Double
        get() = average(damageScouting, battles)

    val avgSpottedShips: Double
        get() = average(shipsSpotted.toDouble(), battles)

    val avgPotentialDamage: Double
        get() = average(totalPotentialDamage, battles)

    val battleString: String
        get() = battles.toString()

    val winrateString: String
        get() = "${winrate.toFixedString(1)}%"

    val avgDamageString: String
        get() = avgDamage.toFixedString(0)

    val avgExpString: String
        get() = avgExp.toFixedString(0)

    val killDeathString: String
        get() = killDeath.toFixedString(2)

    val mainHitRatio: Double
        get() = mainBattery?.hitRatio ?: Double.NaN

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

fun Double.toFixedString(digits: Int): String {
    return String.format("%.${digits}f", this)
}

data class MainBattery(val hitRatio: Double)