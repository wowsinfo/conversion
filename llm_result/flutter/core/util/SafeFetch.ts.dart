
class SafeFetch {
  /// Make sure language is at the end
  static Future<Map<String, dynamic>> get(String api, [String? lang, List<dynamic>? extra]) async {
    if (extra != null && extra.length > 1) {
      lang = extra.removeLast() as String?;
    }
    String link = api.replaceAllMapped(RegExp(r'\{(\d+)\}'), (match) => extra![int.parse(match[1]!)].toString()) + (lang ?? '');
    print('SafeFetch\n$link');
    
    final response = await http.get(Uri.parse(link));
    
    if (response.statusCode == 200) {
      final json = jsonDecode(response.body);
      if (json != null && json['status'] == 'ok') {
        return json;
      } else {
        return {};
      }
    } else {
      return {};
    }
  }
}


class ApiService {
  static Future<Map<String, dynamic>> normal(String api) async {
    print('NormalFetch\n$api');
    try {
      final response = await http.get(Uri.parse(api));
      if (response.statusCode == 200) {
        final json = jsonDecode(response.body);
        return SafeValue(json, {});
      }
    } catch (err) {
      print(err);
    }
    return {};
  }

  static Map<String, dynamic> SafeValue(Map<String, dynamic> json, Map<String, dynamic> defaultValue) {
    // Implement your SafeValue logic here
    return json.isNotEmpty ? json : defaultValue;
  }
}


class SafeFetch extends StatelessWidget {
  final String url;

  SafeFetch({required this.url});

  Future<Map<String, dynamic>> fetchData() async {
    try {
      final response = await http.get(Uri.parse(url));
      if (response.statusCode == 200) {
        return json.decode(response.body);
      } else {
        throw Exception('Failed to load data');
      }
    } catch (e) {
      throw Exception('Failed to fetch data: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Map<String, dynamic>>(
      future: fetchData(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return Center(child: CircularProgressIndicator());
        } else if (snapshot.hasError) {
          return Center(child: Text('Error: ${snapshot.error}'));
        } else {
          final data = snapshot.data!;
          return Center(child: Text('Data: ${data.toString()}'));
        }
      },
    );
  }
}