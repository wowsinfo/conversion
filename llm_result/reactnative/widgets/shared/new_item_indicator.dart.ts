
interface NewItemIndicatorProps {
  size?: number;
  color?: string;
}

const NewItemIndicator: React.FC<NewItemIndicatorProps> = ({ size = 20, color = 'red' }) => {
  return (
    <View style={[styles.indicator, { width: size, height: size, backgroundColor: color }]} />
  );
};

const styles = StyleSheet.create({
  indicator: {
    borderRadius: 10,
  },
});

export default NewItemIndicator;


interface CircleProps {
  size?: number;
  color?: string;
}

const Circle: React.FC<CircleProps> = ({ size = 10, color }) => {
  return (
    <View style={styles.container}>
      <View
        style={[
          styles.circle,
          {
            width: size,
            height: size,
            backgroundColor: color || '#6200ee', // Default to a primary color
            borderRadius: size / 2,
          },
        ]}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'center',
  },
  circle: {
    // Additional styles can be added here if needed
  },
});

export default Circle;


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