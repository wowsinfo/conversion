
interface Encodable

interface SearchResult : Encodable {
    val displayName: String
    val id: String
}

@Serializable
data class PlayerResult(
    val nickname: String? = null,
    val accountId: String? = null,
    val createdAt: String? = null
) : SearchResult {
    override val displayName: String
        get() = nickname ?: "Unknown Player"
    
    override val id: String
        get() = accountId ?: "Unknown ID"
}

    val nickname: String?,
    val accountId: Int?,
    val createdAt: TimeStampDate?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): PlayerResult {
            return PlayerResult(
                nickname = json["nickname"] as? String,
                accountId = json["account_id"] as? Int,
                createdAt = json["created_at"]?.let { TimeStampDate(it) }
            )
        }
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "nickname" to nickname,
            "account_id" to accountId,
            "created_at" to createdAt?.timeStamp
        )
    }

    override fun toString(): String {
        return "PlayerResult(nickname=$nickname, accountId=$accountId, createdAt=$createdAt)"
    }
}


@Serializable
data class User(
    val nickname: String? = null,
    val accountId: Int? = null
) {
    val displayName: String
        get() = nickname ?: "-"

    val id: String
        get() = accountId?.toString() ?: "-"
}

@Serializable
data class ClanResult(
    val membersCount: Int? = null,
    val createdAt: String? = null,
    val clanId: Int? = null,
    val tag: String? = null,
    val name: String? = null
) : SearchResult

    val membersCount: Int?,
    val createdAt: TimeStampDate?,
    val clanId: Int?,
    val tag: String?,
    val name: String?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): ClanResult {
            return ClanResult(
                membersCount = json["members_count"] as? Int,
                createdAt = json["created_at"]?.let { TimeStampDate(it as Long) },
                clanId = json["clan_id"] as? Int,
                tag = json["tag"] as? String,
                name = json["name"] as? String
            )
        }
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "members_count" to membersCount,
            "created_at" to createdAt?.timeStamp,
            "clan_id" to clanId,
            "tag" to tag,
            "name" to name
        )
    }

    override fun toString(): String {
        return "ClanResult(membersCount=$membersCount, createdAt=$createdAt, clanId=$clanId, tag=$tag, name=$name)"
    }
}

data class TimeStampDate(val timeStamp: Long)

    private val tag: String? = null,
    private val clanId: Int? = null
) {
    val displayName: String
        get() = tag ?: "-"

    val id: String
        get() = clanId?.toString() ?: "-"
}