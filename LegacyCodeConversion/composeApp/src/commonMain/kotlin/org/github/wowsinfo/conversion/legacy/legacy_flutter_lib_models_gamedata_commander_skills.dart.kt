
enum class CommanderSkillType {
    airCarrier,
    battleship,
    cruiser,
    destroyer,
    submarine;

    val rawName: String
        get() = name.substring(0, 1).uppercase() + name.substring(1)

    val langName: String
        get() = name.uppercase()
}

@Serializable
data class CommandSkillInfo(
    val airCarrier: List<List<ShipSkill>>,
    val battleship: List<List<ShipSkill>>,
    val cruiser: List<List<ShipSkill>>,
    val destroyer: List<List<ShipSkill>>,
    val submarine: List<List<ShipSkill>>,
    val shipTypes: Map<String, List<List<ShipSkill>>>
) {
    companion object {
        fun fromJson(json: Map<String, Any>): CommandSkillInfo = CommandSkillInfo(
            airCarrier = (json["AirCarrier"] as List).map { ShipSkill.fromJson(it as Map<String, Any>) }.chunked(4),
            battleship = (json["Battleship"] as List).map { ShipSkill.fromJson(it as Map<String, Any>) }.chunked(5),
            cruiser = (json["Cruiser"] as List).map { ShipSkill.fromJson(it as Map<String, Any>) }.chunked(3),
            destroyer = (json["Destroyer"] as List).map { ShipSkill.fromJson(it as Map<String, Any>) }.chunked(4),
            submarine = (json["Submarine"] as List).map { ShipSkill.fromJson(it as Map<String, Any>) }.chunked(4),
            shipTypes = json.mapValues { (_, value) ->
                (value as List).map { ShipSkill.fromJson(it as Map<String, Any>) }.chunked(
                    when (_key) {
                        "AirCarrier" -> 4
                        "Battleship" -> 5
                        "Cruiser" -> 3
                        "Destroyer" -> 4
                        else -> 4
                    }
                )
            }
        )
    }

    fun toMap(): Map<String, Any> = mapOf(
        "AirCarrier" to airCarrier.flatten().map { it.toMap() },
        "Battleship" to battleship.flatten().map { it.toMap() },
        "Cruiser" to cruiser.flatten().map { it.toMap() },
        "Destroyer" to destroyer.flatten().map { it.toMap() },
        "Submarine" to submarine.flatten().map { it.toMap() }
    )
}

@Serializable
data class ShipSkill(
    val name: String,
    val tier: Int,
    val column: Int
) {
    fun toJson(): Map<String, Any> = mapOf(
        "name" to name,
        "tier" to tier,
        "column" to column
    )
}