
class SectionTitle extends StatelessWidget {
  final String title;
  final bool? back;
  final bool? center;
  final TextStyle? style;
  final bool? bold;

  const SectionTitle({
    Key? key,
    required this.title,
    this.back,
    this.center,
    this.style,
    this.bold,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Container(
      color: back == true ? Theme.of(context).colorScheme.secondary : null,
      alignment: center == true ? Alignment.center : null,
      padding: const EdgeInsets.only(left: 16, top: 8),
      child: Text(
        title,
        style: TextStyle(
          color: theme.primaryColor,
          fontSize: bold == true ? 32 : null,
          fontWeight: bold == true ? FontWeight.bold : null,
        ).merge(style),
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