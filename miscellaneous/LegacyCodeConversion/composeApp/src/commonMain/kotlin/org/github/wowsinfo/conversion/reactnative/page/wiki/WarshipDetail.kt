
@Composable
fun WarshipDetail(item: Warship) {
    val coroutineScope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(true) }
    var similarShips by remember { mutableStateOf(listOf<Warship>()) }
    var currentShip by remember { mutableStateOf(item) }

    LaunchedEffect(currentShip.shipId) {
        loading = true
        similarShips = fetchSimilarShips(currentShip)
        loading = false
    }

    if (loading) {
        LoadingIndicator()
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = currentShip.name, style = MaterialTheme.typography.h5)
            // Other ship details here

            SectionTitle("Similar Ships")
            LazyColumn {
                items(similarShips) { ship ->
                    WarshipCell(ship)
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(text = title, style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp))
}

@Composable
fun WarshipCell(ship: Warship) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = ship.name, style = MaterialTheme.typography.body1)
            // Other ship info here
        }
    }
}

fun fetchSimilarShips(currentShip: Warship): List<Warship> {
    val warships = AppGlobalData.get(SAVED.warship)
    return warships.filter { 
        it.tier == currentShip.tier && 
        it.type == currentShip.type && 
        it.shipId != currentShip.shipId 
    }
}

data class Warship(val shipId: String, val name: String, val tier: Int, val type: String)


@Composable
fun MyComponent(similar: List<SimilarType>) {
    LaunchedEffect(similar) {
        buildCharts(similar)
    }
}

fun buildCharts(similar: List<SimilarType>) {
    // Implementation of chart building logic
}

    private var module: Module? = null
    private var data: Data? = null
    private var loading: Boolean = false

    override fun componentDidUpdate() {
        val currentModule = props.module
        if (currentModule != null) {
            if (this.module == currentModule) {
                return
            }
            loading = true
            this.module = currentModule
            getNewModule(currentModule).then { json ->
                val newModule = Guard(json, "data.${currentModule.shipId}", null)
                if (newModule != null) {
                    val newData = data?.copy() ?: Data()
                    newData.defaultProfile = newModule
                    loading = false
                    data = newData
                    println("Module updated")
                }
            }
        }
    }

    private fun getNewModule(module: Module): Promise<Json> {
        // Implementation for fetching new module data
    }
}

    val (shipId, module) = data
    val (artillery, diveBomber, engine, fighter, flightControl, hull, suo, torpedoBomber, torpedoes) = module

    println(module)

    SafeFetch.get(
        WoWsAPI.ShipModule,
        server,
        shipId,
        artillery,
        diveBomber,
        engine,
        fighter,
        suo,
        flightControl,
        hull,
        torpedoBomber,
        torpedoes,
        langStr()
    )
}

fun ShipInfo(curr: Ship?, similar: List<Ship>) {
    if (curr != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            WoWsInfo(title = "${curr.shipIdStr} ${curr.shipId}") {
                ScrollableColumn(modifier = Modifier.weight(1f)) {
                    AnimatedVisibility(visible = true) {
                        // Replace with appropriate animation
                        WikiIcon(warship = curr, scale = 3f)
                    }
                    renderContent(curr)
                }
                renderSimilar(similar)
            }
        }
    } else {
        // Handle the case where curr is null
    }
}

fun RenderContent(curr: CurrentType, loading: Boolean, data: DataType) {
    if (loading) {
        LoadingIndicator()
    } else {
        Column {
            RenderBasic(curr, data)
            RenderAll(data)
        }
    }
}

