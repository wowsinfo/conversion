
class Statistics extends StatefulWidget {
  final Map<String, dynamic> info;

  Statistics({Key? key, required this.info}) : super(key: key);

  @override
  _StatisticsState createState() => _StatisticsState();
}

class _StatisticsState extends State<Statistics> {
  late String name;
  late String id;
  late String server;
  bool valid = true;
  bool hidden = false;
  bool canBeMaster = true;
  bool canBeFriend = true;
  String clan = '';
  int currRank = 0;
  double rating = 0.0;
  bool achievement = false;
  bool rank = false;
  bool rankShip = false;
  bool ship = false;
  bool basic = false;
  bool graph = false;
  bool showMore = false;
  Color ratingColor = Color(0xFF607D8B);
  String? domain;
  String? prefix;

  @override
  void initState() {
    super.initState();
    setLastLocation('Statistics');
    String? accountId = Guard(widget.info, 'account_id', null);
    if (accountId != null && accountId.isNotEmpty) {
      final accountInfo = widget.info;
      name = accountInfo['nickname'];
      id = accountInfo['account_id'];
      server = accountInfo['server'];
      var friend = AppGlobalData.get(LOCAL.friendList);
      var master = AppGlobalData.get(LOCAL.userInfo);
      canBeMaster = master['account_id'] != accountId;
      canBeFriend = friend['player'][accountId] == null;

      domain = getDomain(server);
      prefix = getPrefix(server);
      if (domain != null) {
        getBasic();
        getRank();
        getClan();
        getShip();
        getAchievement();
      } else {
        setState(() {
          valid = false;
        });
      }
    } else {
      setState(() {
        id = '';
        valid = false;
      });
    }
  }

  void getBasic() {
    // Implement your logic to fetch basic data
  }

  void getRank() {
    // Implement your logic to fetch rank data
  }

  void getClan() {
    // Implement your logic to fetch clan data
  }

  void getShip() {
    // Implement your logic to fetch ship data
  }

  void getAchievement() {
    // Implement your logic to fetch achievement data
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Statistics'),
      ),
      body: valid
          ? SingleChildScrollView(
              child: Column(
                children: [
                  // Add your widgets here
                  LoadingIndicator(),
                  WoWsInfo(),
                  FooterPlus(),
                  // Other components...
                ],
              ),
            )
          : Center(
              child: Text('Invalid account or data not found.'),
            ),
    );
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
    // reset the theme colour back
    widget.theme.copyWith(primaryColor: TintColour()[500]);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

Map<int, Color> TintColour() {
  return {
    500: Colors.blue, // Example color, replace with actual tint logic
  };
}


class PlayerInfo extends StatefulWidget {
  @override
  _PlayerInfoState createState() => _PlayerInfoState();
}

class _PlayerInfoState extends State<PlayerInfo> {
  String server;
  String id;
  bool hidden = false;
  bool valid = true;
  Map<String, dynamic> basic;

  @override
  void initState() {
    super.initState();
    getBasic();
  }

  Future<void> getBasic() async {
    final response = await SafeFetch.get(WoWsAPI.PlayerInfo, getDomain(server), id);
    final data = json.decode(response.body);

    // Check if account is hidden
    print(data);
    final hidden = Guard(data, 'meta.hidden', null);
    bool hiddenAccount = false;
    if (hidden != null) {
      // If hidden is not null, it is hidden
      hiddenAccount = true;
      setState(() {
        this.hidden = true;
      });
    }

    // Get player data here
    final player = Guard(data, 'data.$id', null);
    if (player == null) {
      // Invalid data
      setState(() {
        valid = false;
      });
    } else {
      final battle = Guard(player, 'statistics.pvp.battles', 0);
      // Treat zero battle account as hidden not for hidden accounts
      if (!hiddenAccount && battle == 0) {
        setState(() {
          hidden = true;
        });
      }
      setState(() {
        basic = player;
      });
    }
  }

  String getDomain(String server) {
    // Implement your domain logic here
    return 'https://api.example.com';
  }
}

class SafeFetch {
  static Future<http.Response> get(String url, String domain, String id) {
    return http.get(Uri.parse('$domain/$url/$id'));
  }
}

dynamic Guard(Map<String, dynamic> data, String path, dynamic defaultValue) {
  final keys = path.split('.');
  dynamic current = data;
  for (var key in keys) {
    if (current is Map<String, dynamic> && current.containsKey(key)) {
      current = current[key];
    } else {
      return defaultValue;
    }
  }
  return current;
}

class WoWsAPI {
  static const String PlayerInfo = 'playerinfo';
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  String id = 'your_id_here'; // Replace with actual ID
  String clan = '';

