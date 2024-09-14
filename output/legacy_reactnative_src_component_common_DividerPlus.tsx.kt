import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable

@Composable
fun DividerPlus() {
    Divider(
        modifier = Modifier.fillMaxSize(),
        thickness = 8.dp
    )
}