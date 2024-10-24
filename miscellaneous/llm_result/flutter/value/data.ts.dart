
class App {
  static const String version = '1.7.0';
  static const String iosVersion = '1.7.0';
  static const String gameVersion = '12.7.0.0';
  static const String github = 'https://github.com/wowsinfo/react-native-app';
  static const String appStore = 'https://itunes.apple.com/app/id1202750166';
  static const String googlePlay = 'https://play.google.com/store/apps/details?id=com.yihengquan.wowsinfo';
  static const String developer = 'mailto:development.henryquan@gmail.com?subject=[WoWs Info 1.7.0]';
  static const String patreon = 'https://www.patreon.com/henryquan';
  static const String paypal = 'https://www.paypal.me/YihengQuan';
  static const String weChat = 'https://github.com/HenryQuan/WoWs-Info-Origin/blob/master/Support/WeChat.png';
  static const String personalRating = 'https://wows-numbers.com/personal/rating';
  static const String latestRelease = 'https://github.com/wowsinfo/react-native-app/releases/latest';
}

class Local {
  static const String friendList = '@WoWs_Info:playerList';
  static const String userInfo = '@WoWs_Info:userInfo';
  static const String userData = '@WoWs_Info:userData';
  static const String userServer = '@WoWs_Info:currServer';
  static const String appVersion = '@WoWs_Info:currVersion';
  static const String gameVersion = '@WoWs_Info:gameVersion';
  static const String date = '@WoWs_Info:currDate';
  static const String lastUpdate = '@WoWs_Info:lastUpdate';
  static const String theme = '@WoWs_Info:themeColour';
  static const String darkMode = '@WoWs_Info:darkMode';
  static const String swapButton = '@WoWs_Info:swapButton';
  static const String noImageMode = '@WoWs_Info:noImageMode';
  static const String firstLaunch = '@WoWs_Info:firstLaunch';
  static const String apiLanguage = '@WoWs_Info:apiLanguage';
  static const String userLanguage = '@WoWs_Info:userLanguage';
  static const String lastLocation = '@WoWs_Info:lastLocation';
  static const String proVersion = '@WoWs_Info:proVersion';
  static const String rsIP = '@WoWs_Info:rsIP';
  static const String showBanner = '@WoWs_Info:banner_ads';
  static const String showFullscreen = '@WoWs_Info:fullscreen_ads';
}

class Saved {
  static const String language = '@Data:language';
  static const String encyclopedia = '@Data:encyclopedia';
  static const String achievement = '@Data:achievement';
  static const String commanderSkill = '@Data:commander_skill';
  static const String collection = '@Data:collection';
  static const String warship = '@Data:warship';
  static const String map = '@Data:gameMap';
  static const String consumable = '@Data:consumable';
  static const String pr = '@Data:personal_rating';
}

class AppGlobalData {
  static final FlutterSecureStorage storage = FlutterSecureStorage();

  static Future<bool?> get(String key) async {
    String? value = await storage.read(key: key);
    return value == 'true';
  }

  static Future<void> set(String key, bool value) async {
    await storage.write(key: key, value: value.toString());
  }
}

class AppFunctions {
  static Future<bool?> getFirstLaunch() async {
    return await AppGlobalData.get(Local.firstLaunch);
  }

  static Future<void> setFirstLaunch(bool mode) async {
    await AppGlobalData.set(Local.firstLaunch, mode);
  }

  static const List<String> server = ['ru', 'eu', 'com', 'asia'];

  static String getCurrDomain() {
    return server[getCurrServer()];
  }

  static String getDomain(int index) {
    return server[index];
  }

  static String getCurrPrefix() {
    String prefix = getCurrDomain();
    if (prefix == 'com') {
      prefix = 'na';
    }
    return prefix;
  }

