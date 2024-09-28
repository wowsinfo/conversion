
const premiumGroups = [
  'special',
];

const specialGroups = [
  'ultimate',
  'specialUnsellable',
  'upgradeableUltimate',
  'upgradeableExclusive',
  'unavailable',
  'disabled',
  'preserved',
  'clan',
  'earlyAccess',
  'demoWithoutStats',
  'demoWithStats'
];

const specialShipGroups = [
  'preserved',
  'disabled',
  'clan',
  'unavailable',
  'coopOnly',
];

interface Consumable {
  // Define properties for Consumable
}

interface Module {
  // Define properties for Module
}

interface Component {
  // Define properties for Component
}

interface ShipProps {
  name: string;
  description: string;
  year: number;
  paperShip: boolean;
  id: string;
  index: number;
  tier: number;
  region: string;
  type: string;
  regionId: string;
  typeId: string;
  group: string;
  consumables: Consumable[];
  costXp: number;
  costGold: number;
  costCr: number;
  nextShips?: string[];
  modules: Module[];
  components: Component[];
  added?: boolean;
}

const Ship: React.FC<ShipProps> = ({
  name,
  description,
  year,
  paperShip,
  id,
  index,
  tier,
  region,
  type,
  regionId,
  typeId,
  group,
  consumables,
  costXp,
  costGold,
  costCr,
  nextShips,
  modules,
  components,
  added,
}) => {
  return (
    <View>
      <Text>Name: {name}</Text>
      <Text>Description: {description}</Text>
      <Text>Year: {year}</Text>
      <Text>Paper Ship: {paperShip ? 'Yes' : 'No'}</Text>
      <Text>ID: {id}</Text>
      <Text>Index: {index}</Text>
      <Text>Tier: {tier}</Text>
      <Text>Region: {region}</Text>
      <Text>Type: {type}</Text>
      <Text>Region ID: {regionId}</Text>
      <Text>Type ID: {typeId}</Text>
      <Text>Group: {group}</Text>
      <Text>Cost XP: {costXp}</Text>
      <Text>Cost Gold: {costGold}</Text>
      <Text>Cost Cr: {costCr}</Text>
      {nextShips && <Text>Next Ships: {nextShips.join(', ')}</Text>}
      <Text>Added: {added ? 'Yes' : 'No'}</Text>
    </View>
  );
};

export default Ship;


interface Consumable {}

interface ShipModuleInfo {}

interface ShipProps {
  name: string;
  description: string;
  year: string;
  paperShip: boolean;
  id: number;
  index: string;
  tier: number;
  region: string;
  type: string;
  regionId: string;
  typeId: string;
  group: string;
  consumables: Consumable[][];
  costXp: number;
  costGold: number;
  costCr: number;
  nextShips?: number[];
  modules: Record<string, ShipModuleInfo[]>;
  components: Record<string, any>;
  added?: number;
}

const GameInfo = {
  tiers: ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X'], // Example tiers
};

const _specialGroups = ['specialGroup1', 'specialGroup2']; // Example special groups
const _premiumGroups = ['premiumGroup1', 'premiumGroup2']; // Example premium groups
const _specialShipGroups = ['specialShipGroup1', 'specialShipGroup2']; // Example special ship groups

const Ship: React.FC<ShipProps> = ({
  name,
  description,
  year,
  paperShip,
  id,
  index,
  tier,
  region,
  type,
  group,
  consumables,
  costXp,
  costGold,
  costCr,
  nextShips,
  modules,
  components,
  added,
}) => {
  const tierString = GameInfo.tiers[tier - 1];

  const isSpecial = _specialGroups.includes(group);
  const isPremium = _premiumGroups.includes(group);
  const isSpecialGroup = _specialShipGroups.includes(group);

  return (
    <div>
      <h1>{name}</h1>
      <p>{description}</p>
      <p>Year: {year}</p>
      <p>Paper Ship: {paperShip ? 'Yes' : 'No'}</p>
      <p>ID: {id}</p>
      <p>Index: {index}</p>
      <p>Tier: {tierString}</p>
      <p>Region: {region}</p>
      <p>Type: {type}</p>
      <p>Group: {group}</p>
      <p>Consumables: {JSON.stringify(consumables)}</p>
      <p>Cost XP: {costXp}</p>
      <p>Cost Gold: {costGold}</p>
      <p>Cost Cr: {costCr}</p>
      <p>Next Ships: {nextShips ? nextShips.join(', ') : 'None'}</p>
      <p>Modules: {JSON.stringify(modules)}</p>
      <p>Components: {JSON.stringify(components)}</p>
      {added && <p>Added: {added}</p>}
      <p>Is Special: {isSpecial ? 'Yes' : 'No'}</p>
      <p>Is Premium: {isPremium ? 'Yes' : 'No'}</p>
      <p>Is Special Group: {isSpecialGroup ? 'Yes' : 'No'}</p>
    </div>
  );
};

