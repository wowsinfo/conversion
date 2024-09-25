
data class TabButtonProps(
    val icon: @Composable () -> Unit,
    val disabled: Boolean = false,
    val onPress: (() -> Unit)? = null,
    val otherProps: Map<String, Any>? = null
)

@Composable
fun TabButton(
    icon: @Composable () -> Unit,
    onPress: (() -> Unit)? = null,
    disabled: Boolean = false,
    otherProps: Map<String, Any>? = null
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .padding(4.dp)
            .let { if (disabled) it else it.clickable { onPress?.invoke() } }
    ) {
        IconButton(onClick = { onPress?.invoke() }) {
            icon()
        }
    }
}

@Preview
@Composable
fun PreviewTabButton() {
    TabButton(
        icon = { Icon(/* Your icon here */, contentDescription = null) },
        onPress = { /* Handle press */ }
    )
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { state = !state }) {
            Text(text = if (state) "ON" else "OFF")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current state: ${if (state) "ON" else "OFF"}")
    }
}