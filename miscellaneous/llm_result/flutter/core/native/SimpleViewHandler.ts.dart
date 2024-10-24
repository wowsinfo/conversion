
class SimpleViewHandler {
  static Future<void> openURL(String url) async {
    if (Theme.of(context).platform == TargetPlatform.iOS) {
      final success = await _showSafariViewController(url);
      print('SimpleViewHandler.openURL $success');
      if (success) {
        return;
      }
    }

    _openExternalURL(url);
  }

  static Future<bool> _showSafariViewController(String url) async {
    // Implement Safari View Controller logic for iOS
    // This is a placeholder for actual implementation
    return await launch(url);
  }

  static void _openExternalURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Open External URL Example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              openExternalURL('https://example.com');
            },
            child: Text('Open URL'),
          ),
        ),
      ),
    );
  }

  static Future<void> openExternalURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}

void main() {
  runApp(MyApp());
}


abstract class SimpleViewHandlerInterface {
  Future<bool> showSafariViewController(String url);
}

class SimpleViewHandler implements SimpleViewHandlerInterface {
  @override
  Future<bool> showSafariViewController(String url) async {
    if (await canLaunch(url)) {
      await launch(url, forceSafariVC: true);
      return true;
    } else {
      return false;
    }
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Safari View Controller Example')),
        body: Center(
          child: ElevatedButton(
            onPressed: () async {
              SimpleViewHandler handler = SimpleViewHandler();
              bool success = await handler.showSafariViewController('https://www.example.com');
              if (success) {
                print('Launched successfully');
              } else {
                print('Could not launch');
              }
            },
            child: Text('Open URL'),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}