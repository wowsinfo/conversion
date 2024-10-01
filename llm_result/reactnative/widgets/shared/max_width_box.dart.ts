
interface MaxWidthBoxProps {
  maxWidth?: number;
  children: React.ReactNode;
}

const MaxWidthBox: React.FC<MaxWidthBoxProps> = ({ maxWidth, children }) => {
  return (
    <View style={[styles.container, maxWidth ? { maxWidth } : {}]}>
      {children}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    width: '100%',
    alignSelf: 'center',
  } as ViewStyle,
});

export default MaxWidthBox;


interface ConstrainedBoxProps {
  maxWidth?: number;
  children: React.ReactNode;
}

const ConstrainedBox: React.FC<ConstrainedBoxProps> = ({ maxWidth, children }) => {
  return (
    <View style={[styles.container, { maxWidth: maxWidth || ScreenSize.maxDialogWidth }]}>
      {children}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    width: '100%',
  },
});

// Assuming ScreenSize is defined somewhere in your code
const ScreenSize = {
  maxDialogWidth: 400, // Example value, adjust as needed
};

export default ConstrainedBox;


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