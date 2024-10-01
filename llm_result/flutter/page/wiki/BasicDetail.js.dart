
class BasicDetail extends StatelessWidget {
  final dynamic item;

  BasicDetail({required this.item});

  @override
  Widget build(BuildContext context) {
    String id = '';
    if (item['consumable_id'] != null) {
      id = item['consumable_id'];
    } else if (item['achievement_id'] != null) {
      id = item['achievement_id'];
    } else if (item['collection_id'] != null) {
      id = item['card_id'];
    }

    return Scaffold(
      appBar: AppBar(
        title: Text('Item Details'),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            WoWsInfo(title: id, child: renderDetail()),
          ],
        ),
      ),
    );
  }

  Widget renderDetail() {
    // Implement your detail rendering logic here
    return Column(
      children: [
        Title(
          child: Text('Detail Title'),
        ),
        Paragraph(
          child: Text('Detail description goes here.'),
        ),
        Caption(
          child: Text('Additional information.'),
        ),
      ],
    );
  }
}

class WoWsInfo extends StatelessWidget {
  final String title;
  final Widget child;

  WoWsInfo({required this.title, required this.child});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(title, style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold)),
            SizedBox(height: 10),
            child,
          ],
        ),
      ),
    );
  }
}


class DetailWidget extends StatelessWidget {
  final dynamic item;

  DetailWidget({required this.item});

  @override
  Widget build(BuildContext context) {
    final container = BoxDecoration(
      // Add your container styles here
    );
    final label = TextStyle(
      // Add your label styles here
    );

    // Make title colour tint colour
    final titleStyle = label.copyWith(color: TintTextColour());

    if (item['profile'] != null) {
      // Consumables
      final name = item['name'];
      final description = item['description'];
      final profile = item['profile'];
      String bonus = profile.entries.map((curr) => curr.value['description']).join('\n');

      return SingleChildScrollView(
        child: Container(
          decoration: container,
          child: Column(
            children: [
              AnimatedContainer(
                duration: Duration(seconds: 1),
                child: PulseAnimation(
                  child: WikiIcon(scale: 1.6, item: item),
                ),
              ),
              Text(name, style: titleStyle),
              PriceLabel(item: item),
              Text(description, style: label),
              Text(bonus, style: label),
            ],
          ),
        ),
      );
    } else if (item['perks'] != null) {
      // This is commander skill
      final name = item['name'];
      final perks = item['perks'];
      String bonus = perks.entries.map((curr) => curr.value['description']).join('\n');

      return SingleChildScrollView(
        child: Container(
          decoration: container,
          child: Column(
            children: [
              AnimatedContainer(
                duration: Duration(seconds: 1),
                child: PulseAnimation(
                  child: WikiIcon(scale: 1.6, item: item),
                ),
              ),
              Text(name, style: titleStyle),
              Text(bonus, style: label),
            ],
          ),
        ),
      );
    } else if (item['image_inactive'] != null || item['card_id'] != null) {
      // This is achievement or collection item
      final description = item['description'];
      final name = item['name'];

      return SingleChildScrollView(
        child: Container(
          decoration: container,
          child: Column(
            children: [
              AnimatedContainer(
                duration: Duration(seconds: 1),
                child: PulseAnimation(
                  child: WikiIcon(scale: 1.6, item: item),
                ),
              ),
              Text(name, style: titleStyle),
              Text(description, style: label),
            ],
          ),
        ),
      );
    }

    return Container(); // Fallback if no conditions are met
  }
}

class PulseAnimation extends StatelessWidget {
  final Widget child;

  PulseAnimation({required this.child});

  @override
  Widget build(BuildContext context) {
    return AnimatedScale(
      scale: 1.1,
      duration: Duration(seconds: 1),
      child: child,
    );
  }
}

class WikiIcon extends StatelessWidget {
  final double scale;
  final dynamic item;

  WikiIcon({required this.scale, required this.item});

  @override
  Widget build(BuildContext context) {
    // Implement your WikiIcon widget here
    return Container(); // Placeholder
  }
}

class PriceLabel extends StatelessWidget {
  final dynamic item;

  PriceLabel({required this.item});

  @override
  Widget build(BuildContext context) {
    // Implement your PriceLabel widget here
    return Container(); // Placeholder
  }
}

Color TintTextColour() {
  // Implement your TintTextColour function here
  return Colors.black; // Placeholder
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
          alignment: Alignment.center,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                // Add your content here
              ],
            ),
          ),
        ),
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        margin: EdgeInsets.only(top: 8, left: 8, right: 8, bottom: 8),
        child: Text(
          'Your Text Here',
          textAlign: TextAlign.center,
        ),
      ),
    );
  }
}


class BasicDetail extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Basic Detail'),
      ),
      body: Center(
        child: Text('This is the Basic Detail screen'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: BasicDetail(),
  ));
}