import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun SafeAction(screen: String, obj: Any? = null, max: Int = 0) {
    val context = LocalContext.current

    if (context.resources.getStringArray(R.array.screenNames).contains(screen)) {
        Log.d("SafeAction", "$screen rejected")
    } else {
        // Assuming we have a method named navigateTo in our ViewModel or Navigator
        viewModel.navigateTo(screen, obj)
        Log.d("SafeAction", "$screen pushed")
    }
}