
class PlayerAchievement extends StatefulWidget {
  final Map<String, dynamic> data;

  PlayerAchievement({required this.data});

  @override
  _PlayerAchievementState createState() => _PlayerAchievementState();
}

class _PlayerAchievementState extends State<PlayerAchievement> {
  List<Map<String, dynamic>> displayData = [];

  @override
  void initState() {
    super.initState();
    _loadAchievements();
  }

  void _loadAchievements() {
    var saved = AppGlobalData.get(SAVED.achievement);
    List<Map<String, dynamic>> formatted = [];

    widget.data.forEach((key, value) {
      var obj = saved[key];
      if (obj != null) {
        formatted.add({'data': obj, 'num': value});
      }
    });

    formatted.sort((a, b) => b['num'].compareTo(a['num']));
    setState(() {
      displayData = formatted;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Player Achievements'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: StaggeredGridView.countBuilder(
          crossAxisCount: 2,
          itemCount: displayData.length,
          itemBuilder: (BuildContext context, int index) {
            return Card(
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(displayData[index]['data'].toString(), style: TextStyle(fontSize: 18)),
                    SizedBox(height: 8),
                    Text('Number: ${displayData[index]['num']}', style: TextStyle(fontSize: 14)),
                  ],
                ),
              ),
            );
          },
          staggeredTileBuilder: (int index) => StaggeredTile.fit(1),
          mainAxisSpacing: 8.0,
          crossAxisSpacing: 8.0,
        ),
      ),
    );
  }
}


class AchievementScreen extends StatelessWidget {
  final List<AchievementData> displayData;
  final String title;

  AchievementScreen({required this.displayData, required this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('$title - ${displayData.length}'),
      ),
      body: FadeInAnimation(
        child: StaggeredGridView.countBuilder(
          crossAxisCount: 4,
          itemCount: displayData.length,
          itemBuilder: (BuildContext context, int index) {
            final item = displayData[index];
            return GestureDetector(
              onTap: () {
                Navigator.pushNamed(context, 'BasicDetail', arguments: item.data);
              },
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  WikiIcon(item: item.data),
                  Text(
                    item.num.toString(),
                    style: TextStyle(fontSize: 16),
                  ),
                ],
              ),
            );
          },
          staggeredTileBuilder: (int index) => StaggeredTile.fit(1),
          mainAxisSpacing: 4.0,
          crossAxisSpacing: 4.0,
        ),
      ),
    );
  }
}

class AchievementData {
  final dynamic data;
  final int num;

  AchievementData({required this.data, required this.num});
}

class WikiIcon extends StatelessWidget {
  final dynamic item;

  WikiIcon({required this.item});

  @override
  Widget build(BuildContext context) {
    // Implement your icon rendering logic here
    return Container(
      // Placeholder for the icon
      width: 50,
      height: 50,
      color: Colors.blue,
    );
  }
}

class FadeInAnimation extends StatelessWidget {
  final Widget child;

  FadeInAnimation({required this.child});

  @override
  Widget build(BuildContext context) {
    return AnimatedOpacity(
      opacity: 1.0,
      duration: Duration(milliseconds: 500),
      child: child,
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

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text('Flutter App'),
      ),
      body: MyWidget(),
    ),
  ));
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Home Page'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}