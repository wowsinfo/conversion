@Serializable
data class ShipAdditional(
    val damage: Int,
    val frags: Double,
    val winrate: Double,
    val battles: Int?
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ShipAdditional {
            return ShipAdditional(
                damage = json["damage"] as Int,
                frags = json["frags"] as Double,
                winrate = json["winrate"] as Double,
                battles = json["battles"] as? Int
            )
        }
    }
}