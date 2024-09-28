
@Serializable
data class Alias(
    val alias: String
)

    companion object {
        fun fromJson(json: Map<String, Any>): Alias {
            return Alias(
                alias = json["alias"] as String
            )
        }
    }
}