
class Downloader {
  String domain;
  String language;
  bool isNew;

  Downloader(int server) {
    // Convert server index to string
    domain = getCurrDomain();
    language = langStr();

    // To prevent when first launch, everything is new and too many dots
    isNew = !AppGlobalData.get(LOCAL.firstLaunch);
    print(isNew);

    print("Downloader\nYour server is '$domain'");
  }
}

  /// Check if there is an update
  /// [previous] is the version we have
  /// [current] is the version from API
  bool checkVersionUpdate(String previous, String current) {
    // Simply check if they are different
    if (previous != current) {
      return true;
    }

    List<String> pList = previous.split('.');
    List<String> cList = current.split('.');

    for (int i = 0; i < pList.length; i++) {
      // If one digit is larger, we need to update here
      if (i < cList.length && int.parse(cList[i]) > int.parse(pList[i])) {
        return true;
      }
    }

    return false;
  }
}


class ReviewShareWidget extends StatefulWidget {
  @override
  _ReviewShareWidgetState createState() => _ReviewShareWidgetState();
}

class _ReviewShareWidgetState extends State<ReviewShareWidget> {
  DateTime lastUpdate = DateTime.now();

  void reviewOrShareIfLucky() {
    // Logic to show random review and share to users
  }

  void updateCurrData() {
    // Logic to update current data
  }

  bool shouldUpdateWithCycle() {
    final now = DateTime.now();
    return now.difference(lastUpdate).inDays >= 7;
  }

  bool checkUpdateCycle() {
    updateCurrData();
    final shouldUpdate = shouldUpdateWithCycle();
    print(shouldUpdate);
    return shouldUpdate;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          ElevatedButton(
            onPressed: reviewOrShareIfLucky,
            child: Text('Review or Share'),
          ),
          ElevatedButton(
            onPressed: () {
              checkUpdateCycle();
            },
            child: Text('Check Update Cycle'),
          ),
        ],
      ),
    );
  }
}


class Downloader {
  Future<String> getVersion() async {
    // Implement your logic to get the game version
  }

  Future<String> getLanguage() async {
    // Implement your logic to get the language
  }

  Future<String> getEncyclopedia() async {
    // Implement your logic to get the encyclopedia
  }

  Future<String> getWarship() async {
    // Implement your logic to get the warship
  }

  Future<String> getAchievement() async {
    // Implement your logic to get the achievement
  }

  Future<String> getCollectionAndItem() async {
    // Implement your logic to get the collection and item
  }

  Future<String> getCommanderSkill() async {
    // Implement your logic to get the commander skill
  }

  Future<String> getConsumable() async {
    // Implement your logic to get the consumable
  }

  Future<String> getMap() async {
    // Implement your logic to get the map
  }

  Future<String> getPR() async {
    // Implement your logic to get the PR
  }

  String readLocalPR() {
    // Implement your logic to read local PR
  }

  bool checkUpdateCycle() {
    // Implement your logic to check update cycle
  }

  bool checkVersionUpdate(String currVersion, String gameVersion) {
    // Implement your logic to check version update
  }

  Future<Map<String, dynamic>> makeObj(bool success, String log) async {
    return {'success': success, 'log': log};
  }

