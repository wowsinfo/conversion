
@Composable
fun RS() {
    val coroutineScope = rememberCoroutineScope()
    var ip by remember { mutableStateOf(AppGlobalData.get(LOCAL.rsIP)) }
    var rs by remember { mutableStateOf(null) }
    var valid by remember { mutableStateOf(false) }
    var info by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }
    var battleTime by remember { mutableStateOf("") }
    var allay by remember { mutableStateOf(listOf<Any>()) }
    var allayInfo by remember { mutableStateOf(mapOf<String, Any>()) }
    var enemy by remember { mutableStateOf(listOf<Any>()) }
    var enemyInfo by remember { mutableStateOf(mapOf<String, Any>()) }

    setLastLocation("RS")

    // UI components go here
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = TextFieldValue(ip),
            onValueChange = { ip = it.text },
            label = { Text("IP Address") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(onClick = {
            // Handle button click
        }, modifier = Modifier.padding(16.dp)) {
            Text("Submit")
        }
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        // Additional UI components for displaying ally and enemy info
    }
}

    super.onCreate(savedInstanceState)
    KeepAwake.activate()
    val ip = state.ip
    if (ip.isNotEmpty()) {
        validIP(ip)
    }
}

    KeepAwake.deactivate()
    // reset the theme colour back
    theme.colors.primary = TintColour()[500]
}

fun WoWsInfoScreen(viewModel: YourViewModel) {
    val ip by viewModel.ip.collectAsState()
    val rs by viewModel.rs.collectAsState()
    val valid by viewModel.valid.collectAsState()

    WoWsInfo(
        onPress = if (rs) { { viewModel.setInfo(true) } } else null,
        title = "Map Information"
    ) {
        if (!valid) {
            KeyboardAvoidingView {
                TextField(
                    value = ip,
                    onValueChange = { viewModel.setIp(it) },
                    placeholder = { Text("192.168.1.x") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = if (isAndroid) KeyboardType.Decimal else KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    SimpleViewHandler.openURL("https://github.com/wowsinfo/WoWs-RS/releases/latest")
                }) {
                    Text(lang.extra_rs_beta_download)
                }
            }
        } else {
            renderPlayer()
        }
        renderMapInfo(rs)
    }
}

fun RenderPlayer(loading: Boolean, allay: List<Player>, enemy: List<Player>) {
    if (loading) {
        LoadingIndicator()
        return
    }

    val allayRating = getOverallRating(allay)
    val enemyRating = getOverallRating(enemy)
    val sortedAllay = allay.sortedByDescending { it.ap }
    val sortedEnemy = enemy.sortedByDescending { it.ap }

    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            RatingButton(rating = allayRating, number = true)
            Text("RS Beta", style = MaterialTheme.typography.h6)
            RatingButton(rating = enemyRating, number = true)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier.weight(1f)
            ) {
                items(sortedAllay) { player ->
                    RenderPlayerCell(player)
                }
            }
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier.weight(1f)
            ) {
                items(sortedEnemy) { player ->
                    RenderPlayerCell(player)
                }
            }
        }
    }
}

