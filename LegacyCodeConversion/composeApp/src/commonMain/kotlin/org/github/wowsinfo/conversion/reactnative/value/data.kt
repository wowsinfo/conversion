    const val Version = "1.7.0"
    const val IOSVersion = "1.7.0"
    const val GameVersion = "12.7.0.0"
    const val Github = "https://github.com/wowsinfo/react-native-app"
    const val AppStore = "https://itunes.apple.com/app/id1202750166"
    const val GooglePlay = "https://play.google.com/store/apps/details?id=com.yihengquan.wowsinfo"
    const val Developer = "mailto:development.henryquan@gmail.com?subject=[WoWs Info 1.7.0]"
    const val Patreon = "https://www.patreon.com/henryquan"
    const val PayPal = "https://www.paypal.me/YihengQuan"
    const val WeChat = "https://github.com/HenryQuan/WoWs-Info-Origin/blob/master/Support/WeChat.png"
    const val PersonalRating = "https://wows-numbers.com/personal/rating"
    const val LatestRelease = "https://github.com/wowsinfo/react-native-app/releases/latest"
}

object LOCAL {
    const val friendList = "@WoWs_Info:playerList"
    const val userInfo = "@WoWs_Info:userInfo"
    const val userData = "@WoWs_Info:userData"
    const val userServer = "@WoWs_Info:currServer"
    const val appVersion = "@WoWs_Info:currVersion"
    const val gameVersion = "@WoWs_Info:gameVersion"
    const val date = "@WoWs_Info:currDate"
    const val lastUpdate = "@WoWs_Info:lastUpdate"
    const val theme = "@WoWs_Info:themeColour"
    const val darkMode = "@WoWs_Info:darkMode"
    const val swapButton = "@WoWs_Info:swapButton"
    const val noImageMode = "@WoWs_Info:noImageMode"
    const val firstLaunch = "@WoWs_Info:firstLaunch"
    const val apiLanguage = "@WoWs_Info:apiLanguage"
    const val userLanguage = "@WoWs_Info:userLanguage"
    const val lastLocation = "@WoWs_Info:lastLocation"
    const val proVersion = "@WoWs_Info:proVersion"
    const val rsIP = "@WoWs_Info:rsIP"
    const val showBanner = "@WoWs_Info:banner_ads"
    const val showFullscreen = "@WoWs_Info:fullscreen_ads"
}

object SAVED {
    const val language = "@Data:language"
    const val encyclopedia = "@Data:encyclopedia"
    const val achievement = "@Data:achievement"
    const val commanderSkill = "@Data:commander_skill"
    const val collection = "@Data:collection"
    const val warship = "@Data:warship"
    const val map = "@Data:gameMap"
    const val consumable = "@Data:consumable"
    const val pr = "@Data:personal_rating"
}

fun getFirstLaunch(): Boolean {
    return AppGlobalData.get(LOCAL.firstLaunch)
}

fun setFirstLaunch(mode: Boolean) {
    AppGlobalData.set(LOCAL.firstLaunch, mode)
    SafeStorage.set(LOCAL.firstLaunch, mode)
}

val SERVER = listOf("ru", "eu", "com", "asia")

fun getCurrDomain(): String {
    return SERVER[getCurrServer()]
}

fun getDomain(index: Int): String {
    return SERVER[index]
}

fun getCurrPrefix(): String {
    var prefix = getCurrDomain()
    if (prefix == "com") {
        prefix = "na"
    }
    return prefix
}

    var prefix = getDomain(index)
    if (prefix == "com") {
        prefix = "na"
    }
    return prefix
}



object LOCAL {
    const val userServer = "userServer"
    const val userLanguage = "userLanguage"
    const val apiLanguage = "apiLanguage"
    const val swapButton = "swapButton"
    const val lastLocation = "lastLocation"
    const val proVersion = "proVersion"
}

fun getCurrServer(): Int {
    return SafeValue(AppGlobalData.get(LOCAL.userServer), 3)
}

fun setCurrServer(index: Int) {
    val str = LOCAL.userServer
    AppGlobalData.set(str, index)
    SafeStorage.set(str, index)
}

fun getUserLang(): String {
    return SafeValue(AppGlobalData.get(LOCAL.userLanguage), "en")
}

fun setUserLang(lang: String) {
    val str = LOCAL.userLanguage
    AppGlobalData.set(str, lang)
    SafeStorage.set(str, lang)
}

fun getAPILanguage(): String {
    return SafeValue(AppGlobalData.get(LOCAL.apiLanguage), "en")
}

fun getAPILangName(): String {
    return getAPIList()[getAPILanguage()]
}

