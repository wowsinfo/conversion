
class Search extends StatefulWidget {
  @override
  _SearchState createState() => _SearchState();
}

class _SearchState extends State<Search> {
  String search = '';
  String server = '';
  Map<String, dynamic> result = {'player': [], 'clan': []};
  String online = '???';
  bool showFriend = true;
  double goodWidth = 400;

  @override
  void initState() {
    super.initState();
    setLastLocation('Search');
    getPlayerOnline();
  }

  Future<void> getPlayerOnline() async {
    String domain = getCurrDomain();
    final response = await http.get(Uri.parse(WoWsAPI.PlayerOnline + domain));
    if (response.statusCode == 200) {
      var data = json.decode(response.body);
      String onlinePlayers = Guard(data, 'data.wows.0.players_online', '???');
      setState(() {
        online = onlinePlayers;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Search'),
      ),
      body: KeyboardAvoidingView(
        behavior: ScrollView.keyboardDismissBehavior,
        child: SingleChildScrollView(
          child: Column(
            children: [
              SearchBar(
                onChanged: (value) {
                  setState(() {
                    search = value;
                  });
                },
              ),
              SectionTitle(title: 'Online Players: $online'),
              // Add PlayerCell and Friend widgets here
            ],
          ),
        ),
      ),
    );
  }
}

class SearchBar extends StatelessWidget {
  final Function(String) onChanged;

  SearchBar({required this.onChanged});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: TextField(
        onChanged: onChanged,
        decoration: InputDecoration(
          labelText: 'Search',
          border: OutlineInputBorder(),
        ),
      ),
    );
  }
}

void setLastLocation(String location) {
  // Implement your logic to set the last location
}

String getCurrDomain() {
  // Implement your logic to get the current domain
  return 'example.com';
}

String getCurrPrefix() {
  // Implement your logic to get the current prefix
  return 'na';
}

String Guard(Map<String, dynamic> data, String path, String defaultValue) {
  // Implement your logic to safely access the data
  return data.containsKey(path) ? data[path] : defaultValue;
}

class WoWsAPI {
  static String PlayerOnline = 'https://api.example.com/player/online?domain=';
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  double goodWidth = 0;

  double bestWidth(double maxWidth, double newWidth) {
    return newWidth > maxWidth ? maxWidth : newWidth;
  }

  void updateWidth(BuildContext context) {
    final RenderBox renderBox = context.findRenderObject() as RenderBox;
    final newWidth = renderBox.size.width;
    setState(() {
      goodWidth = bestWidth(400, newWidth);
    });
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        WidgetsBinding.instance.addPostFrameCallback((_) => updateWidth(context));
        return Container(
          width: goodWidth,
          color: Colors.blue,
          child: Center(child: Text('Width: $goodWidth')),
        );
      },
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  String search = '';
  String online = 'Online'; // Example value
  final TextEditingController _searchController = TextEditingController();

  void searchAll(String value) {
    setState(() {
      search = value;
    });
  }

