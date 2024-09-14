import kotlinx.serialization.Serializable
@Serializable
data class Modernization(
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
    override fun toString(): String =
        if (unique != null && ships != null)
            "${modifiers.toString()}\n${GameRepository.instance.shipNameOf(ships.first.toString()) ?: ""}"
        else
            modifiers.toString()

    val title: String
        get() = if (Localisation.instance.stringOf(name) == null) ""
        else "$name [${slot + 1}]"

    fun greater(other: Modernization): Int =
        if (special == other.special && unique == other.unique)
            id - other.id
        else
            if (special == null && unique == null) 2
            else if (unique == null && other.unique != null) 1
            else -1

    fun isFor(ship: Ship): Boolean =
        if (ships?.contains(ship.id) == true)
            true
        else if (excludes?.contains(ship.id) == true)
            false
        else {
            val region = nation ?: emptyList()
            val shipType = type ?: emptyList()
            val tier = level ?: emptyList()

            region.contains(ship.region) &&
                    shipType.contains(ship.type) &&
                    tier.contains(ship.tier)
        }

    companion object {
        fun fromJson(json: Map<String, Any>): Modernization =
            Modernization(
                slot = json["slot"] as Int,
                id = json["id"] as Int,
                costCR = json["costCR"] as Int,
                name = json["name"] as String,
                icon = json["icon"] as String,
                description = json["description"] as String,
                level = if (json["level"] == null) null else (json["level"] as List<Any>).map { it as Int }.toList(),
                type = if (json["type"] == null) null else (json["type"] as List<Any>).map { it as String }.toList(),
                nation = if (json["nation"] == null) null else (json["nation"] as List<Any>).map { it as String }.toList(),
                modifiers = Modifiers.fromJson(json["modifiers"] as Map<String, Any>),
                ships = if (json["ships"] == null) null else (json["ships"] as List<Any>).map { it as Int }.toList(),
                excludes = if (json["excludes"] == null) null else (json["excludes"] as List<Any>).map { it as Int }.toList(),
                special = json["special"] as Boolean?,
                unique = json["unique"] as Boolean?
            )
    }
}