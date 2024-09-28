
const val maxPoints = 21

class CommanderSkillProvider(private val context: Any, private val type: CommanderSkillType? = null) : ViewModel() {
    private val logger = LoggerFactory.getLogger(CommanderSkillProvider::class.java)

    private val _skills = mutableStateOf(getSkillsFrom(type ?: CommanderSkillType.DESTROYER))
    val skills: List<List<ShipSkill>> get() = _skills.value

    private fun getSkillsFrom(type: CommanderSkillType): List<List<ShipSkill>> {
        return GameRepository.instance.commandSkillsOf(type)
    }
}

    return when (tab) {
        airCarrier -> getSkillsFrom(CommanderSkillType.airCarrier)
        battleship -> getSkillsFrom(CommanderSkillType.battleship)
        cruiser -> getSkillsFrom(CommanderSkillType.cruiser)
        destroyer -> getSkillsFrom(CommanderSkillType.destroyer)
        submarine -> getSkillsFrom(CommanderSkillType.submarine)
        else -> throw Exception("Unknown tab: $tab")
    }
}

    companion object {
        val instance = Localisation()
    }

    val airCarrier: String = "Air Carrier"
    val destroyer: String = "Destroyer"
    val cruiser: String = "Cruiser"
    val battleship: String = "Battleship"
    val submarine: String = "Submarine"
}

class TabSelector : Observable() {
    private val logger = Logger.getLogger(TabSelector::class.java.name)

    private val airCarrier = Localisation.instance.airCarrier
    private val destroyer = Localisation.instance.destroyer
    private val cruiser = Localisation.instance.cruiser
    private val battleship = Localisation.instance.battleship
    private val submarine = Localisation.instance.submarine

    private var _selectedTab: String = destroyer
    val selectedTab: String get() = _selectedTab

    private var _selectedPoints: Int = 0
    private val _selectedSkills: MutableList<String> = mutableListOf()
    private var _skills: List<String> = emptyList()

    fun select(tab: String) {
        logger.fine("Selected tab: $tab")
        _selectedTab = tab

        _selectedPoints = 0
        _selectedSkills.clear()

        _skills = getSkillsFromTab(tab)

        setChanged()
        notifyObservers()
    }

    private fun getSkillsFromTab(tab: String): List<String> {
        // Implement logic to retrieve skills based on the selected tab
        return when (tab) {
            airCarrier -> listOf("Skill A", "Skill B")
            destroyer -> listOf("Skill C", "Skill D")
            cruiser -> listOf("Skill E", "Skill F")
            battleship -> listOf("Skill G", "Skill H")
            submarine -> listOf("Skill I", "Skill J")
            else -> emptyList()
        }
    }
}

private val selectedSkills = mutableListOf<ShipSkill>()

val selectedDescriptions: String
    get() {
        if (selectedSkills.isEmpty()) return ""
        var additionalDescription = ""
        val skillDescription = selectedSkills.mapNotNull { skill ->
            val commanderSkill = GameRepository.instance.skillOf(skill.name)
            val passiveSkill = commanderSkill?.skillDescription
            if (passiveSkill != null) {
                additionalDescription += "\n$passiveSkill"
            }
            commanderSkill?.let {
                it.modifiers.merge(it.logicTrigger.modifiers)
            }
        }.reduceOrNull { prev, curr -> prev.merge(curr) }
            ?.toString()
            ?.split("\n")
            ?.filter { line ->
                !line.contains(")") || (line.contains(")") && line.contains(selectedTab))
            }
            ?.joinToString("\n") ?: ""

        additionalDescription = additionalDescription.replace("\n\n", "\n").trim()
        return "$additionalDescription\n\n$skillDescription"
    }

    private val _selectedSkills = mutableSetOf<ShipSkill>()
    private var _selectedPoints = 0
    private val _maxPoints = 10
    private val _logger = Logger()

    fun isSkillSelected(skill: ShipSkill): Boolean {
        return _selectedSkills.contains(skill)
    }

    fun selectSkill(skill: ShipSkill) {
        if (_selectedSkills.contains(skill)) {
            _logger.fine("Skill already selected: $skill")
            _selectedSkills.remove(skill)
            if (_selectedSkills.none { it.tier == skill.tier }) {
                _selectedSkills.removeIf { it.tier > skill.tier }
            }
            _selectedPoints = _selectedSkills.sumOf { it.tier }
            notifyListeners()
            return
        }

        val tier = skill.tier
        if (tier + _selectedPoints > _maxPoints) {
            _logger.fine("More than max points")
            return
        }

        if (_selectedPoints == 0 && tier > 1) {
            _logger.fine("Tier too high")
            return
        }

        if (_selectedPoints > 0 && tier > 1) {
            if (_selectedSkills.none { it.tier == tier - 1 }) {
                _logger.fine("$skill cannot be selected")
                return
            }
        }

        _logger.fine("Selected skill: $skill")
        _selectedSkills.add(skill)
        _selectedPoints += skill.tier
        _logger.fine("Selected points: $_selectedPoints")
        notifyListeners()
    }

    private fun notifyListeners() {
        // Notify listeners about the changes
    }
}

data class ShipSkill(val tier: Int)

class Logger {
    fun fine(message: String) {
        println(message)
    }
}

    selectedPoints = 0
    selectedSkills.clear()
    notifyListeners()
}

    val commanderSkill = GameRepository.instance.skillOf(skill.name)
    if (commanderSkill == null) {
        throw IllegalArgumentException("Skill not found: ${skill.name}")
    }

    val skillName = Localisation.instance.stringOf(commanderSkill.name)
    showDialog {
        AlertDialog(
            onDismissRequest = { /* Handle dismiss */ },
            title = { Text(skillName ?: "-") },
            text = { Text(commanderSkill.fullDescriptions) },
            confirmButton = {
                TextButton(onClick = { /* Handle close */ }) {
                    Text("Close")
                }
            }
        )
    }
}

    val selectedPointsString: String
        get() = selectedPoints.toString()
    
    val totalPointsString: String
        get() = maxPoints.toString()
    
    val pointInfo: String
        get() = "$selectedPointsString / $totalPointsString"
}