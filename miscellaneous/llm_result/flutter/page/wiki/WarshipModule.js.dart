
class WarshipModule extends StatefulWidget {
  final Map<String, dynamic> data;

  WarshipModule({Key? key, required this.data}) : super(key: key);

  @override
  _WarshipModuleState createState() => _WarshipModuleState();
}

class _WarshipModuleState extends State<WarshipModule> {
  late String shipId;
  late Map<String, String> module;
  late List<dynamic> tree;
  late List<Section> section;

  @override
  void initState() {
    super.initState();
    shipId = widget.data['ship_id'];
    module = {
      'Artillery': '',
      'DiveBomber': '',
      'Engine': '',
      'Fighter': '',
      'FlightControl': '',
      'Hull': '',
      'Suo': '',
      'TorpedoBomber': '',
      'Torpedoes': '',
    };
    tree = widget.data['modules_tree'];
    section = makeSection(widget.data);
  }

  List<Section> makeSection(Map<String, dynamic> data) {
    // Implement your logic to create sections based on the data
    return [];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Module'),
      ),
      body: ListView.builder(
        itemCount: section.length,
        itemBuilder: (context, index) {
          return SectionTitle(section: section[index]);
        },
      ),
    );
  }
}

class Section {
  // Define your Section class based on your requirements
}

class SectionTitle extends StatelessWidget {
  final Section section;

  SectionTitle({required this.section});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(8.0),
      child: Text(
        'Section Title', // Replace with actual section title
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
    );
  }
}


class WoWsInfo extends StatelessWidget {
  final bool hideAds;
  final String title;
  final Function onPress;
  final Widget child;

  WoWsInfo({this.hideAds, this.title, this.onPress, this.child});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
        actions: [
          IconButton(
            icon: Icon(Icons.check),
            onPressed: onPress,
          ),
        ],
      ),
      body: child,
    );
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({this.title});

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

class Space extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(height: 20);
  }
}

class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<Map<String, dynamic>> section = [
    {
      'title': 'Section 1',
      'data': ['Module 1', 'Module 2']
    },
    {
      'title': 'Section 2',
      'data': ['Module 3', 'Module 4']
    },
  ];

  void apply() {
    // Implement your apply logic here
  }

  Widget renderModule(String module) {
    return ListTile(
      title: Text(module),
    );
  }

  @override
  Widget build(BuildContext context) {
    return WoWsInfo(
      hideAds: true,
      title: 'Warship Apply Module',
      onPress: apply,
      child: ListView.builder(
        reverse: true,
        itemCount: section.length,
        itemBuilder: (context, index) {
          final item = section[index];
          return Column(
            key: ValueKey(item['title']),
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SectionTitle(title: item['title']),
              ...item['data'].map<Widget>((d) => renderModule(d)).toList(),
            ],
          );
        },
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  Map<String, dynamic> state = {};

  void apply() {
    Navigator.of(context).popUntil((route) => route.settings.name == 'WarshipDetail');
    Future.delayed(Duration(milliseconds: 100), () {
      Navigator.of(context).pushReplacementNamed('WarshipDetail', arguments: state);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class ModuleRenderer extends StatefulWidget {
  @override
  _ModuleRendererState createState() => _ModuleRendererState();
}

class _ModuleRendererState extends State<ModuleRenderer> {
  Map<String, dynamic> tree = {}; // Populate with your data
  Map<String, String> module = {}; // Populate with your data
  TextStyle xp = TextStyle(); // Define your text style
  Color themeBackColor = Colors.grey; // Define your theme color

  void updateModule(Map<String, dynamic> tree, String id) {
    // Implement your update logic here
  }

  Widget renderModule(String id) {
    final moduleData = tree[id];
    final String name = moduleData['name'];
    final int priceXp = moduleData['price_xp'];
    final int priceCredit = moduleData['price_credit'];

    bool selected = module.values.contains(id);

    Widget right() {
      if (priceXp > 0) {
        return Text('$priceXp xp', style: xp);
      } else {
        return SizedBox.shrink();
      }
    }

    return ListTile(
      key: ValueKey(id),
      tileColor: selected ? themeBackColor : null,
      title: Text(name),
      subtitle: Text('$priceCredit'),
      onTap: () => updateModule(tree, id),
      trailing: right(),
    );
  }

  @override
  Widget build(BuildContext context) {
    // Example usage of renderModule
    return Scaffold(
      appBar: AppBar(title: Text('Module Renderer')),
      body: ListView(
        children: tree.keys.map((id) => renderModule(id)).toList(),
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  Map<String, String> module = {};

  void updateModule(List<Map<String, dynamic>> tree, String ID) {
    setState(() {
      module[tree[ID]['type']] = ID;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class ModuleSection {
  final Map<String, List<String>> modules;
  final Map<String, ModuleData> modulesTree;
  final String moduleName;

  ModuleSection(this.modules, this.modulesTree, this.moduleName);

  List<Map<String, dynamic>> makeSection() {
    List<Map<String, dynamic>> section = [];
    modules.forEach((key, curr) {
      if (curr.length > 1) {
        // Ignore empty or one module, you cannot update them anyway
        curr.sort((a, b) {
          ModuleData aM = modulesTree[a]!;
          ModuleData bM = modulesTree[b]!;

          if (aM.priceXp != bM.priceXp) {
            // Sort by XP (more xp, more advanced)
            return aM.priceXp.compareTo(bM.priceXp);
          } else if (aM.nextModules != null && bM.nextModules != null) {
            // They all have next module, we need to check the id
            return aM.nextModules![0] == b ? -1 : 1;
          } else {
            // Whoever is not null comes first
            return aM.nextModules != null ? -1 : 1;
          }
        });

        Map<String, dynamic> obj = {
          'title': moduleName,
          'data': curr,
        };
        section.add(obj);
      }
    });
    return section;
  }
}

class ModuleData {
  final int priceXp;
  final List<String>? nextModules;

  ModuleData(this.priceXp, this.nextModules);
}

  List<String> names = key.split('_');
  String upperFirst(String str) => str[0].toUpperCase() + str.substring(1);
  names = names.map((n) => upperFirst(n)).toList();

  String name = names.join('');
  if (name == 'FireControl') {
    name = 'Suo';
  }
  return name;
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

  padding: EdgeInsets.only(right: 4),
  alignment: Alignment.center,
)


class WarshipModule extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Module'),
      ),
      body: Center(
        child: Text('Welcome to the Warship Module!'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: WarshipModule(),
  ));
}