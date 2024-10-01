
class Setup extends StatefulWidget {
  @override
  _SetupState createState() => _SetupState();
}

class _SetupState extends State<Setup> {
  bool loading = true;
  bool error = false;
  final String server = 'SERVER'; // Replace with actual server
  int selectedServer = 3;
  List<String> langList = [];
  List<String> langData = [];
  String selectedLang = 'en';

  @override
  void initState() {
    super.initState();
    fetchLanguage();
  }

  Future<void> fetchLanguage() async {
    try {
      final response = await http.get(Uri.parse('YOUR_API_URL')); // Replace with actual API URL
      if (response.statusCode == 200) {
        final data = json.decode(response.body);
        setState(() {
          langList = List<String>.from(data.keys);
          langList.sort();
          loading = false;
        });
      } else {
        setState(() {
          error = true;
        });
      }
    } catch (e) {
      setState(() {
        error = true;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Setup API Language and Server'),
      ),
      body: loading
          ? Center(child: CircularProgressIndicator())
          : error
              ? Center(child: Text('Error fetching language data'))
              : ScrollView(
                  child: Column(
                    children: [
                      Text('Select Language', style: Theme.of(context).textTheme.headline6),
                      ...langList.map((lang) {
                        return ListTile(
                          title: Text(lang),
                          leading: Radio<String>(
                            value: lang,
                            groupValue: selectedLang,
                            onChanged: (String? value) {
                              setState(() {
                                selectedLang = value!;
                              });
                            },
                          ),
                        );
                      }).toList(),
                      ElevatedButton(
                        onPressed: () {
                          // Handle the next action
                        },
                        child: Text('Continue'),
                      ),
                    ],
                  ),
                ),
    );
  }
}


class SettingsPage extends StatefulWidget {
  @override
  _SettingsPageState createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  bool loading = false;
  List<String> server = []; // Populate with your server data
  int selectedServer = 0;
  List<String> langList = []; // Populate with your language data
  int selectedLang = 0;

  void updateServer(int index) {
    setState(() {
      selectedServer = index;
    });
  }

  void finishSetup() {
    // Implement your finish setup logic here
  }

  Widget renderAPILanguage() {
    return Column(
      children: List.generate(langList.length, (index) {
        return ListTile(
          title: Text(langList[index]),
          onTap: () {
            setState(() {
              selectedLang = index;
            });
          },
        );
      }),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                'API Settings',
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 24),
                textAlign: TextAlign.center,
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                'Game Server: ${server[selectedServer]}',
                style: TextStyle(fontSize: 18),
              ),
            ),
            Wrap(
              children: List.generate(server.length, (index) {
                return ElevatedButton(
                  onPressed: () => updateServer(index),
                  child: Text(server[index]),
                );
              }),
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Text(
                'API Language: ${langList[selectedLang]}',
                style: TextStyle(fontSize: 18),
              ),
            ),
            renderAPILanguage(),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: loading ? null : finishSetup,
        child: Icon(Icons.check),
        label: Text('Setup Done'),
      ),
    );
  }
}


class LanguageScreen extends StatefulWidget {
  @override
  _LanguageScreenState createState() => _LanguageScreenState();
}

class _LanguageScreenState extends State<LanguageScreen> {
  bool loading = true;
  String? error;
  List<String> langData = [];
  Map<String, String> langList = {};

  @override
  void initState() {
    super.initState();
    // Load your data here and update loading, error, langData, and langList accordingly
  }

  void updateApiLanguage(String item) {
    // Implement your language update logic here
  }

  @override
  Widget build(BuildContext context) {
    final titleStyle = TextStyle(fontSize: 20, fontWeight: FontWeight.bold);
    final wrapView = BoxDecoration(
      border: Border.all(color: Colors.grey),
      borderRadius: BorderRadius.circular(8),
    );

    if (loading) {
      return Center(child: CircularProgressIndicator());
    }
    if (error != null) {
      return Column(
        children: [
          Text('Error downloading issue', style: titleStyle),
          ListTile(
            title: Text('Send Feedback'),
            subtitle: Text('We would love to hear from you!'),
            onTap: () {
              // Open URL logic here
            },
          ),
        ],
      );
    }

    return Container(
      decoration: wrapView,
      child: ListView(
        children: langData.map((item) {
          return ElevatedButton(
            onPressed: () => updateApiLanguage(item),
            child: Text(langList[item] ?? ''),
          );
        }).toList(),
      ),
    );
  }
}


class ServerSelector extends StatefulWidget {
  @override
  _ServerSelectorState createState() => _ServerSelectorState();
}

class _ServerSelectorState extends State<ServerSelector> {
  int selectedServer;

  void updateServer(int index) {
    setCurrServer(index);
    setState(() {
      selectedServer = index;
    });
  }

  void setCurrServer(int index) {
    // Implement your logic to set the current server here
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Select Server'),
      ),
      body: ListView.builder(
        itemCount: 10, // Example item count
        itemBuilder: (context, index) {
          return ListTile(
            title: Text('Server $index'),
            selected: selectedServer == index,
            onTap: () => updateServer(index),
          );
        },
      ),
    );
  }
}


class LanguageSelector extends StatefulWidget {
  @override
  _LanguageSelectorState createState() => _LanguageSelectorState();
}

class _LanguageSelectorState extends State<LanguageSelector> {
  String selectedLang;

  void updateApiLanguage(String lang) {
    setAPILanguage(lang);
    setState(() {
      selectedLang = lang;
    });
  }

  void setAPILanguage(String lang) {
    // Implement your API language setting logic here
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Select Language'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () => updateApiLanguage('en'),
              child: Text('English'),
            ),
            ElevatedButton(
              onPressed: () => updateApiLanguage('es'),
              child: Text('Spanish'),
            ),
            // Add more language buttons as needed
          ],
        ),
      ),
    );
  }
}


class MenuPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Menu'),
      ),
      body: Center(
        child: Text('Welcome to the Menu Page'),
      ),
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MenuPage(),
    );
  }
}

void main() {
  runApp(MyApp());
}

class Setup {
  void finishSetup(BuildContext context) {
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute(builder: (context) => MenuPage()),
      (Route<dynamic> route) => false,
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
        body: Container(
          child: Center(
            child: Text('Hello, Flutter!'),
          ),
          decoration: BoxDecoration(
            color: Colors.white,
          ),
        ),
      ),
    );
  }
}


class MyScrollWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
          child: Container(
            padding: EdgeInsets.only(top: MediaQuery.of(context).size.height * 0.15),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
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

void main() {
  runApp(MaterialApp(
    home: MyScrollWidget(),
  ));
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          child: Column(
            children: [
              Expanded(
                child: Container(
                  // Add your content here
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text('Your content here'),
      ),
      floatingActionButton: Padding(
        padding: const EdgeInsets.all(16.0),
        child: FloatingActionButton(
          onPressed: () {
            // Add your action here
          },
          child: Icon(Icons.add),
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MyHomePage(),
  ));
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        margin: EdgeInsets.only(top: 16),
        child: Text(
          'Your Title Here',
          textAlign: TextAlign.center,
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
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
      child: Wrap(
        alignment: WrapAlignment.center,
        direction: Axis.horizontal,
        children: [
          // Add your widgets here
        ],
      ),
    );
  }
}


class Setup extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Setup'),
        ),
        body: Center(
          child: Text('Hello, Flutter!'),
        ),
      ),
    );
  }
}

void main() {
  runApp(Setup());
}