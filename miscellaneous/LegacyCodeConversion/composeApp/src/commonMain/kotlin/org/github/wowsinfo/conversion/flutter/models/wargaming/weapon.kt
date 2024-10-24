typealias SecondaryBattery = Weapon
typealias RamAttack = Weapon
typealias Torpedoe = Weapon
typealias AttackAircraft = Weapon

data class Weapon(
    val maxFragsBattle: Int? = null,
    val frags: Int? = null,
    val hits: Int? = null,
    val maxFragsShipId: Int? = null,
    val shots: Int? = null
)

    val maxFragsBattle: Int? = null,
    val frags: Int? = null,
    val hits: Int? = null,
    val maxFragsShipId: Int? = null,
    val shots: Int? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): Weapon {
            return Weapon(
                maxFragsBattle = json["max_frags_battle"] as? Int,
                frags = json["frags"] as? Int,
                hits = json["hits"] as? Int,
                maxFragsShipId = json["max_frags_ship_id"] as? Int,
                shots = json["shots"] as? Int
            )
        }
    }

    val hasHitRatio: Boolean
        get() = shots != null && hits != null && hits!! > 0

    val hitRatio: Double
        get() = if (hasHitRatio) hits!! * 10000.0 / shots!! / 100.0 else Double.NaN

    val maxFrag: String
        get() = maxFragsBattle.toString()

    val totalFrag: String
        get() = frags.toString()

    override fun toString(): String {
        return "Weapon(maxFragsBattle=$maxFragsBattle, frag=$frags, hits=$hits, maxFragsShipId=$maxFragsShipId, shot=$shots)"
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
        Text(text = "You have clicked the button $count times.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click Me")
        }
    }
}