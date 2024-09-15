
@Composable
fun CompareShipPage(
    navController: NavController,
    viewModel: CompareShipViewModel = ViewModelProvider(LocalContext.current).get(CompareShipViewModel::class.java)
) {
    val ships by viewModel.ships.collectAsLazyPagingItems()
    var filter by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Compare Ship")
        Spacer(modifier = Modifier.weight(1f))
        if (ships.loadState is androidx.compose.runtime.ComposableLambda2<*, *, *>) {
            val composable =
                ships.loadState as androidx.compose.runtime.ComposableLambda2<*, *, *>
            composable.invoke(null, null)
        }
        Spacer()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { viewModel.filter(filter) }) {
                Icon(painterResource("filter"))
            }
            TextField(
                value = filter,
                onValueChange = { filter = it },
                label = { Text(text = "Filter") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }

    fun onRowClick(ship: Ship) {
        // TODO: implement onRowClick
    }
}

@Preview(showBackground = true)
@Composable
fun CompareShipPagePreview() {
    DataTableTwoTheme {
        CompareShipPage(navController = NavController(LocalContext.current), ViewModelProvider(LocalContext.current).get(CompareShipViewModel::class.java))
    }
}