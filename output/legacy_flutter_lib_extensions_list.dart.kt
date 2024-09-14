import androidx.compose.runtime.Composable

fun List<*>.enumerate() = asMap().entries.map { (key, value) -> key to value }