export default Ship;

    tier: number;
    type: string;
    region: string;
    id: number;

    constructor(tier: number, type: string, region: string, id: number) {
        this.tier = tier;
        this.type = type;
        this.region = region;
        this.id = id;
    }

    greater(other: Ship): number {
        // sort by tier, type, region
        if (this.tier === other.tier) {
            if (this.type === other.type) {
                if (this.region === other.region) {
                    return this.id - other.id;
                }
                return this.region.localeCompare(other.region);
            }
            // cv first
            return other.type.localeCompare(this.type);
        }

        return this.tier - other.tier;
    }
}


interface Consumable {
  // Define the properties of Consumable based on your requirements
}

interface ShipModuleInfo {
  cost: number;
  index: number;
  name: string;
  components: any; // Define the type based on your requirements
}

interface Ship {
  name: string;
  description: string;
  year: number;
  paperShip: boolean;
  id: number;
  index: number;
  tier: number;
  region: string;
  type: string;
  regionId: number;
  typeId: number;
  group: string;
  consumables: Consumable[][];
  costXp: number;
  costGold: number;
  costCr: number;
  nextShips: number[] | null;
  modules: { [key: string]: ShipModuleInfo[] };
  components: { [key: string]: any }; // Define the type based on your requirements
  added: string; // Define the type based on your requirements
}

const ShipFromJson = (json: any): Ship => {
  return {
    name: json.name,
    description: json.description,
    year: json.year,
    paperShip: json.paperShip,
    id: json.id,
    index: json.index,
    tier: json.tier,
    region: json.region,
    type: json.type,
    regionId: json.regionID,
    typeId: json.typeID,
    group: json.group,
    consumables: json.consumables.map((x: any) =>
      x.map((y: any) => ConsumableFromJson(y))
    ),
    costXp: json.costXP,
    costGold: json.costGold,
    costCr: json.costCR,
    nextShips: json.nextShips ? Array.from(json.nextShips) : null,
    modules: Object.fromEntries(
      Object.entries(json.modules).map(([key, value]) => [
        key,
        value.map((item: any) => ShipModuleInfoFromJson(item)),
      ])
    ),
    components: { ...json.components },
    added: json.added,
  };
};

const ConsumableFromJson = (json: any): Consumable => {
  // Implement the conversion logic for Consumable
  return {} as Consumable; // Replace with actual implementation
};

const ShipModuleInfoFromJson = (json: any): ShipModuleInfo => {
  return {
    cost: json.cost,
    index: json.index,
    name: json.name,
    components: json.components, // Define the type based on your requirements
  };
};

interface Cost {
  // Define the properties of Cost based on your requirements
}

interface ShipModuleInfo {
  cost: Cost;
  index: number;
  name: string;
  components: Record<string, string[]>;

  compareTo(other: ShipModuleInfo): number;
}

const ShipModuleInfo = {
  fromJson(json: any): ShipModuleInfo {
    return {
      cost: Cost.fromJson(json.cost),
      index: json.index,
      name: json.name,
      components: Object.fromEntries(
        Object.entries(json.components).map(([k, v]) => [k, Array.from(v)])
      ),
      compareTo(other: ShipModuleInfo): number {
        return this.index - other.index;
      }
    };
  }
};

  cost: number;
  index: number;
  components: any[];

  constructor(cost: number, index: number, components: any[]) {
    this.cost = cost;
    this.index = index;
    this.components = components;
  }

  toString(): string {
    return `ShipModule{cost: ${this.cost}, index: ${this.index}, components: ${this.components}}`;
  }
}


interface ShipComponentProps {}

const ShipComponent: React.FC<ShipComponentProps> = () => {
  return (
    <View>
      <Text>Ship Component</Text>
    </View>
  );
};

interface GunInfoProps {
  range: number;
  sigma: number;
  guns: number;
  far?: number;
  bubbles?: number;
  burst?: number;
}

const GunInfo: React.FC<GunInfoProps> = ({ range, sigma, guns, far, bubbles, burst }) => {
  return (
    <View>
      <Text>Gun Info</Text>
      <Text>Range: {range}</Text>
      <Text>Sigma: {sigma}</Text>
      <Text>Guns: {guns}</Text>
      {far !== undefined && <Text>Far: {far}</Text>}
      {bubbles !== undefined && <Text>Bubbles: {bubbles}</Text>}
      {burst !== undefined && <Text>Burst: {burst}</Text>}
    </View>
  );
};

export { ShipComponent, GunInfo };


interface WeaponInfo {
  // Define properties of WeaponInfo based on your requirements
}

interface AuraInfo {
  // Define properties of AuraInfo based on your requirements
}

interface AirBubbles {
  // Define properties of AirBubbles based on your requirements
}

interface Burst {
  burstReloadTime: number;
  fullReloadTime: number;
  modifiers?: any; // Define the type based on your requirements
  shotIntensity: number;
  shotsCount: number;
}

