
class Menu extends StatefulWidget {
  @override
  _MenuState createState() => _MenuState();
}

class _MenuState extends State<Menu> {
  bool loading = true;
  var main;
  double bestItemWidth;

  @override
  void initState() {
    super.initState();
    bestItemWidth = bestWidth(400);
    getData();
  }

  Future<void> getData() async {
    // Implement your data fetching logic here
    SharedPreferences prefs = await SharedPreferences.getInstance();
    main = AppGlobalData.get(LOCAL.userInfo);
    setState(() {
      loading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    if (loading) {
      return Loading();
    }

    return Scaffold(
      appBar: AppBar(
        title: Text(lang.menuTitle),
      ),
      body: ScrollView(
        child: Column(
          children: [
            SectionTitle(title: lang.wiki),
            // Add your wiki content here
            SectionTitle(title: lang.extra),
            // Add your extra content here
            // Add more widgets as needed
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Implement your FAB action here
        },
        child: Icon(Icons.add),
      ),
    );
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  Map<String, dynamic> main = {};
  
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    getData();
  }

  void getData() {
    // Fetch data logic here
    var curr = AppGlobalData.get(LOCAL.userInfo);
    setLastLocation('');
    if (curr['account_id'] != main['account_id']) {
      setState(() {
        main = curr;
      });
    }
  }

  void setLastLocation(String location) {
    // Set last location logic here
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      // Your widget tree here
    );
  }
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool loading = true;
  bool first = true;

  @override
  void initState() {
    super.initState();
    appHasLoaded();
    performPendingShortcut();

    if (first) {
      final time = Future.delayed(Duration(seconds: 20), () => false);

      // Update data here if it is not first launch
      final dn = Downloader(getCurrServer());
      final update = dn.updateAll(true);

      Future.race([time, update]).then((obj) {
        if (obj == null) {
          showDialog(
            context: context,
            builder: (context) => AlertDialog(
              title: Text(lang.error_title),
              content: Text(lang.error_timeout),
              actions: [
                TextButton(
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                  child: Text('OK'),
                ),
              ],
            ),
          );
          setState(() {
            loading = false;
          });
        } else {
          if (obj.status) {
            setState(() {
              loading = false;
            });
            setFirstLaunch(false);
          } else {
            showDialog(
              context: context,
              builder: (context) => AlertDialog(
                title: Text(lang.error_title),
                content: Text('${lang.error_download_issue}\n\n${obj.log}'),
                actions: [
                  TextButton(
                    onPressed: () {
                      SimpleViewHandler.openURL(
                          '${APP.Developer}&body=${obj.log}');
                      Navigator.of(context).pop();
                    },
                    child: Text(lang.settings_app_send_feedback_subtitle),
                  ),
                  TextButton(
                    onPressed: () {
                      Navigator.of(context).pop();
                    },
                    child: Text('OK'),
                  ),
                ],
              ),
            );
            setState(() {
              loading = false;
            });
          }
        }
      }).catchError((err) {
        print(err);
        setState(() {
          loading = false;
        });
      });
    } else {
      if (differentMonth()) {
        setState(() {
          loading = false;
        });
      } else {
        validateProVersion().then(() {
          setState(() {
            loading = false;
          });
        }).catchError((err) {
          print(err);
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: loading
            ? Center(child: CircularProgressIndicator())
            : Container(), // Replace with your main content
      ),
    );
  }
}


class WikiData {
  List<Map<String, dynamic>> wiki;
  List<Map<String, String>> officialWebsites;
  List<Map<String, String>> statsInfoWebsite;
  List<Map<String, String>> utilityWebsites;
  List<Map<String, String>> ingameWebsites;
  List<Map<String, String>> links;
  List<Map<String, String>> youtubers;
  String domain;
  String prefix;

  WikiData() {
    getData();
  }

  void getData() {
    // Data for the list
    wiki = [
      {
        't': lang.wiki_achievement,
        'i': {'uri': 'Achievement'},
        'p': () => SafeAction('Achievement'),
      },
      {
        't': lang.wiki_warships,
        'i': {'uri': 'Warship'},
        'p': () => SafeAction('Warship'),
      },
      {
        't': lang.wiki_upgrades,
        'i': {'uri': 'Upgrade'},
        'p': () => SafeAction('Consumable', {'upgrade': true}),
      },
      {
        't': lang.wiki_flags,
        'i': {'uri': 'Camouflage'},
        'p': () => SafeAction('Consumable'),
      },
      // {
      //   't': lang.wiki_skills,
      //   'i': {'uri': 'CommanderSkill'},
      //   'p': () => SafeAction('CommanderSkill'),
      // },
      {'t': lang.wiki_maps, 'i': 'map', 'p': () => SafeAction('Map')},
      {
        't': lang.wiki_collections,
        'i': {'uri': 'Collection'},
        'p': () => SafeAction('Collection'),
      },
    ];

    domain = getCurrDomain();
    prefix = getCurrPrefix();

    officialWebsites = [
      {'t': lang.website_official_site, 'd': 'https://worldofwarships.$domain/'},
      {
        't': lang.website_premium,
        'd': 'https://$prefix.wargaming.net/shop/wows/',
      },
      {
        't': lang.website_global_wiki,
        'd': 'http://wiki.wargaming.net/en/World_of_Warships/',
      },
      {'t': lang.website_dev_blog, 'd': 'https://blog.worldofwarships.com/'},
    ];

    statsInfoWebsite = [
      // {'t': lang.website_sea_group, 'd': 'https://sea-group.org/'},
      // {
      //   't': lang.website_daily_bounce,
      //   'd': 'https://thedailybounce.net/category/world-of-warships/',
      // },
      {'t': lang.website_numbers, 'd': 'https://$prefix.wows-numbers.com/'},
      // {
      //   't': lang.website_models,
      //   'd': 'https://sketchfab.com/tags/world-of-warships',
      // },
      {
        't': lang.website_game_models,
        'd': 'https://gamemodels3d.com/games/worldofwarships/',
      },
    ];

    utilityWebsites = [
      // {
      //   't': lang.website_ap_calculator,
      //   'd': 'https://mustanghx.github.io/ship_ap_calculator/',
      // },
      {'t': lang.website_wowsft, 'd': 'https://wowsft.com/'},
    ];

    ingameWebsites = [
      {
        't': lang.website_wargaming_login,
        'd': 'https://$prefix.wargaming.net/id/signin/',
      },
      {
        't': lang.website_userbonus,
        'd': 'https://worldofwarships.$domain/userbonus/',
      },
      {
        't': lang.website_news_ingame,
        'd': 'https://worldofwarships.$domain/news_ingame/',
      },
      {
        't': lang.website_ingame_armory,
        'd': 'https://armory.worldofwarships.$domain/',
      },
      {
        't': lang.website_ingame_clan,
        'd': 'https://clans.worldofwarships.$domain/clans/gateway/wows/profile/',
      },
      {
        't': lang.website_ingame_warehouse,
        'd': 'https://warehouse.worldofwarships.$domain/',
      },
      {
        't': lang.website_my_logbook,
        'd': 'https://logbook.worldofwarships.$domain/',
      },
    ];

    links = [
      {
        't': lang.content_creator_official,
        'd': lang.content_creator_official_link,
      },
      {
        't': lang.content_creator_fubuki,
        'd': lang.content_creator_fubuki_link,
      }
    ];

    youtubers = [
      {
        't': lang.youtuber_official,
        'd': 'https://www.youtube.com/user/worldofwarshipsCOM',
      },
      {'t': lang.youtuber_flambass, 'd': 'https://www.youtube.com/user/Flambass'},
      {'t': lang.youtuber_flamu, 'd': 'https://www.youtube.com/user/cheesec4t'},
      {
        't': lang.youtuber_iChaseGaming,
        'd': 'https://www.youtube.com/user/ichasegaming',
      },
      {
        't': lang.youtuber_jingles,
        'd': 'https://www.youtube.com/user/BohemianEagle',
      },
      {'t': lang.youtuber_notser, 'd': 'https://www.youtube.com/user/MrNotser'},
      {
        't': lang.youtuber_NoZoupForYou,
        'd': 'https://www.youtube.com/user/ZoupGaming',
      },
      {
        't': lang.youtuber_panzerknacker,
        'd': 'https://www.youtube.com/user/pzkpasch',
      },
      {
        't': lang.youtuber_Toptier,
        'd': 'https://www.youtube.com/channel/UCXOZ2gv_ZGomWNcQU8BBfdQ',
      },
      {'t': lang.youtuber_yuro, 'd': 'https://www.youtube.com/user/spzjess'},
    ];
  }

  void SafeAction(String action, [Map<String, dynamic>? params]) {
    // Implement SafeAction logic here
  }

  String getCurrDomain() {
    // Implement logic to get current domain
    return 'com';
  }

  String getCurrPrefix() {
    // Implement logic to get current prefix
    return 'en';
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  double bestItemWidth = 0;

  double bestWidth(double maxWidth, double goodWidth) {
    return goodWidth < maxWidth ? goodWidth : maxWidth;
  }

  void updateBestWidth(BoxConstraints constraints) {
    final goodWidth = constraints.maxWidth;
    setState(() {
      bestItemWidth = bestWidth(400, goodWidth);
    });
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        updateBestWidth(constraints);
        return Container(
          width: bestItemWidth,
          height: 100,
          color: Colors.blue,
        );
      },
    );
  }
}


class YourWidget extends StatefulWidget {
  @override
  _YourWidgetState createState() => _YourWidgetState();
}

class _YourWidgetState extends State<YourWidget> {
  bool loading = true;
  Main main = Main(); // Assume Main is a model class with account_id and nickname

  @override
  Widget build(BuildContext context) {
    if (loading) {
      return Loading();
    }

    // For main account
    bool enabled = main.accountId.isNotEmpty;
    String title = '- ${main.nickname} -';
    if (title == '-  -') {
      title = '- ??? -';
    }

    return WoWsInfo(
      noRight: true,
      title: title,
      onPress: enabled ? () => SafeAction('Statistics', info: main) : null,
      home: true,
      upper: false,
      child: SingleChildScrollView(
        physics: AlwaysScrollableScrollPhysics(),
        keyboardDismissBehavior: ScrollViewKeyboardDismissBehavior.onDrag,
        child: Column(
          children: [
            AnimatableView(
              animation: 'fadeInDown',
              child: AppName(),
            ),
            renderProButton(),
            AnimatableView(
              animation: 'fadeInUp',
              delay: Duration(milliseconds: 200),
              child: renderContent(),
            ),
            SizedBox(height: 16),
            FloatingActionButton(
              child: Icon(Icons.search),
              onPressed: () => SafeAction('Search'),
            ),
          ],
        ),
      ),
    );
  }

  Widget renderProButton() {
    // Implement your Pro button rendering logic here
    return Container(); // Placeholder for the actual implementation
  }

  Widget renderContent() {
    // Implement your content rendering logic here
    return Container(); // Placeholder for the actual implementation
  }
}

class Main {
  String accountId = '';
  String nickname = '';
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return renderProButton(context);
  }

  Widget renderProButton(BuildContext context) {
    if (isProVersion()) {
      return SizedBox.shrink();
    }
    return ElevatedButton(
      style: ElevatedButton.styleFrom(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(0),
        ),
        margin: EdgeInsets.only(top: 8),
      ),
      onPressed: () {
        Navigator.pushNamed(context, '/proVersion');
      },
      child: Text(lang.pro_upgrade_button),
    );
  }

  bool isProVersion() {
    // Implement your logic to check if the user is in pro version
    return false; // Placeholder return value
  }
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  double bestItemWidth = 300; // Example width
  List<Map<String, dynamic>> wiki = []; // Populate with your data
  List<Map<String, dynamic>> officialWebsites = []; // Populate with your data
  List<Map<String, dynamic>> links = []; // Populate with your data
  List<Map<String, dynamic>> statsInfoWebsite = []; // Populate with your data
  List<Map<String, dynamic>> utilityWebsites = []; // Populate with your data
  List<Map<String, dynamic>> ingameWebsites = []; // Populate with your data
  String lang = 'en'; // Example language
  String store = 'https://example.com'; // Example store link

  void shareApp(String store) {
    // Implement share functionality
  }

  void openURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  void openExternalURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Container(
        margin: EdgeInsets.only(bottom: 80),
        child: Column(
          children: [
            SectionTitle(title: 'Wiki Section Title'),
            Wrap(
              children: wiki.map((item) {
                return ListTile(
                  title: Text(item['t']),
                  onTap: () => item['p'](),
                  leading: Icon(item['i']),
                  trailing: isAndroid ? null : Icon(Icons.chevron_right),
                );
              }).toList(),
            ),
            SectionTitle(title: 'Extra Section Title'),
            Wrap(
              children: [
                ListTile(
                  title: Text('RS Beta'),
                  subtitle: Text('Extra RS Beta Subtitle'),
                  onTap: () => onlyProVersion() ? SafeAction('RS') : null,
                ),
                ListTile(
                  title: Text('Write a Review'),
                  subtitle: Text(store),
                  onTap: () {
                    showDialog(
                      context: context,
                      builder: (context) {
                        return AlertDialog(
                          title: Text('Write a Review Title'),
                          content: Text('Write a Review Message'),
                          actions: [
                            TextButton(
                              onPressed: () {
                                openURL(APP.Developer);
                                Navigator.of(context).pop();
                              },
                              child: Text('Yes'),
                            ),
                            TextButton(
                              onPressed: () {
                                openURL(store);
                                Navigator.of(context).pop();
                              },
                              child: Text('No'),
                            ),
                          ],
                        );
                      },
                    );
                  },
                ),
                ListTile(
                  title: Text('Share App'),
                  subtitle: Text('Share App Subtitle'),
                  onTap: () => shareApp(store),
                ),
              ],
            ),
            SectionTitle(title: 'Website Title'),
            ExpansionTile(
              title: Text('Official Websites'),
              children: officialWebsites.map((item) {
                return ListTile(
                  title: Text(item['t']),
                  subtitle: Text(item['d']),
                  onTap: () => openURL(item['d']),
                );
              }).toList(),
            ),
            ExpansionTile(
              title: Text('Content Creator Title'),
              children: links.map((item) {
                return ListTile(
                  title: Text(item['t']),
                  subtitle: Text(item['d']),
                  onTap: () => openExternalURL(item['d']),
                );
              }).toList(),
            ),
            ExpansionTile(
              title: Text('Stats News Title'),
              children: statsInfoWebsite.map((item) {
                return ListTile(
                  title: Text(item['t']),
                  subtitle: Text(item['d']),
                  onTap: () => openURL(item['d']),
                );
              }).toList(),
            ),
            ExpansionTile(
              title: Text('Utility Websites Title'),
              children: utilityWebsites.map((item) {
                return ListTile(
                  title: Text(item['t']),
                  subtitle: Text(item['d']),
                  onTap: () => openURL(item['d']),
                );
              }).toList(),
            ),
            ExpansionTile(
              title: Text('In-Game Websites Title'),
              subtitle: Text('Wargaming Login Subtitle'),
              children: ingameWebsites.map((item) {
                return ListTile(
                  title: Text(item['t']),
                  subtitle: Text(item['d']),
                  onTap: () => openURL(item['d']),
                );
              }).toList(),
            ),
          ],
        ),
      ),
    );
  }
}

