import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.boatnavigator.R
import com.google.accompanist.pager.ExperimentalPagerApi

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