
class CompareShipProvider {
  private logger: Logger;
  @observable private filter: ShipFilter | null = null;

  constructor() {
    this.logger = Logger.getLogger('CompareShipProvider');
    makeAutoObservable(this);
  }

  @action
  setFilter(value: ShipFilter) {
    if (this.filter === value) return;
    this._compareShip(value);
  }

  private _compareShip(value: ShipFilter) {
    // Implement the comparison logic here
    this.filter = value;
    this.logger.info('Filter set to:', value);
  }
}

export default CompareShipProvider;


const useShipProvider = (filter: ShipFilter) => {
  const [ships, setShips] = useState<ShipInfoProvider[]>([]);
  const logger = useLogger();

  useEffect(() => {
    const shipList = GameRepository.instance.shipList;
    const filteredShips = shipList.filter(s => filter.shouldDisplay(s))
                                   .map(s => new ShipInfoProvider(s));
    setShips(filteredShips);
    logger.info(`CompareShipProvider updated with ${filteredShips.length} ships`);
  }, [filter]);

  return ships;
};

export default useShipProvider;


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