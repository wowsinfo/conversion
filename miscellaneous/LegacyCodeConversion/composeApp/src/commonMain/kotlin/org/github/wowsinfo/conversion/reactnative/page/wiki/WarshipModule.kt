
@Composable
fun WarshipModule(data: WarshipData) {
    val shipId = data.shipId
    val modulesTree = data.modulesTree
    val server = getCurrDomain()

    var module by remember { mutableStateOf(Module()) }
    var section by remember { mutableStateOf(makeSection(data)) }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionTitle(title = "Warship Modules")
        LazyColumn {
            items(section) { item ->
                ListItem(
                    modifier = Modifier.padding(8.dp),
                    text = { Text(item.title) },
                    secondaryText = { Text(item.description) },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) }
                )
            }
        }
    }
}

data class WarshipData(val shipId: String, val modulesTree: List<ModuleTree>)
data class Module(var artillery: String = "", var diveBomber: String = "", var engine: String = "", var fighter: String = "", var flightControl: String = "", var hull: String = "", var suo: String = "", var torpedoBomber: String = "", var torpedoes: String = "")
data class ModuleTree(val title: String, val description: String)

fun makeSection(data: WarshipData): List<ModuleTree> {
    // Implementation for creating sections from data
}

fun getCurrDomain(): String {
    // Implementation for getting current domain
}

fun WoWsInfoScreen(viewModel: YourViewModel) {
    val section by viewModel.section.collectAsState()

    WoWsInfo(
        hideAds = true,
        title = lang.warship_apply_module,
        onPress = { viewModel.apply() }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(section) { item ->
                    Column {
                        SectionTitle(title = item.title)
                        item.data.forEach { d ->
                            renderModule(d)
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        )
    }
}

    navController.popBackStack("WarshipDetail", false)
    CoroutineScope(Dispatchers.Main).launch {
        delay(1000)
        navController.navigate("currentRoute") {
            popUpTo("WarshipDetail") { inclusive = true }
            launchSingleTop = true
            restoreState = true
        }
    }
}

fun RenderModule(ID: String, state: YourStateType, updateModule: (tree: Map<String, ModuleType>, ID: String) -> Unit) {
    val tree = state.tree
    val module = state.module
    val moduleData = tree[ID] ?: return
    val name = moduleData.name
    val priceXp = moduleData.price_xp
    val priceCredit = moduleData.price_credit
    val xpStyle = styles.xp

    val selected = module.values.any { it == ID }

    val right: @Composable () -> Unit = {
        if (priceXp > 0) {
            Text(text = "$priceXp xp", style = xpStyle)
        }
    }

    ListItem(
        modifier = if (selected) Modifier.background(ThemeBackColour()) else Modifier,
        text = { Text(name) },
        secondaryText = { Text(priceCredit.toString()) },
        trailing = right,
        modifier = Modifier.clickable { updateModule(tree, ID) }
    )
}

    val module = state.module.toMutableMap()
    module[tree[id]?.type] = id
    setState { this.module = module }
}

    val (modules, modulesTree) = data

    val moduleName = AppGlobalData.get(SAVED.encyclopedia).ship_modules

    val section = mutableListOf<Section>()
    for ((key, curr) in modules) {
        if (curr.size > 1) {
            val sorted = curr.sortedWith { a, b ->
                val aM = modulesTree[a]!!
                val bM = modulesTree[b]!!
                when {
                    aM.price_xp != bM.price_xp -> aM.price_xp.compareTo(bM.price_xp)
                    aM.next_modules != null && bM.next_modules != null -> {
                        if (aM.next_modules[0] == b) -1 else 1
                    }
                    else -> if (aM.next_modules != null) -1 else 1
                }
            }

            val obj = Section(title = moduleName[normaliseKey(key)], data = sorted)
            section.add(obj)
        }
    }
    return section
}

    val names = key.split('_')
    val upperFirst: (String) -> String = { str -> str.replaceFirstChar { it.uppercase() } }
    val capitalizedNames = names.map { upperFirst(it) }

    var name = capitalizedNames.joinToString("")
    if (name == "FireControl") {
        name = "Suo"
    }
    return name
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}

    modifier = Modifier
        .padding(end = 4.dp)
        .align(Alignment.CenterHorizontally)
)


@Composable
fun WarshipModule() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Warship Module", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text("Click Me")
        }
    }
}