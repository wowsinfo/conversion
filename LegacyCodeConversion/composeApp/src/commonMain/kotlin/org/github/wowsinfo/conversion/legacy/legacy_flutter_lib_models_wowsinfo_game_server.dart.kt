enum class WoWsServer(val index: Int) {
    RUSSIA(0),
    EUROPE(1),
    NORTH_AMERICA(2),
    ASIA(3);
}

const val domain = arrayOf("ru", "eu", "com", "asia")
const val prefix = arrayOf("ru", "eu", "na", "asia")
const val numberDomain = arrayOf("ru.", "", "na.", "asia.")

class GameServer(private val server: WoWsServer) {
    fun domain(): String = domain[server.index]
    fun prefix(): String = prefix[server.index]
    fun numberDomain(): String = numberDomain[server.index]

    companion object {
        fun fromIndex(index: Int): GameServer {
            if (index !in 0..3) throw IllegalArgumentException("index must be between 0 and 3")
            return GameServer(WoWsServer.values[index])
        }

        val defaultServer = WoWsServer.ASIA
    }
}