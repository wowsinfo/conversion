
interface DebutEffectProps {
  child: React.ReactNode;
  intervalStart?: number;
  duration?: number;
  curve?: any;
  keepAlive?: boolean;
}

const DebutEffect: React.FC<DebutEffectProps> = ({
  child,
  intervalStart = 0,
  duration = 500,
  curve = 'easeInOut',
  keepAlive = true,
}) => {
  const animatedValue = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    const timer = setTimeout(() => {
      Animated.timing(animatedValue, {
        toValue: 1,
        duration: duration,
        useNativeDriver: true,
        easing: curve === 'easeInOut' ? Animated.Easing.inOut(Animated.Easing.ease) : undefined,
      }).start();
    }, intervalStart);

    return () => {
      clearTimeout(timer);
    };
  }, [animatedValue, intervalStart, duration, curve]);

  const animatedStyle = {
    opacity: animatedValue,
    transform: [
      {
        translateY: animatedValue.interpolate({
          inputRange: [0, 1],
          outputRange: [20, 0],
        }),
      },
    ],
  };

  return (
    <Animated.View style={[animatedStyle, keepAlive ? styles.keepAlive : {}]}>
      {child}
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  keepAlive: {
    // Add any specific styles for keepAlive if needed
  },
});

export default DebutEffect;


interface DebutEffectProps {
  children: React.ReactNode;
  duration: number;
  intervalStart: number;
  curve: (input: number) => number;
  keepAlive?: boolean;
}

const DebutEffect: React.FC<DebutEffectProps> = ({
  children,
  duration,
  intervalStart,
  curve,
  keepAlive = false,
}) => {
  const translateY = useRef(new Animated.Value(30)).current;
  const opacity = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    const startAnimation = () => {
      Animated.parallel([
        Animated.timing(translateY, {
          toValue: 0,
          duration: duration,
          useNativeDriver: true,
          delay: 100,
          easing: curve,
        }),
        Animated.timing(opacity, {
          toValue: 1,
          duration: duration,
          useNativeDriver: true,
          delay: 100,
          easing: curve,
        }),
      ]).start();
    };

    const timeout = setTimeout(startAnimation, intervalStart * duration);

    return () => clearTimeout(timeout);
  }, [duration, intervalStart, curve, translateY, opacity]);

  return (
    <Animated.View
      style={[
        styles.container,
        {
          transform: [{ translateY }],
          opacity,
        },
      ]}
    >
      {children}
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  container: {
    // Add any additional styles here
  },
});

export default DebutEffect;


interface AnimatedComponentProps {
  child: React.ReactNode;
}

const AnimatedComponent: React.FC<AnimatedComponentProps> = ({ child }) => {
  const translateY = useRef(new Animated.Value(0)).current;
  const opacity = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    Animated.parallel([
      Animated.timing(translateY, {
        toValue: 100, // Change this value as needed
        duration: 500,
        useNativeDriver: true,
      }),
      Animated.timing(opacity, {
        toValue: 1,
        duration: 500,
        useNativeDriver: true,
      }),
    ]).start();
  }, [translateY, opacity]);

  return (
    <Animated.View
      style={[
        styles.container,
        {
          transform: [{ translateY }],
          opacity,
        },
      ]}
    >
      {child}
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  container: {
    // Add any additional styles here
  },
});

export default AnimatedComponent;


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
        {/* Add your content here */}
      </View>
    );
  }
}

export default MyComponent;