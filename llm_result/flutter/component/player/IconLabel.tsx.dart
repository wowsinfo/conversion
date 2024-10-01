
class IconLabel extends StatelessWidget {
  final dynamic info;
  final IconData icon;
  final EdgeInsetsGeometry? padding;
  final VoidCallback? onPressed;

  const IconLabel({
    Key? key,
    required this.info,
    required this.icon,
    this.padding,
    this.onPressed,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Container(
      padding: padding ?? EdgeInsets.all(4),
      alignment: Alignment.center,
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          IconButton(
            icon: Icon(icon, color: theme.primaryColor, size: 36),
            onPressed: onPressed,
          ),
          Text(
            info.toString(),
            style: TextStyle(color: theme.textTheme.bodyText1?.color),
          ),
        ],
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text(
      'Your Text Here',
      style: TextStyle(
        fontSize: 14,
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