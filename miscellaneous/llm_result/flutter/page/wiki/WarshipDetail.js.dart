
class WarshipDetail extends StatefulWidget {
  final dynamic item;

  WarshipDetail({Key? key, required this.item}) : super(key: key);

  @override
  _WarshipDetailState createState() => _WarshipDetailState();
}

class _WarshipDetailState extends State<WarshipDetail> {
  late dynamic curr;
  late List<dynamic> similar;
  bool loading = true;
  Map<String, dynamic> data = {};
  bool compare = false;

  @override
  void initState() {
    super.initState();
    curr = widget.item;
    similar = getSimilarShips(curr);
    efficientDataRequest(curr['ship_id']);
  }

  List<dynamic> getSimilarShips(dynamic curr) {
    List<dynamic> warship = AppGlobalData.get(SAVED.warship);
    List<dynamic> similarShips = warship.where((s) {
      return s['tier'] == curr['tier'] &&
          s['type'] == curr['type'] &&
          s['ship_id'] != curr['ship_id'];
    }).toList();

    return similarShips.map((s) => s).toList();
  }

  void efficientDataRequest(String shipId) {
    // Implement your data request logic here
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Detail'),
      ),
      body: loading
          ? Center(child: CircularProgressIndicator())
          : SingleChildScrollView(
              child: Column(
                children: [
                  // Display current ship details
                  Text(curr['name'], style: TextStyle(fontSize: 24)),
                  // Add more ship details here

                  // Display similar ships
                  SectionTitle(title: 'Similar Ships'),
                  ListView.builder(
                    shrinkWrap: true,
                    physics: NeverScrollableScrollPhysics(),
                    itemCount: similar.length,
                    itemBuilder: (context, index) {
                      return WarshipCell(ship: similar[index]);
                    },
                  ),
                ],
              ),
            ),
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

class WarshipCell extends StatelessWidget {
  final dynamic ship;

  WarshipCell({required this.ship});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(ship['name']),
      subtitle: Text('Tier: ${ship['tier']} - Type: ${ship['type']}'),
      onTap: () {
        // Navigate to ship detail
      },
    );
  }
}


class MyChartWidget extends StatefulWidget {
  @override
  _MyChartWidgetState createState() => _MyChartWidgetState();
}

class _MyChartWidgetState extends State<MyChartWidget> {
  List<dynamic> similar = [];

  @override
  void initState() {
    super.initState();
    // Simulate fetching data
    fetchData();
  }

  void fetchData() {
    // Simulate fetching similar data
    setState(() {
      similar = [/* Your data here */];
    });
    buildCharts(similar);
  }

  void buildCharts(List<dynamic> data) {
    // Implement your chart building logic here
    // For example, using a charting library
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Charts'),
      ),
      body: Center(
        child: Text('Charts will be displayed here'),
      ),
    );
  }
}


class YourWidget extends StatefulWidget {
  final Module module;

  YourWidget({Key? key, required this.module}) : super(key: key);

  @override
  _YourWidgetState createState() => _YourWidgetState();
}

class _YourWidgetState extends State<YourWidget> {
  late Map<String, dynamic> data;
  bool loading = false;
  late Module module;

  @override
  void initState() {
    super.initState();
    data = {}; // Initialize your data here
    module = widget.module;
  }

  @override
  void didUpdateWidget(YourWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (widget.module != module) {
      setState(() {
        loading = true;
        module = widget.module;
      });
      getNewModule(module).then((json) {
        final newModule = Guard(json, 'data.${module.shipId}', null);
        if (newModule != null) {
          // Copy data
          Map<String, dynamic> newData = Map.from(data);
          // Update module info
          newData.remove('default_profile');
          newData['default_profile'] = newModule;
          setState(() {
            loading = false;
            data = newData;
          });
          print('Module updated');
        }
      });
    }
  }

  Future<Map<String, dynamic>> getNewModule(Module module) async {
    // Implement your API call here and return the JSON response
  }

  @override
  Widget build(BuildContext context) {
    return loading
        ? CircularProgressIndicator()
        : Container(); // Replace with your actual UI
  }
}

class Module {
  final String shipId;

  Module({required this.shipId});
}


class ShipModuleService {
  final String server;

  ShipModuleService(this.server);

  Future<void> getNewModule(Map<String, dynamic> data) async {
    final shipId = data['ship_id'];
    final module = data['module'];

    final artillery = module['Artillery'];
    final diveBomber = module['DiveBomber'];
    final engine = module['Engine'];
    final fighter = module['Fighter'];
    final flightControl = module['FlightControl'];
    final hull = module['Hull'];
    final suo = module['Suo'];
    final torpedoBomber = module['TorpedoBomber'];
    final torpedoes = module['Torpedoes'];

    print(module);

    final response = await SafeFetch.get(
      WoWsAPI.ShipModule,
      server,
      shipId,
      artillery,
      diveBomber,
      engine,
      fighter,
      suo,
      flightControl,
      hull,
      torpedoBomber,
      torpedoes,
      langStr(),
    );

    // Handle the response as needed
  }
}

