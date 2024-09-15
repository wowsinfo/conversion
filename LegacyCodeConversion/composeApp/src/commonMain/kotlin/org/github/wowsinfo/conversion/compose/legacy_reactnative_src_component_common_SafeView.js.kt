import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SafeView(
    theme: Any,
    style: Modifier = Modifier,
    children: @Composable() () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    AndroidView(
        factory = { context ->
            // Replace with your native implementation of SafeAreaView and Surface
            // For example, you can use a combination of `FrameLayout` and `Surface`
            // to achieve the same effect.
        },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            systemUiController.setSystemBarsColor(
                color = Color.Unspecified,
                darkIcons = false
            )
        }
    ) {
        children()
    }
}