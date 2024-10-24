
@Composable
fun Touchable(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.surface,
    shape: Shape = MaterialTheme.shapes.medium,
    contentColor: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit,
    children: @Composable() () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = shape,
            color = color,
            contentColor = contentColor
        ) {
            children()
        }
    }
}