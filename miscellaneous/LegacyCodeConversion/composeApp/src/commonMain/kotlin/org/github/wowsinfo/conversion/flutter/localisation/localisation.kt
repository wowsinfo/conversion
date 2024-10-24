
@Serializable
data class LocalisationData(val strings: Map<String, String>)

object Localisation {
    private lateinit var localisations: LocalisationData

    fun loadLocalisations(context: Context) {
        val jsonString = context.assets.open("localisations.json").bufferedReader().use { it.readText() }
        localisations = Json.decodeFromString(jsonString)
    }

    @Composable
    fun of(key: String): String {
        val context = LocalContext.current
        return remember { localisations.strings[key] ?: throw Exception("Localisation key not found: $key") }
    }
}


object Localisation {
    private val validGameLanguages = setOf("en", "fr", "es", "de", "it") // Add your valid languages here

    suspend fun decideLang(customLang: String? = null): String {
        val logger = LoggerFactory.getLogger("Localisation|decideLang")
        val lang = customLang ?: currentLocale.languageCode ?: "en"
        logger.info("System locale is $lang")
        val langCode = lang.lowercase()

        if (validGameLanguages.contains(langCode)) {
            return langCode
        }

        if ('_' in langCode) {
            val localeCode = langCode.split('_')[0]
            if (validGameLanguages.contains(localeCode)) {
                logger.info("Using locale `$localeCode`")
                return localeCode
            }
        }

        logger.warn("Unsupported locale $langCode, falling back to en")
        return "en"
    }
}


object Localisation {
    private val logger = LoggerFactory.getLogger(Localisation::class.java)
    private var initialised = false
    private lateinit var lang: Map<String, Map<String, String>>
    private lateinit var gameLang: String

    val validGameLanguages = listOf(
        "cs", "de", "en", "es", "es_mx", "fr", "it", "ja", "ko", "nl", "pl", "pt", "pt_br", "ru", "th", "tr", "uk", "zh", "zh_sg", "zh_tw"
    )

    val supportedGameLanguages: List<String>
        get() = lang.keys.toList()

    suspend fun initialise() {
        if (initialised) {
            logger.error("Localisation already initialised")
            throw Exception("Localisation is already initialised")
        }

        val langString = withContext(Dispatchers.IO) {
            // Load the language file from assets or resources
            // Replace with actual loading logic
            loadLangFile("data/live/app/lang/lang.json")
        }

        val langObject = Json.decodeFromString<JsonObject>(langString)
        lang = langObject.mapValues { (_, value) ->
            value.jsonObject.mapValues { it.value.jsonPrimitive.content }
        }

        gameLang = decideLang()
        initialised = true
        logger.info("Localisation initialised")
    }

    private fun loadLangFile(filePath: String): String {
        // Implement the logic to load the file from the specified path
        return ""
    }

    private fun decideLang(): String {
        // Implement the logic to decide the language
        return "en" // Default language
    }
}

    if (!validGameLanguages.contains(language)) {
        logger.severe("Invalid language $language")
        return
    }
    if (lang[language] == null) {
        logger.warning("Language $language is not supported")
        return
    }

    logger.info("Updating data language to $language")
    gameLang = language
}

    val keys = listOf("exampleKey1", "exampleKey2", "exampleKey3") // Replace with actual keys
    return keys.filter { key ->
        key.contains(search) && (prefix == null || key.startsWith(prefix))
    }
}

    var searchUpper = search.uppercase()
    if (prefix != null) {
        searchUpper = prefix + searchUpper
    }

    val result = mutableListOf<String>()
    val langKeys = langMap[gameLang]?.keys
    if (langKeys == null) return emptyList()
    for (key in langKeys) {
        if (key.contains(searchUpper)) result.add(key)
    }
    return result
}

    private var _initialised: Boolean = false
    private val _logger = Logger.getLogger(Localisation::class.java.name)
    private val _lang: MutableMap<String, Map<String, String>> = mutableMapOf()
    private var _gameLang: String = ""

    fun get(key: String): String? {
        if (!_initialised) {
            _logger.severe("Localisation not initialised")
            return null
        }
        if (_lang[_gameLang] == null) {
            _logger.warning("Language $_gameLang is not supported")
            return null
        }
        if (_lang[_gameLang]?.get(key) == null) {
            _logger.warning("Key $key is not supported")
            return null
        }
        return _lang[_gameLang]?.get(key)
    }
}

    key: String?,
    constants: Map<String, Any>? = null,
    prefix: String? = null
): String? {
    val fullKey = if (prefix != null) "$prefix.$key" else key
    val localizedString = getLocalizedString(fullKey)
    return formatString(localizedString, constants)
}

