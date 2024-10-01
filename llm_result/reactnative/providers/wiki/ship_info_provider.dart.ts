
interface ShipInfoContextType {
  ship: Ship;
  shipModules: ShipModules;
  shipUpgrades: ShipUpgrades;
}

const ShipInfoContext = createContext<ShipInfoContextType | undefined>(undefined);

export const ShipInfoProvider: React.FC<{ ship: Ship }> = ({ ship, children }) => {
  const logger = new Logger('ShipInfoProvider');
  const [shipModules, setShipModules] = useState<ShipModules>(new ShipModules(ship));
  const [shipUpgrades, setShipUpgrades] = useState<ShipUpgrades>(new ShipUpgrades(ship));

  useEffect(() => {
    shipModules.unpackModules();
    shipUpgrades.unpackUpgrades();
  }, [shipModules, shipUpgrades]);

  return (
    <ShipInfoContext.Provider value={{ ship, shipModules, shipUpgrades }}>
      {children}
    </ShipInfoContext.Provider>
  );
};

export const useShipInfo = (): ShipInfoContextType => {
  const context = useContext(ShipInfoContext);
  if (!context) {
    throw new Error('useShipInfo must be used within a ShipInfoProvider');
  }
  return context;
};


interface ShipModuleSelection {
  // Define the properties of ShipModuleSelection here
}

interface ShipModules {
  selection: ShipModuleSelection;
  updateSelection: (selection: ShipModuleSelection) => void;
}

const ShipModulesContext = createContext<ShipModules | undefined>(undefined);

export const ShipModulesProvider: React.FC = ({ children }) => {
  const [selection, setSelection] = useState<ShipModuleSelection | null>(null);

  const updateSelection = (newSelection: ShipModuleSelection) => {
    setSelection(newSelection);
    console.log('Updated ship module selection');
  };

  return (
    <ShipModulesContext.Provider value={{ selection, updateSelection }}>
      {children}
    </ShipModulesContext.Provider>
  );
};

export const useShipModules = () => {
  const context = useContext(ShipModulesContext);
  if (!context) {
    throw new Error('useShipModules must be used within a ShipModulesProvider');
  }
  return context;
};

const rawPercent = (value: number | null): string => {
    if (value === null) return '-';
    return `${(value * 100).toFixed(2)}%`;
};

    if (value === null) return '-';
    return (value * 100).toFixed(2) + '%';
}


const format = (value: number | null, suffix: string = ''): string => {
    if (value === null) return '-';
    return `${value.toFixed(2)} ${suffix}`;
};

const FormattedText: React.FC<{ value: number | null; suffix?: string }> = ({ value, suffix = '' }) => {
    return <Text>{format(value, suffix)}</Text>;
};

export default FormattedText;


const toDecimalString = (num: number): string => {
  return num.toFixed(2); // Adjust the precision as needed
};

const calc = (value: number | null, factor: number): string => {
  if (value === null) return '-';
  const calculated = value * factor;
  return `${toDecimalString(value)} - ${toDecimalString(calculated)}`;
};

const MyComponent: React.FC<{ value: number | null; factor: number }> = ({ value, factor }) => {
  return (
    <Text>{calc(value, factor)}</Text>
  );
};

export default MyComponent;

function calcAdd(value: number | null, offset: number): string {
    if (value === null) return '-';
    const calculated = (value + offset).toFixed(2);
    return `${value.toFixed(2)} - ${calculated}`;
}


class Ship {
    private _shipUpgrades: any; // Define the type as necessary
    private _ship: any; // Define the type as necessary

    get allModifiers() {
        const flags = GameRepository.instance.usefulFlagList;
        const flagModifiers = flags
            .map((e: any) => e.modifiers)
            .filter((e: any) => e !== null)
            .reduce((a: any, b: any) => a?.merge(b), null);

        const upgrades = this._shipUpgrades.modernizations;
        const upgradeModifiers = upgrades
            .map((e: any) => e.modifiers)
            .reduce((a: any, b: any) => a.merge(b), null);

        const commanderSkills = GameRepository.instance.commanderSkills[this._ship.type];
        const skills = commanderSkills
            ?.flatMap((e: any) => e)
            .map((e: any) => GameRepository.instance.skillOf(e.name)?.modifiers)
            .filter((e: any) => e !== null)
            .reduce((a: any, b: any) => a?.merge(b), null);

        const consumables = this._ship.consumables.flatMap((e: any) => e);
        const consumableModifiers = consumables
            .map((e: any) => GameRepository.instance.abilityOf(e.name)?.abilityList.reduce((a: any, b: any) => a.merge(b), null))
            .filter((e: any) => e !== null)
            .reduce((a: any, b: any) => a?.merge(b), null);

        if (flagModifiers === null || skills === null || consumableModifiers === null) {
            throw new Error('All modifiers should be valid');
        }

        return [flagModifiers, upgradeModifiers, skills, consumableModifiers]
            .filter((e: any) => e !== null)
            .reduce((a: any, b: any) => a?.merge(b), null);
    }
}