  Widget renderContent() {
    // Implement your content rendering logic here
    return Container(); // Placeholder for actual content
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Menu Footer'), // Replace with lang.menu_footer
      ),
      body: KeyboardAvoidingView(
        behavior: Platform.isIOS ? KeyboardAvoidingViewBehavior.padding : null,
        child: Column(
          children: [
            TextField(
              controller: _searchController,
              onChanged: searchAll,
              decoration: InputDecoration(
                hintText: '${search.toUpperCase()} - $online Search Player Online', // Replace with lang.search_player_online
              ),
              textInputAction: TextInputAction.search,
              autocorrect: false,
              textCapitalization: TextCapitalization.none,
            ),
            Expanded(
              child: SingleChildScrollView(
                keyboardDismissBehavior: ScrollViewKeyboardDismissBehavior.onDrag,
                child: renderContent(),
              ),
            ),
          ],
        ),
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  String search = '';
  Result result = Result(); // Assume Result is a defined class
  bool showFriend = false;

  @override
  Widget build(BuildContext context) {
    return renderContent();
  }

  Widget renderContent() {
    if (showFriend && search.length < 2) {
      return Friend(); // Assume Friend is a defined widget
    } else {
      int playerLen = result.player.length;
      int clanLen = result.clan.length;
      return Column(
        children: [
          SectionTitle(title: '${lang.menu_search_clan} - $clanLen'), // Assume lang is defined
          renderClan(result.clan),
          SectionTitle(title: '${lang.menu_search_player} - $playerLen'),
          renderPlayer(result.player),
        ],
      );
    }
  }

  Widget renderClan(List<Clan> clans) { // Assume Clan is a defined class
    return ListView.builder(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      itemCount: clans.length,
      itemBuilder: (context, index) {
        return ClanWidget(clan: clans[index]); // Assume ClanWidget is a defined widget
      },
    );
  }

  Widget renderPlayer(List<Player> players) { // Assume Player is a defined class
    return ListView.builder(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      itemCount: players.length,
      itemBuilder: (context, index) {
        return PlayerWidget(player: players[index]); // Assume PlayerWidget is a defined widget
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


class ClanWidget extends StatelessWidget {
  final List<dynamic> clan;
  final double goodWidth;

  ClanWidget({required this.clan, required this.goodWidth});

  @override
  Widget build(BuildContext context) {
    if (clan.isNotEmpty) {
      return Container(
        width: double.infinity,
        child: Wrap(
          children: clan.map((item) {
            return PlayerCell(
              key: ValueKey(item['clan_id']),
              item: item,
              clan: true,
              width: goodWidth,
            );
          }).toList(),
        ),
      );
    }

    return SizedBox.shrink();
  }
}

class PlayerCell extends StatelessWidget {
  final dynamic item;
  final bool clan;
  final double width;

  PlayerCell({Key? key, required this.item, required this.clan, required this.width}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your PlayerCell UI here
    return Container(
      width: width,
      // Add additional UI elements based on item data
    );
  }
}


class PlayerList extends StatelessWidget {
  final List<dynamic> player;
  final double goodWidth;

  PlayerList({required this.player, required this.goodWidth});

  @override
  Widget build(BuildContext context) {
    if (player.isNotEmpty) {
      return Container(
        width: goodWidth,
        child: Column(
          children: player.map((item) {
            return PlayerCell(
              key: ValueKey(item['account_id']),
              item: item,
              player: true,
              width: goodWidth,
            );
          }).toList(),
        ),
      );
    }

    return SizedBox.shrink();
  }
}

class PlayerCell extends StatelessWidget {
  final dynamic item;
  final bool player;
  final double width;

  PlayerCell({Key? key, required this.item, required this.player, required this.width}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your PlayerCell UI here
    return Container(
      width: width,
      // Add your content here
    );
  }
}


class SearchWidget extends StatefulWidget {
  @override
  _SearchWidgetState createState() => _SearchWidgetState();
}

class _SearchWidgetState extends State<SearchWidget> {
  String search = '';
  Map<String, List<dynamic>> result = {'player': [], 'clan': []};
  Timer? delayedRequest;

  void searchAll(String text) {
    // Reset search
    if (text.length < 2) {
      setState(() {
        result = {'player': [], 'clan': []};
      });
    }
    setState(() {
      search = text;
    });

    // Clear timeout every time for efficient data request
    delayedRequest?.cancel();
    delayedRequest = Timer(Duration(milliseconds: 500), () async {
      String domain = getCurrDomain();
      Map<String, List<dynamic>> all = {'player': [], 'clan': []};
      int length = text.length;

      if (length > 1 && length < 6) {
        // For clan, only 2 - 5
        var clanData = await SafeFetch.get(WoWsAPI.ClanSearch, domain, text);
        var data = Guard(clanData, 'data', null);
        if (data != null) {
          data.forEach((v) => v['server'] = getCurrServer());
          all['clan'] = data;
          setState(() {
            result = all;
          });
        }
      }

      if (length > 2) {
        // For player, 3+
        var playerData = await SafeFetch.get(WoWsAPI.PlayerSearch, domain, text);
        var data = Guard(playerData, 'data', null);
        if (data != null) {
          data.forEach((v) => v['server'] = getCurrServer());
          all['player'] = data;
          setState(() {
            result = all;
          });
        }
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return TextField(
      onChanged: searchAll,
      decoration: InputDecoration(
        hintText: 'Search player or clan',
      ),
    );
  }
}

// Placeholder functions for the missing parts
String getCurrDomain() {
  // Implement your logic to get the current domain
  return 'example.com';
}

String getCurrServer() {
  // Implement your logic to get the current server
  return 'server';
}

class SafeFetch {
  static Future<dynamic> get(String api, String domain, String text) async {
    // Implement your logic to fetch data from the API
    return {};
  }
}

class WoWsAPI {
  static const String ClanSearch = 'clan_search_api';
  static const String PlayerSearch = 'player_search_api';
}

dynamic Guard(dynamic result, String key, dynamic defaultValue) {
  // Implement your logic to guard the data
  return result[key] ?? defaultValue;
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
          flex: 1,
          alignment: Alignment.center,
          child: Center(
            child: Text('Hello, Flutter!'),
          ),
        ),
      ),
    );
  }
}


class SearchBarExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // Your main content goes here
          Positioned(
            top: 16,
            left: 16,
            right: 16,
            child: Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(100),
                boxShadow: [
                  BoxShadow(
                    color: Colors.black26,
                    blurRadius: 8.0,
                    offset: Offset(0, 2),
                  ),
                ],
              ),
              child: TextField(
                decoration: InputDecoration(
                  border: InputBorder.none,
                  hintText: 'Search...',
                  contentPadding: EdgeInsets.symmetric(horizontal: 20, vertical: 15),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: SearchBarExample(),
  ));
}


class MyScrollWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        padding: EdgeInsets.only(top: 64),
        child: Column(
          children: <Widget>[
            // Add your content here
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MyScrollWidget(),
  ));
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


class Search extends StatefulWidget {
  @override
  _SearchState createState() => _SearchState();
}

class _SearchState extends State<Search> {
  final TextEditingController _controller = TextEditingController();
  String _searchText = '';

  void _onSearchChanged(String text) {
    setState(() {
      _searchText = text;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Search'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _controller,
              onChanged: _onSearchChanged,
              decoration: InputDecoration(
                labelText: 'Search',
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 20),
            Text('You searched for: $_searchText'),
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Search(),
  ));
}