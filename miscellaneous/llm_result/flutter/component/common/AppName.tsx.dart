
class AppName extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Image.asset(
            'assets/logo.png', // Replace with your app logo path
            width: 50,
            height: 50,
          ),
          SizedBox(width: 10),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'App Name', // Replace with your app name
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
              Text(
                getVersion(),
                style: TextStyle(
                  fontSize: 16,
                  color: Colors.grey,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  String getVersion() {
    String appVersion = APP.Version; // Replace with your app version logic
    String gameVersion = AppGlobalData.get(LOCAL.gameVersion); // Replace with your game version logic
    return '$appVersion ($gameVersion)';
  }
}


class YourWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        // Handle tap
      },
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Container(
            padding: EdgeInsets.only(left: 8),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  lang.app_name,
                  style: TextStyle(
                    color: isProVersion() ? Colors.orange[500] : null,
                  ),
                ),
                Text(
                  getVersion(),
                  style: TextStyle(),
                ),
              ],
            ),
          ),
          AnimatedContainer(
            duration: Duration(milliseconds: 1000),
            curve: Curves.ease,
            child: Image.network(
              'Logo',
              height: 64,
              width: 64,
              color: TintColour()[500],
            ),
          ),
        ],
      ),
    );
  }

  String getVersion() {
    // Implement your version retrieval logic here
    return '1.0.0'; // Placeholder
  }

  bool isProVersion() {
    // Implement your logic to check if it's a pro version
    return false; // Placeholder
  }
}


class GameWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: -8),
      // Add your content here
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Game')),
      body: GameWidget(),
    ),
  ));
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('App Name', style: TextStyle(fontWeight: FontWeight.bold)),
        ),
        body: Center(
          child: Text('Hello, World!'),
        ),
      ),
    );
  }
}


class HorizontalLayout extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 8, bottom: 0, right: 8, top: 8),
      child: Row(
        children: [
          // Add your widgets here
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
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Home Page'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}