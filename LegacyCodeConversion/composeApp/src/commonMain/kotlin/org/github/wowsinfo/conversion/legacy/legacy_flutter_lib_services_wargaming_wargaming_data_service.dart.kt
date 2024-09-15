
class WargamingDataService(private val server: GameServer, private val language: String = "en") : BaseService() {
    override fun baseUrl(): String {
        return "https://api.worldofwarships.${server.domain}"
    }

    private val wgnUrl = "https://api.worldoftanks.${server.domain}/wgn"
    private val applicationId = "?application_id=$apiKey"

    suspend fun getShipsInfo(): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/ships/list$applicationId&language=${language}")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get ships info. Status code: ${response.status}"))
        }
    }

    suspend fun getClanInfo(): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/info$applicationId&language=${language}")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan info. Status code: ${response.status}"))
        }
    }

    suspend fun getStatsInfo(): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/info$applicationId&language=${language}")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get stats info. Status code: ${response.status}"))
        }
    }

    suspend fun getAchievementsInfo(): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/achievements$applicationId&language=${language}")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get achievements info. Status code: ${response.status}"))
        }
    }

    suspend fun getAchievementDescriptions(): Result<Unit> {
        val response = ktorClient.get<String>("$wgnUrl/achievements/info$applicationId&language=${language}")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get achievement descriptions. Status code: ${response.status}"))
        }
    }

    suspend fun getShipInfo(shipId: Int): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/ships/info$applicationId&language=${language}&ship_id=$shipId")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get ship info. Status code: ${response.status}"))
        }
    }

    suspend fun getShipTypes(): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/ships/types$applicationId&language=${language}")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get ship types. Status code: ${response.status}"))
        }
    }

    suspend fun getPlayerInfo(accountId: Int): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/list$applicationId&language=${language}&search=$accountId")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get player info. Status code: ${response.status}"))
        }
    }

    suspend fun getPlayerInfoByNickname(nickname: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/list$applicationId&language=${language}&search=$nickname")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get player info. Status code: ${response.status}"))
        }
    }

    suspend fun getPlayerStats(accountId: Int): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/info$applicationId&language=${language}&account_id=$accountId")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get player stats. Status code: ${response.status}"))
        }
    }

    suspend fun getPlayerAchievements(accountId: Int): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/achievements$applicationId&language=${language}&account_id=$accountId")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get player achievements. Status code: ${response.status}"))
        }
    }

    suspend fun getPlayerClan(accountId: Int): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/account/clans$applicationId&language=${language}&account_id=$accountId")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get player clan. Status code: ${response.status}"))
        }
    }

    suspend fun getClanInfoByTag(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/info$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan info. Status code: ${response.status}"))
        }
    }

    suspend fun getClanMembers(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/members$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan members. Status code: ${response.status}"))
        }
    }

    suspend fun getClanStats(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/stats$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan stats. Status code: ${response.status}"))
        }
    }

    suspend fun getClanAchievements(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/achievements$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan achievements. Status code: ${response.status}"))
        }
    }

    suspend fun getClanHistory(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/history$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan history. Status code: ${response.status}"))
        }
    }

    suspend fun getClanOperations(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/operations$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan operations. Status code: ${response.status}"))
        }
    }

    suspend fun getClanApplications(tag: String): Result<Unit> {
        val response = ktorClient.get<String>("${baseUrl()}/clans/applications$applicationId&language=${language}&tag=$tag")
        return when (response.status) {
            OK -> Result.Success(Unit)
            else -> Result.Error(Exception("Failed to get clan applications. Status code: ${response.status}"))
        }
    }

}