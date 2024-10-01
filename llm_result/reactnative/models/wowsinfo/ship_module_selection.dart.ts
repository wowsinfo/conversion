
class ShipModuleSelection {
  private _logger = console; // Using console for logging

  @observable
  private _selectedHull: number = 0;
  @observable
  private _selectedGun: number = 0;
  @observable
  private _selectedSecondary: number = 0;
  @observable
  private _selectedTorp: number = 0;
  @observable
  private _selectedPinger: number = 0;
  @observable
  private _selectedFireControl: number = 0;
  @observable
  private _selectedEngine: number = 0;
  @observable
  private _selectedAirSupport: number = 0;
  @observable
  private _selectedAirDefense: number = 0;
  @observable
  private _selectedDepthCharge: number = 0;
  @observable
  private _selectedSpecial: number = 0;

  @observable
  private _selectedFighter: number = 0;
  @observable
  private _selectedSkipBomber: number = 0;
  @observable
  private _selectedTorpedoBomber: number = 0;
  @observable
  private _selectedDiveBomber: number = 0;

  constructor() {
    makeAutoObservable(this);
  }

  get hullIndex(): number {
    return this._selectedHull;
  }
  get gunIndex(): number {
    return this._selectedGun;
  }
  get secondaryIndex(): number {
    return this._selectedSecondary;
  }
  get torpIndex(): number {
    return this._selectedTorp;
  }
  get pingerIndex(): number {
    return this._selectedPinger;
  }
  get fireControlIndex(): number {
    return this._selectedFireControl;
  }
  get engineIndex(): number {
    return this._selectedEngine;
  }
  get airSupportIndex(): number {
    return this._selectedAirSupport;
  }
  get airDefenseIndex(): number {
    return this._selectedAirDefense;
  }
  get depthChargeIndex(): number {
    return this._selectedDepthCharge;
  }
  get specialIndex(): number {
    return this._selectedSpecial;
  }

  get fighterIndex(): number {
    return this._selectedFighter;
  }
  get skipBomberIndex(): number {
    return this._selectedSkipBomber;
  }
  get torpedoBomberIndex(): number {
    return this._selectedTorpedoBomber;
  }
  get diveBomberIndex(): number {
    return this._selectedDiveBomber;
  }

  // Copy from another selection
  static fromSelection(selection: ShipModuleSelection): ShipModuleSelection {
    const newSelection = new ShipModuleSelection();
    newSelection._selectedHull = selection.hullIndex;
    newSelection._selectedGun = selection.gunIndex;
    newSelection._selectedSecondary = selection.secondaryIndex;
    newSelection._selectedTorp = selection.torpIndex;
    newSelection._selectedPinger = selection.pingerIndex;
    newSelection._selectedFireControl = selection.fireControlIndex;
    newSelection._selectedEngine = selection.engineIndex;
    newSelection._selectedAirSupport = selection.airSupportIndex;
    newSelection._selectedAirDefense = selection.airDefenseIndex;
    newSelection._selectedDepthCharge = selection.depthChargeIndex;
    newSelection._selectedSpecial = selection.specialIndex;

    newSelection._selectedFighter = selection.fighterIndex;
    newSelection._selectedSkipBomber = selection.skipBomberIndex;
    newSelection._selectedTorpedoBomber = selection.torpedoBomberIndex;
    newSelection._selectedDiveBomber = selection.diveBomberIndex;

    return newSelection;
  }
}

export default ShipModuleSelection;

enum ShipModuleType {
  hull,
  gun,
  secondary,
  torpedo,
  pinger,
  fireControl,
  engine,
  airSupport,
  airDefense,
  depthCharge,
  fighter,
  skipBomber,
  torpedoBomber,
  diveBomber,
  unknown,
}

class Ship {
  private _selectedHull: number;
  private _selectedGun: number;
  private _selectedSecondary: number;
  private _selectedTorp: number;
  private _selectedPinger: number;
  private _selectedFireControl: number;
  private _selectedEngine: number;
  private _selectedAirSupport: number;
  private _selectedAirDefense: number;
  private _selectedDepthCharge: number;
  private _selectedFighter: number;
  private _selectedSkipBomber: number;
  private _selectedTorpedoBomber: number;
  private _selectedDiveBomber: number;
  private _logger: { severe: (message: string) => void };

  constructor() {
    // Initialize selected modules with default values
    this._selectedHull = -1;
    this._selectedGun = -1;
    this._selectedSecondary = -1;
    this._selectedTorp = -1;
    this._selectedPinger = -1;
    this._selectedFireControl = -1;
    this._selectedEngine = -1;
    this._selectedAirSupport = -1;
    this._selectedAirDefense = -1;
    this._selectedDepthCharge = -1;
    this._selectedFighter = -1;
    this._selectedSkipBomber = -1;
    this._selectedTorpedoBomber = -1;
    this._selectedDiveBomber = -1;
    this._logger = { severe: (message: string) => console.error(message) };
  }