  static int getCurrServer() {
    // Implement logic to get current server index
    return 0; // Placeholder
  }
}

  // Implement your logic to get the domain based on the index
  // This is a placeholder implementation
  List<String> domains = ['com', 'org', 'net', 'io'];
  return domains[index % domains.length];
}

String getPrefix(int index) {
  String prefix = getDomain(index);
  if (prefix == 'com') {
    prefix = 'na';
  }
  return prefix;
}


class AppGlobalData {
  static final Map<String, dynamic> _data = {};
  static final FlutterSecureStorage _storage = FlutterSecureStorage();

  static dynamic get(String key) {
    return _data[key];
  }

  static void set(String key, dynamic value) {
    _data[key] = value;
  }

  static Future<void> saveToStorage(String key, dynamic value) async {
    await _storage.write(key: key, value: value.toString());
  }

  static Future<String?> readFromStorage(String key) async {
    return await _storage.read(key: key);
  }
}

class LOCAL {
  static const String userServer = 'userServer';
  static const String userLanguage = 'userLanguage';
  static const String apiLanguage = 'apiLanguage';
  static const String swapButton = 'swapButton';
  static const String lastLocation = 'lastLocation';
  static const String proVersion = 'proVersion';
}

dynamic safeValue(dynamic value, dynamic defaultValue) {
  return value ?? defaultValue;
}

class UserPreferences {
  static Future<int> getCurrServer() async {
    return safeValue(await AppGlobalData.readFromStorage(LOCAL.userServer), 3);
  }

  static Future<void> setCurrServer(int index) async {
    await AppGlobalData.set(LOCAL.userServer, index);
    await AppGlobalData.saveToStorage(LOCAL.userServer, index);
  }

  static Future<String> getUserLang() async {
    return safeValue(await AppGlobalData.readFromStorage(LOCAL.userLanguage), 'en');
  }

  static Future<void> setUserLang(String lang) async {
    await AppGlobalData.set(LOCAL.userLanguage, lang);
    await AppGlobalData.saveToStorage(LOCAL.userLanguage, lang);
  }

  static Future<String> getAPILanguage() async {
    return safeValue(await AppGlobalData.readFromStorage(LOCAL.apiLanguage), 'en');
  }

  static Future<String> getAPILangName() async {
    return (await getAPIList())[await getAPILanguage()];
  }

  static Future<String> langStr() async {
    return '&language=${await getAPILanguage()}';
  }

  static Future<Map<String, String>> getAPIList() async {
    return await AppGlobalData.readFromStorage('SAVED.language') as Map<String, String>;
  }

  static Future<void> setAPILanguage(String lang) async {
    await AppGlobalData.set(LOCAL.apiLanguage, lang);
    await AppGlobalData.saveToStorage(LOCAL.apiLanguage, lang);
  }

  static Future<bool> getSwapButton() async {
    return await AppGlobalData.readFromStorage(LOCAL.swapButton) == 'true';
  }

  static Future<void> setSwapButton(bool swap) async {
    await AppGlobalData.set(LOCAL.swapButton, swap);
    await AppGlobalData.saveToStorage(LOCAL.swapButton, swap);
  }

  static Future<void> setLastLocation(String str) async {
    await AppGlobalData.set(LOCAL.lastLocation, str);
    await AppGlobalData.saveToStorage(LOCAL.lastLocation, str);
  }

  static Future<bool> isProVersion() async {
    return await AppGlobalData.readFromStorage(LOCAL.proVersion) == 'true';
  }

  static Future<void> setProVersion(bool pro) async {
    await AppGlobalData.set(LOCAL.proVersion, pro);
    await AppGlobalData.saveToStorage(LOCAL.proVersion, pro);
  }

  static Future<bool> onlyProVersion() async {
    return await isProVersion();
  }
}


