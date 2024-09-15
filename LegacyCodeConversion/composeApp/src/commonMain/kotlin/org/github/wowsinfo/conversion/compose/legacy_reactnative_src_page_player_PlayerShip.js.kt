import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun FooterPlus(
    content: @Composable () -> Unit,
) {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        content()
    }
}