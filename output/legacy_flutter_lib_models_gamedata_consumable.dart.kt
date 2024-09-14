import androidx.compose.runtime.Immutable

@Immutable
data class Consumable(
    val name: String,
    val type: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Consumable {
            return Consumable(
                name = json["name"] as String,
                type = json["type"] as String
            )
        }
    }
}