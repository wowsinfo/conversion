class AppGlobalData {
  // We store saved and local data as a single object
  static Map<String, dynamic> dataSource = {};

  static void setupWith(Map<String, dynamic>? data) {
    if (data == null) {
      throw Exception(
        'The app cannot continue because there is a problem with the data. Please try again later.',
      );
    }

    AppGlobalData.dataSource = data;
  }
}

  static Map<String, dynamic> dataSource = {};

  static dynamic get(String key) {
    return dataSource[key];
  }
}

class AppGlobalData {
  static final Map<String, dynamic> dataSource = {};

  static void set(String key, dynamic value) {
    if (key == null || value == null) {
      print('AppGlobalData.set() cannot set null key or value');
      StackTrace.current;
      return;
    }

    // if value is a Future, we need to handle it
    if (value is Future) {
      print('AppGlobalData.set() value is a Future');
      StackTrace.current;
      return;
    }

    dataSource[key] = value;
  }
}


class AppGlobalData {
  static var dataSource = 'Your data source here'; // Replace with actual data source
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Data Source Example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: printDataSource,
            child: Text('Print Data Source'),
          ),
        ),
      ),
    );
  }

  void printDataSource() {
    print(AppGlobalData.dataSource);
  }
}

void main() {
  runApp(MyApp());
}

class AppGlobalData {
  static Map<String, dynamic> lightTheme = {};
  static Map<String, dynamic> darkTheme = {};
  static bool isDarkMode = false;

  // App Settings
  static bool shouldSwapButton = false;
  // static bool useNoImageMode = false; // not used
  // static bool useCleanMode = false; // not used

  // You can only check one time
  static bool canCheckForUpdate = true;
  // Only update api once as well
  static bool shouldUpdateAPI = true;

  // Trace how many battles
  static int realtimeBattleCount = 0;

  // Trace last known location
  static String lastLocation = '';

  static bool githubVersion = false;
}

final appGlobalData = AppGlobalData();