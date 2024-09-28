
@Serializable
data class GameInfo(
    val regions: List<String>,
    val types: List<String>
)

    val regions: List<String>,
    val types: List<String>
) {
    companion object {
        val preservedTiers = listOf("✱", "✸", "✹", "✺")

        val tiers = listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "★")

        fun fromJson(json: Map<String, Any>): GameInfo {
            val regions = (json["regions"] as List<String>)
            val types = (json["types"] as List<String>)
            return GameInfo(regions, types)
        }
    }
}