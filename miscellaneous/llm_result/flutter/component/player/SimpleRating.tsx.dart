
class SimpleRating extends StatelessWidget {
  final Map<String, dynamic> info;

  SimpleRating({required this.info});

  Color getColour(double rating) {
    // Implement your logic to get color based on rating
    // This is a placeholder implementation
    if (rating >= 4) {
      return Colors.green;
    } else if (rating >= 2) {
      return Colors.yellow;
    } else {
      return Colors.red;
    }
  }

  @override
  Widget build(BuildContext context) {
    final double? pvp = info['pvp'];
    final double rating = info['rating'];
    final Color ratingColour = getColour(rating);

    bool nothing = pvp == null;

    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        if (!nothing) 
          Container(
            color: ratingColour,
            child: Text(
              'Rating: ${rating.toStringAsFixed(1)}',
              style: TextStyle(color: Colors.white),
            ),
          ),
        if (nothing)
          Text(
            'No Rating Available',
            style: TextStyle(color: Colors.grey),
          ),
      ],
    );
  }
}

  nothing = true;
}


class PvpStats extends StatelessWidget {
  final bool nothing;
  final Pvp pvp;
  final Color ratingColour;

  PvpStats({required this.nothing, required this.pvp, required this.ratingColour});

  @override
  Widget build(BuildContext context) {
    final iconStyle = BoxDecoration(
      color: ratingColour,
      borderRadius: BorderRadius.circular(12),
    );

    return Column(
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  height: 24,
                  width: 24,
                  decoration: iconStyle,
                  child: Image.network('Battle'),
                ),
                Text(nothing ? '0' : pvp.battles.toString()),
              ],
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  height: 24,
                  width: 24,
                  decoration: iconStyle,
                  child: Image.network('WinRate'),
                ),
                Text(
                  nothing
                      ? '0.0%'
                      : '${(pvp.wins / pvp.battles * 100).toStringAsFixed(2)}%',
                ),
              ],
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  height: 24,
                  width: 24,
                  decoration: iconStyle,
                  child: Image.network('Damage'),
                ),
                Text(nothing ? '0' : (pvp.damage_dealt / pvp.battles).toString()),
              ],
            ),
          ],
        ),
        Container(
          color: ratingColour,
          height: 12,
        ),
      ],
    );
  }
}

class Pvp {
  final int battles;
  final int wins;
  final double damage_dealt;

  Pvp({required this.battles, required this.wins, required this.damage_dealt});
}


class CenterTextWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        'Your Text Here',
        style: TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w300,
        ),
      ),
    );
  }
}


class CenterView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            // Add your content here
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: CenterView(),
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