class SafeFetch {
  static Future<http.Response> get(
    String apiEndpoint,
    String server,
    String shipId,
    dynamic artillery,
    dynamic diveBomber,
    dynamic engine,
    dynamic fighter,
    dynamic suo,
    dynamic flightControl,
    dynamic hull,
    dynamic torpedoBomber,
    dynamic torpedoes,
    String lang,
  ) async {
    // Construct the URL and make the HTTP GET request
    final url = Uri.parse('$apiEndpoint/$server/$shipId?artillery=$artillery&diveBomber=$diveBomber&engine=$engine&fighter=$fighter&suo=$suo&flightControl=$flightControl&hull=$hull&torpedoBomber=$torpedoBomber&torpedoes=$torpedoes&lang=$lang');
    return await http.get(url);
  }
}

String langStr() {
  // Return the language string as needed
  return 'en';
}


class YourWidget extends StatefulWidget {
  @override
  _YourWidgetState createState() => _YourWidgetState();
}

class _YourWidgetState extends State<YourWidget> {
  Map<String, dynamic>? curr;
  List<dynamic>? similar;

  @override
  Widget build(BuildContext context) {
    if (curr != null) {
      return WoWsInfo(
        title: '${curr!['ship_id_str']} ${curr!['ship_id']}',
        child: SingleChildScrollView(
          child: Column(
            children: [
              AnimatedContainer(
                duration: Duration(seconds: 1),
                child: AnimeView(
                  animation: 'pulse',
                  iterationCount: 'infinite',
                  child: WikiIcon(warship: curr!, scale: 3),
                ),
              ),
              renderContent(),
              renderSimilar(similar),
            ],
          ),
        ),
      );
    } else {
      return Center(child: CircularProgressIndicator());
    }
  }

  Widget renderContent() {
    // Implement your content rendering logic here
    return Container(); // Replace with actual content
  }

  Widget renderSimilar(List<dynamic>? similar) {
    // Implement your similar items rendering logic here
    return Container(); // Replace with actual similar items
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool loading = true;
  var curr;
  var data;

  @override
  Widget build(BuildContext context) {
    return renderContent();
  }

  Widget renderContent() {
    if (loading) {
      return Center(child: CircularProgressIndicator());
    } else {
      return Column(
        children: [
          renderBasic(curr, data),
          renderAll(data),
        ],
      );
    }
  }

  Widget renderBasic(curr, data) {
    // Implement your basic rendering logic here
    return Text('Basic Content'); // Placeholder
  }

  Widget renderAll(data) {
    // Implement your all rendering logic here
    return Text('All Content'); // Placeholder
  }
}


class ShipDetails extends StatelessWidget {
  final dynamic curr;
  final dynamic data;

  ShipDetails({required this.curr, required this.data});

  @override
  Widget build(BuildContext context) {
    final container = BoxDecoration(
      // Add your container styles here
    );
    final horizontal = BoxDecoration(
      // Add your horizontal styles here
    );
    final shipTitle = TextStyle(
      // Add your ship title styles here
    );
    final centerText = TextStyle(
      // Add your center text styles here
    );
    final modelBtn = ElevatedButton.styleFrom(
      // Add your model button styles here
    );

    final name = curr['name'];
    final model = curr['model'];
    final type = curr['type'];
    final nation = curr['nation'];
    final shipId = curr['ship_id'];
    final description = data['description'];

    var currShip = AppGlobalData.get(SAVED.pr)[shipId];
    var avgDamage = Guard(currShip, 'average_damage_dealt', 0);
    var avgWinrate = Guard(currShip, 'win_rate', 0);
    var avgFrag = Guard(currShip, 'average_frags', 0);

    return Container(
      decoration: container,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(name, style: shipTitle.copyWith(margin: EdgeInsets.only(top: 8))),
          if (currShip != null) ...[
            Container(
              decoration: horizontal,
              margin: EdgeInsets.only(bottom: 16),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InfoLabel(title: lang.warship_avg_damage, info: avgDamage.toStringAsFixed(0)),
                  InfoLabel(title: lang.warship_avg_winrate, info: '${avgWinrate.toStringAsFixed(1)}%'),
                  InfoLabel(title: lang.warship_avg_frag, info: avgFrag.toStringAsFixed(2)),
                ],
              ),
            ),
          ],
          Text(nation.toUpperCase()),
          Text(type),
          PriceLabel(item: data),
          if (model != null) 
            ElevatedButton(
              style: modelBtn,
              onPressed: () async {
                final url = 'https://sketchfab.com/models/$model/embed?autostart=1&preload=1';
                if (await canLaunch(url)) {
                  await launch(url);
                } else {
                  throw 'Could not launch $url';
                }
              },
              child: Text(lang.warship_model),
            ),
          Padding(
            padding: const EdgeInsets.only(top: 16.0),
            child: Text(description, style: centerText),
          ),
        ],
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  final dynamic curr;

  MyWidget({required this.curr});

  dynamic guard(dynamic curr, String key, dynamic defaultValue) {
    return curr[key] ?? defaultValue;
  }

