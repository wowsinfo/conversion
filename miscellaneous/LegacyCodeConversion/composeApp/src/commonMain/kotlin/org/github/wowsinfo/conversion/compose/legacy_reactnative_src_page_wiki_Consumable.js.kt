
@Composable
fun ConsumableScreen(upgrade: Boolean = false) {
    val context = LocalContext.current
    setLastLocation(context, if (upgrade) "Upgrade" else "Consumable")

    // Load data depending on 'upgrade' prop
    val consumables = AppGlobalData.get(SAVED.consumable)
    val data = mutableListOf<WoWsInfo>()

    for (key in consumables.keys) {
        val current = consumables[key]
        if ((upgrade && current.type == "Modernization") || (!upgrade && current.type != "Modernization")) {
            data.add(current)
        }
    }

    // Sort by price
    data.sortWith(compareByDescending { item ->
        if (upgrade) {
            item.priceGold ?: 0
        } else {
            if (item.type == "Flags") -1 else 1
        }
    })

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        items(data) { item ->
            WikiIcon(
                item = item,
                modifier = Modifier.clickable {
                    SafeAction(context, "BasicDetail", mapOf("item" to item))
                }
            )
        }
    }
}

fun setLastLocation(context: Context, loc: String) {
    val sharedPref = context.getSharedPreferences("WoWsPreferences", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("lastLocation", loc)
        apply()
    }
}