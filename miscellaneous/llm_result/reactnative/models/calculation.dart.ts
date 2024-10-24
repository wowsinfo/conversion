
interface CalculationProps {
  value?: number;
  base?: number;
}

const Calculation: React.FC<CalculationProps> = ({ value, base }) => {
  const average = (value?: number, base?: number): number => {
    if (value === undefined || value === null) return NaN;
    if (base === undefined || base === null) return NaN;
    return value / base;
  };

  const avg = average(value, base);

  return (
    <View>
      <Text>Average: {isNaN(avg) ? 'Invalid input' : avg.toString()}</Text>
    </View>
  );
};

export default Calculation;

function rate(value: number | null, base: number | null): number | null {
  if (value === null) return null;
  if (base === null) return null;
  return (value * 10000 / base) / 100.0;
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