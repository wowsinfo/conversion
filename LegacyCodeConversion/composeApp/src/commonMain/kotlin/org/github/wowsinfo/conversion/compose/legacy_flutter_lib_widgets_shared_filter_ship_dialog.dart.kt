
@Composable
fun showFilterShipDialog(
    context: Context,
    onFilter: (ShipFilter) -> Unit
) {
    Dialog(onDismissRequest = { /* TODO: Add logic to handle dialog dismiss */ }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Expanded(
                content = {
                    ScrollView(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            mainAxisSize = MainAxisSize.Min,
                            crossAxisAlignment = CrossAxisAlignment.Start
                        ) {
                            renderListTitle(
                                context = context,
                                title = Localisation.instance.shipNameFilterName
                            )
                            renderTextField(context)
                            Divider()
                            renderListTitle(
                                context = context,
                                title = Localisation.instance.tierFilterName
                            )
                            renderTierList(context)
                            Divider()
                            renderListTitle(
                                context = context,
                                title = Localisation.instance.shipTypeFilterName
                            )
                            renderTypeList(context)
                            Divider()
                            renderListTitle(
                                context = context,
                                title = provider.regionFilterName
                            )
                            renderRegionList(context)
                        }
                    }
                }
            )
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = { /* TODO: Add logic to handle reset button */ },
                    icon = Icon(Icons.refresh)
                )
                IconButton(
                    onClick = { onFilter(provider.onFilter(remember { nameController.text })) },
                    icon = Icon(Icons.check)
                )
            }
        }
    }
}

@Composable
fun renderListTitle(context: Context, title: String) {
    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = title,
        style = TextStyle(color = MaterialTheme.of(context).colorScheme.primary)
            .copyOf(fontWeight = FontWeight.W500)
    )
}

@Composable
fun renderTextField(context: Context): TextField {
    val nameController = remember { TextEditingController() }
    return TextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        controller = nameController,
        decoration = InputDecoration(border = null, hintText = "...")
    )
}

@Composable
fun renderTierList(context: Context) {
    val provider = LocalProvider.current<FilterShipProvider>()
    _FilterList(
        filterList = provider.tierList,
        isSelected = { key -> provider.isTierSelected(key) },
        onSelected = { value -> provider.updateTier(value) }
    )
}

@Composable
fun renderTypeList(context: Context) {
    val provider = LocalProvider.current<FilterShipProvider>()
    _FilterList(
        filterList = provider.typeList,
        isSelected = { key -> provider.isTypeSelected(key) },
        onSelected = { value -> provider.updateType(value) }
    )
}

@Composable
fun renderRegionList(context: Context) {
    val provider = LocalProvider.current<FilterShipProvider>()
    _FilterList(
        filterList = provider.regionList,
        isSelected = { key -> provider.isRegionSelected(key) },
        onSelected = { value -> provider.updateRegion(value) }
    )
}

@Composable
fun _FilterList(
    filterList: List<String>,
    isSelected: (Int) -> Boolean,
    onSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier.align(Alignment.TopStart).padding(4.dp)
    ) {
        Wrap(
            crossAxisAlignment = WrapCrossAlignment.Center,
            spacing = if (App.isMobile) (-8).dp else 8.dp,
            runSpacing = 8.dp,
            children = filterList.withIndex().map { item ->
                Row(modifier = Modifier.clickable(onClick = { onSelected(item.value) })) {
                    Checkbox(
                        checked = isSelected(item.index),
                        onCheckedChange = { onSelected(item.value) }
                    )
                    Text(
                        text = item.value,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = TextStyle(fontWeight = FontWeight.W500)
                    )
                }
            }
        )
    }
}