
interface AbilityProps {
  nation: string;
  id: string;
  name: string;
  icon: string;
  description: string;
  filter: string;
  type: string;
  abilities: AbilityProps[];
  abilityList: AbilityProps[];
  alter?: any; // Define the type of alter if known
}

const Ability: React.FC<AbilityProps> = ({
  nation,
  id,
  name,
  icon,
  description,
  filter,
  type,
  abilities,
  abilityList,
  alter,
}) => {
  return (
    <View>
      <Text>{name}</Text>
      <Image source={{ uri: icon }} style={{ width: 50, height: 50 }} />
      <Text>{description}</Text>
      <Text>{`Nation: ${nation}`}</Text>
      <Text>{`ID: ${id}`}</Text>
      <Text>{`Filter: ${filter}`}</Text>
      <Text>{`Type: ${type}`}</Text>
      {/* Render abilities and abilityList if needed */}
      {abilities.map((ability, index) => (
        <Ability key={index} {...ability} />
      ))}
      {abilityList.map((ability, index) => (
        <Ability key={index} {...ability} />
      ))}
    </View>
  );
};

export default Ability;

interface Modifiers {
  // Define the structure of Modifiers based on your requirements
}

interface AbilityAlter {
  // Define the structure of AbilityAlter based on your requirements
}

interface AbilityInfo {
  // Define the structure of AbilityInfo based on your requirements
}

class Character {
  nation: string;
  id: number;
  name: string;
  icon: string;
  description: string;
  filter: string;
  type: string;
  abilities: Record<string, Modifiers>;
  abilityList: Modifiers[];
  alter?: Record<string, AbilityAlter>;

  constructor(
    nation: string,
    id: number,
    name: string,
    icon: string,
    description: string,
    filter: string,
    type: string,
    abilities: Record<string, Modifiers>,
    abilityList: Modifiers[],
    alter?: Record<string, AbilityAlter>
  ) {
    this.nation = nation;
    this.id = id;
    this.name = name;
    this.icon = icon;
    this.description = description;
    this.filter = filter;
    this.type = type;
    this.abilities = abilities;
    this.abilityList = abilityList;
    this.alter = alter;
  }

  descriptionOf(index: number): string {
    const ability = this.abilityList[index];
    return ability.toString();
  }
}


interface AbilityAlter {
  name: string;
  description: string;
}

interface Modifiers {
  // Define properties of Modifiers based on your requirements
}

interface Ability {
  nation: string;
  id: string;
  name: string;
  icon: string;
  description: string;
  filter: string;
  type: string;
  abilities: Record<string, Modifiers>;
  abilityList: Modifiers[];
  alter: Record<string, AbilityAlter> | null;
}

const fromJson = (json: any): Ability => {
  return {
    nation: json.nation,
    id: json.id,
    name: json.name,
    icon: json.icon,
    description: json.description,
    filter: json.filter,
    type: json.type,
    abilities: Object.fromEntries(
      Object.entries(json.abilities).map(([key, value]) => [
        key,
        Modifiers.fromJson(value), // Assuming Modifiers has a fromJson method
      ])
    ),
    abilityList: Object.values(json.abilities).map((e: any) => Modifiers.fromJson(e)),
    alter: json.alter
      ? Object.fromEntries(
          Object.entries(json.alter).map(([key, value]) => [
            key,
            {
              name: value.name,
              description: value.description,
            } as AbilityAlter,
          ])
        )
      : null,
  };
};


interface AbilityAlter {
  name: string;
  description: string;
}

const AbilityAlter = (json: any): AbilityAlter => ({
  name: json.name,
  description: json.description,
});

