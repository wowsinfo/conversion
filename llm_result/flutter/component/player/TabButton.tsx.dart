
class TabButton extends StatelessWidget {
  final IconData icon;
  final bool? disabled;
  final VoidCallback? onPress;

  const TabButton({
    Key? key,
    required this.icon,
    this.disabled,
    this.onPress,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return GestureDetector(
      onTap: disabled == true ? null : onPress,
      child: Container(
        alignment: Alignment.center,
        padding: const EdgeInsets.all(4.0),
        child: Icon(
          icon,
          size: 26,
          color: theme.primaryColor,
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