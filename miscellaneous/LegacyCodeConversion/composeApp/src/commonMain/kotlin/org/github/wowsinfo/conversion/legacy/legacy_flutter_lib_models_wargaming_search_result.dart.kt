interface SearchResult : Encodable {
    val displayName: String
    val id: String
}

data class PlayerResult(
    override val displayName: String,
    override val id: String,
    val nickname: String?,
    val accountId: Int?,
    val createdAt: TimeStampDate?
) : SearchResult, Encodable

data class ClanResult(
    override val displayName: String,
    override val id: String,
    val membersCount: Int?,
    val createdAt: TimeStampDate?,
    val clanId: Int?,
    val tag: String?,
    val name: String?
) : SearchResult, Encodable