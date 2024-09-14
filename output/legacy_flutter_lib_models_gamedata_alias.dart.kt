@Immutable
data class Alias(
    val alias: String,
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Alias = Alias(alias = json["alias"] as String)
    }
}