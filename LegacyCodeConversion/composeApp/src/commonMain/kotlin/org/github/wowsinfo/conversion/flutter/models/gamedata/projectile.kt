
@Serializable
data class Projectile(
    val type: String,
    val nation: String,
    val name: String,
    val weight: Double? = null,
    val ammoType: String? = null,
    val speed: Double? = null,
    val damage: Double? = null,
    val ricochetAngle: Double? = null,
    val ricochetAlways: Boolean? = null,
    val diameter: Double? = null,
    val ap: Double? = null,
    val overmatch: Double? = null,
    val fuseTime: Double? = null,
    val penHe: Double? = null,
    val burnChance: Double? = null,
    val visibility: Double? = null,
    val range: Double? = null,
    val floodChance: Double? = null,
    val alphaDamage: Double? = null,
    val deepWater: Boolean? = null,
    val penSAP: Double? = null,
    val ignoreClasses: List<String>? = null
)


@Serializable
data class Projectile(
    val type: String,
    val nation: String,
    val name: String,
    val ammoType: String? = null,
    val weight: Double? = null,
    val speed: Double? = null,
    val damage: Double? = null,
    val ricochetAngle: Double? = null,
    val ricochetAlways: Double? = null,
    val diameter: Double? = null,
    val ap: ArmorPiecingInfo? = null,
    val overmatch: Int? = null,
    val fuseTime: Double? = null,
    val penHe: Double? = null,
    val penSap: Double? = null,
    val burnChance: Double? = null,
    val visibility: Double? = null,
    val range: Double? = null,
    val floodChance: Double? = null,
    val alphaDamage: Double? = null,
    val deepWater: Boolean? = null,
    val ignoreClasses: List<String>? = null
)

@Serializable
data class ArmorPiecingInfo(
    val diameter: Double,
    val weight: Double,
    val drag: Double,
    val velocity: Double,
    val krupp: Double
)

fun fromJson(jsonString: String): Projectile {
    return Json.decodeFromString(jsonString)
}

    val diameter: Double,
    val weight: Number,
    val drag: Double,
    val velocity: Number,
    val krupp: Number
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ArmorPiecingInfo {
            return ArmorPiecingInfo(
                diameter = (json["diameter"] as Number).toDouble(),
                weight = json["weight"] as Number,
                drag = (json["drag"] as Number).toDouble(),
                velocity = json["velocity"] as Number,
                krupp = json["krupp"] as Number
            )
        }
    }
}