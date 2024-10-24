
class SafeAction {
  static void navigate(BuildContext context, String screen, {Object? obj, int max = 0}) {
    final navigator = Navigator.of(context);
    final currentRoute = ModalRoute.of(context)?.settings.name;

    if (currentRoute == screen && max == 0) {
      print('$screen rejected');
      return;
    }

    final routes = navigator.widget.pages;
    final screenCount = routes.where((route) => route.name == screen).length;

    if (screenCount > max) {
      print('$screen rejected');
    } else {
      navigator.pushNamed(screen, arguments: obj);
    }
  }
}

  context,
  MaterialPageRoute(
    builder: (context) => YourScreen(obj: obj),
  ),
).then((_) {
  print('$screen pushed');
});


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