  Future<void> updateAll({bool force = false}) async {
    String log = 'Getting gameVersion\n';
    try {
      String gameVersion = await getVersion();
      log += 'gameVersion - $gameVersion\n';
      if (gameVersion == null) {
        return await makeObj(false, log);
      }
      String currVersion = await getCurrVersion();
      print('Current: $currVersion\nAPI: $gameVersion');
      String appVersion = await getAppVersion();
      log += 'appVersion - $appVersion\n';
      print('Current app version: $appVersion\nLatest: ${APP.Version}');

      if (checkUpdateCycle() || checkVersionUpdate(currVersion, gameVersion) || force || appVersion != APP.Version) {
        log += 'Updating Data\n';
        print('Downloader\nUpdating all data from API');

        await saveData(SAVED.language, await getLanguage());
        log += '${lang.setting_api_language}\n';

        await saveData(SAVED.encyclopedia, await getEncyclopedia());
        log += '${lang.wiki_section_title}\n';

        await saveData(SAVED.warship, await getWarship());
        log += '${lang.wiki_warships}\n';

        await saveData(SAVED.achievement, await getAchievement());
        log += '${lang.wiki_achievement}\n';

        await saveData(SAVED.collection, await getCollectionAndItem());
        log += '${lang.wiki_collections}\n';

        await saveData(SAVED.commanderSkill, await getCommanderSkill());
        log += '${lang.wiki_skills}\n';

        await saveData(SAVED.consumable, await getConsumable());
        log += '${lang.wiki_upgrades}\n';

        await saveData(SAVED.map, await getMap());
        log += '${lang.wiki_maps}\n';

        await saveData(SAVED.pr, await getPR());
        log += '${lang.rating_title}\n';

        var PR = await getData(SAVED.pr);
        if (PR == null || PR.length < 10) {
          await saveData(SAVED.pr, readLocalPR());
          log += '${lang.rating_title} - local\n';
          PR = await getData(SAVED.pr);
          if (PR == null || PR.length < 10) {
            log += '${lang.error_pr_corrupted}\n';
            log += ' ${lang.rating_title} - ${PR.toString()}\n';
            return await makeObj(false, log);
          }
        }

        await saveVersionData(gameVersion, APP.Version);
        await saveLastUpdate();
      }
      return await makeObj(true, log);
    } catch (err) {
      log += err.toString();
      return await makeObj(false, log);
    }
  }

  Future<String> getCurrVersion() async {
    // Implement your logic to get the current version
  }

  Future<String> getAppVersion() async {
    // Implement your logic to get the app version
  }

  Future<void> saveData(String key, dynamic value) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }

  Future<dynamic> getData(String key) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString(key);
  }

  Future<void> saveVersionData(String gameVersion, String appVersion) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString(LOCAL.gameVersion, gameVersion);
    await prefs.setString(LOCAL.appVersion, appVersion);
  }

  Future<void> saveLastUpdate() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString(LOCAL.lastUpdate, getCurrDate());
  }

  String getCurrDate() {
    // Implement your logic to get the current date
  }
}

    return {'status': status, 'log': log};
}


class WoWsAPI {
  static const String GameVersion = 'https://api.example.com/game/version'; // Replace with actual API endpoint
}

class Guard {
  static String guard(Map<String, dynamic> json, String path, String defaultValue) {
    final keys = path.split('.');
    dynamic value = json;

    for (var key in keys) {
      if (value is Map<String, dynamic> && value.containsKey(key)) {
        value = value[key];
      } else {
        return defaultValue;
      }
    }
    return value ?? defaultValue;
  }
}

class APP {
  static const String GameVersion = '1.0.0'; // Default game version
}

class GameService {
  final String domain;

  GameService(this.domain);

  Future<String> getVersion() async {
    final response = await http.get(Uri.parse(WoWsAPI.GameVersion));

    if (response.statusCode == 200) {
      final json = jsonDecode(response.body);
      return Guard.guard(json, 'data.game_version', APP.GameVersion);
    } else {
      throw Exception('Failed to load game version');
    }
  }
}


class WikiAPI {
  static const String Language = 'your_api_endpoint_here'; // Replace with actual endpoint
}

class SafeFetch {
  static Future<Map<String, dynamic>> get(String url, String domain) async {
    final response = await http.get(Uri.parse('$domain/$url'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }
}

class Guard {
  static dynamic call(Map<String, dynamic> json, String path, dynamic defaultValue) {
    final keys = path.split('.');
    dynamic result = json;
    for (var key in keys) {
      if (result is Map<String, dynamic> && result.containsKey(key)) {
        result = result[key];
      } else {
        return defaultValue;
      }
    }
    return result;
  }
}

class SafeStorage {
  static Future<void> set(String key, dynamic value) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, json.encode(value));
  }
}

