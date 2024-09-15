import androidx.compose.foundation.Image
import androidx.compose.foundation.StackBox
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ShipIcon(
    icon: String,
    height: Dp = 80.dp,
    width: Dp? = null,
    hero: Boolean = false,
    isNew: Boolean = false
) {
    val fullIcon = "data/live/app/assets/ships/$icon.png"
    Box(modifier = Modifier.size(height, width)) {
        StackBox(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            children = {
                Image(
                    painter = painterResource(id = fullIcon),
                    contentDescription = null
                )
                if (isNew) NewItemIndicator()
            }
        )
    }

    if (hero) {
        Hero(tag = icon, child = ShipIcon(icon = icon, height = height, width = width, hero = false, isNew = isNew))
    }
}