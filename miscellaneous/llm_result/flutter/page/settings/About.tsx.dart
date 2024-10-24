
class About extends StatefulWidget {
  @override
  _AboutState createState() => _AboutState();
}

class _AboutState extends State<About> {
  String animation = 'pulse';
  late Timer timer;

  @override
  void initState() {
    super.initState();
    timer = Timer.periodic(Duration(seconds: 2), (Timer t) {
      setState(() {
        animation = getRandomAnimation();
      });
    });
  }

  @override
  void dispose() {
    timer.cancel();
    super.dispose();
  }

  String getRandomAnimation() {
    // Implement your logic to return a random animation type
    List<String> animations = ['pulse', 'bounce', 'fade', 'slide'];
    return (animations..shuffle()).first;
  }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    double imageWidth = size.width > size.height ? size.height * 0.5 : size.width * 0.5;

    return Scaffold(
      body: Center(
        child: RandomAnimatedWidget(
          duration: Duration(seconds: 1),
          animation: animation,
          child: Image.asset(
            'assets/your_image.png', // Replace with your image asset
            width: imageWidth,
          ),
        ),
      ),
    );
  }
}


class WoWsInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(); // Replace with actual content
  }
}

class MyWidget extends StatelessWidget {
  final String aboutGithubLink = 'https://github.com'; // Replace with actual link
  final double imageWidth = 100.0; // Set your desired image width
  final String animation = 'animationName'; // Replace with actual animation name

  void _openURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  @override
  Widget build(BuildContext context) {
    return WoWsInfo(
      child: GestureDetector(
        onTap: () => _openURL(aboutGithubLink),
        child: RiveAnimation.asset(
          'assets/animation.riv', // Replace with your Rive animation file
          fit: BoxFit.cover,
          animations: [animation],
        ),
      ),
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: MyWidget(),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
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
        title: Text('My App'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}