  @override
  Widget build(BuildContext context) {
    // Check if there are modules available
    var module = guard(curr, 'modules', {});
    bool hasModule = false;
    for (var id in module.keys) {
      var currModule = module[id];
      if (currModule.length > 1) {
        hasModule = true;
        break;
      }
    }

    return Column(
      children: [
        renderStatus(guard(curr, 'default_profile', null)),
        if (hasModule)
          ElevatedButton(
            style: ElevatedButton.styleFrom(
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.zero),
            ),
            onPressed: () => safeAction('WarshipModule', {'data': curr}),
            child: Text('Update Module'), // Replace with lang.warship_update_module
          ),
        renderSurvivability(curr),
        renderMainBattery(guard(curr, 'default_profile.artillery', null)),
        renderSecondary(guard(curr, 'default_profile.atbas', null)),
        renderTorpedo(guard(curr, 'default_profile.torpedoes', null)),
        renderAADefense(guard(curr, 'default_profile.anti_aircraft', null)),
        renderMobility(guard(curr, 'default_profile.mobility', null)),
        renderConcealment(guard(curr, 'default_profile.concealment', null)),
        renderUpgrade(curr),
        renderNextShip(guard(curr, 'next_ships'), null),
      ],
    );
  }

  Widget renderStatus(dynamic profile) {
    // Implement your renderStatus logic here
    return Container(); // Placeholder
  }

  Widget renderSurvivability(dynamic curr) {
    // Implement your renderSurvivability logic here
    return Container(); // Placeholder
  }

  Widget renderMainBattery(dynamic artillery) {
    // Implement your renderMainBattery logic here
    return Container(); // Placeholder
  }

  Widget renderSecondary(dynamic atbas) {
    // Implement your renderSecondary logic here
    return Container(); // Placeholder
  }

  Widget renderTorpedo(dynamic torpedoes) {
    // Implement your renderTorpedo logic here
    return Container(); // Placeholder
  }

  Widget renderAADefense(dynamic antiAircraft) {
    // Implement your renderAADefense logic here
    return Container(); // Placeholder
  }

  Widget renderMobility(dynamic mobility) {
    // Implement your renderMobility logic here
    return Container(); // Placeholder
  }

  Widget renderConcealment(dynamic concealment) {
    // Implement your renderConcealment logic here
    return Container(); // Placeholder
  }

  Widget renderUpgrade(dynamic curr) {
    // Implement your renderUpgrade logic here
    return Container(); // Placeholder
  }

  Widget renderNextShip(dynamic nextShips, dynamic param) {
    // Implement your renderNextShip logic here
    return Container(); // Placeholder
  }

  void safeAction(String action, Map<String, dynamic> data) {
    // Implement your safeAction logic here
  }
}


class WarshipStat extends StatelessWidget {
  final dynamic profile;

  WarshipStat({required this.profile});

  @override
  Widget build(BuildContext context) {
    // Implement the UI for displaying the warship status based on the profile
    return Text('Warship Status: ${profile.toString()}');
  }
}

class ShipStatus extends StatelessWidget {
  final dynamic profile;

  ShipStatus({required this.profile});

  @override
  Widget build(BuildContext context) {
    if (profile == null) {
      return SizedBox.shrink();
    }
    return WarshipStat(profile: profile);
  }
}


class SurvivabilityWidget extends StatelessWidget {
  final dynamic curr;
  final dynamic lang;
  final dynamic styles;

  SurvivabilityWidget({required this.curr, required this.lang, required this.styles});

  @override
  Widget build(BuildContext context) {
    if (curr == null) {
      return SizedBox.shrink();
    }

    var armour = Guard(curr, 'default_profile.armour', null);
    var tier = Guard(curr, 'tier', null);

    final floodProb = armour['flood_prob'];
    final range = armour['range'];
    final health = armour['health'];
    final horizontal = styles['horizontal'];

    return Container(
      margin: styles['margin'],
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SectionTitle(title: lang['warship_survivability']),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              InfoLabel(
                title: lang['warship_survivability_health'],
                info: '${health} - ${health + tier * 350}',
              ),
              InfoLabel(
                title: lang['warship_survivability_armour'],
                info: '${range['min']} - ${range['max']} mm',
              ),
              if (floodProb != 0)
                InfoLabel(
                  title: lang['warship_survivability_protection'],
                  info: '${floodProb}%',
                ),
            ],
          ),
        ],
      ),
    );
  }
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
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title, style: TextStyle(fontSize: 16)),
        Text(info, style: TextStyle(fontSize: 14)),
      ],
    );
  }
}

dynamic Guard(dynamic curr, String path, dynamic defaultValue) {
  // Implement the Guard function to safely access nested properties
  // This is a placeholder for the actual implementation
  return null; // Replace with actual logic
}


class ArtilleryWidget extends StatelessWidget {
  final Artillery artillery;
  final List<int> upgrades;

  ArtilleryWidget({required this.artillery, required this.upgrades});

