
@Serializable
data class PlayerInformation(
    val lastBattleTime: Instant? = null,
    val accountId: String? = null,
    val levelingTier: Int? = null,
    val createdAt: Instant? = null,
    val levelingPoints: Int? = null,
    val updatedAt: Instant? = null,
    val hiddenProfile: Boolean? = null,
    val logoutAt: Instant? = null,
    val statistics: Statistics? = null,
    val nickname: String? = null,
    val statsUpdatedAt: Instant? = null
)

@Serializable
data class Statistics(
    // Define the properties of Statistics here
)

    val lastBattleTime: Int? = null,
    val accountId: Int? = null,
    val levelingTier: Int? = null,
    val createdAt: TimeStampDate? = null,
    val levelingPoints: Int? = null,
    val updatedAt: Int? = null,
    val hiddenProfile: Boolean? = null,
    val logoutAt: Int? = null,
    val statistics: PlayerStatistics? = null,
    val nickname: String? = null,
    val statsUpdatedAt: Int? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): PlayerInformation {
            if (json.isEmpty()) return PlayerInformation()

            val player = json.values.first() as? Map<String, Any?>
            return player?.let {
                PlayerInformation(
                    lastBattleTime = it["last_battle_time"] as? Int,
                    accountId = it["account_id"] as? Int,
                    levelingTier = it["leveling_tier"] as? Int,
                    createdAt = it["created_at"]?.let { date -> TimeStampDate(date) },
                    levelingPoints = it["leveling_points"] as? Int,
                    updatedAt = it["updated_at"] as? Int,
                    hiddenProfile = it["hidden_profile"] as? Boolean,
                    logoutAt = it["logout_at"] as? Int,
                    statistics = it["statistics"]?.let { stats -> PlayerStatistics.fromJson(stats as Map<String, Any?>) },
                    nickname = it["nickname"] as? String,
                    statsUpdatedAt = it["stats_updated_at"] as? Int
                )
            } ?: throw IllegalArgumentException("Not a hidden account but data is missing")
        }
    }
}


@Serializable
data class PlayerStatistics(
    val battles: Int? = null,
    val distance: Double? = null,
    val pvp: Int? = null,
    val pve: Int? = null,
    val rankSolo: Int? = null,
    val pvpDiv2: Int? = null,
    val pvpDiv3: Int? = null
)

    val battles: Int? = null,
    val distance: Int? = null,
    val pvp: PvP? = null,
    val pve: PvE? = null,
    val rankSolo: Rank? = null,
    val pvpDiv2: PvPDiv2? = null,
    val pvpDiv3: PvPDiv3? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): PlayerStatistics {
            return PlayerStatistics(
                battles = json["battles"] as? Int,
                distance = json["distance"] as? Int,
                pvp = json["pvp"]?.let { PvP.fromJson(it as Map<String, Any?>) },
                pve = json["pve"]?.let { PvE.fromJson(it as Map<String, Any?>) },
                rankSolo = json["rank_solo"]?.let { Rank.fromJson(it as Map<String, Any?>) },
                pvpDiv2 = json["pvp_div2"]?.let { PvPDiv2.fromJson(it as Map<String, Any?>) },
                pvpDiv3 = json["pvp_div3"]?.let { PvPDiv3.fromJson(it as Map<String, Any?>) }
            )
        }
    }
}