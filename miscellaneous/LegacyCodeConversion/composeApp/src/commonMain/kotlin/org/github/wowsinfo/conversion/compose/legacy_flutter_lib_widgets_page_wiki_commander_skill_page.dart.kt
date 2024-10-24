
@Composable
fun CommanderSkillPage(
    modifier: Modifier = Modifier,
    provider: CommanderSkillProvider,
) {
    val configuration by LocalConfiguration.currentFlow()
    Scaffold(
        topBar = AppBar(title = Text(stringResource(R.string.wiki_skills))),
        floatingActionButtonLocation = FloatingActionButtonLocation.CenterFloat,
        floatingActionButton = FAB(provider::reset),
        content = Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                verticalAlignment = Alignment.BottomCenter,
                modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
            ) {
                for (tab in provider.tabs) {
                    Tab(provider, tab)
                }
            }

            Text(
                text = provider.pointInfo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row {
                Column(modifier = Modifier.padding(end = 4.dp)) {
                    for (skill in provider.skills[0]) {
                        SkillIcon(provider, skill, modifier = Modifier.weight(1f))
                    }
                }

                Column {
                    for (skill in provider.skills[1]) {
                        SkillIcon(
                            provider,
                            skill,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                        )
                    }
                }

                Column(modifier = Modifier.padding(start = 4.dp)) {
                    for (skill in provider.skills[2]) {
                        SkillIcon(provider, skill, modifier = Modifier.weight(1f))
                    }
                }
            }

            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = provider.selectedDescriptions,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    )
}

@Composable
private fun Tab(provider: CommanderSkillProvider, tab: String) {
    val selected = provider.tab == tab
    val darkMode by LocalConfiguration.currentDarkModeFlow()
    Text(
        text = tab,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable { provider.select(tab) },
        color = if (selected) Color.White else if (darkMode) Color.LightGray else Color.DarkGray,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun SkillIcon(
    provider: CommanderSkillProvider,
    skill: ShipSkill,
    modifier: Modifier = Modifier
) {
    val darkMode by LocalConfiguration.currentDarkModeFlow()
    val selected = provider.selected.contains(skill)
    val color = if (darkMode) Color.White else Color.Black
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (selected) color else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { provider.selectSkill(skill) }
            .onLongPress { provider.onLongPress(skill) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_${skill.name}),
            contentDescription = null,
            modifier = Modifier
                .background(Color.Transparent)
                .padding(4.dp),
            colorFilter = ColorFilter.tint(color)
        )
    }
}

@Composable
private fun FAB(onClick: () -> Unit) {
    val darkMode by LocalConfiguration.currentDarkModeFlow()
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh),
        tint = if (darkMode) Color.White else Color.Black,
        modifier = Modifier.clickable { onClick() }
            .padding(16.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview() {
    val provider = rememberCommanderSkillProvider()
    CommanderSkillPage(provider = provider)
}