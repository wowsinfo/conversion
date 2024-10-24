
const FilterShipContext = createContext(null);

export const FilterShipProvider = ({ children }) => {
  const regions = GameRepository.instance.shipRegionList;
  const types = GameRepository.instance.shipTypeList;
  const logger = new Logger('FilterShipProvider');

  const [selectedRegion, setSelectedRegion] = useState<Set<number>>(new Set());
  const [selectedType, setSelectedType] = useState<Set<number>>(new Set());
  const [selectedTier, setSelectedTier] = useState<Set<number>>(new Set());

  const get = (key: string) => {
    const name = Localisation.instance.stringOf(key, { prefix: 'IDS_' });

    if (!name) {
      logger.severe(`${key} is invalid!`);
      throw new Error(`Failed to get filter name: ${key}`);
    }

    return name;
  };

  return (
    <FilterShipContext.Provider value={{ regions, types, selectedRegion, setSelectedRegion, selectedType, setSelectedType, selectedTier, setSelectedTier, get }}>
      {children}
    </FilterShipContext.Provider>
  );
};

export const useFilterShip = () => {
  const context = useContext(FilterShipContext);
  if (!context) {
    throw new Error('useFilterShip must be used within a FilterShipProvider');
  }
  return context;
};


const MyComponent: React.FC = () => {
  const { _regions, _types, _selectedRegion, _selectedType, _selectedTier, _context } = useContext(MyContext); // Assuming you have a context for your state

  const regionList = useMemo(() => _regions.map(r => _get(r)), [_regions]);
  const typeList = useMemo(() => _types.map(t => _get(t)), [_types]);
  const tierList = GameInfo.tiers;

  const isRegionSelected = (index: number) => _selectedRegion.includes(index);
  const isTypeSelected = (index: number) => _selectedType.includes(index);
  const isTierSelected = (index: number) => _selectedTier.includes(index);

  const regionFilterName = useMemo(() => {
    const name = Localisation.instance.regionFilterName;
    return `${name} (${Localisation.of(_context).region})`;
  }, [_context]);

  return (
    <div>
      {/* Render your UI components here using regionList, typeList, tierList, etc. */}
    </div>
  );
};

export default MyComponent;


const useRegion = (regionList: string[]) => {
  const [selectedRegion, setSelectedRegion] = useState<number[]>([]);
  const logger = useLogger();

  const updateRegion = (key: string) => {
    const index = regionList.indexOf(key);
    if (selectedRegion.includes(index)) {
      setSelectedRegion(prev => {
        const newSelectedRegion = prev.filter(i => i !== index);
        logger.fine(`${key} is removed from region list`);
        return newSelectedRegion;
      });
    } else {
      setSelectedRegion(prev => {
        const newSelectedRegion = [...prev, index];
        logger.fine(`${key} is added to region list`);
        return newSelectedRegion;
      });
    }
  };

  return { selectedRegion, updateRegion };
};

export default useRegion;


const useTypeList = (typeList: string[]) => {
  const [selectedType, setSelectedType] = useState<number[]>([]);
  const logger = useLogger();

  const updateType = (key: string) => {
    const index = typeList.indexOf(key);
    if (selectedType.includes(index)) {
      setSelectedType(prev => {
        const newSelectedType = prev.filter(item => item !== index);
        logger.fine(`${key} is removed from type list`);
        return newSelectedType;
      });
    } else {
      setSelectedType(prev => {
        const newSelectedType = [...prev, index];
        logger.fine(`${key} is added to type list`);
        return newSelectedType;
      });
    }
  };

  return { selectedType, updateType };
};

export default useTypeList;


const GameInfo = {
  tiers: ['Tier1', 'Tier2', 'Tier3'], // Example tiers
};

const useTierManager = () => {
  const [selectedTier, setSelectedTier] = useState<number[]>([]);
  const logger = useLogger();

  const updateTier = (key: string) => {
    const index = GameInfo.tiers.indexOf(key);
    if (selectedTier.includes(index)) {
      setSelectedTier(prev => {
        const newSelectedTier = prev.filter(i => i !== index);
        logger.fine(`${key} is removed from tier list`);
        return newSelectedTier;
      });
    } else {
      setSelectedTier(prev => {
        logger.fine(`${key} is added to tier list`);
        return [...prev, index];
      });
    }
  };

  return { selectedTier, updateTier };
};

export default useTierManager;


const MyComponent: React.FC = () => {
    const [selectedRegion, setSelectedRegion] = useState<string[]>([]);
    const [selectedType, setSelectedType] = useState<string[]>([]);
    const [selectedTier, setSelectedTier] = useState<string[]>([]);

    const resetAll = () => {
        setSelectedRegion([]);
        setSelectedType([]);
        setSelectedTier([]);
    };

    return (
        <div>
            <button onClick={resetAll}>Reset All</button>
            {/* Other component content */}
        </div>
    );
};

export default MyComponent;

interface ShipFilter {
    name: string;
    tiers: number[];
    regions: string[];
    types: string[];
}

function onFilter(name: string, selectedTier: number[], selectedType: number[], types: string[], selectedRegion: number[], regions: string[]): ShipFilter {
    const tiers = selectedTier.map(e => e + 1);
    const typesList = selectedType.map(e => types[e]);
    const regionsList = selectedRegion.map(e => regions[e]);

    return { name, tiers, regions: regionsList, types: typesList };
}


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