interface Ship {
  id: string;
  tier: number;
  name: string;
}

interface GameInfo {
  tiers: string[];
}

interface Props {
  ship: Ship;
  shipIcon: JSX.Element;
  allModifiers: any[]; // Replace with the actual type of modifiers
  gameInfo: GameInfo;
}

const ShipInfo: React.FC<Props> = ({ ship, shipIcon, allModifiers, gameInfo }) => {
  const testModifiers = allModifiers.toString();

  const title = `${shipIcon} ${ship.id}`;
  const tier = gameInfo.tiers[ship.tier - 1];

  const shipName = Localisation.instance.stringOf(ship.name);

  return (
    <Text>
      {title}
      {'\n'}
      {tier}
      {'\n'}
      {shipName}
      {'\n'}
      {testModifiers}
    </Text>
  );
};

export default ShipInfo;

    if (shipName === null) return '-';
    return `${tier} ${shipName}`;
};


interface Ship {
  index: string;
  description: string;
  typeId: string;
  regionId: string;
  costCr: number;
  costGold: number;
  id: number;
}

interface ShipModules {
  canChangeModules: boolean;
  moduleList: ShipModuleMap;
  hullInfo?: { data: HullInfo };
  gunInfo?: { data: GunInfo };
  fireControlInfo?: { data: { maxDistCoef: number } };
}

class ShipComponent extends React.Component<{ ship: Ship; shipModules: ShipModules }> {
  private _ship: Ship;
  private _shipModules: ShipModules;

  constructor(props: { ship: Ship; shipModules: ShipModules }) {
    super(props);
    this._ship = props.ship;
    this._shipModules = props.shipModules;
  }

  get shipIcon(): string {
    return this._ship.index;
  }

  get description(): string {
    return Localisation.instance.stringOf(this._ship.description) || '-';
  }

  get type(): string {
    return Localisation.instance.stringOf(this._ship.typeId) || '-';
  }

  get region(): string {
    return Localisation.instance.stringOf(this._ship.regionId) || '-';
  }

  get costCR(): string | null {
    return this._ship.costCr > 0 ? this._format(this._ship.costCr) : null;
  }

  get costGold(): string | null {
    return this._ship.costGold > 0 ? this._format(this._ship.costGold) : null;
  }

  get shipAdditional(): ShipAdditional | null {
    return GameRepository.instance.shipAdditionalOf(this._ship.id.toString());
  }

  get canChangeModules(): boolean {
    return this._shipModules.canChangeModules;
  }

  get moduleList(): ShipModuleMap {
    return this._shipModules.moduleList;
  }

  private get _hullInfo(): HullInfo | undefined {
    return this._shipModules.hullInfo?.data;
  }

  get renderHull(): boolean {
    return this._hullInfo !== undefined;
  }

  get health(): string {
    return this._format(this._hullInfo?.health);
  }

  get torpedoProtection(): string {
    return this._rawPercent(this._hullInfo?.protection);
  }

  private get _mainGunInfo(): GunInfo | undefined {
    return this._shipModules.gunInfo?.data;
  }

  get renderMainGun(): boolean {
    return this._mainGunInfo !== undefined;
  }

  private get _gun(): WeaponInfo | undefined {
    return this._mainGunInfo?.guns[0];
  }

  get gunReloadTime(): string {
    return this._format(this._gun?.reload, 's');
  }

  get gunRange(): string {
    const suo = this._shipModules.fireControlInfo;
    const range = this._mainGunInfo?.range;
    if (range === undefined) return '-';
    if (suo === undefined) {
      return this._format(range / 1000, 'km');
    }

    const improvedRange = range * suo.data.maxDistCoef;
    return this._format(improvedRange / 1000, 'km');
  }

  private _format(value?: number, suffix?: string): string {
    return value !== undefined ? `${value}${suffix ? ' ' + suffix : ''}` : '-';
  }

  private _rawPercent(value?: number): string {
    return value !== undefined ? `${value}%` : '-';
  }

  render() {
    // Render logic here
    return null; // Replace with actual rendering logic
  }
}


interface Gun {
  count: number;
  each: number;
}

interface MainGunInfo {
  guns: Gun[] | null;
}

interface Props {
  mainGunInfo: MainGunInfo | null;
}

