
fun showFilterShipDialog(
    onFilter: (ShipFilter) -> Unit
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }
    
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Filter Ships") },
            text = {
                val filterShipProvider: FilterShipProvider = getViewModel()
                // Add UI elements to interact with filterShipProvider
            },
            confirmButton = {
                // Add confirm button logic to call onFilter with the selected ShipFilter
            },
            dismissButton = {
                // Add dismiss button logic
            }
        )
    }
}

class ShipFilter

class FilterShipProvider {
    // Implement the provider logic here
}

fun ShipFilterDialog(onFilter: (ShipFilter) -> Unit) {
    val provider = remember { FilterShipProvider() }
    val nameState = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { /* Handle dismiss */ }) {
        Surface {
            Column {
                LazyColumn {
                    item {
                        Text(
                            text = Localisation.instance.shipNameFilterName,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    item {
                        TextField(
                            value = nameState.value,
                            onValueChange = { nameState.value = it },
                            placeholder = { Text("...") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
                        )
                    }
                    item { Divider() }
                    item {
                        Text(
                            text = Localisation.instance.tierFilterName,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    item { TierList(provider) }
                    item { Divider() }
                    item {
                        Text(
                            text = Localisation.instance.shipTypeFilterName,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    item { TypeList(provider) }
                    item { Divider() }
                    item {
                        Text(
                            text = provider.regionFilterName,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    item { RegionList(provider) }
                }
                Divider()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { provider.resetAll() }) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                    }
                    IconButton(onClick = {
                        onFilter(provider.onFilter(nameState.value))
                    }) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                }
            }
        }
    }
}

fun RenderListTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(start = 8.dp),
        style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.primary)
    )
}

fun RenderRegionList(viewModel: FilterShipViewModel) {
    val regionList = viewModel.regionList.collectAsState()
    val isRegionSelected = viewModel.isRegionSelected.collectAsState()

    FilterList(
        filterList = regionList.value,
        isSelected = isRegionSelected.value,
        onSelected = { region -> viewModel.updateRegion(region) }
    )
}

@Composable
fun FilterList(filterList: List<Region>, isSelected: (Region) -> Boolean, onSelected: (Region) -> Unit) {
    LazyColumn {
        items(filterList) { region ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelected(region) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isSelected(region),
                    onCheckedChange = { onSelected(region) }
                )
                Text(text = region.name, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

fun RenderTypeList(context: Context) {
    val provider = LocalContext.current as FilterShipProvider
    FilterList(
        filterList = provider.typeList,
        isSelected = provider.isTypeSelected,
        onSelected = { type -> provider.updateType(type) }
    )
}

@Composable
fun FilterList(filterList: List<Type>, isSelected: (Type) -> Boolean, onSelected: (Type) -> Unit) {
    LazyColumn {
        items(filterList) { type ->
            val selected = isSelected(type)
            Text(
                text = type.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelected(type) }
                    .background(if (selected) Color.Gray else Color.Transparent)
                    .padding(16.dp)
            )
        }
    }
}

fun RenderTierList(viewModel: FilterShipViewModel) {
    val tierList by viewModel.tierList.collectAsState()
    val isTierSelected by viewModel.isTierSelected.collectAsState()

    FilterList(
        filterList = tierList,
        isSelected = isTierSelected,
        onSelected = { tier -> viewModel.updateTier(tier) }
    )
}


@Composable
fun FilterList(
    filterList: List<String>,
    isSelected: (String) -> Boolean,
    onSelected: (String) -> Unit
) {
    Column {
        filterList.forEach { filter ->
            Chip(
                modifier = Modifier.padding(4.dp),
                onClick = { onSelected(filter) },
                colors = if (isSelected(filter)) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary
                }
            ) {
                Text(text = filter)
            }
        }
    }
}

@Preview
@Composable
fun PreviewFilterList() {
    val filters = listOf("Filter 1", "Filter 2", "Filter 3")
    val selectedFilters = remember { mutableStateOf(setOf<String>()) }

    FilterList(
        filterList = filters,
        isSelected = { selectedFilters.value.contains(it) },
        onSelected = { filter ->
            selectedFilters.value = if (selectedFilters.value.contains(filter)) {
                selectedFilters.value - filter
            } else {
                selectedFilters.value + filter
            }
        }
    )
}

fun FilterList(
    filterList: List<String>,
    isSelected: (Int) -> Boolean,
    onSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier.padding(4.dp).fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        LazyColumn {
            itemsIndexed(filterList) { index, filter ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelected(filter) }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isSelected(index),
                        onCheckedChange = { onSelected(filter) }
                    )
                    Text(
                        text = filter,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
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
            Text("Click Me")
        }
    }
}