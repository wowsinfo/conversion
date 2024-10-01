
class Friend extends StatefulWidget {
  @override
  _FriendState createState() => _FriendState();
}

class _FriendState extends State<Friend> {
  List<dynamic> player;
  List<dynamic> clan;
  double goodWidth;

  @override
  void initState() {
    super.initState();
    var all = AppGlobalData.get(LOCAL.friendList);

    player = getPlayer(all);
    clan = getClan(all);
    goodWidth = bestWidth(400);
  }

  List<dynamic> getPlayer(List<dynamic> all) {
    // Implement your logic to get player data
  }

  List<dynamic> getClan(List<dynamic> all) {
    // Implement your logic to get clan data
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(lang.friendTitle),
      ),
      body: ListView(
        children: [
          SectionTitle(title: lang.playerTitle),
          // Add your ListView.builder or other widgets to display player and clan data
        ],
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  double goodWidth = 0;

  double bestWidth(double maxWidth, double newWidth) {
    return newWidth < maxWidth ? newWidth : maxWidth;
  }

  void updateBestWidth(BoxConstraints constraints) {
    final newWidth = constraints.maxWidth;
    setState(() {
      goodWidth = bestWidth(400, newWidth);
    });
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        updateBestWidth(constraints);
        return Container(
          width: goodWidth,
          height: 100,
          color: Colors.blue,
        );
      },
    );
  }
}

  List<dynamic> player = [];
  all['player'].forEach((ID, value) {
    player.add(value);
  });
  return player;
}

  List<dynamic> clan = [];
  all['clan'].forEach((ID, value) {
    clan.add(value);
  });
  return clan;
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<Player> player = [];
  List<Clan> clan = [];
  double goodWidth = 0;

  void updateBestWidth() {
    // Implement your logic to update goodWidth
  }

  void pushToClan(Clan item) {
    // Implement your logic to navigate to clan
  }

  void pushToPlayer(Player item) {
    // Implement your logic to navigate to player
  }

  void removeClan(Clan item) {
    // Implement your logic to remove clan
  }

  void removeFriend(Player item) {
    // Implement your logic to remove friend
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        goodWidth = constraints.maxWidth; // Update goodWidth based on layout
        return SingleChildScrollView(
          child: Column(
            children: [
              SectionTitle(title: 'Friend Clan - ${clan.length}'),
              Wrap(
                children: clan.map((item) {
                  return Container(
                    width: goodWidth,
                    child: ListTile(
                      title: Text(item.tag),
                      subtitle: Text('${item.clanId}'),
                      onTap: () => pushToClan(item),
                      trailing: IconButton(
                        icon: Icon(Icons.close, color: Colors.grey[500]),
                        onPressed: () => removeClan(item),
                      ),
                    ),
                  );
                }).toList(),
              ),
              SectionTitle(title: 'Friend Player - ${player.length}'),
              Wrap(
                children: player.map((item) {
                  return Container(
                    width: goodWidth,
                    child: ListTile(
                      title: Text(item.nickname),
                      subtitle: Text('${item.accountId}'),
                      onTap: () => pushToPlayer(item),
                      trailing: IconButton(
                        icon: Icon(Icons.close, color: Colors.grey[500]),
                        onPressed: () => removeFriend(item),
                      ),
                    ),
                  );
                }).toList(),
              ),
            ],
          ),
        );
      },
    );
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Text(
        title,
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
    );
  }
}

class Player {
  final String nickname;
  final String accountId;

  Player({required this.nickname, required this.accountId});
}

class Clan {
  final String tag;
  final String clanId;

  Clan({required this.tag, required this.clanId});
}


class FriendManager extends StatefulWidget {
  @override
  _FriendManagerState createState() => _FriendManagerState();
}

class _FriendManagerState extends State<FriendManager> {
  List<String> playerList = [];

  @override
  void initState() {
    super.initState();
    _loadFriendList();
  }

  Future<void> _loadFriendList() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      playerList = prefs.getStringList('friendList') ?? [];
    });
  }

  Future<void> removeFriend(String accountId) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    List<String> updatedList = playerList.where((player) => player != accountId).toList();
    await prefs.setStringList('friendList', updatedList);
    setState(() {
      playerList = updatedList;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Friend List'),
      ),
      body: ListView.builder(
        itemCount: playerList.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(playerList[index]),
            trailing: IconButton(
              icon: Icon(Icons.delete),
              onPressed: () => removeFriend(playerList[index]),
            ),
          );
        },
      ),
    );
  }
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Map<String, dynamic> friendList = {};
  Map<String, dynamic> clan = {};

  @override
  void initState() {
    super.initState();
    _loadFriendList();
  }

  Future<void> _loadFriendList() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? str = prefs.getString('friendList');
    if (str != null) {
      friendList = Map<String, dynamic>.from(json.decode(str));
      setState(() {
        clan = _getClan(friendList);
      });
    }
  }

  Map<String, dynamic> _getClan(Map<String, dynamic> data) {
    return data['clan'] ?? {};
  }

  Future<void> removeClan(String clanId) async {
    friendList['clan'].remove(clanId);
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('friendList', json.encode(friendList));
    setState(() {
      clan = _getClan(friendList);
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Friend List'),
        ),
        body: Center(
          child: Text('Clan: ${clan.toString()}'),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}

  SafeAction('Statistics', info: info);
}

void SafeAction(String action, {Map<String, dynamic>? info}) {
  // Implement the action logic here
}

  SafeAction('ClanInfo', {'info': info});
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
          height: double.infinity,
          // Add your content here
        ),
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Wrap(
        direction: Axis.horizontal,
        alignment: WrapAlignment.start,
        children: [
          // Add your widgets here
        ],
      ),
    );
  }
}


class Friend extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Friend'),
      ),
      body: Center(
        child: Text('Friend Component'),
      ),
    );
  }
}