class YourClass {
  final String domain;

  YourClass(this.domain);

  Future<dynamic> getLanguage() async {
    final json = await SafeFetch.get(WikiAPI.Language, domain);
    final data = Guard.call(json, 'data.languages', {});
    await SafeStorage.set('SAVED.language', data);
    return data;
  }
}


class EncyclopediaService {
  final String domain;
  final String language;

  EncyclopediaService(this.domain, this.language);

  Future<Map<String, dynamic>> getEncyclopedia() async {
    final response = await http.get(Uri.parse('$domain/${WikiAPI.Encyclopedia}?lang=$language'));

    if (response.statusCode == 200) {
      final json = jsonDecode(response.body);
      final data = json['data'] ?? {};
      await _saveToStorage(data);
      return data;
    } else {
      throw Exception('Failed to load encyclopedia');
    }
  }

  Future<void> _saveToStorage(Map<String, dynamic> data) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(SAVED.encyclopedia, jsonEncode(data));
  }
}

class WikiAPI {
  static const String Encyclopedia = 'encyclopedia_endpoint'; // Replace with actual endpoint
}

class SAVED {
  static const String encyclopedia = 'encyclopedia_data';
}


class WarshipService {
  final String domain;
  final String language;
  final bool isNew;

  WarshipService(this.domain, this.language, this.isNew);

  Future<Map<String, dynamic>> getWarship() async {
    int pageTotal = 1;
    int page = 0;
    Map<String, dynamic> all = {};

    // Download data from Github
    final model3D = await _safeFetchNormal(WikiAPI.Github_Model);

    // For Chinese and Japanese users only
    Map<String, dynamic>? japaneseShips;
    String currLang = _getAPILanguage();
    if (currLang.contains('zh') || currLang.contains('ja')) {
      japaneseShips = await _safeFetchNormal(WikiAPI.Github_Alias);
    }

    while (page < pageTotal) {
      final json = await _safeFetchGet(
        WikiAPI.Warship,
        domain,
        '&page_no=${page + 1}&$language',
      );
      pageTotal = _guard(json, 'meta.page_total', 1);
      Map<String, dynamic> data = _guard(json, 'data', {});

      data.forEach((id, curr) {
        if (curr['name'].contains('[')) {
          data.remove(id);
        } else {
          curr['icon'] = curr['images']['small'];
          curr.remove('images');
          curr['premium'] = curr['is_premium'] || curr['is_special'];
          curr.remove('is_premium');
          curr.remove('is_special');

          if (isNew) {
            final isOld = _guard(AppGlobalData.get(SAVED.warship), id, true);
            curr['new'] = !isOld;
          }

          if (model3D != null && model3D[id] != null) {
            curr['model'] = model3D[id]['model'];
          }

          if (japaneseShips != null) {
            final entry = japaneseShips[id];
            if (entry != null) {
              curr['name'] = entry['alias'];
            }
          }
        }
      });

      all.addAll(data);
      page++;
    }

    await _safeStorageSet(SAVED.warship, all);
    return all;
  }

  Future<Map<String, dynamic>?> _safeFetchNormal(String url) async {
    final response = await http.get(Uri.parse(url));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }

  Future<Map<String, dynamic>> _safeFetchGet(String endpoint, String domain, String query) async {
    final response = await http.get(Uri.parse('$domain$endpoint$query'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }

  dynamic _guard(Map<String, dynamic> json, String path, dynamic defaultValue) {
    final keys = path.split('.');
    dynamic value = json;
    for (var key in keys) {
      if (value is Map<String, dynamic> && value.containsKey(key)) {
        value = value[key];
      } else {
        return defaultValue;
      }
    }
    return value;
  }

  Future<void> _safeStorageSet(String key, Map<String, dynamic> value) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString(key, json.encode(value));
  }

  String _getAPILanguage() {
    // Implement your logic to get the API language
    return 'en';
  }
}


class AchievementService {
  final String domain;
  final String language;
  final bool isNew;