const GunConfiguration: React.FC<Props> = ({ mainGunInfo }) => {
  const getGunConfiguration = () => {
    const guns = mainGunInfo?.guns;
    if (guns === null) return '-';

    const config: string[] = [];
    for (const gun of guns) {
      config.push(`${gun.count} x ${gun.each}`);
    }

    if (config.length === 0) return '-';
    return config.join(' ');
  };

  return (
    <View>
      <Text>{getGunConfiguration()}</Text>
    </View>
  );
};

export default GunConfiguration;


interface Burst {
  fullReloadTime: number;
  burstReloadTime: number;
  shotsCount: number;
  modifiers?: any; // Adjust type as necessary
}

interface MainGunInfo {
  sigma: number;
  burst?: Burst;
}

interface ShipModules {
  gunInfo?: {
    module?: {
      name: string;
    };
  };
}

interface GunProps {
  gun?: {
    rotation: number;
  };
  mainGunInfo?: MainGunInfo;
  shipModules: ShipModules;
}

const GunComponent: React.FC<GunProps> = ({ gun, mainGunInfo, shipModules }) => {
  const format = (value: number | undefined, suffix?: string) => {
    return value !== undefined ? `${value} ${suffix}` : '-';
  };

  const gunRotationTime = format(gun?.rotation, Localisation.instance.second);
  const gunName = Localisation.instance.stringOf(shipModules.gunInfo?.module?.name) || '-';
  const gunSigma = format(mainGunInfo?.sigma, 'σ');

  const hasBurstFire = mainGunInfo?.burst !== undefined;
  const burst = mainGunInfo?.burst;
  const burstFireHolder = extractBurstFire(burst);

  const extractBurstFire = (burst: Burst | undefined) => {
    if (!burst) return null;
    return {
      reload: format(burst.fullReloadTime, Localisation.instance.second),
      interval: format(burst.burstReloadTime, Localisation.instance.second),
      shots: format(burst.shotsCount),
      modifiers: burst.modifiers?.toString(),
    };
  };

  return (
    <Text>
      Gun Rotation Time: {gunRotationTime}
      {'\n'}Gun Name: {gunName}
      {'\n'}Gun Sigma: {gunSigma}
      {'\n'}Has Burst Fire: {hasBurstFire ? 'Yes' : 'No'}
      {'\n'}Burst Fire Details: {burstFireHolder ? JSON.stringify(burstFireHolder) : 'None'}
    </Text>
  );
};

export default GunComponent;


interface Burst {
  shotsCount?: number;
  burstReloadTime?: number;
  fullReloadTime?: number;
  modifiers?: any;
}

interface MainGunInfo {
  guns: Array<{
    ammo: string[];
  }>;
}

interface Props {
  burst?: Burst;
  _mainGunInfo?: MainGunInfo;
}

const MyComponent: React.FC<Props> = ({ burst, _mainGunInfo }) => {
  const _logger = new Logger();

  const _format = (value?: number, suffix?: string) => {
    return value !== undefined ? `${value} ${suffix || ''}` : 'N/A';
  };

  const _percent = (value?: number) => {
    return value !== undefined ? `${value}%` : 'N/A';
  };

  const burstShots = _format(burst?.shotsCount);
  const burstReloadTime = _format(burst?.burstReloadTime, Localisation.instance.second);
  const burstFullReloadTime = _format(burst?.fullReloadTime, Localisation.instance.second);
  const burstModifier = burst?.modifiers?.toString();

  const shells = _extractShells(_mainGunInfo);

  const _extractShells = (gunInfo?: GunInfo) => {
    if (!gunInfo) return [];
    const shells: ShellHolder[] = [];
    const gun = gunInfo.guns[0];
    for (const ammo of gun.ammo) {
      const ammoInfo = GameRepository.instance.projectileOf(ammo);
      if (!ammoInfo) continue;

      _logger.fine(`Found ammo: ${ammoInfo.name}`);
      const type = ammoInfo.ammoType;
      if (!type) continue;

      const shell = new ShellHolder(type);
      shell.burnChance = _percent(ammoInfo.burnChance);
      shell.damage = _format(ammoInfo.damage);
      shell.velocity = _format(ammoInfo.speed, Localisation.instance.meterPerSecond);
      shell.weight = _format(ammoInfo.weight, Localisation.instance.kilogram);
      switch (ammoInfo.ammoType) {
        case 'HE':
          shell.penetration = _format(ammoInfo.penHe, Localisation.instance.millimeter);
          break;
        case 'AP':
          shell.overmatch = _format(ammoInfo.overmatch, Localisation.instance.millimeter);
          break;
        case 'CS':
          shell.penetration = _format(ammoInfo.penSAP, Localisation.instance.millimeter);
          break;
        default:
          _logger.severe(`Unknown ammo type: ${ammoInfo.type}`);
          continue;
      }
      shells.push(shell);
    }
    return shells;
  };

  return (
    <div>
      {/* Render your component UI here */}
    </div>
  );
};

