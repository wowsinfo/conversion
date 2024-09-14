import androidx.compose.runtime.Immutable

@Immutable
data class PlayerInformation(
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
)

@Immutable
data class PlayerStatistics(
    val battles: Int? = null,
    val distance: Int? = null,
    val pvp: PvP? = null,
    val pve: PvE? = null,
    val rankSolo: Rank? = null,
    val pvpDiv2: PvPDiv2? = null,
    val pvpDiv3: PvPDiv3? = null
)