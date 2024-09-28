
type ShipModuleMap<T> = Record<ShipModuleType, ShipModuleHolder<T>[]>;

enum ShipModuleType {
  hull = 'hull',
  gun = 'artillery',
  secondary = 'atba',
  torpedo = 'torpedoes',
  pinger = 'pinger',
  fireControl = 'fireControl',
  engine = 'engine',
  airSupport = 'airSupport',
  airDefense = 'airDefense',
  depthCharge = 'depthCharges',
  fighter = '',
  skipBomber = '',
  torpedoBomber = '',
  diveBomber = '',
  special = 'specials',
  unknown = 'unknown'
}

class ShipModuleHolder<T> {
  // Define properties and methods for ShipModuleHolder
}

function getKey(moduleType: ShipModuleType): string {
  switch (moduleType) {
    case ShipModuleType.hull:
      return 'hull';
    case ShipModuleType.gun:
      return 'artillery';
    case ShipModuleType.secondary:
      return 'atba';
    case ShipModuleType.torpedo:
      return 'torpedoes';
    case ShipModuleType.pinger:
      return 'pinger';
    case ShipModuleType.fireControl:
      return 'fireControl';
    case ShipModuleType.engine:
      return 'engine';
    case ShipModuleType.airSupport:
      return 'airSupport';
    case ShipModuleType.airDefense:
      return 'airDefense';
    case ShipModuleType.depthCharge:
      return 'depthCharges';
    case ShipModuleType.fighter:
    case ShipModuleType.skipBomber:
    case ShipModuleType.torpedoBomber:
    case ShipModuleType.diveBomber:
      return ''; // aircrafts are special so they don't need a key
    case ShipModuleType.special:
      return 'specials';
    case ShipModuleType.unknown:
      throw new Error(`Unknown module type: ${moduleType}`);
  }
}

export { ShipModuleType, ShipModuleMap, ShipModuleHolder, getKey };

enum ShipModuleType {
  hull,
  gun,
  secondary,
  torpedo,
  pinger,
  fireControl,
  engine,
  depthCharge,
  airSupport,
  airDefense,
  fighter,
  skipBomber,
  torpedoBomber,
  diveBomber,
  special,
  unknown,
}

class Localisation {
  static instance = new Localisation();

  hull = "Hull";
  artillery = "Artillery";
  secondaries = "Secondaries";
  torpedoes = "Torpedoes";
  sonar = "Sonar";
  fireControl = "Fire Control";
  engine = "Engine";
  airDefense = "Air Defense";
  fighter = "Fighter";
  skipBomber = "Skip Bomber";
  torpedoBomber = "Torpedo Bomber";
  diveBomber = "Dive Bomber";
}

function getModuleName(moduleType: ShipModuleType): string | null {
  switch (moduleType) {
    case ShipModuleType.hull:
      return Localisation.instance.hull;
    case ShipModuleType.gun:
      return Localisation.instance.artillery;
    case ShipModuleType.secondary:
      return Localisation.instance.secondaries;
    case ShipModuleType.torpedo:
      return Localisation.instance.torpedoes;
    case ShipModuleType.pinger:
      return Localisation.instance.sonar;
    case ShipModuleType.fireControl:
      return Localisation.instance.fireControl;
    case ShipModuleType.engine:
      return Localisation.instance.engine;
    case ShipModuleType.depthCharge:
    case ShipModuleType.airSupport:
      return '';
    case ShipModuleType.airDefense:
      return Localisation.instance.airDefense;
    case ShipModuleType.fighter:
      return Localisation.instance.fighter;
    case ShipModuleType.skipBomber:
      return Localisation.instance.skipBomber;
    case ShipModuleType.torpedoBomber:
      return Localisation.instance.torpedoBomber;
    case ShipModuleType.diveBomber:
      return Localisation.instance.diveBomber;
    case ShipModuleType.special:
      return '';
    case ShipModuleType.unknown:
      throw new Error(`Unknown module type: ${moduleType}`);
  }
}


class ShipModules {
  private _logger = new Logger('ShipModules');

  private _ship: Ship;
  constructor(ship: Ship) {
    this._ship = ship;
  }