export default MyComponent;


interface GunInfo {
  range: number;
}

interface ShipModules {
  secondaryInfo?: {
    data?: GunInfo;
  };
}

interface Props {
  shipModules: ShipModules;
}

const SecondaryGuns: React.FC<Props> = ({ shipModules }) => {
  const secondaryInfo = shipModules.secondaryInfo?.data;

  const renderSecondaryGun = secondaryInfo !== undefined;

  const extractSecondaryGuns = (info?: GunInfo) => {
    // Implement your logic to extract secondary guns from info
    return []; // Placeholder for actual extraction logic
  };

  const secondaryGuns = extractSecondaryGuns(secondaryInfo);

  const secondaryRange = () => {
    const range = secondaryInfo?.range;
    if (range == null) return '-';
    return `${(range / 1000).toFixed(2)} ${Localisation.instance.kilometer}`;
  };

  return (
    <div>
      {renderSecondaryGun && (
        <div>
          <h2>Secondary Guns</h2>
          <p>Range: {secondaryRange()}</p>
          {/* Render secondaryGuns here */}
        </div>
      )}
    </div>
  );
};

export default SecondaryGuns;


interface AmmoInfo {
  burnChance: number;
  damage: number;
  speed: number;
  penHe?: number;
  penSAP?: number;
  ammoType: string;
}

interface Gun {
  ammo: string[];
  count: number;
  each: string;
  reload: number;
}

interface GunInfo {
  guns: Gun[];
}

class SecondaryGunHolder {
  name: string;
  burnChance: string;
  damage: string;
  velocity: string;
  reloadTime: string;
  config: string;
  penetration?: string;

  constructor(name: string) {
    this.name = name;
    this.burnChance = '';
    this.damage = '';
    this.velocity = '';
    this.reloadTime = '';
    this.config = '';
  }
}

const _percent = (value: number): string => `${value}%`;
const _format = (value: number, suffix?: string): string => `${value}${suffix ? ' ' + suffix : ''}`;

const _extractSecondaryGuns = (gunInfo: GunInfo | null): SecondaryGunHolder[] => {
  if (gunInfo === null) return [];
  const guns: SecondaryGunHolder[] = [];
  
  for (const gun of gunInfo.guns) {
    const ammo = gun.ammo[0];
    const ammoInfo: AmmoInfo | null = GameRepository.instance.projectileOf(ammo);
    if (ammoInfo === null) continue;

    const config = `${gun.count} x ${gun.each}`;
    const holder = new SecondaryGunHolder(`${config} ${Localisation.instance.stringOf(ammo, 'IDS_')}`);

    holder.burnChance = _percent(ammoInfo.burnChance);
    holder.damage = _format(ammoInfo.damage);
    holder.velocity = _format(ammoInfo.speed, Localisation.instance.meterPerSecond);
    holder.reloadTime = _format(gun.reload, Localisation.instance.second);
    holder.config = config;

    switch (ammoInfo.ammoType) {
      case 'HE':
        holder.penetration = _format(ammoInfo.penHe!, Localisation.instance.millimeter);
        break;
      case 'CS':
        holder.penetration = _format(ammoInfo.penSAP!, Localisation.instance.millimeter);
        break;
      default:
        break;
    }
    guns.push(holder);
  }
  return guns;
};


interface PingerInfo {
  rangeInKm?: number;
  reload?: number;
  speed?: number;
  lifeTime1?: number;
  lifeTime2?: number;
}

interface SubmarineBatteryInfo {
  capacity?: number;
  regen?: number;
}

interface TorpedoInfo {
  launchers: Array<{
    count: number;
    each: number;
    reload?: number;
    rotation?: number;
  }>;
}

interface ShipModules {
  pingerInfo?: { data: PingerInfo };
  torpedoInfo?: { data: TorpedoInfo };
}

interface HullInfo {
  submarineBattery?: SubmarineBatteryInfo;
}

interface Props {
  shipModules: ShipModules;
  hullInfo?: HullInfo;
}

