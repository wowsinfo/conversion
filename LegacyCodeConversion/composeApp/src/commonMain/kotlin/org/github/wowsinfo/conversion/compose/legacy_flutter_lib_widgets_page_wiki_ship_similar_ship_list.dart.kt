import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wowsinfo.Routes
import com.example.wowsinfo.models.GameData.Ship
import com.example.wowsinfo.utils.Localisation
import com.example.wowsinfo.widgets.shared.Wiki.ShipCell

@Composable
fun SimilarShipList(
  source: Ship,
  ships: List<Ship>,
  navController: NavController
) {
  Column(
    modifier = Modifier.padding(horizontal = 16.dp),
    crossAxisAlignment = CrossAxisAlignment.stretch,
    mainAxisSize = MainAxisSize.Min,
    children = listOf(
      ElevatedButton(onClick = { 
        navController.navigate(Routes.ShipSimilar.routeName, 
          args = ShipSimilarPageArgs(source))
      }) {
        Text(Localisation.getString(R.string.warship_compare_similar))
      },
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        children = ships.map { ship ->
          ShipCell(
            icon = ship.index,
            name = Localisation.getString(ship.name),
            isPremium = ship.isPremium,
            isSpecial = ship.isSpecial,
            onClick = {
              navController.navigate(Routes.ShipInfo.routeName, 
                args = ShipInfoPageArgs(ship))
            }
          )
        }
      )
    )
  )
}