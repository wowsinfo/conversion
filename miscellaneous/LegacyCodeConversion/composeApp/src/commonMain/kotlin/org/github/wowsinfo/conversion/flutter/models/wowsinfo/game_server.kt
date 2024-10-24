    russia,
    europe,
    northAmerica,
    asia
}

private val domain = listOf("ru", "eu", "com", "asia")
private val prefix = listOf("ru", "eu", "na", "asia")
private val numberDomain = listOf("ru.", "", "na.", "asia.")

data class GameServer(private val server: WoWsServer) {
    val domain: String = domain[server.ordinal]
    val prefix: String = prefix[server.ordinal]
    val numberDomain: String = numberDomain[server.ordinal]

    val index: Int
        get() = server.ordinal

    companion object {
        fun fromIndex(index: Int): GameServer {
            require(index in 0..3) { "index must be between 0 and 3" }
            return GameServer(WoWsServer.values()[index])
        }
    }
}

    val asia = WoWsServer()
}

val defaultServer: WoWsServer
    get() = WoWsServer.asia