  @override
  void initState() {
    super.initState();
    getClan();
  }

  Future<void> getClan() async {
    final response = await http.get(Uri.parse('https://api.example.com/player/clan/$id')); // Replace with actual API URL

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      String tag = data['data'][id]['clan']['tag'] ?? '';
      if (tag.isNotEmpty) {
        setState(() {
          clan = tag;
        });
      }
    } else {
      throw Exception('Failed to load clan data');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Clan Info'),
      ),
      body: Center(
        child: Text('Clan Tag: $clan'),
      ),
    );
  }
}


class PlayerAchievement extends StatefulWidget {
  @override
  _PlayerAchievementState createState() => _PlayerAchievementState();
}

class _PlayerAchievementState extends State<PlayerAchievement> {
  String domain = 'your_domain_here'; // Replace with your domain
  String id = 'your_id_here'; // Replace with your player ID
  Map<String, dynamic>? achievement;

  @override
  void initState() {
    super.initState();
    getAchievement();
  }

  Future<void> getAchievement() async {
    final response = await http.get(Uri.parse('$domain/api/playerAchievement/$id'));

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      var achievementData = data['data'][id]['battle'];
      if (achievementData != null) {
        setState(() {
          achievement = achievementData;
        });
      }
    } else {
      throw Exception('Failed to load achievement');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Player Achievement'),
      ),
      body: achievement != null
          ? Center(child: Text('Achievement: ${achievement.toString()}'))
          : Center(child: CircularProgressIndicator()),
    );
  }
}


class PlayerRankInfo extends StatefulWidget {
  @override
  _PlayerRankInfoState createState() => _PlayerRankInfoState();
}

class _PlayerRankInfoState extends State<PlayerRankInfo> {
  String domain = 'your_domain_here';
  String id = 'your_player_id_here';
  int currRank = 0;
  Map<String, dynamic> rank = {};
  Map<String, dynamic> rankShip = {};

  @override
  void initState() {
    super.initState();
    getRank();
  }

  Future<void> getRank() async {
    await getRankInfo();
    await getRankShipInfo();
  }

  Future<void> getRankInfo() async {
    final response = await http.get(Uri.parse('$domain/api/rank_info/$id'));
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      var rankData = data['data'][id]['seasons'];
      if (rankData != null) {
        List<String> keys = rankData.keys.toList();
        if (keys.isNotEmpty) {
          String last = keys.last;
          int currentRank = rankData[last]['rank_info']['rank'] ?? 0;
          if (currentRank > 0) {
            setState(() {
              currRank = currentRank;
            });
          }
        }
        setState(() {
          rank = rankData;
        });
      }
    }
  }

  Future<void> getRankShipInfo() async {
    final response = await http.get(Uri.parse('$domain/api/rank_ship_info/$id'));
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      var ships = data['data'][id];
      if (ships != null) {
        Map<String, List<dynamic>> formatted = {};
        for (var ship in ships) {
          var seasons = ship['seasons'];
          String shipId = ship['ship_id'];
          for (var season in seasons.keys) {
            if (formatted[season] == null) {
              formatted[season] = [];
            }
            var curr = seasons[season];
            if (curr['rank_solo'] != null) {
              curr['pvp'] = curr['rank_solo'];
              curr.remove('rank_solo');
            } else if (curr['rank_div2'] != null) {
              curr['pvp'] = curr['rank_div2'];
              curr.remove('rank_div2');
            } else if (curr['rank_div3'] != null) {
              curr['pvp'] = curr['rank_div3'];
              curr.remove('rank_div3');
            } else {
              continue;
            }
            curr['ship_id'] = shipId;
            formatted[season].add(curr);
          }
        }
        setState(() {
          rankShip = formatted;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Player Rank Info'),
      ),
      body: Center(
        child: Text('Current Rank: $currRank'),
      ),
    );
  }
}


class ShipInfoProvider with ChangeNotifier {
  String domain;
  String id;
  Map<String, dynamic> ship;
  double rating;
  Color ratingColor;

  ShipInfoProvider(this.domain, this.id);

  Future<void> getShip() async {
    final response = await http.get(Uri.parse('$domain/shipInfo/$id'));

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      ship = data['data'][id];

      if (ship != null) {
        rating = getOverallRating(ship);
        ratingColor = getColour(rating);
        notifyListeners();
      }
    } else {
      throw Exception('Failed to load ship info');
    }
  }

  double getOverallRating(Map<String, dynamic> ship) {
    // Implement your logic to calculate overall rating
    return 0.0; // Placeholder
  }

  Color getColour(double rating) {
    // Implement your logic to determine color based on rating
    return Colors.white; // Placeholder
  }
}


