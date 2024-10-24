
class RS extends StatefulWidget {
  @override
  _RSState createState() => _RSState();
}

class _RSState extends State<RS> {
  String ip;
  dynamic rs;
  bool valid = false;
  bool info = false;
  bool loading = true;
  String battleTime = '';
  List<dynamic> allay = [];
  Map<String, dynamic> allayInfo = {};
  List<dynamic> enemy = [];
  Map<String, dynamic> enemyInfo = {};

  @override
  void initState() {
    super.initState();
    setLastLocation('RS');
    ip = AppGlobalData.get(LOCAL.rsIP); // load saved ip
    // Load player info or any other initialization
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('RS'),
      ),
      body: KeyboardAvoidingView(
        behavior: isAndroid ? null : ScrollView.keyboardDismissBehavior,
        child: loading
            ? LoadingIndicator()
            : SingleChildScrollView(
                child: Column(
                  children: [
                    // Add your UI components here
                    TextField(
                      decoration: InputDecoration(labelText: 'IP Address'),
                      onChanged: (value) {
                        setState(() {
                          ip = value;
                        });
                      },
                    ),
                    ElevatedButton(
                      onPressed: () {
                        // Implement your button action here
                      },
                      child: Text('Submit'),
                    ),
                    // Display ally and enemy information
                    // Example: ListView.builder for allies
                    ListView.builder(
                      itemCount: allay.length,
                      itemBuilder: (context, index) {
                        return WarshipCell(allay[index]);
                      },
                    ),
                    // Example: ListView.builder for enemies
                    ListView.builder(
                      itemCount: enemy.length,
                      itemBuilder: (context, index) {
                        return WarshipCell(enemy[index]);
                      },
                    ),
                  ],
                ),
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
  String ip = '';

  @override
  void initState() {
    super.initState();
    KeepAwake.enable();
    // Enter rs mode when there is a valid ip
    if (ip.isNotEmpty) {
      validIP(ip);
    }
  }

  void validIP(String ip) {
    // Implement your validIP logic here
  }

  @override
  void dispose() {
    KeepAwake.disable();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class MyWidget extends StatefulWidget {
  final ThemeData theme;

  MyWidget({required this.theme});

  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  @override
  void dispose() {
    KeepAwake.deactivate();
    // reset the theme colour back
    widget.theme.copyWith(primaryColor: TintColour()[500]);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}

Map<int, Color> TintColour() {
  return {
    500: Colors.blue, // Replace with your actual color logic
  };
}


class WoWsInfoWidget extends StatefulWidget {
  @override
  _WoWsInfoWidgetState createState() => _WoWsInfoWidgetState();
}

class _WoWsInfoWidgetState extends State<WoWsInfoWidget> {
  String ip = '';
  bool rs = false;
  bool valid = false;

  void validIP(String ip) {
    // Implement your IP validation logic here
    setState(() {
      valid = RegExp(r'^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$').hasMatch(ip);
    });
  }

  void openURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  Widget renderPlayer() {
    // Implement your player rendering logic here
    return Container(); // Placeholder for player rendering
  }

  Widget renderMapInfo(bool rs) {
    // Implement your map info rendering logic here
    return Container(); // Placeholder for map info rendering
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: rs ? () => setState(() => valid = true) : null,
      child: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text("Map Information", style: TextStyle(fontSize: 24)),
            if (!valid) ...[
              Padding(
                padding: const EdgeInsets.only(top: 16.0),
                child: TextField(
                  decoration: InputDecoration(
                    hintText: '192.168.1.x',
                    border: OutlineInputBorder(),
                  ),
                  keyboardType: TextInputType.numberWithOptions(decimal: true),
                  onChanged: (t) => setState(() => ip = t),
                  onSubmitted: (value) => validIP(ip),
                ),
              ),
              ElevatedButton(
                onPressed: () => openURL('https://github.com/wowsinfo/WoWs-RS/releases/latest'),
                child: Text('Download Beta'),
              ),
            ] else ...[
              renderPlayer(),
            ],
            renderMapInfo(rs),
          ],
        ),
      ),
    );
  }
}


class PlayerScreen extends StatefulWidget {
  @override
  _PlayerScreenState createState() => _PlayerScreenState();
}

class _PlayerScreenState extends State<PlayerScreen> {
  bool loading = true;
  List<Player> allay = [];
  List<Player> enemy = [];

  @override
  void initState() {
    super.initState();
    // Load data here and set loading to false
  }

  double getOverallRating(List<Player> players) {
    // Implement your rating logic here
    return 0.0;
  }

  Widget renderPlayerCell(Player player) {
    // Implement your player cell rendering logic here
    return Container(); // Replace with actual implementation
  }

