
class WarshipLabel extends StatelessWidget {
  final TextStyle? style;
  final Map<String, dynamic>? item;

  const WarshipLabel({Key? key, this.style, this.item}) : super(key: key);

  String getTierLabel(int tier) {
    // Implement your tier label logic here
    return 'Tier $tier'; // Placeholder implementation
  }

  @override
  Widget build(BuildContext context) {
    if (item != null) {
      final tier = item!['tier'];
      final name = item!['name'];
      final premium = item!['premium'] ?? false;

      return Text(
        '${getTierLabel(tier)} $name',
        style: style?.copyWith(color: premium ? Colors.orange : null) ?? 
               TextStyle(color: premium ? Colors.orange : null),
        maxLines: 1,
        overflow: TextOverflow.ellipsis,
      );
    }
    return SizedBox.shrink();
  }
}


class MyWidget extends StatelessWidget {
  final TextStyle style;
  final TextStyle label;
  final String langWarshipUnknown;

  MyWidget({required this.style, required this.label, required this.langWarshipUnknown});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Text(
        langWarshipUnknown,
        style: style.merge(label),
        maxLines: 1,
        overflow: TextOverflow.ellipsis,
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        'Label',
        textAlign: TextAlign.center,
        style: TextStyle(
          fontSize: 16.0,
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text('Flutter App'),
      ),
      body: MyWidget(),
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
        title: Text('My App'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}