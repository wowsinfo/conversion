import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable

/**
 * Space.kt
 *
 * Add space to component
 */

@Composable
fun Space(height: Int) {
    Box(
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
    )
}