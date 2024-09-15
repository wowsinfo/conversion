
@Composable
fun Donation() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        // They won't allow wechat and paypal
        if (!AppGlobalData.githubVersion) {
            List(
                items = listOf(
                    "GitHub",
                    "https://github.com/HenryQuan/WoWs-Info-Origin"
                ),
                onClick = { _, data ->
                    SimpleViewHandler.openURL(context, data)
                }
            )
        } else {
            List(
                items = listOf(APP.Patreon, APP.PayPal, APP.WeChat),
                onClick = { _, data ->
                    SimpleViewHandler.openURL(context, data)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Donation()
        }
    }
}