
@Serializable
data class ClanInformation(
    val membersCount: Int? = null,
    val name: String? = null,
    val creatorName: String? = null,
    val createdAt: Timestamp? = null,
    val tag: String? = null,
    val updatedAt: Timestamp? = null,
    val leaderName: String? = null,
    val membersIds: List<String>? = null,
    val creatorId: String? = null,
    val clanId: String? = null,
    val members: List<Member>? = null,
    val oldName: String? = null,
    val isClanDisbanded: Boolean? = null,
    val renamedAt: Timestamp? = null,
    val oldTag: String? = null,
    val leaderId: String? = null,
    val description: String? = null
)

@Serializable
data class Member(
    val id: String,
    val name: String
)

    val membersCount: Int? = null,
    val name: String? = null,
    val creatorName: String? = null,
    val createdAt: TimeStampDate? = null,
    val tag: String? = null,
    val updatedAt: TimeStampDate? = null,
    val leaderName: String? = null,
    val membersIds: List<Int>? = null,
    val creatorId: Int? = null,
    val clanId: Int? = null,
    val members: Map<String, ClanMember>? = null,
    val oldName: String? = null,
    val isClanDisbanded: Boolean? = null,
    val renamedAt: TimeStampDate? = null,
    val oldTag: String? = null,
    val leaderId: Int? = null,
    val description: String? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): ClanInformation {
            if (json.isEmpty()) return ClanInformation()

            val clan = json.values.first() as? Map<String, Any?> ?: return ClanInformation()

            return ClanInformation(
                membersCount = clan["members_count"] as? Int,
                name = clan["name"] as? String,
                creatorName = clan["creator_name"] as? String,
                createdAt = clan["created_at"]?.let { TimeStampDate(it) },
                tag = clan["tag"] as? String,
                updatedAt = clan["updated_at"]?.let { TimeStampDate(it) },
                leaderName = clan["leader_name"] as? String,
                membersIds = clan["members_ids"] as? List<Int>,
                creatorId = clan["creator_id"] as? Int,
                clanId = clan["clan_id"] as? Int,
                members = clan["members"]?.let {
                    (it as Map<String, Any?>).mapValues { entry -> ClanMember.fromJson(entry.value as Map<String, Any?>) }
                },
                oldName = clan["old_name"] as? String,
                isClanDisbanded = clan["is_clan_disbanded"] as? Boolean,
                renamedAt = clan["renamed_at"]?.let { TimeStampDate(it) },
                oldTag = clan["old_tag"] as? String,
                leaderId = clan["leader_id"] as? Int,
                description = clan["description"] as? String
            )
        }
    }
}

    val membersCount: Int,
    val name: String,
    val creatorName: String,
    val createdAt: String,
    val tag: String,
    val updatedAt: String,
    val leaderName: String,
    val membersIds: List<String>,
    val creatorId: String,
    val clanId: String,
    val members: List<Member>,
    val oldName: String,
    val isClanDisbanded: Boolean,
    val renamedAt: String,
    val oldTag: String,
    val leaderId: String,
    val description: String
) {
    override fun toString(): String {
        return "ClanInformation(membersCount=$membersCount, name='$name', creatorName='$creatorName', createdAt='$createdAt', tag='$tag', updatedAt='$updatedAt', leaderName='$leaderName', membersIds=$membersIds, creatorId='$creatorId', clanId='$clanId', members=$members, oldName='$oldName', isClanDisbanded=$isClanDisbanded, renamedAt='$renamedAt', oldTag='$oldTag', leaderId='$leaderId', description='$description')"
    }
}

data class Member(
    val id: String,
    val name: String
)


data class ClanMember(
    val role: String? = null,
    val joinedAt: Instant? = null,
    val accountId: String? = null,
    val accountName: String? = null
)

    val role: Role? = null,
    val joinedAt: TimeStampDate? = null,
    val accountId: Int? = null,
    val accountName: String? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): ClanMember {
            return ClanMember(
                role = json["role"]?.let { Role.valueOf(it as String) },
                joinedAt = json["joined_at"]?.let { TimeStampDate(it) },
                accountId = json["account_id"] as? Int,
                accountName = json["account_name"] as? String
            )
        }
    }
}

enum class Role {
    COMMANDER,
    COMMISSIONED_OFFICER,
    EXECUTIVE_OFFICER,
    OFFICER,
    PRIVATE,
    RECRUITMENT_OFFICER
}

data class TimeStampDate(val timestamp: Any)