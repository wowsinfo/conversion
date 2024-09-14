import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.SafeAction
import com.example.data.AppGlobalData
import com.example.value.Constants.SAVED
import com.example.wiki_screen.component.WikiIcon
import com.example.wiki_screen.component.WoWsInfo

class Achievement : Component() {
    private val achievement by lazy { AppGlobalData.get(SAVED.achievement) }
    private val sorted = achievement.entries.sortedBy { (key, value) ->
        if (value.hidden == value.hidden) key else value.hidden
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLastLocation("Achievement")
        Log.d(TAG, "WIKI - Achievement")
        sorted.forEachIndexed { index, entry ->
            sorted[index] = entry.value
        }
        Log.d(TAG, sorted.toString())
    }

    @Composable
    override fun Content() {
        WoWsInfo {
            FlatGrid(
                itemDimension = 80.dp,
                data = sorted,
                onItemPressed = { item -> SafeAction("BasicDetail", intent = Intent().apply { putExtra("item", item) }) },
                showsVerticalScrollIndicator = false
            )
        }
    }

    companion object {
        const val TAG = "Achievement"
    }
}