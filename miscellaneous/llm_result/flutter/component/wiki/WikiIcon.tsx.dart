
class WikiIcon extends StatelessWidget {
  final dynamic item;
  final double? scale;
  final bool? warship;
  final bool? selected;
  final bool? themeIcon;
  final Map<String, dynamic>? otherProps;

  const WikiIcon({
    Key? key,
    this.item,
    this.scale,
    this.warship,
    this.selected,
    this.themeIcon,
    this.otherProps,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    double width = 80;
    if (scale != null) {
      width *= scale!;
    }

    return Container(
      width: width,
      height: width,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8),
        color: Colors.grey[200],
      ),
      child: Stack(
        children: [
          // Replace with your image source
          Image.asset(
            'assets/icon.png', // Placeholder for the icon image
            width: width,
            height: width,
            fit: BoxFit.cover,
          ),
          Positioned(
            top: 0,
            right: 0,
            child: Container(
              padding: EdgeInsets.symmetric(horizontal: 4, vertical: 2),
              color: Colors.red,
              child: Text(
                'New',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 12,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  final dynamic item; // Replace with your actual item type
  final String theme = AppGlobalData.get(LOCAL.theme);

  MyWidget({required this.item});

  @override
  Widget build(BuildContext context) {
    String imageSrc = item['image'] ?? item['icon'];

    return Image.network(imageSrc);
  }
}


class WarshipWidget extends StatelessWidget {
  final bool warship;
  final dynamic item;
  final String imageSrc;
  final double width;
  final Color themeColor;
  final bool themeIcon;

  WarshipWidget({
    required this.warship,
    this.item,
    required this.imageSrc,
    required this.width,
    required this.themeColor,
    required this.themeIcon,
  });

  @override
  Widget build(BuildContext context) {
    if (warship) {
      return Container(
        width: width,
        height: width / 1.7,
        child: Stack(
          children: [
            if (item != null && item['new'] == true)
              Container(
                width: width,
                height: 20,
                color: themeColor,
              ),
            Image.network(
              imageSrc,
              fit: BoxFit.contain,
              color: themeIcon ? themeColor : null,
            ),
          ],
        ),
      );
    }
    return SizedBox.shrink();
  }
}


class CustomTouchable extends StatelessWidget {
  final bool selected;
  final bool isNew;
  final String imageSrc;
  final Color themeColor;
  final double width;

  const CustomTouchable({
    Key? key,
    required this.selected,
    required this.isNew,
    required this.imageSrc,
    required this.themeColor,
    required this.width,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        // Handle tap
      },
      child: Container(
        decoration: BoxDecoration(
          border: selected ? Border.all(color: themeColor) : null,
        ),
        child: Stack(
          children: [
            if (isNew)
              Container(
                width: width,
                height: width,
                color: themeColor,
              ),
            Image.asset(
              imageSrc,
              width: width,
              height: width,
              fit: BoxFit.contain,
            ),
          ],
        ),
      ),
    );
  }
}


class MyContainer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8),
        border: Border.all(color: Colors.transparent),
      ),
      child: // Add your content here
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        // Your other widgets here
        Positioned(
          bottom: 0,
          child: Container(
            height: 8,
            width: 8,
            decoration: BoxDecoration(
              color: Colors.blue, // Change to your desired color
              borderRadius: BorderRadius.circular(99),
            ),
          ),
        ),
      ],
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
        title: Text('My Home Page'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}