const ShipInfo: React.FC<Props> = ({ shipModules, hullInfo }) => {
  const pingerInfo = shipModules.pingerInfo?.data;
  const submarineBatteryInfo = hullInfo?.submarineBattery;
  const torpedoInfo = shipModules.torpedoInfo?.data;
  const torpedo = torpedoInfo?.launchers[0];

  const format = (value?: number, suffix?: string) => {
    return value !== undefined ? `${value} ${suffix}` : '-';
  };

  const renderPinger = pingerInfo !== undefined;
  const renderSubmarineBattery = submarineBatteryInfo !== undefined;
  const renderTorpedo = torpedoInfo !== undefined;

  const pingerRange = format(pingerInfo?.rangeInKm, Localisation.instance.kilometer);
  const pingerReloadTime = format(pingerInfo?.reload, Localisation.instance.second);
  const pingerSpeed = format(pingerInfo?.speed, Localisation.instance.meterPerSecond);
  const pingerDuration = `${format(pingerInfo?.lifeTime1, Localisation.instance.second)} | ${format(pingerInfo?.lifeTime2, Localisation.instance.second)}`;

  const submarineBatteryCapacity = format(submarineBatteryInfo?.capacity, Localisation.instance.unit);
  const submarineBatteryUseRate = format(1.0, Localisation.instance.unit);
  const submarineBatteryRegen = format(submarineBatteryInfo?.regen, Localisation.instance.unitPerSecond);

  const torpedoReloadTime = format(torpedo?.reload, Localisation.instance.second);
  const torpedoRotationTime = format(torpedo?.rotation, Localisation.instance.second);
  
  const torpedoConfiguration = () => {
    if (!torpedoInfo) return '-';
    return torpedoInfo.launchers.map(launcher => `${launcher.count} x ${launcher.each}`).join(' ');
  };

  return (
    <div>
      {renderPinger && (
        <div>
          <p>Pinger Range: {pingerRange}</p>
          <p>Pinger Reload Time: {pingerReloadTime}</p>
          <p>Pinger Speed: {pingerSpeed}</p>
          <p>Pinger Duration: {pingerDuration}</p>
        </div>
      )}
      {renderSubmarineBattery && (
        <div>
          <p>Submarine Battery Capacity: {submarineBatteryCapacity}</p>
          <p>Submarine Battery Use Rate: {submarineBatteryUseRate}</p>
          <p>Submarine Battery Regen: {submarineBatteryRegen}</p>
        </div>
      )}
      {renderTorpedo && (
        <div>
          <p>Torpedo Reload Time: {torpedoReloadTime}</p>
          <p>Torpedo Rotation Time: {torpedoRotationTime}</p>
          <p>Torpedo Configuration: {torpedoConfiguration()}</p>
        </div>
      )}
    </div>
  );
};

export default ShipInfo;


interface TorpedoInfo {
  ammo: string[];
}

interface AmmoInfo {
  alphaDamage?: number;
  damage?: number;
  range?: number;
  visibility?: number;
  speed?: number;
  floodChance?: number;
}

interface TorpedoHolder {
  name: string;
  damage?: string;
  range?: string;
  visibility?: string;
  speed?: string;
  floodChance?: string;
  reactionTime?: string;
}

class TorpedoExtractor {
  private _torpedoInfo: TorpedoInfo | null = null;

  get torpedoes(): TorpedoHolder[] {
    return this._extractTorpedoes(this._torpedoInfo);
  }

  private _extractTorpedoes(torpedoInfo: TorpedoInfo | null): TorpedoHolder[] {
    if (!torpedoInfo) return [];
    const torpedoes: TorpedoHolder[] = [];
    
    for (const ammo of torpedoInfo.ammo) {
      const ammoInfo: AmmoInfo | null = GameRepository.instance.projectileOf(ammo);
      if (!ammoInfo) continue;

      const torpName = Localisation.instance.stringOf(ammo, 'IDS_') || '-';
      const holder: TorpedoHolder = { name: torpName };

      const alphaDamage = ammoInfo.alphaDamage || 0;
      const damage = ammoInfo.damage || 0;
      const actualDamage = Math.round(alphaDamage / 3 + damage);
      if (actualDamage > 0) {
        holder.damage = this._format(actualDamage);
      }

      const range = ammoInfo.range || 0;
      const actualRange = range / (100.0 / 3.0);
      if (actualRange > 0) {
        holder.range = this._format(actualRange, Localisation.instance.kilometer);
      }

      const visibility = ammoInfo.visibility;
      if (visibility != null) {
        holder.visibility = this._format(visibility, Localisation.instance.kilometer);
      }

      const speed = ammoInfo.speed;
      if (speed != null) {
        holder.speed = this._format(speed, Localisation.instance.knot);
      }

      const floodChance = ammoInfo.floodChance;
      if (floodChance != null) {
        holder.floodChance = this._rawPercent(floodChance);
      }

      const reaction = (visibility || 0) / (speed || 1) / 2.6854 * 1000;
      if (reaction > 0) {
        holder.reactionTime = this._format(reaction, Localisation.instance.second);
      }

      torpedoes.push(holder);
    }
    return torpedoes;
  }

  private _format(value: number, suffix?: string): string {
    return `${value}${suffix ? ' ' + suffix : ''}`;
  }

  private _rawPercent(value: number): string {
    return `${value}%`;
  }
}


interface TorpedoInfo {
  launchers: { ammo: string[] }[];
}

