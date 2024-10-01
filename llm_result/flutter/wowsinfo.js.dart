
void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Your App',
      theme: ThemeData.light(),
      darkTheme: ThemeData.dark(),
      home: HomeScreen(),
      navigatorObservers: [HeroController()],
    );
  }
}

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  void initState() {
    super.initState();
    SystemChannels.platform.setMethodCallHandler((call) async {
      if (call.method == 'backButtonPressed') {
        _onBackPressed();
      }
    });
  }

  Future<void> _onBackPressed() async {
    bool shouldExit = await showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Exit App'),
        content: Text('Do you want to exit the app?'),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(false),
            child: Text('No'),
          ),
          TextButton(
            onPressed: () => Navigator.of(context).pop(true),
            child: Text('Yes'),
          ),
        ],
      ),
    );

    if (shouldExit) {
      SystemNavigator.pop();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
      ),
      body: Center(
        child: Text('Welcome to Your App!'),
      ),
    );
  }
}

void setJSExceptionHandler(Function(dynamic, bool) handler) {
  // Implement your exception handling logic here
}

void showAlert(String message, String type) {
  // Implement your alert logic here
}

  // Your code logic here
} catch (e) {
  print('JSException\n$e');
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool loading = true;
  bool dark = false;

  @override
  void initState() {
    super.initState();
    setup();
  }

  Future<void> setup() async {
    // Simulate loading data from AsyncStorage
    final prefs = await SharedPreferences.getInstance();
    String userLang = prefs.getString('userLanguage') ?? '';
    dark = prefs.getBool('darkMode') ?? false;

    // Setup themes
    ThemeData darkTheme = ThemeData.dark().copyWith(
      primaryColor: Colors.red,
      accentColor: Colors.redAccent,
    );

    ThemeData lightTheme = ThemeData.light().copyWith(
      primaryColor: Colors.red,
      accentColor: Colors.redAccent,
    );

    // Set the theme based on user preference
    ThemeData theme = dark ? darkTheme : lightTheme;

    // Simulate checking for first launch
    bool firstLaunch = await getFirstLaunch();
    if (!firstLaunch) {
      // Update data here if it is not first launch
      var result = await updateData();
      if (!result['status']) {
        showAlert(result['log']);
      }
    }

    setState(() {
      loading = false;
    });
  }

  Future<bool> getFirstLaunch() async {
    // Implement your logic to check for first launch
    return false;
  }

  Future<Map<String, dynamic>> updateData() async {
    // Simulate data update
    return {'status': true, 'log': ''};
  }

  void showAlert(String msg) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('FATAL ERROR'),
          content: Text('$msg\n\nPlease contact developer'),
          actions: <Widget>[
            TextButton(
              child: Text('OK'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('E-mail'),
              onPressed: () async {
                final Uri emailLaunchUri = Uri(
                  scheme: 'mailto',
                  path: 'development.henryquan@gmail.com',
                  query: 'subject=[WoWs Info] &body=$msg',
                );
                if (await canLaunch(emailLaunchUri.toString())) {
                  await launch(emailLaunchUri.toString());
                } else {
                  throw 'Could not launch $emailLaunchUri';
                }
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: dark ? ThemeData.dark() : ThemeData.light(),
      home: loading
          ? Center(child: CircularProgressIndicator())
          : Scaffold(
              appBar: AppBar(
                title: Text('My App'),
              ),
              body: Center(
                child: Text('Welcome to My App!'),
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
  bool loading = true;
  bool dark = false;

  @override
  void initState() {
    super.initState();
    // Simulate loading
    Future.delayed(Duration(seconds: 2), () {
      setState(() {
        loading = false;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    if (loading) {
      return Loading();
    }
    return MaterialApp(
      theme: ThemeData(
        brightness: dark ? Brightness.dark : Brightness.light,
      ),
      home: Navigator(
        onGenerateRoute: (RouteSettings settings) {
          switch (settings.name) {
            case '/Menu':
              return MaterialPageRoute(builder: (context) => Menu());
            case '/Setup':
              return MaterialPageRoute(builder: (context) => Setup());
            case '/Search':
              return MaterialPageRoute(builder: (context) => Search());
            case '/RS':
              return MaterialPageRoute(builder: (context) => RS());
            case '/Rating':
              return MaterialPageRoute(builder: (context) => Rating());
            case '/Statistics':
              return MaterialPageRoute(builder: (context) => Statistics());
            case '/Graph':
              return MaterialPageRoute(builder: (context) => Graph());
            case '/PlayerAchievement':
              return MaterialPageRoute(builder: (context) => PlayerAchievement());
            case '/PlayerShip':
              return MaterialPageRoute(builder: (context) => PlayerShip());
            case '/PlayerShipDetail':
              return MaterialPageRoute(builder: (context) => Detailed());
            case '/Rank':
              return MaterialPageRoute(builder: (context) => Rank());
            case '/ClanInfo':
              return MaterialPageRoute(builder: (context) => ClanInfo());
            case '/Consumable':
              return MaterialPageRoute(builder: (context) => Consumable());
            case '/CommanderSkill':
              return MaterialPageRoute(builder: (context) => CommanderSkill());
            case '/Achievement':
              return MaterialPageRoute(builder: (context) => Achievement());
            case '/Map':
              return MaterialPageRoute(builder: (context) => GameMap());
            case '/Collection':
              return MaterialPageRoute(builder: (context) => Collection());
            case '/Warship':
              return MaterialPageRoute(builder: (context) => Warship());
            case '/WarshipFilter':
              return MaterialPageRoute(builder: (context) => WarshipFilter());
            case '/SimilarGraph':
              return MaterialPageRoute(builder: (context) => SimilarGraph());
            case '/WarshipDetail':
              return MaterialPageRoute(builder: (context) => WarshipDetail());
            case '/WarshipModule':
              return MaterialPageRoute(builder: (context) => WarshipModule());
            case '/BasicDetail':
              return MaterialPageRoute(builder: (context) => BasicDetail());
            case '/Settings':
              return MaterialPageRoute(builder: (context) => Settings());
            case '/License':
              return MaterialPageRoute(builder: (context) => License());
            case '/About':
              return MaterialPageRoute(builder: (context) => About());
            case '/ProVersion':
              return MaterialPageRoute(builder: (context) => ProVersion());
            default:
              return MaterialPageRoute(builder: (context) => Menu());
          }
        },
      ),
    );
  }
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Screen'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () => handleBack(context),
          child: Text('Back'),
        ),
      ),
    );
  }

  void handleBack(BuildContext context) {
    if (Navigator.of(context).canPop()) {
      Navigator.of(context).pop();
    } else {
      SystemNavigator.pop();
    }
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
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Screen'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}