class WoWsInfo extends StatelessWidget {
  final Widget child;
  final String title;
  final VoidCallback onPress;

  const WoWsInfo({Key? key, required this.child, required this.title, required this.onPress}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPress,
      child: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text(title, style: TextStyle(fontSize: 24)),
            child,
          ],
        ),
      ),
    );
  }
}

class FooterPlus extends StatelessWidget {
  final Widget child;

  const FooterPlus({Key? key, required this.child}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 16.0),
      child: child,
    );
  }
}

class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  late String name;
  late String id;
  late bool valid;
  late dynamic achievement;
  late dynamic rank;
  late dynamic rankShip;
  late dynamic basic;
  late dynamic ship;
  late dynamic graph;
  late Color ratingColor;

  @override
  Widget build(BuildContext context) {
    final errorStyle = BoxDecoration(color: Colors.red);
    final containerStyle = BoxDecoration(color: Colors.white);
    final footerStyle = BoxDecoration(color: Colors.grey[200]);

    if (id.isEmpty) {
      return Container(
        decoration: errorStyle,
        child: Center(child: Text('BUG')),
      );
    } else if (!valid) {
      return Container(
        decoration: containerStyle,
        child: Center(child: Text('Data is not valid\nPlease try again later')),
      );
    } else {
      Theme.of(context).primaryColor = ratingColor;

      return WoWsInfo(
        title: '- $id -',
        onPress: () async {
          final url = 'https://yourprefix.wows-numbers.com/player/$id,$name/';
          if (await canLaunch(url)) {
            await launch(url);
          } else {
            throw 'Could not launch $url';
          }
        },
        child: SingleChildScrollView(
          child: Column(
            children: [
              renderBasic(basic),
              FooterPlus(
                child: Column(
                  children: [
                    renderAchievement(achievement),
                    renderGraph(graph),
                    renderShip(ship),
                    renderRank(rank, rankShip),
                  ],
                ),
              ),
            ],
          ),
        ),
      );
    }
  }

  Widget renderBasic(dynamic basic) {
    // Implement your basic rendering logic here
    return Container(); // Placeholder
  }

  Widget renderAchievement(dynamic achievement) {
    // Implement your achievement rendering logic here
    return Container(); // Placeholder
  }

  Widget renderGraph(dynamic graph) {
    // Implement your graph rendering logic here
    return Container(); // Placeholder
  }

  Widget renderShip(dynamic ship) {
    // Implement your ship rendering logic here
    return Container(); // Placeholder
  }

  Widget renderRank(dynamic rank, dynamic rankShip) {
    // Implement your rank rendering logic here
    return Container(); // Placeholder
  }
}


class BasicRenderer extends StatelessWidget {
  final dynamic basic;
  final dynamic state;

  BasicRenderer({required this.basic, required this.state});

  @override
  Widget build(BuildContext context) {
    final container = BoxDecoration(
      // Add your container styles here
    );
    final horizontal = BoxDecoration(
      // Add your horizontal styles here
    );
    final playerName = TextStyle(
      // Add your player name styles here
    );
    final level = TextStyle(
      // Add your level styles here
    );

    if (basic == null) {
      final name = state['name'];
      return Container(
        decoration: container,
        child: Column(
          children: [
            Text(name, style: playerName),
            CircularProgressIndicator(),
          ],
        ),
      );
    } else {
      final createdAt = basic['created_at'];
      final levelingTier = basic['leveling_tier'];
      final lastBattleTime = basic['last_battle_time'];
      final nickname = basic['nickname'];
      final hidden = state['hidden'];
      final clan = state['clan'];
      final currRank = state['currRank'];
      final canBeFriend = state['canBeFriend'];
      final canBeMaster = state['canBeMaster'];
      final rating = state['rating'];

      String register = humanTimeString(createdAt);
      String lastBattle = humanTimeString(lastBattleTime);

      if (hidden) {
        return Container(
          decoration: container,
          child: Column(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(nickname, style: playerName),
                  IconButton(
                    icon: Image.network("https"),
                    iconSize: 24,
                    onPressed: () {},
                  ),
                ],
              ),
              Column(
                children: [
                  if (canBeFriend)
                    ElevatedButton(
                      onPressed: addFriend,
                      child: Text('Add Friend'),
                    ),
                  InfoLabel(title: 'Register Date', info: register),
                  InfoLabel(title: 'Last Battle', info: lastBattle),
                  InfoLabel(title: 'Level Tier', info: 'Unknown'),
                ],
              ),
            ],
          ),
        );
      } else {
        String name = nickname;
        if (clan != '') {
          name = '[$clan]\n$nickname';
        }
        String extraInfo = 'Lv $levelingTier';
        if (currRank > 0) {
          extraInfo += ' | ⭐$currRank';
        }
        return Container(
          decoration: container,
          child: Column(
            children: [
              RatingButton(rating: rating),
              Text(name, style: playerName),
              Text(extraInfo, style: level),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InfoLabel(title: 'Register Date', info: register),
                  InfoLabel(title: 'Last Battle', info: lastBattle),
                ],
              ),
              Padding(
                padding: const EdgeInsets.all(4.0),
                child: Column(
                  children: [
                    if (canBeFriend)
                      ElevatedButton(
                        onPressed: addFriend,
                        child: Text('Add Friend'),
                      ),
                    if (canBeMaster)
                      ElevatedButton(
                        onPressed: setMainAccount,
                        child: Text('Set Main'),
                      ),
                  ],
                ),
              ),
              renderStatistics(basic['statistics']),
            ],
          ),
        );
      }
    }
  }

  String humanTimeString(dynamic time) {
    // Implement your human time string conversion here
    return '';
  }

  void addFriend() {
    // Implement your add friend logic here
  }

  void setMainAccount() {
    // Implement your set main account logic here
  }

  Widget renderStatistics(dynamic statistics) {
    // Implement your statistics rendering logic here
    return Container();
  }
}

