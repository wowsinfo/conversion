
class CommanderSkill extends StatefulWidget {
  @override
  _CommanderSkillState createState() => _CommanderSkillState();
}

class _CommanderSkillState extends State<CommanderSkill> {
  List<Map<String, dynamic>> data = [];
  int point = 19;

  @override
  void initState() {
    super.initState();
    setLastLocation('CommanderSkill');
    print('WIKI - Commander Skill');
    var skill = AppGlobalData.get(SAVED.commanderSkill);
    var cloned = List.from(skill);

    List<Map<String, dynamic>> section = [];
    for (var i in cloned) {
      int index = i['tier'] - 1;
      if (section.length <= index) {
        section.add({'title': '${lang.wiki_skills_tier} ${i['tier']}', 'data': []});
      }
      section[index]['data'].add(Map.from(i));
    }

    setState(() {
      data = section;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Commander Skills'),
      ),
      body: ListView.builder(
        itemCount: data.length,
        itemBuilder: (context, index) {
          return ExpansionTile(
            title: Text(data[index]['title']),
            children: (data[index]['data'] as List).map<Widget>((skill) {
              return ListTile(
                title: Text(skill['name']),
                subtitle: Text(skill['description']),
              );
            }).toList(),
          );
        },
      ),
    );
  }
}


class WoWsInfo extends StatelessWidget {
  final String title;
  final VoidCallback onPress;
  final Widget child;

  const WoWsInfo({required this.title, required this.onPress, required this.child});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPress,
      child: Column(
        children: [
          Text(title),
          child,
        ],
      ),
    );
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  const SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Text(title, style: TextStyle(fontWeight: FontWeight.bold));
  }
}

class WikiIcon extends StatelessWidget {
  final dynamic item;
  final bool selected;
  final VoidCallback onPress;
  final VoidCallback onLongPress;

  const WikiIcon({required this.item, required this.selected, required this.onPress, required this.onLongPress});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPress,
      onLongPress: onLongPress,
      child: Container(
        width: 80,
        height: 80,
        color: selected ? Colors.blue : Colors.grey,
        child: Center(child: Text(item['name'])),
      ),
    );
  }
}

class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<Map<String, dynamic>> data = []; // Populate with your data
  int point = 0; // Initialize your point
  String lang = 'en'; // Replace with your language logic

  void reset() {
    // Implement your reset logic here
  }

  void skillSelected(dynamic item) {
    // Implement your skill selection logic here
  }

  void safeAction(String action, {dynamic item}) {
    // Implement your safe action logic here
  }

  @override
  Widget build(BuildContext context) {
    return WoWsInfo(
      title: '$point ${lang.wiki_skills_point}',
      onPress: reset,
      child: SectionGrid(
        itemDimension: 80,
        sections: data,
        renderItem: (item) {
          return WikiIcon(
            item: item,
            selected: item['selected'],
            onPress: () => skillSelected(item),
            onLongPress: () => safeAction('BasicDetail', item: item),
          );
        },
        renderSectionHeader: (section) {
          return SectionTitle(title: section['title']);
        },
      ),
    );
  }
}

class SectionGrid extends StatelessWidget {
  final double itemDimension;
  final List<Map<String, dynamic>> sections;
  final Function(Map<String, dynamic>) renderItem;
  final Function(Map<String, dynamic>) renderSectionHeader;

  const SectionGrid({
    required this.itemDimension,
    required this.sections,
    required this.renderItem,
    required this.renderSectionHeader,
  });

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: sections.length,
      itemBuilder: (context, index) {
        final section = sections[index];
        return Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            renderSectionHeader(section),
            GridView.builder(
              shrinkWrap: true,
              physics: NeverScrollableScrollPhysics(),
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: (MediaQuery.of(context).size.width / itemDimension).floor(),
              ),
              itemCount: section['items'].length,
              itemBuilder: (context, itemIndex) {
                return renderItem(section['items'][itemIndex]);
              },
            ),
          ],
        );
      },
    );
  }
}


class SkillSelector extends StatefulWidget {
  @override
  _SkillSelectorState createState() => _SkillSelectorState();
}

class _SkillSelectorState extends State<SkillSelector> {
  int point = 10; // Initial points
  final String langWikiSkillsReset = 'Reset'; // Example language string

  void skillSelected(SkillItem item) {
    int pointLeft = point;
    if (item.selected) {
      if (pointLeft == langWikiSkillsReset) {
        pointLeft = 0;
      }
      pointLeft += item.tier;
      item.selected = false;
      setState(() {
        point = pointLeft;
      });
    } else {
      pointLeft -= item.tier;
      if (pointLeft >= 0) {
        item.selected = true;
        if (pointLeft == 0) {
          setState(() {
            point = langWikiSkillsReset;
          });
        } else {
          setState(() {
            point = pointLeft;
          });
        }
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    // Build your UI here
    return Container();
  }
}

class SkillItem {
  bool selected;
  int tier;

  SkillItem({required this.selected, required this.tier});
}

  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<Map<String, dynamic>> data = []; // Initialize your data structure here
  int point = 19;

  void reset() {
    for (var i in data) {
      for (var j in i['data']) {
        j.remove('selected');
      }
    }
    setState(() {
      point = 19;
      // data is already updated in the loop
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class CommanderSkill extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Commander Skill'),
      ),
      body: Center(
        child: Text('This is the Commander Skill screen.'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: CommanderSkill(),
  ));
}