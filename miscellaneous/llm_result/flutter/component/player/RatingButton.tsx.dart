
class RatingButton extends StatelessWidget {
  final int? rating;
  final bool? number;

  const RatingButton({Key? key, this.rating, this.number}) : super(key: key);

  Color getColour(int? rating) {
    // Implement your color logic based on the rating
    if (rating == null) return Colors.grey;
    if (rating >= 4) return Colors.green;
    if (rating >= 2) return Colors.yellow;
    return Colors.red;
  }

  @override
  Widget build(BuildContext context) {
    if (number == true) {
      return ElevatedButton(
        style: ElevatedButton.styleFrom(
          primary: getColour(rating),
        ),
        onPressed: () {},
        child: Text(rating?.toString() ?? '0'),
      );
    }
    return Container();
  }
}


class RatingButton extends StatelessWidget {
  final double? rating;

  RatingButton({this.rating});

  Color getColour(double rating) {
    // Implement your logic to get color based on rating
    return Colors.blue; // Placeholder
  }

  String getComment(double rating) {
    // Implement your logic to get comment based on rating
    return 'Comment'; // Placeholder
  }

  void safeAction(String action) {
    // Implement your safe action logic here
  }

  @override
  Widget build(BuildContext context) {
    if (rating == null || rating == 0) {
      // return a placeholder button
      return ElevatedButton(
        style: ElevatedButton.styleFrom(
          primary: Colors.transparent,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(0),
          ),
        ),
        onPressed: () {},
        child: Container(),
      );
    }

    return ElevatedButton(
      style: ElevatedButton.styleFrom(
        primary: getColour(rating!),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(0),
        ),
      ),
      onPressed: () => safeAction('Rating'),
      child: Text(getComment(rating!)),
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