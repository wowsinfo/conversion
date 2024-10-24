
@Composable
fun getFirstLaunch(): Boolean {
    return remember { AppGlobalData.get(LOCAL.firstLaunch) }
}

@Composable
fun setFirstLaunch(mode: Boolean) {
    val str = LOCAL.firstLaunch
    AppGlobalData.set(str, mode)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, mode)
    }
}

val SERVER = listOf("ru", "eu", "com", "asia")

@Composable
fun getCurrDomain(): String {
    return remember { getDomain(getCurrServer()) }
}

@Composable
fun getDomain(index: Int): String {
    return remember { SERVER[index] }
}

@Composable
fun getCurrPrefix(): String {
    val prefix = getCurrDomain()
    return remember { if (prefix == "com") "na" else prefix }
}

@Composable
fun getPrefix(index: Int): String {
    val prefix = getDomain(index)
    return remember { if (prefix == "com") "na" else prefix }
}

@Composable
fun getCurrServer(): Int {
    return remember { AppGlobalData.get(LOCAL.userServer) ?: 3 }
}

@Composable
fun setCurrServer(index: Int) {
    val str = LOCAL.userServer
    AppGlobalData.set(str, index)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, index)
    }
}

@Composable
fun getUserLang(): String {
    return remember { AppGlobalData.get(LOCAL.userLanguage) ?: "en" }
}

@Composable
fun setUserLang(lang: String) {
    val str = LOCAL.userLanguage
    AppGlobalData.set(str, lang)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, lang)
    }
}

@Composable
fun getAPILanguage(): String {
    return remember { AppGlobalData.get(LOCAL.apiLanguage) ?: "en" }
}

@Composable
fun getAPILangName(): String {
    val list = remember { getAPIList() }
    return remember { list[getAPILanguage()] }
}

@Composable
fun langStr(): String {
    return remember { "&language=${getAPILanguage()}" }
}

@Composable
fun setAPILanguage(lang: String) {
    val str = LOCAL.apiLanguage
    AppGlobalData.set(str, lang)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, lang)
    }
}

@Composable
fun getSwapButton(): Boolean {
    return remember { AppGlobalData.get(LOCAL.swapButton) ?: false }
}

@Composable
fun setSwapButton(swap: Boolean) {
    val str = LOCAL.swapButton
    AppGlobalData.shouldSwapButton = swap
    AppGlobalData.set(str, swap)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, swap)
    }
}

@Composable
fun setLastLocation(str: String) {
    val loc = LOCAL.lastLocation
    AppGlobalData.set(loc, str)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(loc, str)
    }
}

@Composable
fun isProVersion(): Boolean {
    return remember { AppGlobalData.get(LOCAL.proVersion) == true }
}

@Composable
fun setProVersion(pro: Boolean) {
    val str = LOCAL.proVersion
    AppGlobalData.set(str, pro)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, pro)
    }
}

suspend fun validateProVersion(showAlert: Boolean) {
    try {
        val history = getAvailablePurchases()
        if (history.isNotEmpty()) {
            val latest = history.sortedByDescending { it.transactionDate }[0]
            val receipt = latest.transactionReceipt
            val date = latest.transactionDate

            if (receipt != null && date != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    restorePurchase(latest.autoRenewingAndroid == true, showAlert)
                    return@suspend fun
                } else {
                    // Check if it expires
                    val purchaseDate = Date(date)
                    purchaseDate.setYear(purchaseDate.year + 1)
                    val todayDate = Date()
                    console.log("today: $todayDate\nexpire: $purchaseDate")
                    restorePurchase(todayDate < purchaseDate, showAlert)
                    return@suspend fun
                }
            }
        }

        // Should not be pro version
        setProVersion(false)
        if (showAlert) {
            throw Exception(lang.iap_no_purchase_history)
        }
    } catch (err: Exception) {
        Alert.alert(err.message)
    }
}

private suspend fun restorePurchase(shouldRestore: Boolean, showAlert: Boolean) {
    console.log("Restore purchase - $shouldRestore")
    if (shouldRestore) {
        setProVersion(true)
        if (showAlert) {
            Actions.pop()
            Alert.alert(lang.pro_title, lang.iap_thx_for_support)
            delay(500)
            Actions.refresh()
        }
    } else {
        throw Exception(lang.iap_pro_expired)
    }
}

@Composable
fun getCurrDate(): String {
    return remember { AppGlobalData.get(LOCAL.date) ?: "" }
}

suspend fun updateCurrData() {
    val today = Date().toDateString()
    val str = LOCAL.date
    AppGlobalData.set(str, today)
    GlobalScope.launch(Dispatchers.IO) {
        SafeStorage.set(str, today)
    }
}

fun differentMonth(): Boolean {
    val today = Date()
    val curr = Date(getCurrDate())
    return remember { today.month == curr.month && today.year == curr.year }
}

@Composable
fun getLastUpdate(): String {
    return remember { AppGlobalData.get(LOCAL.lastUpdate) ?: "" }
}

suspend fun shouldUpdateWithCycle(): Boolean {
    val curr = Date(getCurrDate())
    val last = Date(getLastUpdate())

    val diff = Math.abs(curr.time - last.time)
    val diffDays = (diff / (1000 * 60 * 60 * 24)).roundToInt()
    return remember { diffDays >= 7 }
}