fun RenderBasic(curr: Ship, data: ShipData) {
    val container = styles.container
    val horizontal = styles.horizontal
    val shipTitle = styles.shipTitle
    val centerText = styles.centerText
    val modelBtn = styles.modelBtn
    val name = curr.name
    val model = curr.model
    val type = curr.type
    val nation = curr.nation
    val shipId = curr.ship_id
    val description = data.description

    val currShip = AppGlobalData.get(SAVED.pr)[shipId]
    val avgDamage = Guard(currShip, "average_damage_dealt", 0)
    val avgWinrate = Guard(currShip, "win_rate", 0)
    val avgFrag = Guard(currShip, "average_frags", 0)

    Column(modifier = container) {
        Text(text = name, style = shipTitle.copy(marginTop = 8.dp))
        if (currShip != null) {
            Row(modifier = horizontal.then(Modifier.padding(bottom = 16.dp))) {
                InfoLabel(title = lang.warship_avg_damage, info = avgDamage.toInt().toString())
                InfoLabel(title = lang.warship_avg_winrate, info = "${avgWinrate.toString().toDouble().format(1)}%")
                InfoLabel(title = lang.warship_avg_frag, info = avgFrag.toString().format(2))
            }
        }
        Text(text = nation.uppercase())
        Text(text = type)
        PriceLabel(item = data)
        if (model != null) {
            Button(
                onClick = {
                    SimpleViewHandler.openURL("https://sketchfab.com/models/$model/embed?autostart=1&preload=1")
                },
                modifier = modelBtn
            ) {
                Text(text = lang.warship_model)
            }
        }
        Text(text = description, style = centerText.then(Modifier.padding(vertical = 16.dp)))
    }
}

fun RenderAll(curr: YourDataType) {
    val module = Guard(curr, "modules", emptyMap<String, List<Any>>())
    var hasModule = false
    for ((id, currModule) in module) {
        if (currModule.size > 1) {
            hasModule = true
            break
        }
    }

    Column {
        RenderStatus(Guard(curr, "default_profile", null))
        if (hasModule) {
            Button(
                onClick = { SafeAction("WarshipModule", mapOf("data" to curr)) },
                shape = MaterialTheme.shapes.small
            ) {
                Text(lang.warship_update_module)
            }
        }
        RenderSurvivability(curr)
        RenderMainBattery(Guard(curr, "default_profile.artillery", null))
        RenderSecondary(Guard(curr, "default_profile.atbas", null))
        RenderTorpedo(Guard(curr, "default_profile.torpedoes", null))
        RenderAADefense(Guard(curr, "default_profile.anti_aircraft", null))
        RenderMobility(Guard(curr, "default_profile.mobility", null))
        RenderConcealment(Guard(curr, "default_profile.concealment", null))
        RenderUpgrade(curr)
        RenderNextShip(Guard(curr, "next_ships"), null)
    }
}

fun RenderStatus(profile: Profile?) {
    if (profile == null) {
        return
    }
    WarshipStat(profile = profile)
}

fun RenderSurvivability(curr: CurrentData?) {
    if (curr == null) {
        return
    }
    val armour = Guard(curr, "default_profile.armour", null)
    val tier = Guard(curr, "tier", null)

    val floodProb = armour.flood_prob
    val range = armour.range
    val health = armour.health

    Column(modifier = Modifier.padding(16.dp)) {
        SectionTitle(title = lang.warship_survivability)
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoLabel(
                title = lang.warship_survivability_health,
                info = "${health} - ${health + tier * 350}"
            )
            InfoLabel(
                title = lang.warship_survivability_armour,
                info = "${range.min} - ${range.max} mm"
            )
            if (floodProb != 0) {
                InfoLabel(
                    title = lang.warship_survivability_protection,
                    info = "${floodProb}%"
                )
            }
        }
    }
}

