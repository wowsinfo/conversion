
@Composable
fun Statistics(info: PlayerInfo) {
    val coroutineScope = rememberCoroutineScope()
    var state by remember { mutableStateOf(StatisticsState()) }

    LaunchedEffect(info) {
        val ID = info.accountId
        if (ID.isNotEmpty()) {
            val friendList = AppGlobalData.get(LOCAL.friendList)
            val master = AppGlobalData.get(LOCAL.userInfo)
            state = state.copy(
                name = info.nickname,
                id = info.accountId,
                server = info.server,
                valid = true,
                hidden = false,
                canBeMaster = master.accountId != info.accountId,
                canBeFriend = friendList.player[info.accountId] == null,
                clan = "",
                currRank = 0,
                rating = 0,
                achievement = false,
                rank = false,
                rankShip = false,
                ship = false,
                basic = false,
                graph = false,
                showMore = false,
                ratingColor = "#607D8B"
            )

            val domain = getDomain(info.server)
            val prefix = getPrefix(info.server)

            if (domain != null) {
                getBasic(domain, state.id) { basicData ->
                    state = state.copy(basic = true)
                }
                getRank(domain, state.id) { rankData ->
                    state = state.copy(rank = true)
                }
                getClan(domain, state.id) { clanData ->
                    state = state.copy(clan = clanData)
                }
                getShip(domain, state.id) { shipData ->
                    state = state.copy(ship = true)
                }
                getAchievement(domain, state.id) { achievementData ->
                    state = state.copy(achievement = true)
                }
            } else {
                state = state.copy(valid = false)
            }
        } else {
            state = state.copy(valid = false)
        }
    }

    if (state.valid) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = state.name, style = MaterialTheme.typography.h6)
            // Additional UI components based on state
        }
    } else {
        Text(text = "Invalid account", color = MaterialTheme.colors.error)
    }
}

data class StatisticsState(
    val name: String = "",
    val id: String = "",
    val server: String = "",
    val valid: Boolean = false,
    val hidden: Boolean = false,
    val canBeMaster: Boolean = false,
    val canBeFriend: Boolean = false,
    val clan: String = "",
    val currRank: Int = 0,
    val rating: Int = 0,
    val achievement: Boolean = false,
    val rank: Boolean = false,
    val rankShip: Boolean = false,
    val ship: Boolean = false,
    val basic: Boolean = false,
    val graph: Boolean = false,
    val showMore: Boolean = false,
    val ratingColor: String = "#607D8B"
)

    super.onCleared()
    // reset the theme colour back
    theme.colors.primary = TintColour()[500]
}

    val server = state.server
    val id = state.id
    SafeFetch.get(WoWsAPI.PlayerInfo, getDomain(server), id).onSuccess { data ->
        val hidden = Guard(data, "meta.hidden", null)
        var hiddenAccount = false
        if (hidden != null) {
            hiddenAccount = true
            setState { hidden = true }
        }

        val player = Guard(data, "data.$id", null)
        if (player == null) {
            setState { valid = false }
        } else {
            val battle = Guard(player, "statistics.pvp.battles", 0)
            if (!hiddenAccount && battle == 0) {
                setState { hidden = true }
            }
            setState { basic = player }
        }
    }.onFailure { error ->
        // Handle error
    }
}

    val id = state.id
    SafeFetch.get(WoWsAPI.PlayerClan, domain, id).onSuccess { data ->
        val tag = Guard(data, "data.$id.clan.tag", "")
        if (tag.isNotEmpty()) {
            setState { clan = tag }
        }
    }
}

    val id = state.id
    SafeFetch.get(WoWsAPI.PlayerAchievement, domain, id).onSuccess { data ->
        val achievement = Guard(data, "data.$id.battle", null)
        if (achievement != null) {
            setState { this.achievement = achievement }
        }
    }
}

    private val _currRank = MutableLiveData<Int>()
    val currRank: LiveData<Int> get() = _currRank

    private val _rank = MutableLiveData<Map<String, Any>>()
    val rank: LiveData<Map<String, Any>> get() = _rank

    private val _rankShip = MutableLiveData<Map<String, List<ShipRankInfo>>>()
    val rankShip: LiveData<Map<String, List<ShipRankInfo>>> get() = _rankShip

    fun getRank(id: String) {
        viewModelScope.launch {
            val rankInfo = SafeFetch.get(WoWsAPI.RankInfo, domain, id)
            val rank = Guard(rankInfo, "data.$id.seasons", null)
            if (rank != null) {
                val keys = rank.keys.toList()
                if (keys.isNotEmpty()) {
                    val last = keys.last()
                    val currRank = Guard(rank[last], "rank_info.rank", 0)
                    if (currRank > 0) {
                        _currRank.value = currRank
                    }
                }
                _rank.value = rank
            }

            val rankShipInfo = SafeFetch.get(WoWsAPI.RankShipInfo, domain, id)
            val ships = Guard(rankShipInfo, "data.$id", null)
            if (ships != null) {
                val formatted = mutableMapOf<String, MutableList<ShipRankInfo>>()
                for (ship in ships) {
                    val seasons = ship.seasons
                    val shipId = ship.ship_id
                    for ((season, curr) in seasons) {
                        if (formatted[season] == null) {
                            formatted[season] = mutableListOf()
                        }
                        val rankSolo = curr.rank_solo
                        val rankDiv2 = curr.rank_div2
                        val rankDiv3 = curr.rank_div3
                        curr.pvp = rankSolo ?: rankDiv2 ?: rankDiv3 ?: continue
                        curr.ship_id = shipId
                        formatted[season]?.add(curr)
                    }
                }
                _rankShip.value = formatted
            }
        }
    }
}

    val id = state.id
    val data = SafeFetch.get(WoWsAPI.ShipInfo, domain, id)
    val ship = Guard(data, "data.$id", null)
    println(ship)
    if (ship != null) {
        val rating = getOverallRating(ship)
        setState {
            this.ship = ship
            this.rating = rating
            this.graph = ship
            this.ratingColor = getColour(rating)
        }
    }
}

