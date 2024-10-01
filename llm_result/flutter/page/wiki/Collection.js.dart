
class Collection extends StatefulWidget {
  final dynamic item;

  Collection({Key? key, this.item}) : super(key: key);

  @override
  _CollectionState createState() => _CollectionState();
}

class _CollectionState extends State<Collection> {
  late List<dynamic> data;
  late bool isCollection;
  late dynamic header;

  @override
  void initState() {
    super.initState();
    setLastLocation('Collection');
    print('WIKI - Collection');

    List<dynamic> collection = [];
    isCollection = false;

    if (widget.item != null) {
      // Inside a single collection
      collection = widget.item;
      isCollection = true;
    } else {
      // Display all available collections
      var saved = AppGlobalData.get(SAVED.collection).collection;
      saved.forEach((k, v) {
        collection.add(v);
      });
    }

    print(collection);
    setState(() {
      data = collection;
      header = isCollection ? collection.removeAt(0) : null;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Collection'),
      ),
      body: Column(
        children: [
          if (header != null) ...[
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                header['title'] ?? '',
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                header['description'] ?? '',
                style: TextStyle(fontSize: 16),
              ),
            ),
          ],
          Expanded(
            child: GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: 1,
              ),
              itemCount: data.length,
              itemBuilder: (context, index) {
                return Card(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(Icons.info), // Replace with WikiIcon
                      Text(data[index]['title'] ?? ''),
                    ],
                  ),
                );
              },
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
  List<dynamic> data = [];
  bool collection = false;
  dynamic header;

  void itemOrCollection(dynamic item) {
    // Implement your logic here
  }

  @override
  Widget build(BuildContext context) {
    String ID = '';
    if (data.isNotEmpty && data[0]['card_id'] != null) {
      ID = data[0]['collection_id'];
    }

    return Scaffold(
      appBar: AppBar(
        title: Text(ID),
      ),
      body: Column(
        children: [
          if (collection) ...[
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Column(
                children: [
                  WikiIcon(item: header, scale: 1.6),
                  Text(
                    header['name'],
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  Text(
                    header['description'],
                    style: TextStyle(fontSize: 16),
                  ),
                ],
              ),
            ),
          ],
          Expanded(
            child: GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 4,
                childAspectRatio: 1,
              ),
              itemCount: data.length,
              itemBuilder: (context, index) {
                return GestureDetector(
                  onTap: () => itemOrCollection(data[index]),
                  child: WikiIcon(item: data[index]),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

class WikiIcon extends StatelessWidget {
  final dynamic item;
  final double scale;

  const WikiIcon({Key? key, required this.item, this.scale = 1.0}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Implement your WikiIcon widget here
    return Container(
      // Example implementation
      width: 80 * scale,
      height: 80 * scale,
      color: Colors.blue,
      child: Center(child: Text(item['name'])),
    );
  }
}


class AppGlobalData {
  static Map<String, dynamic> get(String key) {
    // Implement your data retrieval logic here
    return {};
  }
}

class SAVED {
  static const String collection = 'collection';
}

void SafeAction(String action, {Map<String, dynamic>? item, int? param}) {
  // Implement your action handling logic here
}

class YourWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with your widget structure
  }

  void itemOrCollection(dynamic item) {
    if (item['card_id'] != null) {
      // This is an item
      SafeAction('BasicDetail', item: item);
    } else {
      // This is a collection
      var id = item['collection_id'];
      var items = AppGlobalData.get(SAVED.collection)['item'];

      List<dynamic> collectionItems = [];
      collectionItems.add(AppGlobalData.get(SAVED.collection)['collection'][id]);
      for (var one in items) {
        var curr = items[one];
        if (curr['collection_id'] == id) {
          collectionItems.add(curr);
        }
      }
      SafeAction('Collection', item: {'item': collectionItems}, param: 1);
    }
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


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        alignment: Alignment.center,
        child: Text(
          'Your Text Here',
          textAlign: TextAlign.center,
        ),
      ),
    );
  }
}


class Collection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Collection'),
      ),
      body: Center(
        child: Text('Your content goes here'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Collection(),
  ));
}