fun getLocalizedString(key: String?): String? {
    // Implementation to retrieve the localized string based on the key
}

fun formatString(localizedString: String?, constants: Map<String, Any>?): String? {
    // Implementation to format the string with the given constants
}

    if (key == null) {
        logger.severe("Key is null")
        return null
    }

    if (lang[gameLang] == null) {
        logger.severe("Language $gameLang not found")
        return null
    }

    var langKey = key.uppercase()
    if (prefix != null) {
        langKey = prefix + langKey
    }
    val rawString = lang[gameLang]?.get(langKey)
    if (rawString.isNullOrBlank()) {
        logger.severe("Language key $langKey not found or empty")
        return null
    }

    if (constants == null || constants.isEmpty()) {
        return rawString
    }

    var formattedString = rawString

    val regex = Regex("%\\((.*?)\\)[s|d]")
    val matches = regex.findAll(rawString)
    for (match in matches) {
        val key = match.groups[1]?.value
        val originalString = match.value
        if (key == null || originalString == null) {
            logger.severe("Invalid match, key is null")
            continue
        }
        val constantKey = key.replace("_percent", "")
        var constantValue = constants[constantKey]
        logger.fine("Constant $key = $constantValue")
        if (key.endsWith("_percent")) {
            val number = constantValue as Number
            constantValue = "${(number.toDouble() * 100).toDecimalString()}%"
        }

        logger.info("Replacing $originalString with $constantValue")
        formattedString = formattedString.replace(originalString, constantValue.toString())

        if (formattedString.contains("%(")) {
            error("Still have %(key) in the string")
        }
    }

    return formattedString
}


    private fun get(key: String, prefix: String? = null): String {
        val string = stringOf(key, prefix)
        if (string == null) {
            throw Exception("Make sure $key is correct")
        }
        return string
    }

    val battles: String get() = get("IDS_BATTLES")

    // Ship type names
    val airCarrier: String get() = get("IDS_AIRCARRIER")
    val battleship: String get() = get("IDS_BATTLESHIP")
    val cruiser: String get() = get("IDS_CRUISER")
    val destroyer: String get() = get("IDS_DESTROYER")
    val submarine: String get() = get("IDS_SUBMARINE")

    // Units
    val second: String get() = get("SECOND", "IDS_")
    val kilometer: String get() = get("KILOMETER", "IDS_")
    val meter: String get() = get("METER", "IDS_")
    val millimeter: String get() = get("MILLIMETER", "IDS_")
    val kilogram: String get() = get("KILOGRAMM", "IDS_")
    val knot: String get() = get("KNOT", "IDS_")
    val meterPerSecond: String get() = get("METER_SECOND", "IDS_")
    val unit: String get() = get("UNITS", "IDS_")
    val unitPerSecond: String get() = get("UNITS_SECOND", "IDS_")

    private const val shipFilter = "IDS_CAROUSEL_APPLIED_FILTER_HINT_"
    val regionFilterName: String get() = get("nation", shipFilter)
    val shipNameFilterName: String get() = get("shipname", shipFilter)
    val shipTypeFilterName: String get() = get("shiptype", shipFilter)
    val tierFilterName: String get() = get("level", shipFilter)

    private const val moduleType = "IDS_MODULE_TYPE_"
    val hull: String get() = get("HULL", moduleType)
    val artillery: String get() = get("ARTILLERY", moduleType)
    val secondaries: String get() = get("SECONDARYWEAPONS", moduleType)
    val torpedoes: String get() = get("TORPEDOES", moduleType)
    val sonar: String get() = get("SONAR", moduleType)
    val fireControl: String get() = get("SUO", moduleType)
    val engine: String get() = get("ENGINE", moduleType)
    val fighter: String get() = get("FIGHTER", moduleType)
    val skipBomber: String get() = get("SKIPBOMBER", moduleType)
    val torpedoBomber: String get() = get("TORPEDOBOMBER", moduleType)
    val diveBomber: String get() = get("DIVEBOMBER", moduleType)

    private const val shipParam = "IDS_SHIP_PARAM_"
    private const val paramModifier = "IDS_PARAMS_MODIFIER_"
    val maximumRange: String get() = get("MAX_DIST", shipParam)
    val reloadTime: String get() = get("SHOT_DELAY", shipParam)
    val rotationTime: String get() = get("ROTATION_TIME", shipParam)
    val shellWeight: String get() = get("AMMO_BULLET_MASS", shipParam)
    val diveCapacity: String get() = get("BATTERY", shipParam)

    // Durability
    val durability: String get() = get("DURABILITY", shipParam)
    val health: String get() = get("HEALTH", shipParam)
    val torpedoProtection: String get() = get("PTZDAMAGEPROB", shipParam)

    // Main battery
    val shipArtillery: String get() = get("ARTILLERY", shipParam)
    val gunReloadTime: String get() = reloadTime
    val gunRotationTime: String get() = rotationTime
    val gunDamage: String get() = get("ARTILLERY_MAX_DAMAGE", shipParam)
    val gunRange: String get() = maximumRange
    val gunDispersion: String get() = get("DISPERSION", shipParam)
    val shellVelocity: String get() = get("ARTILLERY_AMMO_SPEED", shipParam)
    val shellPenetration: String get() = get("ARTILLERY_ALPHA_PIERCING", shipParam)
    val shellFireChance: String get() = get("ARTILLERY_BURN_PROB", shipParam)
    // Burst fire mode
    val burstFire: String get() = get("BURST_FIRE_NAME", shipParam)
    val burstFireCount: String get() = get("BURST_FIRE_SALVO_COUNT", shipParam)
    val burstFireInterval: String get() = get("BURST_FIRE_SALVO_SHOT_DELAY", shipParam)
    // The full reload after burst fire mode usually 30s to 40s
    val burstFireReload: String get() = get("BURST_FIRE_RELOAD_TIME", shipParam)

    // Secondary battery
    val secondaryBattery: String get() = get("ATBA_SHORT", shipParam)

    // Sonar
    val pingerDuration: String get() = get("WAVE_DURATION", shipParam)

    // Battery
    val diveCapcity: String get() = get("BATTERY", shipParam)
    val batteryMaxCapacity: String get() = get("BATTERY_MAX_CAPACITY", shipParam)
    val batteryConsumption: String get() = get("BATTERY_CONSUMPTION_RATE", shipParam)
    val bateryRegen: String get() = get("BATTERY_REGEN_RATE", shipParam)

    // Torpedo
    val torpedoRange: String get() = get("TORPEDO_MAX_DIST", shipParam)
    val torpedoSpeed: String get() = get("TORPEDO_SPEED", shipParam)
    val torpedoReloadTime: String get() = reloadTime
    val torpedoRotationTime: String get() = rotationTime
    val torpedoDamage: String get() = get("TORPEDO_DAMAGE", shipParam)
    val torpedoDetection: String get() = get("TORPEDO_VISIBILITY_DIST", shipParam)
    val floodChance: String get() = get("FLOODCHANCEFACTOR", paramModifier)

    // AA
    val airDefense: String get() = get("AIR_DEFENSE", shipParam)
    val continousDamage: String get() = get("AA_AVERAGE_CONST_DAMAGE", shipParam)
    val airBubbleDamage: String get() = get("AA_BUBBLE_DAMAGE_IN_A_SECOND", shipParam)
    val aaRange: String get() = get("AA_RANGE", shipParam)
    val hitChance: String get() = get("AA_BUBBLE_HITCHANCE", shipParam)
    val bubbleExplosion: String get() = get("AA_EXPL_COUNT", shipParam)

    // Air support
    private const val airSupportParams = "IDS_SHIP_PARAM_AIR_SUPPORT_"
    val numberOfBombs: String get() = get("NUM_BOMBS_ON_PLANE", airSupportParams)
    val availableFlights: String get() = get("NUM_SQUADRONS", airSupportParams)
    val airSupportTotalPlanes: String get() = get("NUM_PLANES_IN_CHARGE", airSupportParams)
    val bombDamage: String get() = get("BOMB_DAMAGE", airSupportParams)
    val planeHealth: String get() = get("PLANEHEALTH", paramModifier)

    // Mobility
    val mobility: String get() = get("MOBILITY", shipParam)
    val maxSpeed: String get() = get("MAXSPEED", shipParam)
    val turningRadius: String get() = get("TURNINGRADIUS", shipParam)
    val rudderTime: String get() = get("RUDDER_TIME", shipParam)

    // Visibility
    val visibility: String get() = get("VISIBILITY", shipParam)
    val seaDetection: String get() = get("VISIBILITY_DIST_BY_SHIP", shipParam)
    val airDetection: String get() = get("VISIBILITY_DIST_BY_PLANE", shipParam)

    val airSupport: String get() = get("AIRSUPPORT", shipParam)
    val depthCharge: String get() = get("DEPTH_CHARGE", shipParam)
    val actionTime: String get() = get("WORK_TIME", shipParam)
    val requiredHits: String get() = get("REQUIREDHITS_RAGEMODE", paramModifier)

    // Upgrades
    val upgrades: String get() = get("MODERNIZATIONS", "IDS_")
    val nextShip: String get() = get("SPECTATE_SWITCH_SHIP", "IDS_")
    val consumables: String get() = get("ABILITIES", "IDS_MODULE_TYPE_")
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
            }
        }) {
            Text("Click Me")
        }
    }
}