@Immutable
data class Projectile(
    val type: String,
    val nation: String,
    val name: String,
    val ammoType: String?,
    val weight: Double?,
    val speed: Double?,
    val damage: Double?,
    val ricochetAngle: Double?,
    val ricochetAlways: Double?,
    val diameter: Double?,
    val ap: ArmorPiecingInfo?,
    val overmatch: Int?,
    val fuseTime: Double?,
    val penHe: Double?,
    val burnChance: Double?,
    val visibility: Double?,
    val range: Double?,
    val floodChance: Double?,
    val alphaDamage: Double?,
    val deepWater: Boolean?,
    val ignoreClasses: List<String>?
)

@Immutable
data class ArmorPiecingInfo(
    val diameter: Double,
    val weight: Double,
    val drag: Double,
    val velocity: Double,
    val krupp: Double
)