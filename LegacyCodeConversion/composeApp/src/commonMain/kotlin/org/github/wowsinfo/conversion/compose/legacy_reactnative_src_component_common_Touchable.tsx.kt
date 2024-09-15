import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.core.ui.theme.TouchableTheme

@Composable
fun Touchable(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.surface,
    shape: Shape = MaterialTheme.shapes.medium,
    contentColor: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit,
    children: @Composable() () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = shape,
            color = color,
            contentColor = contentColor
        ) {
            children()
        }
    }
}