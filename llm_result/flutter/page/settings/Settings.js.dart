
class Settings extends StatefulWidget {
  @override
  _SettingsState createState() => _SettingsState();
}

class _SettingsState extends State<Settings> {
  bool darkMode;
  Color tintColour;
  bool showColour;
  String server;
  String apiLanguage;
  String userLanguage;
  bool swapButton;

  final List<Color> colourList = [
    Colors.red,
    Colors.pink,
    Colors.purple,
    Colors.deepPurple,
    Colors.indigo,
    Colors.blue,
    Colors.lightBlue,
    Colors.cyan,
    Colors.teal,
    Colors.green,
    Colors.lightGreen,
    Colors.lime,
    Colors.yellow,
    Colors.amber,
    Colors.deepOrange,
    Colors.brown,
    Colors.grey,
    Colors.blueGrey,
  ];

  @override
  void initState() {
    super.initState();
    darkMode = AppGlobalData.isDarkMode;
    tintColour = TintColour();
    showColour = false;
    server = getCurrServer();
    apiLanguage = getAPILanguage();
    userLanguage = getUserLang();
    swapButton = getSwapButton();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Settings'),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            ListTile(
              title: Text('Dark Mode'),
              trailing: Switch(
                value: darkMode,
                onChanged: (value) {
                  setState(() {
                    darkMode = value;
                    UpdateDarkMode(value);
                  });
                },
              ),
            ),
            ListTile(
              title: Text('Server'),
              subtitle: Text(server),
              onTap: () {
                // Handle server selection
              },
            ),
            ListTile(
              title: Text('API Language'),
              subtitle: Text(apiLanguage),
              onTap: () {
                // Handle API language selection
              },
            ),
            ListTile(
              title: Text('User Language'),
              subtitle: Text(userLanguage),
              onTap: () {
                // Handle user language selection
              },
            ),
            ListTile(
              title: Text('Swap Button'),
              trailing: Checkbox(
                value: swapButton,
                onChanged: (value) {
                  setState(() {
                    swapButton = value;
                    setSwapButton(value);
                  });
                },
              ),
            ),
            ListTile(
              title: Text('Tint Colour'),
              onTap: () {
                setState(() {
                  showColour = !showColour;
                });
              },
            ),
            if (showColour)
              Wrap(
                children: colourList.map((color) {
                  return GestureDetector(
                    onTap: () {
                      setState(() {
                        tintColour = color;
                        UpdateTintColour(color);
                      });
                    },
                    child: Container(
                      width: 50,
                      height: 50,
                      color: color,
                      margin: EdgeInsets.all(4),
                    ),
                  );
                }).toList(),
              ),
          ],
        ),
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  @override
  void dispose() {
    super.dispose();
    Future.delayed(Duration(milliseconds: 300), () {
      Navigator.of(context).pushReplacement(
        MaterialPageRoute(builder: (context) => MyWidget()),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Widget'),
      ),
      body: Center(
        child: Text('Hello, World!'),
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool showColour = false;
  List<Map<int, Color>> colourList = [
    {500: Colors.red},
    {500: Colors.green},
    {500: Colors.blue},
    // Add more colors as needed
  ];

  void updateTint(Map<int, Color> item) {
    // Implement your tint update logic here
  }

  Widget renderAPISettings() {
    // Implement your API settings widget here
    return Container();
  }

  Widget renderAppSettings() {
    // Implement your app settings widget here
    return Container();
  }

  Widget renderWoWsInfo() {
    // Implement your WoWs info widget here
    return Container();
  }

  Widget renderOpenSource() {
    // Implement your open source widget here
    return Container();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            renderAPISettings(),
            renderAppSettings(),
            renderWoWsInfo(),
            renderOpenSource(),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          setState(() {
            showColour = true;
          });
        },
        child: Icon(Icons.color_lens),
      ),
      bottomSheet: showColour
          ? Container(
              color: Colors.white,
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Container(
                    height: MediaQuery.of(context).size.height * 0.618,
                    child: ListView.builder(
                      itemCount: colourList.length,
                      itemBuilder: (context, index) {
                        final item = colourList[index];
                        return GestureDetector(
                          onTap: () => updateTint(item),
                          child: Container(
                            color: item[500],
                            height: 64,
                          ),
                        );
                      },
                    ),
                  ),
                  ElevatedButton(
                    onPressed: () {
                      setState(() {
                        showColour = false;
                      });
                    },
                    child: Text('Dismiss'),
                  ),
                ],
              ),
            )
          : null,
    );
  }
}


