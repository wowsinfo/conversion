import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.data_table_2.data.Ship
import com.example.data_table_2.ui.theme.DataTableTwoTheme
import com.example.data_table_2.ui.viewmodels.CompareShipViewModel

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