
class ShipUpgrades(private val ship: Ship) {
    private val logger = LoggerFactory.getLogger(ShipUpgrades::class.java)

    private lateinit var modernizations: List<Modernization>
    private lateinit var modernizationsBySlot: List<List<Modernization>>

    fun getModernizations(): List<Modernization> = modernizations
    fun getModernizationsBySlot(): List<List<Modernization>> = modernizationsBySlot

    fun unpackUpgrades() {
        val upgrades = GameRepository.instance.modernizationList
        val shipUpgrades = mutableListOf<Modernization>()
        var maxSlot = 0
        for (upgrade in upgrades) {
            if (upgrade.isFor(ship)) {
                shipUpgrades.add(upgrade)
                val slot = upgrade.slot
                if (slot > maxSlot) maxSlot = slot
            }
        }
        logger.info("Unpacked ${shipUpgrades.size} upgrades for ${ship.name}")
        require(shipUpgrades.size < 30) { "Too many upgrades for ${ship.name}" }

        modernizations = shipUpgrades
        modernizationsBySlot = unpackUpgradesBySlot(maxSlot + 1)
    }

    private fun unpackUpgradesBySlot(max: Int): List<List<Modernization>> {
        val slots = MutableList(max) { mutableListOf<Modernization>() }
        for (upgrade in modernizations) {
            slots[upgrade.slot].add(upgrade)
        }
        return slots
    }
}

    val modernizationsBySlot = mutableListOf<List<Modernization>>()
    for (slot in 0 until max) {
        println("Unpacking slot $slot")
        modernizationsBySlot.add(modernizations.filter { it.slot == slot })
    }
    return modernizationsBySlot
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
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}