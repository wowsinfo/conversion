
class Space extends StatelessWidget {
  final double? height;

  Space({this.height});

  @override
  Widget build(BuildContext context) {
    // Default value is 128
    double h = height ?? 128.0;

    return Container(
      height: h,
    );
  }
}


class Space extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter App'),
        ),
        body: Space(),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}