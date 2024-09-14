import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.wowsinfo.R
import com.example.wowsinfo.core.domain.model.Ship
import com.example.wowsinfo.presentation.screens.ship.ShipScreenViewModel
import com.example.wowsinfo.presentation.shared.wiki.WikiShipIcon
import com.example.wowsinfo.presentation.shared.wiki.WikiShipName

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