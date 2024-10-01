
class ClanInfo extends StatefulWidget {
  final Map<String, dynamic> info;

  ClanInfo({required this.info});

  @override
  _ClanInfoState createState() => _ClanInfoState();
}

class _ClanInfoState extends State<ClanInfo> {
  late String id;
  late String tag;
  bool valid = true;
  bool canBeFriend = true;
  Map<String, dynamic>? clanInfo;

  @override
  void initState() {
    super.initState();
    final clanId = widget.info['clan_id'];
    tag = widget.info['tag'] ?? '???';

    if (clanId == null) {
      id = '???';
    } else {
      id = clanId.toString();
      // Assuming AppGlobalData.get(LOCAL.friendList) returns a Map
      var friend = AppGlobalData.get(LOCAL.friendList);
      canBeFriend = friend['clan'][clanId] == null;

      fetchClanInfo(clanId);
    }
  }

  Future<void> fetchClanInfo(String clanId) async {
    final domain = getDomain(widget.info['server']);
    final response = await http.get(Uri.parse('$domain/api/clan/$clanId'));

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      clanInfo = data['data'][clanId];
      if (clanInfo == null) {
        setState(() {
          valid = false;
        });
      } else {
        setState(() {
          valid = true;
        });
      }
    } else {
      setState(() {
        valid = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    if (!valid) {
      return Center(child: Text('Invalid Clan ID'));
    }

    if (clanInfo == null) {
      return Center(child: CircularProgressIndicator());
    }

    return Scaffold(
      appBar: AppBar(
        title: Text('Clan Info'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Clan ID: $id', style: TextStyle(fontSize: 20)),
            Text('Tag: $tag', style: TextStyle(fontSize: 20)),
            // Add more clan info display here
            // Example: Text('Clan Name: ${clanInfo['name']}'),
          ],
        ),
      ),
    );
  }
}

// Placeholder for AppGlobalData and other utility functions
class AppGlobalData {
  static Map<String, dynamic> get(String key) {
    // Implement your logic to retrieve data
    return {};
  }
}

String getDomain(String server) {
  // Implement your logic to get domain based on server
  return 'https://example.com';
}


class ClanInfoWidget extends StatefulWidget {
  @override
  _ClanInfoWidgetState createState() => _ClanInfoWidgetState();
}

class _ClanInfoWidgetState extends State<ClanInfoWidget> {
  Map<String, dynamic> info;
  String tag;
  String id;
  bool valid;

  @override
  Widget build(BuildContext context) {
    final clanTagStyle = TextStyle(fontSize: 20, fontWeight: FontWeight.bold);
    final containerStyle = BoxDecoration(
      border: Border.all(color: Colors.grey),
      borderRadius: BorderRadius.circular(8),
      padding: EdgeInsets.all(10),
    );

    if (valid) {
      return GestureDetector(
        onTap: () async {
          final url = 'https://${this.prefix}.wows-numbers.com/clan/$id, $tag/';
          if (await canLaunch(url)) {
            await launch(url);
          } else {
            throw 'Could not launch $url';
          }
        },
        child: Container(
          decoration: containerStyle,
          child: Column(
            children: [
              Text('- $id -', style: TextStyle(fontSize: 24)),
              renderClanInfo(info),
            ],
          ),
        ),
      );
    } else {
      return Container(
        decoration: containerStyle,
        child: Column(
          children: [
            Text('- $id -', style: TextStyle(fontSize: 24)),
            Text(tag, style: clanTagStyle),
          ],
        ),
      );
    }
  }

  Widget renderClanInfo(Map<String, dynamic> info) {
    // Implement your clan info rendering logic here
    return Text(info.toString());
  }
}


class ClanInfo extends StatelessWidget {
  final Map<String, dynamic> data;
  final bool canBeFriend;

  ClanInfo({required this.data, required this.canBeFriend});

  String humanTimeString(int timestamp) {
    final date = DateTime.fromMillisecondsSinceEpoch(timestamp);
    return DateFormat.yMMMd().format(date);
  }

  void pushToMaster(String name, String id) {
    // Implement navigation to master
  }

  void pushToPlayer(Map<String, dynamic> item) {
    // Implement navigation to player
  }

