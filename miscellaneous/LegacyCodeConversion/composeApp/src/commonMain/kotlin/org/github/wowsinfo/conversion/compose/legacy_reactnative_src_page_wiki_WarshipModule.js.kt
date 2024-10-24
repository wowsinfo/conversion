
@Composable
fun WarshipModule(
    ship_id: Int,
    modules_tree: Map<Int, Any>,
) {
    val server = getCurrDomain()
    val module = remember { mutableMapOf<String, Int>() }
    val tree = modules_tree
    // Data needed for section grid
    val section = remember(ship_id, modules_tree) { makeSection(ship_id, modules_tree) }

    LazyColumn {
        item {
            WoWsInfo(
                hideAds = true,
                title = stringResource(id = R.string.warship_apply_module),
                onPress = { apply() }
            )
        }
        itemsIndexed(section) { index, item ->
            SectionTitle(title = item.title)
            item.data.forEach { moduleId ->
                renderModule(moduleId, tree)
            }
            if (index < section.size - 1) {
                Divider()
            }
        }

        // Add space to the end of the list
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun apply() {
    // TODO: Implement apply logic
}

@Composable
fun renderModule(moduleId: Int, tree: Map<Int, Any>) {
    val name = tree[moduleId]?.name ?: ""
    val priceXp = tree[moduleId]?.price_xp ?: 0
    val priceCredit = tree[moduleId]?.price_credit ?: 0

    ListItem(
        modifier = Modifier.clickable { updateModule(moduleId) },
        trailing = {
            if (priceXp > 0) {
                Caption(text = "$priceXp xp", style = MaterialTheme.typography.caption)
            }
        }
    ) {
        Text(text = name, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = priceCredit.toString(),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.End
        )
    }
}

private fun updateModule(moduleId: Int) {
    // TODO: Implement update module logic
}

@Composable
fun makeSection(ship_id: Int, modules_tree: Map<Int, Any>): List<Map<String, Any>> {
    val modules = remember { mutableMapOf<String, List<Int>>() }
    val moduleName = AppGlobalData.get(SAVED.encyclopedia).ship_modules

    // TODO: Implement make section logic
    return emptyList()
}