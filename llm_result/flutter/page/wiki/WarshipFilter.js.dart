
class WarshipFilter extends StatefulWidget {
  @override
  _WarshipFilterState createState() => _WarshipFilterState();
}

class _WarshipFilterState extends State<WarshipFilter> {
  bool premium = false;
  String name = '';
  List<bool> nation = List.generate(0, (index) => false);
  List<bool> type = List.generate(0, (index) => false);
  List<bool> tier = List.generate(0, (index) => false);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Filter'),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            children: [
              TextField(
                decoration: InputDecoration(labelText: 'Name'),
                onChanged: (value) {
                  setState(() {
                    name = value;
                  });
                },
              ),
              CheckboxListTile(
                title: Text('Premium'),
                value: premium,
                onChanged: (value) {
                  setState(() {
                    premium = value!;
                  });
                },
              ),
              // Add your nation, type, and tier checkboxes here
              // Example for nation
              // CheckboxListTile(
              //   title: Text('Nation 1'),
              //   value: nation[0],
              //   onChanged: (value) {
              //     setState(() {
              //       nation[0] = value!;
              //     });
              //   },
              // ),
              // Repeat for other nations, types, and tiers
              ElevatedButton(
                onPressed: () {
                  // Implement your filter logic here
                },
                child: Text('Apply Filter'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}


class MyScrollView extends StatefulWidget {
  @override
  _MyScrollViewState createState() => _MyScrollViewState();
}

class _MyScrollViewState extends State<MyScrollView> {
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _scrollController = ScrollController();
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _scrollController.jumpTo(128);
    });
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      controller: _scrollController,
      child: Column(
        children: List.generate(100, (index) => Container(height: 50, color: index.isEven ? Colors.blue : Colors.red)),
      ),
    );
  }
}


class WarshipFilter extends StatefulWidget {
  @override
  _WarshipFilterState createState() => _WarshipFilterState();
}

class _WarshipFilterState extends State<WarshipFilter> {
  bool premium = false;
  String name = '';
  List<String> tier = [];
  List<String> nation = [];
  List<String> type = [];

  @override
  Widget build(BuildContext context) {
    final tierList = getTierList();
    final nations = AppGlobalData.get(SAVED.encyclopedia).ship_nations;
    final nationList = nations.values.toList();
    final types = AppGlobalData.get(SAVED.encyclopedia).ship_types;
    final typeList = types.values.toList();

    return Scaffold(
      appBar: AppBar(
        title: Text(lang.wiki_warship_filter_placeholder),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                labelText: lang.wiki_warship_filter_placeholder,
              ),
              onChanged: (t) {
                setState(() {
                  name = t;
                });
              },
              onSubmitted: (value) {
                if (name.trim().isNotEmpty) {
                  applyAll();
                }
              },
              textCapitalization: TextCapitalization.none,
            ),
            SizedBox(height: 16),
            _buildSection(
              title: lang.wiki_warship_filter_tier,
              selectedItems: tier,
              itemList: tierList,
              onItemSelected: (item) => addData(item, MODE.TIER),
            ),
            _buildSection(
              title: lang.wiki_warship_filter_nation,
              selectedItems: nation,
              itemList: nationList,
              onItemSelected: (item) => addData(item, MODE.NATION),
            ),
            _buildSection(
              title: lang.wiki_warship_filter_type,
              selectedItems: type,
              itemList: typeList,
              onItemSelected: (item) => addData(item, MODE.TYPE),
            ),
            ListTile(
              title: Text(lang.wiki_warship_filter_premium),
              trailing: Checkbox(
                value: premium,
                onChanged: (value) {
                  setState(() {
                    premium = value!;
                  });
                },
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: resetAll,
                  child: Text(lang.wiki_warship_reset_btn),
                ),
                ElevatedButton(
                  onPressed: applyAll,
                  child: Text(lang.wiki_warship_filter_btn),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildSection({
    required String title,
    required List<String> selectedItems,
    required List<String> itemList,
    required Function(String) onItemSelected,
  }) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: Text(title, style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
        ),
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: Text(selectedItems.join(' | ')),
        ),
        Wrap(
          spacing: 8.0,
          children: itemList.map((item) {
            return ElevatedButton(
              onPressed: () => onItemSelected(item),
              child: Text(item),
            );
          }).toList(),
        ),
      ],
    );
  }

  void applyAll() {
    // Implement apply logic
  }

  void resetAll() {
    // Implement reset logic
  }

  void addData(String item, String mode) {
    // Implement add data logic
  }

  List<String> getTierList() {
    // Implement tier list retrieval logic
    return [];
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool premium = false;
  String name = '';
  List<String> nation = [];
  List<String> type = [];
  List<String> tier = [];

  void resetAll() {
    setState(() {
      premium = false;
      name = '';
      nation = [];
      type = [];
      tier = [];
    });
  }

  void applyAll(BuildContext context) {
    Navigator.pop(context);
    Future.delayed(Duration(milliseconds: 100), () {
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(
          builder: (context) => FilterPage(
            filter: {
              'premium': premium,
              'name': name,
              'nation': nation,
              'type': type,
              'tier': tier,
            },
          ),
        ),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
}

class FilterPage extends StatelessWidget {
  final Map<String, dynamic> filter;

  FilterPage({required this.filter});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Filter')),
      body: Center(child: Text('Filter applied: $filter')),
    );
  }
}


class MyWidget extends StatelessWidget {
  final String item;
  final VoidCallback event;

  MyWidget({required this.item, required this.event});

  @override
  Widget build(BuildContext context) {
    return renderButton(item, event);
  }

  Widget renderButton(String item, VoidCallback event) {
    return ElevatedButton(
      key: ValueKey(item),
      onPressed: event,
      child: Text(item),
    );
  }
}


enum Mode { TIER, NATION, TYPE }

class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<String> tier = [];
  List<String> nation = [];
  List<String> type = [];

  void addData(String item, Mode mode) {
    List<String> arr;

    switch (mode) {
      case Mode.TIER:
        arr = tier;
        break;
      case Mode.NATION:
        arr = nation;
        break;
      case Mode.TYPE:
        arr = type;
        break;
    }

    // Same as last added item
    if (arr.isNotEmpty && arr.last == item) {
      return;
    }
    arr.add(item);

    setState(() {
      switch (mode) {
        case Mode.TIER:
          tier = arr;
          break;
        case Mode.NATION:
          nation = arr;
          break;
        case Mode.TYPE:
          type = arr;
          break;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget tree
  }
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
          children: <Widget>[
            // Add your content here
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


class MyButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ElevatedButton(
        onPressed: () {
          // Add your onPressed logic here
        },
        child: Text('Button'),
        style: ElevatedButton.styleFrom(
          minimumSize: Size(double.infinity, 50), // Flex: 1 equivalent
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Flutter Button')),
      body: Center(child: MyButton()),
    ),
  ));
}


class SelectionText extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16.0),
      child: Text(
        'Your selection text here',
        style: TextStyle(fontSize: 16.0),
      ),
    );
  }
}


class WrapViewExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Wrap View Example'),
      ),
      body: Container(
        child: Wrap(
          direction: Axis.horizontal,
          children: [
            // Add your widgets here
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
            // Add more widgets as needed
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: WrapViewExample(),
  ));
}


class WarshipFilter extends StatefulWidget {
  @override
  _WarshipFilterState createState() => _WarshipFilterState();
}

class _WarshipFilterState extends State<WarshipFilter> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Warship Filter'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            // Add your filter options here
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: WarshipFilter(),
  ));
}