interface AbilityInfo {
  numConsumables?: number;
  preparationTime?: number;
  reloadTime?: number;
  workTime?: number;
  regenerationHPSpeed?: number;
  regenerationHPSpeedUnits?: string;
  areaDamageMultiplier?: number;
  bubbleDamageMultiplier?: number;
  climbAngle?: number;
  distanceToKill?: number;
  dogFightTime?: number;
  fightersName?: string;
  fightersNum?: number;
  flyAwayTime?: number;
  radius?: number;
  timeDelayAttack?: number;
  timeFromHeaven?: number;
  timeToTryingCatch?: number;
  timeWaitDelayAttack?: number;
  artilleryDistCoeff?: number;
  activationDelay?: number;
  height?: number;
  lifeTime?: number;
  spawnBackwardShift?: number;
  speedLimit?: number;
  startDelayTime?: number;
  descIDs?: string[];
  iconIDs?: string[];
  titleIDs?: string[];
  backwardEngineForsag?: number;
  backwardEngineForsagMaxSpeed?: number;
  boostCoeff?: number;
  forwardEngineForsag?: number;
  forwardEngineForsagMaxSpeed?: number;
  distShip?: number;
  distTorpedo?: number;
  targetBuoyancyCoefficients?: number[];
  torpedoReloadTime?: number;
  affectedClasses?: string[];
  regenerationRate?: number;
  ammo?: number;
  acousticWaveRadius?: number;
  updateFrequency?: number;
  zoneLifetime?: number;
  buoyancyRudderResetTimeCoeff?: number;
  buoyancyRudderTimeCoeff?: number;
  maxBuoyancySpeedCoeff?: number;
  underwaterMaxRudderAngleCoeff?: number;
  canUseOnEmpty?: boolean;
  logic?: string;
  criticalChance?: number;
  source?: string;
  target?: string;
  zoneRadius?: number;
  allyAuraBuff?: number;
  selfAuraBuff?: number;
  startDistance?: number;
  waveDistance?: number;
  waveParams?: any;
  absoluteBuff?: number;
  condition?: string;
  conditionalBuff?: number;
  targetBuff?: number;
  buoyancyState?: string;
  effectOnEndLongivity?: number;
  reloadBoostCoeff?: number;
  weaponTypes?: string[];
}

const defaultAbilityInfo: AbilityInfo = {
  numConsumables: 1,
  preparationTime: 1,
  reloadTime: 1,
  workTime: 1,
  regenerationHPSpeed: 1,
  regenerationHPSpeedUnits: 'HP/s',
  areaDamageMultiplier: 1,
  bubbleDamageMultiplier: 1,
  climbAngle: 1,
  distanceToKill: 1,
  dogFightTime: 1,
  fightersName: '',
  fightersNum: 1,
  flyAwayTime: 1,
  radius: 1,
  timeDelayAttack: 1,
  timeFromHeaven: 1,
  timeToTryingCatch: 1,
  timeWaitDelayAttack: 1,
  artilleryDistCoeff: 1,
  activationDelay: 1,
  height: 1,
  lifeTime: 1,
  spawnBackwardShift: 1,
  speedLimit: 1,
  startDelayTime: 1,
  descIDs: [],
  iconIDs: [],
  titleIDs: [],
  backwardEngineForsag: 1,
  backwardEngineForsagMaxSpeed: 1,
  boostCoeff: 1,
  forwardEngineForsag: 1,
  forwardEngineForsagMaxSpeed: 1,
  distShip: 1,
  distTorpedo: 1,
  targetBuoyancyCoefficients: [],
  torpedoReloadTime: 1,
  affectedClasses: [],
  regenerationRate: 1,
  ammo: 1,
  acousticWaveRadius: 1,
  updateFrequency: 1,
  zoneLifetime: 1,
  buoyancyRudderResetTimeCoeff: 1,
  buoyancyRudderTimeCoeff: 1,
  maxBuoyancySpeedCoeff: 1,
  underwaterMaxRudderAngleCoeff: 1,
  canUseOnEmpty: false,
  logic: '',
  criticalChance: 1,
  source: '',
  target: '',
  zoneRadius: 1,
  allyAuraBuff: 1,
  selfAuraBuff: 1,
  startDistance: 1,
  waveDistance: 1,
  waveParams: {},
  absoluteBuff: 1,
  condition: '',
  conditionalBuff: 1,
  targetBuff: 1,
  buoyancyState: '',
  effectOnEndLongivity: 1,
  reloadBoostCoeff: 1,
  weaponTypes: [],
};

const App = () => {
  return (
    <View>
      <Text>Ability Info</Text>
      {/* Render AbilityInfo and AbilityAlter as needed */}
    </View>
  );
};

export default App;


interface FighterProps {
  numConsumables?: number;
  preparationTime?: number;
  reloadTime?: number;
  workTime?: number;
  regenerationHPSpeed?: number;
  regenerationHPSpeedUnits?: number;
  areaDamageMultiplier?: number;
  bubbleDamageMultiplier?: number;
  climbAngle?: number;
  distanceToKill?: number;
  dogFightTime?: number;
  fightersName?: string;
  fightersNum?: number;
  flyAwayTime?: number;
  radius?: number;
  timeDelayAttack?: number;
  timeFromHeaven?: number;
  timeToTryingCatch?: number;
  timeWaitDelayAttack?: number;
  artilleryDistCoeff?: number;
  activationDelay?: number;
  height?: number;
  lifeTime?: number;
  spawnBackwardShift?: number;
  speedLimit?: number;
  startDelayTime?: number;
  descIDs?: string;
  iconIDs?: string;
  titleIDs?: string;
  backwardEngineForsag?: number;
  backwardEngineForsagMaxSpeed?: number;
  boostCoeff?: number;
  forwardEngineForsag?: number;
  forwardEngineForsagMaxSpeed?: number;
  distShip?: number;
  distTorpedo?: number;
  targetBuoyancyCoefficients?: { [key: string]: number };
  torpedoReloadTime?: number;
  affectedClasses?: string[];
  regenerationRate?: number;
  ammo?: string;
  acousticWaveRadius?: number;
  updateFrequency?: number;
  zoneLifetime?: number;
  buoyancyRudderResetTimeCoeff?: number;
  buoyancyRudderTimeCoeff?: number;
  maxBuoyancySpeedCoeff?: number;
  underwaterMaxRudderAngleCoeff?: number;
  canUseOnEmpty?: boolean;
  logic?: string;
  criticalChance?: number;
  source?: string[];
  target?: string[];
  zoneRadius?: number;
  allyAuraBuff?: string;
  selfAuraBuff?: string;
  startDistance?: number;
  waveDistance?: number;
  waveParams?: string;
  absoluteBuff?: string;
  condition?: string;
  conditionalBuff?: string;
  targetBuff?: string;
  buoyancyState?: number;
  effectOnEndLongivity?: number;
  reloadBoostCoeff?: number;
  weaponTypes?: string[];
}

