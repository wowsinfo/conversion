import android.util.Log
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wows.CalculatorApp.Companion.AppGlobalData
import com.example.wows.component.WoWsInfo
import com.example.wows.core.SafeAction
import com.example.wows.value.data.SAVED
import com.example.wows.value.lang

/**
 * Warship.kt
 *
 * This is wiki warship
 */

@Composable
fun Warship(filter: Map<String, Int?>) {
    val lastLocation = "Warship"
    Log.d("WIKI", lastLocation)
    setLastLocation(lastLocation)

    val warship = AppGlobalData.get(SAVED.warship)
    val sorted = warship.entries.sortedWith(compareByDescending { (_, value) -> value.new })
        .thenBy { (name, _) -> name }
    Log.d("WIKI", sorted.toString())

    var filteredData by remember {
        mutableStateOf(sorted.map { it.value })
    }

    if (filter.isNotEmpty()) {
        val filtered = filterShip(filter)
        filteredData = if (filtered == null) {
            sorted.map { it.value }
        } else {
            filtered
        }
    }

    WoWsInfo(
        title = "${lang.wiki_warship_footer} - ${filteredData.size}",
        modifier = Modifier.fillMaxSize(),
        onPress = { SafeAction("WarshipFilter", mapOf("applyFunc" to ::updateShip)) }
    ) {
        LazyVerticalGrid(
            columns = 3,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(filteredData.size) { index ->
                WarshipCell(
                    item = filteredData[index],
                    modifier = Modifier.weight(1f),
                    scale = bestCellWidthEven(80).toFloat() / 40
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        if (filter.isNotEmpty()) {
            updateShip(filter)
        }
    }

}

@Composable
private fun updateShip(data: Map<String, Int?>) {
    val filtered = filterShip(data)
    val sorted =
        if (filtered == null) {
            AppGlobalData.get(SAVED.warship).entries.sortedWith(compareByDescending { (_, value) -> value.new })
                .thenBy { (name, _) -> name }
        } else {
            filtered.entries.sortedWith(compareByDescending { (_, value) -> value?.new ?: false })
                .thenBy { (name, _) -> name }
        }
    val filteredData = sorted.map { it.value }
    println(filteredData)
}