fun WoWsInfoScreen(viewModel: WoWsInfoViewModel) {
    val state = viewModel.state.collectAsState().value
    val errorStyle = styles.error
    val containerStyle = styles.container
    val footerStyle = styles.footer

    if (state.id == null || state.id.isEmpty()) {
        WoWsInfo(style = errorStyle) {
            Text("BUG")
        }
    } else if (!state.valid) {
        WoWsInfo(style = containerStyle) {
            Text("Data is not valid\nPlease try again later")
        }
    } else {
        viewModel.theme.colors.primary = state.ratingColor

        WoWsInfo(
            style = containerStyle,
            title = "- ${state.id} -",
            onClick = {
                SimpleViewHandler.openURL("https://${viewModel.prefix}.wows-numbers.com/player/${state.id},${state.name}/")
            }
        ) {
            ScrollableColumn {
                renderBasic(state.basic)
            }
            FooterPlus(style = footerStyle) {
                renderAchievement(state.achievement)
                renderGraph(state.graph)
                renderShip(state.ship)
                renderRank(state.rank, state.rankShip)
            }
        }
    }
}

fun RenderBasic(basic: BasicData?) {
    val container = Modifier.fillMaxWidth().padding(16.dp)
    val horizontal = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    val playerName = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
    val level = TextStyle(fontSize = 16.sp)

    if (basic == null) {
        val name = remember { mutableStateOf("") }
        // Assume name is set somewhere in the state
        Column(modifier = container) {
            Text(text = name.value, style = playerName)
            CircularProgressIndicator()
        }
    } else {
        val createdAt = basic.createdAt
        val levelingTier = basic.levelingTier
        val lastBattleTime = basic.lastBattleTime
        val nickname = basic.nickname
        val hidden = remember { mutableStateOf(false) } // Assume this is set in state
        val clan = remember { mutableStateOf("") } // Assume this is set in state
        val currRank = remember { mutableStateOf(0) } // Assume this is set in state
        val canBeFriend = remember { mutableStateOf(false) } // Assume this is set in state
        val canBeMaster = remember { mutableStateOf(false) } // Assume this is set in state
        val rating = remember { mutableStateOf(0) } // Assume this is set in state

        val register = humanTimeString(createdAt)
        val lastBattle = humanTimeString(lastBattleTime)

        if (hidden.value) {
            Column(modifier = container) {
                Row(modifier = horizontal) {
                    Text(text = nickname, style = playerName)
                    IconButton(onClick = { /* Handle click */ }) {
                        Icon(painterResource("https"), contentDescription = null)
                    }
                }
                Column(modifier = Modifier.padding(8.dp)) {
                    if (canBeFriend.value) {
                        Button(onClick = { addFriend() }) {
                            Text(text = "Add Friend")
                        }
                    }
                    InfoLabel(title = "Register Date", info = register)
                    InfoLabel(title = "Last Battle", info = lastBattle)
                    InfoLabel(title = "Level Tier", info = "Unknown")
                }
            }
        } else {
            var name = nickname
            if (clan.value.isNotEmpty()) {
                name = "[${clan.value}]\n$nickname"
            }
            var extraInfo = "Lv $levelingTier"
            if (currRank.value > 0) {
                extraInfo += " | ⭐${currRank.value}"
            }
            Column(modifier = container) {
                RatingButton(rating = rating.value)
                Text(text = name, style = playerName)
                Text(text = extraInfo, style = level)
                Row(modifier = horizontal) {
                    InfoLabel(title = "Register Date", info = register)
                    InfoLabel(title = "Last Battle", info = lastBattle)
                }
                Column(modifier = Modifier.padding(4.dp)) {
                    if (canBeFriend.value) {
                        Button(onClick = { addFriend() }) {
                            Text(text = "Add Friend")
                        }
                    }
                    if (canBeMaster.value) {
                        Button(onClick = { setMainAccount() }) {
                            Text(text = "Set Main")
                        }
                    }
                }
                RenderStatistics(basic.statistics)
            }
        }
    }
}

    val accountId = info.accountId
    val nickname = info.nickname
    val server = info.server
    return PlayerInfo(nickname, accountId, server)
}