class InfoLabel extends StatelessWidget {
  final String title;
  final String info;

  InfoLabel({required this.title, required this.info});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(title),
        Text(info),
      ],
    );
  }
}

class RatingButton extends StatelessWidget {
  final dynamic rating;

  RatingButton({required this.rating});

  @override
  Widget build(BuildContext context) {
    // Implement your rating button logic here
    return Container();
  }
}

  final String accountId;
  final String nickname;
  final String server;

  PlayerInfo({required this.accountId, required this.nickname, required this.server});

  Map<String, String> getPlayerInfo() {
    return {
      'nickname': nickname,
      'account_id': accountId,
      'server': server,
    };
  }
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool canBeMaster = true;

  Future<void> setMainAccount() async {
    var info = await getPlayerInfo();
    AppGlobalData.set(LOCAL.userInfo, info);
    await SafeStorage.set(LOCAL.userInfo, info);
    setState(() {
      canBeMaster = false;
    });
  }

  Future<Map<String, dynamic>> getPlayerInfo() async {
    // Implement your logic to get player info
    return {};
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter App'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: setMainAccount,
            child: Text('Set Main Account'),
          ),
        ),
      ),
    );
  }
}

class AppGlobalData {
  static void set(String key, dynamic value) {
    // Implement your logic to set global data
  }
}

class SafeStorage {
  static Future<void> set(String key, dynamic value) async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString(key, value.toString());
  }
}

class LOCAL {
  static const String userInfo = 'userInfo';
}

void main() {
  runApp(MyApp());
}


class FriendManager extends StatefulWidget {
  @override
  _FriendManagerState createState() => _FriendManagerState();
}

class _FriendManagerState extends State<FriendManager> {
  bool canBeFriend = true;

  Map<String, dynamic> getPlayerInfo() {
    // Implement your logic to get player info
    return {
      'account_id': '12345', // Example account_id
      // Add other player info as needed
    };
  }

  Future<void> addFriend() async {
    Map<String, dynamic> info = getPlayerInfo();

    // Update object
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String str = 'friendList';
    Map<String, dynamic> friendList = prefs.getString(str) != null
        ? Map<String, dynamic>.from(prefs.getString(str))
        : {};

    friendList[info['account_id']] = info;

    await prefs.setString(str, friendList.toString());
    setState(() {
      canBeFriend = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Friend Manager'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: canBeFriend ? addFriend : null,
          child: Text('Add Friend'),
        ),
      ),
    );
  }
}


class StatisticsWidget extends StatefulWidget {
  final dynamic statistics;

  StatisticsWidget({Key? key, required this.statistics}) : super(key: key);

  @override
  _StatisticsWidgetState createState() => _StatisticsWidgetState();
}

class _StatisticsWidgetState extends State<StatisticsWidget> {
  bool showMore = false;

  @override
  Widget build(BuildContext context) {
    if (widget.statistics == null) {
      return SizedBox.shrink();
    }
    return Padding(
      padding: const EdgeInsets.only(bottom: 8.0),
      child: Column(
        children: [
          DetailedInfo(data: widget.statistics, more: showMore),
          PlayerRecord(data: widget.statistics['pvp']),
        ],
      ),
    );
  }
}

