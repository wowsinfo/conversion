import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconPlaceholder(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Image(
        painter = painterResource(id = R.drawable.placeholder_icon),
        contentDescription = null, // Provide a description for accessibility
        modifier = modifier.size(size),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(Color.Blue)
    )
}

@Preview(showBackground = true)
@Composable
fun IconPlaceholderPreview() {
    IconPlaceholder()
}