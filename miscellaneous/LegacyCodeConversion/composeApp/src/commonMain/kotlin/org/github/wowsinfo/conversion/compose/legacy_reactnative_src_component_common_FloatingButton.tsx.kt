
@OptIn(ExperimentalPagerApi::class)
@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    FloatingActionButton(
        onClick = { /* Handle navigation logic here */ },
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(Icons.Default.Ship, contentDescription = "Ship")
    }
}