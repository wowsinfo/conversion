import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

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