
class App {
  static appVersion = '1.7.0';
  static githubLink = 'https://github.com/WoWs-Info/WoWs-Info-Seven';
  static appstoreLink = 'https://itunes.apple.com/app/id1202750166';
  static playstoreLink = 'https://play.google.com/store/apps/details?id=com.yihengquan.wowsinfo';
  static latestRelease = `${App.githubLink}/releases/latest`;
  static newIssueLink = `${App.githubLink}/issues/new`;
  static emailToLink = `mailto:development.henryquan@gmail.com?subject=[WoWs Info ${App.appVersion}]`;

  private static instance: App;
  private _context: any;

  private constructor() {}

  static getInstance() {
    if (!App.instance) {
      App.instance = new App();
    }
    return App.instance;
  }

  inject(context: any) {
    this._context = context;
  }

  openLink(url: string) {
    Linking.openURL(url).catch(err => console.error('An error occurred', err));
  }
}

export default App;


export const PlatformUtils = {
  isWeb: Platform.OS === 'web',

  isIOS: Platform.OS === 'ios',

  isAndroid: Platform.OS === 'android',

  isMobile: !PlatformUtils.isWeb && (PlatformUtils.isIOS || PlatformUtils.isAndroid),

  isDesktop: !PlatformUtils.isWeb && !PlatformUtils.isMobile,

  isApple: !PlatformUtils.isWeb && (PlatformUtils.isIOS || Platform.OS === 'macos'),

  platformPageRoute: (
    component: React.FC,
    settings?: Route,
    maintainState: boolean = true,
    fullscreenDialog: boolean = false
  ) => {
    return (
      <Modal
        visible={true}
        animationType={fullscreenDialog ? 'slide' : 'fade'}
        onRequestClose={() => {
          // Handle close
        }}
      >
        <View style={{ flex: 1 }}>
          {React.createElement(component)}
        </View>
      </Modal>
    );
  }
};


const Stack = createStackNavigator();

const isMobile = true; // Replace with actual mobile detection logic

const CustomTransition = ({ component: Component }) => {
  const animation = React.useRef(new Animated.Value(0)).current;

  React.useEffect(() => {
    Animated.timing(animation, {
      toValue: 1,
      duration: 300,
      easing: Easing.linear,
      useNativeDriver: true,
    }).start();
  }, [animation]);

  const opacity = animation.interpolate({
    inputRange: [0, 1],
    outputRange: [0, 1],
  });

  return (
    <Animated.View style={{ flex: 1, opacity }}>
      <Component />
    </Animated.View>
  );
};

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={() => <View><Text>Home Screen</Text></View>}
          options={{
            headerShown: false,
            cardStyleInterpolator: ({ current }) => ({
              cardStyle: {
                opacity: current.progress,
              },
            }),
          }}
        />
        <Stack.Screen
          name="Details"
          component={() => <View><Text>Details Screen</Text></View>}
          options={{
            headerShown: false,
            cardStyleInterpolator: ({ current }) => ({
              cardStyle: {
                opacity: current.progress,
              },
            }),
          }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;


const launch = async (url: string) => {
  const canLaunch = await Linking.canOpenURL(url);
  if (canLaunch) {
    await Linking.openURL(url);
  } else {
    Alert.alert('Error', `Could not launch ${url}`);
  }
};


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