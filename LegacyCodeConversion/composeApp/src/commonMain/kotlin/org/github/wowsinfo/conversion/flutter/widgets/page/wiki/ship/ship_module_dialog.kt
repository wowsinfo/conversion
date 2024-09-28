
@Composable
fun showShipModuleDialog(
    modules: ShipModuleMap,
    selection: ShipModuleSelection,
    onUpdate: (ShipModuleSelection) -> Unit
) {
    val context = LocalContext.current
    val shipModuleProvider: ShipModuleProvider = getViewModel { parametersOf(modules, ShipModuleSelection.fromSelection(selection)) }

    AlertDialog(
        onDismissRequest = { /* Handle dismiss */ },
        title = { Text("Select Ship Module") },
        text = { ShipModuleDialogContent(modules, shipModuleProvider, onUpdate) },
        confirmButton = { /* Add confirm button */ },
        dismissButton = { /* Add dismiss button */ }
    )
}

@Composable
fun ShipModuleDialogContent(
    modules: ShipModuleMap,
    shipModuleProvider: ShipModuleProvider,
    onUpdate: (ShipModuleSelection) -> Unit
) {
    // Implement the content of the dialog using Jetpack Compose
}

fun ShipModuleDialog(modules: ShipModuleMap, onUpdate: (ShipModuleSelection) -> Unit) {
    Dialog(onDismissRequest = { /* Handle dismiss */ }) {
        Surface {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                renderModuleMap(modules, onUpdate)
            }
        }
    }
}

@Composable
fun renderModuleMap(modules: ShipModuleMap, onUpdate: (ShipModuleSelection) -> Unit) {
    // Implement the rendering logic for the module map
}

fun RenderModuleMap(context: Context, provider: ShipModuleProvider, modules: Map<ModuleType, List<Module>>) {
    val entries = modules.entries
    Column {
        entries.forEachIndexed { index, entry ->
            val moduleType = entry.key
            val list = entry.value
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = moduleType.name ?: "X",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
            list.forEachIndexed { moduleIndex, module ->
                BuildModuleListTile(
                    context = context,
                    moduleKey = moduleIndex,
                    isSelected = provider.isSelected(moduleType, moduleIndex),
                    moduleValue = module,
                    updateSelection = provider::updateSelection
                )
            }
            if (index != entries.lastIndex) {
                Divider()
            }
            if (index == entries.lastIndex) {
                Button(
                    onClick = {
                        // Assuming there's a method to dismiss the dialog
                        dismissDialog()
                        onUpdate(provider.selection)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Update")
                }
            }
        }
    }
}

fun ModuleListTile(
    context: Context,
    index: Int,
    selected: Boolean,
    module: ShipModuleHolder,
    onChange: (ShipModuleType, Int) -> Unit
) {
    val info = module.module!!
    val type = module.type
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChange(type, index) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = { onChange(type, index) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = Localisation.instance.stringOf(info.name) ?: "")
            Text(
                text = info.cost.costCr.toString(),
                color = WoWsColours.creditPrice
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${info.cost.costXp} XP")
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Greeting("World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello, $name!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}