  @override
  Widget build(BuildContext context) {
    if (loading) {
      return Center(child: CircularProgressIndicator());
    }

    double allayRating = getOverallRating(allay);
    double enemyRating = getOverallRating(enemy);
    allay.sort((a, b) => b.ap.compareTo(a.ap));
    enemy.sort((a, b) => b.ap.compareTo(a.ap));

    return SingleChildScrollView(
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              RatingButton(rating: allayRating, number: true),
              Text('RS Beta', style: TextStyle(fontSize: 20)),
              RatingButton(rating: enemyRating, number: true),
            ],
          ),
          Row(
            children: [
              Expanded(
                child: StaggeredGridView.countBuilder(
                  crossAxisCount: 2,
                  itemCount: allay.length,
                  itemBuilder: (context, index) => renderPlayerCell(allay[index]),
                  staggeredTileBuilder: (index) => StaggeredTile.fit(1),
                  physics: NeverScrollableScrollPhysics(),
                  shrinkWrap: true,
                ),
              ),
              Expanded(
                child: StaggeredGridView.countBuilder(
                  crossAxisCount: 2,
                  itemCount: enemy.length,
                  itemBuilder: (context, index) => renderPlayerCell(enemy[index]),
                  staggeredTileBuilder: (index) => StaggeredTile.fit(1),
                  physics: NeverScrollableScrollPhysics(),
                  shrinkWrap: true,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}

class RatingButton extends StatelessWidget {
  final double rating;
  final bool number;

  RatingButton({required this.rating, required this.number});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(rating.toString(), style: TextStyle(fontSize: 16)),
        if (number) Text('Rating', style: TextStyle(fontSize: 12)),
      ],
    );
  }
}

class Player {
  final int account_id;
  final double ap;

  Player({required this.account_id, required this.ap});
}


class PlayerCell extends StatelessWidget {
  final Map<String, dynamic> info;

  PlayerCell({required this.info});

  @override
  Widget build(BuildContext context) {
    final playerNameStyle = TextStyle(/* Define your text style here */);
    final cellStyle = BoxDecoration(/* Define your box decoration here */);
    String pName = safeValue(info['nickname'], info['name']);

    // For pushing to player
    info['server'] = getCurrServer();

    return GestureDetector(
      onTap: info['pvp'] ? () => safeAction('PlayerShipDetail', {'data': info}) : null,
      onLongPress: info['account_id'] != null ? () => safeAction('Statistics', {'info': info}) : null,
      child: Container(
        decoration: cellStyle,
        child: Row(
          children: [
            WarshipCell(item: AppGlobalData.get(SAVED.warship)[info['ship_id']], scale: 1.4),
            Expanded(
              child: Text(
                pName,
                style: playerNameStyle,
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
              ),
            ),
            SimpleRating(info: info),
          ],
        ),
      ),
    );
  }

  String safeValue(String? nickname, String name) {
    return nickname ?? name;
  }

  String getCurrServer() {
    // Implement your logic to get the current server
  }

  void safeAction(String route, Map<String, dynamic> data) {
    // Implement your navigation logic here
  }
}

class WarshipCell extends StatelessWidget {
  final dynamic item;
  final double scale;

  WarshipCell({required this.item, required this.scale});

  @override
  Widget build(BuildContext context) {
    // Implement your WarshipCell UI here
  }
}

class SimpleRating extends StatelessWidget {
  final Map<String, dynamic> info;

  SimpleRating({required this.info});

  @override
  Widget build(BuildContext context) {
    // Implement your SimpleRating UI here
  }
}


class MapInfoDialog extends StatelessWidget {
  final Map<String, dynamic>? rs;
  final bool info;
  final Function() onDismiss;

  MapInfoDialog({required this.rs, required this.info, required this.onDismiss});

