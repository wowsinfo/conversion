package org.github.wowsinfo.conversion.legacy

object App {
    const val appVersion = "1.7.0"
    const val githubLink = "https://github.com/WoWs-Info/WoWs-Info-Seven"
    const val appstoreLink = "https://itunes.apple.com/app/id1202750166"
    const val playstoreLink =
        "https://play.google.com/store/apps/details?id=com.yihengquan.wowsinfo"
    const val latestRelease = "$githubLink/releases/latest"
    const val newIssueLink = "$githubLink/issues/new"
    const val emailToLink =
        "mailto:development.henryquan@gmail.com?subject=[WoWs Info $appVersion] "

    // TO be implemented using except and actual
//    fun isWeb() = BuildConfig.BUILD_TYPE == "web"
//
//    fun isIOS() = BuildConfig.BUILD_TYPE == "ios"
//
//    fun isAndroid() = BuildConfig.BUILD_TYPE == "android"
//
//    fun isMobile() = !isWeb() && (isIOS() || isAndroid())
//
//    fun isDesktop() = !isWeb() && !isMobile()
//
//    fun isApple() = !isWeb() && (isIOS() || Build.VERSION.SDK_INT >= 28)

    // TODO: to be removed, should be handled else where
//    fun launch(url: String) {
//        val canLaunch = ContextCompat.getSystemService(GlobalScope.launch {
//            url.toUri().let { Uri.parse(it.toString()) }.also { uri ->
//                try {
//                    GlobalScope.launch {
//                        FlutterActivity.launchUrl(context, uri)
//                    }
//                } catch (e: Exception) {
//                    println(e.message)
//                }
//            }
//        })
//    }
}