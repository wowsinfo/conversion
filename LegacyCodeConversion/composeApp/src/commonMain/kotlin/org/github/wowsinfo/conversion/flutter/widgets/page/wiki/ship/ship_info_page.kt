
@Composable
fun ShipInfoPage(ship: Ship) {
    val shipInfoViewModel: ShipInfoViewModel = getViewModel()
    val scrollProvider: ScrollProvider = getViewModel()
    val similarShipProvider: SimilarShipProvider = getViewModel()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(ship.name) })
        Spacer(modifier = Modifier.height(8.dp))
        ShipIcon(ship = ship)
        ShipCell(ship = ship)
        ShipAdditionalBox(ship = ship)
        SimilarShipList(similarShips = similarShipProvider.getSimilarShips(ship))
        Button(onClick = { shipInfoViewModel.showModuleDialog(ship) }) {
            Text("Show Modules")
        }
        Button(onClick = { shipInfoViewModel.showPenetrationDialog(ship) }) {
            Text("Show Penetration")
        }
    }
}

@Composable
fun ShipIcon(ship: Ship) {
    // Implementation for displaying ship icon
}

@Composable
fun ShipCell(ship: Ship) {
    // Implementation for displaying ship details
}

@Composable
fun ShipAdditionalBox(ship: Ship) {
    // Implementation for displaying additional ship information
}

@Composable
fun SimilarShipList(similarShips: List<Ship>) {
    // Implementation for displaying a list of similar ships
}

class ShipInfoViewModel : ViewModel() {
    fun showModuleDialog(ship: Ship) {
        // Implementation for showing module dialog
    }

    fun showPenetrationDialog(ship: Ship) {
        // Implementation for showing penetration dialog
    }
}


data class Ship(val name: String)

class ShipInfoViewModel(ship: Ship) : ViewModel() {
    // Implement your logic here
}

class SimilarShipViewModel(ship: Ship) : ViewModel() {
    // Implement your logic here
}

class ScrollViewModel(private val scrollController: ScrollController, private val slideController: AnimationController) : ViewModel() {
    // Implement your logic here
}

@Composable
fun ShipInfoPage(ship: Ship) {
    val shipInfoViewModel: ShipInfoViewModel = viewModel(factory = ShipInfoViewModelFactory(ship))
    val similarShipViewModel: SimilarShipViewModel = viewModel(factory = SimilarShipViewModelFactory(ship))
    val scrollState = rememberScrollState()
    val slideController = remember { AnimationController() }
    val scrollViewModel = remember { ScrollViewModel(scrollState, slideController) }

    Box(modifier = Modifier.height(130.dp)) {
        // Your UI components go here
    }
}

