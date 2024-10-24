
private const val maxPoints = 21

@Composable
fun CommanderSkillProvider(
    context: Context,
    type: CommanderSkillType? = null
) {
    val logger = Timber.tag("CommanderSkillProvider").writable()

    val skills by remember { mutableStateOf(getSkillsFrom(type)) }
    val selectedTab by remember { mutableStateOf(if (type == null) "destroyer" else type.name.lowercase()) }

    var selectedPoints by remember { mutableStateOf(0) }
    val selectedSkills by remember { mutableStateOf(mutableListOf<ShipSkill>()) }

    val airCarrier = Localisation.instance.airCarrier
    val destroyer = Localisation.instance.destroyer
    val cruiser = Localisation.instance.cruiser
    val battleship = Localisation.instance.battleship
    val submarine = Localisation.instance.submarine

    var selectedDescriptions by remember { mutableStateOf("") }

    fun getSkillsFrom(tab: String) = GameRepository.instance.commandSkillsOf(CommanderSkillType.valueOf(tab.uppercase()))

    fun getSelectedDescriptions() {
        if (selectedSkills.isEmpty()) return ""

        val additionalDescription = ""
        val skillDescription = selectedSkills
            .map { skill ->
                val commanderSkill = GameRepository.instance.skillOf(skill.name)
                val passiveSkill = commanderSkill?.skillDescription
                if (passiveSkill != null) {
                    additionalDescription += "\n$passiveSkill"
                }
                if (commanderSkill == null) return@map null
                commanderSkill.modifiers.merge(commanderSkill.logicTrigger.modifiers)
            }
            .filterNotNull()
            .reduce { prev, curr -> prev?.merge(curr) }
            ?.toString()
            ?.split("\n")
            ?.filter {
                !it.contains(")") || (it.contains(")") && it.contains(selectedTab))
            }
            ?.join("\n")

        selectedDescriptions = "$additionalDescription\n\n$skillDescription"
    }

    fun isSkillSelected(skill: ShipSkill) = selectedSkills.contains(skill)

    fun selectSkill(skill: ShipSkill) {
        if (selectedSkills.contains(skill)) {
            logger.d("Skill already selected: $skill")
            // remove from the selection
            selectedSkills.remove(skill)
            // if this is the only skill selected for the tier, remove everything below it
            if (!selectedSkills.any { it.tier == skill.tier }) {
                selectedSkills.removeAll { it.tier > skill.tier }
            }
            // update the points
            selectedPoints = selectedSkills.fold(0) { prev, curr -> prev + curr.tier }
            return
        }

        val tier = skill.tier
        if (tier + selectedPoints > maxPoints) {
            logger.d("More than max points")
            return
        }

        if (selectedPoints == 0 && tier > 1) {
            logger.d("Tier too high")
            return
        }

        // tier 1 can always be selected
        if (selectedPoints > 0 && tier > 1) {
            // only select higher tiers if there is at least one lower tier selected
            if (!selectedSkills.any { it.tier == tier - 1 }) {
                logger.d("$skill cannot be selected")
                return
            }
        }

        logger.d("Selected skill: $skill")
        selectedSkills.add(skill)
        selectedPoints += skill.tier
        logger.d("Selected points: $selectedPoints")
    }

    fun reset() {
        selectedPoints = 0
        selectedSkills.clear()
    }

    fun onLongPress(skill: ShipSkill) {
        // Show the information of the skill
        val commanderSkill = GameRepository.instance.skillOf(skill.name)
        if (commanderSkill == null) {
            assert(false, "Skill not found: ${skill.name}")
            return
        }
        val skillName = Localisation.instance.stringOf(commanderSkill.name)
        AlertDialog(
            title = Text(skillName ?: "-"),
            text = commanderSkill.fullDescriptions,
            onDismissRequest = { Navigator.pop(context) },
        )
    }

    Column {
        TabRow(
            selectedTabIndex = tabs.indexOfFirst { it.lowercase() == selectedTab },
            onSelected = { index ->
                val tab = tabs[index]
                selectedPoints = 0
                selectedSkills.clear()
                skills = getSkillsFrom(tab)
                selectedTab = tab
                logger.d("Selected tab: $tab")
                notifyListeners()
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(text = Localisation.instance.stringOf(tab), modifier = Modifier.weight(1f))
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            skills.forEach { tierSkills ->
                Text(
                    Localisation.instance.stringOf("tier_${tierSkills.first().tier}"),
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(tierSkills) { skill ->
                        CommanderSkillCard(
                            skill = skill,
                            isSelected = isSkillSelected(skill),
                            onSelect = { selectSkill(skill) },
                            onLongPress = { onLongPress(skill) }
                        )
                    }
                }
            }

            Text(pointInfo)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { reset() }) {
                Text(Localisation.instance.reset)
            }
        }
    }
}