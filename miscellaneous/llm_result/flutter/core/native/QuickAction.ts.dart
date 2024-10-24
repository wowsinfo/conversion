
class QuickAction {
  static const MethodChannel _channel = MethodChannel('quick_action_manager');
  static const EventChannel _eventChannel = EventChannel('quick_action_event_emitter');
  static late Stream<String> _quickActionStream;

  QuickAction() {
    _quickActionStream = _eventChannel.receiveBroadcastStream().map((event) => event as String);
    _quickActionStream.listen((type) {
      print('quick action - $type');
      switch (type) {
        case 'search':
          SafeAction('Search');
          break;
        case 'warships':
          SafeAction('Warship');
          break;
        case 'account':
          SafeAction('Statistics');
          break;
        default:
          print('Unknown action - $type');
      }
    });
  }

  void SafeAction(String action) {
    // Implement your navigation or action handling logic here
    print('Navigating to $action');
  }
}

  void addMainAccount(String name) {
    // Implementation for adding a main account
  }
}

class AccountService {
  static final AccountManager manager = AccountManager();

  static void addMainAccount(String name) {
    manager.addMainAccount(name);
  }
}


class ShortcutManager {
  void performPendingShortcut() {
    // Implement the logic to perform the pending shortcut
  }
}

class MyApp extends StatelessWidget {
  final ShortcutManager manager = ShortcutManager();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Shortcut Example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              manager.performPendingShortcut();
            },
            child: Text('Perform Pending Shortcut'),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


class QuickAction extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Quick Action'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // Implement your action here
          },
          child: Text('Perform Quick Action'),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: QuickAction(),
  ));
}