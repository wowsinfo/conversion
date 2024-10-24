
class DetailedInfo extends StatefulWidget {
  final bool more;

  DetailedInfo({Key? key, required this.more}) : super(key: key);

  @override
  _DetailedInfoState createState() => _DetailedInfoState();
}

class _DetailedInfoState extends State<DetailedInfo> {
  late bool more;
  double width = 0;

  @override
  void initState() {
    super.initState();
    more = widget.more;
    width = MediaQuery.of(context).size.width;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: width,
      child: Column(
        children: [
          InfoLabel(),
          Space(),
          SectionTitle(title: lang['detailed_info']),
          Info6Icon(),
          Button(
            onPressed: () {
              // Implement button action here
            },
            child: Text('Button'),
          ),
        ],
      ),
    );
  }
}

class InfoLabel extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text('Info Label');
  }
}

class Space extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(height: 16);
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Text(
      title,
      style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
    );
  }
}

class Info6Icon extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Icon(Icons.info);
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  double width = 0;

  void updateBestWidth(BuildContext context) {
    final RenderBox renderBox = context.findRenderObject() as RenderBox;
    final newWidth = renderBox.size.width;
    setState(() {
      width = newWidth;
    });
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        WidgetsBinding.instance.addPostFrameCallback((_) => updateBestWidth(context));
        return Container(
          width: width,
          child: Center(
            child: Text('Current Width: $width'),
          ),
        );
      },
    );
  }
}


class MyWidget extends StatefulWidget {
  final dynamic data;

  MyWidget({Key? key, required this.data}) : super(key: key);

  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool more = false;

  @override
  Widget build(BuildContext context) {
    final container = BoxDecoration(); // Define your styles here

    if (widget.data == null) {
      return SizedBox.shrink();
    }

    final lastBattleTime = widget.data['last_battle_time'];
    final pvp = widget.data['pvp'];
    bool playerMode = pvp['max_damage_dealt_ship_id'] != null;

    return Container(
      decoration: container,
      child: Column(
        children: [
          if (!playerMode)
            InfoLabel(
              title: lang.basic_last_battle,
              info: humanTimeString(lastBattleTime),
            ),
          Info6Icon(data: pvp),
          if (more)
            renderMore(playerMode)
          else
            ElevatedButton(
              onPressed: () {
                if (onlyProVersion()) {
                  setState(() {
                    more = true;
                  });
                }
              },
              child: Text(lang.basic_more_stat),
            ),
          renderAllShipRecord(pvp, playerMode),
        ],
      ),
    );
  }

  Widget renderMore(bool playerMode) {
    // Implement your renderMore logic here
    return Container(); // Replace with actual implementation
  }

  Widget renderAllShipRecord(dynamic pvp, bool playerMode) {
    // Implement your renderAllShipRecord logic here
    return Container(); // Replace with actual implementation
  }

  String humanTimeString(int time) {
    // Implement your humanTimeString logic here
    return ''; // Replace with actual implementation
  }

  bool onlyProVersion() {
    // Implement your onlyProVersion logic here
    return false; // Replace with actual implementation
  }
}

class InfoLabel extends StatelessWidget {
  final String title;
  final String info;

  InfoLabel({required this.title, required this.info});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title, style: TextStyle(fontWeight: FontWeight.bold)),
        Text(info),
      ],
    );
  }
}

class Info6Icon extends StatelessWidget {
  final dynamic data;

  Info6Icon({required this.data});

  @override
  Widget build(BuildContext context) {
    // Implement your Info6Icon logic here
    return Container(); // Replace with actual implementation
  }
}


class MyWidget extends StatelessWidget {
  final Map<String, dynamic> data;

  MyWidget({required this.data});

  @override
  Widget build(BuildContext context) {
    return renderMore(data['pvp']);
  }

  Widget renderMore(String playerMode) {
    final pvp = data['pvp'];
    return renderInfo(pvp, playerMode);
  }

