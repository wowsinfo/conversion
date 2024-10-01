
class LicensePage extends StatelessWidget {
  final List<Map<String, String>> libraries = [
    {'name': 'react', 'link': 'https://github.com/facebook/react'},
    {'name': 'react-native', 'link': 'https://github.com/facebook/react-native'},
    {'name': 'react-native-animatable', 'link': 'https://github.com/oblador/react-native-animatable'},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Libraries Used in WoWs Info'),
      ),
      body: ResponsiveGridList(
        horizontalGridSpacing: 10,
        verticalGridSpacing: 10,
        minItemWidth: 200,
        children: libraries.map((library) {
          return Card(
            child: ListTile(
              title: Text(library['name']!),
              onTap: () {
                launch(library['link']!);
              },
            ),
          );
        }).toList(),
      ),
    );
  }

  void launch(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}


class DeviceDetection extends StatefulWidget {
  @override
  _DeviceDetectionState createState() => _DeviceDetectionState();
}

class _DeviceDetectionState extends State<DeviceDetection> {
  String deviceInfo = "Unknown";

  @override
  void initState() {
    super.initState();
    _getDeviceInfo();
  }

  Future<void> _getDeviceInfo() async {
    DeviceInfoPlugin deviceInfoPlugin = DeviceInfoPlugin();
    String info;

    if (Theme.of(context).platform == TargetPlatform.android) {
      AndroidDeviceInfo androidInfo = await deviceInfoPlugin.androidInfo;
      info = 'Running on ${androidInfo.model}';
    } else if (Theme.of(context).platform == TargetPlatform.iOS) {
      IosDeviceInfo iosInfo = await deviceInfoPlugin.iosInfo;
      info = 'Running on ${iosInfo.utsname.machine}';
    } else {
      info = 'Unsupported platform';
    }

    setState(() {
      deviceInfo = info;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Device Detection'),
      ),
      body: Center(
        child: Text(
          deviceInfo,
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: DeviceDetection(),
  ));
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Exception Handler',
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
          onPressed: () {
            throw Exception('This is a test exception');
          },
          child: Text('Throw Exception'),
        ),
      ),
    );
  }
}

class ErrorHandler {
  static void handleError(Object error, StackTrace stackTrace) {
    // Handle the error here, e.g., log it or show a dialog
    print('Caught an error: $error');
    print('Stack trace: $stackTrace');
  }
}

void runZonedGuarded(Function() body, void Function(Object, StackTrace) onError) {
  runZoned(
    body,
    zoneSpecification: ZoneSpecification(
      handleUncaughtError: (Zone self, ZoneDelegate parent, Zone zone, Object error, StackTrace stackTrace) {
        onError(error, stackTrace);
      },
    ),
  );
}

void main() {
  runZonedGuarded(() {
    runApp(MyApp());
  }, ErrorHandler.handleError);
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter IAP Example',
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<IAPItem> items = [];
  String? _purchaseStatus;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {
    await FlutterInappPurchase.instance.initConnection;
    List<IAPItem> result = await FlutterInappPurchase.instance.getProducts(['your_product_id']);
    setState(() {
      items = result;
    });
  }

  Future<void> buyProduct(IAPItem item) async {
    try {
      var result = await FlutterInappPurchase.instance.requestPurchase(item.productId);
      setState(() {
        _purchaseStatus = 'Purchase successful: ${result.toString()}';
      });
    } catch (error) {
      setState(() {
        _purchaseStatus = 'Purchase failed: $error';
      });
    }
  }

  @override
  void dispose() {
    FlutterInappPurchase.instance.endConnection;
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('In-App Purchase Example'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          if (items.isNotEmpty)
            ...items.map((item) {
              return ElevatedButton(
                onPressed: () => buyProduct(item),
                child: Text('Buy ${item.title} for ${item.price}'),
              );
            }).toList(),
          if (_purchaseStatus != null)
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(_purchaseStatus!),
            ),
        ],
      ),
    );
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Keep Awake Example',
      home: KeepAwakePage(),
    );
  }
}

class KeepAwakePage extends StatefulWidget {
  @override
  _KeepAwakePageState createState() => _KeepAwakePageState();
}

class _KeepAwakePageState extends State<KeepAwakePage> {
  bool _isAwake = false;

