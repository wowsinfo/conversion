
interface PopupBoxProps {
  children: React.ReactNode;
  duration?: number;
  curve?: (value: number) => number;
  keepAlive?: boolean;
}

const PopupBox: React.FC<PopupBoxProps> = ({
  children,
  duration = 300,
  curve = Easing.ease,
  keepAlive = false,
}) => {
  const [visible, setVisible] = useState(false);
  const [animation] = useState(new Animated.Value(0));

  useEffect(() => {
    if (visible) {
      Animated.timing(animation, {
        toValue: 1,
        duration,
        easing: curve,
        useNativeDriver: true,
      }).start();
    } else {
      Animated.timing(animation, {
        toValue: 0,
        duration,
        easing: curve,
        useNativeDriver: true,
      }).start();
    }
  }, [visible, animation, duration, curve]);

  const handleShow = () => {
    setVisible(true);
  };

  const handleHide = () => {
    if (keepAlive) {
      setVisible(false);
    } else {
      setVisible(false);
    }
  };

  const animatedStyle = {
    opacity: animation,
    transform: [
      {
        translateY: animation.interpolate({
          inputRange: [0, 1],
          outputRange: [50, 0],
        }),
      },
    ],
  };

  return (
    <View style={styles.container}>
      <Animated.View style={[styles.popup, animatedStyle]}>
        {children}
      </Animated.View>
      <View style={styles.buttonContainer}>
        <Button title="Show" onPress={handleShow} />
        <Button title="Hide" onPress={handleHide} />
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
  popup: {
    width: 200,
    height: 100,
    backgroundColor: 'white',
    borderRadius: 10,
    justifyContent: 'center',
    alignItems: 'center',
    position: 'absolute',
  },
  buttonContainer: {
    flexDirection: 'row',
    marginTop: 20,
  },
});

export default PopupBox;


interface PopupBoxProps {
  child: React.ReactNode;
  duration: number;
  curve: Easing;
  keepAlive: boolean;
}

const PopupBox: React.FC<PopupBoxProps> = ({ child, duration, curve, keepAlive }) => {
  const animation = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    Animated.timing(animation, {
      toValue: 1,
      duration: duration,
      easing: curve,
      useNativeDriver: true,
    }).start();
  }, [animation, duration, curve]);

  const animatedStyle = {
    opacity: animation,
  };

  return (
    <Animated.View style={animatedStyle}>
      {child}
    </Animated.View>
  );
};

export default PopupBox;


interface ScaleTransitionProps {
  child: React.ReactNode;
}

const ScaleTransition: React.FC<ScaleTransitionProps> = ({ child }) => {
  const scale = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    Animated.spring(scale, {
      toValue: 1,
      useNativeDriver: true,
    }).start();
  }, [scale]);

  return (
    <Animated.View style={{ transform: [{ scale }] }}>
      {child}
    </Animated.View>
  );
};

export default ScaleTransition;


const YourComponent: React.FC = () => {
  const controller = new SomeController();

  useEffect(() => {
    return () => {
      controller.dispose();
    };
  }, []);

  return (
    <View>
      {/* Your component content goes here */}
    </View>
  );
};

export default YourComponent;


interface Props {
  keepAlive: boolean;
}

class MyComponent extends Component<Props> {
  shouldComponentUpdate(nextProps: Props) {
    return nextProps.keepAlive !== this.props.keepAlive;
  }

  render() {
    return (
      <View>
        {/* Your content here */}
      </View>
    );
  }
}

export default MyComponent;