import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Column
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextStyle

@Composable
fun TextWithCaption(
    title: String = "",
    value: String = "",
    titleWidget: @Composable (() -> Unit)? = null,
    valueWidget: @Composable (() -> Unit)? = null,
) {
    Column(modifier = Modifier.padding(top = 4.dp)) {
        if (titleWidget != null) {
            titleWidget()
        } else {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
        if (valueWidget != null) {
            valueWidget()
        } else {
            Text(
                text = value,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}