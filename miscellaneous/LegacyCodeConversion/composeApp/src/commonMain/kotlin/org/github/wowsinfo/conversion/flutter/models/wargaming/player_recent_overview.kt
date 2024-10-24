
@Serializable
data class PlayerRecentOverview(
    val pvp: Pvp? = null
)

@Serializable
data class Pvp(
    // Define properties for Pvp as needed
)

    val pvp: Map<String, PlayerRecentPvP>? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any>): PlayerRecentOverview {
            if (json.isEmpty()) return PlayerRecentOverview()

            val player = json.values.firstOrNull()
            if (player is Map<*, *>) {
                val pvpData = player["pvp"] as? Map<*, *>
                val pvp = pvpData?.mapKeys { it.key as String }
                    ?.mapValues { PlayerRecentPvP.fromJson(it.value as Map<String, Any>) }
                return PlayerRecentOverview(pvp = pvp)
            }

            return PlayerRecentOverview()
        }
    }
}

data class PlayerRecentPvP(
    // Define properties here
) {
    companion object {
        fun fromJson(json: Map<String, Any>): PlayerRecentPvP {
            // Implement parsing logic here
            return PlayerRecentPvP()
        }
    }
}


@Serializable
data class PlayerRecentPvP(
    val capturePoints: Int? = null,
    val accountId: String? = null,
    val maxXp: Int? = null,
    val wins: Int? = null,
    val planesKilled: Int? = null,
    val battles: Int? = null,
    val damageDealt: Int? = null,
    val battleType: String? = null,
    val date: String? = null,
    val xp: Int? = null,
    val frags: Int? = null,
    val survivedBattles: Int? = null,
    val droppedCapturePoints: Int? = null
) {
    fun toJson(): JsonObject {
        return JsonObject(mapOf(
            "capturePoints" to (capturePoints?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "accountId" to (accountId?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "maxXp" to (maxXp?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "wins" to (wins?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "planesKilled" to (planesKilled?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "battles" to (battles?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "damageDealt" to (damageDealt?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "battleType" to (battleType?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "date" to (date?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "xp" to (xp?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "frags" to (frags?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "survivedBattles" to (survivedBattles?.let { JsonPrimitive(it) } ?: JsonPrimitive(null)),
            "droppedCapturePoints" to (droppedCapturePoints?.let { JsonPrimitive(it) } ?: JsonPrimitive(null))
        ))
    }
}

    val capturePoints: Int?,
    val accountId: Int?,
    val maxXp: Int?,
    val wins: Int?,
    val planesKilled: Int?,
    val battles: Int?,
    val damageDealt: Int?,
    val battleType: String?,
    val date: String?,
    val xp: Int?,
    val frags: Int?,
    val survivedBattles: Int?,
    val droppedCapturePoints: Int?
) {
    val winrate: Double
        get() = rate(wins, battles)

    val avgDamage: Double
        get() = average(damageDealt, battles)

    companion object {
        fun fromJson(json: Map<String, Any?>): PlayerRecentPvP {
            return PlayerRecentPvP(
                capturePoints = json["capture_points"] as? Int,
                accountId = json["account_id"] as? Int,
                maxXp = json["max_xp"] as? Int,
                wins = json["wins"] as? Int,
                planesKilled = json["planes_killed"] as? Int,
                battles = json["battles"] as? Int,
                damageDealt = json["damage_dealt"] as? Int,
                battleType = json["battle_type"] as? String,
                date = json["date"] as? String,
                xp = json["xp"] as? Int,
                frags = json["frags"] as? Int,
                survivedBattles = json["survived_battles"] as? Int,
                droppedCapturePoints = json["dropped_capture_points"] as? Int
            )
        }
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "capture_points" to capturePoints,
            "account_id" to accountId,
            "max_xp" to maxXp,
            "wins" to wins,
            "planes_killed" to planesKilled,
            "battles" to battles,
            "damage_dealt" to damageDealt,
            "battle_type" to battleType,
            "date" to date,
            "xp" to xp,
            "frags" to frags,
            "survived_battles" to survivedBattles,
            "dropped_capture_points" to droppedCapturePoints
        )
    }
}

fun rate(wins: Int?, battles: Int?): Double {
    return if (battles != null && battles > 0) {
        wins?.toDouble()?.div(battles) ?: 0.0
    } else {
        0.0
    }
}

fun average(damageDealt: Int?, battles: Int?): Double {
    return if (battles != null && battles > 0) {
        damageDealt?.toDouble()?.div(battles) ?: 0.0
    } else {
        0.0
    }
}