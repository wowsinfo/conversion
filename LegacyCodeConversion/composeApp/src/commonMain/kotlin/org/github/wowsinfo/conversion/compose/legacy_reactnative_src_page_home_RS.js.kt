
@Composable
fun RS() {
    val context = LocalContext.current
    val ip = AppGlobalData.get(LocalData.RsIP)
    val valid by remember { mutableStateOf(ip.isNotEmpty()) }
    val rsInfo by remember { mutableStateOf(null) }
    var battleTime by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true) }
    val allayList = remember { mutableListOf<RSPlayer>() }
    val enemyList = remember { mutableListOf<RSPlayer>() }
    val systemUiController = rememberSystemUiController()
    // set status bar color
    SideEffect {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.8f),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (valid) {
            // load rs info
            val newBattleTime = rsInfo?.dateTime ?: ""
            if (newBattleTime != battleTime) {
                loading = true
                battleTime = newBattleTime
                allayList.clear()
                enemyList.clear()

                for (v in rsInfo!!.vehicles) {
                    launch(Dispatchers.IO) {
                        val player = appendExtraInfo(v)
                        val team = player.relation
                        if (team < 2) {
                            allayList.add(player)
                        } else {
                            enemyList.add(player)
                        }

                        if (player.account_id == null) {
                            player.account_id = random(88888888)
                        }
                    }
                }
            }
        }
    }
}

private suspend fun appendExtraInfo(player: RSPlayer): RSPlayer {
    val name = player.name
    if (name.startsWith(":")) return player

    val idInfo = HttpRequester.get(WoWsAPI.PlayerSearch, getCurrDomain(), name)
    val playerId = idInfo.data?.get(0)?.account_id ?: null
    if (playerId != null) {
        player.account_id = playerId
        player.nickname = idInfo.data?.get(0)?.nickname

        val shipInfo = HttpRequester.get(
            WoWsAPI.OneShipInfo,
            getCurrDomain(),
            player.shipId,
            playerId
        )
        val pvp = shipInfo.data?.get(playerId.toString())?.get(0)?.pvp ?: null
        if (pvp != null) player.pvp = pvp
    }
    return player
}

data class RSPlayer(
    var id: Int? = null,
    var name: String? = null,
    var shipId: Int? = null,
    var relation: Int? = null,
    var account_id: Int? = null,
    var nickname: String? = null,
    val pvp: PVPShipInfo? = null
) {
    override fun toString(): String {
        return "id=$id, name=$name, shipId=$shipId, relation=$relation, account_id=$account_id"
    }
}

data class PVPShipInfo(
    var battles: Int? = null,
    var wins: Int? = null,
    var defeats: Int? = null,
    var draws: Int? = null
)

/**
 * Update the IP format and try to send a request to it
 */
suspend fun validIP(ip: String) {
    val url = "http://${ip.split('/').joinToString()}:8605"
    try {
        HttpRequester.fetch(url)
        // Update IP when it is valid
        AppGlobalData.set(LocalData.RsIP, ip)
    } catch (e: Exception) {
        Alert(context).showAlert("Error", "$url is not valid")
    }
}

@Composable
fun WarshipCell(info: WarshipInfo?) {

}