const Fighter: React.FC<FighterProps> = (props) => {
  const {
    numConsumables,
    preparationTime,
    reloadTime,
    workTime,
    regenerationHPSpeed,
    regenerationHPSpeedUnits,
    areaDamageMultiplier,
    bubbleDamageMultiplier,
    climbAngle,
    distanceToKill,
    dogFightTime,
    fightersName,
    fightersNum,
    flyAwayTime,
    radius,
    timeDelayAttack,
    timeFromHeaven,
    timeToTryingCatch,
    timeWaitDelayAttack,
    artilleryDistCoeff,
    activationDelay,
    height,
    lifeTime,
    spawnBackwardShift,
    speedLimit,
    startDelayTime,
    descIDs,
    iconIDs,
    titleIDs,
    backwardEngineForsag,
    backwardEngineForsagMaxSpeed,
    boostCoeff,
    forwardEngineForsag,
    forwardEngineForsagMaxSpeed,
    distShip,
    distTorpedo,
    targetBuoyancyCoefficients,
    torpedoReloadTime,
    affectedClasses,
    regenerationRate,
    ammo,
    acousticWaveRadius,
    updateFrequency,
    zoneLifetime,
    buoyancyRudderResetTimeCoeff,
    buoyancyRudderTimeCoeff,
    maxBuoyancySpeedCoeff,
    underwaterMaxRudderAngleCoeff,
    canUseOnEmpty,
    logic,
    criticalChance,
    source,
    target,
    zoneRadius,
    allyAuraBuff,
    selfAuraBuff,
    startDistance,
    waveDistance,
    waveParams,
    absoluteBuff,
    condition,
    conditionalBuff,
    targetBuff,
    buoyancyState,
    effectOnEndLongivity,
    reloadBoostCoeff,
    weaponTypes,
  } = props;

  return (
    <View>
      <Text>{fightersName}</Text>
      {/* Additional rendering logic can be added here */}
    </View>
  );
};

export default Fighter;


function format(key: string, value: number): string {
    const description = Localisation.instance.stringOf(
        key.toUpperCase(),
        { prefix: 'IDS_PARAMS_MODIFIER_' }
    );
    if (description === ' ') return '';

    const valueString = value.toFixed(2); // Assuming toDecimalString() formats to 2 decimal places

    if (key.includes('time')) {
        return `${valueString}s`;
    }

    return valueString;
}

interface AbilityInfo {
    numConsumables: number;
    preparationTime: number;
    reloadTime: number;
    workTime: number;
    regenerationHPSpeed: number;
    regenerationHPSpeedUnits: string;
    areaDamageMultiplier: number;
    bubbleDamageMultiplier: number;
    climbAngle: number;
    distanceToKill: number;
    dogFightTime: number;
    fightersName: string;
    fightersNum: number;
    flyAwayTime: number;
    radius: number;
    timeDelayAttack: number;
    timeFromHeaven: number;
    timeToTryingCatch: number;
    timeWaitDelayAttack: number;
    artilleryDistCoeff: number;
    activationDelay: number;
    height: number;
    lifeTime: number;
    spawnBackwardShift: number;
    speedLimit: number;
    startDelayTime: number;
    descIDs: string[];
    iconIDs: string[];
    titleIDs: string[];
    backwardEngineForsag: number;
    backwardEngineForsagMaxSpeed: number;
    boostCoeff: number;
    forwardEngineForsag: number;
    forwardEngineForsagMaxSpeed: number;
    distShip: number;
    distTorpedo: number;
    targetBuoyancyCoefficients: { [key: string]: number } | null;
    torpedoReloadTime: number;
    affectedClasses: string[] | null;
    regenerationRate: number;
    ammo: number;
    acousticWaveRadius: number;
    updateFrequency: number;
    zoneLifetime: number;
    buoyancyRudderResetTimeCoeff: number;
    buoyancyRudderTimeCoeff: number;
    maxBuoyancySpeedCoeff: number;
    underwaterMaxRudderAngleCoeff: number;
    canUseOnEmpty: boolean;
    logic: string;
    criticalChance: number;
    source: string[] | null;
    target: string[] | null;
    zoneRadius: number;
    allyAuraBuff: number;
    selfAuraBuff: number;
    startDistance: number;
    waveDistance: number;
    waveParams: any;
    absoluteBuff: number;
    condition: string;
    conditionalBuff: number;
    targetBuff: number;
    buoyancyState: string;
    effectOnEndLongivity: number;
    reloadBoostCoeff: number;
    weaponTypes: string[] | null;
}