  private _gunCount: number = 0;
  private _torpCount: number = 0;
  private _hullInfo: ShipModuleHolder<HullInfo>[] = [];
  private _gunInfo: ShipModuleHolder<GunInfo>[] = [];
  private _secondaryInfo: ShipModuleHolder<GunInfo>[] = [];
  private _torpInfo: ShipModuleHolder<TorpedoInfo>[] = [];
  private _pingerInfo: ShipModuleHolder<PingerInfo>[] = [];
  private _fireControlInfo: ShipModuleHolder<FireControlInfo>[] = [];
  private _engineInfo: ShipModuleHolder<EngineInfo>[] = [];
  private _airSupportInfo: ShipModuleHolder<AirSupportInfo>[] = [];
  private _airDefenseInfo: ShipModuleHolder<AirDefense>[] = [];
  private _depthChargeInfo: ShipModuleHolder<DepthChargeInfo>[] = [];
  private _specialsInfo: ShipModuleHolder<SpecialsInfo>[] = [];

  // aircrafts
  private _fighterInfo: ShipModuleHolder<Aircraft>[] = [];
  private _skipBomberInfo: ShipModuleHolder<Aircraft>[] = [];
  private _torpedoBomberInfo: ShipModuleHolder<Aircraft>[] = [];
  private _diveBomberInfo: ShipModuleHolder<Aircraft>[] = [];

  private _moduleSelection: ShipModuleSelection = new ShipModuleSelection();
  public get selection(): ShipModuleSelection {
    return this._moduleSelection;
  }

  public updateSelection(selection: ShipModuleSelection): void {
    this._moduleSelection = selection;
  }
}

    if (index < 0 || index >= list.length) return null;
    return list[index];
}


type ShipModuleType = 'hull' | 'gun' | 'torpedo' | 'fireControl' | 'engine' | 'fighter' | 'skipBomber' | 'torpedoBomber' | 'diveBomber';

interface ShipModuleMap {
  [key: string]: any[];
}

interface ShipProps {
  gunCount: number;
  torpCount: number;
  hullInfo: any[];
  fireControlInfo: any[];
  engineInfo: any[];
  skipBomberInfo: any[];
  torpedoBomberInfo: any[];
  diveBomberInfo: any[];
  fighterInfo: any[];
  gunInfo: any[];
  torpInfo: any[];
}

const Ship: React.FC<ShipProps> = ({
  gunCount,
  torpCount,
  hullInfo,
  fireControlInfo,
  engineInfo,
  skipBomberInfo,
  torpedoBomberInfo,
  diveBomberInfo,
  fighterInfo,
  gunInfo,
  torpInfo,
}) => {
  const canChangeModules = gunCount > 1 ||
    torpCount > 1 ||
    hullInfo.length > 1 ||
    fireControlInfo.length > 1 ||
    engineInfo.length > 1 ||
    skipBomberInfo.length > 1 ||
    torpedoBomberInfo.length > 1 ||
    diveBomberInfo.length > 1 ||
    fighterInfo.length > 1;

  const moduleList: ShipModuleMap = makeModuleList();

  function makeModuleList(): ShipModuleMap {
    const moduleMap: ShipModuleMap = {};
    if (hullInfo.length > 1) moduleMap[ShipModuleType.hull] = hullInfo;
    if (gunCount > 1) moduleMap[ShipModuleType.gun] = gunInfo;
    if (torpCount > 1) moduleMap[ShipModuleType.torpedo] = torpInfo;
    if (fireControlInfo.length > 1) {
      moduleMap[ShipModuleType.fireControl] = fireControlInfo;
    }
    if (engineInfo.length > 1) moduleMap[ShipModuleType.engine] = engineInfo;
    if (fighterInfo.length > 1) {
      moduleMap[ShipModuleType.fighter] = fighterInfo;
    }
    if (skipBomberInfo.length > 1) {
      moduleMap[ShipModuleType.skipBomber] = skipBomberInfo;
    }
    if (torpedoBomberInfo.length > 1) {
      moduleMap[ShipModuleType.torpedoBomber] = torpedoBomberInfo;
    }
    if (diveBomberInfo.length > 1) {
      moduleMap[ShipModuleType.diveBomber] = diveBomberInfo;
    }
    return moduleMap;
  }

  return (
    <div>
      {/* Render your ship components here */}
    </div>
  );
};

