
interface PopupColumnProps {
  children: React.ReactNode[];
  duration?: number;
  curve?: (value: number) => number;
  keepAlive?: boolean;
  textDirection?: 'ltr' | 'rtl';
  textBaseline?: TextStyle['textAlignVertical'];
  mainAxisAlignment?: 'flex-start' | 'center' | 'flex-end' | 'space-between' | 'space-around' | 'space-evenly';
  mainAxisSize?: 'max' | 'min';
  crossAxisAlignment?: 'flex-start' | 'center' | 'flex-end' | 'stretch';
  verticalDirection?: 'up' | 'down';
}

const PopupColumn: React.FC<PopupColumnProps> = ({
  children,
  duration = 500,
  curve = (t) => t,
  keepAlive = true,
  textDirection,
  textBaseline,
  mainAxisAlignment = 'flex-start',
  mainAxisSize = 'max',
  crossAxisAlignment = 'center',
  verticalDirection = 'down',
}) => {
  const [animation] = useState(new Animated.Value(0));
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    if (keepAlive) {
      setVisible(true);
      Animated.timing(animation, {
        toValue: 1,
        duration,
        useNativeDriver: true,
      }).start();
    }
  }, [keepAlive]);

  const animatedStyle = {
    opacity: animation,
    transform: [
      {
        translateY: animation.interpolate({
          inputRange: [0, 1],
          outputRange: verticalDirection === 'down' ? [-20, 0] : [20, 0],
        }),
      },
    ],
  };

  return (
    <View style={[styles.container, { flexDirection: textDirection === 'rtl' ? 'row-reverse' : 'row' }]}>
      {visible && (
        <Animated.View style={[animatedStyle, styles.animatedView]}>
          {React.Children.map(children, (child) => (
            <View style={styles.childContainer}>{child}</View>
          ))}
        </Animated.View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  animatedView: {
    alignItems: 'center',
  },
  childContainer: {
    marginVertical: 5,
  },
});

export default PopupColumn;


interface DebutEffectProps {
  intervalStart: number;
  duration: number;
  curve: any;
  keepAlive: boolean;
  children: React.ReactNode;
}

const DebutEffect: React.FC<DebutEffectProps> = ({ intervalStart, duration, curve, keepAlive, children }) => {
  const animatedValue = new Animated.Value(0);

  useEffect(() => {
    const delay = intervalStart * 1000; // Convert to milliseconds
    const animation = Animated.timing(animatedValue, {
      toValue: 1,
      duration: duration,
      useNativeDriver: true,
      delay: delay,
      easing: curve,
    });

    animation.start();

    return () => {
      if (keepAlive) {
        animatedValue.setValue(1);
      } else {
        animatedValue.setValue(0);
      }
    };
  }, [intervalStart, duration, curve, keepAlive]);

  const opacity = animatedValue.interpolate({
    inputRange: [0, 1],
    outputRange: [0, 1],
  });

  return (
    <Animated.View style={{ opacity }}>
      {children}
    </Animated.View>
  );
};

interface PopupColumnProps {
  children: React.ReactNode[];
  duration: number;
  curve: any;
  keepAlive: boolean;
  mainAxisAlignment?: 'flex-start' | 'center' | 'flex-end' | 'space-between' | 'space-around' | 'space-evenly';
  mainAxisSize?: 'max' | 'min';
  crossAxisAlignment?: 'flex-start' | 'center' | 'flex-end' | 'stretch';
  textDirection?: 'ltr' | 'rtl';
  verticalDirection?: 'up' | 'down';
  textBaseline?: any;
}

const PopupColumn: React.FC<PopupColumnProps> = ({
  children,
  duration,
  curve,
  keepAlive,
  mainAxisAlignment = 'flex-start',
  mainAxisSize = 'max',
  crossAxisAlignment = 'flex-start',
  textDirection,
  verticalDirection = 'down',
  textBaseline,
}) => {
  const count = children.length;

  const columnStyle: ViewStyle = {
    flexDirection: 'column',
    justifyContent: mainAxisAlignment,
    alignItems: crossAxisAlignment,
    flex: mainAxisSize === 'max' ? 1 : undefined,
  };

  return (
    <View style={columnStyle}>
      {React.Children.map(children, (child, index) => (
        <DebutEffect
          key={index}
          intervalStart={index / count}
          keepAlive={keepAlive}
          duration={duration}
          curve={curve}
        >
          {child}
        </DebutEffect>
      ))}
    </View>
  );
};

export default PopupColumn;


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