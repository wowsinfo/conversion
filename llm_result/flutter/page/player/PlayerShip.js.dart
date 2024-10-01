
class PlayerShip extends StatefulWidget {
  final List<dynamic> data;
  final dynamic rating;

  PlayerShip({required this.data, this.rating});

  @override
  _PlayerShipState createState() => _PlayerShipState();
}

class _PlayerShipState extends State<PlayerShip> {
  late List<dynamic> original;
  late List<dynamic> data;
  late dynamic rating;
  Map<String, dynamic> filter = {};
  String sortStr = '';

  @override
  void initState() {
    super.initState();
    List<dynamic> ships = widget.data;
    rating = widget.rating ?? getOverallRating(ships);
    print(ships);

    original = ships..sort((a, b) => b['last_battle_time'].compareTo(a['last_battle_time']));
    data = original;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(lang['player_ship']),
      ),
      body: FlatGrid(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          childAspectRatio: bestCellWidthEven(),
        ),
        items: data,
        itemBuilder: (context, ship) {
          return WarshipCell(ship: ship);
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Implement your action here
        },
        child: Icon(Icons.add),
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
  List<dynamic> data = [];
  double rating = 0.0;

  @override
  void initState() {
    super.initState();
    filter = widget.filter;
    updateShip(filter);
  }

  @override
  void didUpdateWidget(MyWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (widget.filter.isNotEmpty) {
      if (widget.filter == filter) {
        setState(() {
          rating = getOverallRating(data);
        });
        return;
      }
      setState(() {
        filter = widget.filter;
      });
      updateShip(filter);
    }
  }

  void updateShip(String filter) {
    // Implement your logic to update the ship based on the filter
  }

  double getOverallRating(List<dynamic> data) {
    // Implement your logic to calculate overall rating
    return 0.0; // Placeholder return value
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class WarshipInfo extends StatefulWidget {
  @override
  _WarshipInfoState createState() => _WarshipInfoState();
}

class _WarshipInfoState extends State<WarshipInfo> {
  List<dynamic> data = []; // Initialize with your data
  double rating = 0.0; // Initialize with your rating

  List<Map<String, dynamic>> sortingMethod = [
    {'n': lang.ship_sort_battle, 'v': 'pvp.battles'},
    {'n': lang.warship_avg_damage, 'v': 'avgDmg'},
    {'n': lang.warship_avg_winrate, 'v': 'avgWinrate'},
    {'n': lang.warship_avg_frag, 'v': 'avgFrags'},
    {'n': lang.ship_sort_colour, 'v': 'rating'},
    {'n': 'AP', 'v': 'ap'},
    {'n': lang.basic_last_battle, 'v': 'last_battle_time'},
    {'n': lang.record_max_damage_dealt, 'v': 'pvp.max_damage_dealt'},
    {'n': lang.record_max_xp, 'v': 'pvp.max_xp'},
    {'n': lang.record_max_frags_battle, 'v': 'pvp.max_frags_battle'},
  ];

  Color getColour(double rating) {
    // Implement your color logic based on rating
    return Colors.blue; // Placeholder
  }

  double bestCellWidthEven(double width) {
    // Implement your logic for best cell width
    return width; // Placeholder
  }

  void updateShip() {
    // Implement your update ship logic
  }

  void sortData(String value) {
    // Implement your sorting logic
  }

  Widget renderShip(dynamic item) {
    // Implement your ship rendering logic
    return Container(); // Placeholder
  }

  @override
  Widget build(BuildContext context) {
    Color ratingColor = getColour(rating);
    Theme.of(context).primaryColor = ratingColor;
    double cellWidth = bestCellWidthEven(160);

    return Scaffold(
      appBar: AppBar(
        title: Text('${lang.wiki_warship_footer} - ${data.length}'),
      ),
      body: Column(
        children: [
          ElevatedButton(
            onPressed: () => SafeAction('WarshipFilter', applyFunc: updateShip),
            child: Text('Filter'),
          ),
          Expanded(
            child: GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: cellWidth / 100, // Adjust height as needed
              ),
              itemCount: data.length,
              itemBuilder: (context, index) {
                return renderShip(data[index]);
              },
            ),
          ),
          Container(
            padding: EdgeInsets.all(8),
            child: SingleChildScrollView(
              scrollDirection: Axis.horizontal,
              child: Row(
                children: sortingMethod.map((item) {
                  return Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ElevatedButton(
                      onPressed: () => sortData(item['v']),
                      child: Text(item['n']),
                    ),
                  );
                }).toList(),
              ),
            ),
          ),
        ],
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<Map<String, dynamic>> data = []; // Initialize with your data
  String sortStr = '';

  void sortData(String v) {
    print('$sortStr, $v');
    if (v == sortStr) {
      // Simply reverse it
      setState(() {
        data = data.reversed.toList();
        sortStr = '';
      });
    } else {
      setState(() {
        data.sort((a, b) => guard(b, v).compareTo(guard(a, v)));
        sortStr = v;
      });
    }
  }

  dynamic guard(Map<String, dynamic> item, String key) {
    // Implement your Guard logic here
    return item[key]; // Example implementation
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class ShipRenderer extends StatelessWidget {
  final dynamic item;

  ShipRenderer({required this.item});

  @override
  Widget build(BuildContext context) {
    var ship = AppGlobalData.get(SAVED.warship)[item['ship_id']];
    return GestureDetector(
      onTap: () {
        SafeAction('PlayerShipDetail', {'data': item});
      },
      child: Column(
        children: [
          WarshipCell(item: ship, scale: 2),
          SimpleRating(info: item),
        ],
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
  double rating = 0.0;

  void updateShip(List<dynamic> newData) {
    List<dynamic>? sorted = filterShip(newData, original);
    if (sorted == null) {
      setState(() {
        data = original;
      });
    } else {
      // Get rating again
      double newRating = getOverallRating(sorted);
      setState(() {
        data = sorted;
        rating = newRating;
      });
    }
  }

  List<dynamic>? filterShip(List<dynamic> data, List<dynamic> original) {
    // Implement your filtering logic here
    return data; // Replace with actual filtered data
  }

  double getOverallRating(List<dynamic> sorted) {
    // Implement your rating calculation logic here
    return 0.0; // Replace with actual rating calculation
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}


class PlayerShip extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      // Add your content here
      color: Colors.blue, // Example property
      width: 100, // Example property
      height: 100, // Example property
    );
  }
}

class ThemedPlayerShip extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Theme(
      data: Theme.of(context), // Use the current theme
      child: PlayerShip(),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      body: Center(
        child: ThemedPlayerShip(),
      ),
    ),
  ));
}