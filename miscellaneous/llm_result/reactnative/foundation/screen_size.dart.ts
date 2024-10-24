
class ScreenSize {
  private static instance: ScreenSize;
  private constructor(private context: any) {}

  static of(context: any) {
    if (!ScreenSize.instance) {
      ScreenSize.instance = new ScreenSize(context);
    }
    return ScreenSize.instance;
  }

  static maxDialogWidth = 500;

  get screenSize() {
    return Dimensions.get('window');
  }

  isPhone() {
    return this.screenSize.width < 600;
  }
}

export default ScreenSize;


const isTablet = (): boolean => {
    const screenSize = Dimensions.get('window');
    return screenSize.width >= 600;
};

export default isTablet;


const { width } = Dimensions.get('window');

const isSmallTablet = (): boolean => {
  return width >= 600 && width < 840;
};

export default isSmallTablet;


const isLargeTablet = (): boolean => {
    const screenSize = Dimensions.get('window');
    return screenSize.width >= 840;
};

export default isLargeTablet;


const { width, height } = Dimensions.get('window');

const isPhone8 = (): boolean => {
  return width <= 375 && height <= 667;
};

const App: React.FC = () => {
  return (
    <div>
      {isPhone8() ? <p>This is an iPhone 8 size device.</p> : <p>This is not an iPhone 8 size device.</p>}
    </div>
  );
};

export default App;


const { width } = Dimensions.get('window');

const isPhoneSE = (): boolean => {
  return width <= 320;
};

export default isPhoneSE;


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