  AchievementService(this.domain, this.language, this.isNew);

  Future<Map<String, dynamic>> getAchievement() async {
    final response = await http.get(Uri.parse('$domain/$language/achievement'));
    final json = jsonDecode(response.body);
    final data = json['data']['battle'] ?? {};

    if (isNew) {
      final prefs = await SharedPreferences.getInstance();
      final savedAchievements = prefs.getStringList('achievement') ?? [];
      for (var id in data.keys) {
        data[id]['new'] = !savedAchievements.contains(id);
      }
    }

    final prefs = await SharedPreferences.getInstance();
    await prefs.setString('achievement', jsonEncode(data));

    return data;
  }
}


class YourClass {
  final String domain;
  final String language;
  final bool newFlag;

  YourClass(this.domain, this.language, this.newFlag);

  Future<Map<String, dynamic>> getCollectionAndItem() async {
    Map<String, dynamic> all = {};

    var collectionResponse = await SafeFetch.get(
      WikiAPI.Collection,
      domain,
      language,
    );
    var itemResponse = await SafeFetch.get(
      WikiAPI.CollectionItem,
      domain,
      language,
    );

    var collection = Guard(collectionResponse, 'data', {});
    var item = Guard(itemResponse, 'data', {});

    item.forEach((id, curr) {
      curr['image'] = curr['images']['small'];
      curr['images'] = null;
    });

    if (newFlag) {
      collection.forEach((id, curr) {
        var isOld = Guard(
          AppGlobalData.get(SAVED.collection),
          'collection.$id',
          true,
        );
        curr['new'] = isOld ? false : true;
      });
    }

    all['collection'] = collection;
    all['item'] = item;

    await SafeStorage.set(SAVED.collection, all);
    return all;
  }
}

class SafeFetch {
  static Future<dynamic> get(String api, String domain, String language) async {
    final response = await http.get(Uri.parse('$domain/$api/$language'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }
}

class Guard {
  static dynamic call(Map<String, dynamic> data, String key, dynamic defaultValue) {
    return data.containsKey(key) ? data[key] : defaultValue;
  }
}

class AppGlobalData {
  static Map<String, dynamic> data = {};

  static dynamic get(String key) {
    return data[key];
  }
}

class SafeStorage {
  static Future<void> set(String key, Map<String, dynamic> value) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString(key, json.encode(value));
  }
}

class WikiAPI {
  static const String Collection = 'collection';
  static const String CollectionItem = 'collectionItem';
}

class SAVED {
  static const String collection = 'saved_collection';
}


class CommanderSkillService {
  final String domain;
  final String language;

  CommanderSkillService(this.domain, this.language);

  Future<List<dynamic>> getCommanderSkill() async {
    final response = await http.get(Uri.parse('$domain/WikiAPI/CommanderSkill?lang=$language'));

    if (response.statusCode == 200) {
      final json = jsonDecode(response.body);
      final skill = json['data'] ?? [];
      List<dynamic> data = skill.map((k) => k).toList();
      data.sort((a, b) => a['tier'].compareTo(b['tier']));

      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('SAVED.commanderSkill', jsonEncode(data));
      return data;
    } else {
      throw Exception('Failed to load commander skills');
    }
  }
}


class ConsumableService {
  final String domain;
  final String language;
  final bool isNew;

  ConsumableService(this.domain, this.language, this.isNew);