export default Ship;


interface ModuleSelection {
  hullIndex: number;
  gunIndex: number;
  secondaryIndex: number;
  torpIndex: number;
  engineIndex: number;
  pingerIndex: number;
  fireControlIndex: number;
  airSupportIndex: number;
  airDefenseIndex: number;
  depthChargeIndex: number;
  specialIndex: number;
  fighterIndex: number;
  skipBomberIndex: number;
  torpedoBomberIndex: number;
  diveBomberIndex: number;
}

class Ship {
  components: any; // Define the type based on your data structure
  modules: Map<string, any>; // Define the type based on your data structure
  index: number;
}

class ShipModuleManager {
  private _ship: Ship;
  private _moduleSelection: ModuleSelection;
  private _logger: Logger;
  private _hullInfo: ShipModuleHolder<HullInfo>[] = [];
  private _gunInfo: ShipModuleHolder<GunInfo>[] = [];
  private _secondaryInfo: ShipModuleHolder<GunInfo>[] = [];
  private _torpInfo: ShipModuleHolder<TorpedoInfo>[] = [];
  private _engineInfo: ShipModuleHolder<EngineInfo>[] = [];
  private _pingerInfo: ShipModuleHolder<PingerInfo>[] = [];
  private _fireControlInfo: ShipModuleHolder<FireControlInfo>[] = [];
  private _airSupportInfo: ShipModuleHolder<AirSupportInfo>[] = [];
  private _airDefenseInfo: ShipModuleHolder<AirDefense>[] = [];
  private _depthChargeInfo: ShipModuleHolder<DepthChargeInfo>[] = [];
  private _specialsInfo: ShipModuleHolder<SpecialsInfo>[] = [];
  private _fighterInfo: ShipModuleHolder<Aircraft>[] = [];
  private _skipBomberInfo: ShipModuleHolder<Aircraft>[] = [];
  private _torpedoBomberInfo: ShipModuleHolder<Aircraft>[] = [];
  private _diveBomberInfo: ShipModuleHolder<Aircraft>[] = [];
  private _gunCount: number = 0;
  private _torpCount: number = 0;

  constructor(ship: Ship, moduleSelection: ModuleSelection, logger: Logger) {
    this._ship = ship;
    this._moduleSelection = moduleSelection;
    this._logger = logger;
  }

  private _valueAt<T>(array: ShipModuleHolder<T>[], index: number): ShipModuleHolder<T> | undefined {
    return array[index];
  }

  get hullInfo(): ShipModuleHolder<HullInfo> | undefined {
    return this._valueAt(this._hullInfo, this._moduleSelection.hullIndex);
  }

  get gunInfo(): ShipModuleHolder<GunInfo> | undefined {
    return this._valueAt(this._gunInfo, this._moduleSelection.gunIndex);
  }

  get secondaryInfo(): ShipModuleHolder<GunInfo> | undefined {
    return this._valueAt(this._secondaryInfo, this._moduleSelection.secondaryIndex);
  }

  get torpedoInfo(): ShipModuleHolder<TorpedoInfo> | undefined {
    return this._valueAt(this._torpInfo, this._moduleSelection.torpIndex);
  }

  get engineInfo(): ShipModuleHolder<EngineInfo> | undefined {
    return this._valueAt(this._engineInfo, this._moduleSelection.engineIndex);
  }

  get pingerInfo(): ShipModuleHolder<PingerInfo> | undefined {
    return this._valueAt(this._pingerInfo, this._moduleSelection.pingerIndex);
  }

  get fireControlInfo(): ShipModuleHolder<FireControlInfo> | undefined {
    return this._valueAt(this._fireControlInfo, this._moduleSelection.fireControlIndex);
  }

  get airSupportInfo(): ShipModuleHolder<AirSupportInfo> | undefined {
    return this._valueAt(this._airSupportInfo, this._moduleSelection.airSupportIndex);
  }

  get airDefenseInfo(): ShipModuleHolder<AirDefense> | undefined {
    return this._valueAt(this._airDefenseInfo, this._moduleSelection.airDefenseIndex);
  }

