
interface ShipNameProps {
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
}

const ShipName: React.FC<ShipNameProps> = ({ name, isPremium, isSpecial }) => {
  return (
    <Text style={[styles.shipName, isPremium && styles.premium, isSpecial && styles.special]}>
      {name}
    </Text>
  );
};

const styles = StyleSheet.create({
  shipName: {
    fontSize: 16,
    color: 'black',
  },
  premium: {
    color: 'gold',
  },
  special: {
    fontWeight: 'bold',
  },
});

export default ShipName;


interface ShipProps {
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
}

const WoWsColours = {
  premiumShip: '#FFD700', // Example color for premium ships
  specialShip: '#FF4500', // Example color for special ships
};

const Ship: React.FC<ShipProps> = ({ name, isPremium, isSpecial }) => {
  let style: TextStyle;

  if (isPremium) {
    style = styles.premium;
  } else if (isSpecial) {
    style = styles.special;
  } else {
    style = styles.default;
  }

  return (
    <Text
      numberOfLines={1}
      ellipsizeMode="tail"
      textAlign="center"
      style={style}
    >
      {name}
    </Text>
  );
};

const styles = StyleSheet.create({
  premium: {
    fontSize: 14,
    color: WoWsColours.premiumShip,
    fontWeight: '500',
  },
  special: {
    fontSize: 14,
    color: WoWsColours.specialShip,
    fontWeight: '500',
  },
  default: {
    fontSize: 14,
    fontWeight: '300',
  },
});

export default Ship;


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