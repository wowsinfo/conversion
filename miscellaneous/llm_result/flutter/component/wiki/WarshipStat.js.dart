
class WarshipStat extends StatelessWidget {
  final Map<String, dynamic> profile;

  WarshipStat({required this.profile});

  @override
  Widget build(BuildContext context) {
    final mobility = profile['mobility'];
    final weaponry = profile['weaponry'];
    final concealment = profile['concealment'];
    final armour = profile['armour'];
    final antiAircraft = weaponry['anti_aircraft'];
    final aircraft = weaponry['aircraft'];
    final artillery = weaponry['artillery'];
    final torpedoes = weaponry['torpedoes'];

    return Column(
      children: [
        renderProgress(armour['total'], 'Survivability'),
        renderProgress(artillery, 'Artillery'),
        renderProgress(torpedoes, 'Torpedoes'),
        renderProgress(antiAircraft, 'Anti-Aircraft'),
        renderProgress(mobility['total'], 'Maneuverability'),
        renderProgress(aircraft, 'Aircraft'),
        renderProgress(concealment['total'], 'Concealment'),
      ],
    );
  }

  Widget renderProgress(double value, String label) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(label),
        SizedBox(height: 4),
        LinearProgressIndicator(
          value: value / 100, // Assuming the value is out of 100
          backgroundColor: Colors.grey[300],
          color: Colors.blue,
        ),
        SizedBox(height: 8),
        Text(value.toString(), style: TextStyle(fontWeight: FontWeight.bold)),
      ],
    );
  }
}


class ProgressWidget extends StatelessWidget {
  final double value;
  final String title;

  ProgressWidget({required this.value, required this.title});

  @override
  Widget build(BuildContext context) {
    if (value > 0) {
      return Column(
        children: [
          Container(
            padding: EdgeInsets.all(8.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(title, style: TextStyle(fontSize: 16)),
                Text(value.toString(), style: TextStyle(fontSize: 16)),
              ],
            ),
          ),
          LinearProgressIndicator(value: value / 100),
        ],
      );
    }
    return SizedBox.shrink();
  }
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          margin: EdgeInsets.only(bottom: 16),
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


class MyHeader extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.only(left: 16, right: 16),
      margin: EdgeInsets.only(top: 8),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          // Add your left widget here
          Text('Left Widget'),
          // Add your right widget here
          Text('Right Widget'),
        ],
      ),
    );
  }
}


class WarshipStat extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Stat'),
      ),
      body: Center(
        child: Text('Warship statistics will be displayed here.'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: WarshipStat(),
  ));
}