interface ShipModules {
  specialsInfo?: SpecialsInfo;
  airDefenseInfo?: { data: AirDefense };
}

interface Props {
  _torpedoInfo?: TorpedoInfo;
  _shipModules: ShipModules;
  _mainGunInfo?: GunInfo;
  _secondaryInfo?: GunInfo;
}

const ShipComponent: React.FC<Props> = ({
  _torpedoInfo,
  _shipModules,
  _mainGunInfo,
  _secondaryInfo,
}) => {
  const getTorpedoName = () => {
    return Localisation.instance.stringOf(`IDS_${_torpedoInfo?.launchers[0].ammo[0]}`) || '';
  };

  const specialsInfo = _shipModules.specialsInfo?.data;
  const renderSpecials = specialsInfo !== undefined;
  const rageMode = specialsInfo?.rageMode;

  const getSpecialName = () => {
    return Localisation.instance.stringOf(rageMode?.rageModeName.toUpperCase(), {
      prefix: 'IDS_DOCK_RAGE_MODE_TITLE_',
    }) || '';
  };

  const getSpecialDescription = () => {
    return Localisation.instance.stringOf(rageMode?.rageModeName.toUpperCase(), {
      prefix: 'IDS_DOCK_RAGE_MODE_DESCRIPTION_',
    }) || '';
  };

  const getSpecialModifier = () => {
    return rageMode?.modifiers?.toString() || '';
  };

  const getSpecialDuration = () => {
    return _format(rageMode?.boostDuration, {
      suffix: Localisation.instance.second,
    });
  };

  const getSpecialHitsRequired = () => {
    return _format(specialsInfo?.rageMode.requiredHits);
  };

  const airDefense = _shipModules.airDefenseInfo?.data;
  const renderAirDefense = airDefenses.length > 0;
  const airDefenses = extractAirDefenses(airDefense, _mainGunInfo, _secondaryInfo);
  const airBubbles = extractAirBubbles(_mainGunInfo, _secondaryInfo);

  const extractAirBubbles = (mainGunInfo?: GunInfo, secondaryInfo?: GunInfo) => {
    const airBubbles: AirBubbleHolder[] = [];
    const mainBubble = mainGunInfo?.bubbles;
    const secondaryBubble = secondaryInfo?.bubbles;

    [mainBubble, secondaryBubble].forEach((bubble) => {
      if (!bubble) return;
      const holder: AirBubbleHolder = {
        damage: _format(bubble.damage),
        explosions: `${bubble.inner} + ${bubble.outer}`,
        hitChance: _percent(bubble.hitChance),
        range: `${_format(bubble.minRange)}- ${_format(bubble.maxRange, {
          suffix: Localisation.instance.kilometer,
        })}`,
      };
      airBubbles.push(holder);
    });

    return airBubbles;
  };

  return (
    <div>
      {/* Render your component UI here using the above methods */}
    </div>
  );
};

export default ShipComponent;


interface AuraGuns {
  name: string;
  count: number;
  each: number;
}

interface AirDefenseInfo {
  dps: number;
  hitChance: number;
  maxRange: number;
  guns: AuraGuns[];
}

interface AirDefense {
  medium?: AirDefenseInfo[];
  near?: AirDefenseInfo[];
  far?: AirDefenseInfo[];
}

interface GunInfo {
  far?: AirDefenseInfo[];
}

interface AirDefenseHolder {
  name: string;
  damage: string;
  hitChance: string;
  range: string;
}

const Localisation = {
  instance: {
    stringOf: (name: string) => name, // Replace with actual localization logic
    kilometer: 'km',
  },
};

const format = (value: number, suffix?: string) => {
  return `${value}${suffix ? ` ${suffix}` : ''}`;
};

const percent = (value: number) => {
  return `${value}%`;
};

const extractAirDefenses = (
  airDefense: AirDefense | null,
  mainGunInfo: GunInfo | null,
  secondaryInfo: GunInfo | null,
): AirDefenseHolder[] => {
  const airDefenses: AirDefenseHolder[] = [];
  const mid = airDefense?.medium;
  const near = airDefense?.near;
  const far = airDefense?.far;
  const mainFar = mainGunInfo?.far;
  const secondaryFar = secondaryInfo?.far;

  for (const aa of [near, mid, far, mainFar, secondaryFar]) {
    if (!aa) continue;
    for (const aaInfo of aa) {
      const aaGuns: { [key: string]: AuraGuns[] } = {};
      for (const gun of aaInfo.guns) {
        const name = Localisation.instance.stringOf(gun.name);
        if (!name) continue;
        if (!aaGuns[name]) {
          aaGuns[name] = [gun];
        } else {
          aaGuns[name].push(gun);
        }
      }

      for (const [name, gunList] of Object.entries(aaGuns)) {
        if (gunList.length === 0) {
          console.error(`No guns found for ${name}`);
          continue;
        }

        let count = 0;
        const each = gunList[0].each;
        for (const gunInfo of gunList) {
          count += gunInfo.count;
        }

        const holder: AirDefenseHolder = {
          name: `${count} x ${each} ${name}`,
          damage: format(aaInfo.dps),
          hitChance: percent(aaInfo.hitChance),
          range: format(aaInfo.maxRange, Localisation.instance.kilometer),
        };
        airDefenses.push(holder);
      }
    }
  }

  return airDefenses;
};


