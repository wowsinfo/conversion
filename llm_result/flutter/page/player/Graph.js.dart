
class Graph extends StatefulWidget {
  final List<dynamic> data;

  Graph({Key? key, required this.data}) : super(key: key);

  @override
  _GraphState createState() => _GraphState();
}

class _GraphState extends State<Graph> {
  late Map<String, int> tierInfo;
  late Map<String, int> nationInfo;
  late Map<String, int> typeInfo;
  late int totalBattle;

  @override
  void initState() {
    super.initState();
    tierInfo = {};
    nationInfo = {};
    typeInfo = {};
    totalBattle = 0;
    _processData(widget.data);
  }

  void _processData(List<dynamic> data) {
    for (var ship in data) {
      final pvp = ship['pvp'];
      final shipId = ship['ship_id'];
      final battles = pvp['battles'];

      var curr = AppGlobalData.get(SAVED.warship)[shipId];
      if (curr == null) {
        continue;
      }

      final nation = curr['nation'];
      final tier = curr['tier'];
      final type = curr['type'];

      tierInfo[tier] = (tierInfo[tier] ?? 0) + battles;
      nationInfo[nation] = (nationInfo[nation] ?? 0) + battles;
      typeInfo[type] = (typeInfo[type] ?? 0) + battles;
      totalBattle += battles;
    }
  }

  List<charts.Series<ChartData, String>> objToChart(Map<String, int> info) {
    return [
      charts.Series<ChartData, String>(
        id: 'ChartData',
        colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
        domainFn: (ChartData data, _) => data.label,
        measureFn: (ChartData data, _) => data.value,
        data: info.entries
            .map((entry) => ChartData(entry.key, entry.value))
            .toList(),
      )
    ];
  }

  double getAvgTier(Map<String, int> tierInfo) {
    if (totalBattle == 0) return 0.0;
    double totalTier = 0.0;
    tierInfo.forEach((tier, battles) {
      totalTier += int.parse(tier) * battles;
    });
    return totalTier / totalBattle;
  }

  @override
  Widget build(BuildContext context) {
    final tierChart = objToChart(tierInfo);
    final avgTier = getAvgTier(tierInfo);
    final nationChart = objToChart(nationInfo);
    final typeChart = objToChart(typeInfo);

    return Scaffold(
      appBar: AppBar(title: Text('Graph')),
      body: SingleChildScrollView(
        child: Column(
          children: [
            Container(
              height: 300,
              child: charts.BarChart(tierChart),
            ),
            Text('Average Tier: ${avgTier.toStringAsFixed(2)}'),
            Container(
              height: 300,
              child: charts.PieChart(nationChart),
            ),
            Container(
              height: 300,
              child: charts.PieChart(typeChart),
            ),
          ],
        ),
      ),
    );
  }
}

class ChartData {
  final String label;
  final int value;

  ChartData(this.label, this.value);
}

  // Key will be x and Value will be y
  Map<String, List<dynamic>> chart = {'x': [], 'y': []};
  
  obj.forEach((key, val) {
    if (val == 0 || val < min) {
      return;
    }

    String label = key;
    if (name.containsKey(key)) {
      label = name[key]!;
    }

    chart['x']!.add(label);
    chart['y']!.add(val);
  });
  
  print(chart);
  return chart;
}

  double weight = 0;
  double total = 0;
  tier.forEach((key, curr) {
    weight += curr * double.parse(key);
    total += curr;
  });
  return (total > 0) ? (weight / total).toStringAsFixed(1) : '0.0';
}


class MyChartPage extends StatefulWidget {
  @override
  _MyChartPageState createState() => _MyChartPageState();
}

class _MyChartPageState extends State<MyChartPage> {
  Map<String, dynamic> tier = {
    'x': ['Tier 1', 'Tier 2', 'Tier 3'],
    'y': [10, 20, 30],
  };
  Map<String, dynamic> nation = {
    'x': ['Nation A', 'Nation B', 'Nation C'],
    'y': [15, 25, 10],
  };
  Map<String, dynamic> type = {
    'x': ['Type X', 'Type Y', 'Type Z'],
    'y': [20, 30, 10],
  };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('WoWs Info'),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            BarChart(
              BarChartData(
                barGroups: tier['y'].asMap().entries.map((entry) {
                  int index = entry.key;
                  double value = entry.value.toDouble();
                  return BarChartGroupData(
                    x: index,
                    barRods: [
                      BarChartRodData(toY: value, color: Colors.blue),
                    ],
                  );
                }).toList(),
                titlesData: FlTitlesData(
                  leftTitles: SideTitles(showTitles: true),
                  bottomTitles: SideTitles(
                    showTitles: true,
                    getTitlesWidget: (value, meta) {
                      return Text(tier['x'][value.toInt()]);
                    },
                  ),
                ),
              ),
            ),
            PieChart(
              PieChartData(
                sections: nation['y'].asMap().entries.map((entry) {
                  int index = entry.key;
                  double value = entry.value.toDouble();
                  return PieChartSectionData(
                    value: value,
                    title: nation['x'][index],
                    color: Colors.primaries[index % Colors.primaries.length],
                  );
                }).toList(),
              ),
            ),
            PieChart(
              PieChartData(
                sections: type['y'].asMap().entries.map((entry) {
                  int index = entry.key;
                  double value = entry.value.toDouble();
                  return PieChartSectionData(
                    value: value,
                    title: type['x'][index],
                    color: Colors.primaries[index % Colors.primaries.length],
                  );
                }).toList(),
              ),
            ),
          ],
        ),
      ),
    );
  }
}


class Graph extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Graph Example'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: LineChart(
          LineChartData(
            gridData: FlGridData(show: false),
            titlesData: FlTitlesData(
              leftTitles: SideTitles(showTitles: true),
              bottomTitles: SideTitles(showTitles: true),
            ),
            borderData: FlBorderData(
              show: true,
              border: Border.all(color: const Color(0xff37434d), width: 1),
            ),
            minX: 0,
            maxX: 6,
            minY: 0,
            maxY: 6,
            lineBarsData: [
              LineChartBarData(
                spots: [
                  FlSpot(0, 1),
                  FlSpot(1, 3),
                  FlSpot(2, 2),
                  FlSpot(3, 5),
                  FlSpot(4, 4),
                  FlSpot(5, 6),
                ],
                isCurved: true,
                colors: [Colors.blue],
                dotData: FlDotData(show: false),
                belowBarData: BarAreaData(show: false),
              ),
            ],
          ),
        ),
      ),
    );
  }
}