fun ShipScreen(viewModel: ShipViewModel) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Column {
        TopAppBar(
            title = { Text(state.title) },
            backgroundColor = MaterialTheme.colors.primary
        )
        LazyColumn(state = scrollState) {
            item {
                ShipTitleSection(
                    icon = state.shipIcon,
                    name = state.shipNameWithTier,
                    region = state.region,
                    type = state.type,
                    costCR = state.costCR,
                    costGold = state.costGold,
                    additional = state.shipAdditional,
                    description = state.description
                )
            }
            if (state.canChangeModules) {
                item {
                    ShipModuleButton(
                        title = "Change Ship Modules",
                        shipModules = state.moduleList,
                        selection = state.selection
                    )
                }
            }
            if (state.renderHull) {
                item { ShipSurvivability() }
            }
            if (state.renderMainGun) {
                item { ShipMainBattery(ship = viewModel.ship) }
            }
            if (state.renderSpecials) {
                item { ShipSpecial() }
            }
            if (state.renderSecondaryGun) {
                item { ShipSecondaries() }
            }
            if (state.renderPinger) {
                item { ShipPinger() }
            }
            if (state.renderSubmarineBattery) {
                item { ShipBattery() }
            }
            if (state.renderTorpedo) {
                item { ShipTorpedo() }
            }
            if (state.renderAirDefense) {
                item { ShipAirDefense() }
            }
            if (state.renderAirSupport) {
                item { ShipAirSupport() }
            }
            if (state.renderDepthCharge) {
                item { ShipDepthCharge() }
            }
            if (state.renderMobility) {
                item { ShipMobility() }
            }
            if (state.renderVisibility) {
                item { ShipVisibility() }
            }
            if (state.hasUpgrades) {
                item { ShipUpgrades() }
            }
            if (state.hasNextShip) {
                item { ShipNextShip() }
            }
            if (state.hasConsumables) {
                item { ShipConsumables() }
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
        BottomNavigation {
            // Implement bottom navigation items here
        }
    }
}

fun SimilarShipsView(similarProvider: SimilarProvider, scrollProvider: ScrollProvider, ship: Ship) {
    if (!similarProvider.hasSimilarShips) return

    val display = scrollProvider.display
    val transitionState = remember { MutableTransitionState(display) }
    transitionState.targetState = display

    AnimatedVisibility(
        visibleState = transitionState,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (display) {
            SimilarShipList(source = ship, ships = similarProvider.similarShips)
        } else {
            Box(modifier = Modifier.size(0.dp))
        }
    }
}


@Composable
fun ShipTitleSection(
    icon: @Composable () -> Unit,
    name: String,
    region: String,
    type: String,
    costCR: Int? = null,
    costGold: Int? = null,
    additional: String? = null,
    description: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        icon()
        Text(text = name)
        Text(text = region)
        Text(text = type)
        costCR?.let { Text(text = "Cost CR: $it") }
        costGold?.let { Text(text = "Cost Gold: $it") }
        additional?.let { Text(text = "Additional: $it") }
        Text(text = description)
    }
}

fun ShipCard(
    icon: String,
    name: String,
    region: String,
    type: String,
    costCR: String?,
    costGold: String?,
    description: String,
    additional: ShipAdditional?
) {
    Card {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShipIcon(icon = icon, height = 128.dp, hero = true)
            Text(name, style = MaterialTheme.typography.h6)
            Text(region)
            Text(type)
            costCR?.let {
                Text(it, color = WoWsColours.creditPrice)
            }
            costGold?.let {
                Text(it, color = WoWsColours.goldPrice)
            }
            additional?.let {
                ShipAdditionalBox(shipAdditional = it)
            }
            Text(description, textAlign = TextAlign.Center)
        }
    }
}


@Composable
fun ShipModuleButton(
    title: String,
    shipModules: List<ShipModule>,
    selection: (ShipModule) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { 
                val selectedModule = remember { shipModules.first() } // Replace with actual selection logic
                selection(selectedModule) 
            }
    ) {
        Text(text = title)
    }
}

data class ShipModule(val id: String, val name: String)

fun ShipModuleButton(
    title: String,
    shipModules: ShipModuleMap,
    selection: ShipModuleSelection,
    shipInfoProvider: ShipInfoProvider
) {
    val context = LocalContext.current
    val padding = 16.dp

    Button(
        onClick = {
            showShipModuleDialog(
                context,
                shipModules,
                selection
            ) { newSelection ->
                shipInfoProvider.updateSelection(newSelection)
            }
        },
        modifier = Modifier.padding(horizontal = padding)
    ) {
        Text(text = title)
    }
}

