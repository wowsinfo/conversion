
class SimilarGraph extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Similar Ships Average Stats'),
      ),
      body: Scrollbar(
        child: SingleChildScrollView(
          child: Column(
            children: [
              // Replace with your widget to display similar ships' average stats
              WoWsInfo(),
            ],
          ),
        ),
      ),
    );
  }
}

class WoWsInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16.0),
      child: Text(
        'Display similar ships\' average stats here.',
        style: TextStyle(fontSize: 18),
      ),
    );
  }
}


class WoWsInfo extends StatelessWidget {
  final Widget info;

  WoWsInfo({required this.info});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          padding: EdgeInsets.all(16.0),
          child: info,
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
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter App'),
        ),
        body: SingleChildScrollView(
          padding: EdgeInsets.all(8.0),
          child: Column(
            children: [
              // Add your content here
            ],
          ),
        ),
      ),
    );
  }
}


class SimilarGraph extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Similar Graph'),
      ),
      body: Center(
        child: Text('This is the Similar Graph widget'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: SimilarGraph(),
  ));
}