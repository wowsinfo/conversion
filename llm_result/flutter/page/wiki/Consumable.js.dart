
class Consumable extends StatefulWidget {
  final bool upgrade;

  Consumable({Key? key, required this.upgrade}) : super(key: key);

  @override
  _ConsumableState createState() => _ConsumableState();
}

class _ConsumableState extends State<Consumable> {
  late List<dynamic> data;

  @override
  void initState() {
    super.initState();
    String loc = widget.upgrade ? 'Upgrade' : 'Consumable';
    setLastLocation(loc);

    // Load data depending on 'upgrade' prop
    data = [];
    var consumable = AppGlobalData.get(SAVED.consumable);
    consumable.forEach((key, curr) {
      if (widget.upgrade && curr['type'] == 'Modernization') {
        data.add(curr);
      } else if (!widget.upgrade && curr['type'] != 'Modernization') {
        data.add(curr);
      }
    });

    // Create sections for new and old consumables
    data.sort((a, b) {
      if (!widget.upgrade) {
        // Flags first then camouflages
        return a['type'] == 'Flags' ? -1 : 1;
      }

      // Sort by price
      if (a['price_gold'] == 0) {
        return a['price_credit'].compareTo(b['price_credit']);
      } else {
        return a['price_gold'].compareTo(b['price_gold']);
      }
    });

    print(data);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.upgrade ? 'Upgrade' : 'Consumable'),
      ),
      body: data.isEmpty
          ? Center(child: CircularProgressIndicator())
          : GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: 1,
              ),
              itemCount: data.length,
              itemBuilder: (context, index) {
                var item = data[index];
                return Card(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      WikiIcon(item['icon']),
                      Text(item['name']),
                      Text('Price: ${item['price_gold'] > 0 ? item['price_gold'] : item['price_credit']}'),
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

class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return WoWsInfo(child: renderGrid());
  }

  Widget renderGrid() {
    // Implement your grid rendering logic here
    return GridView.count(
      crossAxisCount: 2,
      children: List.generate(10, (index) {
        return Center(
          child: Text('Item $index'),
        );
      }),
    );
  }
}


class MyGridWidget extends StatefulWidget {
  @override
  _MyGridWidgetState createState() => _MyGridWidgetState();
}

class _MyGridWidgetState extends State<MyGridWidget> {
  List<dynamic>? data;

  @override
  void initState() {
    super.initState();
    // Load your data here and setState to update the UI
  }

  @override
  Widget build(BuildContext context) {
    return renderGrid();
  }

  Widget renderGrid() {
    if (data == null) {
      return LoadingModal();
    }

    return Container(
      child: StaggeredGridView.countBuilder(
        crossAxisCount: 4,
        itemCount: data!.length,
        itemBuilder: (BuildContext context, int index) {
          final item = data![index];
          return WikiIcon(
            item: item,
            onPress: () => SafeAction('BasicDetail', {'item': item}),
          );
        },
        staggeredTileBuilder: (int index) => StaggeredTile.fit(1),
        mainAxisSpacing: 4.0,
        crossAxisSpacing: 4.0,
      ),
    );
  }
}

class LoadingModal extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(child: CircularProgressIndicator());
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
        // Customize your WikiIcon widget here
        child: Text(item.toString()), // Example content
      ),
    );
  }
}

void SafeAction(String route, Map<String, dynamic> params) {
  // Implement your navigation logic here
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


class Consumable extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Consumable'),
      ),
      body: Center(
        child: Text('This is the Consumable screen'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Consumable(),
  ));
}