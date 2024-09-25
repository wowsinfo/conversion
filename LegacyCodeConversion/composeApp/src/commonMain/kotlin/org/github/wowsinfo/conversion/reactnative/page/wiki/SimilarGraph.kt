
@Composable
fun SimilarGraph() {
    val shipsStats = remember { /* Fetch or calculate similar ships' average stats */ }

    ScrollableColumn(modifier = Modifier.fillMaxSize()) {
        shipsStats.forEach { stat ->
            WoWsInfo(stat)
        }
    }
}

fun WoWsInfoScreen(info: @Composable () -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        info()
    }
}


@Composable
fun ScrollableContent() {
    val scrollState = rememberScrollState()
    androidx.compose.foundation.ScrollableColumn(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {
        // Add your content here
    }
}


@Composable
fun SimilarGraph() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Your UI components go here
    }
}