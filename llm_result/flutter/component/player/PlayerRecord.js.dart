
class PlayerRecord extends StatefulWidget {
  @override
  _PlayerRecordState createState() => _PlayerRecordState();
}

class _PlayerRecordState extends State<PlayerRecord> {
  double goodWidth;

  @override
  void initState() {
    super.initState();
    goodWidth = bestWidth(400);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: goodWidth,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SectionTitle(title: lang['player_record']),
          WarshipCell(),
          InfoLabel(),
          Paragraph(text: SAVED.toString()),
        ],
      ),
    );
  }

  double bestWidth(double maxWidth) {
    // Implement your logic for bestWidth here
    return maxWidth; // Placeholder implementation
  }
}


class RecordsWidget extends StatelessWidget {
  final Map<String, dynamic> data;
  final Map<String, String> lang;

  RecordsWidget({required this.data, required this.lang});

  @override
  Widget build(BuildContext context) {
    if (data.isEmpty) {
      return Container();
    }

    final aircraft = data['aircraft'];
    final mainBattery = data['main_battery'];
    final ramming = data['ramming'];
    final secondBattery = data['second_battery'];
    final torpedoes = data['torpedoes'];
    final maxDamageDealt = data['max_damage_dealt'];
    final maxDamageDealtShipId = data['max_damage_dealt_ship_id'];
    final maxFragsBattle = data['max_frags_battle'];
    final maxFragsShipId = data['max_frags_ship_id'];
    final maxPlanesKilled = data['max_planes_killed'];
    final maxPlanesKilledShipId = data['max_planes_killed_ship_id'];
    final maxShipsSpotted = data['max_ships_spotted'];
    final maxShipsSpottedShipId = data['max_ships_spotted_ship_id'];
    final maxXp = data['max_xp'];
    final maxXpShipId = data['max_xp_ship_id'];
    final maxDamageScouting = data['max_damage_scouting'];
    final maxScoutingDamageShipId = data['max_scouting_damage_ship_id'];
    final maxTotalAgro = data['max_total_agro'];
    final maxTotalAgroShipId = data['max_total_agro_ship_id'];

    // Max records
    List<Map<String, dynamic>> max = [
      {
        'name': lang['record_max_damage_dealt'],
        'num': maxDamageDealt,
        'id': maxDamageDealtShipId,
      },
      {
        'name': lang['record_max_frags_battle'],
        'num': maxFragsBattle,
        'id': maxFragsShipId,
      },
      {
        'name': lang['record_max_planes_killed'],
        'num': maxPlanesKilled,
        'id': maxPlanesKilledShipId,
      },
      {
        'name': lang['record_max_xp'],
        'num': maxXp,
        'id': maxXpShipId,
      },
      {
        'name': lang['record_max_ships_spotted'],
        'num': maxShipsSpotted,
        'id': maxShipsSpottedShipId,
      },
      {
        'name': lang['record_max_total_agro'],
        'num': maxTotalAgro,
        'id': maxTotalAgroShipId,
      },
      {
        'name': lang['record_max_damage_scouting'],
        'num': maxDamageScouting,
        'id': maxScoutingDamageShipId,
      },
    ];

    // Best ships
    List<Map<String, dynamic>> records = [
      {'name': lang['warship_artillery_main'], 'data': mainBattery},
      {'name': lang['warship_artillery_secondary'], 'data': secondBattery},
      {'name': lang['warship_torpedoes'], 'data': torpedoes},
      {'name': lang['warship_aircraft'], 'data': aircraft},
      {'name': lang['warship_ramming'], 'data': ramming},
    ];

    return Container(
      child: Column(
        children: [
          Text(
            lang['record_title'],
            style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
          ),
          Column(
            children: max.map((data) => renderMax(data)).toList(),
          ),
          Column(
            children: records.map((data) => renderRecord(data)).toList(),
          ),
        ],
      ),
    );
  }

  Widget renderMax(Map<String, dynamic> data) {
    return ListTile(
      title: Text(data['name']),
      trailing: Text(data['num'].toString()),
    );
  }

  Widget renderRecord(Map<String, dynamic> data) {
    return ListTile(
      title: Text(data['name']),
      subtitle: Text(data['data'].toString()),
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

  void updateBestWidth(Size size) {
    final newWidth = size.width;
    setState(() {
      goodWidth = bestWidth(400, newWidth);
    });
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        updateBestWidth(constraints.biggest);
        return Container(
          width: goodWidth,
          height: 100,
          color: Colors.blue,
        );
      },
    );
  }
}


class MaxRenderer extends StatelessWidget {
  final Map<String, dynamic> data;
  final double goodWidth;

  MaxRenderer({required this.data, required this.goodWidth});

  @override
  Widget build(BuildContext context) {
    final String? id = data['id'];
    final String name = data['name'];
    final int num = data['num'];

    if (id == null) {
      return SizedBox.shrink();
    }

    var ship = AppGlobalData.get(SAVED.warship)[id];

    return Container(
      width: goodWidth,
      key: ValueKey(name),
      child: Column(
        children: [
          Container(
            child: WarshipCell(
              item: ship,
              scale: 2,
              onPress: () => SafeAction('WarshipDetail', {'item': ship}),
            ),
          ),
          Container(
            child: InfoLabel(title: name, info: num),
          ),
        ],
      ),
    );
  }
}


class RecordWidget extends StatelessWidget {
  final Map<String, dynamic> item;
  final double goodWidth;

  RecordWidget({required this.item, required this.goodWidth});

  @override
  Widget build(BuildContext context) {
    final data = item['data'];
    final name = item['name'];
    final frags = data['frags'];
    final maxFragsBattle = data['max_frags_battle'];
    final maxFragsShipId = data['max_frags_ship_id'];
    final hits = data['hits'];
    final shots = data['shots'];

    if (maxFragsShipId == null) {
      return SizedBox.shrink();
    }

    var bestShip = AppGlobalData.get(SAVED.warship)[maxFragsShipId];

    return Container(
      width: goodWidth,
      key: ValueKey(name),
      child: Column(
        children: [
          SectionTitle(title: name, center: true),
          Container(
            decoration: BoxDecoration(
              border: Border.all(color: Colors.grey),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Column(
              children: [
                Container(
                  padding: EdgeInsets.all(8),
                  child: Column(
                    children: [
                      Text(lang.record_best_ship),
                      WarshipCell(
                        item: bestShip,
                        scale: 2,
                        onPress: () => SafeAction('WarshipDetail', {'item': bestShip}),
                      ),
                    ],
                  ),
                ),
                Container(
                  padding: EdgeInsets.all(8),
                  child: Column(
                    children: [
                      InfoLabel(title: lang.weapon_total_frags, info: frags.toString()),
                      InfoLabel(title: lang.weapon_max_frags, info: maxFragsBattle.toString()),
                      if (hits != null)
                        InfoLabel(
                          title: lang.weapon_hit_ratio,
                          info: '${(hits / shots * 100).toStringAsFixed(2)}%',
                        ),
                    ],
                  ),
                ),
              ],
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


class RecordWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: [
        // Add your content here
      ],
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Record Example')),
      body: RecordWidget(),
    ),
  ));
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Wrap(
        direction: Axis.horizontal,
        alignment: WrapAlignment.center,
        children: [
          // Add your widgets here
        ],
      ),
    );
  }
}


class PlayerRecord extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Player Record'),
      ),
      body: Center(
        child: Text('Player Record Content'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: PlayerRecord(),
  ));
}