  @override
  Widget build(BuildContext context) {
    if (artillery == null) {
      return Container();
    }
    final horizontal = BoxDecoration(); // Define your horizontal style
    final centerText = TextStyle(); // Define your center text style
    final maxDispersion = artillery.maxDispersion;
    final gunRate = artillery.gunRate;
    final distance = artillery.distance;
    final rotationTime = artillery.rotationTime;
    final slots = artillery.slots;
    final shells = artillery.shells;
    final AP = shells.AP;
    final HE = shells.HE;

    // Get all guns
    String mainGun = '';
    String gunName = '';
    for (var gun in slots) {
      mainGun += '${gun.guns} x ${gun.barrels}  ';
    }
    gunName = slots.last.name;

    // Get gun penetration
    int caliber = int.parse(gunName.split(' ')[0]);
    double fireRate = caliber > 160 ? 4 : 3;
    String penetration = '';
    if (HE != null) {
      double oneFourth = (caliber / 4).toStringAsFixed(1);
      double oneFifth = (caliber / 5).toStringAsFixed(1);
      double oneSixth = (caliber / 6).toStringAsFixed(1);
      penetration =
          '1/6 | $oneSixth - ${(double.parse(oneSixth) * 1.25).toStringAsFixed(1)} mm\n' +
          '1/5 | $oneFifth - ${(double.parse(oneFifth) * 1.25).toStringAsFixed(1)} mm\n' +
          '1/4 | $oneFourth - ${(double.parse(oneFourth) * 1.25).toStringAsFixed(1)} mm';
      fireRate += HE.burnProbability;
    }

    String overmatch = '';
    if (AP != null) {
      overmatch = '${(caliber / 14.3).toStringAsFixed(2)} mm\n';
    }

    // Get best reload
    double reload = (60 / gunRate).toStringAsFixed(1);
    double re1 = caliber <= 139 ? 0.9 : 1;
    double re2 = upgrades.contains(4280471472) ? 0.88 : 1;
    double bestReload = (60 / gunRate * re1 * re2).toStringAsFixed(1);
    String reloadMsg = '$reload - $bestReload s';
    if (reload == bestReload) {
      reloadMsg = '$reload s';
    }

    // Get best range
    double range = distance.toStringAsFixed(1);
    double ra1 = caliber <= 139 ? 1.2 : 1;
    double ra2 = upgrades.contains(4278374320) ? 1.16 : 1;
    double bestRange = (distance * ra1 * ra2).toStringAsFixed(1);
    String rangeMsg = '$range - $bestRange km';
    if (range == bestRange) {
      rangeMsg = '$range km';
    }

    return Container(
      margin: EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('Main Artillery', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: 'Reload', info: reloadMsg),
              InfoLabel(title: 'Range', info: rangeMsg),
              InfoLabel(title: 'Configuration', info: mainGun),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: 'Dispersion', info: '$maxDispersion m'),
              InfoLabel(title: 'Rotation', info: '$rotationTime s'),
            ],
          ),
          Text(gunName, style: centerText),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              if (HE != null) ...[
                Column(
                  children: [
                    Text('HE', style: centerText),
                    InfoLabel(title: 'Fire Chance', info: '🔥${HE.burnProbability} - ${fireRate}%'),
                    InfoLabel(title: 'Weight', info: '${HE.bulletMass} kg'),
                    InfoLabel(title: 'Damage', info: '${HE.damage}'),
                    InfoLabel(title: 'Speed', info: '${HE.bulletSpeed} m/s'),
                    InfoLabel(title: 'HE Penetration', info: penetration),
                  ],
                ),
              ],
              if (AP != null) ...[
                Column(
                  children: [
                    Text('AP', style: centerText),
                    InfoLabel(title: 'Fire Chance', info: '0%🔥'),
                    InfoLabel(title: 'Weight', info: '${AP.bulletMass} kg'),
                    InfoLabel(title: 'Damage', info: '${AP.damage}'),
                    InfoLabel(title: 'Speed', info: '${AP.bulletSpeed} m/s'),
                    InfoLabel(title: 'Overmatch', info: overmatch),
                  ],
                ),
              ],
            ],
          ),
        ],
      ),
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
        Text(title, style: TextStyle(fontWeight: FontWeight.bold)),
        Text(info),
      ],
    );
  }
}

class Artillery {
  final double maxDispersion;
  final double gunRate;
  final double distance;
  final double rotationTime;
  final List<Slot> slots;
  final Shells shells;

  Artillery({
    required this.maxDispersion,
    required this.gunRate,
    required this.distance,
    required this.rotationTime,
    required this.slots,
    required this.shells,
  });
}

class Slot {
  final String guns;
  final String barrels;
  final String name;

  Slot({required this.guns, required this.barrels, required this.name});
}

class Shells {
  final HE? HE;
  final AP? AP;

  Shells({this.HE, this.AP});
}

class HE {
  final double burnProbability;
  final double bulletMass;
  final double damage;
  final double bulletSpeed;

  HE({
    required this.burnProbability,
    required this.bulletMass,
    required this.damage,
    required this.bulletSpeed,
  });
}

class AP {
  final double bulletMass;
  final double damage;
  final double bulletSpeed;

  AP({
    required this.bulletMass,
    required this.damage,
    required this.bulletSpeed,
  });
}


class SecondaryInfo extends StatelessWidget {
  final dynamic secondary;

  SecondaryInfo({this.secondary});

  @override
  Widget build(BuildContext context) {
    if (secondary == null) {
      return SizedBox.shrink();
    }
    final horizontal = Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
    );
    final centerText = TextStyle(textAlign: TextAlign.center);
    final distance = secondary['distance'];
    final slots = secondary['slots'];

    List<dynamic> guns = [];
    for (var gun in slots.keys) {
      guns.add(slots[gun]);
    }

    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('${lang.warship_artillery_secondary} (${distance} km)', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
          ...guns.map((value) {
            final burnProbability = value['burn_probability'];
            final bulletSpeed = value['bullet_speed'];
            final name = value['name'];
            final gunRate = value['gun_rate'];
            final damage = value['damage'];
            final type = value['type'];

            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('$type - $name', style: centerText),
                horizontal,
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    InfoLabel(
                      title: lang.warship_weapon_reload,
                      info: '${(60 / gunRate).toStringAsFixed(1)} s',
                    ),
                    InfoLabel(
                      title: lang.warship_weapon_speed,
                      info: '$bulletSpeed m/s',
                    ),
                    if (burnProbability != null) 
                      InfoLabel(
                        title: lang.warship_weapon_fire_chance,
                        info: '🔥$burnProbability%',
                      ),
                    InfoLabel(
                      title: lang.warship_weapon_damage,
                      info: damage.toString(),
                    ),
                  ],
                ),
              ],
            );
          }).toList(),
        ],
      ),
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
        Text(title, style: TextStyle(fontWeight: FontWeight.bold)),
        Text(info),
      ],
    );
  }
}


