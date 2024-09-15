import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Caption
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpackcompose.R

@Composable
fun InfoLabel(
    title: String,
    info: String,
    left: Boolean = false,
    right: Boolean = false,
    style: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = if (left) Alignment.Start else if (right) Alignment.End else Alignment.CenterHorizontally
    ) {
        Caption(text = title, textAlign = TextAlign.Center)
        Text(
            text = info,
            style = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center).merge(style),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun styles() {
    val container = Modifier.align(Alignment.CenterHorizontally)
}