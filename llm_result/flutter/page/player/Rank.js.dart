
class Rank extends StatefulWidget {
  final Map<String, dynamic> data;
  final dynamic ship;

  Rank({Key? key, required this.data, required this.ship}) : super(key: key);

  @override
  _RankState createState() => _RankState();
}

class _RankState extends State<Rank> {
  late List<dynamic> data;
  late dynamic ship;

  @override
  void initState() {
    super.initState();
    data = [];
    ship = widget.ship;

    for (var key in widget.data.keys) {
      var curr = widget.data[key];
      curr['season'] = int.parse(key);
      data.add(curr);
    }
    data.sort((a, b) => b['season'].compareTo(a['season']));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Rank'),
      ),
      body: SafeArea(
        child: GridView.builder(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            childAspectRatio: 1,
          ),
          itemCount: data.length,
          itemBuilder: (context, index) {
            return GestureDetector(
              onTap: () {
                // Handle tap
              },
              child: Card(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(Icons.info), // Replace with Info6Icon
                    Text('Season: ${data[index]['season']}'),
                    // Add more data display as needed
                  ],
                ),
              ),
            );
          },
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
  List<dynamic> data;
  Map<String, dynamic> ship;

  @override
  Widget build(BuildContext context) {
    if (data == null || data.isEmpty) {
      return Container();
    }

    print(data);
    return WoWsInfo(title: '${lang.tab_rank_title} - ${data.length}', child: 
      GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 1,
          childAspectRatio: 300 / 200,
          mainAxisSpacing: 0,
          crossAxisSpacing: 8,
        ),
        itemCount: data.length,
        itemBuilder: (context, index) {
          final item = data[index];
          final season = item['season'];
          final shipData = ship[season];
          print(shipData);

          return GestureDetector(
            onTap: shipData == null || shipData.isEmpty
                ? null
                : () => SafeAction('PlayerShip', {'data': shipData}),
            child: Container(
              margin: EdgeInsets.all(8),
              child: Column(
                children: [
                  Text(
                    '- ${lang.rank_season_title} $season -',
                    textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  // Text(
                  //   '$emoji $rank $emoji',
                  //   textAlign: TextAlign.center,
                  // ),
                  renderSeasonInfo(item),
                ],
              ),
            ),
          );
        },
      ),
    );
  }

  Widget renderSeasonInfo(dynamic item) {
    // Implement your season info rendering logic here
    return Container(); // Replace with actual implementation
  }
}

class WoWsInfo extends StatelessWidget {
  final String title;
  final Widget child;

  WoWsInfo({required this.title, required this.child});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(title, style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold)),
        child,
      ],
    );
  }
}

void SafeAction(String action, Map<String, dynamic> data) {
  // Implement your SafeAction logic here
}


class SeasonInfoWidget extends StatelessWidget {
  final Map<String, dynamic>? data;

  SeasonInfoWidget({this.data});

  @override
  Widget build(BuildContext context) {
    if (data == null) {
      return SizedBox.shrink();
    }

    // find the other key which is not season
    final rankKey = data.keys.firstWhere((key) => key != 'season', orElse: () => null);
    if (rankKey == null) {
      return SizedBox.shrink();
    }

    final rankData = data[rankKey];
    final rankSolo = rankData['rank_solo'];
    final rankDiv2 = rankData['rank_div2'];
    final rankDiv3 = rankData['rank_div3'];

    var info = rankSolo ?? rankDiv2 ?? rankDiv3;
    if (info == null) {
      return SizedBox.shrink();
    }

    return Info6Icon(data: info, compact: true);
  }
}

class Info6Icon extends StatelessWidget {
  final dynamic data;
  final bool compact;

  Info6Icon({required this.data, required this.compact});

  @override
  Widget build(BuildContext context) {
    // Implement your icon rendering logic here
    return Container(); // Placeholder for actual icon rendering
  }
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          padding: EdgeInsets.only(bottom: 8),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              // Add your content here
            ],
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
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
      appBar: AppBar(title: Text('Center Text Example')),
      body: CenterTextWidget(),
    ),
  ));
}


class Rank extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Rank'),
      ),
      body: Center(
        child: Text('Rank Component'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Rank(),
  ));
}