fun RenderMainBattery(artillery: Artillery?) {
    if (artillery == null) {
        return
    }
    val horizontal = styles.horizontal
    val centerText = styles.centerText
    val (maxDispersion, gunRate, distance, rotationTime, slots, shells) = artillery
    val (AP, HE) = shells

    var mainGun = ""
    var gunName = ""
    for (gun in slots) {
        mainGun += "${gun.guns} x ${gun.barrels}  "
    }
    gunName = slots.last().name

    val caliber = gunName.split(" ")[0].toInt()
    var fireRate = if (HE != null) {
        val baseFireRate = if (caliber > 160) 4 else 3
        val oneFourth = (caliber / 4.0).format(1)
        val oneFifth = (caliber / 5.0).format(1)
        val oneSixth = (caliber / 6.0).format(1)
        val penetration = """
            1/6 | $oneSixth - ${(oneSixth.toDouble() * 1.25).format(1)} mm
            1/5 | $oneFifth - ${(oneFifth.toDouble() * 1.25).format(1)} mm
            1/4 | $oneFourth - ${(oneFourth.toDouble() * 1.25).format(1)} mm
        """.trimIndent()
        fireRate += HE.burnProbability
        Pair(fireRate, penetration)
    } else {
        Pair(0, "")
    }

    val overmatch = if (AP != null) "${(caliber / 14.3).format(2)} mm\n" else ""

    val reload = (60 / gunRate).format(1)
    val re1 = if (caliber <= 139) 0.9 else 1.0
    val re2 = if (upgrades.contains(4280471472)) 0.88 else 1.0
    val bestReload = (60 / gunRate * re1 * re2).format(1)
    val reloadMsg = if (reload == bestReload) "$reload s" else "$reload - $bestReload s"

    val range = distance.format(1)
    val ra1 = if (caliber <= 139) 1.2 else 1.0
    val ra2 = if (upgrades.contains(4278374320)) 1.16 else 1.0
    val bestRange = (distance * ra1 * ra2).format(1)
    val rangeMsg = if (range == bestRange) "$range km" else "$range - $bestRange km"

    Column(modifier = Modifier.padding(16.dp)) {
        SectionTitle(title = lang.warship_artillery_main)
        Row(modifier = horizontal) {
            InfoLabel(title = lang.warship_weapon_reload, info = reloadMsg)
            InfoLabel(title = lang.warship_weapon_range, info = rangeMsg)
            InfoLabel(title = lang.warship_weapon_configuration, info = mainGun)
        }
        Row(modifier = horizontal) {
            InfoLabel(title = lang.warship_weapon_dispersion, info = "$maxDispersion m")
            InfoLabel(title = lang.warship_weapon_rotation, info = "$rotationTime s")
        }
        Text(text = gunName, style = centerText)
        Row(modifier = horizontal) {
            HE?.let {
                Column {
                    Text(text = "HE", style = centerText)
                    InfoLabel(title = lang.warship_weapon_fire_chance, info = "🔥${it.burnProbability} - $fireRate%")
                    InfoLabel(title = lang.warship_artillery_main_weight, info = "${it.bulletMass} kg")
                    InfoLabel(title = lang.warship_weapon_damage, info = "${it.damage}")
                    InfoLabel(title = lang.warship_weapon_speed, info = "${it.bulletSpeed} m/s")
                    InfoLabel(title = lang.warship_weapon_he_penetration, info = fireRate.second)
                }
            }
            AP?.let {
                Column {
                    Text(text = "AP", style = centerText)
                    InfoLabel(title = lang.warship_weapon_fire_chance, info = "0%🔥")
                    InfoLabel(title = lang.warship_artillery_main_weight, info = "${it.bulletMass} kg")
                    InfoLabel(title = lang.warship_weapon_damage, info = "${it.damage}")
                    InfoLabel(title = lang.warship_weapon_speed, info = "${it.bulletSpeed} m/s")
                    InfoLabel(title = lang.warship_weapon_ap_overmatch, info = overmatch)
                }
            }
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun RenderSecondary(secondary: Secondary?) {
    if (secondary == null) {
        return
    }
    val horizontal = styles.horizontal
    val centerText = styles.centerText
    val distance = secondary.distance
    val slots = secondary.slots

    val guns = slots.values.toList()
    Column(modifier = Modifier.padding(styles.margin)) {
        SectionTitle(title = "${lang.warship_artillery_secondary} ($distance km)")
        guns.forEachIndexed { index, value ->
            val burnProbability = value.burn_probability
            val bulletSpeed = value.bullet_speed
            val name = value.name
            val gunRate = value.gun_rate
            val damage = value.damage
            val type = value.type
            Column(key = index) {
                Text(text = "$type - $name", style = centerText)
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.warship_weapon_reload,
                        info = "${(60 / gunRate).toFixed(1)} s"
                    )
                    InfoLabel(
                        title = lang.warship_weapon_speed,
                        info = "$bulletSpeed m/s"
                    )
                    burnProbability?.let {
                        InfoLabel(
                            title = lang.warship_weapon_fire_chance,
                            info = "🔥$it%"
                        )
                    }
                    InfoLabel(title = lang.warship_weapon_damage, info = damage)
                }
            }
        }
    }
}

fun RenderAircraft(profile: Profile) {
    val basicTextStyle = TextStyle() // Define your text style here
    val flightControl = profile.flightControl

    if (flightControl != null) {
        val fighterSquadrons = flightControl.fighterSquadrons
        val torpedoSquadrons = flightControl.torpedoSquadrons
        val bomberSquadrons = flightControl.bomberSquadrons

        Column(modifier = Modifier.padding(16.dp)) {
            RenderTitle(language.detail_aircraft)
            RenderFighter()
            RenderTorpedoBomber()
            RenderBomber()
            Text(
                text = "$fighterSquadrons - $torpedoSquadrons - $bomberSquadrons",
                style = basicTextStyle
            )
        }
    }
}

fun RenderFighter(profile: ProfileState) {
    val basicTitleStyle = TextStyle(/* define your text style here */)
    val fighters = profile.fighters
    if (fighters != null) {
        Column(modifier = Modifier.padding(/* define your margin here */)) {
            Text(text = stringResource(id = R.string.aircraft_fighter), style = basicTitleStyle)
        }
    }
}

fun RenderTorpedoBomber(profile: Profile, language: Language) {
    val torpedoBomber = profile.torpedoBomber
    if (torpedoBomber != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = language.aircraftTorpedoBomber,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

fun RenderBomber(profile: Profile, language: Language) {
    val diveBomber = profile.diveBomber
    if (diveBomber != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = language.aircraftBomber, style = basicTitleStyle)
        }
    }
}

fun RenderTorpedo(torpedoes: Torpedoes?) {
    if (torpedoes == null) {
        return
    }
    val horizontal = styles.horizontal
    val centerText = styles.centerText
    val (visibilityDist, distance, torpedoName, reloadTime, torpedoSpeed, slots, maxDamage) = torpedoes

    val dist = String.format("%.1f", distance)
    val torps = slots.joinToString("  ") { "${it.guns} x ${it.barrels}" }
    val reactionTime = String.format("%.1f", (visibilityDist * 1000) / 2.6 / torpedoSpeed)

    val shortDist = String.format("%.1f", distance * 0.8)
    val fastestSpeed = String.format("%.1f", (torpedoSpeed + 5) * 1.05)
    val reactionTimeP = String.format("%.1f", (visibilityDist * 1000) / 2.6 / fastestSpeed)

    val modifier = if (upgrades.contains(4279422896)) 0.85 else 1.0
    val minReload = String.format("%.1f", reloadTime * 0.9 * modifier)

    Column(modifier = Modifier.padding(16.dp)) {
        SectionTitle(title = lang.warship_torpedoes)
        Row(modifier = horizontal) {
            InfoLabel(title = lang.warship_weapon_reload, info = "$reloadTime - $minReload s")
            InfoLabel(title = lang.warship_weapon_range, info = "$dist - $shortDist km")
            InfoLabel(title = lang.warship_weapon_configuration, info = torps)
        }
        Text(
            text = "$torpedoName ($reactionTime - $reactionTimeP s)",
            style = centerText
        )
        Row(modifier = horizontal) {
            InfoLabel(title = lang.warship_torpedoes_visible_distance, info = "$visibilityDist km")
            InfoLabel(title = lang.warship_weapon_damage, info = maxDamage.toString())
            InfoLabel(title = lang.warship_weapon_speed, info = "$torpedoSpeed - $fastestSpeed kt")
        }
    }
}

fun RenderAADefense(antiAircraft: AntiAircraft?) {
    if (antiAircraft == null) {
        return
    }
    val horizontalModifier = Modifier.fillMaxWidth().padding(8.dp)
    val centerTextModifier = Modifier.align(Alignment.CenterHorizontally)
    val slots = antiAircraft.slots

    val aaValues = slots.values.toList()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.warship_antiaircraft), style = MaterialTheme.typography.h6)
        aaValues.forEachIndexed { index, value ->
            val avgDamage = value.avgDamage
            val name = value.name
            val guns = value.guns
            Column(key = index) {
                Text(text = name, modifier = centerTextModifier, style = MaterialTheme.typography.h6)
                Row(modifier = horizontalModifier) {
                    InfoLabel(title = stringResource(id = R.string.warship_weapon_configuration), info = "${guns}x")
                    InfoLabel(title = stringResource(id = R.string.warship_weapon_damage), info = "${avgDamage} dps")
                }
            }
        }
    }
}

