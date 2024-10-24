
class FloatingButton extends StatefulWidget {
  @override
  _FloatingButtonState createState() => _FloatingButtonState();
}

class _FloatingButtonState extends State<FloatingButton> {
  bool menu = false;
  String icon = 'Ship';

  @override
  void initState() {
    super.initState();
    checkMenu();
  }

  void checkMenu() {
    final hasMenu = RouterState.routes.indexWhere((r) => r.routeName == 'Menu');
    setState(() {
      menu = hasMenu > 0;
      icon = hasMenu > 0 ? 'home' : 'Ship';
    });
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Stack(
        children: [
          Positioned(
            bottom: 16,
            right: 16,
            child: FloatingActionButton(
              onPressed: () {
                // Add your navigation logic here
              },
              child: Icon(menu ? Icons.home : Icons.ship),
            ),
          ),
        ],
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  final bool menu;

  MyWidget({required this.menu});

  void navigate(BuildContext context) {
    if (menu) {
      Navigator.of(context).popUntil((route) => route.settings.name == 'Menu');
    } else if (ModalRoute.of(context)?.settings.name != 'Menu') {
      Navigator.of(context).pushNamed('Menu');
    }
  }

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () => navigate(context),
      child: Text('Navigate'),
    );
  }
}


class MyWidget extends StatelessWidget {
  final IconData icon;
  final VoidCallback navigate;

  MyWidget({required this.icon, required this.navigate});

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Stack(
        children: [
          Positioned(
            right: 16,
            bottom: 16,
            child: FloatingActionButton(
              onPressed: navigate,
              child: Icon(icon),
            ),
          ),
        ],
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
        title: Text('My App'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}