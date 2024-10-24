

interface Encodable

data class IDEntry<T : SearchResult>(
    val entry: T,
    val server: String
) : Encodable

    val entry: T,
    val server: Int
) {
    fun toJson(): Map<String, Any> {
        return mapOf(
            "entry" to entry.toJson(),
            "server" to server
        )
    }
}