
@Composable
fun OpenURL(url: String) {
    val context = LocalContext.current

    fun openSafari() {
        // Use Android's default web browser to open the URL
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    openSafari()
}

@Composable
fun OpenExternalURL(url: String) {
    val context = LocalContext.current

    fun openExternal() {
        // Use Android's default web browser to open the URL
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    openExternal()
}