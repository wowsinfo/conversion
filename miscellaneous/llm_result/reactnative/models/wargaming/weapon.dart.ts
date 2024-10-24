
type MainBattery = Weapon;
type SecondaryBattery = Weapon;
type RamAttack = Weapon;
type Torpedo = Weapon;
type AttackAircraft = Weapon;

interface WeaponProps {
  maxFragsBattle?: number;
  frags?: number;
  hits?: number;
  maxFragsShipId?: number;
  shots?: number;
}

const Weapon: React.FC<WeaponProps> = ({
  maxFragsBattle,
  frags,
  hits,
  maxFragsShipId,
  shots,
}) => {
  return (
    <div>
      <h3>Weapon Stats</h3>
      <p>Max Frags Battle: {maxFragsBattle}</p>
      <p>Frags: {frags}</p>
      <p>Hits: {hits}</p>
      <p>Max Frags Ship ID: {maxFragsShipId}</p>
      <p>Shots: {shots}</p>
    </div>
  );
};

export default Weapon;

interface Weapon {
  maxFragsBattle?: number;
  frags?: number;
  hits?: number;
  maxFragsShipId?: number;
  shots?: number;
}

const fromJson = (json: any): Weapon => ({
  maxFragsBattle: json.max_frags_battle,
  frags: json.frags,
  hits: json.hits,
  maxFragsShipId: json.max_frags_ship_id,
  shots: json.shots,
});

const hasHitRatio = (weapon: Weapon): boolean => 
  weapon.shots !== undefined && weapon.hits !== undefined && weapon.hits > 0;

const hitRatio = (weapon: Weapon): number => 
  hasHitRatio(weapon) ? (weapon.hits! * 10000) / weapon.shots! / 100.0 : NaN;

const maxFrag = (weapon: Weapon): string => 
  weapon.maxFragsBattle?.toString() || '';

const totalFrag = (weapon: Weapon): string => 
  weapon.frags?.toString() || '';

const toString = (weapon: Weapon): string => 
  `Weapon(maxFragsBattle: ${weapon.maxFragsBattle}, frag: ${weapon.frags}, hits: ${weapon.hits}, maxFragsShipId: ${weapon.maxFragsShipId}, shot: ${weapon.shots})`;


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