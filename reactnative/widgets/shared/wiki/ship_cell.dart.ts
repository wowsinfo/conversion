
interface ShipCellProps {
  icon: string;
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
  height?: number;
  width?: number;
  hero?: string;
  onTap?: () => void;
  isNew?: boolean;
}

const ShipCell: React.FC<ShipCellProps> = ({
  icon,
  name,
  isPremium,
  isSpecial,
  height,
  width,
  hero,
  onTap,
  isNew,
}) => {
  return (
    <TouchableOpacity onPress={onTap} style={[styles.container, { height, width }]}>
      <Image source={{ uri: icon }} style={styles.icon} />
      <Text style={styles.name}>{name}</Text>
      {isPremium && <Text style={styles.premium}>Premium</Text>}
      {isSpecial && <Text style={styles.special}>Special</Text>}
      {isNew && <Text style={styles.new}>New</Text>}
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    justifyContent: 'center',
    padding: 10,
  },
  icon: {
    width: 50,
    height: 50,
  },
  name: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  premium: {
    color: 'gold',
  },
  special: {
    color: 'red',
  },
  new: {
    color: 'green',
  },
});

export default ShipCell;


interface ShipIconProps {
  icon: string;
  height?: number;
  width?: number;
  hero?: boolean;
  isNew?: boolean;
}

const ShipIcon: React.FC<ShipIconProps> = ({ icon, height, width, hero, isNew }) => {
  return (
    <Image
      source={{ uri: icon }}
      style={{ height: height, width: width }}
      // Add any additional props for hero and isNew if needed
    />
  );
};

interface ShipNameProps {
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
}

const ShipName: React.FC<ShipNameProps> = ({ name, isPremium, isSpecial }) => {
  return (
    <Text style={{ fontWeight: isPremium ? 'bold' : 'normal', color: isSpecial ? 'red' : 'black' }}>
      {name}
    </Text>
  );
};

interface ShipCellProps {
  icon: string;
  height?: number;
  width?: number;
  hero?: boolean;
  isNew?: boolean;
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
  onTap?: () => void;
}

const ShipCell: React.FC<ShipCellProps> = ({ icon, height, width, hero, isNew, name, isPremium, isSpecial, onTap }) => {
  const cell = (
    <View style={{ alignItems: 'center' }}>
      <ShipIcon icon={icon} height={height} width={width} hero={hero} isNew={isNew} />
      <ShipName name={name} isPremium={isPremium} isSpecial={isSpecial} />
    </View>
  );

  if (!onTap) return cell;
  return (
    <TouchableOpacity onPress={onTap}>
      {cell}
    </TouchableOpacity>
  );
};

export default ShipCell;


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