
type SimilarChartValue = { label: string; value: number };

const SimilarShipProvider: React.FC<{ ship: Ship }> = ({ ship }) => {
  const logger = new Logger('SimilarShipProvider');
  const [similarShips, setSimilarShips] = useState<Ship[]>([]);

  useEffect(() => {
    const fetchSimilarShips = () => {
      const ships = GameRepository.instance.shipList;
      const similar = ships.filter((s) =>
        s.id !== ship.id &&
        s.type === ship.type &&
        s.tier === ship.tier &&
        s.isSpecialGroup === ship.isSpecialGroup
      );
      setSimilarShips(similar);
    };

    fetchSimilarShips();
  }, [ship]);

  const hasSimilarShips = similarShips.length > 0;

  return (
    <div>
      {hasSimilarShips ? (
        <ul>
          {similarShips.map((similarShip) => (
            <li key={similarShip.id}>{similarShip.name}</li>
          ))}
        </ul>
      ) : (
        <p>No similar ships found.</p>
      )}
    </div>
  );
};

export default SimilarShipProvider;


const YourComponent = ({ similarShips, ship }) => {
  const [chartHeight, setChartHeight] = useState(0);
  const [averageDamage, setAverageDamage] = useState(0);
  const [averageWinrate, setAverageWinrate] = useState(0);
  const [averageFrags, setAverageFrags] = useState(0);
  const [totalBattles, setTotalBattles] = useState(0);
  const [winrateSeries, setWinrateSeries] = useState<Series<ChartValue, string>[]>([]);
  const [fragsSeries, setFragsSeries] = useState<Series<ChartValue, string>[]>([]);
  const [damageSeries, setDamageSeries] = useState<Series<ChartValue, string>[]>([]);
  const [battleSeries, setBattleSeries] = useState<Series<ChartValue, string>[]>([]);

  useEffect(() => {
    const screenHeight = Dimensions.get('window').height;
    setChartHeight(25 * Math.max(similarShips.length, 5));

    extractShipAdditional();
  }, [similarShips, ship]);

  const extractShipAdditional = async () => {
    const damage = new AverageCounter();
    const frags = new AverageCounter();
    const winrate = new AverageCounter();
    const battles = new TotalCounter();
    const damageChart: ChartValue[] = [];
    const fragsChart: ChartValue[] = [];
    const winrateChart: ChartValue[] = [];
    const battlesChart: ChartValue[] = [];

    const allShips = [...similarShips, ship];
    for (const ship of allShips) {
      const info = await GameRepository.instance.shipAdditionalOf(ship.id.toString());
      if (!info) {
        console.warn(`No ship additional info for ${ship.name}`);
        continue;
      }

      damage.add(info.damage);
      frags.add(info.frags);
      winrate.add(info.winrate);
      const shipBattleCount = info.battles ?? 0;
      battles.add(shipBattleCount);

      const shipName = Localisation.instance.stringOf(ship.name);
      if (!shipName) {
        console.error(`No localisation for ${ship.name}, unknown ship`);
        continue;
      }

      damageChart.push(new ChartValue(shipName, info.damage));
      fragsChart.push(new ChartValue(shipName, info.frags));
      winrateChart.push(new ChartValue(shipName, info.winrate));
      battlesChart.push(new ChartValue(shipName, shipBattleCount));
    }

    setAverageDamage(damage.average);
    setAverageFrags(frags.average);
    setAverageWinrate(winrate.average);
    setTotalBattles(battles.total);

    damageChart.sort((a, b) => b.value - a.value);
    setDamageSeries([
      ChartUtils.convertDefault('damage', {
        values: damageChart,
        color: ChartUtils.damageColour,
        labelFormatter: (v) => v.value.toDecimalString(),
      }),
    ]);

    fragsChart.sort((a, b) => b.value - a.value);
    setFragsSeries([
      ChartUtils.convertDefault('frags', {
        values: fragsChart,
        color: ChartUtils.fragsColour,
        labelFormatter: (v) => v.value.toDecimalString(),
      }),
    ]);

    winrateChart.sort((a, b) => b.value - a.value);
    setWinrateSeries([
      ChartUtils.convertDefault('winrate', {
        values: winrateChart,
        color: ChartUtils.winrateColour,
        labelFormatter: (v) => v.value.asPercentString(),
      }),
    ]);

    battlesChart.sort((a, b) => b.value - a.value);
    setBattleSeries([
      ChartUtils.convertDefault('battles', {
        values: battlesChart,
        color: ChartUtils.battleColour,
        labelFormatter: (v) => v.value.toDecimalString(),
      }),
    ]);
  };

  return (
    // Your component JSX goes here, using chartHeight, averageDamage, etc.
  );
};

export default YourComponent;


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