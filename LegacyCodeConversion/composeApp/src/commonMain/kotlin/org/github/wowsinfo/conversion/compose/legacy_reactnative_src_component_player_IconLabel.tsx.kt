import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun IconLabel(info: Any, iconResId: Int, style: Modifier = Modifier) {
    Row(
        modifier = style.padding(4.dp).align(Alignment.Center),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /* Do something when clicked */ }) {
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        setImageResource(iconResId)
                    }
                },
                modifier = Modifier.size(36.dp),
                update = { view ->
                    view.setImageResource(iconResId)
                }
            )
        }
        Text(text = info.toString(), style = androidx.compose.ui.text.style.TextStyle(fontSize = 14.sp))
    }
}