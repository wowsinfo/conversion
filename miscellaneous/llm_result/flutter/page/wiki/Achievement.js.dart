
class Achievement extends StatefulWidget {
  @override
  _AchievementState createState() => _AchievementState();
}

class _AchievementState extends State<Achievement> {
  List<MapEntry<String, dynamic>> sorted = [];

  @override
  void initState() {
    super.initState();
    setLastLocation('Achievement');
    print('WIKI - Achievement');
    var achievement = AppGlobalData.get(SAVED.achievement);
    sorted = achievement.entries.toList()
      ..sort((a, b) {
        if (a.value['hidden'] == b.value['hidden']) {
          return a.key.compareTo(b.key);
        } else {
          return a.value['hidden'] ? 1 : -1;
        }
      });

    sorted = sorted.map((item) => MapEntry(item.key, Map.from(item.value))).toList();
    print(sorted);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Achievements'),
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          childAspectRatio: 1,
        ),
        itemCount: sorted.length,
        itemBuilder: (context, index) {
          var achievement = sorted[index].value;
          return Card(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                WikiIcon(achievement['icon']),
                Text(achievement['name']),
              ],
            ),
          );
        },
      ),
    );
  }
}


class WoWsInfo extends StatelessWidget {
  final Widget child;

  WoWsInfo({required this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: child,
    );
  }
}

class WikiIcon extends StatelessWidget {
  final dynamic item;
  final VoidCallback onPress;

  WikiIcon({required this.item, required this.onPress});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPress,
      child: Container(
        width: 80,
        height: 80,
        child: Center(child: Text(item.toString())), // Replace with actual icon representation
      ),
    );
  }
}

class MyGridView extends StatefulWidget {
  @override
  _MyGridViewState createState() => _MyGridViewState();
}

class _MyGridViewState extends State<MyGridView> {
  List<dynamic> data = []; // Initialize with your data

  void safeAction(String route, {dynamic item}) {
    // Implement your navigation logic here
  }

  @override
  Widget build(BuildContext context) {
    return WoWsInfo(
      child: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 4,
          childAspectRatio: 1,
        ),
        itemCount: data.length,
        itemBuilder: (context, index) {
          final item = data[index];
          return WikiIcon(
            item: item,
            onPress: () => safeAction('BasicDetail', item: item),
          );
        },
      ),
    );
  }
}


class Achievement extends StatelessWidget {
  final String title;
  final String description;

  Achievement({required this.title, required this.description});

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 4.0,
      margin: EdgeInsets.all(10.0),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: TextStyle(
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
            SizedBox(height: 8.0),
            Text(
              description,
              style: TextStyle(
                fontSize: 16.0,
                color: Colors.grey[600],
              ),
            ),
          ],
        ),
      ),
    );
  }
}