  Widget renderInfo(dynamic pvp, String playerMode) {
    // Implement your rendering logic based on pvp and playerMode
    return Column(
      children: [
        Text('PVP: $pvp'),
        Text('Player Mode: $playerMode'),
      ],
    );
  }
}


class RenderInfo extends StatelessWidget {
  final Map<String, dynamic> data;
  final bool playerMode;

  RenderInfo({required this.data, required this.playerMode});

  @override
  Widget build(BuildContext context) {
    final artAgro = data['art_agro'];
    final torpedoAgro = data['torpedo_agro'];
    final battles = data['battles'];
    final wins = data['wins'];
    final losses = data['losses'];
    final draws = data['draws'];
    final survivedBattles = data['survived_battles'];
    final survivedWins = data['survived_wins'];
    final damageDealt = data['damage_dealt'];
    final damageScouting = data['damage_scouting'];
    final planesKilled = data['planes_killed'];
    final shipsSpotted = data['ships_spotted'];
    final xp = data['xp'];
    final frags = data['frags'];
    final maxDamageDealt = data['max_damage_dealt'];
    final maxDamageScouting = data['max_damage_scouting'];
    final maxFragsBattle = data['max_frags_battle'];
    final maxPlanesKilled = data['max_planes_killed'];
    final maxShipsSpotted = data['max_ships_spotted'];
    final maxXp = data['max_xp'];

    return Container(
      padding: EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: lang.detailed_win, info: wins.toString()),
              InfoLabel(title: lang.detailed_draw, info: draws.toString()),
              InfoLabel(title: lang.detailed_loss, info: losses.toString()),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: lang.detailed_survived, info: survivedBattles.toString()),
              InfoLabel(title: lang.detailed_total_xp, info: xp.toString()),
              InfoLabel(title: lang.detailed_survived_win, info: survivedWins.toString()),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(
                title: lang.detailed_survival_rate,
                info: '${roundTo((survivedBattles / battles) * 100, 2)}%',
              ),
              InfoLabel(
                title: lang.detailed_survived_win_rate,
                info: '${roundTo((survivedWins / survivedBattles) * 100, 2)}%',
              ),
            ],
          ),
          SizedBox(height: 16),
          if (artAgro != null) ...[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.detailed_total_potential_damage, info: artAgro.toString()),
                InfoLabel(title: lang.detailed_avg_potential_damage, info: roundTo(artAgro / battles).toString()),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.detailed_total_torp_potential_damage, info: torpedoAgro.toString()),
                InfoLabel(title: lang.detailed_avg_torp_potential_damage, info: roundTo(torpedoAgro / battles).toString()),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.detailed_total_scouting_damage, info: damageScouting.toString()),
                InfoLabel(title: lang.detailed_avg_scouting_damage, info: roundTo(damageScouting / battles).toString()),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.detailed_total_damage, info: damageDealt.toString()),
                InfoLabel(title: lang.detailed_damage_potential_ratio, info: '${roundTo((damageDealt / artAgro) * 100, 2)}%'),
              ],
            ),
            SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.detailed_total_spotted, info: shipsSpotted.toString()),
                InfoLabel(title: lang.detailed_avg_spotted, info: roundTo(shipsSpotted / battles, 2).toString()),
              ],
            ),
          ],
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: lang.detailed_total_frag, info: frags.toString()),
              InfoLabel(title: lang.detailed_frag_spot_ratio, info: '${roundTo((frags / shipsSpotted) * 100, 2)}%'),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: lang.detailed_total_plane_killed, info: planesKilled.toString()),
              InfoLabel(title: lang.detailed_avg_plane_killed, info: roundTo(planesKilled / battles, 2).toString()),
            ],
          ),
          if (!playerMode) ...[
            SectionTitle(title: lang.record_title),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.record_max_damage_dealt, info: maxDamageDealt.toString()),
                InfoLabel(title: lang.record_max_damage_scouting, info: maxDamageScouting.toString()),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.record_max_xp, info: maxXp.toString()),
                InfoLabel(title: lang.record_max_frags_battle, info: maxFragsBattle.toString()),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoLabel(title: lang.record_max_ships_spotted, info: maxShipsSpotted.toString()),
                InfoLabel(title: lang.record_max_planes_killed, info: maxPlanesKilled.toString()),
              ],
            ),
          ],
        ],
      ),
    );
  }

  double roundTo(double value, [int decimals = 0]) {
    final factor = pow(10, decimals);
    return (value * factor).round() / factor;
  }
}