class APISettings extends StatefulWidget {
  @override
  _APISettingsState createState() => _APISettingsState();
}

class _APISettingsState extends State<APISettings> {
  String server = 'default_server'; // Replace with actual server state
  int APILanguage = 0; // Replace with actual API language state
  String userLanguage = 'en'; // Replace with actual user language state

  List<String> SERVER = ['Server1', 'Server2', 'Server3']; // Replace with actual server list
  Map<String, String> lang = {
    'settings_api_settings': 'API Settings',
    'setting_game_server': 'Game Server',
    'setting_api_language': 'API Language',
    'setting_app_language': 'App Language',
    'server_name': 'Server Name',
  };

  List<String> getAPIList() {
    return ['API1', 'API2', 'API3']; // Replace with actual API list
  }

  void updateServer(int index) {
    setState(() {
      server = SERVER[index];
    });
  }

  void updateUserLang(String code) {
    setState(() {
      userLanguage = code;
    });
  }

  Widget renderAPILanguage(List<String> langList) {
    // Implement your API language rendering logic here
    return Container(); // Placeholder
  }

  @override
  Widget build(BuildContext context) {
    List<String> langList = getAPIList();
    Map<String, String> appLang = {
      'en': 'English',
      'ja': '日本語',
      'zh': '简体中文',
      'zh-hant': '繁体中文',
    };

    List<Map<String, String>> appLangList = appLang.entries
        .map((entry) => {'code': entry.key, 'lang': entry.value})
        .toList();

    String display = appLang[userLanguage] ?? '???';

    return Scaffold(
      appBar: AppBar(title: Text(lang['settings_api_settings'])),
      body: SingleChildScrollView(
        child: Column(
          children: [
            ListTile(
              title: Text('${lang['setting_game_server']} - ${lang['server_name']}'),
              subtitle: Wrap(
                children: SERVER.asMap().entries.map((entry) {
                  int index = entry.key;
                  String value = entry.value;
                  return ElevatedButton(
                    onPressed: () => updateServer(index),
                    child: Text(lang['server_name']),
                  );
                }).toList(),
              ),
            ),
            ListTile(
              title: Text('${lang['setting_api_language']} - ${langList[APILanguage]}'),
              subtitle: renderAPILanguage(langList),
            ),
            ListTile(
              title: Text('${lang['setting_app_language']} - $display'),
              subtitle: Wrap(
                children: appLangList.map((item) {
                  return ElevatedButton(
                    onPressed: () => updateUserLang(item['code']),
                    child: Text(item['lang']),
                  );
                }).toList(),
              ),
            ),
          ],
        ),
      ),
    );
  }
}


class LanguageSelector extends StatefulWidget {
  final Map<String, String> langList;
  final String appName;
  final String settingApiUpdateDataTitle;
  final String settingApiUpdateDataUpdate;
  final String settingApiUpdateDataCancel;
  final String settingApiUpdateData;

  LanguageSelector({
    required this.langList,
    required this.appName,
    required this.settingApiUpdateDataTitle,
    required this.settingApiUpdateDataUpdate,
    required this.settingApiUpdateDataCancel,
    required this.settingApiUpdateData,
  });

  @override
  _LanguageSelectorState createState() => _LanguageSelectorState();
}

class _LanguageSelectorState extends State<LanguageSelector> {
  String? apiLanguage;

  void updateApiLanguage(String language, [bool showAlert = false]) {
    // Implement your update logic here
    if (showAlert) {
      // Handle the update confirmation logic
    }
    setState(() {
      apiLanguage = language;
    });
  }

