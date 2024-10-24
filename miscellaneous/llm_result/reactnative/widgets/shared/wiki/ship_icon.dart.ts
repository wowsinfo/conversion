
interface ShipIconProps {
  icon: string;
  height?: number;
  width?: number;
  hero?: boolean;
  isNew?: boolean;
}

const ShipIcon: React.FC<ShipIconProps> = ({ icon, height, width, hero, isNew }) => {
  return (
    <View style={[styles.container, { height, width }]}>
      <AssetImageLoader source={icon} style={styles.image} />
      {isNew && <NewItemIndicator />}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    position: 'relative',
  },
  image: {
    width: '100%',
    height: '100%',
    resizeMode: 'contain',
  },
});

export default ShipIcon;


interface IconProps {
  icon: string;
  height?: number;
  width?: number;
  hero?: boolean;
  isNew?: boolean;
}

const Icon: React.FC<IconProps> = ({ icon, height = 80, width, hero, isNew }) => {
  const fullIcon = `data/live/app/assets/ships/${icon}.png`;
  
  const boxStyle = {
    height: height,
    width: width,
  };

  const box = (
    <View style={[styles.box, boxStyle]}>
      <Image
        source={{ uri: fullIcon }}
        style={styles.image}
        defaultSource={require('data/live/app/assets/ships/_default.png')}
      />
      {isNew && <NewItemIndicator />}
    </View>
  );

  if (hero) {
    return (
      <Hero tag={icon}>
        {box}
      </Hero>
    );
  } else {
    return box;
  }
};

const styles = StyleSheet.create({
  box: {
    position: 'relative',
  },
  image: {
    width: '100%',
    height: '100%',
    resizeMode: 'contain',
  },
});

export default Icon;


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