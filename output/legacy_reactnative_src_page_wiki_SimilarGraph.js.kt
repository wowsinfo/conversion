import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimilarGraph(info: List<Any>) {
    WoWsInfo {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            info.forEachIndexed { index, data ->
                // Replace with your Composable for displaying individual items
                Text(text = "Item $index: $data")
            }
        }
    }
}