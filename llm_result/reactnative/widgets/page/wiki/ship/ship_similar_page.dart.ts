
interface ShipSimilarPageProps {
  ship: Ship;
}

const ShipSimilarPage: React.FC<ShipSimilarPageProps> = ({ ship }) => {
  const provider = new SimilarShipProvider(ship);
  provider.extractShipAdditional();
  const chartHeight = provider.chartHeight;

  const buildChart = (list: any[], title: string, subtitle: string) => (
    <View style={styles.chartContainer}>
      <Text style={styles.chartTitle}>{title}</Text>
      <Text style={styles.chartSubtitle}>{subtitle}</Text>
      <BarChart
        data={{
          labels: list.map(item => item.label),
          datasets: [
            {
              data: list.map(item => item.value),
            },
          ],
        }}
        width={320}
        height={chartHeight}
        yAxisLabel=""
        chartConfig={{
          backgroundColor: '#ffffff',
          backgroundGradientFrom: '#ffffff',
          backgroundGradientTo: '#ffffff',
          decimalPlaces: 2,
          color: (opacity = 1) => `rgba(0, 0, 255, ${opacity})`,
          labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
          style: {
            borderRadius: 16,
          },
          propsForDots: {
            r: '6',
            strokeWidth: '2',
            stroke: '#ffa726',
          },
        }}
        style={{
          marginVertical: 8,
          borderRadius: 16,
        }}
      />
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.header}>{useLocalization().warship_compare_similar}</Text>
      <ScrollView contentContainerStyle={styles.scrollView}>
        {buildChart(provider.damageSeries, useLocalization().warship_avg_damage, provider.averageDamage)}
        {buildChart(provider.winrateSeries, useLocalization().warship_avg_winrate, provider.averageWinrate)}
        {buildChart(provider.fragsSeries, useLocalization().warship_avg_frag, provider.averageFrags)}
        {buildChart(provider.battleSeries, Localisation.instance.battles, provider.totalBattles)}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  header: {
    fontSize: 20,
    fontWeight: 'bold',
    margin: 20,
  },
  scrollView: {
    alignItems: 'center',
  },
  chartContainer: {
    margin: 10,
    width: '90%',
  },
  chartTitle: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  chartSubtitle: {
    fontSize: 14,
    color: 'gray',
  },
});

export default ShipSimilarPage;


interface ChartValue {
  x: string;
  y: number;
}

interface BuildChartProps {
  height: number;
  list: ChartValue[];
  title: string;
  subtitle: string;
}

const BuildChart: React.FC<BuildChartProps> = ({ height, list, title, subtitle }) => {
  return (
    <View style={[styles.container, { height }]}>
      <Text style={styles.title}>{title}</Text>
      <Text style={styles.subtitle}>{subtitle}</Text>
      <VictoryChart theme={VictoryTheme.material}>
        <VictoryBar data={list} />
      </VictoryChart>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  subtitle: {
    fontSize: 16,
    marginBottom: 8,
  },
});

export default BuildChart;


const ShipSimilarPage = ({ title, subtitle, list, height }) => {
  const { colors } = useTheme();
  const isDark = colors.background === 'black';

  const maxWidth = 500;
  const screenWidth = Dimensions.get('window').width;
  let chartWidth;

  if (maxWidth * 4 < screenWidth) {
    chartWidth = screenWidth / 4;
  } else if (maxWidth * 2 < screenWidth) {
    chartWidth = screenWidth / 2;
  } else {
    chartWidth = screenWidth;
  }

  const logger = new Logger('ShipSimilarPage');
  logger.info(`chartWidth: ${chartWidth}`);
  logger.info(`screenWidth: ${screenWidth}`);

  return (
    <View style={[styles.container, { maxWidth: chartWidth }]}>
      <Text style={styles.title}>{title}</Text>
      <Text style={styles.subtitle}>{subtitle}</Text>
      <View style={{ height }}>
        <BarChart
          data={{
            labels: list.map(item => item.label),
            datasets: [
              {
                data: list.map(item => item.value),
              },
            ],
          }}
          width={chartWidth}
          height={height}
          yAxisLabel=""
          chartConfig={{
            backgroundColor: isDark ? '#000' : '#fff',
            backgroundGradientFrom: isDark ? '#000' : '#fff',
            backgroundGradientTo: isDark ? '#000' : '#fff',
            decimalPlaces: 2,
            color: (opacity = 1) => (isDark ? `rgba(255, 255, 255, ${opacity})` : `rgba(0, 0, 0, ${opacity})`),
            labelColor: (opacity = 1) => (isDark ? `rgba(255, 255, 255, ${opacity})` : `rgba(0, 0, 0, ${opacity})`),
            style: {
              borderRadius: 16,
            },
            propsForDots: {
              r: '6',
              strokeWidth: '2',
              stroke: isDark ? '#fff' : '#000',
            },
          }}
          vertical={false}
          fromZero={true}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
  },
  subtitle: {
    fontSize: 16,
    color: 'gray',
  },
});

export default ShipSimilarPage;


const App: React.FC = () => {
  const [count, setCount] = React.useState(0);

  const increment = () => {
    setCount(count + 1);
  };

  const decrement = () => {
    setCount(count - 1);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Count: {count}</Text>
      <View style={styles.buttonContainer}>
        <Button title="Increment" onPress={increment} />
        <Button title="Decrement" onPress={decrement} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    fontSize: 24,
    marginBottom: 20,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
  },
});

export default App;