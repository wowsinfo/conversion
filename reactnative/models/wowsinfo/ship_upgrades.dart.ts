
class ShipUpgrades {
  private logger = new Logger('ShipUpgrades');

  private ship: Ship;
  private modernizations: Modernization[];
  private modernizationsBySlot: Modernization[][];

  constructor(ship: Ship) {
    this.ship = ship;
    this.modernizations = [];
    this.modernizationsBySlot = [];
  }

  public getModernizations(): Modernization[] {
    return this.modernizations;
  }

  public getModernizationsBySlot(): Modernization[][] {
    return this.modernizationsBySlot;
  }

  public unpackUpgrades(): void {
    const upgrades = GameRepository.instance.modernizationList;
    const shipUpgrades: Modernization[] = [];
    let maxSlot = 0;

    for (const upgrade of upgrades) {
      if (upgrade.isFor(this.ship)) {
        shipUpgrades.push(upgrade);
        const slot = upgrade.slot;
        if (slot > maxSlot) maxSlot = slot;
      }
    }

    this.logger.debug(`Unpacked ${shipUpgrades.length} upgrades for ${this.ship.name}`);
    if (shipUpgrades.length >= 30) {
      throw new Error(`Too many upgrades for ${this.ship.name}`);
    }

    this.modernizations = shipUpgrades;
    this.modernizationsBySlot = this.unpackUpgradesBySlot(maxSlot + 1);
  }

  private unpackUpgradesBySlot(max: number): Modernization[][] {
    const upgradesBySlot: Modernization[][] = Array.from({ length: max }, () => []);
    for (const modernization of this.modernizations) {
      upgradesBySlot[modernization.slot].push(modernization);
    }
    return upgradesBySlot;
  }
}


const logger = new Logger();

const unpackUpgradesBySlot = (max: number): Array<Array<Modernization>> => {
  const modernizationsBySlot: Array<Array<Modernization>> = [];
  for (let slot = 0; slot < max; slot++) {
    logger.fine(`Unpacking slot ${slot}`);
    const upgradesInSlot = _modernizations.filter(upgrade => upgrade.slot === slot);
    modernizationsBySlot.push(upgradesInSlot);
  }
  return modernizationsBySlot;
};

export default unpackUpgradesBySlot;


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