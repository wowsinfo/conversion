
@Composable
fun MaxWidthBox(maxWidth: Double? = null, content: @Composable () -> Unit) {
    BoxWithConstraints(
        modifier = Modifier.maxSize(width = maxWidth ?: ScreenSize.maxDialogWidth),
        contentAlignment = Alignment.Center,
        content = content
    )
}