  void _toggleAwake() {
    setState(() {
      _isAwake = !_isAwake;
      if (_isAwake) {
        Wakelock.enable();
      } else {
        Wakelock.disable();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Keep Awake Example'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              _isAwake ? 'Device is awake' : 'Device is asleep',
              style: TextStyle(fontSize: 24),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _toggleAwake,
              child: Text(_isAwake ? 'Turn Off Keep Awake' : 'Turn On Keep Awake'),
            ),
          ],
        ),
      ),
    );
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Localization',
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
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _currentLanguage = 'en';

  void _changeLanguage(String languageCode) {
    setState(() {
      _currentLanguage = languageCode;
    });
  }

  String get _localizedString {
    switch (_currentLanguage) {
      case 'es':
        return 'Hola Mundo';
      case 'en':
      default:
        return 'Hello World';
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Localization Example'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              _localizedString,
              style: TextStyle(fontSize: 24),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => _changeLanguage('en'),
              child: Text('English'),
            ),
            ElevatedButton(
              onPressed: () => _changeLanguage('es'),
              child: Text('Spanish'),
            ),
          ],
        ),
      ),
    );
  }
}


class MaterialColorExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Material Color Example',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Material Color Example'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                color: Colors.red,
                width: 100,
                height: 100,
                child: Center(child: Text('Red')),
              ),
              SizedBox(height: 20),
              Container(
                color: Colors.green,
                width: 100,
                height: 100,
                child: Center(child: Text('Green')),
              ),
              SizedBox(height: 20),
              Container(
                color: Colors.blue,
                width: 100,
                height: 100,
                child: Center(child: Text('Blue')),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialColorExample());
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Page'),
      ),
      body: Center(
        child: Text(
          'Hello, World!',
          style: TextStyle(fontSize: 24),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Add your action here
        },
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Router Example',
      initialRoute: '/',
      routes: {
        '/': (context) => HomeScreen(),
        '/second': (context) => SecondScreen(),
      },
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
          onPressed: () {
            Navigator.pushNamed(context, '/second');
          },
          child: Text('Go to Second Screen'),
        ),
      ),
    );
  }
}

class SecondScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Second Screen'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            Navigator.pop(context);
          },
          child: Text('Back to Home Screen'),
        ),
      ),
    );
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Super Grid',
      home: SuperGridScreen(),
    );
  }
}

class SuperGridScreen extends StatelessWidget {
  final List<String> items = List.generate(20, (index) => 'Item $index');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Super Grid'),
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          childAspectRatio: 1,
        ),
        itemCount: items.length,
        itemBuilder: (context, index) {
          return Card(
            child: Center(
              child: Text(
                items[index],
                style: TextStyle(fontSize: 20),
              ),
            ),
          );
        },
      ),
    );
  }
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Vector Icons',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter Vector Icons Example'),
        ),
        body: Center(
          child: Icon(
            MaterialCommunityIcons.home, // Example icon from MaterialCommunityIcons
            size: 100.0,
            color: Colors.blue,
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
      title: 'String Format Example',
      home: Scaffold(
        appBar: AppBar(
          title: Text('String Format Example'),
        ),
        body: Center(
          child: StringFormatExample(),
        ),
      ),
    );
  }
}

class StringFormatExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    String formattedString = stringFormat('Hello, {0}!', ['World']);
    return Text(formattedString);
  }

  String stringFormat(String format, List<String> args) {
    for (int i = 0; i < args.length; i++) {
      format = format.replaceAll('{$i}', args[i]);
    }
    return format;
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Native Chart Experiment',
      home: ChartPage(),
    );
  }
}

class ChartPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Native Chart Experiment'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: LineChart(
          LineChartData(
            gridData: FlGridData(show: false),
            titlesData: FlTitlesData(
              leftTitles: SideTitles(showTitles: true),
              bottomTitles: SideTitles(showTitles: true),
            ),
            borderData: FlBorderData(show: true),
            lineBarsData: [
              LineChartBarData(
                spots: [
                  FlSpot(0, 1),
                  FlSpot(1, 3),
                  FlSpot(2, 2),
                  FlSpot(3, 5),
                  FlSpot(4, 4),
                ],
                isCurved: true,
                colors: [Colors.blue],
                dotData: FlDotData(show: false),
                belowBarData: BarAreaData(show: false),
              ),
            ],
          ),
        ),
      ),
    );
  }
}


class License extends StatelessWidget {
  final List<Map<String, String>> libraries = [
    {'name': 'WoWs Info', 'link': 'https://github.com/HenryQuan/WoWs-Info'},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('License'),
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 1,
          childAspectRatio: 3,
        ),
        itemCount: libraries.length,
        itemBuilder: (context, index) {
          final item = libraries[index];
          return ListTile(
            title: Text(item['name']!),
            subtitle: Text(item['link']!),
            onTap: () async {
              final url = item['link']!;
              if (await canLaunch(url)) {
                await launch(url);
              } else {
                throw 'Could not launch $url';
              }
            },
          );
        },
      ),
    );
  }
}