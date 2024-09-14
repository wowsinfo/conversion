@Immutable
data class ClanInformation(
    val membersCount: Int?,
    val name: String?,
    val creatorName: String?,
    val createdAt: TimeStampDate?,
    val tag: String?,
    val updatedAt: TimeStampDate?,
    val leaderName: String?,
    val membersIds: List<Int>?,
    val creatorId: Int?,
    val clanId: Int?,
    val members: Map<String, ClanMember>?,
    val oldName: String?,
    val isClanDisbanded: Boolean?,
    val renamedAt: TimeStampDate?,
    val oldTag: String?,
    val leaderId: Int?,
    val description: String?
)

@Immutable
data class ClanMember(
    val role: String?,
    val joinedAt: TimeStampDate?,
    val accountId: Int?,
    val accountName: String?
)