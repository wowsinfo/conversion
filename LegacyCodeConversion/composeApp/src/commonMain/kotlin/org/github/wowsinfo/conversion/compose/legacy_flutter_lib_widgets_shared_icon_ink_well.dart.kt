import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

@Composable
fun IconInkWell(
    icon: Int,
    onTap: (() -> Unit)? = null,
    size: Double? = 48.0,
) {
    val actualSize = size?.dp ?: 48.dp
    Box(
        modifier = Modifier.size(actualSize),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        ) {
            Icon(
                iconResource = icon,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(actualSize)
            )
        }
    }
}