  @override
  Widget build(BuildContext context) {
    List<String> langData = widget.langList.keys.toList()..sort();

    return Column(
      children: [
        Wrap(
          direction: Axis.horizontal,
          children: langData.map((item) {
            return ElevatedButton(
              onPressed: () => updateApiLanguage(item),
              child: Text(widget.langList[item]!),
            );
          }).toList(),
        ),
        ElevatedButton(
          onPressed: () {
            showDialog(
              context: context,
              builder: (BuildContext context) {
                return AlertDialog(
                  title: Text(widget.appName),
                  content: Text(widget.settingApiUpdateDataTitle),
                  actions: [
                    TextButton(
                      onPressed: () {
                        updateApiLanguage(apiLanguage!, true);
                        Navigator.of(context).pop();
                      },
                      child: Text(widget.settingApiUpdateDataUpdate),
                    ),
                    TextButton(
                      onPressed: () {
                        Navigator.of(context).pop();
                      },
                      child: Text(widget.settingApiUpdateDataCancel),
                    ),
                  ],
                );
              },
            );
          },
          child: Text(widget.settingApiUpdateData),
        ),
      ],
    );
  }
}


class AppSettings extends StatefulWidget {
  @override
  _AppSettingsState createState() => _AppSettingsState();
}

class _AppSettingsState extends State<AppSettings> {
  bool darkMode = false;
  bool swapButton = false;
  Color tintColour = Colors.blue; // Example color
  bool showColour = false;

  void updateTheme() {
    setState(() {
      darkMode = !darkMode;
    });
  }

  void swapButtonState(bool value) {
    setState(() {
      swapButton = value;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(
          'App Settings', // Replace with your localization
          style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
        ),
        ListTile(
          title: Text('Dark Mode'), // Replace with your localization
          trailing: Checkbox(
            value: darkMode,
            onChanged: (value) {
              updateTheme();
            },
          ),
          onTap: updateTheme,
        ),
        ListTile(
          title: Text('Theme Colour'), // Replace with your localization
          trailing: Container(
            width: 24,
            height: 24,
            color: tintColour,
          ),
          onTap: () {
            setState(() {
              showColour = true;
            });
          },
        ),
        ListTile(
          title: Text('Swap Buttons'), // Replace with your localization
          trailing: Checkbox(
            value: swapButton,
            onChanged: (value) {
              swapButtonState(value!);
            },
          ),
          onTap: () {
            swapButtonState(!swapButton);
          },
        ),
      ],
    );
  }
}


class WoWsInfo extends StatelessWidget {
  final String appName = 'Your App Name'; // Replace with actual app name
  final String developerUrl = 'https://yourdeveloperurl.com'; // Replace with actual developer URL
  final String issueLink = 'https://github.com/yourrepo/issues/new'; // Replace with actual issue link
  final String version = '1.0.0'; // Replace with actual version
  final bool isAndroid = true; // Replace with actual platform check

  void _openURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  void _checkAppUpdate() {
    // Implement your app update check logic here
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(
          appName,
          style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
        ),
        ListTile(
          title: Text('Send Feedback'),
          subtitle: Text('Send your feedback about the app'),
          onTap: () => _openURL(developerUrl),
        ),
        ListTile(
          title: Text('Report Issues'),
          subtitle: Text(issueLink),
          onTap: () => _openURL(issueLink),
        ),
        if (isAndroid) 
          ListTile(
            title: Text('Check for Update'),
            subtitle: Text('v$version'),
            onTap: _checkAppUpdate,
          ),
      ],
    );
  }
}


class OpenSourceView extends StatelessWidget {
  final String githubUrl = 'https://github.com/your-repo'; // Replace with actual GitHub URL
  final String licenseSubtitle = 'View the license details here'; // Replace with actual subtitle

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        SectionTitle(title: 'Open Source'), // Replace with actual localization
        ListTile(
          title: Text('GitHub Repository'), // Replace with actual localization
          subtitle: Text(githubUrl),
          onTap: () => _openURL(githubUrl),
        ),
        ListTile(
          title: Text('License'), // Replace with actual localization
          subtitle: Text(licenseSubtitle),
          onTap: () => _safeAction('License'),
        ),
      ],
    );
  }

  void _openURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  void _safeAction(String action) {
    // Implement your safe action logic here
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: Text(
        title,
        style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
      ),
    );
  }
}


