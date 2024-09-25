
@Composable
fun Touchable(
    modifier: Modifier = Modifier,
    fill: Boolean = false,
    onPress: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onPress?.invoke() },
        modifier = if (fill) modifier.fillMaxSize() else modifier
    ) {
        Box(modifier = modifier) {
            content()
        }
    }
}