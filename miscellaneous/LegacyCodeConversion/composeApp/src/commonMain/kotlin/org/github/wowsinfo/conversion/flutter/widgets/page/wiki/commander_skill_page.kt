
@Composable
fun CommanderSkillPage() {
    val provider: CommanderSkillProvider = viewModel()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Localisation.wiki_skills) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    provider.reset()
                }
            }) {
                Icon(Icons.Default.Refresh, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            buildSegmentedControl(provider)
            Spacer(modifier = Modifier.height(8.dp))
            buildPointInfo(provider)
            Spacer(modifier = Modifier.weight(1f))
            buildCommanderSkills(provider)
        }
    }
}

@Composable
fun buildSegmentedControl(provider: CommanderSkillProvider) {
    // Implementation of segmented control
}

@Composable
fun buildPointInfo(provider: CommanderSkillProvider) {
    // Implementation of point info
}

@Composable
fun buildCommanderSkills(provider: CommanderSkillProvider) {
    // Implementation of commander skills
}

fun SegmentedControl(provider: CommanderSkillProvider) {
    val selectedTab = provider.selectedTab
    val onValueChanged: (String) -> Unit = { value -> provider.select(value) }

    val children = mapOf(
        provider.submarine to buildTitle(provider.submarine),
        provider.destroyer to buildTitle(provider.destroyer),
        provider.cruiser to buildTitle(provider.cruiser),
        provider.battleship to buildTitle(provider.battleship),
        provider.airCarrier to buildTitle(provider.airCarrier)
    )

    Row {
        children.forEach { (key, title) ->
            val isSelected = key == selectedTab
            Button(
                onClick = { onValueChanged(key) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSelected) Color.Blue else Color.Gray
                )
            ) {
                Text(title)
            }
        }
    }
}

fun buildTitle(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Center
    )
}

fun PointInfo(provider: CommanderSkillProvider) {
    Text(
        text = provider.pointInfo,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center
    )
}

fun buildCommanderSkills(viewModel: CommanderSkillViewModel) {
    val skills by viewModel.skills.collectAsState()
    val selectedDescriptions by viewModel.selectedDescriptions.collectAsState()

    LazyColumn {
        itemsIndexed(skills) { index, skillGroup ->
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    skillGroup.forEach { skill ->
                        Spacer(modifier = Modifier.width(4.dp))
                        buildItem(skill, viewModel::selectSkill, viewModel::onLongPress, viewModel::isSkillSelected)
                    }
                }
                if (index < 3) {
                    Divider(modifier = Modifier.width(300.dp))
                }
            }
        }
        item {
            Text(
                text = selectedDescriptions,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 70.dp)
            )
        }
    }
}

fun BuildItem(
    skill: ShipSkill,
    onTap: (ShipSkill) -> Unit,
    onLongPress: (ShipSkill) -> Unit,
    isSelected: (ShipSkill) -> Boolean,
) {
    val icon = skill.name
    val selected = isSelected(skill)

    val darkMode = MaterialTheme.colors.isLight.not()
    val color = if (darkMode) Color.White else Color.Black.copy(alpha = 0.87f)

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (selected) color else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                onClick = { onTap(skill) },
                onLongClick = { onLongPress(skill) }
            )
            .size(80.dp)
    ) {
        Image(
            painter = painterResource("data/live/app/assets/skills/$icon.png"),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier.fillMaxSize()
        )
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
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}