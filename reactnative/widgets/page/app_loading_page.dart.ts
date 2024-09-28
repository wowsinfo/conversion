
const Stack = createStackNavigator();

const AppLoadingPage: React.FC = () => {
  useEffect(() => {
    App.instance.inject();
    GameRepository.instance.initialise().then(() => {
      // Navigate to UpgradePage after initialization
      navigation.navigate('Upgrade');
    });
  }, []);

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <ActivityIndicator size="large" color="#0000ff" />
    </View>
  );
};

const AppNavigator: React.FC = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Loading">
        <Stack.Screen name="Loading" component={AppLoadingPage} options={{ headerShown: false }} />
        <Stack.Screen name="Upgrade" component={UpgradePage} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;


const App = () => {
  return (
    <View style={styles.container}>
      <View style={styles.box} />
      <View style={styles.progressContainer}>
        <ActivityIndicator size="large" color="#0000ff" />
      </View>
      <Text>Loading...</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  box: {
    width: 100,
    height: 100,
    backgroundColor: 'blue',
  },
  progressContainer: {
    padding: 16,
  },
});

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