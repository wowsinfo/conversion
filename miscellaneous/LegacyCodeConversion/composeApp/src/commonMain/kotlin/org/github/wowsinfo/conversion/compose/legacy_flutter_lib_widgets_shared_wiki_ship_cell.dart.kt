
@Composable
fun ShipCell(
    ship: Ship,
    viewModel: ShipScreenViewModel,
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Column(modifier = modifier) {
        WikiShipIcon(ship.icon, isNew = ship.isNew)
        WikiShipName(
            name = stringResource(id = ship.nameResId),
            isPremium = ship.isPremium,
            isSpecial = ship.isSpecial
        )
    }
    .clickable { navController?.navigateTo(viewModel.getShipDetailsRoute(ship)) }
}