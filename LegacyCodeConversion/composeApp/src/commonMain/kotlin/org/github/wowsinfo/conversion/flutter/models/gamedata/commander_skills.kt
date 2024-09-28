    AirCarrier,
    Battleship,
    Cruiser,
    Destroyer,
    Submarine;

    val rawName: String
        get() = name.replaceFirstChar { it.uppercase() }

    val langName: String
        get() = name.uppercase()
}

data class CommandSkillInfo(
    val airCarrier: Int,
    val battleship: Int,
    val cruiser: Int,
    val destroyer: Int,
    val submarine: Int,
    val shipTypes: List<CommanderSkillType>
)

    val airCarrier: List<List<ShipSkill>>,
    val battleship: List<List<ShipSkill>>,
    val cruiser: List<List<ShipSkill>>,
    val destroyer: List<List<ShipSkill>>,
    val submarine: List<List<ShipSkill>>,
    val shipTypes: Map<String, List<List<ShipSkill>>>
) {
    companion object {
        fun fromJson(json: Map<String, Any>): CommandSkillInfo {
            return CommandSkillInfo(
                airCarrier = (json["AirCarrier"] as List<*>).map { 
                    (it as List<*>).map { ShipSkill.fromJson(it as Map<String, Any>) } 
                },
                battleship = (json["Battleship"] as List<*>).map { 
                    (it as List<*>).map { ShipSkill.fromJson(it as Map<String, Any>) } 
                },
                cruiser = (json["Cruiser"] as List<*>).map { 
                    (it as List<*>).map { ShipSkill.fromJson(it as Map<String, Any>) } 
                },
                destroyer = (json["Destroyer"] as List<*>).map { 
                    (it as List<*>).map { ShipSkill.fromJson(it as Map<String, Any>) } 
                },
                submarine = (json["Submarine"] as List<*>).map { 
                    (it as List<*>).map { ShipSkill.fromJson(it as Map<String, Any>) } 
                },
                shipTypes = json.mapValues { 
                    (it.value as List<*>).map { 
                        (it as List<*>).map { ShipSkill.fromJson(it as Map<String, Any>) } 
                    } 
                }
            )
        }
    }
}

data class ShipSkill(
    val name: String,
    val tier: Int,
    val column: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ShipSkill {
            return ShipSkill(
                name = json["name"] as String,
                tier = json["tier"] as Int,
                column = json["column"] as Int
            )
        }
    }
}

    val name: String,
    val tier: Int,
    val column: Int
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ShipSkill {
            return ShipSkill(
                name = json["name"] as String,
                tier = json["tier"] as Int,
                column = json["column"] as Int
            )
        }
    }

    fun toJson(): Map<String, Any> {
        return mapOf(
            "name" to name,
            "tier" to tier,
            "column" to column
        )
    }

    override fun toString(): String {
        return "ShipSkill{name='$name', tier=$tier, column=$column}"
    }
}