fun RenderPlayerCell(info: PlayerInfo) {
    val playerNameStyle = styles.playerName
    val cellStyle = styles.cell
    val pName = SafeValue(info.nickname, info.name)
    info.server = getCurrServer()

    Box(
        modifier = Modifier
            .clickable(
                enabled = info.pvp,
                onClick = { SafeAction("PlayerShipDetail", mapOf("data" to info)) }
            )
            .combinedClickable(
                onLongClick = { if (info.account_id != null) SafeAction("Statistics", mapOf("info" to info)) }
            )
            .then(cellStyle)
    ) {
        WarshipCell(item = AppGlobalData.get(SAVED.warship)[info.ship_id], scale = 1.4f)
        Text(
            text = pName,
            style = playerNameStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        SimpleRating(info = info)
    }
}

fun RenderMapInfo(rs: MapInfoResponse?) {
    if (rs == null) {
        return
    }
    val info by remember { mutableStateOf(false) }

    val (clientVersionFromExe, dateTime, duration, gameLogic, mapDisplayName, matchGroup, name, weatherParams) = rs

    val params = weatherParams.entries.joinToString("\n") { it.value.joinToString(", ") }

    if (info) {
        Dialog(onDismissRequest = { info = false }) {
            Surface(shape = MaterialTheme.shapes.medium) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    ListItem(
                        modifier = Modifier.padding(16.dp),
                        headlineContent = { Text("Client Version") },
                        supportingContent = { Text(clientVersionFromExe) }
                    )
                    ListItem(
                        modifier = Modifier.padding(16.dp),
                        headlineContent = { Text("Time") },
                        supportingContent = { Text(dateTime) }
                    )
                    ListItem(
                        modifier = Modifier.padding(16.dp),
                        headlineContent = { Text("Game Mode") },
                        supportingContent = { Text("$matchGroup - $gameLogic - $name") }
                    )
                    ListItem(
                        modifier = Modifier.padding(16.dp),
                        headlineContent = { Text("Map") },
                        supportingContent = { Text(mapDisplayName) }
                    )
                    ListItem(
                        modifier = Modifier.padding(16.dp),
                        headlineContent = { Text("Duration") },
                        supportingContent = { Text("${roundTo(duration / 60)} min") }
                    )
                    Text(
                        text = params,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

    val url = "http://${ip.replace("/", "")}:8605"
    try {
        val response = khttp.get(url)
        if (response.statusCode == 200) {
            setState { valid = true }
            SafeStorage.set(LOCAL.rsIP, ip)
            getArenaInfo(url)
            interval = Timer().scheduleAtFixedRate(0, 22222) { getArenaInfo(url) }
        }
    } catch (e: Exception) {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage("$url is not valid")
            .setPositiveButton("OK", null)
            .show()
    }
}

    try {
        val text = fetch(url).await().text()
        if (text != "[]") {
            val data = Json.decodeFromString<ArenaData>(text)
            setState { rs = data }
            val battleTime = state.battleTime
            if (data.dateTime != battleTime) {
                setState { loading = true; battleTime = data.dateTime }
                val vehicles = data.vehicles
                val allayList = mutableListOf<Player>()
                val enemyList = mutableListOf<Player>()
                for (v in vehicles) {
                    delay(300)
                    appendExtraInfo(v).let { player ->
                        val team = player.relation
                        if (team < 2) {
                            allayList.add(player)
                        } else {
                            enemyList.add(player)
                        }
                        if (player.accountId == null) {
                            player.accountId = random(88888888)
                        }
                        setState {
                            this.allay = allayList
                            this.enemy = enemyList
                            loading = false
                        }
                    }
                }
            }
        }
    } catch (e: Exception) {
        clearTimeout(interval)
        setState { valid = false; rs = null }
    }
}

    val (name, shipId) = player
    if (name.startsWith(':')) {
        return player
    }
    val idInfo = SafeFetch.get(WoWsAPI.PlayerSearch, domain, name)
    val playerID = Guard(idInfo, "data.0", null)
    if (playerID != null) {
        player.ship_id = player.shipId
        player.shipId = null
        player.id = null
        player.name = null
        player.account_id = playerID.account_id
        player.nickname = playerID.nickname

        // Get player ship info
        val shipInfo = SafeFetch.get(
            WoWsAPI.OneShipInfo,
            domain,
            shipId,
            player.account_id
        )
        val pvp = Guard(shipInfo, "data.${player.account_id}.0.pvp", null)
        if (pvp != null) {
            player.pvp = pvp
        }
    }
    return player
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Your content goes here
    }
}


@Composable
fun MyComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        // Your content here
    }
}


val horizontalModifier = Modifier
    .fillMaxWidth()
    .padding(8.dp)
    .horizontalScroll(rememberScrollState())

    text = playerName,
    fontWeight = FontWeight.Light,
    fontSize = 17.sp,
    modifier = Modifier
        .padding(bottom = 8.dp)
        .fillMaxWidth(),
    textAlign = TextAlign.Center
)


val cell = Modifier.padding(4.dp)


@Composable
fun RS() {
    MaterialTheme {
        Surface {
            // Your UI components go here
        }
    }
}