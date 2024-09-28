
const logger = new Logger('ScrollProvider');

interface ScrollProviderProps {
  scrollY: Animated.Value;
  animation: Animated.Value;
  height: number;
}

const ScrollProvider: React.FC<ScrollProviderProps> = ({ scrollY, animation, height }) => {
  const [display, setDisplay] = useState(true);
  const [offset, setOffset] = useState(0);

  useEffect(() => {
    const listenerId = scrollY.addListener(({ value }) => {
      if (value > height) {
        setDisplay(false);
      } else {
        setDisplay(true);
      }
      setOffset(display ? value : 0);
    });

    return () => {
      scrollY.removeListener(listenerId);
    };
  }, [scrollY, height, display]);

  return (
    <Animated.View style={{ transform: [{ translateY: offset }] }}>
      {/* Your content goes here */}
    </Animated.View>
  );
};

export default ScrollProvider;


const ScrollHideShowWidget = () => {
  const [display, setDisplay] = useState(true);
  const animation = useRef(new Animated.Value(0)).current;
  const height = 100; // Set your widget height here
  const _offset = useRef(-height).current;

  const handleScroll = (event: any) => {
    const direction = event.nativeEvent.contentOffset.y > _offset ? 'forward' : 'reverse';
    if (direction === 'reverse') {
      if (display) {
        Animated.timing(animation, {
          toValue: -height,
          duration: 300,
          useNativeDriver: true,
        }).start();
        setDisplay(false);
        console.log('Hiding widget');
      }
    } else if (direction === 'forward') {
      if (!display) {
        Animated.timing(animation, {
          toValue: 0,
          duration: 300,
          useNativeDriver: true,
        }).start();
        setDisplay(true);
        console.log('Showing widget');
      }
    }
  };

  return (
    <View style={styles.container}>
      <Animated.View style={[styles.widget, { transform: [{ translateY: animation }] }]}>
        {/* Your widget content goes here */}
      </Animated.View>
      <ScrollView onScroll={handleScroll} scrollEventThrottle={16}>
        {/* Your scrollable content goes here */}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  widget: {
    height: 100, // Set your widget height here
    backgroundColor: 'blue', // Example background color
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
  },
});

export default ScrollHideShowWidget;


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