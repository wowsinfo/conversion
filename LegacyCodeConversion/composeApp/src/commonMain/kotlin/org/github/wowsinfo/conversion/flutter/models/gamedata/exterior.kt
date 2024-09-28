
typealias Camouflage = Exterior
typealias Flag = Exterior

@Serializable
data class Exterior(
    val id: String,
    val name: String,
    val icon: String,
    val description: String? = null,
    val costGold: Int? = null,
    val costCR: Int? = null,
    val modifiers: List<Modifier>? = null,
    val type: String
)

@Serializable
data class Modifier(
    val id: String,
    val name: String,
    val effect: String
)

    val id: Int,
    val name: String,
    val icon: String,
    val description: String?,
    val costGold: Int?,
    val costCR: Int?,
    val modifiers: Modifiers?,
    val type: String
) {
    val isFlag: Boolean
        get() = type == "Flags"

    companion object {
        fun fromJson(json: Map<String, Any?>): Exterior {
            return Exterior(
                id = json["id"] as Int,
                name = json["name"] as String,
                icon = json["icon"] as String,
                description = json["description"] as String?,
                costGold = json["costGold"] as Int?,
                costCR = json["costCR"] as Int?,
                modifiers = json["modifiers"]?.let { Modifiers.fromJson(it as Map<String, Any?>) },
                type = json["type"] as String
            )
        }
    }
}