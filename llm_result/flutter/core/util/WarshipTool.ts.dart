
const SAVED = []; // Define your SAVED constant as needed

String getTierLabel(int tier) {
  if (tier < 1) {
    print('getTierLabel: Invalid tier: $tier');
    return 'O';
  }
  // Add additional logic for valid tiers if needed
  return ''; // Return appropriate label for valid tiers
}


List<String> getTierList() {
  // Replace this with your actual tier list
  return [
    'Tier 1',
    'Tier 2',
    'Tier 3',
    'Tier 4',
    'Tier 5',
    'Tier 6',
    'Tier 7',
    'Tier 8',
    'Tier 9',
    'Tier 10',
    'Tier 11',
    'Tier 12',
    'Tier 13',
    'Tier 14',
    'Tier 15',
  ];
}

String getTierLabel(int tier) {
  final label = getTierList();
  return label[tier - 1];
}

Color getColourWithRange(double min, double curr, double max) {
  if (curr < min) {
    return Color(0xFFFF0000); // Red
  } else if (curr >= min && curr <= max) {
    double ratio = (curr - min) / (max - min);
    int red = (255 * (1 - ratio)).toInt();
    int green = (255 * ratio).toInt();
    return Color.fromARGB(255, red, green, 0); // Gradient from red to green
  } else {
    return Color(0xFF00FF00); // Green
  }
}


String componentToHex(int c) {
  String hex = c.toRadixString(16);
  hex = hex.substring(0, 2);
  return hex.length == 1 ? '0' + hex : hex;
}

  String hex = c.toRadixString(16);
  return hex.length == 1 ? '0$hex' : hex;
}

String rgbToHex(int r, int g, int b) {
  return '#' + componentToHex(r) + componentToHex(g) + componentToHex(b);
}


String rgbToHex(int r, int g, int b) {
  return '#${r.toRadixString(16).padLeft(2, '0')}${g.toRadixString(16).padLeft(2, '0')}${b.toRadixString(16).padLeft(2, '0')}';
}

String getColour(int scale) {
  int g = (255 * scale) ~/ 100;
  int r = (255 * (100 - scale)) ~/ 100;
  String colour = rgbToHex(r, g, 0);
  print(colour);
  return colour;
}

String? getKeyByValue(Map<String, dynamic> object, dynamic value) {
  return object.keys.firstWhere((key) => object[key] == value, orElse: () => '');
}

List<String> getTierList() {
  return ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X', '★'];
}

dynamic filterShip(Map<String, dynamic> data, {List<dynamic>? shipData}) {
  bool premium = data['premium'];
  String name = data['name'];
  List<dynamic> nation = data['nation'];
  List<dynamic> type = data['type'];
  List<dynamic> tier = data['tier'];
  
  print(data);
  if (!premium && name.isEmpty && nation.isEmpty && type.isEmpty && tier.isEmpty) {
    return null;
  }
  return data; // Return the data if it doesn't match the filter criteria
}


class ShipFilter {
  final String name;
  final String nation;
  final String type;
  final String tier;
  final bool premium;
  final Map<String, dynamic> warship;
  final List<dynamic> shipData;

  ShipFilter({
    required this.name,
    required this.nation,
    required this.type,
    required this.tier,
    required this.premium,
    required this.warship,
    required this.shipData,
  });

  List<dynamic> filterShips() {
    String fname = name.toLowerCase();
    Map<String, dynamic> fdata = normalise(nation, type, tier);

    print(fdata);
    List<dynamic> filtered = [];

    if (shipData != null) {
      for (var ship in shipData) {
        var curr = warship[ship['ship_id']];
        // Ignore removed ships
        if (curr == null) {
          continue;
        }
        if (validShip(curr, fname, fdata, premium)) {
          filtered.add(ship);
        }
      }
    }
    return filtered;
  }

  Map<String, dynamic> normalise(String nation, String type, String tier) {
    // Implement normalisation logic here
    return {
      'nation': nation.toLowerCase(),
      'type': type.toLowerCase(),
      'tier': tier.toLowerCase(),
    };
  }

  bool validShip(dynamic curr, String fname, Map<String, dynamic> fdata, bool premium) {
    // Implement validation logic here
    return true; // Placeholder for actual validation logic
  }
}