Future<void> validateProVersion({bool showAlert = false}) async {
  final QueryPurchaseDetailsResponse response = await InAppPurchase.instance.queryPastPurchases();
  final List<PurchaseDetails> history = response.pastPurchases;

  if (history.isNotEmpty) {
    // Sort by date first
    history.sort((a, b) => a.transactionDate.compareTo(b.transactionDate));
    final latest = history.last;
    
    final String? receipt = latest.verificationData.localVerificationData;
    final DateTime? date = latest.transactionDate;

    if (receipt != null && date != null) {
      if (kIsWeb) {
        // Handle web-specific logic if needed
      } else if (Platform.isAndroid) {
        restorePurchase(latest.pendingCompletePurchase, showAlert);
        return;
      } else if (Platform.isIOS) {
        // Check if it expires
        final DateTime purchaseDate = date;
        final DateTime expiryDate = DateTime(purchaseDate.year + 1, purchaseDate.month, purchaseDate.day);
        final DateTime todayDate = DateTime.now();
        
        restorePurchase(todayDate.isBefore(expiryDate), showAlert);
        return;
      }
    }
  }

  // Should not be pro version
  setProVersion(false);
  if (showAlert) {
    throw Exception('No purchase history found.');
  }
}

void restorePurchase(bool isPro, bool showAlert) {
  // Implement your restore purchase logic here
}

void setProVersion(bool isPro) {
  // Implement your logic to set pro version here
}

  // Your code that may throw an error
} catch (err) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text('Error'),
        content: Text(err.toString()),
        actions: <Widget>[
          TextButton(
            child: Text('OK'),
            onPressed: () {
              Navigator.of(context).pop();
            },
          ),
        ],
      );
    },
  );
}


class PurchaseManager {
  bool proVersion = false;

  void restorePurchase(bool shouldRestore, {bool showAlert = false}) {
    print('Restore purchase - $shouldRestore');
    if (shouldRestore) {
      setProVersion(true);
      if (showAlert) {
        // Assuming you have a context to use for navigation and alerts
        Navigator.of(context).pop();
        showDialog(
          context: context,
          builder: (BuildContext context) {
            return AlertDialog(
              title: Text(lang.pro_title),
              content: Text(lang.iap_thx_for_support),
              actions: <Widget>[
                TextButton(
                  child: Text('OK'),
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                ),
              ],
            );
          },
        );
        Future.delayed(Duration(milliseconds: 500), () {
          // Refresh logic here, assuming you have a way to refresh the UI
          refreshUI();
        });
      }
    }
  }

  void setProVersion(bool value) {
    proVersion = value;
  }

  void refreshUI() {
    // Implement your UI refresh logic here
  }
}

  // Your condition logic here
} else {
  throw Exception(lang.iap_pro_expired);
}


class AppGlobalData {
  static Future<String?> get(String key) async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString(key);
  }

  static Future<void> set(String key, String value) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }
}

class LOCAL {
  static const String date = 'date';
  static const String lastUpdate = 'lastUpdate';
}

Future<String?> getCurrDate() async {
  return await AppGlobalData.get(LOCAL.date);
}

Future<void> updateCurrData() async {
  final today = DateTime.now().toLocal().toString().split(' ')[0];
  await AppGlobalData.set(LOCAL.date, today);
}

Future<bool> differentMonth() async {
  final today = DateTime.now();
  final currDateStr = await getCurrDate();
  if (currDateStr == null) return false;
  final curr = DateTime.parse(currDateStr);
  final sameMonth = today.month == curr.month;
  print('Same month - $sameMonth');
  return sameMonth;
}

Future<String?> getLastUpdate() async {
  return await AppGlobalData.get(LOCAL.lastUpdate);
}

Future<bool> shouldUpdateWithCycle() async {
  final currDateStr = await getCurrDate();
  final lastUpdateStr = await getLastUpdate();
  if (currDateStr == null || lastUpdateStr == null) return false;

  final curr = DateTime.parse(currDateStr);
  final last = DateTime.parse(lastUpdateStr);

  final diff = curr.difference(last).inDays;
  print('$diff day(s)');
  return diff >= 7;
}