  void addFriend() {
    // Implement add friend functionality
  }

  @override
  Widget build(BuildContext context) {
    if (data.isNotEmpty) {
      final createdAt = data['created_at'];
      final creatorName = data['creator_name'];
      final creatorId = data['creator_id'];
      final leaderName = data['leader_name'];
      final leaderId = data['leader_id'];
      final description = data['description'];
      final name = data['name'];
      final members = data['members'];
      final membersCount = data['members_count'];
      final tag = data['tag'];

      List memberInfo = members.values.toList();
      memberInfo.sort((a, b) => a['joined_at'].compareTo(b['joined_at']));

      return SingleChildScrollView(
        child: Column(
          children: [
            Text(tag, style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold)),
            Text(name, style: TextStyle(color: Colors.blue, fontSize: 20, textAlign: TextAlign.center)),
            ListTile(
              title: Text('Created Date'),
              subtitle: Text(humanTimeString(createdAt)),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                GestureDetector(
                  onTap: () => pushToMaster(creatorName, creatorId),
                  child: Column(
                    children: [
                      Text('Creator'),
                      Text(creatorName),
                    ],
                  ),
                ),
                GestureDetector(
                  onTap: () => pushToMaster(leaderName, leaderId),
                  child: Column(
                    children: [
                      Text('Leader'),
                      Text(leaderName),
                    ],
                  ),
                ),
              ],
            ),
            if (canBeFriend)
              ElevatedButton(
                onPressed: addFriend,
                child: Text('Add Friend'),
              ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(description),
            ),
            Text('${membersCount} Members', style: TextStyle(fontSize: 18)),
            ListView.builder(
              shrinkWrap: true,
              physics: NeverScrollableScrollPhysics(),
              itemCount: memberInfo.length,
              itemBuilder: (context, index) {
                final item = memberInfo[index];
                return ListTile(
                  title: Text(item['account_name']),
                  subtitle: Text(humanTimeString(item['joined_at'])),
                  onTap: () => pushToPlayer(item),
                  trailing: Text(item['account_id'].toString()),
                );
              },
            ),
          ],
        ),
      );
    } else {
      return Center(child: CircularProgressIndicator());
    }
  }
}


class MyWidget extends StatefulWidget {
  final Map<String, dynamic> info;

  MyWidget({required this.info});

  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool canBeFriend = true;

  Future<void> addFriend() async {
    final clanId = widget.info['clan_id'];
    final tag = widget.info['tag'];
    final server = widget.info['server'];
    final str = 'friendList';

    final prefs = await SharedPreferences.getInstance();
    final friendList = prefs.getString(str);
    Map<String, dynamic> friendData = friendList != null ? Map<String, dynamic>.from(json.decode(friendList)) : {'clan': {}};

    friendData['clan'][clanId] = {'clan_id': clanId, 'tag': tag, 'server': server};

    await prefs.setString(str, json.encode(friendData));
    setState(() {
      canBeFriend = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ElevatedButton(
        onPressed: canBeFriend ? addFriend : null,
        child: Text('Add Friend'),
      ),
    );
  }
}


class Statistics {
  final String server;

  Statistics(this.server);

  void pushToMaster(String name, String id) {
    Map<String, dynamic> item = {
      'nickname': name,
      'account_id': id,
      'server': server,
    };
    safeAction('Statistics', {'info': item});
  }

  void safeAction(String action, Map<String, dynamic> data) {
    // Implement your action handling logic here
    print('Action: $action, Data: $data');
  }
}

  item['nickname'] = item['account_name'];
  item['server'] = this.server;
  SafeAction('Statistics', {'info': item});
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


class ClanTagWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.only(top: 16.0),
        child: Text(
          'Clan Tag', // Replace with your actual clan tag
          style: TextStyle(
            fontSize: 36,
            fontWeight: FontWeight.w500,
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}


class MyHorizontalLayout extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Horizontal Layout'),
      ),
      body: Row(
        mainAxisAlignment: MainAxisAlignment.start,
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


class ClanInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Clan Info'),
      ),
      body: Center(
        child: Text('Clan information goes here'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: ClanInfo(),
  ));
}