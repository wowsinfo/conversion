extension DateTimeExtension on DateTime {
  /// returns a human readable string, 2022-01-02
  String toHumanString() {
    // Add `0` in front of the date, otherwise, it won't work
    return [year, month, day]
        .map((e) => e.toString().padStart(2, '0'))
        .join('-');
  }
}

function diffInDays(other: Date): number {
  const duration = other.getTime() - this.getTime();
  return Math.abs(Math.floor(duration / (1000 * 60 * 60 * 24)));
}

  static fromTimeStamp(timeStamp: number): Date {
    return new Date(timeStamp * 1000);
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