@Serializable
data class Exterior(
    val id: Int,
    val name: String,
    val icon: String,
    val description: String? = null,
    val costGold: Int? = null,
    val costCR: Int? = null,
    val modifiers: Modifiers? = null,
    val type: String
) {
    val isFlag get() = type == "Flags"

    companion object {
        fun fromJson(json: Map<String, Any>): Exterior {
            return Exterior(
                id = json["id"] as Int,
                name = json["name"] as String,
                icon = json["icon"] as String,
                description = json["description"] as? String,
                costGold = json["costGold"] as? Int,
                costCR = json["costCR"] as? Int,
                modifiers = json["modifiers"]?.let { Modifiers.fromJson(it) },
                type = json["type"] as String
            )
        }
    }

    override fun toString(): String {
        return "Exterior(id=$id, name='$name', icon='$icon', description=$description, costGold=$costGold, costCR=$costCR, modifiers=$modifiers, type='$type')"
    }
}

data class Modifiers(
    val value: Int,
    val attribute: Attribute
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Modifiers {
            return Modifiers(
                value = json["value"] as Int,
                attribute = Attribute.valueOf((json["attribute"] as String).uppercase())
            )
        }
    }

    override fun toString(): String {
        return "Modifiers(value=$value, attribute=$attribute)"
    }
}

enum class Attribute {
    SPEED, STAMINA, AGILITY, CRITICAL_STRIKE
}