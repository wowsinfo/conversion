@Serializable
data class Achievement(
    val icon: String,
    val name: String,
    val description: String,
    val type: List<String>,
    val id: Int,
    val constants: Map<String, Any> = emptyMap(),
    val added: Int? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Achievement {
            return Achievement(
                icon = json["icon"] as String,
                name = json["name"] as String,
                description = json["description"] as String,
                type = (json["type"] as List<Any>).map { it.toString() }.toList(),
                id = (json["id"] as Number).toInt(),
                constants = (json["constants"] as Map<String, Any>)
                    .filterKeys { it.isNotEmpty() }
                    .mapValues { it.value },
                added = json["added"] as? Int
            )
        }
    }

    override fun toString(): String {
        return "Achievement(icon=$icon, name=$name, description=$description, type=$type, id=$id, constants=$constants, added=$added)"
    }
}