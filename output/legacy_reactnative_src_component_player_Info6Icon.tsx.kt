import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.placeholder.PlaceholderHighlight
import dev.chrisbanes.accompanist.placeholder.shimmer
import java.util.*

@Composable
fun Info6Icon(data: Data?, compact: Boolean, topOnly: Boolean) {
    val cellWidth = bestWidth(100)
    var bestItemWidth: Int? = null

    Box(
        modifier = Modifier.onLayout { event ->
            val goodWidth = event.size.width
            bestItemWidth = bestWidth(400, goodWidth)
        }
    ) {
        Row(modifier = if (compact) Modifier else Modifier.padding(top = 16.dp)) {
            IconLabel(icon = R.drawable.battle_icon, info = data?.battles.toString(), style = Modifier.weight(cellWidth))
            IconLabel(
                icon = R.drawable.win_rate_icon,
                info = "${roundTo((data?.wins?.toDouble() ?: 0.0) / (data?.battles?.toDouble() ?: 1.0) * 100, 2)}%",
                style = Modifier.weight(cellWidth)
            )
            IconLabel(
                icon = R.drawable.damage_icon,
                info = roundTo(data?.damage_dealt?.div((data?.battles?.toLong() ?: 1L).toDouble())).toString(),
                style = Modifier.weight(cellWidth)
            )
            if (!topOnly) {
                IconLabel(icon = R.drawable.exp_icon, info = roundTo(data?.xp?.div((data?.battles?.toLong() ?: 1L).toDouble())).toString(), style = Modifier.weight(cellWidth))
                IconLabel(
                    icon = R.drawable.kill_death_ratio_icon,
                    info = roundTo((data?.frags?.div((data?.death?.toLong() ?: 1L).toDouble()))!!, 2).toString(),
                    style = Modifier.weight(cellWidth)
                )
                IconLabel(icon = R.drawable.hit_ratio_icon, info = "${roundTo(((data?.main_battery?.hits?.toLong() ?: 0L) / (data?.main_battery?.shots?.toLong() ?: 1L)).toDouble() * 100, 2)}%", style = Modifier.weight(cellWidth))
            }
        }

    }
}

@Composable
fun IconLabel(icon: Int, info: String, style: Modifier) {
    Row(modifier = style.fillMaxWidth()) {
        Image(painterResource(id = icon), contentDescription = null)
        Text(info, modifier = Modifier.padding(start = 4.dp))
    }
}