fun ShipSurvivability() {
    val provider = LocalShipInfoProvider.current
    Card {
        Padding(padding = 8.dp) {
            Column {
                Text(
                    text = Localisation.instance.durability,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithCaption(
                        title = Localisation.instance.health,
                        value = provider.health.toString()
                    )
                    TextWithCaption(
                        title = Localisation.instance.torpedoProtection,
                        value = provider.torpedoProtection.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun TextWithCaption(title: String, value: String) {
    Column {
        Text(text = title)
        Text(text = value)
    }
}


@Composable
fun ShipMainBattery(ship: Ship) {
    val shipState = remember { ship }
    // Add your UI components here using Jetpack Compose
}

fun ShipInfoCard(ship: Ship, provider: ShipInfoProvider) {
    Card {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Localisation.instance.artillery,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextWithCaption(
                    title = Localisation.instance.gunReloadTime,
                    value = provider.gunReloadTime
                )
                TextWithCaption(
                    title = Localisation.instance.gunRange,
                    value = provider.gunRange
                )
                TextWithCaption(
                    title = Localisation.of(context).warship_weapon_configuration,
                    value = provider.gunConfiguration
                )
                TextWithCaption(
                    title = Localisation.instance.gunDispersion,
                    value = ""
                )
                TextWithCaption(
                    title = Localisation.instance.gunRotationTime,
                    value = provider.gunRotationTime
                )
                TextWithCaption(
                    title = "Sigma",
                    value = provider.gunSigma
                )
            }
            if (provider.hasBurstFire) {
                RenderBurstFire(context, provider.burstFireHolder)
            }
            Text(
                text = provider.gunName,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            TextButton(onClick = { showShipPenetrationDialog(context, ship) }) {
                Icon(Icons.Default.BarChart, contentDescription = null)
                Text("show ap penetration curve")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                provider.shells.forEach { shell ->
                    RenderShell(context, shell)
                }
            }
        }
    }
}

fun RenderShell(shell: ShellHolder) {
    Column {
        Text(text = shell.name, style = MaterialTheme.typography.h6)
        shell.burnChance?.let {
            TextWithCaption(
                title = Localisation.instance.shellFireChance,
                value = it
            )
        }
        shell.weight?.let {
            TextWithCaption(
                title = Localisation.instance.shellWeight,
                value = it
            )
        }
        shell.damage?.let {
            TextWithCaption(
                title = Localisation.instance.gunDamage,
                value = it
            )
        }
        shell.velocity?.let {
            TextWithCaption(
                title = Localisation.instance.shellVelocity,
                value = it
            )
        }
        shell.penetration?.let {
            TextWithCaption(
                title = Localisation.instance.shellPenetration,
                value = it
            )
        }
        shell.overmatch?.let {
            TextWithCaption(
                title = Localisation.instance.warship_weapon_ap_overmatch,
                value = it
            )
        }
    }
}

fun RenderBurstFire(holder: BurstFireHolder?) {
    if (holder == null) {
        throw IllegalArgumentException("Burst fire holder shouldn't be null here, not expected")
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Localisation.instance.burstFire,
            style = MaterialTheme.typography.h6
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextWithCaption(
                title = Localisation.instance.burstFireCount,
                value = holder.shots.toString()
            )
            TextWithCaption(
                title = Localisation.instance.burstFireInterval,
                value = holder.interval.toString()
            )
            TextWithCaption(
                title = Localisation.instance.burstFireReload,
                value = holder.reload.toString()
            )
        }
        holder.modifiers?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun ShipSecondaries() {
    val provider = LocalContext.current.get<ShipInfoProvider>()
    Card {
        Padding(Modifier.padding(8.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${Localisation.instance.secondaryBattery} (${provider.secondaryRange})",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                provider.secondaryGuns.forEach { gun ->
                    renderSecondaries(gun)
                }
            }
        }
    }
}

@Composable
fun renderSecondaries(gun: Gun) {
    // Implement the rendering logic for each secondary gun
}

fun RenderSecondaries(info: SecondaryGunHolder) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = info.name,
            style = MaterialTheme.typography.h6
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextWithCaption(
                title = Localisation.instance.gunReloadTime,
                value = info.reloadTime ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.shellVelocity,
                value = info.velocity ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.shellFireChance,
                value = info.burnChance ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.gunDamage,
                value = info.damage ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.shellPenetration,
                value = info.penetration ?: "-"
            )
        }
    }
}

fun ShipPinger() {
    val provider = LocalShipInfoProvider.current
    Card {
        Padding(Modifier.padding(8.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Localisation.instance.sonar,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextWithCaption(
                        title = Localisation.instance.reloadTime,
                        value = provider.pingerReloadTime
                    )
                    TextWithCaption(
                        title = Localisation.instance.pingerDuration,
                        value = provider.pingerDuration
                    )
                    TextWithCaption(
                        title = Localisation.instance.shellVelocity,
                        value = provider.pingerSpeed
                    )
                    TextWithCaption(
                        title = Localisation.instance.maximumRange,
                        value = provider.pingerRange
                    )
                }
            }
        }
    }
}

fun ShipBattery() {
    val provider = LocalShipInfoProvider.current
    Card {
        Padding(padding = 8.dp) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = Localisation.instance.diveCapacity,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextWithCaption(
                        title = Localisation.instance.batteryMaxCapacity,
                        value = provider.submarineBatteryCapacity
                    )
                    TextWithCaption(
                        title = Localisation.instance.batteryConsumption,
                        value = provider.submarineBatteryUseRate
                    )
                    TextWithCaption(
                        title = Localisation.instance.bateryRegen,
                        value = provider.submarineBatteryRegen
                    )
                }
            }
        }
    }
}

