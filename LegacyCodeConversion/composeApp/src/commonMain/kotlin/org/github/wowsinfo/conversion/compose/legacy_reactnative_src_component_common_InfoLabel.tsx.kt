
@Composable
fun InfoLabel(
    title: String,
    info: String,
    left: Boolean = false,
    right: Boolean = false,
    style: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = if (left) Alignment.Start else if (right) Alignment.End else Alignment.CenterHorizontally
    ) {
        Caption(text = title, textAlign = TextAlign.Center)
        Text(
            text = info,
            style = MaterialTheme.typography.labelMedium.copy(textAlign = TextAlign.Center).merge(style),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun styles() {
    val container = Modifier.align(Alignment.CenterHorizontally)
}