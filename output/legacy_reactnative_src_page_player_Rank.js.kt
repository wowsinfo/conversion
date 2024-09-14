import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wws.R
import com.example.wws.component.Info6Icon
import com.example.wws.core.SafeAction

@Composable
fun RankScreen(data: Map<Int, Any>, shipData: Map<Int, List<Any>>) {
    val list = data.values.reversed().toMutableList()
    val sortedData = list.map { it as Map<String, Any> }.sortedByDescending { it["season"] }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Rank - ${data.size}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            sortedData.forEach { season ->
                val seasonNumber = season["season"] as Int
                val shipInfo = shipData[seasonNumber]
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable {
                            if (shipInfo != null && shipInfo.isNotEmpty()) {
                                SafeAction("PlayerShip", mapOf("data" to shipInfo))
                            }
                        }
                ) {
                    Text(
                        text = "- Season $seasonNumber -",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (shipInfo != null && shipInfo.isNotEmpty()) {
                        Icon(
                            painter = painterResource(R.drawable.ic_chevron_right_24px),
                            contentDescription = "Navigate",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}