
class Warship extends StatefulWidget {
  @override
  _WarshipState createState() => _WarshipState();
}

class _WarshipState extends State<Warship> {
  List<dynamic> original = [];
  List<dynamic> data = [];
  Map<String, dynamic> filter = {};

  @override
  void initState() {
    super.initState();
    setLastLocation('Warship');
    print('WIKI - Warship');
    var warship = AppGlobalData.get(SAVED.warship);
    var sorted = warship.entries.toList()
      ..sort((a, b) {
        if (a.value['new']) {
          return -1;
        }
        if (b.value['new']) {
          return 1;
        }
        if (a.value['tier'] == b.value['tier']) {
          return a.value['type'].compareTo(b.value['type']);
        } else {
          return b.value['tier'].compareTo(a.value['tier']);
        }
      });

    sorted = sorted.map((s) => s.value).toList();
    original = sorted;
    data = sorted;
    print(data);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(lang.warshipTitle),
      ),
      body: SafeArea(
        child: GridView.builder(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            childAspectRatio: bestCellWidthEven(),
          ),
          itemCount: data.length,
          itemBuilder: (context, index) {
            return WarshipCell(warship: data[index]);
          },
        ),
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  final String filter;

  MyWidget({Key? key, required this.filter}) : super(key: key);

  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  String? filter;

  @override
  void initState() {
    super.initState();
    filter = widget.filter;
    updateShip(filter);
  }

  @override
  void didUpdateWidget(MyWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (widget.filter != oldWidget.filter) {
      setState(() {
        filter = widget.filter;
      });
      updateShip(filter);
    }
  }

  void updateShip(String? filter) {
    // Implement your update logic here
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class WoWsInfo extends StatelessWidget {
  final String title;
  final Function onPress;
  final Widget child;

  WoWsInfo({required this.title, required this.onPress, required this.child});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => onPress(),
      child: Column(
        children: [
          Text(title),
          child,
        ],
      ),
    );
  }
}

class WarshipCell extends StatelessWidget {
  final double scale;
  final dynamic item;
  final Function onPress;

  WarshipCell({required this.scale, required this.item, required this.onPress});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => onPress(),
      child: Container(
        width: 80 * scale,
        height: 80 * scale,
        child: Text(item['name']), // Assuming item has a 'name' property
      ),
    );
  }
}

class WarshipGrid extends StatefulWidget {
  @override
  _WarshipGridState createState() => _WarshipGridState();
}

class _WarshipGridState extends State<WarshipGrid> {
  List<dynamic> data = []; // Replace with your data fetching logic

  double bestCellWidthEven(double width) {
    return width; // Implement your logic for best cell width
  }

  void updateShip(dynamic ship) {
    // Implement your update logic here
  }

  void safeAction(String route, {dynamic args}) {
    // Implement your navigation logic here
  }

  @override
  Widget build(BuildContext context) {
    final double width = bestCellWidthEven(160);

    return WoWsInfo(
      title: 'Wiki Warship Footer - ${data.length}',
      onPress: () => safeAction('WarshipFilter', args: {'applyFunc': updateShip}),
      child: StaggeredGridView.countBuilder(
        crossAxisCount: 2,
        itemCount: data.length,
        itemBuilder: (BuildContext context, int index) {
          final item = data[index];
          return WarshipCell(
            scale: width / 80,
            item: item,
            onPress: () => safeAction('WarshipDetail', args: {'item': item}),
          );
        },
        staggeredTileBuilder: (int index) => StaggeredTile.fit(1),
        mainAxisSpacing: 0,
        crossAxisSpacing: 0,
      ),
    );
  }
}


class ShipUpdater extends StatefulWidget {
  @override
  _ShipUpdaterState createState() => _ShipUpdaterState();
}

class _ShipUpdaterState extends State<ShipUpdater> {
  List<dynamic> original = []; // Initialize with your original data
  List<dynamic> data = [];

  void updateShip(List<dynamic> newData) {
    List<dynamic>? sorted = filterShip(newData);
    if (sorted == null) {
      setState(() {
        data = original;
      });
    } else {
      setState(() {
        data = sorted;
      });
    }
  }

  List<dynamic>? filterShip(List<dynamic> data) {
    // Implement your filtering logic here
    return data.isNotEmpty ? data : null; // Example logic
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class Warship extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship'),
      ),
      body: Center(
        child: Text('Welcome to the Warship!'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Warship(),
  ));
}