interface GunInfo {
  range: number;
  sigma: number;
  guns: WeaponInfo[];
  far?: AuraInfo[] | null;
  bubbles?: AirBubbles | null;
  burst?: Burst | null;
}

const GunInfoComponent: React.FC<{ gunInfo: GunInfo }> = ({ gunInfo }) => {
  return (
    <View>
      <Text>Range: {gunInfo.range}</Text>
      <Text>Sigma: {gunInfo.sigma}</Text>
      {/* Render guns, far, bubbles, and burst as needed */}
    </View>
  );
};

const fromJson = (json: any): GunInfo => {
  return {
    range: json.range,
    sigma: json.sigma,
    guns: json.guns.map((x: any) => fromWeaponInfoJson(x)),
    far: json.far ? json.far.map((x: any) => fromAuraInfoJson(x)) : null,
    bubbles: json.bubbles ? fromAirBubblesJson(json.bubbles) : null,
    burst: json.burst ? fromBurstJson(json.burst) : null,
  };
};

const fromWeaponInfoJson = (json: any): WeaponInfo => {
  // Implement the conversion logic for WeaponInfo
  return {} as WeaponInfo; // Replace with actual implementation
};

const fromAuraInfoJson = (json: any): AuraInfo => {
  // Implement the conversion logic for AuraInfo
  return {} as AuraInfo; // Replace with actual implementation
};

const fromAirBubblesJson = (json: any): AirBubbles => {
  // Implement the conversion logic for AirBubbles
  return {} as AirBubbles; // Replace with actual implementation
};

const fromBurstJson = (json: any): Burst => {
  return {
    burstReloadTime: json.burstReloadTime,
    fullReloadTime: json.fullReloadTime,
    modifiers: json.modifiers,
    shotIntensity: json.shotIntensity,
    shotsCount: json.shotsCount,
  };
};

export default GunInfoComponent;


interface Modifiers {
  // Define the properties of Modifiers based on your requirements
}

interface Burst {
  burstReloadTime: number;
  fullReloadTime: number;
  modifiers?: Modifiers | null;
  shotIntensity: number;
  shotsCount: number;
}

const Burst = (json: any): Burst => ({
  burstReloadTime: json.burstReloadTime,
  fullReloadTime: json.fullReloadTime,
  modifiers: Object.keys(json.modifiers).length > 0 ? Modifiers.fromJson(json.modifiers) : null,
  shotIntensity: json.shotIntensity,
  shotsCount: json.shotsCount,
});

interface TorpedoInfo {
  singleShot: boolean;
  launchers: any; // Define the type of launchers based on your requirements
}

const TorpedoInfo = (singleShot: boolean, launchers: any): TorpedoInfo => ({
  singleShot,
  launchers,
});

// Example usage
const App = () => {
  const burstData = {
    burstReloadTime: 2.5,
    fullReloadTime: 10.0,
    modifiers: {},
    shotIntensity: 1.0,
    shotsCount: 5,
  };

  const burst = Burst(burstData);

  return (
    <View>
      <Text>Burst Reload Time: {burst.burstReloadTime}</Text>
      <Text>Full Reload Time: {burst.fullReloadTime}</Text>
      <Text>Shot Intensity: {burst.shotIntensity}</Text>
      <Text>Shots Count: {burst.shotsCount}</Text>
    </View>
  );
};

export default App;


interface WeaponInfo {
  // Define the properties of WeaponInfo based on your requirements
}

interface TorpedoInfo {
  singleShot: boolean;
  launchers: WeaponInfo[];
}

const TorpedoInfoComponent: React.FC<{ data: TorpedoInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Single Shot: {data.singleShot ? 'Yes' : 'No'}</Text>
      <Text>Launchers:</Text>
      {data.launchers.map((launcher, index) => (
        <Text key={index}>{JSON.stringify(launcher)}</Text>
      ))}
    </View>
  );
};

const DepthChargeInfo = {
  reload: 0, // Replace with actual type
  ammo: 0, // Replace with actual type
  bombs: 0, // Replace with actual type
  groups: 0, // Replace with actual type
};

const DepthChargeInfoComponent: React.FC<{ data: typeof DepthChargeInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Reload: {data.reload}</Text>
      <Text>Ammo: {data.ammo}</Text>
      <Text>Bombs: {data.bombs}</Text>
      <Text>Groups: {data.groups}</Text>
    </View>
  );
};

const App: React.FC = () => {
  const torpedoData: TorpedoInfo = {
    singleShot: true,
    launchers: [], // Populate with actual WeaponInfo data
  };

  return (
    <View>
      <TorpedoInfoComponent data={torpedoData} />
      <DepthChargeInfoComponent data={DepthChargeInfo} />
    </View>
  );
};

export default App;


interface DepthChargeInfo {
  reload: number;
  ammo: string;
  bombs: number;
  groups: number;
}

