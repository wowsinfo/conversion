import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WarshipLabel(style: Modifier = Modifier, item: Any?) {
    val labelStyle = style.then(
        when (item is Map<*, *> && item["premium"] as Boolean) {
            true -> MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.secondary)
            else -> MaterialTheme.typography.bodyLarge
        }
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val tierLabel = if (item is Map<*, *> && item["tier"] != null) {
            getTierLabel(item["tier"] as Int)
        } else {
            lang.warship_unknown
        }
        Paragraph(
            text = "${tierLabel} ${item?.get("name") ?: ""}",
            style = labelStyle,
            modifier = Modifier.padding(16.dp)
        )
    }
}

fun getTierLabel(tier: Int) = when (tier) {
    1 -> lang.warship_tier_1
    2 -> lang.warship_tier_2
    3 -> lang.warship_tier_3
    4 -> lang.warship_tier_4
    5 -> lang.warship_tier_5
    else -> ""
}