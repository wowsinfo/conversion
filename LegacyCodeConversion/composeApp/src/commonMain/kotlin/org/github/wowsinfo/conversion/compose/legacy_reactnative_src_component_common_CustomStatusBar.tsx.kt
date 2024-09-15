
@Composable
fun CustomStatusBar(
    children: @Composable () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    dark: Boolean = false,
) {
    AndroidView(
        factory = {
            View(it).apply {
                setBackgroundColor(backgroundColor.toArgb())
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { view ->
        children()
        if (dark) {
            android.os.Build.VERSION.SDK_INT.takeIf { it >= android.os.Build.VERSION_CODES.M }?.let {
                view.systemUiVisibility = view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        } else {
            android.os.Build.VERSION.SDK_INT.takeIf { it < android.os.Build.VERSION_CODES.M }?.let {
                view.systemUiVisibility = view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }
}