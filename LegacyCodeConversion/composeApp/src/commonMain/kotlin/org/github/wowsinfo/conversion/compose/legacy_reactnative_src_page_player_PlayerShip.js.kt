
@Composable
fun FooterPlus(
    content: @Composable () -> Unit,
) {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        content()
    }
}