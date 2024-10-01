
class TimeTracker {
  private timer: Date;

  constructor() {
    this.timer = new Date();
    this.logTime();
  }

  private logTime() {
    console.log('TimeTracker initialized at:', this.timer);
  }
}

const App = () => {
  useEffect(() => {
    const timeTracker = new TimeTracker();
    return () => {
      // Cleanup if necessary
    };
  }, []);

  return null; // Replace with your component's UI
};

export default App;


const MyComponent: React.FC = () => {
  const _timer = useRef(new Date());
  const _logger = new Logger();

  const log = (message?: string) => {
    const duration = new Date().getTime() - _timer.current.getTime();
    _logger.info(`${message ?? 'Time'} ${duration} ms`);
  };

  useEffect(() => {
    // Example usage of log function
    const interval = setInterval(() => {
      log('Elapsed time');
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return (
    <Text>Check the console for logged time.</Text>
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