const AbilityInfoFromJson = (json: any): AbilityInfo => {
    return {
        numConsumables: json.numConsumables,
        preparationTime: json.preparationTime,
        reloadTime: json.reloadTime,
        workTime: json.workTime,
        regenerationHPSpeed: json.regenerationHPSpeed,
        regenerationHPSpeedUnits: json.regenerationHPSpeedUnits,
        areaDamageMultiplier: json.areaDamageMultiplier,
        bubbleDamageMultiplier: json.bubbleDamageMultiplier,
        climbAngle: json.climbAngle,
        distanceToKill: json.distanceToKill,
        dogFightTime: json.dogFightTime,
        fightersName: json.fightersName,
        fightersNum: json.fightersNum,
        flyAwayTime: json.flyAwayTime,
        radius: json.radius,
        timeDelayAttack: json.timeDelayAttack,
        timeFromHeaven: json.timeFromHeaven,
        timeToTryingCatch: json.timeToTryingCatch,
        timeWaitDelayAttack: json.timeWaitDelayAttack,
        artilleryDistCoeff: json.artilleryDistCoeff,
        activationDelay: json.activationDelay,
        height: json.height,
        lifeTime: json.lifeTime,
        spawnBackwardShift: json.spawnBackwardShift,
        speedLimit: json.speedLimit,
        startDelayTime: json.startDelayTime,
        descIDs: json.descIDs,
        iconIDs: json.iconIDs,
        titleIDs: json.titleIDs,
        backwardEngineForsag: json.backwardEngineForsag,
        backwardEngineForsagMaxSpeed: json.backwardEngineForsagMaxSpeed,
        boostCoeff: json.boostCoeff,
        forwardEngineForsag: json.forwardEngineForsag,
        forwardEngineForsagMaxSpeed: json.forwardEngineForsagMaxSpeed,
        distShip: json.distShip,
        distTorpedo: json.distTorpedo,
        targetBuoyancyCoefficients: json.targetBuoyancyCoefficients ? 
            Object.fromEntries(Object.entries(json.targetBuoyancyCoefficients).map(([k, v]) => [k, v as number])) : null,
        torpedoReloadTime: json.torpedoReloadTime,
        affectedClasses: json.affectedClasses ? Array.from(json.affectedClasses) : null,
        regenerationRate: json.regenerationRate,
        ammo: json.ammo,
        acousticWaveRadius: json.acousticWaveRadius,
        updateFrequency: json.updateFrequency,
        zoneLifetime: json.zoneLifetime,
        buoyancyRudderResetTimeCoeff: json.buoyancyRudderResetTimeCoeff,
        buoyancyRudderTimeCoeff: json.buoyancyRudderTimeCoeff,
        maxBuoyancySpeedCoeff: json.maxBuoyancySpeedCoeff,
        underwaterMaxRudderAngleCoeff: json.underwaterMaxRudderAngleCoeff,
        canUseOnEmpty: json.canUseOnEmpty,
        logic: json.logic,
        criticalChance: json.criticalChance,
        source: json.source ? Array.from(json.source) : null,
        target: json.target ? Array.from(json.target) : null,
        zoneRadius: json.zoneRadius,
        allyAuraBuff: json.allyAuraBuff,
        selfAuraBuff: json.selfAuraBuff,
        startDistance: json.startDistance,
        waveDistance: json.waveDistance,
        waveParams: json.waveParams,
        absoluteBuff: json.absoluteBuff,
        condition: json.condition,
        conditionalBuff: json.conditionalBuff,
        targetBuff: json.targetBuff,
        buoyancyState: json.buoyancyState,
        effectOnEndLongivity: json.effectOnEndLongivity,
        reloadBoostCoeff: json.reloadBoostCoeff,
        weaponTypes: json.weaponTypes ? Array.from(json.weaponTypes) : null,
    };
};