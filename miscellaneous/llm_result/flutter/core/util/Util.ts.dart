
class Utils {
  /// Deep clone an object
  static dynamic copy(dynamic value) {
    return jsonDecode(jsonEncode(value));
  }

  /// Return a number between 0 to range - 1
  static int random(int range) {
    return Random().nextInt(range);
  }

  static double roundTo(double? num, [int digit = 0]) {
    if (num == null) {
      return -1.0;
    }
    return double.parse(num.toStringAsFixed(digit));
  }
}

  if (num.isNaN || num.isInfinite) {
    return -1.0;
  }
  return num;
}


double formatNumber(double num, int digit) {
  return double.parse(num.toStringAsFixed(digit));
}

/// Get date difference in days
int dayDifference(int time) {
  int timeDiff = (DateTime.now().millisecondsSinceEpoch / 1000).abs().toInt() - time;
  return ((timeDiff / (3600 * 24)).ceil());
}

/// Get a readable date string
String humanTimeString(int? time) {
  if (time == null) {
    return 'Unknown'; // Replace with your localization string
  }
  DateTime dateTime = DateTime.fromMillisecondsSinceEpoch(time * 1000);
  return DateFormat.yMMMd().format(dateTime);
}

  if (time == 0) {
    return '---';
  }
  // Add additional logic for other time values if needed
  return time.toString();
}


String formatDateTime(int time) {
  DateTime obj = DateTime.fromMillisecondsSinceEpoch(time * 1000);
  return '${obj.toIso8601String().substring(0, 10).replaceAll('-', '.')} ${TimeOfDay.fromDateTime(obj).format(BuildContext())}';
}

String getRandomAnimation() {
  List<String> list = [
    'bounce',
    'flash',
    'pulse',
    'rotate',
    'rubberBand',
    'shake',
    'swing',
    'tada',
    'wobble',
  ];
  return list[Random().nextInt(list.length)];
}

double bestCellWidth(double target) {
  double deviceWidth = MediaQueryData.fromWindow(WidgetsBinding.instance.window).size.width;
  double usualCount = deviceWidth / target;
  if (usualCount > 6) {
    return deviceWidth / 6;
  }
  return target;
}


double bestCellWidthEven(double target) {
  double deviceWidth = MediaQueryData.fromWindow(WidgetsBinding.instance.window).size.width;
  double usualCount = deviceWidth / target;
  double result = deviceWidth / usualCount.floor();
  return result;
}

double bestWidth(double width, {double? deviceWidth}) {
  deviceWidth ??= MediaQueryData.fromWindow(WidgetsBinding.instance.window).size.width;
  int maxCount = (deviceWidth / width).round();
  return deviceWidth / (maxCount > 0 ? maxCount : 1);
}

double currDeviceWidth() {
  return MediaQueryData.fromWindow(WidgetsBinding.instance.window).size.width;
}