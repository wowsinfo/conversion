
class Info6Icon extends StatefulWidget {
  final dynamic data;
  final bool? compact;
  final bool? topOnly;

  const Info6Icon({Key? key, this.data, this.compact, this.topOnly}) : super(key: key);

  @override
  _Info6IconState createState() => _Info6IconState();
}

class _Info6IconState extends State<Info6Icon> {
  double cellWidth = bestWidth(100);
  double? bestItemWidth;

  void updateBestWidth(BoxConstraints constraints) {
    final goodWidth = constraints.maxWidth;
    setState(() {
      bestItemWidth = bestWidth(400, goodWidth);
    });
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        updateBestWidth(constraints);
        return Container(
          width: cellWidth,
          child: Column(
            children: [
              // Replace with your IconLabel widget implementation
              IconLabel(data: widget.data, compact: widget.compact, topOnly: widget.topOnly),
            ],
          ),
        );
      },
    );
  }
}

double bestWidth(double baseWidth, [double? maxWidth]) {
  // Implement your bestWidth logic here
  return maxWidth != null && baseWidth > maxWidth ? maxWidth : baseWidth;
}


class MyWidget extends StatelessWidget {
  final dynamic data;

  MyWidget({this.data});

  @override
  Widget build(BuildContext context) {
    if (data == null) {
      return null;
    }

    return Container(
      child: Text('Data is available'),
    );
  }
}


class Stats {
  final int battles;
  final int wins;
  final int damageDealt;
  final int frags;
  final int xp;
  final int survivedBattles;
  final int mainBattery;

  Stats({
    required this.battles,
    required this.wins,
    required this.damageDealt,
    required this.frags,
    required this.xp,
    required this.survivedBattles,
    required this.mainBattery,
  });
}

class StatsWidget extends StatelessWidget {
  final Stats stats;

  StatsWidget({required this.stats});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text('Battles: ${stats.battles}'),
        Text('Wins: ${stats.wins}'),
        Text('Damage Dealt: ${stats.damageDealt}'),
        Text('Frags: ${stats.frags}'),
        Text('XP: ${stats.xp}'),
        Text('Survived Battles: ${stats.survivedBattles}'),
        Text('Main Battery: ${stats.mainBattery}'),
      ],
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Stats')),
      body: StatsWidget(
        stats: Stats(
          battles: 100,
          wins: 50,
          damageDealt: 2000,
          frags: 30,
          xp: 1500,
          survivedBattles: 70,
          mainBattery: 5,
        ),
      ),
    ),
  ));
}


class StatsView extends StatelessWidget {
  final int battles;
  final int survivedBattles;
  final int wins;
  final double damageDealt;
  final double xp;
  final int frags;
  final int hits;
  final int shots;
  final bool compact;
  final bool topOnly;
  final double cellWidth;

  StatsView({
    required this.battles,
    required this.survivedBattles,
    required this.wins,
    required this.damageDealt,
    required this.xp,
    required this.frags,
    required this.hits,
    required this.shots,
    required this.compact,
    required this.topOnly,
    required this.cellWidth,
  });

  @override
  Widget build(BuildContext context) {
    final int death = battles - survivedBattles;

    return Container(
      margin: compact ? EdgeInsets.zero : EdgeInsets.symmetric(vertical: 16),
      child: LayoutBuilder(
        builder: (context, constraints) {
          return Wrap(
            alignment: WrapAlignment.center,
            children: [
              IconLabel(icon: 'Battle', info: battles.toString(), style: TextStyle(width: cellWidth)),
              IconLabel(icon: 'WinRate', info: '${(wins / battles * 100).toStringAsFixed(2)}%', style: TextStyle(width: cellWidth)),
              IconLabel(icon: 'Damage', info: (damageDealt / battles).toStringAsFixed(2), style: TextStyle(width: cellWidth)),
              if (!topOnly) ...[
                IconLabel(icon: 'EXP', info: (xp / battles).toStringAsFixed(2), style: TextStyle(width: cellWidth)),
                IconLabel(icon: 'KillDeathRatio', info: (frags / death).toStringAsFixed(2), style: TextStyle(width: cellWidth)),
                IconLabel(icon: 'HitRatio', info: '${(hits / shots * 100).toStringAsFixed(2)}%', style: TextStyle(width: cellWidth)),
              ],
            ],
          );
        },
      ),
    );
  }
}

class IconLabel extends StatelessWidget {
  final String icon;
  final String info;
  final TextStyle style;

  IconLabel({required this.icon, required this.info, required this.style});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Image.asset('assets/icons/$icon.png'), // Assuming icons are stored in assets
        Text(info, style: style),
      ],
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