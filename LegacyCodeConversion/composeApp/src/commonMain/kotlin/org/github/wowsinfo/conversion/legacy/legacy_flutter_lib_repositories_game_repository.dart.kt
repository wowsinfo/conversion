import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class GameRepository(private val flutterEngine: FlutterEngine) {
    private var initialized = false
    private val logger = Logger("GameRepository")

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

    private val achievementList = mutableListOf<Achievement>()
    private val consumableList = mutableListOf<Ability>()
    private val exteriorList = mutableListOf<Exterior>()
    private val modernizationList = mutableListOf<Modernization>()
    private val shipList = mutableListOf<Ship>()
    private val shipRegionList = mutableListOf<String>()
    private val shipTypeList = mutableListOf<String>()
    private val usefulFlagList = mutableListOf<Flag>()

    fun initialize() {
        if (initialized) {
            logger.severe("GameRepository already initialized")
            throw Exception("GameRepository is already initialized")
        }

        // Load wowsinfo.json from /data/live/app/data/
        val jsonString = flutterEngine.dartExecutor.binaryMessenger.runBlocking {
            MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "wowsinfo").invokeMethod<String>("loadWowsinfoJson")!!
        }
        logger.log(message = "Loaded wowsinfo.json")

        // Decode JSON into models and store them in the repository
        val dataObject = Json.decodeValue(jsonString)
        logger.log(message = "Parsed wowsinfo.json")

        alias = (dataObject["alias"] as Map).mapValues { (_, value) -> Alias.fromJson(value) }
        abilities = (dataObject["abilities"] as Map).mapValues { (_, value) -> Ability.fromJson(value) }
        achievements = (dataObject["achievements"] as Map).mapValues { (_, value) -> Achievement.fromJson(value) }
        aircrafts = (dataObject["aircrafts"] as Map).mapValues { (_, value) -> Aircraft.fromJson(value) }
        exteriors = (dataObject["exteriors"] as Map).mapValues { (_, value) -> Exterior.fromJson(value) }
        modernizations = (dataObject["modernizations"] as Map).mapValues { (_, value) -> Modernization.fromJson(value) }
        projectiles = (dataObject["projectiles"] as Map).mapValues { (_, value) -> Projectile.fromJson(value) }
        ships = (dataObject["ships"] as Map).mapValues { (_, value) -> Ship.fromJson(value) }
        skills = (dataObject["skills"] as Map).mapValues { (_, value) -> CommanderSkill.fromJson(value) }
        shipAdditionals = (dataObject["number"] as Map).mapValues { (_, value) -> ShipAdditional.fromJson(value) }
        gameInfo = GameInfo.fromJson(dataObject["game"])
        logger.log(message = "Decoded wowsinfo.json")

        // Load commander skills
        val commandSkillsJsonString = flutterEngine.dartExecutor.binaryMessenger.runBlocking {
            MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "skills").invokeMethod<String>("loadSkillsJson")!!
        }
        logger.log(message = "Loaded skills.json")
        commandSkills = CommandSkillInfo.fromJson(Json.decodeValue(commandSkillsJsonString))
        logger.log(message = "Decoded skills.json")

        generateLists()
        initialized = true
        logger.log(message = "Initialized GameRepository")
    }

    private fun generateLists() {
        // Sort achievements by id
        achievementList.addAll(achievements.values.sortedByDescending { it.id })
        // Sort consumables by id
        consumableList.addAll(abilities.values.sortedByDescending { it.id })
        // Sort exteriors by id
        exteriorList.addAll(exteriors.values.sortedByDescending { it.id })
        // Sort modernizations by id
        modernizationList.addAll(modernizations.values.sortedByDescending { it.greater(it) })
        // Sort ships by id
        shipList.addAll(ships.values.sortedByDescending { it.greater(it) })

        // Sort regions and types only to make sure they are the same
        shipRegionList.addAll(gameInfo.regions.sorted())
        shipTypeList.addAll(gameInfo.types.sorted())

        // Get all flags which have a modifier
        usefulFlagList.addAll(exteriorList.filter { it.isFlag && (it.modifiers?.isNotEmpty ?: false) }.sortedByDescending { it.id })
    }

    fun aliasOf(key: String): String? {
        return alias[key]?.alias
    }

    fun abilityOf(key: String): Ability? {
        return abilities[key]
    }

    fun achievementOf(key: String): Achievement? {
        return achievements[key]
    }

    fun aircraftOf(key: String): Aircraft? {
        return aircrafts[key]
    }

    fun flagOf(key: String): Flag? {
        val flag = exteriors[key]
        if (flag == null) return null
        // Make sure this is a flag not a camouflage
        if (flag.type != "Flags") throw Exception("$key is not a flag")
        return flag
    }

    fun camouflageOf(key: String): Camouflage? {
        val camouflage = exteriors[key]
        if (camouflage == null) return null
        // Make sure this is a camouflage not a flag
        if (camouflage.type == "Flags") throw Exception("$key is not a camouflage")
        return camouflage
    }

    fun modernizationOf(key: String): Modernization? {
        return modernizations[key]
    }

    fun projectileOf(key: String): Projectile? {
        return projectiles[key]
    }

    fun shipOf(id: String): Ship? {
        // TODO: We can check if this id is numeric
        return ships[id]
    }

    fun shipNameOf(id: String): String? {
        val ship = shipOf(id)
        if (ship == null) return null
        return Localisation.instance.stringOf(ship.name)
    }

    fun shipAdditionalOf(id: String): ShipAdditional? {
        return shipAdditionals[id]
    }

    fun commanderSkills(): Map<String, List<List<ShipSkill>>> {
        return commandSkills.shipTypes
    }

    fun commandSkillsOf(type: CommanderSkillType): List<List<ShipSkill>> {
        val skills = commandSkills.shipTypes[type.rawName]
        if (skills == null) throw Exception("No skills for $type")
        return skills
    }

    fun skillOf(name: String): CommanderSkill? {
        return skills[name]
    }
}