interface MobilityInfo {
  rudderTime: number;
  speed: number;
  turningRadius: number;
}

interface VisibilityInfo {
  sea: number;
  plane: number;
}

interface AirSupportInfo {
  name: string;
  chargesNum: number;
  reload: number;
  range: number;
  plane: string;
}

interface DepthChargeInfo {
  reload: number;
  groups: number;
  bombs: number;
  ammo: string;
}

interface Projectile {
  damage: number;
  burnChance: number;
  floodChance: number;
  penHe?: number;
  penSAP?: number;
}

interface Aircraft {
  health: number;
  aircraft: {
    attackCount: number;
    bombName: string;
  };
  totalPlanes: number;
}

interface ShipModules {
  airSupportInfo?: { data: AirSupportInfo };
  depthChargeInfo?: { data: DepthChargeInfo };
}

interface ShipUpgrades {
  modernizations: Modernization[];
  modernizationsBySlot: List<List<Modernization>>;
}

interface Ship {
  nextShips?: string[];
  consumables: List<List<Consumable>>;
}

class ShipInfo {
  private hullInfo: { mobility?: MobilityInfo; visibility?: VisibilityInfo };
  private shipModules: ShipModules;
  private shipUpgrades: ShipUpgrades;
  private ship: Ship;

  private format(value?: number, suffix?: string): string {
    return value !== undefined ? `${value} ${suffix || ''}` : 'N/A';
  }

  private percent(value?: number): string {
    return value !== undefined ? `${(value * 100).toFixed(2)}%` : 'N/A';
  }

  private rawPercent(value?: number): string {
    return value !== undefined ? `${value}%` : 'N/A';
  }

  private get mobility(): MobilityInfo | undefined {
    return this.hullInfo.mobility;
  }

  public get renderMobility(): boolean {
    return this.mobility !== undefined;
  }

  public get rudderTime(): string {
    return this.format(this.mobility?.rudderTime, Localisation.instance.second);
  }

  public get maxSpeed(): string {
    return this.format(this.mobility?.speed, Localisation.instance.knot);
  }

  public get turningRadius(): string {
    return this.format(this.mobility?.turningRadius, Localisation.instance.meter);
  }

  private get visibility(): VisibilityInfo | undefined {
    return this.hullInfo.visibility;
  }

  public get renderVisibility(): boolean {
    return this.visibility !== undefined;
  }

  public get seaVisibility(): string {
    return this.format(this.visibility?.sea, Localisation.instance.kilometer);
  }

  public get planeVisibility(): string {
    return this.format(this.visibility?.plane, Localisation.instance.kilometer);
  }

  private get airSupport(): AirSupportInfo | undefined {
    return this.shipModules.airSupportInfo?.data;
  }

  public get renderAirSupport(): boolean {
    return this.airSupport !== undefined;
  }

  public get airSupportName(): string {
    return Localisation.instance.stringOf(this.airSupport?.name) || '';
  }

  public get airSupportCharges(): string {
    return this.format(this.airSupport?.chargesNum);
  }

  public get airSupportReload(): string {
    return this.format(this.airSupport?.reload, Localisation.instance.second);
  }

  public get airSupportRange(): string {
    return this.format(this.airSupport?.range, Localisation.instance.kilometer);
  }

  private get airSupportPlane(): Aircraft | undefined {
    return GameRepository.instance.aircraftOf(this.airSupport?.plane || '');
  }

  public get airSupportPlaneHealth(): string {
    return this.format(this.airSupportPlane?.health);
  }

  public get airSupportBombs(): string {
    return this.format(this.airSupportPlane?.aircraft?.attackCount);
  }

  public get airSupportTotalPlanes(): string {
    return this.format(this.airSupportPlane?.totalPlanes);
  }

  private get aircraftBomb(): Projectile | undefined {
    return GameRepository.instance.projectileOf(this.airSupportPlane?.aircraft?.bombName || '');
  }

  public get airSupportBombDamage(): string {
    return this.format(this.aircraftBomb?.damage);
  }

  public get airSupportBombBurnChance(): string {
    return this.percent(this.aircraftBomb?.burnChance);
  }

