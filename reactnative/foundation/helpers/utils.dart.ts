
class Utils {
  context: any;
  private logger: Logger;

  constructor(context: any) {
    this.context = context;
    this.logger = new Logger('Utils');
  }

  static of(context: any) {
    return new Utils(context);
  }

  /// A simple wrapper of setTimeout
  static delay(duration: number): Promise<void> {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve();
      }, duration);
    });
  }
}


const { width } = Dimensions.get('window');

function getItemCount(maxItem: number, minItem: number, itemWidth: number): number {
    const count = Math.min(maxItem, Math.max(width / itemWidth, minItem));
    console.log(`Item count: ${count} (${width})`);
    return Math.floor(count);
}


const { width } = Dimensions.get('window');

function getItemWidth(itemWidth: number, maxCount: number = 0, margin: number = 0): number {
    const count = Math.floor(width / itemWidth);

    if (maxCount !== 0 && count > maxCount) {
        return (width / maxCount) - margin;
    }
    return (width / count) - margin;
}

export default getItemWidth;


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