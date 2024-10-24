
@Composable
fun NewItemIndicator(size: Dp? = null, color: Color? = null) {
    val sizeValue = size ?: 10.dp
    Row(
        modifier = Modifier.align(Alignment.BottomCenter),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Box(
            modifier = Modifier.size(sizeValue).clip(CircleShape).background(color ?: MaterialTheme.colorScheme.primary)
        )
    }
}