  @override
  Widget build(BuildContext context) {
    if (rs == null) {
      return Container();
    }

    final clientVersionFromExe = rs!['clientVersionFromExe'];
    final dateTime = rs!['dateTime'];
    final duration = rs!['duration'];
    final gameLogic = rs!['gameLogic'];
    final mapDisplayName = rs!['mapDisplayName'];
    final matchGroup = rs!['matchGroup'];
    final name = rs!['name'];
    final weatherParams = rs!['weatherParams'];

    String params = '';
    weatherParams.forEach((ID, value) {
      params += value.join(', ') + '\n';
    });

    return AlertDialog(
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      content: SingleChildScrollView(
        child: ListBody(
          children: [
            ListTile(
              title: Text("Client Version"),
              subtitle: Text(clientVersionFromExe),
            ),
            ListTile(
              title: Text("Time"),
              subtitle: Text(dateTime),
            ),
            ListTile(
              title: Text("Game Mode"),
              subtitle: Text("$matchGroup - $gameLogic - $name"),
            ),
            ListTile(
              title: Text("Map"),
              subtitle: Text(mapDisplayName),
            ),
            ListTile(
              title: Text("Duration"),
              subtitle: Text("${(duration / 60).toStringAsFixed(2)} min"),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 16.0),
              child: Text(params),
            ),
          ],
        ),
      ),
      actions: [
        TextButton(
          onPressed: onDismiss,
          child: Text("Dismiss"),
        ),
      ],
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool valid = false;
  late String ip;

  Future<void> validIP(String ip) async {
    String url = 'http://${ip.replaceAll('/', '')}:8605';
    try {
      // Only want to know if we can access it
      final response = await http.get(Uri.parse(url));
      if (response.statusCode == 200) {
        setState(() {
          valid = true;
        });
        // Update IP when it is valid
        final prefs = await SharedPreferences.getInstance();
        await prefs.setString('rsIP', ip);
        getArenaInfo(url);
        Future.delayed(Duration(seconds: 22), () => getArenaInfo(url));
      } else {
        _showErrorDialog(url);
      }
    } catch (e) {
      _showErrorDialog(url);
    }
  }

  void getArenaInfo(String url) {
    // Implement your logic to get arena info here
  }

  void _showErrorDialog(String url) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Error'),
          content: Text('$url is not valid'),
          actions: <Widget>[
            TextButton(
              child: Text('OK'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class ArenaInfo extends StatefulWidget {
  @override
  _ArenaInfoState createState() => _ArenaInfoState();
}

class _ArenaInfoState extends State<ArenaInfo> {
  List<dynamic> allay = [];
  List<dynamic> enemy = [];
  bool loading = false;
  String battleTime;
  dynamic rs;
  Timer interval;

  @override
  void initState() {
    super.initState();
    getArenaInfo('your_url_here');
  }

  Future<void> getArenaInfo(String url) async {
    try {
      final response = await http.get(Uri.parse(url));
      String text = response.body;

      if (text != '[]') {
        final data = jsonDecode(text);
        setState(() {
          rs = data;
        });

        if (data['dateTime'] != battleTime) {
          setState(() {
            loading = true;
            battleTime = data['dateTime'];
          });

          List<dynamic> vehicles = data['vehicles'];
          List<dynamic> allayList = [];
          List<dynamic> enemyList = [];

          for (var v in vehicles) {
            await Future.delayed(Duration(milliseconds: 300));
            var player = await appendExtraInfo(v);
            int team = player['relation'];

            if (team < 2) {
              allayList.add(player);
            } else {
              enemyList.add(player);
            }

            if (player['account_id'] == null) {
              player['account_id'] = Random().nextInt(88888888);
            }

            setState(() {
              allay = allayList;
              enemy = enemyList;
              loading = false;
            });
          }
        }
      }
    } catch (e) {
      if (interval != null) {
        interval.cancel();
      }
      setState(() {
        valid = false;
        rs = null;
      });
    }
  }

  Future<dynamic> appendExtraInfo(dynamic vehicle) async {
    // Implement your logic to append extra info here
    // This is a placeholder for the actual implementation
    return vehicle; // Replace with actual player data
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Arena Info')),
      body: loading
          ? Center(child: CircularProgressIndicator())
          : Column(
              children: [
                // Add your UI components here to display allay and enemy
              ],
            ),
    );
  }
}


class Player {
  String name;
  String shipId;
  String accountId;
  String nickname;
  dynamic pvp;

  Player({required this.name, required this.shipId});
}

class WoWsAPI {
  static const String playerSearch = 'your_player_search_endpoint';
  static const String oneShipInfo = 'your_one_ship_info_endpoint';
}

class SafeFetch {
  static Future<dynamic> get(String endpoint, String domain, String name, [String? accountId]) async {
    final response = await http.get(Uri.parse('$domain/$endpoint/$name${accountId != null ? '/$accountId' : ''}'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }
}

class Guard {
  static dynamic call(dynamic data, String path, dynamic defaultValue) {
    var keys = path.split('.');
    for (var key in keys) {
      if (data is Map<String, dynamic> && data.containsKey(key)) {
        data = data[key];
      } else {
        return defaultValue;
      }
    }
    return data;
  }
}

class PlayerService {
  final String domain;

  PlayerService(this.domain);

  Future<Player> appendExtraInfo(Player player) async {
    if (player.name.startsWith(':')) {
      return player;
    }
    var idInfo = await SafeFetch.get(WoWsAPI.playerSearch, domain, player.name);
    var playerID = Guard.call(idInfo, 'data.0', null);
    if (playerID != null) {
      player.shipId = player.shipId; // Assuming shipId is already set
      player.accountId = playerID['account_id'];
      player.nickname = playerID['nickname'];

      // Get player ship info
      var shipInfo = await SafeFetch.get(WoWsAPI.oneShipInfo, domain, player.shipId, player.accountId);
      var pvp = Guard.call(shipInfo, 'data.${player.accountId}.0.pvp', null);
      if (pvp != null) {
        player.pvp = pvp;
      }
    }
    return player;
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


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      margin: EdgeInsets.only(bottom: 8),
      // Add your content here
    );
  }
}


class HorizontalLayout extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          // Add your widgets here
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Horizontal Layout')),
      body: HorizontalLayout(),
    ),
  ));
}


class PlayerNameWidget extends StatelessWidget {
  final String playerName;

  PlayerNameWidget({required this.playerName});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        playerName,
        style: TextStyle(
          fontWeight: FontWeight.w300,
          fontSize: 17,
        ),
        textAlign: TextAlign.center,
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(4),
      child: Column(
        children: <Widget>[
          // Add your content here
        ],
      ),
    );
  }
}


class RS extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        // Define your theme here
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('RS'),
        ),
        body: Center(
          child: Text('Hello, Flutter!'),
        ),
      ),
    );
  }
}

void main() {
  runApp(RS());
}