void filterWarships(Map<String, dynamic> warship, String fname, dynamic fdata, bool premium) {
  for (String ID in warship.keys) {
    var curr = warship[ID];
    if (validShip(curr, fname, fdata, premium)) {
      filtered.add(curr);
    }
  }
}

bool validShip(dynamic curr, String fname, dynamic fdata, bool premium) {
  // Implement your validation logic here
  return true; // Placeholder return value
}

List<dynamic> sorted = filtered;
if (shipData == null) {
  sorted.sort((a, b) {
    // Sort by tier, then by type
    if (a['tier'] == b['tier']) {
      return a['type'].compareTo(b['type']);
    } else {
      return b['tier'].compareTo(a['tier']);
    }
  });
}


bool validShip(Map<String, dynamic> curr, String fname, Map<String, dynamic> fdata, bool premium) {
  bool filterTier = false;
  bool filterName = false;
  bool filterNation = false;
  bool filterType = false;
  bool filterPremium = false;

  var ftier = fdata['tier'];
  var fnation = fdata['nation'];
  var ftype = fdata['type'];

  // It includes this name or name is empty
  if (curr['name'].toLowerCase().contains(fname.toLowerCase()) || fname.trim().isEmpty) {
    filterName = true;
  }

  // Additional filtering logic can be added here based on tier, nation, type, and premium

  return filterName; // Return the result based on the filters applied
}


if (curr.premium == premium || premium == false) {
  filterPremium = true;
}


if (ftier[curr.tier] != null || ftier.isEmpty) {
  filterTier = true;
}


if (fnation[curr.nation] != null || fnation.isEmpty) {
  filterNation = true;
}


if (ftype[curr.type] != null || ftype.isEmpty) {
  filterType = true;
}

  if (filterName && filterNation && filterPremium && filterTier && filterType) {
    return true;
  }
  return false;
}

  return obj.isEmpty;
}

Map<String, Map<String, bool>> normalise(List<dynamic> nation, List<dynamic> type, List<dynamic> tier) {
  Map<String, Map<String, bool>> data = {'nation': {}, 'type': {}, 'tier': {}};

  for (var i in nation) {
    var key = getKeyByValue(AppGlobalData.get(SAVED.encyclopedia)['ship_nations'], i);
    if (key == null) {
      print('normalise: Invalid ship nation: $i');
    } else {
      data['nation'][key] = true;
    }
  }

  return data;
}

dynamic getKeyByValue(Map<dynamic, dynamic> map, dynamic value) {
  return map.keys.firstWhere((k) => map[k] == value, orElse: () => null);
}


class AppGlobalData {
  static Map<String, dynamic> get(String key) {
    // Implement your logic to retrieve data
    return {};
  }
}

class SAVED {
  static const String encyclopedia = 'encyclopedia';
}

class ShipTypeNormalizer {
  final List<String> type;
  final Map<String, bool> data;

  ShipTypeNormalizer(this.type, this.data);

  String? getKeyByValue(Map<String, String> map, String value) {
    return map.entries
        .firstWhere((entry) => entry.value == value, orElse: () => null)
        ?.key;
  }

  void normalize() {
    final shipTypes = AppGlobalData.get(SAVED.encyclopedia)['ship_types'] as Map<String, String>;
    
    type.forEach((i) {
      final key = getKeyByValue(shipTypes, i);
      if (key == null) {
        print('normalise: Invalid ship type: $i');
      } else {
        data[key] = true;
      }
    });
  }
}

  List<String> tierList = getTierList();
  
  for (String i in tier) {
    int index = tierList.indexOf(i);
    if (index == -1) {
      print('normalize: Invalid ship tier: ' + i);
    } else {
      data['tier'][index + 1] = true;
    }
  }
}

List<String> getTierList() {
  // Implement your tier list logic here
  return ['Tier1', 'Tier2', 'Tier3', 'Tier4', 'Tier5']; // Example tiers
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: fetchData(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return CircularProgressIndicator();
        } else if (snapshot.hasError) {
          return Text('Error: ${snapshot.error}');
        } else {
          final data = snapshot.data;
          return Text('Data: $data');
        }
      },
    );
  }

  Future<String> fetchData() async {
    // Simulate a network call
    await Future.delayed(Duration(seconds: 2));
    return 'Fetched Data';
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Flutter Example')),
      body: MyWidget(),
    ),
  ));
}