  get depthChargeInfo(): ShipModuleHolder<DepthChargeInfo> | undefined {
    return this._valueAt(this._depthChargeInfo, this._moduleSelection.depthChargeIndex);
  }

  get specialsInfo(): ShipModuleHolder<SpecialsInfo> | undefined {
    return this._valueAt(this._specialsInfo, this._moduleSelection.specialIndex);
  }

  get fighterInfo(): ShipModuleHolder<Aircraft> | undefined {
    return this._valueAt(this._fighterInfo, this._moduleSelection.fighterIndex);
  }

  get skipBomberInfo(): ShipModuleHolder<Aircraft> | undefined {
    return this._valueAt(this._skipBomberInfo, this._moduleSelection.skipBomberIndex);
  }

  get torpedoBomberInfo(): ShipModuleHolder<Aircraft> | undefined {
    return this._valueAt(this._torpedoBomberInfo, this._moduleSelection.torpedoBomberIndex);
  }

  get diveBomberInfo(): ShipModuleHolder<Aircraft> | undefined {
    return this._valueAt(this._diveBomberInfo, this._moduleSelection.diveBomberIndex);
  }

  private _sortModuleLists() {
    // Implement sorting logic here
  }

  public unpackModules() {
    const shipModules = this._ship.components;
    for (const [moduleType, modules] of this._ship.modules.entries()) {
      for (const module of modules) {
        const components = module.components;

        const addModule = <T>(
          type: ShipModuleType,
          creator: (data: any) => T,
          add: (holder: ShipModuleHolder<T>) => void
        ) => {
          const moduleList = components[type.key];
          if (!moduleList || moduleList.length === 0) return;
          const info = shipModules[moduleList[0]];
          if (!info) {
            this._logger.severe(`Module ${moduleList} not found`);
            return;
          }
          const holder = new ShipModuleHolder<T>({
            module,
            type,
            data: creator(info),
          });
          add(holder);
        };

        switch (moduleType) {
          case '_Hull':
            addModule(ShipModuleType.hull, HullInfo.fromJson, this._hullInfo.push.bind(this._hullInfo));
            addModule(ShipModuleType.depthCharge, DepthChargeInfo.fromJson, this._depthChargeInfo.push.bind(this._depthChargeInfo));
            addModule(ShipModuleType.airSupport, AirSupportInfo.fromJson, this._airSupportInfo.push.bind(this._airSupportInfo));
            addModule(ShipModuleType.airDefense, AirDefense.fromJson, this._airDefenseInfo.push.bind(this._airDefenseInfo));
            addModule(ShipModuleType.secondary, GunInfo.fromJson, this._secondaryInfo.push.bind(this._secondaryInfo));
            addModule(ShipModuleType.pinger, PingerInfo.fromJson, this._pingerInfo.push.bind(this._pingerInfo));
            addModule(ShipModuleType.special, SpecialsInfo.fromJson, this._specialsInfo.push.bind(this._specialsInfo));

            this._gunCount = components['artillery']?.length || 0;
            this._torpCount = components['torpedoes']?.length || 0;
            break;
          case '_Artillery':
            addModule(ShipModuleType.gun, GunInfo.fromJson, this._gunInfo.push.bind(this._gunInfo));
            break;
          case '_Torpedoes':
            addModule(ShipModuleType.torpedo, TorpedoInfo.fromJson, this._torpInfo.push.bind(this._torpInfo));
            break;
          case '_Suo':
            addModule(ShipModuleType.fireControl, FireControlInfo.fromJson, this._fireControlInfo.push.bind(this._fireControlInfo));
            break;
          case '_Engine':
            const moduleList = components[ShipModuleType.engine.key];
            if (!moduleList || moduleList.length === 0) return;
            const info = shipModules[moduleList[0]];
            const holder = new ShipModuleHolder({
              type: ShipModuleType.engine,
              module,
              data: EngineInfo.fromJson(info),
            });
            this._engineInfo.push(holder);
            break;
          case '_SkipBomber':
          case '_TorpedoBomber':
          case '_DiveBomber':
          case '_Fighter':
            const plane = components.values().next().value[0];
            const key = Array.from(shipModules[plane])[0];
            const info = GameRepository.instance.aircraftOf(key);
            if (!info) {
              this._logger.warning(`Cannot find aircraft info of ${key}`);
              continue;
            } else {
              this._logger.fine(`Found aircraft (${moduleType}) info of ${key}`);
            }

            switch (moduleType) {
              case '_SkipBomber':
                this._skipBomberInfo.push(new ShipModuleHolder({
                  type: ShipModuleType.skipBomber,
                  module,
                  data: info,
                }));
                break;
              case '_TorpedoBomber':
                this._torpedoBomberInfo.push(new ShipModuleHolder({
                  type: ShipModuleType.torpedoBomber,
                  module,
                  data: info,
                }));
                break;
              case '_DiveBomber':
                this._diveBomberInfo.push(new ShipModuleHolder({
                  type: ShipModuleType.diveBomber,
                  module,
                  data: info,
                }));
                break;
              case '_Fighter':
                this._fighterInfo.push(new ShipModuleHolder({
                  type: ShipModuleType.fighter,
                  module,
                  data: info,
                }));
                break;
            }
            break;
          case '_Sonar':
          case '_Abilities':
          case '_SecondaryWeapons':
          case '_PrimaryWeapons':
          case '_FlightControl':
            break;
          default:
            throw new Error(`Unknown module - ${moduleType}`);
        }
      }
    }

    if (this.canChangeModules) this._sortModuleLists();
    this._logger.fine(`Unpacked modules of ${this._ship.index}`);
  }
}


