
interface ShipProviderProps {
  children: React.ReactNode;
  special: boolean;
}

interface ShipContextType {
  ships: Ship[];
}

const ShipContext = createContext<ShipContextType | undefined>(undefined);

export const ShipProvider: React.FC<ShipProviderProps> = ({ children, special }) => {
  const [ships, setShips] = useState<Ship[]>([]);

  useEffect(() => {
    const filteredShips = GameRepository.instance.shipList.filter(ship => ship.isSpecialGroup === special);
    setShips(filteredShips);
  }, [special]);

  return (
    <ShipContext.Provider value={{ ships }}>
      {children}
    </ShipContext.Provider>
  );
};

export const useShips = () => {
  const context = useContext(ShipContext);
  if (context === undefined) {
    throw new Error('useShips must be used within a ShipProvider');
  }
  return context;
};


interface Ship {
  // Define the properties of Ship here
}

interface ShipProviderProps {
  children: React.ReactNode;
}

const ShipContext = createContext<{
  shipList: Ship[];
  shipCount: number;
  filterString: string;
  resetFilter: () => void;
} | undefined>(undefined);

export const ShipProvider: React.FC<ShipProviderProps> = ({ children }) => {
  const _logger = new Logger('ShipProvider');
  const [_ships, setShips] = useState<Ship[]>([]); // Initialize with your ship data
  const [_filteredShips, setFilteredShips] = useState<Ship[] | null>(null);

  const shipList = _filteredShips ?? _ships;
  const shipCount = shipList.length;
  const filterString = `-  ${shipCount}`;

  const resetFilter = () => {
    setFilteredShips(null);
    // Notify listeners if needed, e.g., through a state update or context
  };

  return (
    <ShipContext.Provider value={{ shipList, shipCount, filterString, resetFilter }}>
      {children}
    </ShipContext.Provider>
  );
};

export const useShip = () => {
  const context = useContext(ShipContext);
  if (context === undefined) {
    throw new Error('useShip must be used within a ShipProvider');
  }
  return context;
};


const ShipFilterComponent: React.FC<{ ships: Ship[] }> = ({ ships }) => {
  const [filteredShips, setFilteredShips] = useState<Ship[]>(ships);
  const logger = useContext(LoggerContext);

  const showFilter = () => {
    showFilterShipDialog((filter) => {
      logger.fine(`Filter: ${filter}`);
      if (filter.isEmpty) {
        setFilteredShips(ships);
      } else {
        setFilteredShips(ships.filter((ship) => filter.shouldDisplay(ship)));
      }
    });
  };

  return (
    // Your component JSX here
    <></>
  );
};

export default ShipFilterComponent;


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
    backgroundColor: '#F5FCFF',
  },
  text: {
    fontSize: 20,
    margin: 10,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
  },
});

export default App;