  Future<Map<String, dynamic>> getConsumable() async {
    int pageTotal = 1;
    int page = 0;
    Map<String, dynamic> all = {};

    while (page < pageTotal) {
      final response = await http.get(Uri.parse('$domain/WikiAPI/Consumable?page_no=${page + 1}&$language'));
      final json = jsonDecode(response.body);

      pageTotal = json['meta']['page_total'] ?? 1;
      Map<String, dynamic> data = json['data'] ?? {};

      data.forEach((id, curr) {
        if (isNew) {
          curr['new'] = (await _getSavedConsumable())[id] == null;
        }

        if (curr['type'] == 'Modernization') {
          // Calculate their slots
          double price = curr['price_credit'];
          int slot = 1;
          while (price > 125000) {
            price /= 2;
            slot += 1;
          }

          // Legendary upgrades
          if (slot > 6) {
            return;
          }
          curr['slot'] = slot;
        }
      });

      // Add to all
      all.addAll(data);
      page++;
    }

    await _setSavedConsumable(all);
    return all;
  }

  Future<Map<String, dynamic>> _getSavedConsumable() async {
    final prefs = await SharedPreferences.getInstance();
    return jsonDecode(prefs.getString('SAVED.consumable') ?? '{}');
  }

  Future<void> _setSavedConsumable(Map<String, dynamic> consumable) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('SAVED.consumable', jsonEncode(consumable));
  }
}


class MapService {
  final String domain;
  final String language;

  MapService(this.domain, this.language);

  Future<List<dynamic>> getMap() async {
    final response = await http.get(Uri.parse('$domain/WikiAPI/GameMap?lang=$language'));

    if (response.statusCode == 200) {
      final json = jsonDecode(response.body);
      final map = json['data'] ?? [];
      final data = map.map((k) => k).toList();

      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('SAVED.map', jsonEncode(data));
      return data;
    } else {
      throw Exception('Failed to load map');
    }
  }
}


class PersonalRatingService {
  Future<Map<String, dynamic>> getPR() async {
    final response = await http.get(Uri.parse(WikiAPI.PersonalRating));
    
    if (response.statusCode == 200) {
      Map<String, dynamic> json = jsonDecode(response.body)['data'] ?? {};
      
      // Cleanup empty key
      json.removeWhere((key, value) => value.isEmpty);
      
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString(SAVED.pr, jsonEncode(json));
      return json;
    } else {
      throw Exception('Failed to load personal ratings');
    }
  }
}


class LocalPR {
  static const String SAVED_PR = 'saved_pr';

  Future<Map<String, dynamic>> readLocalPR() async {
    print('Reading from local');
    final String response = await rootBundle.rootBundle.loadString('data/personal_rating.json');
    final Map<String, dynamic> res = json.decode(response);
    print(res);
    Map<String, dynamic> json = guard(res, 'data', {});

    // Cleanup empty key
    json.removeWhere((key, value) => value.isEmpty);

    safeStorage.set(SAVED_PR, json);
    return json;
  }

  Map<String, dynamic> guard(Map<String, dynamic> res, String key, Map<String, dynamic> defaultValue) {
    return res.containsKey(key) ? res[key] : defaultValue;
  }
}

class SafeStorage {
  static void set(String key, Map<String, dynamic> value) {
    // Implement your storage logic here
  }
}


class Downloader extends StatefulWidget {
  @override
  _DownloaderState createState() => _DownloaderState();
}

class _DownloaderState extends State<Downloader> {
  String _status = 'Ready to download';

  Future<void> downloadFile(String url, String filename) async {
    setState(() {
      _status = 'Downloading...';
    });

    try {
      final response = await http.get(Uri.parse(url));
      final directory = await getApplicationDocumentsDirectory();
      final filePath = '${directory.path}/$filename';
      final file = File(filePath);
      await file.writeAsBytes(response.bodyBytes);

      setState(() {
        _status = 'Download complete: $filePath';
      });
    } catch (e) {
      setState(() {
        _status = 'Download failed: $e';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('File Downloader'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(_status),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                downloadFile('https://example.com/file.zip', 'file.zip');
              },
              child: Text('Download File'),
            ),
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Downloader(),
  ));
}