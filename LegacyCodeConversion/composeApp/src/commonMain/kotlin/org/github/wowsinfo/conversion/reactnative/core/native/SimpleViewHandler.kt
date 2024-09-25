
class SimpleViewHandler {
    companion object {
        suspend fun openURL(url: String) {
            withContext(Dispatchers.Main) {
                if (Platform.isIos) {
                    val safariViewController = SFSafariViewController(NSURL(string = url)!!)
                    UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(safariViewController, true, null)
                } else {
                    openExternalURL(url)
                }
            }
        }

        private fun openExternalURL(url: String) {
            val nsUrl = NSURL(string = url)
            UIApplication.sharedApplication.openURL(nsUrl!!)
        }
    }
}


suspend fun openExternalURL(url: String) {
    withContext(Dispatchers.Main) {
        val nsUrl = NSURL.URLWithString(url)
        NSWorkspace.sharedWorkspace.openURL(nsUrl)
    }
}

    suspend fun showSafariViewController(url: String): Boolean
}