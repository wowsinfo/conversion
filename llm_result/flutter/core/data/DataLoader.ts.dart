
class DataLoader {
  /// Load all data from storage
  /// Return a map with all data
  static Future<Map<String, dynamic>> loadAll() async {
    Map<String, dynamic> local = await loadLocal();
    Map<String, dynamic> saved = await loadSaved();
    return {...local, ...saved};
  }

  static Future<Map<String, dynamic>> loadLocal() async {
    // Implement your local data loading logic here
    SharedPreferences prefs = await SharedPreferences.getInstance();
    // Example of loading a string
    String? exampleData = prefs.getString('exampleKey');
    return {
      'exampleKey': exampleData,
    };
  }

  static Future<Map<String, dynamic>> loadSaved() async {
    // Implement your saved data loading logic here
    SharedPreferences prefs = await SharedPreferences.getInstance();
    // Example of loading a list
    List<String>? savedData = prefs.getStringList('savedKey');
    return {
      'savedKey': savedData,
    };
  }
}


class LocalData {
  static Future<Map<String, dynamic>> loadLocal() async {
    final prefs = await SharedPreferences.getInstance();

    String apiLanguage = 'en';
    String appVersion = '1.0.0'; // Replace with actual version
    String gameVersion = '1.0.0'; // Replace with actual version
    bool firstLaunch = true;
    Map<String, dynamic> friendList = {};
    Map<String, dynamic> userData = {};
    bool showBanner = true;
    bool showFullscreen = false;
    Map<String, dynamic> userInfo = {'nickname': '', 'account_id': '', 'server': 3};
    int userServer = 3;
    String lastUpdate = DateTime.now().toString();
    String theme = 'RED'; // Replace with actual theme
    bool darkMode = false;
    String date = DateTime.now().toString();
    bool swapButton = false;
    String userLanguage = 'en';
    String rsIP = '';
    String lastLocation = '';
    bool proVersion = false;

    Map<String, dynamic> data = {};

    data['apiLanguage'] = apiLanguage;
    data['userLanguage'] = userLanguage;
    data['swapButton'] = swapButton;
    data['appVersion'] = appVersion;
    data['gameVersion'] = gameVersion;
    data['firstLaunch'] = firstLaunch;
    data['rsIP'] = rsIP;
    data['lastLocation'] = lastLocation;
    data['showBanner'] = showBanner;
    data['showFullscreen'] = showFullscreen;
    data['proVersion'] = proVersion;

    if (Theme.of(context).platform == TargetPlatform.macOS) {
      data['proVersion'] = true;
    }

    Map<String, dynamic> list = {
      'clan': {
        '2000020641': {'tag': 'ICBC', 'clan_id': '2000020641', 'server': 3},
      },
      'player': {
        '2011774448': {
          'nickname': 'HenryQuan',
          'account_id': '2011774448',
          'server': 3,
        },
      },
    };

    data['friendList'] = list;

    if (data['friendList']['player'] == null) {
      Map<String, dynamic> saved = {'clan': {}, 'player': {}};
      for (var v in data['friendList']['player'].values) {
        saved['player'][v['account_id']] = formatConverter(v);
      }
      data['friendList'] = saved;
      await prefs.setString('friendList', saved.toString());
    }

    if (data['friendList']['clan']['2000008934'] != null) {
      data['friendList']['clan'].remove('2000008934');
      data['friendList']['clan']['2000020641'] = {'tag': 'ICBC', 'clan_id': '2000020641', 'server': 3};
      await prefs.setString('friendList', data['friendList'].toString());
    }

    data['userData'] = userData;
    data['userInfo'] = userInfo;

    if (data['userInfo']['nickname'] == null) {
      var formatted = formatConverter(data['userInfo']);
      data['userInfo'] = formatted;
      await prefs.setString('userInfo', formatted.toString());
    }

    data['userServer'] = userServer;
    data['lastUpdate'] = lastUpdate;
    data['theme'] = theme;
    data['darkMode'] = darkMode;
    data['date'] = date;

    return data;
  }

  static Map<String, dynamic> formatConverter(Map<String, dynamic> info) {
    // Implement your format conversion logic here
    return info;
  }
}

  static Map<String, dynamic> formatConverter(Map<String, dynamic> obj) {
    if (obj.containsKey('name')) {
      obj['nickname'] = obj['name'];
      obj.remove('name');
    }

    if (obj.containsKey('id')) {
      obj['account_id'] = obj['id'];
      obj.remove('id');
    }

    return obj;
  }
}


class DataLoader {
  static final FlutterSecureStorage secureStorage = FlutterSecureStorage();
  static const Map<String, String> SAVED = {
    // Define your keys here
    'key1': 'value1',
    'key2': 'value2',
    // Add more keys as needed
  };

  /// Load all saved data, Internet connection is required
  static Future<Map<String, dynamic>> loadSaved() async {
    Map<String, dynamic> data = {};
    for (String key in SAVED.keys) {
      String curr = SAVED[key]!;
      String? value = await secureStorage.read(key: curr);
      data[curr] = value ?? {};
    }
    return data;
  }
}


class StorageService {
  static final FlutterSecureStorage _storage = FlutterSecureStorage();

  /// Load and setup entries
  /// @param {Map<String, dynamic>} data
  /// @param {String} key
  /// @param {dynamic} value
  static Future<void> loadEntry(Map<String, dynamic> data, String key, dynamic value) async {
    String? storedValue = await _storage.read(key: key);
    data[key] = storedValue ?? value;
  }
}


class DataLoader extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Data Loader'),
      ),
      body: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: DataLoader(),
  ));
}