class AppGlobalData {
  static bool canCheckForUpdate = true;
}

class APP {
  static const String Version = "1.0.0"; // Replace with your app's current version
}

class UpdateChecker {
  void checkAppUpdate(BuildContext context) {
    if (AppGlobalData.canCheckForUpdate) {
      AppGlobalData.canCheckForUpdate = false;
      SafeFetch.normal(WikiAPI.Github_AppVersion).then((v) {
        String? version = Guard(v, 'version', null);
        if (version != null) {
          if (version.compareTo(APP.Version) > 0) {
            displayUpdate(context, true, version);
          } else {
            displayUpdate(context, false);
          }
        }
      });
    } else {
      displayUpdate(context, false);
    }
  }

  void displayUpdate(BuildContext context, bool hasUpdate, [String? version]) {
    if (hasUpdate) {
      // Show update available dialog
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text("Update Available"),
            content: Text("A new version $version is available."),
            actions: <Widget>[
              TextButton(
                child: Text("OK"),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
    } else {
      // Show no update dialog
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text("No Update"),
            content: Text("You are on the latest version."),
            actions: <Widget>[
              TextButton(
                child: Text("OK"),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
    }
  }
}

class SafeFetch {
  static Future<Map<String, dynamic>> normal(String url) async {
    final response = await http.get(Uri.parse(url));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }
}

class WikiAPI {
  static const String Github_AppVersion = 'https://api.github.com/repos/your_repo/releases/latest'; // Replace with your API URL
}

String? Guard(Map<String, dynamic> data, String key, String? defaultValue) {
  return data.containsKey(key) ? data[key] as String? : defaultValue;
}


class UpdateDialog {
  static void displayUpdate(BuildContext context, bool result, String? version) {
    if (result) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text(lang.app_name),
            content: Text(format(lang.settings_app_has_update, version)),
            actions: <Widget>[
              TextButton(
                child: Text('Google Play'),
                onPressed: () {
                  _launchURL(APP.GooglePlay);
                },
              ),
              TextButton(
                child: Text('Github'),
                onPressed: () {
                  _launchURL(APP.LatestRelease);
                },
              ),
            ],
          );
        },
      );
    } else {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text(lang.app_name),
            content: Text(lang.settings_app_no_update),
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
  }

  static Future<void> _launchURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool swapButton = false;

  void setSwapButton(bool value) {
    setState(() {
      swapButton = value;
    });
  }

  bool getSwapButton() {
    return swapButton;
  }

  void swapButtonFunction(bool curr) {
    setSwapButton(curr);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Swap Button Example'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            swapButtonFunction(!swapButton);
          },
          child: Text(swapButton ? 'Button Swapped' : 'Original Button'),
        ),
      ),
    );
  }
}


class ThemeProvider with ChangeNotifier {
  bool _isDarkMode = false;
  Color tintColor = Colors.blue; // Example tint color

  bool get isDarkMode => _isDarkMode;

  void updateTheme() {
    _isDarkMode = !_isDarkMode;
    notifyListeners();
  }