class AircraftInfo extends StatelessWidget {
  final Map<String, dynamic> profile;

  AircraftInfo({required this.profile});

  @override
  Widget build(BuildContext context) {
    return renderAircraft();
  }

  Widget renderAircraft() {
    final basicTextStyle = TextStyle(); // Define your text style here
    final flightControl = profile['flight_control'];

    if (flightControl != null) {
      final fighterSquadrons = flightControl['fighter_squadrons'];
      final torpedoSquadrons = flightControl['torpedo_squadrons'];
      final bomberSquadrons = flightControl['bomber_squadrons'];

      return Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            renderTitle('Aircraft Details'), // Replace with your localization
            renderFighter(),
            renderTorpedoBomber(),
            renderBomber(),
            Text(
              '$fighterSquadrons - $torpedoSquadrons - $bomberSquadrons',
              style: basicTextStyle,
            ),
          ],
        ),
      );
    } else {
      return SizedBox.shrink();
    }
  }

  Widget renderTitle(String title) {
    return Text(
      title,
      style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
    );
  }

  Widget renderFighter() {
    // Implement your fighter rendering logic here
    return Text('Fighter Squadron Info'); // Placeholder
  }

  Widget renderTorpedoBomber() {
    // Implement your torpedo bomber rendering logic here
    return Text('Torpedo Bomber Info'); // Placeholder
  }

  Widget renderBomber() {
    // Implement your bomber rendering logic here
    return Text('Bomber Squadron Info'); // Placeholder
  }
}


class FighterWidget extends StatelessWidget {
  final Map<String, dynamic> profile;
  final TextStyle basicTitleStyle;
  final String aircraftFighter;

  FighterWidget({required this.profile, required this.basicTitleStyle, required this.aircraftFighter});

  @override
  Widget build(BuildContext context) {
    final fighters = profile['fighters'];
    if (fighters != null) {
      return Container(
        margin: EdgeInsets.all(16.0),
        child: Text(
          aircraftFighter,
          style: basicTitleStyle,
        ),
      );
    } else {
      return SizedBox.shrink();
    }
  }
}


class YourWidget extends StatelessWidget {
  final Map<String, dynamic> profile;
  final TextStyle basicTitleStyle;
  final String aircraftTorpedoBomber;

  YourWidget({required this.profile, required this.basicTitleStyle, required this.aircraftTorpedoBomber});

  @override
  Widget build(BuildContext context) {
    final torpedoBomber = profile['torpedo_bomber'];
    if (torpedoBomber != null) {
      return Container(
        margin: EdgeInsets.all(16.0),
        child: Text(
          aircraftTorpedoBomber,
          style: basicTitleStyle,
        ),
      );
    } else {
      return SizedBox.shrink();
    }
  }
}


class MyWidget extends StatelessWidget {
  final Map<String, dynamic> profile;
  final TextStyle basicTitleStyle;

  MyWidget({required this.profile, required this.basicTitleStyle});

  @override
  Widget build(BuildContext context) {
    return renderBomber();
  }

  Widget renderBomber() {
    final diveBomber = profile['dive_bomber'];
    if (diveBomber != null) {
      return Container(
        margin: EdgeInsets.all(16.0),
        child: Text(
          'Aircraft Bomber', // Replace with your localization logic
          style: basicTitleStyle,
        ),
      );
    } else {
      return SizedBox.shrink();
    }
  }
}


class TorpedoInfo extends StatelessWidget {
  final Map<String, dynamic> torpedoes;
  final List<int> upgrades;

  TorpedoInfo({required this.torpedoes, required this.upgrades});

  @override
  Widget build(BuildContext context) {
    if (torpedoes.isEmpty) {
      return SizedBox.shrink();
    }

    final horizontal = BoxDecoration(
      display: BoxDisplay.flex,
      flexDirection: Axis.horizontal,
    );

    final centerText = TextStyle(
      textAlign: TextAlign.center,
    );

    final visibilityDist = torpedoes['visibility_dist'];
    final distance = torpedoes['distance'];
    final torpedoName = torpedoes['torpedo_name'];
    final reloadTime = torpedoes['reload_time'];
    final torpedoSpeed = torpedoes['torpedo_speed'];
    final slots = torpedoes['slots'];
    final maxDamage = torpedoes['max_damage'];

    String dist = distance.toStringAsFixed(1);
    String torps = '';
    for (var torp in slots) {
      torps += '${torp['guns']} x ${torp['barrels']}  ';
    }
    String reactionTime = ((visibilityDist * 1000) / 2.6 / torpedoSpeed).toStringAsFixed(1);

    // With Torpedo acceleration
    String shortDist = (distance * 0.8).toStringAsFixed(1);
    String fastestSpeed = ((torpedoSpeed + 5) * 1.05).toStringAsFixed(1);
    String reactionTimeP = ((visibilityDist * 1000) / 2.6 / double.parse(fastestSpeed)).toStringAsFixed(1);

    // check for torpedo upgrade
    double modifier = upgrades.contains(4279422896) ? 0.85 : 1;
    String minReload = (reloadTime * 0.9 * modifier).toStringAsFixed(1);

    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('Warship Torpedoes', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: 'Weapon Reload', info: '$reloadTime - $minReload s'),
              InfoLabel(title: 'Weapon Range', info: '$dist - $shortDist km'),
              InfoLabel(title: 'Weapon Configuration', info: torps),
            ],
          ),
          Text('$torpedoName ($reactionTime - $reactionTimeP s)', style: centerText),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(title: 'Torpedoes Visible Distance', info: '$visibilityDist km'),
              InfoLabel(title: 'Weapon Damage', info: '$maxDamage'),
              InfoLabel(title: 'Weapon Speed', info: '$torpedoSpeed - $fastestSpeed kt'),
            ],
          ),
        ],
      ),
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
        Text(title, style: TextStyle(fontWeight: FontWeight.bold)),
        Text(info),
      ],
    );
  }
}


