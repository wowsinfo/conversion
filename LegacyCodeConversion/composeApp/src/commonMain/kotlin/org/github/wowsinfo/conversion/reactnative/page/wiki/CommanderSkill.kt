
@Composable
fun CommanderSkill() {
    val scope = rememberCoroutineScope()
    val skill = AppGlobalData.get(SAVED.commanderSkill)
    val cloned = skill.map { it.copy() }

    val section = mutableListOf<Section>()
    cloned.forEach { i ->
        val index = i.tier - 1
        if (section.size <= index) {
            section.add(Section(title = "${lang.wiki_skills_tier} ${i.tier}", data = mutableListOf()))
        }
        section[index].data.add(i.copy())
    }

    var point by remember { mutableStateOf(19) }

    Column(modifier = Modifier.padding(16.dp)) {
        section.forEach { sec ->
            Text(text = sec.title, style = MaterialTheme.typography.h6)
            LazyColumn {
                items(sec.data) { skillItem ->
                    SkillItem(skillItem)
                }
            }
        }
    }
}

@Composable
fun SkillItem(skill: Skill) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = skill.name, style = MaterialTheme.typography.body1)
            // Additional skill details can be displayed here
        }
    }
}

data class Section(val title: String, val data: MutableList<Skill>)
data class Skill(val name: String, val tier: Int) {
    fun copy(): Skill {
        return Skill(name, tier)
    }
}

fun WoWsInfoScreen(viewModel: YourViewModel) {
    val state by viewModel.state.collectAsState()

    WoWsInfo(
        title = "${state.point} ${lang.wiki_skills_point}",
        onPress = { viewModel.reset() }
    ) {
        LazyColumn {
            state.data.forEach { section ->
                item {
                    SectionTitle(title = section.title)
                }
                items(section.items) { item ->
                    WikiIcon(
                        item = item,
                        selected = item.selected,
                        onClick = { viewModel.skillSelected(item) },
                        onLongClick = { SafeAction("BasicDetail", item) }
                    )
                }
            }
        }
    }
}

    var pointLeft = state.point
    if (item.selected) {
        if (pointLeft == lang.wiki_skills_reset) {
            pointLeft = 0
        }
        pointLeft += item.tier
        item.selected = false
        setState { point = pointLeft }
    } else {
        pointLeft -= item.tier
        if (pointLeft >= 0) {
            item.selected = true
            if (pointLeft == 0) {
                setState { point = lang.wiki_skills_reset }
            } else {
                setState { point = pointLeft }
            }
        }
    }
}

    val data = state.data
    data.forEach { i ->
        i.data.forEach { j ->
            j.selected = null
        }
    }
    setState { 
        point = 19
        this.data = data 
    }
}


@Composable
fun CommanderSkill() {
    var skillLevel by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Commander Skill Level: $skillLevel")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { skillLevel++ }) {
            Text(text = "Increase Skill Level")
        }
    }
}