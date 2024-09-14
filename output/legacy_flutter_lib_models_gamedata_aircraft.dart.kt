@Immutable
data class Aircraft(
    val type: String,
    val nation: String,
    val name: String,
    val health: Double?,
    val totalPlanes: Int?,
    val visibility: Double?,
    val speed: Double?,
    val aircraft: AircraftInfo?
)

@Immutable
data class AircraftInfo(
    val restoreTime: Double,
    val maxAircraft: Int,
    val attacker: Int,
    val attackCount: Int,
    val cooldown: Double,
    val minSpeed: Double,
    val maxSpeed: Double,
    val boostTime: Double,
    val boostReload: Double?,
    val bombName: String,
    val consumables: List<List<Consumable>>?
)

@Immutable
data class Consumable(
    val id: Int,
    val name: String,
    val icon: String,
    val maxStack: Int,
    val healthCost: Double,
    val speedCost: Double,
    val visibilityCost: Double,
    val damageBonus: Double,
    val accuracyBonus: Double,
    val rangeBonus: Double
)