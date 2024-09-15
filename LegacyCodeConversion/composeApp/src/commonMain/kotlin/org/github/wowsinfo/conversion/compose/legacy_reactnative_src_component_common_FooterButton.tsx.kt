import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.Cog
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FooterButton(icon: String, left: Boolean) {
    val iconRes = when (icon) {
        "cog" -> Icons.Filled.Cog
        "arrow-left" -> Icons.Filled.ArrowLeft
        "home" -> Icons.Filled.Home
        else -> null
    }

    IconButton(
        onClick = { /* Handle button press here */ },
        modifier = Modifier.padding(8)
    ) {
        if (iconRes != null) {
            Icon(imageVector = iconRes, contentDescription = "Icon")
        } else {
            Icon(painterResource(id = R.drawable.ic_default_icon), contentDescription = "Icon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFooterButton() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FooterButton("cog", true)
        Spacer(modifier = Modifier.height(16.dp))
        FooterButton("arrow-left", false)
        Spacer(modifier = Modifier.height(16.dp))
        FooterButton("home", true)
    }
}