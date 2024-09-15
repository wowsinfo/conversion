
@Composable
fun DebugPage() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pages")
        Button(onClick = {
            App.router?.navigateTo("${AppRoute.WikiShip.route}?special=true")
        }) {
            Text("Ships (Special)")
        }
        Button(onClick = {
            App.router?.navigateTo(AppRoute.AppSettings.route)
        }) {
            Text("App Settings")
        }

        Text("Dialogs")
        Button(onClick = {
            showFilterShipDialog(context) { _: Unit -> }
        }) {
            Text("Ship Filter Dialog")
        }

        Text("Game Languages")
        Localisation.instance.supportedGameLanguages.forEach { lang ->
            Button(onClick = {
                Localisation.instance.updateDataLanguage(lang)
            }) {
                Text(lang)
            }
        }
    }
}