data class PlayerInfo(val nickname: String, val accountId: String, val server: String)

    val info = getPlayerInfo()
    AppGlobalData.set(LOCAL.userInfo, info)
    SafeStorage.set(LOCAL.userInfo, info)
    setState { canBeMaster = false }
}

    val info = getPlayerInfo()

    val str = LOCAL.friendList
    val playerData = AppGlobalData.get(str).player.toMutableMap()
    playerData[info.accountId] = info

    SafeStorage.set(str, AppGlobalData.get(str).copy(player = playerData))
    setState { canBeFriend = false }
}

fun RenderStatistics(statistics: Statistics?) {
    if (statistics == null) {
        return
    }
    val showMore = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        DetailedInfo(data = statistics, more = showMore.value)
        PlayerRecord(data = statistics.pvp)
    }
}

fun RenderAchievement(achievement: Achievement?) {
    val loading = achievement == null || achievement.isEmpty()

    TabButton(
        icon = painterResource(id = R.drawable.achievement_tab),
        enabled = !loading,
        onClick = { SafeAction("PlayerAchievement", achievement) }
    )
}

fun RenderShip(ship: List<Ship>?) {
    val loading = ship == null || ship.isEmpty()

    TabButton(
        icon = painterResource(id = R.drawable.ship_icon),
        enabled = !loading,
        onClick = { SafeAction("PlayerShip", ship, 1) }
    )
}

fun RenderRank(rank: RankType?, rankShip: ShipType?) {
    val loading = rank == null || rankShip == null

    TabButton(
        icon = painterResource(id = R.drawable.rank_icon),
        enabled = !loading,
        onClick = { SafeAction("Rank", data = rank, ship = rankShip) }
    )
}

fun RenderGraph(graph: List<Any>?, hidden: Boolean) {
    val loading = graph.isNullOrEmpty()

    TabButton(
        icon = painterResource(id = R.drawable.graph_icon),
        enabled = !loading && !hidden,
        onClick = { SafeAction("Graph", graph) }
    )
}


@Composable
fun ErrorView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Text("Error", style = MaterialTheme.typography.bodyLarge)
    }
}


@Composable
fun HiddenProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Your hidden profile content goes here
    }
}

    // Add your content here
}

    text = playerName,
    modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 32.dp, bottom = 8.dp),
    fontSize = 32.sp,
    fontWeight = FontWeight(500),
    textAlign = TextAlign.Center
)


@Composable
fun CenteredText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}

    modifier = Modifier
        .padding(start = 16.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
    contentAlignment = Alignment.TopStart
) {
    // Your content goes here
}

fun Footer() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Add your footer content here
    }
}


@Composable
fun Statistics() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Your UI components go here
    }
}

@Preview
@Composable
fun PreviewStatistics() {
    Statistics()
}