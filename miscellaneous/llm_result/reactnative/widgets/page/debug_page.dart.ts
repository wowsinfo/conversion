
const DebugPage: React.FC = () => {
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      await loadAppData();
      setLoaded(true);
    };
    fetchData();
  }, []);

  if (!loaded) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ActivityIndicator size="large" color="#0000ff" />
      </View>
    );
  }

  return (
    <View>
      {/* Add your content here after loading */}
    </View>
  );
};

export default DebugPage;


const DebugPage: React.FC = () => {
  const navigation = useNavigation();

  const renderTest = () => {
    return (
      <View style={styles.testContainer}>
        <Text>Test Content</Text>
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Debug Page</Text>
      </View>
      <View style={styles.body}>
        {renderTest()}
      </View>
      <TouchableOpacity
        style={styles.fab}
        onPress={() => navigation.navigate('SearchPage')}
      >
        <Text style={styles.fabIcon}>🔍</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    padding: 16,
    backgroundColor: '#6200ee',
  },
  title: {
    color: '#fff',
    fontSize: 20,
  },
  body: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  testContainer: {
    padding: 20,
  },
  fab: {
    position: 'absolute',
    bottom: 30,
    right: 30,
    backgroundColor: '#6200ee',
    borderRadius: 30,
    padding: 16,
  },
  fabIcon: {
    color: '#fff',
    fontSize: 24,
  },
});

export default DebugPage;


const Stack = createStackNavigator();

const App = () => {
  const [loaded, setLoaded] = useState(false);
  const [elbing, setElbing] = useState(null);

  useEffect(() => {
    // Simulate loading data
    const fetchData = async () => {
      const ship = await GameRepository.instance.shipOf('4074649392');
      setElbing(ship);
      setLoaded(true);
    };
    fetchData();
  }, []);

  const showFilterShipDialog = () => {
    Alert.alert('Ship Filter Dialog', 'Filter options go here');
  };

  const showShipPenetrationDialog = () => {
    Alert.alert('Elbing Penetration Dialog', 'Penetration details go here');
  };

  if (!loaded) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ActivityIndicator />
      </View>
    );
  }

  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={HomeScreen} />
        {/* Add other screens here */}
      </Stack.Navigator>
    </NavigationContainer>
  );

  function HomeScreen() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <View style={{ alignItems: 'center' }}>
          <Text>Pages</Text>
          <Button title={Localisation.of(context).wiki_upgrades} onPress={() => navigation.navigate('UpgradePage')} />
          <Button title={Localisation.of(context).wiki_warships} onPress={() => navigation.navigate('ShipPage')} />
          <Button title="Compare" onPress={() => navigation.navigate('CompareShipPage')} />
          <Button title="Ships (Special)" onPress={() => navigation.navigate('ShipPage', { special: true })} />
          <Button title="Achievements" onPress={() => navigation.navigate('AchievementPage')} />
          <Button title={Localisation.of(context).wiki_skills} onPress={() => navigation.navigate('CommanderSkillPage')} />
          <Button title={Localisation.of(context).settings_app_settings} onPress={() => navigation.navigate('AppSettingsPage')} />
        </View>
        <View style={{ alignItems: 'center' }}>
          <Text>Dialogs</Text>
          <Button title="Ship Filter Dialog" onPress={showFilterShipDialog} />
          <Button title="Elbing Penetration Dialog" onPress={showShipPenetrationDialog} />
        </View>
        <View style={{ alignItems: 'center' }}>
          <Text>Game Languages</Text>
          {Localisation.instance.supportedGameLanguages.map((lang) => (
            <Button key={lang} title={lang} onPress={() => Localisation.instance.updateDataLanguage(lang)} />
          ))}
        </View>
      </View>
    );
  }
};

export default App;


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