const DepthChargeInfoComponent: React.FC<{ data: DepthChargeInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Reload: {data.reload}</Text>
      <Text>Ammo: {data.ammo}</Text>
      <Text>Bombs: {data.bombs}</Text>
      <Text>Groups: {data.groups}</Text>
    </View>
  );
};

const fromJson = (json: any): DepthChargeInfo => {
  return {
    reload: json.reload,
    ammo: json.ammo,
    bombs: json.bombs,
    groups: json.groups,
  };
};

interface AirSupportInfo {
  name: string;
  chargesNum: number;
  plane: string;
  reload: number;
  range: number;
}

const AirSupportInfoComponent: React.FC<{ data: AirSupportInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Name: {data.name}</Text>
      <Text>Charges Number: {data.chargesNum}</Text>
      <Text>Plane: {data.plane}</Text>
      <Text>Reload: {data.reload}</Text>
      <Text>Range: {data.range}</Text>
    </View>
  );
};

const airSupportFromJson = (json: any): AirSupportInfo => {
  return {
    name: json.name,
    chargesNum: json.chargesNum,
    plane: json.plane,
    reload: json.reload,
    range: json.range,
  };
};

export { DepthChargeInfoComponent, fromJson, AirSupportInfoComponent, airSupportFromJson };


interface AirSupportInfo {
  name: string;
  chargesNum: number;
  plane: string;
  reload: number;
  range: number;
}

const AirSupportInfoComponent: React.FC<{ info: AirSupportInfo }> = ({ info }) => {
  return (
    <View>
      <Text>Name: {info.name}</Text>
      <Text>Charges Number: {info.chargesNum}</Text>
      <Text>Plane: {info.plane}</Text>
      <Text>Reload: {info.reload}</Text>
      <Text>Range: {info.range}</Text>
    </View>
  );
};

const fromJson = (json: any): AirSupportInfo => {
  return {
    name: json.name,
    chargesNum: json.chargesNum,
    plane: json.plane,
    reload: json.reload,
    range: json.range,
  };
};

interface FireControlInfo {
  maxDistCoef: number;
  sigmaCountCoef: number;
}

const FireControlInfoComponent: React.FC<{ info: FireControlInfo }> = ({ info }) => {
  return (
    <View>
      <Text>Max Distance Coefficient: {info.maxDistCoef}</Text>
      <Text>Sigma Count Coefficient: {info.sigmaCountCoef}</Text>
    </View>
  );
};

export { AirSupportInfoComponent, fromJson, FireControlInfoComponent };


interface FireControlInfoProps {
  maxDistCoef: number;
  sigmaCountCoef: number;
}

const FireControlInfo: React.FC<FireControlInfoProps> = ({ maxDistCoef, sigmaCountCoef }) => {
  return (
    <View>
      <Text>Max Distance Coefficient: {maxDistCoef}</Text>
      <Text>Sigma Count Coefficient: {sigmaCountCoef}</Text>
    </View>
  );
};

const fromJson = (json: any): FireControlInfoProps => {
  return {
    maxDistCoef: json.maxDistCoef,
    sigmaCountCoef: json.sigmaCountCoef,
  };
};

interface SpecialsInfoProps {
  rageMode: boolean;
}

const SpecialsInfo: React.FC<SpecialsInfoProps> = ({ rageMode }) => {
  return (
    <View>
      <Text>Rage Mode: {rageMode ? 'Enabled' : 'Disabled'}</Text>
    </View>
  );
};

export { FireControlInfo, fromJson, SpecialsInfo };


interface RageModeProps {
  boostDuration: number;
  decrementCount: number;
  decrementDelay: number;
  decrementPeriod: number;
  gunsForSalvo: number;
  modifiers?: any; // Adjust type as necessary
  radius: number;
  rageModeName: string;
  requiredHits: number;
}

class RageMode {
  boostDuration: number;
  decrementCount: number;
  decrementDelay: number;
  decrementPeriod: number;
  gunsForSalvo: number;
  modifiers?: any; // Adjust type as necessary
  radius: number;
  rageModeName: string;
  requiredHits: number;

  constructor(data: RageModeProps) {
    this.boostDuration = data.boostDuration;
    this.decrementCount = data.decrementCount;
    this.decrementDelay = data.decrementDelay;
    this.decrementPeriod = data.decrementPeriod;
    this.gunsForSalvo = data.gunsForSalvo;
    this.modifiers = data.modifiers;
    this.radius = data.radius;
    this.rageModeName = data.rageModeName;
    this.requiredHits = data.requiredHits;
  }

  static fromJson(json: any): RageMode {
    return new RageMode({
      boostDuration: json.boostDuration,
      decrementCount: json.decrementCount,
      decrementDelay: json.decrementDelay,
      decrementPeriod: json.decrementPeriod,
      gunsForSalvo: json.gunsForSalvo,
      modifiers: json.modifiers,
      radius: json.radius,
      rageModeName: json.rageModeName,
      requiredHits: json.requiredHits,
    });
  }
}

interface SpecialsInfoProps {
  rageMode: RageMode;
}

