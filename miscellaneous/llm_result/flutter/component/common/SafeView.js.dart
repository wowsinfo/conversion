
class SafeView extends StatelessWidget {
  final ThemeData? theme;
  final BoxDecoration? decoration;
  final Widget? child;

  const SafeView({Key? key, this.theme, this.decoration, this.child}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: decoration ?? BoxDecoration(),
      child: SafeArea(
        child: child ?? Container(),
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
        body: Container(
          color: Colors.white,
          child: Center(
            child: Text('Hello, Flutter!'),
          ),
        ),
      ),
    );
  }
}


class SafeView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Container(
          // Add your content here
        ),
      ),
    );
  }
}