class AADefense extends StatelessWidget {
  final AntiAircraft antiAircraft;

  AADefense({required this.antiAircraft});

  @override
  Widget build(BuildContext context) {
    if (antiAircraft == null) {
      return SizedBox.shrink();
    }

    final horizontal = BoxDecoration(
      display: BoxDisplay.flex,
      flexDirection: Axis.horizontal,
    );

    final centerText = TextStyle(
      textAlign: TextAlign.center,
    );

    var aaValues = antiAircraft.slots.values.toList();

    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SectionTitle(title: lang.warship_antiaircraft),
          ...aaValues.map((value) {
            final avgDamage = value.avgDamage;
            final name = value.name;
            final guns = value.guns;

            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(name, style: centerText),
                Container(
                  decoration: horizontal,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                      InfoLabel(
                        title: lang.warship_weapon_configuration,
                        info: '${guns}x',
                      ),
                      // InfoLabel(
                      //   title: lang.warship_weapon_range,
                      //   info: '${distance} km',
                      // ),
                      InfoLabel(
                        title: lang.warship_weapon_damage,
                        info: '${avgDamage} dps',
                      ),
                    ],
                  ),
                ),
              ],
            );
          }).toList(),
        ],
      ),
    );
  }
}

class AntiAircraft {
  final Map<String, AAValue> slots;

  AntiAircraft({required this.slots});
}

class AAValue {
  final String name;
  final int guns;
  final int avgDamage;

  AAValue({required this.name, required this.guns, required this.avgDamage});
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Text(title, style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold));
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
        Text(title),
        Text(info),
      ],
    );
  }
}


class MobilityWidget extends StatelessWidget {
  final Map<String, dynamic> mobility;
  final List<int> upgrades;

  MobilityWidget({required this.mobility, required this.upgrades});

  @override
  Widget build(BuildContext context) {
    if (mobility.isEmpty) {
      return SizedBox.shrink();
    }

    final horizontal = BoxDecoration(
      display: BoxDisplay.flex,
      flexDirection: Axis.horizontal,
    );

    final rudderTime = mobility['rudder_time'];
    final turningRadius = mobility['turning_radius'];
    final maxSpeed = mobility['max_speed'];

    final speedFlag = (maxSpeed * 1.05).toStringAsFixed(0);

    // Best stat with upgrades
    final m1 = upgrades.contains(4267888560) ? 0.8 : 1;
    final m2 = upgrades.contains(4257402800) ? 0.6 : 1;
    final modifier = m1 + m2 - 1;
    final maxRudder = (rudderTime * modifier).toStringAsFixed(1);
    String rudderMsg = '$rudderTime - $maxRudder s';
    if (maxRudder == rudderTime.toString()) {
      rudderMsg = '$rudderTime s';
    }

    return Container(
      margin: EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'Warship Maneuverability',
            style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(
                title: 'Rudder Time',
                info: rudderMsg,
              ),
              InfoLabel(
                title: 'Speed',
                info: '$maxSpeed - $speedFlag kt',
              ),
              InfoLabel(
                title: 'Turning Radius',
                info: '$turningRadius m',
              ),
            ],
          ),
        ],
      ),
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
        Text(
          title,
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
        ),
        Text(
          info,
          style: TextStyle(fontSize: 14),
        ),
      ],
    );
  }
}


class ConcealmentWidget extends StatelessWidget {
  final Map<String, dynamic> concealment;
  final List<int> upgrades;

  ConcealmentWidget({required this.concealment, required this.upgrades});

  @override
  Widget build(BuildContext context) {
    if (concealment.isEmpty) {
      return SizedBox.shrink();
    }

    final horizontal = BoxDecoration(
      display: BoxDisplay.flex,
      flexDirection: Axis.horizontal,
    );

    final detectDistanceByPlane = concealment['detect_distance_by_plane'];
    final detectDistanceByShip = concealment['detect_distance_by_ship'];

    // Check if ship has concealment module
    final modifier = upgrades.contains(4265791408) ? 0.9 : 1;
    // Premium ship already included it
    final camouflage = 0.97;
    final deduction = 0.9 * modifier * camouflage;
    final maxShipConcealment = (detectDistanceByShip * deduction).toStringAsFixed(1);
    final maxPlaneConcealment = (detectDistanceByPlane * deduction).toStringAsFixed(1);

    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'Warship Concealment',
            style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              InfoLabel(
                title: 'Detected by Plane',
                info: '$detectDistanceByPlane - $maxPlaneConcealment km',
              ),
              InfoLabel(
                title: 'Detected by Ship',
                info: '$detectDistanceByShip - $maxShipConcealment km',
              ),
            ],
          ),
        ],
      ),
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
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          title,
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
        ),
        Text(
          info,
          style: TextStyle(fontSize: 14),
        ),
      ],
    );
  }
}