fun ShipTorpedo() {
    val provider = remember { ShipInfoProvider() }
    Card {
        Padding(padding = 8.dp) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = Localisation.instance.torpedoes,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextWithCaption(
                        title = Localisation.instance.torpedoReloadTime,
                        value = provider.torpedoReloadTime
                    )
                    TextWithCaption(
                        title = Localisation.instance.warship_weapon_configuration,
                        value = provider.torpedoConfiguration
                    )
                    TextWithCaption(
                        title = Localisation.instance.torpedoRotationTime,
                        value = provider.torpedoRotationTime
                    )
                }
                provider.torpedoes.forEach { torp ->
                    renderTorpedoInfo(torp)
                }
            }
        }
    }
}

@Composable
fun TextWithCaption(title: String, value: String) {
    Column {
        Text(text = title)
        Text(text = value)
    }
}

@Composable
fun renderTorpedoInfo(torp: Torpedo) {
    // Implement the rendering logic for each torpedo
}

fun RenderTorpedoInfo(info: TorpedoHolder) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${info.name} (${info.reactionTime ?: "-"})",
            style = MaterialTheme.typography.h6
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextWithCaption(
                title = Localisation.instance.torpedoDamage,
                value = info.damage ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.torpedoDetection,
                value = info.visibility ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.torpedoRange,
                value = info.range ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.torpedoSpeed,
                value = info.speed ?: "-"
            )
            TextWithCaption(
                title = Localisation.instance.floodChance,
                value = info.floodChance ?: "-"
            )
        }
    }
}

fun ShipAirDefense() {
    val provider = LocalContext.current.get<ShipInfoProvider>()
    Card {
        Padding(Modifier.padding(8.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = Localisation.instance.airDefense,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                provider.airBubbles.forEach { bubble ->
                    renderAirBubble(bubble)
                }
                provider.airDefenses.forEach { aa ->
                    renderAirDefense(aa)
                }
            }
        }
    }
}

fun RenderAirBubble(info: AirBubbleHolder) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextWithCaption(
            title = Localisation.instance.bubbleExplosion,
            value = info.explosions.toString()
        )
        TextWithCaption(
            title = Localisation.instance.gunDamage,
            value = info.damage.toString()
        )
        TextWithCaption(
            title = Localisation.instance.hitChance,
            value = info.hitChance.toString()
        )
        TextWithCaption(
            title = Localisation.instance.aaRange,
            value = info.range.toString()
        )
    }
}

fun renderAirDefense(info: AirDefenseHolder) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = info.name,
            style = MaterialTheme.typography.h6
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextWithCaption(
                title = Localisation.instance.gunDamage,
                value = info.damage
            )
            TextWithCaption(
                title = Localisation.instance.hitChance,
                value = info.hitChance
            )
            TextWithCaption(
                title = Localisation.instance.aaRange,
                value = info.range
            )
        }
    }
}

