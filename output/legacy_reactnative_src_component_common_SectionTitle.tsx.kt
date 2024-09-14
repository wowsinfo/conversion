import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(
    title: String,
    back: Boolean = false,
    center: Boolean = false,
    style: Modifier = Modifier,
    bold: Boolean = false
) {
    val context = LocalContext.current
    Text(
        text = title,
        modifier = style,
        color = if (back) Color(ThemeBackColour(context)) else Color(PrimaryColor(context)),
        fontSize = 16.sp,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
        textAlign = if (center) androidx.compose.ui.text.TextAlign.Center else androidx.compose.ui.text.TextAlign.Start
    )
}

@Preview(showBackground = true)
@Composable
fun SectionTitlePreview() {
    SectionTitle("Example Title")
}