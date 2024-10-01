
void main() {
  testWidgets('wows info renders correctly', (WidgetTester tester) async {
    await tester.pumpWidget(MyApp());
  });
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Wows Info'),
        ),
        body: Center(
          child: Text('Hello, World!'),
        ),
      ),
    );
  }
}