  public get airSupportBombFloodChance(): string {
    return this.rawPercent(this.aircraftBomb?.floodChance);
  }

  public get airSupportBombPenetration(): string {
    return this.format(
      this.aircraftBomb?.penHe || this.aircraftBomb?.penSAP,
      Localisation.instance.millimeter,
    );
  }

  private get depthCharge(): DepthChargeInfo | undefined {
    return this.shipModules.depthChargeInfo?.data;
  }

  public get renderDepthCharge(): boolean {
    return this.depthCharge !== undefined;
  }

  public get depthChargeReload(): string {
    return this.format(this.depthCharge?.reload, Localisation.instance.second);
  }

  public get depthChargeConfig(): string {
    return `${this.format(this.depthCharge?.groups)} x ${this.format(this.depthCharge?.bombs)}`;
  }

  private get depthChargeAmmo(): Projectile | undefined {
    return GameRepository.instance.projectileOf(this.depthCharge?.ammo || '');
  }

  public get depthChargeDamage(): string {
    return this.format(this.depthChargeAmmo?.damage);
  }

  public get depthChargeBurnChance(): string {
    return this.percent(this.depthChargeAmmo?.burnChance);
  }

  public get depthChargeFloodChance(): string {
    return this.rawPercent(this.depthChargeAmmo?.floodChance);
  }

  public get hasUpgrades(): boolean {
    return this.shipUpgrades.modernizations.length > 0;
  }

  public get upgrades(): List<List<Modernization>> {
    return this.shipUpgrades.modernizationsBySlot;
  }

  public get hasNextShip(): boolean {
    return this.ship.nextShips?.length > 0;
  }

  public get nextShips(): Iterable<Ship | undefined> {
    return this.ship.nextShips?.map(e => GameRepository.instance.shipOf(e.toString())) || [];
  }

  public get hasConsumables(): boolean {
    return this.ship.consumables.length > 0;
  }

  public get consumables(): List<List<Consumable>> {
    return this.ship.consumables;
  }
}

class ShellHolder {
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}

interface BurstFireHolder {
  reload: number;
  interval: number;
  shots: number;
  modifiers?: any; // Replace 'any' with the appropriate type if known
}

class Weapon {
  name: string;
  burnChance?: string;
  weight?: string;
  velocity?: string;
  damage?: string;
  penetration?: string;
  overmatch?: string;

  constructor(name: string, burnChance?: string, weight?: string, velocity?: string, damage?: string, penetration?: string, overmatch?: string) {
    this.name = name;
    this.burnChance = burnChance;
    this.weight = weight;
    this.velocity = velocity;
    this.damage = damage;
    this.penetration = penetration;
    this.overmatch = overmatch;
  }
}

  reload: string;
  interval: string;
  shots: string;
  modifiers?: string;
}

class SecondaryGunHolder {
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}

  name: string;
  reloadTime?: string;
  burnChance?: string;
  damage?: string;
  penetration?: string;
  velocity?: string;
  config?: string;
}

class TorpedoHolder {
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}


interface AirDefense {
  name: string;
  damage?: string;
  range?: string;
  visibility?: string;
  speed?: string;
  reactionTime?: string;
  floodChance?: string;
}

interface AirDefenseHolderProps {
  name: string;
  damage: string;
  range: string;
  hitChance: string;
}

const AirDefenseHolder: React.FC<AirDefenseHolderProps> = ({ name, damage, range, hitChance }) => {
  return (
    <View>
      <Text>Name: {name}</Text>
      <Text>Damage: {damage}</Text>
      <Text>Range: {range}</Text>
      <Text>Hit Chance: {hitChance}</Text>
    </View>
  );
};

export default AirDefenseHolder;


interface AirBubble {
  name: string;
  damage: string;
  range: string;
  hitChance: string;
}

interface AirBubbleHolderProps {
  damage: string;
  range: string;
  hitChance: string;
  explosions: number;
}

const AirBubbleHolder: React.FC<AirBubbleHolderProps> = ({ damage, range, hitChance, explosions }) => {
  return (
    <View>
      <Text>Damage: {damage}</Text>
      <Text>Range: {range}</Text>
      <Text>Hit Chance: {hitChance}</Text>
      <Text>Explosions: {explosions}</Text>
    </View>
  );
};

export default AirBubbleHolder;


interface WeaponStatsProps {
  damage: string;
  range: string;
  hitChance: string;
  explosions: string;
}

const WeaponStats: React.FC<WeaponStatsProps> = ({ damage, range, hitChance, explosions }) => {
  return (
    <View>
      <Text>Damage: {damage}</Text>
      <Text>Range: {range}</Text>
      <Text>Hit Chance: {hitChance}</Text>
      <Text>Explosions: {explosions}</Text>
    </View>
  );
};

export default WeaponStats;