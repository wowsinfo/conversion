import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wonsikwon.woows.ui.component.LoadingModal
import com.wonsikwon.woows.ui.component.WikiIcon
import com.wonsikwon.woows.value.AppGlobalData
import com.wonsikwon.woows.value.SAVED
import com.wonsikwon.woows.core.SafeAction

@Composable
fun ConsumableScreen(upgrade: Boolean = false) {
    val context = LocalContext.current
    setLastLocation(context, if (upgrade) "Upgrade" else "Consumable")

    // Load data depending on 'upgrade' prop
    val consumables = AppGlobalData.get(SAVED.consumable)
    val data = mutableListOf<WoWsInfo>()

    for (key in consumables.keys) {
        val current = consumables[key]
        if ((upgrade && current.type == "Modernization") || (!upgrade && current.type != "Modernization")) {
            data.add(current)
        }
    }

    // Sort by price
    data.sortWith(compareByDescending { item ->
        if (upgrade) {
            item.priceGold ?: 0
        } else {
            if (item.type == "Flags") -1 else 1
        }
    })

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        items(data) { item ->
            WikiIcon(
                item = item,
                modifier = Modifier.clickable {
                    SafeAction(context, "BasicDetail", mapOf("item" to item))
                }
            )
        }
    }
}

fun setLastLocation(context: Context, loc: String) {
    val sharedPref = context.getSharedPreferences("WoWsPreferences", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("lastLocation", loc)
        apply()
    }
}