fun RenderMobility(mobility: Mobility?) {
    if (mobility == null) {
        return
    }

    val horizontalModifier = Modifier.fillMaxWidth().padding(8.dp)
    val (rudderTime, turningRadius, maxSpeed) = mobility

    val speedFlag = (maxSpeed * 1.05).toInt()

    val m1 = if (upgrades.contains(4267888560)) 0.8 else 1.0
    val m2 = if (upgrades.contains(4257402800)) 0.6 else 1.0
    val modifier = m1 + m2 - 1
    val maxRudder = (rudderTime * modifier).toString().take(4)
    val rudderMsg = if (maxRudder == rudderTime.toString()) {
        "${rudderTime} s"
    } else {
        "$rudderTime - $maxRudder s"
    }

    Column(modifier = Modifier.padding(16.dp)) {
        SectionTitle(title = stringResource(R.string.warship_maneuverability))
        Row(modifier = horizontalModifier) {
            InfoLabel(
                title = stringResource(R.string.warship_maneuverability_rudder_time),
                info = rudderMsg
            )
            InfoLabel(
                title = stringResource(R.string.warship_maneuverability_speed),
                info = "$maxSpeed - $speedFlag kt"
            )
            InfoLabel(
                title = stringResource(R.string.warship_maneuverability_turning),
                info = "$turningRadius m"
            )
        }
    }
}

