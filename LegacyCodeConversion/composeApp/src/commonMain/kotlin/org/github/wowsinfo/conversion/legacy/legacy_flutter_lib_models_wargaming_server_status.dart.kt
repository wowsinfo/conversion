class ServerStatus(private val wows: List<ServerPlayerOnline>) {
    val playersOnline: Int?
        get() = wows.firstOrNull()?.playersOnline

    companion object {
        fun fromJson(json: Map<String, Any>): ServerStatus {
            return ServerStatus(
                wows = json["wows"] as List<ServerPlayerOnline>
            )
        }
    }

    override fun toString(): String {
        return "ServerStatus(online: $playersOnline)"
    }
}

@Serializable
data class ServerPlayerOnline(val playersOnline: Int?, val server: String?)