class InfoLabel extends StatelessWidget {
  final String title;
  final String info;

  InfoLabel({required this.title, required this.info});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(title, style: TextStyle(fontWeight: FontWeight.bold)),
        Text(info),
      ],
    );
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: Text(title, style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
    );
  }
}


class ShipRecordRenderer extends StatelessWidget {
  final Map<String, dynamic> data;
  final bool playerMode;

  ShipRecordRenderer({required this.data, required this.playerMode});

  @override
  Widget build(BuildContext context) {
    final aircraft = data['aircraft'];
    final mainBattery = data['main_battery'];
    final ramming = data['ramming'];
    final secondBattery = data['second_battery'];
    final torpedoes = data['torpedoes'];

    List<Map<String, dynamic>> weapons = [
      {'name': 'Main Battery', 'data': mainBattery},
      {'name': 'Secondary Battery', 'data': secondBattery},
      {'name': 'Torpedoes', 'data': torpedoes},
      {'name': 'Aircraft', 'data': aircraft},
      {'name': 'Ramming', 'data': ramming},
    ];

    if (!playerMode) {
      return ListView(
        children: weapons.map((d) => renderShipRecord(d)).toList(),
      );
    }
    return SizedBox.shrink();
  }

  Widget renderShipRecord(Map<String, dynamic> weapon) {
    return ListTile(
      title: Text(weapon['name']),
      subtitle: Text(weapon['data'].toString()),
    );
  }
}


class ShipRecord extends StatelessWidget {
  final Weapon weapon;

  ShipRecord({required this.weapon});

  @override
  Widget build(BuildContext context) {
    final data = weapon.data;
    final name = weapon.name;

    if (data == null) {
      return SizedBox.shrink();
    }

    final frags = weapon.data.frags;
    final maxFragsBattle = weapon.data.max_frags_battle;
    final hits = weapon.data.hits;
    final shots = weapon.data.shots;

    if (frags == 0) {
      return SizedBox.shrink();
    }

    return Container(
      key: ValueKey(name),
      padding: EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SectionTitle(title: name),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: lang.weapon_total_frags, info: frags.toString()),
              InfoLabel(title: lang.weapon_max_frags, info: maxFragsBattle.toString()),
              if (hits != null)
                InfoLabel(
                  title: lang.weapon_hit_ratio,
                  info: '${(hits / shots * 100).toStringAsFixed(1)}%',
                ),
            ],
          ),
        ],
      ),
    );
  }
}

class Weapon {
  final String name;
  final WeaponData data;

  Weapon({required this.name, required this.data});
}

class WeaponData {
  final int frags;
  final int max_frags_battle;
  final int? hits;
  final int shots;

  WeaponData({
    required this.frags,
    required this.max_frags_battle,
    this.hits,
    required this.shots,
  });
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Text(
      title,
      style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
    );
  }
}

class InfoLabel extends StatelessWidget {
  final String title;
  final String info;

  InfoLabel({required this.title, required this.info});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(title, style: TextStyle(fontSize: 16)),
        Text(info, style: TextStyle(fontSize: 16)),
      ],
    );
  }
}

class Lang {
  String weapon_total_frags = "Total Frags";
  String weapon_max_frags = "Max Frags in Battle";
  String weapon_hit_ratio = "Hit Ratio";
}

final lang = Lang();


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


class DetailedInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Detailed Info'),
      ),
      body: Center(
        child: Text('This is the detailed information page.'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: DetailedInfo(),
  ));
}