class SpecialsInfo {
  rageMode: RageMode;

  constructor(data: SpecialsInfoProps) {
    this.rageMode = data.rageMode;
  }

  static fromJson(json: any): SpecialsInfo {
    return new SpecialsInfo({
      rageMode: RageMode.fromJson(json.rageMode),
    });
  }
}

const App: React.FC = () => {
  // Example usage
  const jsonData = {
    rageMode: {
      boostDuration: 10,
      decrementCount: 5,
      decrementDelay: 2,
      decrementPeriod: 1,
      gunsForSalvo: 3,
      modifiers: null,
      radius: 15,
      rageModeName: 'Fury',
      requiredHits: 20,
    },
  };

  const specialsInfo = SpecialsInfo.fromJson(jsonData);

  return (
    <View>
      <Text>Rage Mode Name: {specialsInfo.rageMode.rageModeName}</Text>
      <Text>Boost Duration: {specialsInfo.rageMode.boostDuration}</Text>
    </View>
  );
};

export default App;


interface Modifiers {
  // Define the properties of Modifiers based on your requirements
}

interface RageMode {
  boostDuration: number;
  decrementCount: number;
  decrementDelay: number;
  decrementPeriod: number;
  gunsForSalvo: number;
  modifiers?: Modifiers;
  radius: number;
  rageModeName: string;
  requiredHits: number;
}

const RageModeComponent: React.FC<{ rageMode: RageMode }> = ({ rageMode }) => {
  return (
    <View>
      <Text>Boost Duration: {rageMode.boostDuration}</Text>
      <Text>Decrement Count: {rageMode.decrementCount}</Text>
      <Text>Decrement Delay: {rageMode.decrementDelay}</Text>
      <Text>Decrement Period: {rageMode.decrementPeriod}</Text>
      <Text>Guns for Salvo: {rageMode.gunsForSalvo}</Text>
      <Text>Radius: {rageMode.radius}</Text>
      <Text>Rage Mode Name: {rageMode.rageModeName}</Text>
      <Text>Required Hits: {rageMode.requiredHits}</Text>
      {rageMode.modifiers && <Text>Modifiers: {/* Render modifiers here */}</Text>}
    </View>
  );
};

const fromJson = (json: any): RageMode => {
  return {
    boostDuration: json.boostDuration,
    decrementCount: json.decrementCount,
    decrementDelay: json.decrementDelay,
    decrementPeriod: json.decrementPeriod,
    gunsForSalvo: json.gunsForSalvo,
    modifiers: json.modifiers ? json.modifiers : undefined,
    radius: json.radius,
    rageModeName: json.rageModeName,
    requiredHits: json.requiredHits,
  };
};

export { RageModeComponent, fromJson };

interface EngineInfoProps {
  speedCoef: number;
}

class EngineInfo {
  speedCoef: number;

  constructor(speedCoef: number) {
    this.speedCoef = speedCoef;
  }

  static fromJson(json: Record<string, any> | null): EngineInfo {
    // when it is 1, it is not specified
    if (json === null) return new EngineInfo(1.0);
    return new EngineInfo(json.speedCoef);
  }
}


interface PingerInfoProps {
  reload: () => void;
  range: number;
  lifeTime1: number;
  lifeTime2: number;
  speed: number;
}

const PingerInfo: React.FC<PingerInfoProps> = ({ reload, range, lifeTime1, lifeTime2, speed }) => {
  return (
    <View>
      <Text>Range: {range}</Text>
      <Text>Life Time 1: {lifeTime1}</Text>
      <Text>Life Time 2: {lifeTime2}</Text>
      <Text>Speed: {speed}</Text>
      <Text onPress={reload}>Reload</Text>
    </View>
  );
};

export default PingerInfo;


interface PingerInfoProps {
  reload: number;
  range: number;
  lifeTime1: number;
  lifeTime2: number;
  speed: number;
}

class PingerInfo {
  reload: number;
  range: number;
  lifeTime1: number;
  lifeTime2: number;
  speed: number;

  constructor({ reload, range, lifeTime1, lifeTime2, speed }: PingerInfoProps) {
    this.reload = reload;
    this.range = range;
    this.lifeTime1 = lifeTime1;
    this.lifeTime2 = lifeTime2;
    this.speed = speed;
  }

  get rangeInKm(): number {
    return this.range / 1000;
  }

  static fromJson(json: any): PingerInfo {
    return new PingerInfo({
      reload: json.reload,
      range: json.range,
      lifeTime1: json.lifeTime1,
      lifeTime2: json.lifeTime2,
      speed: json.speed,
    });
  }
}

interface HullInfoProps {
  health: number;
  protection?: number;
  visibility: number;
  mobility: number;
  submarineBattery?: number;
}

class HullInfo {
  health: number;
  protection?: number;
  visibility: number;
  mobility: number;
  submarineBattery?: number;