class DetailedInfo extends StatelessWidget {
  final dynamic data;
  final bool more;

  DetailedInfo({required this.data, required this.more});

  @override
  Widget build(BuildContext context) {
    // Implement your detailed info UI here
    return Container(); // Placeholder for actual implementation
  }
}

class PlayerRecord extends StatelessWidget {
  final dynamic data;

  PlayerRecord({required this.data});

  @override
  Widget build(BuildContext context) {
    // Implement your player record UI here
    return Container(); // Placeholder for actual implementation
  }
}


class AchievementTabButton extends StatelessWidget {
  final Map<String, dynamic>? achievement;

  AchievementTabButton({this.achievement});

  @override
  Widget build(BuildContext context) {
    bool loading = true;
    if (achievement != null && achievement!.isNotEmpty) {
      loading = false;
    }

    return ElevatedButton(
      onPressed: loading ? null : () {
        Navigator.pushNamed(context, 'PlayerAchievement', arguments: achievement);
      },
      child: Row(
        children: [
          Image.network('AchievementTab'),
          Text('Achievement'),
        ],
      ),
    );
  }
}


class ShipWidget extends StatelessWidget {
  final List<dynamic> ship;

  ShipWidget({required this.ship});

  void safeAction(String action, {required Map<String, dynamic> data, required int id}) {
    // Implement your SafeAction logic here
  }

  @override
  Widget build(BuildContext context) {
    bool loading = true;
    if (ship.isNotEmpty) {
      loading = false;
    }

    return ElevatedButton(
      onPressed: loading ? null : () => safeAction('PlayerShip', data: ship, id: 1),
      child: Image.network('Ship'),
    );
  }
}


class RankButton extends StatelessWidget {
  final dynamic rank;
  final dynamic rankShip;

  RankButton({required this.rank, required this.rankShip});

  void safeAction(String action, {dynamic data, dynamic ship}) {
    // Implement your SafeAction logic here
  }

  @override
  Widget build(BuildContext context) {
    bool loading = true;
    if (rank != null && rankShip != null) {
      loading = false;
    }

    return ElevatedButton(
      onPressed: loading ? null : () => safeAction('Rank', data: rank, ship: rankShip),
      child: Row(
        children: [
          Image.network('Rank'), // Replace with the actual image URL or asset
          Text('Rank'),
        ],
      ),
    );
  }
}


class GraphTabButton extends StatefulWidget {
  final List<dynamic> graph;

  GraphTabButton({required this.graph});

  @override
  _GraphTabButtonState createState() => _GraphTabButtonState();
}

class _GraphTabButtonState extends State<GraphTabButton> {
  bool hidden = false;

  void safeAction(String action, {required Map<String, dynamic> data}) {
    // Implement your SafeAction logic here
  }

  @override
  Widget build(BuildContext context) {
    bool loading = widget.graph.isEmpty;

    return ElevatedButton(
      onPressed: loading || hidden ? null : () => safeAction('Graph', data: widget.graph),
      child: Row(
        children: [
          Image.asset('assets/Graph.png'), // Assuming the icon is an asset
          SizedBox(width: 8),
          Text('Graph'),
        ],
      ),
    );
  }
}


class ErrorScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text('An error occurred!'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: ErrorScreen(),
  ));
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          flex: 1,
          child: HiddenProfile(),
        ),
      ),
    );
  }
}

class HiddenProfile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(); // Implement your hidden profile UI here
  }
}

void main() {
  runApp(MyApp());
}


class MyHorizontalLayout extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Horizontal Layout'),
      ),
      body: Row(
        children: <Widget>[
          // Add your widgets here
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MyHorizontalLayout(),
  ));
}


class PlayerNameWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.only(top: 32.0, bottom: 8.0),
        child: Text(
          'Player Name', // Replace with actual player name
          style: TextStyle(
            fontSize: 32,
            fontWeight: FontWeight.w500,
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        'Your Text Here',
        textAlign: TextAlign.center,
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.only(left: 16),
      alignment: Alignment.centerLeft,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // Add your content here
        ],
      ),
    );
  }
}


class MyFooter extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          // Add your footer items here
          Text('Item 1'),
          Text('Item 2'),
          Text('Item 3'),
        ],
      ),
    );
  }
}


class Statistics extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Statistics'),
      ),
      body: Center(
        child: Text('Statistics Content Here'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    theme: ThemeData(
      primarySwatch: Colors.blue,
    ),
    home: Statistics(),
  ));
}