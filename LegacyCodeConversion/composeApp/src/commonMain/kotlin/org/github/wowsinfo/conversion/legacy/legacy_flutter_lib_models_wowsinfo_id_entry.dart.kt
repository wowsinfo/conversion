import kotlinx.serialization.Serializable
class IDEntry<T : SearchResult> {
    val entry: T
    val server: Int

    constructor(entry: T, server: Int) {
        this.entry = entry
        this.server = server
    }
}