class UpgradeList extends StatelessWidget {
  final dynamic curr;

  UpgradeList({required this.curr});

  List<dynamic> guard(dynamic obj, String key, dynamic defaultValue) {
    return obj[key] ?? defaultValue;
  }

  List<dynamic> copy(List<dynamic> list) {
    return List.from(list);
  }

  @override
  Widget build(BuildContext context) {
    if (curr == null) {
      return SizedBox.shrink();
    }
    List<dynamic> upgrades = guard(curr, 'upgrades', null);
    int slots = guard(curr, 'mod_slots', null);
    if (upgrades == null || slots == null) {
      return SizedBox.shrink();
    }

    List<dynamic> clone = copy(upgrades);
    clone.sort((a, b) => b.compareTo(a));
    for (int index = 0; index < clone.length; index++) {
      var id = clone[index];
      clone[index] = AppGlobalData.get(SAVED.consumable)[id];
    }

    List<int> count = List.generate(slots, (index) => index);

    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            lang.warship_upgrades,
            style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
          ),
          SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: count.map((num) {
                List<dynamic> all = clone.where((u) => u['slot'] == num + 1).toList();
                return Container(
                  margin: const EdgeInsets.symmetric(horizontal: 8.0),
                  child: Column(
                    children: [
                      Text('${num + 1}.', style: TextStyle(fontSize: 16)),
                      ...all.map((item) {
                        return GestureDetector(
                          onTap: () => SafeAction('BasicDetail', {'item': item}),
                          child: WikiIcon(item: item, scale: 0.8),
                        );
                      }).toList(),
                    ],
                  ),
                );
              }).toList(),
            ),
          ),
        ],
      ),
    );
  }
}


class WarshipNextShip extends StatelessWidget {
  final Map<String, dynamic> nextShips;

  WarshipNextShip({required this.nextShips});

  @override
  Widget build(BuildContext context) {
    if (nextShips.isEmpty) {
      return SizedBox.shrink();
    }

    print(nextShips);
    // Get all ships from next_ships
    List<Map<String, dynamic>> ships = [];
    nextShips.forEach((key, value) {
      ships.add({'key': key, 'exp': value});
    });

    return Container(
      margin: EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'Next Ship', // Replace with your localized string
            style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 10),
          Container(
            height: 150, // Set a fixed height for horizontal list
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: ships.length,
              itemBuilder: (context, index) {
                var curr = AppGlobalData.get(SAVED.warship)[ships[index]['key']];
                return GestureDetector(
                  onTap: () {
                    // Pop and go to another ships
                    Navigator.pop(context);
                    SafeAction('WarshipDetail', {'item': curr}, 1);
                  },
                  child: WarshipCell(
                    scale: 1.4,
                    item: curr,
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}


class SimilarShipsWidget extends StatelessWidget {
  final List<dynamic> similar;
  final dynamic compare;
  final Function(dynamic) onShipPress;
  final Function(dynamic) onComparePress;

  SimilarShipsWidget({required this.similar, this.compare, required this.onShipPress, required this.onComparePress});

  @override
  Widget build(BuildContext context) {
    if (similar.isNotEmpty) {
      return FooterPlus(
        child: Column(
          children: [
            Container(
              height: 100, // Adjust height as needed
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: similar.length,
                itemBuilder: (context, index) {
                  final item = similar[index];
                  return WarshipCell(
                    item: item,
                    scale: 1.4,
                    onPress: () {
                      onShipPress(item['ship_id']);
                    },
                  );
                },
              ),
            ),
            if (compare != null)
              ElevatedButton(
                onPressed: () => onComparePress(compare),
                child: Text('Compare Similar Ships'), // Replace with actual text
              ),
          ],
        ),
      );
    } else {
      return SizedBox.shrink();
    }
  }
}

class FooterPlus extends StatelessWidget {
  final Widget child;

  FooterPlus({required this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16.0),
      child: child,
    );
  }
}

class WarshipCell extends StatelessWidget {
  final dynamic item;
  final double scale;
  final Function onPress;

  WarshipCell({required this.item, required this.scale, required this.onPress});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => onPress(),
      child: Container(
        width: 100 * scale, // Adjust width as needed
        child: Column(
          children: [
            // Replace with actual widget to display ship information
            Text(item['name']),
          ],
        ),
      ),
    );
  }
}


class YourWidget extends StatefulWidget {
  @override
  _YourWidgetState createState() => _YourWidgetState();
}

class _YourWidgetState extends State<YourWidget> {
  List<Widget> compare = [];

