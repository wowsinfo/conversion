
interface IconInkWellProps {
  icon: keyof typeof MaterialIcons.glyphMap;
  onTap?: () => void;
  size?: number;
}

const IconInkWell: React.FC<IconInkWellProps> = ({ icon, onTap, size }) => {
  return (
    <TouchableOpacity onPress={onTap} style={styles.container}>
      <MaterialIcons name={icon} size={size} color="black" />
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default IconInkWell;


interface IconButtonProps {
  icon: keyof typeof MaterialIcons.glyphMap;
  size?: number;
  onTap?: () => void;
}

const IconButton: React.FC<IconButtonProps> = ({ icon, size = 48, onTap }) => {
  return (
    <View style={[styles.container, { width: size, height: size }]}>
      <TouchableOpacity
        style={styles.button}
        onPress={onTap}
      >
        <MaterialIcons name={icon} size={size * 0.5} color="black" />
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    borderRadius: 24, // half of the default size
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default IconButton;


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