  constructor({ health, protection, visibility, mobility, submarineBattery }: HullInfoProps) {
    this.health = health;
    this.protection = protection;
    this.visibility = visibility;
    this.mobility = mobility;
    this.submarineBattery = submarineBattery;
  }
}

const App: React.FC = () => {
  const pinger = PingerInfo.fromJson({
    reload: 5,
    range: 10000,
    lifeTime1: 60,
    lifeTime2: 120,
    speed: 300,
  });

  const hull = new HullInfo({
    health: 100,
    protection: 50,
    visibility: 30,
    mobility: 70,
    submarineBattery: 40,
  });

  return (
    <View>
      <Text>Pinger Reload: {pinger.reload}</Text>
      <Text>Pinger Range in Km: {pinger.rangeInKm}</Text>
      <Text>Hull Health: {hull.health}</Text>
      <Text>Hull Visibility: {hull.visibility}</Text>
    </View>
  );
};

export default App;


interface VisibilityInfo {
  // Define properties for VisibilityInfo
}

interface MobilityInfo {
  speed: number;
  turningRadius: number;
  rudderTime: number;
}

interface SubmarineBatteryInfo {
  // Define properties for SubmarineBatteryInfo
}

interface HullInfo {
  health: number;
  protection?: number;
  visibility: VisibilityInfo;
  mobility: MobilityInfo;
  submarineBattery?: SubmarineBatteryInfo;
}

const HullInfoComponent: React.FC<{ hullInfo: HullInfo }> = ({ hullInfo }) => {
  return (
    <View>
      <Text>Health: {hullInfo.health}</Text>
      <Text>Protection: {hullInfo.protection}</Text>
      {/* Render VisibilityInfo and MobilityInfo here */}
      <Text>Speed: {hullInfo.mobility.speed}</Text>
      <Text>Turning Radius: {hullInfo.mobility.turningRadius}</Text>
      <Text>Rudder Time: {hullInfo.mobility.rudderTime}</Text>
      {hullInfo.submarineBattery && (
        // Render SubmarineBatteryInfo here
        <Text>Submarine Battery Info</Text>
      )}
    </View>
  );
};

const fromJson = (json: any): HullInfo => {
  return {
    health: json.health,
    protection: json.protection,
    visibility: json.visibility, // Assuming a function to parse VisibilityInfo
    mobility: {
      speed: json.mobility.speed,
      turningRadius: json.mobility.turningRadius,
      rudderTime: json.mobility.rudderTime,
    },
    submarineBattery: json.submarineBattery ? json.submarineBattery : undefined,
  };
};

export { HullInfoComponent, fromJson };


interface MobilityInfoProps {
  speed: number;
  turningRadius: number;
  rudderTime: number;
}

const MobilityInfo: React.FC<MobilityInfoProps> = ({ speed, turningRadius, rudderTime }) => {
  return (
    <View>
      <Text>Speed: {speed}</Text>
      <Text>Turning Radius: {turningRadius}</Text>
      <Text>Rudder Time: {rudderTime}</Text>
    </View>
  );
};

interface VisibilityInfoProps {
  sea: number;
  plane: number;
  seaInSmoke: number;
  planeInSmoke: number;
  submarine: number;
  seaFireCoeff: number;
  planeFireCoeff: number;
}

const VisibilityInfo: React.FC<VisibilityInfoProps> = ({
  sea,
  plane,
  seaInSmoke,
  planeInSmoke,
  submarine,
  seaFireCoeff,
  planeFireCoeff,
}) => {
  return (
    <View>
      <Text>Sea: {sea}</Text>
      <Text>Plane: {plane}</Text>
      <Text>Sea in Smoke: {seaInSmoke}</Text>
      <Text>Plane in Smoke: {planeInSmoke}</Text>
      <Text>Submarine: {submarine}</Text>
      <Text>Sea Fire Coefficient: {seaFireCoeff}</Text>
      <Text>Plane Fire Coefficient: {planeFireCoeff}</Text>
    </View>
  );
};

export { MobilityInfo, VisibilityInfo };


interface VisibilityInfo {
  sea: number;
  plane: number;
  seaInSmoke: number;
  planeInSmoke: number;
  submarine: number;
  seaFireCoeff: number;
  planeFireCoeff: number;
}

const VisibilityInfoComponent: React.FC<{ data: VisibilityInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Sea: {data.sea}</Text>
      <Text>Plane: {data.plane}</Text>
      <Text>Sea in Smoke: {data.seaInSmoke}</Text>
      <Text>Plane in Smoke: {data.planeInSmoke}</Text>
      <Text>Submarine: {data.submarine}</Text>
      <Text>Sea Fire Coefficient: {data.seaFireCoeff}</Text>
      <Text>Plane Fire Coefficient: {data.planeFireCoeff}</Text>
    </View>
  );
};

const fromJson = (json: any): VisibilityInfo => ({
  sea: json.sea,
  plane: json.plane,
  seaInSmoke: json.seaInSmoke,
  planeInSmoke: json.planeInSmoke,
  submarine: json.submarine,
  seaFireCoeff: json.seaFireCoeff,
  planeFireCoeff: json.planeFireCoeff,
});