fun langStr(): String {
    return "&language=${getAPILanguage()}"
}

fun getAPIList(): Map<String, String> {
    return AppGlobalData.get(SAVED.language)
}

fun setAPILanguage(lang: String) {
    val str = LOCAL.apiLanguage
    AppGlobalData.set(str, lang)
    SafeStorage.set(str, lang)
}

fun getSwapButton(): Boolean {
    return AppGlobalData.get(LOCAL.swapButton)
}

fun setSwapButton(swap: Boolean) {
    AppGlobalData.shouldSwapButton = swap
    val str = LOCAL.swapButton
    AppGlobalData.set(str, swap)
    SafeStorage.set(str, swap)
}

fun setLastLocation(str: String) {
    val loc = LOCAL.lastLocation
    AppGlobalData.set(loc, str)
    SafeStorage.set(loc, str)
}

fun isProVersion(): Boolean {
    return AppGlobalData.get(LOCAL.proVersion) == true
}

fun setProVersion(pro: Boolean) {
    val str = LOCAL.proVersion
    AppGlobalData.set(str, pro)
    SafeStorage.set(str, pro)
}

fun onlyProVersion(): Boolean {
    return if (isProVersion()) {
        true
    } else {
        false
    }
}

===>>> review manually
// Only push if user is not using pro version
  Actions.ProVersion();
  return false;
};

export const validateProVersion = async (showAlert?: boolean) => {
  try {
    const history = await getAvailablePurchases();
    console.log(history);
    if (history.length > 0) {
      // Sort by date first
      let latest = history.sort(
        (a, b) => a.transactionDate - b.transactionDate,
      )[history.length - 1];
      console.log(latest);
      const receipt = latest.transactionReceipt;
      const date = latest.transactionDate;
      if (receipt && date) {
        console.log('Valid purchase');
        if (Platform.OS === 'android') {
          restorePurchase(latest.autoRenewingAndroid === true, showAlert);
          return;
        } else if (Platform.OS === 'ios') {
          // Check if it expires
          const purchaseDate = new Date(date);
          purchaseDate.setFullYear(purchaseDate.getFullYear() + 1);
          const todayDate = new Date();
          console.log(`today: ${todayDate}\nexpire: ${purchaseDate}`);
          restorePurchase(todayDate < purchaseDate, showAlert);
          return;
        }
      }
    }

    // Should not be pro version
    setProVersion(false);
    if (showAlert) {
      throw new Error(lang.iap_no_purchase_history);
    }
<<<===


    // Your code logic here
} catch (err: Exception) {
    // Assuming you have a function to show alerts in your Kotlin Multiplatform project
    showAlert(err.message ?: "An error occurred")
}

    println("Restore purchase - $shouldRestore")
    if (shouldRestore) {
        setProVersion(true)
        showAlert?.let {
            if (it) {
                Actions.pop()
                AlertDialog.Builder(context)
                    .setTitle(lang.pro_title)
                    .setMessage(lang.iap_thx_for_support)
                    .setPositiveButton("OK", null)
                    .show()
                Handler(Looper.getMainLooper()).postDelayed({
                    Actions.refresh()
                }, 500)
            }
        }
    }
}

    throw IllegalArgumentException(lang.iap_pro_expired)
}


object AppGlobalData {
    private val data = mutableMapOf<String, String>()

    fun get(key: String): String? {
        return data[key]
    }

    fun set(key: String, value: String) {
        data[key] = value
    }
}

object SafeStorage {
    private val storage = mutableMapOf<String, String>()

    fun set(key: String, value: String) {
        storage[key] = value
    }
}

object LOCAL {
    const val date = "date"
    const val lastUpdate = "lastUpdate"
}

fun getCurrDate(): String? {
    return AppGlobalData.get(LOCAL.date)
}

fun updateCurrData() {
    val today = Clock.System.now().toLocalDate().toString()
    AppGlobalData.set(LOCAL.date, today)
    SafeStorage.set(LOCAL.date, today)
}

fun differentMonth(): Boolean {
    val today = Clock.System.now().toLocalDate()
    val curr = getCurrDate()?.let { LocalDate.parse(it) }
    return curr?.month == today.month
}

fun getLastUpdate(): String? {
    return AppGlobalData.get(LOCAL.lastUpdate)
}

fun shouldUpdateWithCycle(): Boolean {
    val curr = getCurrDate()?.let { LocalDate.parse(it) }
    val last = getLastUpdate()?.let { LocalDate.parse(it) }

    if (curr != null && last != null) {
        val diffDays = (curr - last).days
        return diffDays >= 7
    }
    return false
}