
@Composable
fun IconInkWell(
    icon: Int,
    onTap: (() -> Unit)? = null,
    size: Double? = 48.0,
) {
    val actualSize = size?.dp ?: 48.dp
    Box(
        modifier = Modifier.size(actualSize),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        ) {
            Icon(
                iconResource = icon,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(actualSize)
            )
        }
    }
}