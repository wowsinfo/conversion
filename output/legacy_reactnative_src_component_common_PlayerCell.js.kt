import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PlayerCell(item: Any, player: Boolean, clan: Boolean, width: Int) {
    val playerId = if (player && item is Player) item.account_id else null
    val clanId = if (clan && item is Clan) item.clan_id else null
    Column(
        modifier = Modifier
            .clickable { /* Handle click */ }
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = item.nickname ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            if (playerId != null || clanId != null) {
                Caption(
                    text = playerId?.toString() ?: clanId.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

data class Player(val nickname: String, val account_id: Int)

data class Clan(val tag: String, val clan_id: Int)