fun RenderConcealment(concealment: Concealment?) {
    if (concealment == null) {
        return
    }

    val horizontal = styles.horizontal
    val detectDistanceByPlane = concealment.detectDistanceByPlane
    val detectDistanceByShip = concealment.detectDistanceByShip

    val modifier = if (upgrades.contains(4265791408)) 0.9 else 1.0
    val camouflage = 0.97
    val deduction = 0.9 * modifier * camouflage
    val maxShipConcealment = (detectDistanceByShip * deduction).format(1)
    val maxPlaneConcealment = (detectDistanceByPlane * deduction).format(1)

    Column(modifier = Modifier.padding(styles.margin)) {
        SectionTitle(title = lang.warshipConcealment)
        Row(modifier = horizontal) {
            InfoLabel(
                title = lang.warshipConcealmentDetectByPlane,
                info = "$detectDistanceByPlane - $maxPlaneConcealment km"
            )
            InfoLabel(
                title = lang.warshipConcealmentDetectByShip,
                info = "$detectDistanceByShip - $maxShipConcealment km"
            )
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun RenderUpgrade(curr: CurrentType?) {
    if (curr == null) {
        return
    }
    val upgrades = Guard(curr, "upgrades", null)
    val slots = Guard(curr, "mod_slots", null)
    if (upgrades == null || slots == null) {
        return
    }

    val clone = upgrades.toMutableList()
    clone.sortByDescending { it } // Assuming the items are comparable
    for (index in clone.indices) {
        val id = clone[index]
        clone[index] = AppGlobalData.get(SAVED.consumable)[id]!!
    }

    val count = List(slots) { it }

    val upgradeView = styles.upgradeView
    Column(modifier = Modifier.padding(styles.margin)) {
        SectionTitle(title = lang.warship_upgrades)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(count) { num ->
                    val all = clone.filter { it.slot == num + 1 }
                    Column(modifier = upgradeView) {
                        Text(text = "${num + 1}.", modifier = Modifier.padding(styles.margin))
                        all.forEach { item ->
                            WikiIcon(
                                item = item,
                                scale = 0.8f,
                                onClick = { SafeAction("BasicDetail", mapOf("item" to item)) }
                            )
                        }
                    }
                }
            }
        )
    }
}

fun RenderNextShip(nextShips: Map<String, Any>?) {
    if (nextShips.isNullOrEmpty()) {
        return
    }
    val ships = nextShips.map { (key, exp) -> Ship(key, exp) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.warship_next_ship), style = MaterialTheme.typography.h6)
        LazyRow {
            items(ships) { item ->
                val curr = AppGlobalData.get(SAVED.warship)[item.key]
                WarshipCell(
                    scale = 1.4f,
                    item = curr,
                    onClick = {
                        // Pop and go to another ships
                        Actions.pop()
                        SafeAction("WarshipDetail", mapOf("item" to curr), 1)
                    }
                )
            }
        }
    }
}