fun ShipAirSupport() {
    val provider = LocalShipInfoProvider.current
    Card {
        Padding(padding = 8.dp) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Localisation.instance.airSupport,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = provider.airSupportName,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextWithCaption(
                        title = Localisation.instance.reloadTime,
                        value = provider.airSupportReload
                    )
                    TextWithCaption(
                        title = Localisation.instance.availableFlights,
                        value = provider.airSupportCharges
                    )
                    TextWithCaption(
                        title = Localisation.instance.airSupportTotalPlanes,
                        value = provider.airSupportTotalPlanes
                    )
                    TextWithCaption(
                        title = Localisation.instance.planeHealth,
                        value = provider.airSupportPlaneHealth
                    )
                    TextWithCaption(
                        title = Localisation.instance.maximumRange,
                        value = provider.airSupportRange
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextWithCaption(
                        title = Localisation.instance.numberOfBombs,
                        value = provider.airSupportBombs
                    )
                    TextWithCaption(
                        title = Localisation.instance.bombDamage,
                        value = provider.airSupportBombDamage
                    )
                    if (provider.airSupportBombPeneration != "-") {
                        TextWithCaption(
                            title = Localisation.instance.shellPenetration,
                            value = provider.airSupportBombPeneration
                        )
                    }
                    if (provider.airSupportBombBurnChance != "-") {
                        TextWithCaption(
                            title = Localisation.instance.shellFireChance,
                            value = provider.airSupportBombBurnChance
                        )
                    }
                    if (provider.airSupportBombFloodChance != "-") {
                        TextWithCaption(
                            title = Localisation.instance.floodChance,
                            value = provider.airSupportBombFloodChance
                        )
                    }
                }
            }
        }
    }
}

fun ShipDepthCharge() {
    val provider = LocalShipInfoProvider.current
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Localisation.instance.depthCharge,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextWithCaption(
                title = Localisation.instance.bombDamage,
                value = provider.depthChargeDamage
            )
            TextWithCaption(
                title = Localisation.instance.warship_weapon_configuration,
                value = provider.depthChargeConfig
            )
            TextWithCaption(
                title = Localisation.instance.reloadTime,
                value = provider.depthChargeReload
            )
            TextWithCaption(
                title = Localisation.instance.shellFireChance,
                value = provider.depthChargeBurnChance
            )
            TextWithCaption(
                title = Localisation.instance.floodChance,
                value = provider.depthChargeFloodChance
            )
        }
    }
}

fun ShipSpecial(shipInfoProvider: ShipInfoProvider) {
    Card {
        Padding(padding = 8.dp) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = shipInfoProvider.specialName,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = shipInfoProvider.specialDescription,
                    textAlign = TextAlign.Center
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithCaption(
                        title = Localisation.instance.actionTime,
                        value = shipInfoProvider.specialDuration
                    )
                    TextWithCaption(
                        title = Localisation.instance.requiredHits,
                        value = shipInfoProvider.specialHitsRequired
                    )
                }
                Text(
                    text = shipInfoProvider.specialModifier,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

fun ShipMobility() {
    val provider = remember { ShipInfoProvider() }
    Card {
        Padding(padding = 8.dp) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Localisation.instance.mobility,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextWithCaption(
                        title = Localisation.instance.rudderTime,
                        value = provider.rudderTime
                    )
                    TextWithCaption(
                        title = Localisation.instance.maxSpeed,
                        value = provider.maxSpeed
                    )
                    TextWithCaption(
                        title = Localisation.instance.turningRadius,
                        value = provider.turninRadius
                    )
                }
            }
        }
    }
}

fun ShipVisibility() {
    val provider = LocalShipInfoProvider.current
    Card {
        Padding(padding = 8.dp) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Localisation.instance.visibility,
                    style = MaterialTheme.typography.h6
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithCaption(
                        title = Localisation.instance.airDetection,
                        value = provider.planeVisibility
                    )
                    TextWithCaption(
                        title = Localisation.instance.seaDetection,
                        value = provider.seaVisibility
                    )
                }
            }
        }
    }
}

@Composable
fun TextWithCaption(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title)
        Text(text = value)
    }
}

