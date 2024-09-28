abstract class Counter {
  add(value: number): void;
}

class AverageCounter implements Counter {
  private total: number = 0;
  private count: number = 0;
  private average: number = 0;

  getAverage(): number {
    return this.average;
  }

  add(value: number): void {
    this.total += value;
    this.count++;
    this.average = this.total / this.count;
  }
}

  private _total: number = 0;

  get total(): number {
    return this._total;
  }

  add(value: number): void {
    this._total += value;
  }
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