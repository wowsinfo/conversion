@Serializable
data class GameInfo(
    val regions: List<String>,
    val types: List<String>
) {
    companion object {
        @PublishedApi
        internal val preversedTiers = listOf(
            "✱",
            "✸",
            "✹",
            "✺"
        )

        val tiers = listOf(
            "I",
            "II",
            "III",
            "IV",
            "V",
            "VI",
            "VII",
            "VIII",
            "IX",
            "X",
            "★"
        )
    }

    companion object {
        fun fromJson(json: Map<String, Any>): GameInfo = GameInfo(
            regions = json["regions"] as List<String>,
            types = json["types"] as List<String>
        )
    }
}