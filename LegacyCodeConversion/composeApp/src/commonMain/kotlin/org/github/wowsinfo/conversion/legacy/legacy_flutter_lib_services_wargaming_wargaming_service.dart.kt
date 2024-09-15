
class WargamingService(private val server: GameServer, private val language: String = "en") {
    companion object {
        private const val BASE_URL = "https://api.worldofwarships."
        private const val WG_URL = "https://api.worldoftanks."
        private const val APPLICATION_ID = "?application_id=$apiKey"
    }

    private val httpClient = HttpClient(Dispatchers.IO) {
        install(JsonFeature)
    }

    suspend fun getServerStatus(): ApiResult<ServerStatus?> = withContext(Dispatchers.IO) {
        val result = httpClient.get<String>("${WG_URL}${server.domain}/wgn/servers/info$APPLICATION_ID&game=wows")
        decodeObject(result, ServerStatus::fromJson)
    }

    suspend fun searchPlayer(nickname: String): ApiResult<List<PlayerResult>> = withContext(Dispatchers.IO) {
        val result = httpClient.get<String>("${WG_URL}${server.domain}/wgn/account/list$APPLICATION_ID&game=wows&search=$nickname")
        decodeList(result, PlayerResult::fromJson)
    }

    suspend fun searchClan(tag: String): ApiResult<List<ClanResult>> = withContext(Dispatchers.IO) {
        val result = httpClient.get<String>("${BASE_URL}${server.domain}/wows/clans/list$APPLICATION_ID&search=$tag")
        decodeList(result, ClanResult::fromJson)
    }

    suspend fun getPlayerInformation(accountId: String): ApiResult<PlayerInformation> = withContext(Dispatchers.IO) {
        val result = httpClient.get<String>("${BASE_URL}${server.domain}/wows/account/info$APPLICATION_ID&account_id=$accountId&extra=statistics.pve%2Cstatistics.pvp_div2%2Cstatistics.pvp_div3%2Cstatistics.rank_solo")
        decodeObject(result, PlayerInformation::fromJson)
    }

    suspend fun getPlayerAchievements(accountId: String): ApiResult<PlayerAchievement> = withContext(Dispatchers.IO) {
        val result = httpClient.get<String>("${BASE_URL}${server.domain}/wows/account/achievements$APPLICATION_ID&account_id=$accountId")
        decodeObject(result, PlayerAchievement::fromJson)
    }

    suspend fun getClanInformation(clanId: String): ApiResult<ClanInformation> = withContext(Dispatchers.IO) {
        val result = httpClient.get<String>("${BASE_URL}${server.domain}/wows/clans/info$APPLICATION_ID&clan_id=$clanId&extra=members&fields=-members_ids")
        decodeObject(result, ClanInformation::fromJson)
    }
}