  isSelected(type: ShipModuleType, index: number): boolean {
    switch (type) {
      case ShipModuleType.hull:
        return this._selectedHull === index;
      case ShipModuleType.gun:
        return this._selectedGun === index;
      case ShipModuleType.secondary:
        return this._selectedSecondary === index;
      case ShipModuleType.torpedo:
        return this._selectedTorp === index;
      case ShipModuleType.pinger:
        return this._selectedPinger === index;
      case ShipModuleType.fireControl:
        return this._selectedFireControl === index;
      case ShipModuleType.engine:
        return this._selectedEngine === index;
      case ShipModuleType.airSupport:
        return this._selectedAirSupport === index;
      case ShipModuleType.airDefense:
        return this._selectedAirDefense === index;
      case ShipModuleType.depthCharge:
        return this._selectedDepthCharge === index;
      case ShipModuleType.fighter:
        return this._selectedFighter === index;
      case ShipModuleType.skipBomber:
        return this._selectedSkipBomber === index;
      case ShipModuleType.torpedoBomber:
        return this._selectedTorpedoBomber === index;
      case ShipModuleType.diveBomber:
        return this._selectedDiveBomber === index;
      case ShipModuleType.unknown:
        this._logger.severe(`Unknown module type: ${type}`);
    }
    return false;
  }
}


enum ShipModuleType {
  hull,
  gun,
  secondary,
  torpedo,
  pinger,
  fireControl,
  engine,
  airSupport,
  airDefense,
  depthCharge,
  fighter,
  skipBomber,
  torpedoBomber,
  diveBomber,
  unknown,
}

class Ship {
  private _selectedHull: number | null = null;
  private _selectedGun: number | null = null;
  private _selectedSecondary: number | null = null;
  private _selectedTorp: number | null = null;
  private _selectedPinger: number | null = null;
  private _selectedFireControl: number | null = null;
  private _selectedEngine: number | null = null;
  private _selectedAirSupport: number | null = null;
  private _selectedAirDefense: number | null = null;
  private _selectedDepthCharge: number | null = null;
  private _selectedFighter: number | null = null;
  private _selectedSkipBomber: number | null = null;
  private _selectedTorpedoBomber: number | null = null;
  private _selectedDiveBomber: number | null = null;
  private _logger: Logger;

  constructor(logger: Logger) {
    this._logger = logger;
  }

  updateSelection(type: ShipModuleType, index: number) {
    switch (type) {
      case ShipModuleType.hull:
        if (this._selectedHull === index) return;
        this._selectedHull = index;
        this._logger.info(`updateHull: ${index}`);
        break;
      case ShipModuleType.gun:
        if (this._selectedGun === index) return;
        this._selectedGun = index;
        this._logger.info(`updateGun: ${index}`);
        break;
      case ShipModuleType.secondary:
        if (this._selectedSecondary === index) return;
        this._selectedSecondary = index;
        this._logger.info(`updateSecondary: ${index}`);
        break;
      case ShipModuleType.torpedo:
        if (this._selectedTorp === index) return;
        this._selectedTorp = index;
        this._logger.info(`updateTorp: ${index}`);
        break;
      case ShipModuleType.pinger:
        if (this._selectedPinger === index) return;
        this._selectedPinger = index;
        this._logger.info(`updatePinger: ${index}`);
        break;
      case ShipModuleType.fireControl:
        if (this._selectedFireControl === index) return;
        this._selectedFireControl = index;
        this._logger.info(`updateFireControl: ${index}`);
        break;
      case ShipModuleType.engine:
        if (this._selectedEngine === index) return;
        this._selectedEngine = index;
        this._logger.info(`updateEngine: ${index}`);
        break;
      case ShipModuleType.airSupport:
        if (this._selectedAirSupport === index) return;
        this._selectedAirSupport = index;
        this._logger.info(`updateAirSupport: ${index}`);
        break;
      case ShipModuleType.airDefense:
        if (this._selectedAirDefense === index) return;
        this._selectedAirDefense = index;
        this._logger.info(`updateAirDefense: ${index}`);
        break;
      case ShipModuleType.depthCharge:
        if (this._selectedDepthCharge === index) return;
        this._selectedDepthCharge = index;
        this._logger.info(`updateDepthCharge: ${index}`);
        break;
      case ShipModuleType.fighter:
        if (this._selectedFighter === index) return;
        this._selectedFighter = index;
        this._logger.info(`updateFighter: ${index}`);
        break;
      case ShipModuleType.skipBomber:
        if (this._selectedSkipBomber === index) return;
        this._selectedSkipBomber = index;
        this._logger.info(`updateSkipBomber: ${index}`);
        break;
      case ShipModuleType.torpedoBomber:
        if (this._selectedTorpedoBomber === index) return;
        this._selectedTorpedoBomber = index;
        this._logger.info(`updateTorpedoBomber: ${index}`);
        break;
      case ShipModuleType.diveBomber:
        if (this._selectedDiveBomber === index) return;
        this._selectedDiveBomber = index;
        this._logger.info(`updateDiveBomber: ${index}`);
        break;
      case ShipModuleType.unknown:
        this._logger.error(`Unknown module type: ${type}`);
        break;
    }
  }
}


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