interface SubmarineBatteryInfo {
  capacity: number;
  regen: number;
}

const SubmarineBatteryInfoComponent: React.FC<{ data: SubmarineBatteryInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Capacity: {data.capacity}</Text>
      <Text>Regeneration: {data.regen}</Text>
    </View>
  );
};

export { VisibilityInfoComponent, fromJson, SubmarineBatteryInfoComponent };


interface SubmarineBatteryInfo {
  capacity: number;
  regen: number;
}

const SubmarineBatteryInfo = (json: any): SubmarineBatteryInfo => ({
  capacity: json.capacity,
  regen: json.regen,
});

interface WeaponInfo {
  reload: number;
  rotation: number;
  each: number;
  ammo: number;
  vertSector: number;
  count: number;
}

const WeaponInfo = (json: any): WeaponInfo => ({
  reload: json.reload,
  rotation: json.rotation,
  each: json.each,
  ammo: json.ammo,
  vertSector: json.vertSector,
  count: json.count,
});

const App = () => {
  const batteryInfoJson = { capacity: 100, regen: 10 };
  const weaponInfoJson = { reload: 5, rotation: 360, each: 1, ammo: 30, vertSector: 45, count: 2 };

  const batteryInfo = SubmarineBatteryInfo(batteryInfoJson);
  const weaponInfo = WeaponInfo(weaponInfoJson);

  return (
    <View>
      <Text>Submarine Battery Info:</Text>
      <Text>Capacity: {batteryInfo.capacity}</Text>
      <Text>Regen: {batteryInfo.regen}</Text>
      <Text>Weapon Info:</Text>
      <Text>Reload: {weaponInfo.reload}</Text>
      <Text>Rotation: {weaponInfo.rotation}</Text>
      <Text>Each: {weaponInfo.each}</Text>
      <Text>Ammo: {weaponInfo.ammo}</Text>
      <Text>Vertical Sector: {weaponInfo.vertSector}</Text>
      <Text>Count: {weaponInfo.count}</Text>
    </View>
  );
};

export default App;


interface WeaponInfo {
  reload: number;
  rotation: number;
  each: number;
  ammo: string[];
  vertSector: number;
  count: number;
}

const WeaponInfoComponent: React.FC<{ weapon: WeaponInfo }> = ({ weapon }) => {
  return (
    <View>
      <Text>Reload: {weapon.reload}</Text>
      <Text>Rotation: {weapon.rotation}</Text>
      <Text>Each: {weapon.each}</Text>
      <Text>Ammo: {weapon.ammo.join(', ')}</Text>
      <Text>Vertical Sector: {weapon.vertSector}</Text>
      <Text>Count: {weapon.count}</Text>
    </View>
  );
};

const fromJson = (json: any): WeaponInfo => {
  return {
    reload: json.reload,
    rotation: json.rotation,
    each: json.each,
    ammo: Array.isArray(json.ammo) ? json.ammo : [],
    vertSector: json.vertSector,
    count: json.count,
  };
};

interface Cost {
  costCr: number;
  costXp: number;
}

const CostComponent: React.FC<{ cost: Cost }> = ({ cost }) => {
  return (
    <View>
      <Text>Cost CR: {cost.costCr}</Text>
      <Text>Cost XP: {cost.costXp}</Text>
    </View>
  );
};

const fromCostJson = (json: any): Cost => {
  return {
    costCr: json.costCr,
    costXp: json.costXp,
  };
};

export { WeaponInfoComponent, fromJson, CostComponent, fromCostJson };

  costCr: number;
  costXp: number;
}

const fromJson = (json: any): Cost => ({
  costCr: json.costCR,
  costXp: json.costXP,
});

const toString = (cost: Cost): string => {
  return `Cost{costCr: ${cost.costCr}, costXp: ${cost.costXp}}`;
};

// Example usage
const jsonData = { costCR: 100, costXP: 200 };
const costInstance = fromJson(jsonData);
console.log(toString(costInstance));


interface AirDefenseProps {
  medium?: string;
  near?: string;
  far?: string;
}

const AirDefense: React.FC<AirDefenseProps> = ({ medium, near, far }) => {
  return (
    <View>
      {medium && <Text>Medium: {medium}</Text>}
      {near && <Text>Near: {near}</Text>}
      {far && <Text>Far: {far}</Text>}
    </View>
  );
};

export default AirDefense;


interface AuraInfo {
  // Define the properties of AuraInfo based on your requirements
}

interface AirDefense {
  medium?: AuraInfo[] | null;
  near?: AuraInfo[] | null;
  far?: AuraInfo[] | null;
}

const fromJson = (json: any): AirDefense => {
  return {
    medium: json.medium ? json.medium.map((x: any) => fromAuraInfoJson(x)) : null,
    near: json.near ? json.near.map((x: any) => fromAuraInfoJson(x)) : null,
    far: json.far ? json.far.map((x: any) => fromAuraInfoJson(x)) : null,
  };
};

