
class MapPage extends StatefulWidget {
  @override
  _MapPageState createState() => _MapPageState();
}

class _MapPageState extends State<MapPage> {
  List<dynamic> data = [];
  bool shown = false;
  String map = '';
  bool loading = true;

  @override
  void initState() {
    super.initState();
    setLastLocation('Map');
    print('WIKI - Map');

    var mapData = AppGlobalData.get(SAVED.map);
    print(mapData);
    setState(() {
      data = mapData;
      loading = false;
    });
  }

  void setLastLocation(String location) {
    // Implement your logic to set the last location
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Wiki Map'),
      ),
      body: loading
          ? Center(child: CircularProgressIndicator())
          : GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: 1,
              ),
              itemCount: data.length,
              itemBuilder: (context, index) {
                return Card(
                  child: Center(
                    child: Text(data[index].toString()),
                  ),
                );
              },
            ),
    );
  }
}


class WoWsInfo extends StatefulWidget {
  @override
  _WoWsInfoState createState() => _WoWsInfoState();
}

class _WoWsInfoState extends State<WoWsInfo> {
  List<Item> data = []; // Replace with your data model
  bool shown = false;
  String map = '';
  bool loading = true;

  @override
  Widget build(BuildContext context) {
    final double imageWidth = MediaQuery.of(context).size.width > MediaQuery.of(context).size.height
        ? MediaQuery.of(context).size.height - 20
        : MediaQuery.of(context).size.width - 20;

    return Scaffold(
      body: Column(
        children: [
          Expanded(
            child: GridView.builder(
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: 1,
              ),
              itemCount: data.length,
              itemBuilder: (context, index) {
                final item = data[index];
                return GestureDetector(
                  onTap: () {
                    setState(() {
                      shown = true;
                      map = item.icon; // Assuming item.icon is a URL
                    });
                  },
                  child: Card(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(item.name),
                        Text(item.description),
                      ],
                    ),
                  ),
                );
              },
            ),
          ),
          if (shown) 
            Dialog(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              child: Container(
                height: imageWidth,
                width: imageWidth,
                child: Stack(
                  children: [
                    CachedNetworkImage(
                      imageUrl: map,
                      fit: BoxFit.cover,
                      height: imageWidth,
                      width: imageWidth,
                      placeholder: (context, url) => Center(child: CircularProgressIndicator()),
                      errorWidget: (context, url, error) => Icon(Icons.error),
                      onImageLoad: (imageInfo, _) {
                        setState(() {
                          loading = false;
                        });
                      },
                    ),
                    if (loading) 
                      Center(child: CircularProgressIndicator()),
                  ],
                ),
              ),
            ),
        ],
      ),
    );
  }
}

class Item {
  final String name;
  final String description;
  final String icon;

  Item({required this.name, required this.description, required this.icon});
}


class MyIndicator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Positioned.fill(
          child: Container(
            color: Colors.transparent,
            child: Center(
              child: CircularProgressIndicator(),
            ),
          ),
        ),
      ],
    );
  }
}


class MapScreen extends StatefulWidget {
  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  late GoogleMapController mapController;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Map'),
      ),
      body: GoogleMap(
        onMapCreated: (GoogleMapController controller) {
          mapController = controller;
        },
        initialCameraPosition: CameraPosition(
          target: LatLng(37.7749, -122.4194), // Example coordinates
          zoom: 10,
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MapScreen(),
  ));
}