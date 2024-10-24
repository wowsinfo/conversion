
class WargamingDataService(private val server: GameServer, private val language: String = "en") : ServiceBase() {
    private val serverDomain: String = server.domain

    // Additional methods for data downloading and handling would go here
}


    val baseUrl: String
        get() = "https://api.worldofwarships.$server/wows"

    val wgnUrl: String
        get() = "https://api.worldoftanks.$server/wgn"

    private val applicationId: String
        get() = "?application_id=$apiKey"
}