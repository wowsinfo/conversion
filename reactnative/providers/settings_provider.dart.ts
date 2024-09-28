
interface DropdownValue {
  value: number;
  title: string;
}

interface SettingsContextType {
  servers: DropdownValue[];
  server: number;
  setServer: (server: number) => void;
}

const SettingsContext = createContext<SettingsContextType | undefined>(undefined);

export const SettingsProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const logger = new Logger('SettingsProvider');
  const userRepository = UserRepository.instance;
  const [server, setServer] = useState(userRepository.gameServer);
  const [servers, setServers] = useState<DropdownValue[]>([]);

  useEffect(() => {
    const localised = Localisation.getInstance(); // Adjust based on actual implementation
    setServers([
      { value: 0, title: localised.server_russia },
      { value: 1, title: localised.server_europe },
      { value: 2, title: localised.server_north_america },
      { value: 3, title: localised.server_asia },
    ]);
  }, []);

  const handleServerChange = (newServer: number) => {
    setServer(newServer);
    logger.info(`Server changed to: ${newServer}`);
  };

  return (
    <SettingsContext.Provider value={{ servers, server, setServer: handleServerChange }}>
      {children}
    </SettingsContext.Provider>
  );
};

export const useSettings = () => {
  const context = useContext(SettingsContext);
  if (context === undefined) {
    throw new Error('useSettings must be used within a SettingsProvider');
  }
  return context;
};


const MyComponent: React.FC = () => {
  const { userRepository } = useContext(AppContext);
  const [server, setServer] = useState<number>(0);
  const [servers, setServers] = useState<DropdownValue<number>[]>([]);
  const colours = AppThemeColour.colourList;

  useEffect(() => {
    // Initialize servers here, for example:
    setServers([
      { title: 'Server 1', value: 0 },
      { title: 'Server 2', value: 1 },
      // Add more servers as needed
    ]);
  }, []);

  const updateServer = (index: number) => {
    console.info(`Updating server to ${servers[index].title}`);
    setServer(index);
    userRepository.gameServer = index;
  };

  return (
    <div>
      {/* Render your dropdown or UI elements here */}
      {servers.map((serverValue, index) => (
        <button key={index} onClick={() => updateServer(index)}>
          {serverValue.title}
        </button>
      ))}
    </div>
  );
};

export default MyComponent;


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