
@Composable
fun SimilarGraph(info: List<Any>) {
    WoWsInfo {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            info.forEachIndexed { index, data ->
                // Replace with your Composable for displaying individual items
                Text(text = "Item $index: $data")
            }
        }
    }
}