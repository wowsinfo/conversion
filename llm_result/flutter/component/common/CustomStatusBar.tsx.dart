
class CustomStatusBar extends StatelessWidget {
  final Widget? child;
  final Color? backgroundColor;
  final bool? dark;

  const CustomStatusBar({
    Key? key,
    this.child,
    this.backgroundColor,
    this.dark,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: backgroundColor ?? Colors.green,
      child: SafeArea(
        child: Column(
          children: [
            Container(
              color: backgroundColor ?? Colors.green,
              height: MediaQuery.of(context).padding.top,
            ),
            Expanded(child: child ?? Container()),
          ],
        ),
      ),
    );
  }
}


class CustomBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.green,
      // Add your content here
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text('Custom Bar Example'),
      ),
      body: Center(
        child: CustomBar(),
      ),
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