  void buildCharts(List<Ship> similar) {
    List<charts.Series<ChartData, String>> damageChart = [];
    List<charts.Series<ChartData, String>> winrateChart = [];
    List<charts.Series<ChartData, String>> fragChart = [];

    for (var ship in similar) {
      var overall = AppGlobalData.get(SAVED.pr)[ship.ship_id];
      if (overall == null) {
        continue;
      }

      final averageDamageDealt = roundTo(overall.average_damage_dealt);
      final averageFrags = roundTo(overall.average_frags);
      final winRate = roundTo(overall.win_rate, 1);
      final name = ship.name;

      damageChart.add(charts.Series<ChartData, String>(
        id: 'Average Damage',
        domainFn: (ChartData data, _) => data.name,
        measureFn: (ChartData data, _) => data.value,
        data: [ChartData(name, averageDamageDealt)],
      ));

      winrateChart.add(charts.Series<ChartData, String>(
        id: 'Win Rate',
        domainFn: (ChartData data, _) => data.name,
        measureFn: (ChartData data, _) => data.value,
        data: [ChartData(name, winRate)],
      ));

      fragChart.add(charts.Series<ChartData, String>(
        id: 'Average Frags',
        domainFn: (ChartData data, _) => data.name,
        measureFn: (ChartData data, _) => data.value,
        data: [ChartData(name, averageFrags)],
      ));
    }

    compare = [
      buildChartWidget(damageChart, lang.warship_avg_damage, '#2387FF'),
      buildChartWidget(winrateChart, lang.warship_avg_winrate, '#4DA74D'),
      buildChartWidget(fragChart, lang.warship_avg_frag, '#C94A4D'),
    ];
  }

  Widget buildChartWidget(List<charts.Series<ChartData, String>> chartData, String title, String color) {
    return Column(
      children: [
        Text(title, textAlign: TextAlign.center, style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
        Container(
          height: chartData.length * 20.0,
          child: charts.BarChart(
            chartData,
            animate: true,
            barRendererDecorator: charts.BarLabelDecorator<String>(),
            domainAxis: charts.OrdinalAxisSpec(),
            primaryMeasureAxis: charts.NumericAxisSpec(),
            defaultRenderer: charts.BarRendererConfig<String>(
              fillColor: charts.ColorUtil.fromDartColor(Color(int.parse(color.replaceFirst('#', '0xff')))),
            ),
          ),
        ),
      ],
    );
  }

  double roundTo(double value, [int decimals = 0]) {
    final factor = pow(10, decimals);
    return (value * factor).round() / factor;
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: compare);
  }
}

class ChartData {
  final String name;
  final double value;

  ChartData(this.name, this.value);
}

class Ship {
  final String ship_id;
  final String name;

  Ship(this.ship_id, this.name);
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  Map<String, dynamic> data = {};
  List<dynamic> upgrades = [];
  bool loading = true;
  Timer? delayedRequest;

  @override
  void dispose() {
    delayedRequest?.cancel();
    super.dispose();
  }

  void efficientDataRequest(String id) {
    delayedRequest?.cancel();
    delayedRequest = Timer(Duration(seconds: 1), () async {
      final response = await SafeFetch.get(WoWsAPI.ShipWiki, server, id, langStr());
      if (response.statusCode == 200) {
        final json = jsonDecode(response.body);
        final data = Guard(json, 'data', {});
        print(data);
        upgrades = Guard(data[id], 'upgrades', []);
        setState(() {
          this.data = data[id];
          loading = false;
        });
      } else {
        // Handle error
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return loading
        ? CircularProgressIndicator()
        : // Your widget tree here
  }
}

class SafeFetch {
  static Future<http.Response> get(String api, String server, String id, String lang) {
    // Implement your fetch logic here
  }
}

class Guard {
  static dynamic call(Map<String, dynamic> json, String key, dynamic defaultValue) {
    return json.containsKey(key) ? json[key] : defaultValue;
  }
}

class WoWsAPI {
  static const String ShipWiki = 'your_api_endpoint_here';
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: Container(
            alignment: Alignment.center,
            child: Text('Hello, Flutter!'),
          ),
        ),
      ),
    );
  }
}


class ShipTitleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text(
      'Ship Title',
      style: TextStyle(
        fontSize: 24,
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text('Ship Title Example'),
      ),
      body: Center(
        child: ShipTitleWidget(),
      ),
    ),
  ));
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // Add your onPressed logic here
          },
          child: Text('Model Button'),
          style: ElevatedButton.styleFrom(
            margin: EdgeInsets.only(top: 8),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MyWidget(),
  ));
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(8),
      child: Text('Hello, Flutter!'),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Flutter App')),
      body: MyWidget(),
    ),
  ));
}


class CenterTextWidget extends StatelessWidget {
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

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text('Center Text Example'),
      ),
      body: CenterTextWidget(),
    ),
  ));
}


class WeaponTitle extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      margin: EdgeInsets.only(top: -16, bottom: -16),
      child: Text(
        'Weapon Title',
        textAlign: TextAlign.center,
      ),
    );
  }
}


class UpgradeView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          // Add your content here
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      body: UpgradeView(),
    ),
  ));
}


class MyHorizontalLayout extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          // Add your widgets here
          Container(color: Colors.red, width: 50, height: 50),
          Container(color: Colors.green, width: 50, height: 50),
          Container(color: Colors.blue, width: 50, height: 50),
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


class GraphTitle extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      padding: EdgeInsets.only(top: 8, bottom: 0),
      child: Text(
        'Graph Title',
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
    );
  }
}


class WarshipDetail extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Detail'),
      ),
      body: Center(
        child: Text('Details of the Warship will be displayed here.'),
      ),
    );
  }
}