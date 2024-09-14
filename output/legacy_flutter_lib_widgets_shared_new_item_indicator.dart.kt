import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun NewItemIndicator(size: Dp? = null, color: Color? = null) {
    val sizeValue = size ?: 10.dp
    Row(
        modifier = Modifier.align(Alignment.BottomCenter),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Box(
            modifier = Modifier.size(sizeValue).clip(CircleShape).background(color ?: MaterialTheme.colorScheme.primary)
        )
    }
}