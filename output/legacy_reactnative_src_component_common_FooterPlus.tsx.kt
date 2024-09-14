import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FooterPlus(context: Context, children: @Composable () -> Unit) {
  val similarView = remember {
    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
  }
  with(MaterialTheme.colorScheme) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(background, shape = similarView),
    ) { children() }
  }
}