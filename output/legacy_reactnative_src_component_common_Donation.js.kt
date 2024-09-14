import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.henryquan.wowsinfo.value.data.APP
import com.henryquan.wowsinfo.view.core.SimpleViewHandler

@Composable
fun Donation() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        // They won't allow wechat and paypal
        if (!AppGlobalData.githubVersion) {
            List(
                items = listOf(
                    "GitHub",
                    "https://github.com/HenryQuan/WoWs-Info-Origin"
                ),
                onClick = { _, data ->
                    SimpleViewHandler.openURL(context, data)
                }
            )
        } else {
            List(
                items = listOf(APP.Patreon, APP.PayPal, APP.WeChat),
                onClick = { _, data ->
                    SimpleViewHandler.openURL(context, data)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Donation()
        }
    }
}