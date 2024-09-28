
@Serializable
data class ServerStatus(
    val wows: Int? = null
)


    val playersOnline: Int?
        get() {
            val data = wows
            return if (data != null && data.isNotEmpty()) {
                require(data.size == 1) { "There should be only one element in it" }
                data[0].playersOnline
            } else {
                null
            }
        }
}

data class ServerPlayerOnline(val playersOnline: Int)

    val wows: List<ServerPlayerOnline>
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ServerStatus {
            val wows = (json["wows"] as List<Map<String, Any>>).map { ServerPlayerOnline.fromJson(it) }
            return ServerStatus(wows)
        }
    }

    override fun toString(): String {
        return "ServerStatus{online: ${wows.size}}"
    }
}

data class ServerPlayerOnline(
    // Define properties here
) {
    companion object {
        fun fromJson(json: Map<String, Any>): ServerPlayerOnline {
            // Implement parsing logic here
            return ServerPlayerOnline(
                // Initialize properties here
            )
        }
    }
}


@Serializable
data class ServerPlayerOnline(
    val playersOnline: Int? = null,
    val server: String? = null
)

    val playersOnline: Int?,
    val server: String?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): ServerPlayerOnline {
            return ServerPlayerOnline(
                playersOnline = json["players_online"] as? Int,
                server = json["server"] as? String
            )
        }
    }
}