class SectionTitle extends StatelessWidget {
  final String title;

  SectionTitle({required this.title});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: Text(
        title,
        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
      ),
    );
  }
}


void shareApp(String store, bool isIos, String appName) {
  if (isIos) {
    Share.share(store);
  } else {
    Share.share('$appName\n$store');
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

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Home Page'),
      ),
      body: Container(
        child: Center(
          child: Text('Hello, Flutter!'),
        ),
        decoration: BoxDecoration(
          color: Colors.white,
        ),
      ),
    );
  }
}


class MyIconWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        shape: BoxShape.circle,
        color: Colors.blue, // Example color
      ),
      child: Icon(
        Icons.star, // Example icon
        size: 50, // Example size
        color: Colors.white, // Example icon color
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Icon Example')),
      body: Center(child: MyIconWidget()),
    ),
  ));
}


class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text('Hello, World!'),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Add your action here
        },
        child: Icon(Icons.add),
        backgroundColor: Colors.blue,
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endDocked,
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
    return Container(
      child: Wrap(
        direction: Axis.horizontal,
        alignment: WrapAlignment.start,
        children: [
          // Add your widgets here
        ],
      ),
    );
  }
}


class Menu extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Theme(
      data: Theme.of(context),
      child: Scaffold(
        appBar: AppBar(
          title: Text('Menu'),
        ),
        body: Center(
          child: Text('Menu Content'),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Menu(),
  ));
}