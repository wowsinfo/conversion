
@Serializable
data class ShipAdditional(
    val damage: Int,
    val frags: Int,
    val winrate: Double,
    val battles: Int? = null
)

    val damage: Int,
    val frags: Number,
    val winrate: Number,
    val battles: Int?
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ShipAdditional {
            return ShipAdditional(
                damage = json["damage"] as Int,
                frags = json["frags"] as Number,
                winrate = json["winrate"] as Number,
                battles = json["battles"] as? Int
            )
        }
    }
}