class ModuleSorter {
  private hullInfo: string[] = [];
  private secondaryInfo: string[] = [];
  private pingerInfo: string[] = [];
  private torpInfo: string[] = [];
  private fireControlInfo: string[] = [];
  private engineInfo: string[] = [];
  private skipBomberInfo: string[] = [];
  private torpedoBomberInfo: string[] = [];
  private diveBomberInfo: string[] = [];
  private fighterInfo: string[] = [];
  private airSupportInfo: string[] = [];
  private depthChargeInfo: string[] = [];

  constructor() {
    LogBox.ignoreLogs(['Warning: ...']); // Example to ignore specific logs
  }

  private sortModuleLists() {
    this.hullInfo.sort((a, b) => a.localeCompare(b));
    this.secondaryInfo.sort((a, b) => a.localeCompare(b));
    this.pingerInfo.sort((a, b) => a.localeCompare(b));
    this.torpInfo.sort((a, b) => a.localeCompare(b));
    this.fireControlInfo.sort((a, b) => a.localeCompare(b));
    this.engineInfo.sort((a, b) => a.localeCompare(b));
    this.skipBomberInfo.sort((a, b) => a.localeCompare(b));
    this.torpedoBomberInfo.sort((a, b) => a.localeCompare(b));
    this.diveBomberInfo.sort((a, b) => a.localeCompare(b));
    this.fighterInfo.sort((a, b) => a.localeCompare(b));
    this.airSupportInfo.sort((a, b) => a.localeCompare(b));
    this.depthChargeInfo.sort((a, b) => a.localeCompare(b));
    console.log('Sorted all module lists');
  }
}


interface ShipModuleInfo {
  // Define the properties of ShipModuleInfo here
}

interface ShipModuleHolderProps<T> {
  module?: ShipModuleInfo;
  data: T;
  type: string; // Adjust the type as necessary
}

const ShipModuleHolder = <T,>({ module, data, type }: ShipModuleHolderProps<T>): JSX.Element => {
  return (
    <div>
      {module && <div>{/* Render module information here */}</div>}
      <div>{JSON.stringify(data)}</div>
      <div>{type}</div>
    </div>
  );
};

export default ShipModuleHolder;

  compareTo(other: ShipModuleInfo): number;
}

enum ShipModuleType {
  // Define your module types here
}

class ShipModuleHolder<T> {
  module: ShipModuleInfo | null;
  data: T;
  type: ShipModuleType;

  constructor(module: ShipModuleInfo | null, data: T, type: ShipModuleType) {
    this.module = module;
    this.data = data;
    this.type = type;
  }

  compareTo(other: ShipModuleHolder<T>): number {
    if (other.module === null) return 0;
    if (this.module === null) return 0;
    return this.module.compareTo(other.module);
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