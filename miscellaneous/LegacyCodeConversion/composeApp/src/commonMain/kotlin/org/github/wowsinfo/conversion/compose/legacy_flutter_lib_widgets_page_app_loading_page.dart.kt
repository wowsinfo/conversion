@Composable
fun AppLoadingPage() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        App.instance.inject(context)
        GameRepository().initialize().then { result ->
            // TODO: go to home screen here
            NavHostController(LocalContext.current).navigate("upgrade_page")
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp),
        ) {
            Box(
                backgroundColor = Color(0xFF0000FF),
                size = DpSize(100.dp, 100.dp),
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            CircularProgressIndicator(
                progress = 0.9f,
                modifier = Modifier.size(8.dp)
            )
            Text("Loading...", style = TextStyle(fontSize = 16.sp))
        }
    }
}