const fromAuraInfoJson = (json: any): AuraInfo => {
  // Implement the conversion logic for AuraInfo
  return {} as AuraInfo; // Replace with actual implementation
};

interface AirBubbles {
  inner: number; // Adjust type as necessary
  outer: number; // Adjust type as necessary
  rof: number; // Adjust type as necessary
  minRange: number; // Adjust type as necessary
  maxRange: number; // Adjust type as necessary
  spawnTime: number; // Adjust type as necessary
  hitChance: number; // Adjust type as necessary
  damage: number; // Adjust type as necessary
}

const AirBubblesComponent: React.FC<AirBubbles> = ({
  inner,
  outer,
  rof,
  minRange,
  maxRange,
  spawnTime,
  hitChance,
  damage,
}) => {
  return (
    <View>
      <Text>Inner: {inner}</Text>
      <Text>Outer: {outer}</Text>
      <Text>Rate of Fire: {rof}</Text>
      <Text>Min Range: {minRange}</Text>
      <Text>Max Range: {maxRange}</Text>
      <Text>Spawn Time: {spawnTime}</Text>
      <Text>Hit Chance: {hitChance}</Text>
      <Text>Damage: {damage}</Text>
    </View>
  );
};

export { fromJson, AirBubblesComponent };


interface AirBubblesProps {
  inner: number;
  outer: number;
  rof: number;
  minRange: number;
  maxRange: number;
  spawnTime: number;
  hitChance: number;
  damage: number;
}

const AirBubbles: React.FC<AirBubblesProps> = ({
  inner,
  outer,
  rof,
  minRange,
  maxRange,
  spawnTime,
  hitChance,
  damage,
}) => {
  return (
    <View>
      <Text>Inner: {inner}</Text>
      <Text>Outer: {outer}</Text>
      <Text>Rate of Fire: {rof}</Text>
      <Text>Min Range: {minRange}</Text>
      <Text>Max Range: {maxRange}</Text>
      <Text>Spawn Time: {spawnTime}</Text>
      <Text>Hit Chance: {hitChance}</Text>
      <Text>Damage: {damage}</Text>
    </View>
  );
};

interface AuraInfoProps {
  minRange: number;
  maxRange: number;
  hitChance: number;
  damage: number;
  rof: number;
  dps: number;
  guns: number;
}

const AuraInfo: React.FC<AuraInfoProps> = ({
  minRange,
  maxRange,
  hitChance,
  damage,
  rof,
  dps,
  guns,
}) => {
  return (
    <View>
      <Text>Min Range: {minRange}</Text>
      <Text>Max Range: {maxRange}</Text>
      <Text>Hit Chance: {hitChance}</Text>
      <Text>Damage: {damage}</Text>
      <Text>Rate of Fire: {rof}</Text>
      <Text>DPS: {dps}</Text>
      <Text>Guns: {guns}</Text>
    </View>
  );
};

export { AirBubbles, AuraInfo };


interface AuraGuns {
  ammo: number;
  name: string;
  each: number;
  count: number;
  reload: number;
}

interface AuraInfo {
  minRange: number;
  maxRange: number;
  hitChance: number;
  damage: number;
  rof: number;
  dps: number;
  guns: AuraGuns[];
}

const AuraInfoComponent: React.FC<{ data: AuraInfo }> = ({ data }) => {
  return (
    <View>
      <Text>Min Range: {data.minRange}</Text>
      <Text>Max Range: {data.maxRange}</Text>
      <Text>Hit Chance: {data.hitChance}</Text>
      <Text>Damage: {data.damage}</Text>
      <Text>Rate of Fire: {data.rof}</Text>
      <Text>DPS: {data.dps}</Text>
      {data.guns.map((gun, index) => (
        <View key={index}>
          <Text>Gun Name: {gun.name}</Text>
          <Text>Ammo: {gun.ammo}</Text>
          <Text>Each: {gun.each}</Text>
          <Text>Count: {gun.count}</Text>
          <Text>Reload: {gun.reload}</Text>
        </View>
      ))}
    </View>
  );
};

const fromJson = (json: any): AuraInfo => {
  return {
    minRange: json.minRange,
    maxRange: json.maxRange,
    hitChance: json.hitChance,
    damage: json.damage,
    rof: json.rof,
    dps: json.dps,
    guns: json.guns.map((x: any) => ({
      ammo: x.ammo,
      name: x.name,
      each: x.each,
      count: x.count,
      reload: x.reload,
    })),
  };
};

export { AuraInfoComponent, fromJson };

interface AuraGuns {
  ammo: string;
  name: string;
  each: number;
  count: number;
  reload: number;
}

const fromJson = (json: any): AuraGuns => ({
  ammo: json.ammo,
  name: json.name,
  each: json.each,
  count: json.count,
  reload: json.reload,
});