  ThemeData get currentTheme {
    if (_isDarkMode) {
      return ThemeData(
        brightness: Brightness.dark,
        scaffoldBackgroundColor: Colors.black,
        primaryColor: tintColor,
        accentColor: tintColor.withOpacity(0.7),
        textTheme: TextTheme(
          bodyText1: TextStyle(color: Colors.grey[50]),
        ),
      );
    } else {
      return ThemeData(
        brightness: Brightness.light,
        scaffoldBackgroundColor: Colors.white,
        primaryColor: tintColor,
        accentColor: tintColor.withOpacity(0.7),
        textTheme: TextTheme(
          bodyText1: TextStyle(color: Colors.grey[900]),
        ),
      );
    }
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => ThemeProvider(),
      child: Consumer<ThemeProvider>(
        builder: (context, themeProvider, child) {
          return MaterialApp(
            theme: themeProvider.currentTheme,
            home: Scaffold(
              appBar: AppBar(
                title: Text('Theme Switcher'),
                actions: [
                  IconButton(
                    icon: Icon(Icons.brightness_6),
                    onPressed: () {
                      themeProvider.updateTheme();
                    },
                  ),
                ],
              ),
              body: Center(
                child: Text('Hello, World!'),
              ),
            ),
          );
        },
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Color primaryColor = Colors.blue;
  Color accentColor = Colors.red;
  bool showColour = false;
  Map<int, Color> tintColour = {
    500: Colors.blue,
    300: Colors.blueAccent,
  };

  void updateTint(Map<int, Color> tint) {
    setState(() {
      primaryColor = tint[500]!;
      accentColor = tint[300]!;
      showColour = false;
      tintColour = tint;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primaryColor: primaryColor,
        accentColor: accentColor,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Update Tint Color'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              updateTint({
                500: Colors.green,
                300: Colors.greenAccent,
              });
            },
            child: Text('Update Tint'),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


class ServerUpdater extends StatefulWidget {
  @override
  _ServerUpdaterState createState() => _ServerUpdaterState();
}

class _ServerUpdaterState extends State<ServerUpdater> {
  int server;

  void setCurrServer(int index) {
    // Implement your logic to set the current server here
  }

  void updateServer(int index) {
    setCurrServer(index);
    setState(() {
      server = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Server Updater'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () => updateServer(1), // Example index
          child: Text('Update Server'),
        ),
      ),
    );
  }
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String apiLanguage;
  bool firstLaunch = true;

  void updateApiLanguage(String language, bool force) {
    if (!force && language == apiLanguage) {
      return;
    }

    setAPILanguage(language);
    setState(() {
      apiLanguage = language;
    });

    setFirstLaunch(true);
    AppGlobalData.shouldUpdateAPI = false;
    Navigator.of(context).pushReplacementNamed('/menu');
  }

  void setAPILanguage(String language) {
    // Implement the logic to set the API language
  }

  void setFirstLaunch(bool value) {
    firstLaunch = value;
    // Implement any additional logic for first launch
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {
        '/': (context) => HomeScreen(),
        '/menu': (context) => MenuScreen(),
      },
    );
  }
}

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Home')),
      body: Center(child: Text('Home Screen')),
    );
  }
}

class MenuScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Menu')),
      body: Center(child: Text('Menu Screen')),
    );
  }
}

class AppGlobalData {
  static bool shouldUpdateAPI = false;
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String userLanguage = 'en';

  void updateUserLang(String code) {
    setUserLang(code);
    setState(() {
      userLanguage = code;
    });
  }

  void setUserLang(String code) {
    // Implement your logic to set user language here
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      locale: Locale(userLanguage),
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: [
        const Locale('en', ''), // English
        const Locale('es', ''), // Spanish
        // Add other supported locales here
      ],
      home: Scaffold(
        appBar: AppBar(
          title: Text('Language Update Example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              updateUserLang('es'); // Example of changing language to Spanish
            },
            child: Text('Change Language to Spanish'),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          color: Colors.white,
          child: Center(
            child: Text('Hello, Flutter!'),
          ),
        ),
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        // Your other widgets here
        Positioned(
          left: 0,
          right: 0,
          bottom: 0,
          child: Container(
            // Add your content here
            color: Colors.blue, // Example color
            height: 100, // Example height
          ),
        ),
      ],
    );
  }
}


class TintWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 36,
      width: 36,
      decoration: BoxDecoration(
        color: Colors.blue, // You can change the color as needed
        borderRadius: BorderRadius.circular(18),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      body: Center(
        child: TintWidget(),
      ),
    ),
  ));
}


class Settings extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Settings'),
        ),
        body: Center(
          child: Text('Settings Page'),
        ),
      ),
    );
  }
}

void main() {
  runApp(Settings());
}