data class Ship(val key: String, val exp: Any)

fun RenderSimilar(similar: List<Ship>, compare: CompareData?) {
    if (similar.isNotEmpty()) {
        Column {
            LazyRow {
                items(similar) { item ->
                    WarshipCell(item = item, scale = 1.4) {
                        setState { curr = item; loading = true }
                        efficientDataRequest(item.shipId)
                    }
                }
            }
            compare?.let {
                Button(onClick = { SafeAction("SimilarGraph", mapOf("info" to compare)) }) {
                    Text(text = lang.warship_compare_similar)
                }
            }
        }
    }
}

    val damageChart = mutableListOf<ChartData>()
    val winrateChart = mutableListOf<ChartData>()
    val fragChart = mutableListOf<ChartData>()

    for (ship in similar) {
        val overall = AppGlobalData.get(SAVED.pr, ship.ship_id) ?: continue

        val averageDamageDealt = overall.average_damage_dealt
        val averageFrags = overall.average_frags
        val winRate = overall.win_rate
        val name = ship.name

        damageChart.add(ChartData(name, roundTo(averageDamageDealt)))
        winrateChart.add(ChartData(name, roundTo(winRate, 1)))
        fragChart.add(ChartData(name, roundTo(averageFrags, 2)))
    }

    val data = listOf(
        ChartInfo(lang.warship_avg_damage, damageChart, Color(0xFF2387FF)),
        ChartInfo(lang.warship_avg_winrate, winrateChart, Color(0xFF4DA74D)),
        ChartInfo(lang.warship_avg_frag, fragChart, Color(0xFFC94A4D))
    )

    val charts = data.map { c ->
        val names = c.data.map { it.name }
        val values = c.data.map { it.value }
        Column {
            SectionTitle(center = true, title = c.name)
            HorizontalBarChart(
                modifier = Modifier.height((names.size * 20).dp),
                chartData = values,
                xAxisLabels = names,
                darkMode = AppGlobalData.isDarkMode,
                themeColor = c.color
            )
        }
    }
    setState { compare = charts }
}


class YourClass {
    private var delayedRequestJob: Job? = null
    var upgrades: List<Any> = emptyList()
    var data: Any? = null
    var loading: Boolean = true

    fun efficientDataRequest(id: String) {
        delayedRequestJob?.cancel()
        delayedRequestJob = GlobalScope.launch {
            delay(1000)
            val json = SafeFetch.get(WoWsAPI.ShipWiki, server, id, langStr())
            val data = Guard(json, "data", emptyMap())
            println(data)
            upgrades = Guard(data[id], "upgrades", emptyList())
            setState(data[id], false)
        }
    }

    private fun setState(data: Any?, loading: Boolean) {
        this.data = data
        this.loading = loading
    }
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Add your content here
    }
}


@Composable
fun ShipTitle() {
    Text(
        text = "Your Ship Title",
        fontSize = 24.sp
    )
}

    onClick = { /* Handle button click */ },
    modifier = Modifier
        .padding(top = 8.dp)
) {
    Text("Button")
}




@Composable
fun CenteredText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}


@Composable
fun WeaponTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Weapon Title",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}


@Composable
fun UpgradeView() {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Your content goes here
    }
}


@Composable
fun HorizontalLayout() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Add your composable items here
    }
}


@Composable
fun GraphTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Graph Title", modifier = Modifier.padding(bottom = 0.dp))
    }
}


@Composable
fun WarshipDetail() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Warship Details", style = MaterialTheme.typography.h6)
        // Add more UI elements here as needed
    }
}