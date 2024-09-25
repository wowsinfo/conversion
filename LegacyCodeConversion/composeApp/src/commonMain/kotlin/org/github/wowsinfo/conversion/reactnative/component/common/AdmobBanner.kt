
@Composable
fun AdmobBanner() {
    var success by remember { mutableStateOf(true) }

    // Your UI code here, for example:
    if (success) {
        Text("Admob Banner Loaded")
    } else {
        Text("Failed to Load Admob Banner")
    }
}

@Preview
@Composable
fun PreviewAdmobBanner() {
    AdmobBanner()
}

fun SupportMeItem(lang: Language) {
    ListItem(
        modifier = Modifier.clickable { SafeAction("SupportMe") },
        headlineContent = { Text(text = lang.extra_support_wowsinfo) },
        supportingContent = { Text(text = lang.extra_support_wowsinfo_subtitle) }
    )
}

    var success by mutableStateOf(true)

    fun hideAds() {
        success = false
    }

    fun logError(err: Throwable) {
        println("err: $err")
        success = false
    }
}


@Composable
fun AdmobBanner() {
    val context = LocalContext.current
    MobileAds.initialize(context)

    val adView = AdView(context).apply {
        adUnitId = "YOUR_AD_UNIT_ID"
        loadAd(AdRequest.Builder().build())
    }

    AndroidView(factory = { adView })
}