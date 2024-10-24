
class GameRepository private constructor() {
    companion object {
        val instance: GameRepository by lazy { GameRepository() }
    }

    private var initialized = false
    private val logger = LoggerFactory.getLogger(GameRepository::class.java)

    private lateinit var alias: Map<String, Alias>
    private lateinit var abilities: Map<String, Ability>
    private lateinit var achievements: Map<String, Achievement>
    private lateinit var aircrafts: Map<String, Aircraft>
    private lateinit var exteriors: Map<String, Exterior>
    private lateinit var modernizations: Map<String, Modernization>
    private lateinit var projectiles: Map<String, Projectile>
    private lateinit var ships: Map<String, Ship>
    private lateinit var skills: Map<String, CommanderSkill>
    private lateinit var shipAdditionals: Map<String, ShipAdditional>
    private lateinit var gameInfo: GameInfo
    private lateinit var commandSkills: CommandSkillInfo

    private lateinit var achievementList: List<Achievement>
    private lateinit var consumableList: List<Ability>
    private lateinit var exteriorList: List<Exterior>
    private lateinit var modernizationList: List<Modernization>
    private lateinit var shipList: List<Ship>
    private lateinit var shipRegionList: List<String>
    private lateinit var shipTypeList: List<String>
    private lateinit var usefulFlagList: List<Flag>

    suspend fun initialise() {
        if (initialized) {
            logger.error("GameRepository already initialised")
            throw Exception("GameRepository is already initialised")
        }

        val timer = TimeTracker()

        val jsonString = loadJson("data/live/app/data/wowsinfo.json")
        timer.log("Loaded wowsinfo.json")

        val dataObject = Json.decodeFromString<Map<String, Any>>(jsonString)
        timer.log("Parsed wowsinfo.json")

        alias = (dataObject["alias"] as Map<String, Any>).mapValues { Alias.fromJson(it.value) }
        abilities = (dataObject["abilities"] as Map<String, Any>).mapValues { Ability.fromJson(it.value) }
        achievements = (dataObject["achievements"] as Map<String, Any>).mapValues { Achievement.fromJson(it.value) }
        aircrafts = (dataObject["aircrafts"] as Map<String, Any>).mapValues { Aircraft.fromJson(it.value) }
        exteriors = (dataObject["exteriors"] as Map<String, Any>).mapValues { Exterior.fromJson(it.value) }
        modernizations = (dataObject["modernizations"] as Map<String, Any>).mapValues { Modernization.fromJson(it.value) }
        projectiles = (dataObject["projectiles"] as Map<String, Any>).mapValues { Projectile.fromJson(it.value) }
        ships = (dataObject["ships"] as Map<String, Any>).mapValues { Ship.fromJson(it.value) }
        skills = (dataObject["skills"] as Map<String, Any>).mapValues { CommanderSkill.fromJson(it.value) }
        shipAdditionals = (dataObject["number"] as Map<String, Any>).mapValues { ShipAdditional.fromJson(it.value) }
        gameInfo = GameInfo.fromJson(dataObject["game"]!!)
        timer.log("Decoded wowsinfo.json")

        val commanderSkills = loadJson("assets/skills.json")
        timer.log("Loaded skills.json")
        commandSkills = CommandSkillInfo.fromJson(Json.decodeFromString(commanderSkills))
        timer.log("Decoded skills.json")

        generateLists()
        initialized = true
        timer.log("Initialised GameRepository")
    }

    private suspend fun loadJson(filePath: String): String {
        return withContext(Dispatchers.IO) {
            val inputStream: InputStream = this::class.java.classLoader.getResourceAsStream(filePath) ?: throw Exception("File not found")
            inputStream.bufferedReader().use { it.readText() }
        }
    }

    private fun generateLists() {
        achievementList = achievements.values.toList()
        consumableList = abilities.values.toList()
        exteriorList = exteriors.values.toList()
        modernizationList = modernizations.values.toList()
        shipList = ships.values.toList()
        shipRegionList = shipList.map { it.region }
        shipTypeList = shipList.map { it.type }
        usefulFlagList = listOf() // Populate with actual data as needed
    }
}

    achievementList = achievements.values.toList().sortedByDescending { it.id }
    consumableList = abilities.values.toList().sortedByDescending { it.id }
    exteriorList = exteriors.values.toList().sortedByDescending { it.id }
    modernizationList = modernizations.values.toList().sortedBy { it.greater() }
    shipList = ships.values.toList().sortedBy { it.greater() }
    
    shipRegionList = gameInfo.regions.sorted()
    shipTypeList = gameInfo.types.sorted()

    usefulFlagList = exteriorList.filter { it.isFlag && (it.modifiers?.isNotEmpty() == true) }
        .sortedByDescending { it.id }
}

    return _alias[key]?.alias
}

    return abilities[key]
}

    return achievements[key]
}

    return aircrafts[key]
}

    val flag = exteriors[key]
    if (flag == null) return null
    if (flag.type != "Flags") {
        throw Exception("$key is not a flag")
    }
    return flag
}

    val camouflage = exteriors[key]
    if (camouflage == null) return null
    if (camouflage.type == "Flags") {
        throw Exception("$key is not a camouflage")
    }
    return camouflage
}

    return modernizations[key]
}

    return projectiles[key]
}

    return ships[id]
}

    val ship = shipOf(id)
    if (ship == null) return null
    return Localisation.instance.stringOf(ship.name)
}

    return shipAdditionals[id]
}

    get() = commandSkills.shipTypes

fun commandSkillsOf(type: CommanderSkillType): List<List<ShipSkill>> {
    val skills = commandSkills.shipTypes[type.rawName]
    return skills ?: throw Exception("No skills for $type")
}

    return skills[name]
}


@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My App") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state) {
                is MyState.Loading -> CircularProgressIndicator()
                is MyState.Success -> SuccessContent(state.data)
                is MyState.Error -> ErrorContent(state.message)
            }
        }
    }
}

@Composable
fun SuccessContent(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Text(item, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun ErrorContent(message: String) {
    Text(message, modifier = Modifier.padding(16.dp))
}

class MyViewModel(private val repository: MyRepository) {
    var state: MyState = MyState.Loading
        private set

    init {
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = repository.getData()
                state = MyState.Success(data)
            } catch (e: Exception) {
                state = MyState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: List<String>) : MyState()
    data class Error(val message: String) : MyState()
}

interface MyRepository {
    suspend fun getData(): List<String>
}