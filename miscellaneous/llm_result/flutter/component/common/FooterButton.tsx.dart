
class FooterButton extends StatelessWidget {
  final String icon;
  final bool left;

  const FooterButton({Key? key, required this.icon, required this.left}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    String al = '';
    if (icon == 'cog') {
      al = 'Settings'; // Replace with actual localization logic
    }

    return IconButton(
      icon: Icon(Icons.settings), // Replace with actual icon based on `icon` variable
      onPressed: () {
        // Implement navigation logic here
        // For example: Navigator.pushNamed(context, '/settings');
      },
      tooltip: al,
    );
  }
}

    al = lang.button_back_label;
}

    al = lang.button_home_label;
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Button Menu'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              // Add your button action here
            },
            child: Text(lang.button_menu_label),
          ),
        ),
      ),
    );
  }
}

class Lang {
  String button_menu_label = 'Menu';
}

final lang = Lang();

void main() {
  runApp(MyApp());
}


class MyWidget extends StatelessWidget {
  final String icon;

  MyWidget({required this.icon});

  void pressEvent(BuildContext context) {
    if (icon == 'cog') {
      SafeAction('Settings');
    } else if (icon == 'arrow-left') {
      Navigator.of(context).pop();
      if (Navigator.of(context).canPop()) {
        Future.delayed(Duration(seconds: 1), () {
          Navigator.of(context).pushReplacementNamed(Navigator.of(context).currentRoute!.settings.name!);
        });
      }
    } else if (icon == 'home') {
      Navigator.of(context).popUntil((route) => route.settings.name == 'Menu');
      setLastLocation('');
      Future.delayed(Duration(seconds: 1), () {
        Navigator.of(context).pushReplacementNamed(Navigator.of(context).currentRoute!.settings.name!);
      });
    } else {
      SafeAction('Search');
    }
  }

  void SafeAction(String action) {
    // Implement SafeAction logic here
  }

  void setLastLocation(String location) {
    // Implement setLastLocation logic here
  }

  @override
  Widget build(BuildContext context) {
    return IconButton(
      icon: Icon(Icons.settings), // Replace with appropriate icon based on `icon` variable
      onPressed: () => pressEvent(context),
    );
  }
}


class CustomButton extends StatelessWidget {
  final bool left;
  final IconData icon;
  final String al;
  final VoidCallback pressEvent;

  const CustomButton({
    Key? key,
    required this.left,
    required this.icon,
    required this.al,
    required this.pressEvent,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: left ? 8 : null,
      right: left ? null : 8,
      child: SafeArea(
        child: GestureDetector(
          onTap: pressEvent,
          child: Container(
            height: 48,
            width: 48,
            decoration: BoxDecoration(
              color: Colors.grey[500],
              shape: BoxShape.circle,
            ),
            child: Icon(
              icon,
              color: Colors.white,
            ),
          ),
        ),
      ),
    );
  }
}