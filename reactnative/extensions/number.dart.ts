
declare global {
  interface Number {
    toFixedString(fractionDigits: number): string;
  }
}

Number.prototype.toFixedString = function (fractionDigits: number): string {
  if (isNaN(this as any) || !isFinite(this as any)) return '-';
  return this.toFixed(fractionDigits);
};

const App: React.FC = () => {
  const numberValue: number = NaN; // Example value, can be changed to test

  return (
    <Text>{numberValue.toFixedString(2)}</Text>
  );
};

export default App;


function toDecimalString(value: number): string {
  const formatter = new NumberFormat('en-US', {
    minimumFractionDigits: 0,
    maximumFractionDigits: 2,
  });
  return formatter.format(value);
}

  const toDecimalString = (num: number): string => {
    return num.toFixed(2); // Adjust the precision as needed
  };

  return `${toDecimalString(value)}%`;
}

  return `${Math.round(value * 100)}%`;
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