fun ShipUpgrades() {
    val provider = LocalContext.current.get<ShipInfoProvider>()
    Card {
        Column {
            Text(
                text = Localisation.instance.upgrades,
                style = MaterialTheme.typography.h6
            )
            HorizontalScroll {
                Row {
                    provider.upgrades.forEach { slots ->
                        require(slots.isNotEmpty()) { "There should be at least one slot" }
                        val slotNumber = slots[0].slot + 1
                        Column {
                            Text(text = slotNumber.toString())
                            slots.forEach { upgrade ->
                                renderUpgrade(upgrade)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalScroll(content: @Composable () -> Unit) {
    // Implementation for horizontal scrolling
    Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        content()
    }
}

@Composable
fun renderUpgrade(upgrade: Upgrade) {
    // Implementation for rendering an upgrade
}

fun renderUpgrade(upgrade: Modernization) {
    val name = upgrade.icon
    Image(
        painter = rememberImagePainter("data/live/app/assets/upgrades/$name.png"),
        contentDescription = null
    )
}

fun ShipNextShip() {
    val provider = LocalShipInfoProvider.current
    Card {
        Column {
            Text(
                text = Localisation.instance.nextShip,
                style = MaterialTheme.typography.h6
            )
            HorizontalScroll {
                Row {
                    provider.nextShips.forEach { ship ->
                        renderNextShip(ship)
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalScroll(content: @Composable () -> Unit) {
    ScrollableRow {
        content()
    }
}

@Composable
fun renderNextShip(ship: Ship) {
    // Implementation for rendering the ship
}

fun RenderNextShip(nextShip: Ship?) {
    requireNotNull(nextShip) { "Next ship should not be null" }
    
    ShipCell(
        icon = nextShip.index,
        name = Localisation.instance.stringOf(nextShip.name) ?: "-",
        isPremium = nextShip.isPremium,
        isSpecial = nextShip.isSpecial,
        onClick = {
            // Assuming you have a NavController set up
            navController.navigate("shipInfo/${nextShip.id}")
        }
    )
}

fun ShipConsumables() {
    val provider = LocalShipInfoProvider.current
    Card {
        Column {
            Text(
                text = Localisation.instance.consumables,
                style = MaterialTheme.typography.h6
            )
            HorizontalScroller {
                Row {
                    provider.consumables.forEach { consumables ->
                        require(consumables.isNotEmpty()) { "There should be at least one consumable" }
                        Column {
                            consumables.forEach { consumable ->
                                RenderConsumable(consumable)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RenderConsumable(consumable: Consumable) {
    // Implementation for rendering a single consumable
}

fun RenderConsumable(consumable: Consumable) {
    val name = consumable.name
    val type = consumable.type
    val info = GameRepository.instance.abilityOf(name) ?: run {
        assert(false) { "Consumable is not found" }
        return
    }

    val ability = info.abilities[type]

    val icon: String
    val description: String
    val displayName: String

    val iconID = ability?.iconIDs
    if (iconID == null) {
        icon = name
        description = Localisation.instance.stringOf(info.description) ?: ""
        displayName = Localisation.instance.stringOf(info.name) ?: ""
    } else {
        icon = iconID
        val alter = info.alter?.get(iconID) ?: run {
            assert(false) { "Alter isn't found in the ability" }
            return
        }
        description = Localisation.instance.stringOf(alter.description) ?: ""
        displayName = Localisation.instance.stringOf(alter.name) ?: ""
    }

    val abilityModifier = ability.toString()
    val consumableCount = ability?.consumableCount

    Column {
        Image(
            painter = painterResource("data/live/app/assets/consumables/$icon.png"),
            contentDescription = null
        )
        if (consumableCount != null) {
            Text(consumableCount)
        }
        Modifier.clickable {
            AlertDialog(
                onDismissRequest = {},
                title = { Text(displayName) },
                text = { Text("$description\n\n$abilityModifier") },
                confirmButton = {
                    Button(onClick = {}) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            count++
        }) {
            Text("Click me")
        }
    }
}