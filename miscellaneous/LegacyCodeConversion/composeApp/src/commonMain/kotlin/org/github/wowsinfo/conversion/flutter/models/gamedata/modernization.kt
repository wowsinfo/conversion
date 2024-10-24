
@Serializable
data class Modernization(
    val slot: String,
    val id: String,
    val costCR: Int,
    val name: String,
    val icon: String,
    val description: String,
    val level: Int? = null,
    val type: String? = null,
    val nation: String? = null,
    val modifiers: List<Modifier>,
    val ships: List<Ship>? = null,
    val excludes: List<String>? = null,
    val special: Boolean? = null,
    val unique: Boolean? = null
)

    val slot: Int,
    val id: Int,
    val costCR: Int,
    val name: String,
    val icon: String,
    val description: String,
    val level: List<Int>? = null,
    val type: List<String>? = null,
    val nation: List<String>? = null,
    val modifiers: Modifiers,
    val ships: List<Int>? = null,
    val excludes: List<Int>? = null,
    val special: Boolean? = null,
    val unique: Boolean? = null
) {
    override fun toString(): String {
        val description = modifiers.toString()
        if (unique != null && ships != null) {
            val shipName = GameRepository.instance.shipNameOf(ships.first().toString())
            if (shipName != null) {
                return "$description\n$shipName"
            }
        }
        return description
    }
}

    get() {
        val upgradeName = Localisation.instance.stringOf(name)
        if (upgradeName == null) return ""
        return "$upgradeName [${slot + 1}]"
    }

    if (special == other.special && unique == other.unique) {
        return id - other.id
    }

    if (special == null && unique == null) return 2
    if (unique == null && other.unique != null) return 1
    return -1
}

    val shipId = ship.id
    if (ships != null && ships.contains(shipId)) {
        return true
    }

    if (excludes != null && excludes.contains(shipId)) {
        return false
    }

    val region = nation ?: emptyList()
    val type = this.type ?: emptyList()
    val tier = level ?: emptyList()

    if (!region.contains(ship.region)) return false
    if (!type.contains(ship.type)) return false
    if (!tier.contains(ship.tier)) return false
    return true
}

    val slot: String?,
    val id: Int?,
    val costCR: Int?,
    val name: String?,
    val icon: String?,
    val description: String?,
    val level: List<Int>?,
    val type: List<String>?,
    val nation: List<String>?,
    val modifiers: Modifiers?,
    val ships: List<Int>?,
    val excludes: List<Int>?,
    val special: String?,
    val unique: String?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): Modernization {
            return Modernization(
                slot = json["slot"] as? String,
                id = json["id"] as? Int,
                costCR = json["costCR"] as? Int,
                name = json["name"] as? String,
                icon = json["icon"] as? String,
                description = json["description"] as? String,
                level = (json["level"] as? List<*>)?.map { it as Int },
                type = (json["type"] as? List<*>)?.map { it as String },
                nation = (json["nation"] as? List<*>)?.map { it as String },
                modifiers = json["modifiers"]?.let { Modifiers.fromJson(it as Map<String, Any?>) },
                ships = (json["ships"] as? List<*>)?.map { it as Int },
                excludes = (json["excludes"] as? List<*>)?.map { it as Int },
                special = json["special"] as? String,
                unique = json["unique"] as? String
            )
        }
    }
}