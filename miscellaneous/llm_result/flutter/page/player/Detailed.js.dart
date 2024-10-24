
class Detailed extends StatefulWidget {
  final dynamic data;

  Detailed({Key? key, required this.data}) : super(key: key);

  @override
  _DetailedState createState() => _DetailedState();
}

class _DetailedState extends State<Detailed> {
  late dynamic data;

  @override
  void initState() {
    super.initState();
    data = widget.data;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Detailed Info'),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            children: [
              WoWsInfo(data: data),
              WarshipCell(data: data),
              InfoLabel(data: data),
              DetailedInfo(data: data),
              RatingButton(data: data),
            ],
          ),
        ),
      ),
    );
  }
}

class WoWsInfo extends StatelessWidget {
  final dynamic data;

  const WoWsInfo({Key? key, required this.data}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your WoWsInfo widget here
    return Container();
  }
}

class WarshipCell extends StatelessWidget {
  final dynamic data;

  const WarshipCell({Key? key, required this.data}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your WarshipCell widget here
    return Container();
  }
}

class InfoLabel extends StatelessWidget {
  final dynamic data;

  const InfoLabel({Key? key, required this.data}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your InfoLabel widget here
    return Container();
  }
}

class DetailedInfo extends StatelessWidget {
  final dynamic data;

  const DetailedInfo({Key? key, required this.data}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your DetailedInfo widget here
    return Container();
  }
}

class RatingButton extends StatelessWidget {
  final dynamic data;

  const RatingButton({Key? key, required this.data}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your RatingButton widget here
    return Container();
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  Map<String, dynamic>? data;

  @override
  Widget build(BuildContext context) {
    if (data == null) {
      Navigator.of(context).pop();
      return Container();
    }

    final pvp = data!['pvp'];
    final shipId = data!['ship_id'];
    final rating = data!['rating'];
    final ship = AppGlobalData.get(SAVED.warship)[shipId];
    final overall = AppGlobalData.get(SAVED.pr)[shipId];

    // set the theme colour
    Theme.of(context).primaryColor = getColour(rating);

    return WoWsInfo(
      onPress: ship == null ? null : () => SafeAction('WarshipDetail', {'item': ship}),
      title: lang.wiki_section_title,
      child: Column(
        children: [
          RatingButton(rating: rating),
          Expanded(
            child: SingleChildScrollView(
              padding: EdgeInsets.only(bottom: 16, top: 16),
              child: Column(
                children: [
                  WarshipCell(item: ship, scale: 3),
                  renderNumberDiff(pvp, overall),
                  DetailedInfo(data: data),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget renderNumberDiff(dynamic pvp, dynamic overall) {
    // Implement your logic to render number difference
    return Container(); // Placeholder for actual implementation
  }
}


class NumberDiffRenderer extends StatelessWidget {
  final Map<String, dynamic> data;
  final Map<String, dynamic> overall;

  NumberDiffRenderer({required this.data, required this.overall});

  double normalise(double value, int decimalPlaces) {
    return double.parse(value.toStringAsFixed(decimalPlaces));
  }

  Color getColor(double diff) {
    if (diff > 0) {
      return Colors.green;
    } else if (diff < 0) {
      return Colors.red;
    }
    return Colors.grey;
  }

  @override
  Widget build(BuildContext context) {
    if (overall == null || data == null) {
      return SizedBox.shrink();
    }

    final battles = data['battles'];
    final wins = data['wins'];
    final damageDealt = data['damage_dealt'];
    final frags = data['frags'];
    final averageDamageDealt = overall['average_damage_dealt'];
    final averageFrags = overall['average_frags'];
    final winRate = overall['win_rate'];

    double dmgDiff = normalise(damageDealt / battles - averageDamageDealt, 0);
    double winrateDiff = normalise((wins / battles) * 100 - winRate, 2);
    double fragDiff = normalise(frags / battles - averageFrags, 2);

    return Row(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        InfoLabel(
          color: getColor(dmgDiff),
          info: dmgDiff.toString(),
          title: 'Damage',
        ),
        InfoLabel(
          color: getColor(winrateDiff),
          info: '${winrateDiff}%',
          title: 'Win Rate',
        ),
        InfoLabel(
          color: getColor(fragDiff),
          info: fragDiff.toString(),
          title: 'Frags',
        ),
      ],
    );
  }
}

class InfoLabel extends StatelessWidget {
  final Color color;
  final String info;
  final String title;

  InfoLabel({required this.color, required this.info, required this.title});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(
          title,
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        Text(
          info,
          style: TextStyle(color: color),
        ),
      ],
    );
  }
}

  if (diff == 0) {
    return null;
  }
  return diff > 0 ? Colors.green : Colors.red;
}

  double rounded = double.parse((diff * pow(10, digit)).round() / pow(10, digit).toStringAsFixed(digit));
  if (rounded <= 0) {
    return rounded.toString();
  } else {
    return '+${rounded.toString()}';
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


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Horizontal Layout'),
        ),
        body: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            Container(
              width: 100,
              height: 100,
              color: Colors.red,
            ),
            Container(
              width: 100,
              height: 100,
              color: Colors.green,
            ),
            Container(
              width: 100,
              height: 100,
              color: Colors.blue,
            ),
          ],
        ),
      ),
    );
  }
}


class Detailed extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: Theme.of(context),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Detailed'),
        ),
        body: Center(
          child: Text('Detailed Content Here'),
        ),
      ),
    );
  }
}

void main() {
  runApp(Detailed());
}