
class Filter extends StatefulWidget {
  @override
  _FilterState createState() => _FilterState();
}

class _FilterState extends State<Filter> {
  bool filter = false;
  String tier = 'Tier'; // Replace with actual translation
  String nation = 'Nation'; // Replace with actual translation
  String type = 'Type'; // Replace with actual translation
  String name = '';
  bool premium = false;
  int accordion = 0; // 0 for none expanded

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Filter'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                labelText: 'Name',
              ),
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
            ExpansionTile(
              title: Text(tier),
              children: <Widget>[
                // Add tier filter options here
              ],
            ),
            ExpansionTile(
              title: Text(nation),
              children: <Widget>[
                // Add nation filter options here
              ],
            ),
            ExpansionTile(
              title: Text(type),
              children: <Widget>[
                // Add type filter options here
              ],
            ),
            ElevatedButton(
              onPressed: () {
                // Implement filter action
              },
              child: Text('Apply Filter'),
            ),
          ],
        ),
      ),
    );
  }
}


class WikiFilter extends StatefulWidget {
  final Function applyFunc;
  final Function resetFunc;
  final bool wiki;

  WikiFilter({required this.applyFunc, required this.resetFunc, required this.wiki});

  @override
  _WikiFilterState createState() => _WikiFilterState();
}

class _WikiFilterState extends State<WikiFilter> {
  String name = '';
  String tier = 'Select Tier';
  String nation = 'Select Nation';
  String type = 'Select Type';
  bool premium = false;
  int accordion = 0;

  List<String> tierList = ['Tier I', 'Tier II', 'Tier III', 'Tier IV'];
  List<String> nationList = ['Nation A', 'Nation B', 'Nation C'];
  List<String> typeList = ['Type X', 'Type Y', 'Type Z'];

  @override
  Widget build(BuildContext context) {
    if (widget.wiki) {
      return Container(
        color: Theme.of(context).backgroundColor,
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                hintText: 'Search for a warship',
              ),
              onChanged: (text) {
                setState(() {
                  name = text;
                });
              },
            ),
            ListTile(
              title: Text('Premium'),
              trailing: Checkbox(
                value: premium,
                onChanged: (value) {
                  setState(() {
                    premium = value!;
                  });
                },
              ),
            ),
            ExpansionTile(
              title: Text(tier),
              children: tierList.map((item) {
                return ListTile(
                  title: Text(item),
                  onTap: () {
                    setState(() {
                      tier = item;
                      accordion = 0;
                    });
                  },
                );
              }).toList(),
            ),
            ExpansionTile(
              title: Text(nation),
              children: nationList.map((item) {
                return ListTile(
                  title: Text(item),
                  onTap: () {
                    setState(() {
                      nation = item;
                      accordion = 0;
                    });
                  },
                );
              }).toList(),
            ),
            ExpansionTile(
              title: Text(type),
              children: typeList.map((item) {
                return ListTile(
                  title: Text(item),
                  onTap: () {
                    setState(() {
                      type = item;
                      accordion = 0;
                    });
                  },
                );
              }).toList(),
            ),
            ElevatedButton(
              onPressed: () {
                widget.resetFunc();
              },
              child: Text('Reset'),
            ),
            ElevatedButton(
              onPressed: () {
                widget.applyFunc();
              },
              child: Text('Apply Filter'),
            ),
          ],
        ),
      );
    }
    return Container();
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
          title: Text('Flutter App'),
        ),
        body: Padding(
          padding: const EdgeInsets.all(4.0),
          child: Center(
            child: Text('Hello, Flutter!'),
          ),
        ),
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(8),
      child: Text('Hello, Flutter!'),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(
        title: Text('Flutter App'),
      ),
      body: MyWidget(),
    ),
  ));
}


class Filter extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Filter'),
      ),
      body: Center(
        child: Text('Filter Content Here'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Filter(),
  ));
}