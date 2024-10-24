
class SafeStorage {
  static final FlutterSecureStorage _storage = FlutterSecureStorage();

  /// Get a key from secure storage and return a default value if not defined
  /// It will also save the default value if it is null
  static Future<dynamic> get(String key, dynamic value) async {
    String? data = await _storage.read(key: key);
    if (data != null) {
      // Return parsed value
      return data; // Assuming the value is already in the desired format
    } else {
      // Save the default value
      await set(key, value);
      return value;
    }
  }

  /// Set a key in secure storage
  static Future<void> set(String key, dynamic value) async {
    await _storage.write(key: key, value: value.toString());
  }
}


class Storage {
  static final FlutterSecureStorage _storage = FlutterSecureStorage();

  /// Set any values to a key
  /// @param {String} key
  /// @param {dynamic} value
  static Future<void> set(String key, dynamic value) async {
    // Stringify values to json format
    await _storage.write(key: key, value: jsonEncode(value));
  }
}


class StorageService {
  static final FlutterSecureStorage _storage = FlutterSecureStorage();

  /// Clear everything (debug only)
  static Future<void> clear() async {
    await _storage.deleteAll();
  }
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

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Demo Home Page'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}