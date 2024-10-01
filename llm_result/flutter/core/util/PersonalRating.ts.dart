
double roundTo(double value) {
  return double.parse(value.toStringAsFixed(2));
}

class SafeValue {
  // Implement SafeValue methods and properties as needed
}

class AppGlobalData {
  static Map<String, dynamic> get(String key) {
    // Implement the method to retrieve data based on the key
    return {};
  }
}

const SAVED = {
  'pr': 'player_rating', // Example key, adjust as necessary
};

const lang = {
  // Define language constants as needed
};

double getOverall(String id) {
  return AppGlobalData.get(SAVED['pr'])[id];
}

double calRating(
  double actualDmg,
  double expectedDmg,
  double actualWins,
  double expectedWins,
  double actualFrags,
  double expectedFrags,
) {
  final rDmg = actualDmg / expectedDmg;
  final rWins = actualWins / expectedWins;
  final rFrags = actualFrags / expectedFrags;

  final nDmg = (rDmg - 0.4).clamp(0, double.infinity) / (1 - 0.4);
  final nFrags = (rFrags - 0.1).clamp(0, double.infinity) / (1 - 0.1);
  final nWins = (rWins - 0.7).clamp(0, double.infinity) / (1 - 0.7);

  double rating = roundTo(700 * nDmg + 300 * nFrags + 150 * nWins);
  return rating.clamp(0, 9999);
}

double getOverallRating(Map<String, dynamic> ships) {
  if (ships == null) {
    return -1;
  }
  // Implement logic to calculate overall rating based on ships
  return 0; // Placeholder return value, adjust as necessary
}


class Ship {
  int rating = -1;
  double ap = 0;
  double avgDmg = 0;
  double avgWinrate = 0;
  double avgFrags = 0;
  Map<String, dynamic> pvp;
  String shipId;

  Ship(this.pvp, this.shipId);
}

double safeValue(Map<String, dynamic> map, String key, [dynamic defaultValue]) {
  return map.containsKey(key) ? map[key] : defaultValue;
}

Map<String, dynamic> getOverall(String shipId) {
  // Implement your logic to get overall data based on shipId
  return {
    'battles': 100,
    'average_damage_dealt': 1500,
    'average_frags': 2.5,
    'win_rate': 55.0
  };
}

double calRating(double currAvgDmg, double averageDamageDealt, double currWinrate, double winRate, double currFrags, double averageFrags) {
  // Implement your rating calculation logic
  return (currAvgDmg + currWinrate + currFrags) / 3;
}

double getAP(double rating, int battles) {
  // Implement your AP calculation logic
  return rating * battles / 100;
}

void calculateShipStats(List<Ship> ships) {
  double actualDmg = 0,
      expectedDmg = 0,
      actualWins = 0,
      expectedWins = 0,
      actualFrags = 0,
      expectedFrags = 0;

  for (var ship in ships) {
    ship.rating = -1;
    ship.ap = 0;

    var pvp = safeValue(ship.pvp, 'pvp', null);
    if (pvp != null) {
      var overall = getOverall(ship.shipId);
      if (overall == null) {
        continue;
      }

      var battles = pvp['battles'];
      var damageDealt = pvp['damage_dealt'];
      var frags = pvp['frags'];
      var wins = pvp['wins'];
      var averageDamageDealt = overall['average_damage_dealt'];
      var averageFrags = overall['average_frags'];
      var winRate = overall['win_rate'];

      if (battles == 0) {
        continue;
      }
      double currAvgDmg = damageDealt / battles;
      double currWinrate = (wins / battles) * 100;
      double currFrags = frags / battles;
      ship.avgDmg = currAvgDmg;
      ship.avgWinrate = currWinrate;
      ship.avgFrags = currFrags;

      // Accumulate data
      actualDmg += currAvgDmg;
      actualWins += currWinrate;
      actualFrags += currFrags;
      expectedDmg += averageDamageDealt;
      expectedWins += winRate;
      expectedFrags += averageFrags;

      // Calculate rating and ap
      double rating = calRating(
        currAvgDmg,
        averageDamageDealt,
        currWinrate,
        winRate,
        currFrags,
        averageFrags,
      );
      ship.rating = rating;
      ship.ap = getAP(rating, battles);
    }
  }
}

    double actualDmg,
    double expectedDmg,
    double actualWins,
    double expectedWins,
    double actualFrags,
    double expectedFrags,
) {
  // Implement the logic for calculating the rating based on the parameters
  // This is a placeholder for the actual implementation
  return (actualDmg / expectedDmg + actualWins / expectedWins + actualFrags / expectedFrags) / 3;
}

double getAP(double rating, int battle) {
  if (rating == -1 || battle == 0) {
    return 0;
  }
  // Implement the logic for calculating AP based on the rating and battle
  return rating / battle;
}


double roundTo(double value) {
  return double.parse(value.toStringAsFixed(2));
}

double calculateRating(double battle, double rating) {
  return roundTo(log10(max(10, battle)) * rating);
}

  return [0, 750, 1100, 1350, 1550, 1750, 2100, 2450, 9999];
}

/// Returns the index of the range that the given rating falls into.
/// @param rating The personal rating number
/// @returns The index of the rating. If the rating is invalid, returns 0 which is unknown
int getRatingIndex([int? rating]) {
  if (rating == null) {
    return 0;
  }

  List<int> ranges = getRatingRange();
  for (int i = 0; i < ranges.length - 1; i++) {
    if (rating >= ranges[i] && rating < ranges[i + 1]) {
      return i + 1;
    }
  }
  return ranges.length - 1; // If rating is above the last range
}


List<double> getRatingRange() {
  return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9999];
}

int getRatingIndex(double? rating) {
  final range = getRatingRange();
  final index = range.indexWhere((r) => rating! < r);
  return index == -1 ? 0 : index;
}

List<String> getColourList() {
  return [
    '#607D8B', // blue gray
    '#D32F2F', // red700
    '#FF9800', // orange
    '#FFB300', // amber600
    '#7CB342', // light green 600
    '#388E3C', // green700
    '#03A9F4', // blue (replacing cyan)
    '#9C27B0', // purple
    '#673AB7', // deep purple
    'black',
  ];
}

String getColour(double? rating) {
  final colours = getColourList();
  return colours[getRatingIndex(rating)].isNotEmpty ? colours[getRatingIndex(rating)] : '#607D8B';
}

List<String> getRatingList() {
  return [
    'Unknown',
    'Bad',
    'Below Average',
    'Average',
    'Good',
    'Very Good',
    'Great',
    'Unicum',
    'Super Unicum',
  ];
}

String getComment(double rating) {
  final comments = getRatingList();
  int index = getRatingIndex(rating);
  String comment = comments[index];
  double range = getRatingRange()[index];

  double diff = range - rating;
  // Prevent big numbers
  if (range == 9999) {
    diff = rating - 2450;
  }
  
  return comment;
}

  return '$comment (+$diff)';
}