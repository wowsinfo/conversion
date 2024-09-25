
@Composable
fun Space(height: Int? = 128) {
    val h = height ?: 128
    Box(modifier = Modifier.height(h.dp))
}


@Composable
fun Space() {
    Box(modifier = Modifier.size(0.dp))
}