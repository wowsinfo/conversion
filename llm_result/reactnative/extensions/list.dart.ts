
interface ListExtensions {
  enumerate<T>(list: ListExtension<T>): Array<{ index: number; value: T }>;
}

const listExtensions: ListExtensions = {
  enumerate<T>(list: ListExtension<T>): Array<{ index: number; value: T }> {
    return list.map((value, index) => ({ index, value }));
  }
};

// Example usage
const myList = [10, 20, 30];
const enumeratedList = listExtensions.enumerate(myList);
console.log(enumeratedList); // Output: [{ index: 0, value: 10 }, { index: 1, value: 20 }, { index: 2, value: 30 }]


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