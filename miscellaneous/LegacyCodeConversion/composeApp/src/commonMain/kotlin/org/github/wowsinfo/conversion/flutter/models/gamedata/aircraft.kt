
@Serializable
data class Aircraft(
    val type: String,
    val nation: String,
    val name: String,
    val health: Int,
    val totalPlanes: Int,
    val visibility: Int,
    val speed: Int,
    val aircraft: List<Consumable>
)


@Serializable
data class Aircraft(
    val type: String,
    val nation: String,
    val name: String,
    val health: Double? = null,
    val totalPlanes: Int? = null,
    val visibility: Double? = null,
    val speed: Double? = null,
    val aircraft: AircraftInfo? = null
) {
    companion object {
        fun fromJson(json: String): Aircraft {
            return Json.decodeFromString(json)
        }
    }
}

@Serializable
data class AircraftInfo(
    val restoreTime: Int,
    val maxAircraft: Int,
    val attacker: Boolean,
    val attackCount: Int,
    val cooldown: Int,
    val minSpeed: Double,
    val maxSpeed: Double,
    val boostTime: Int,
    val boostReload: Int,
    val bombName: String,
    val consumables: List<String>
)

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
) {
    companion object {
        fun fromJson(json: Map<String, Any>): AircraftInfo {
            return AircraftInfo(
                restoreTime = json["restoreTime"] as Double,
                maxAircraft = json["maxAircraft"] as Int,
                attacker = json["attacker"] as Int,
                attackCount = json["attackCount"] as Int,
                cooldown = (json["cooldown"] as Number).toDouble(),
                minSpeed = (json["minSpeed"] as Number).toDouble(),
                maxSpeed = (json["maxSpeed"] as Number).toDouble(),
                boostTime = json["boostTime"] as Double,
                boostReload = json["boostReload"] as? Double,
                bombName = json["bombName"] as String,
                consumables = json["consumables"]?.let { consumablesJson ->
                    (consumablesJson as List<*>).map { consumableList ->
                        (consumableList as List<*>).map { consumableJson ->
                            Consumable.fromJson(consumableJson as Map<String, Any>)
                        }
                    }
                }
            )
        }
    }
}