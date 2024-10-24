
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