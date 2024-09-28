
const _blackList: string[] = [
  'affectedClasses',
];

const _coeffList: string[] = [
  'AAAuraDamage',
  'AABubbleDamage',
  'AAMaxHP',
  'GMIdealRadius',
  'GMMaxDist',
  'GMMaxHP',
  'GMRotationSpeed',
  'GMShotDelay',
  'GSIdealRadius',
  'GSMaxDist',
  'GSMaxHP',
  'GSShotDelay',
  'GTMaxHP',
  'GTRotationSpeed',
  'GTShotDelay',
  'afterBattleRepair',
  'torpedoBomberHealth',
  'skipBomberHealth',
  'planeHealth',
  'fighterHealth',
  'diveBomberHealth',
  'planeSpeed',
  'planeSpawnTime',
  'planeRegenerationRate',
  'shootShift',
  'gmShotDelay',
  'planeEmptyReturnSpeed',
  'collisionDamageApply',
  'collisionDamageNerf',
];

const _negativeCoeff: string[] = [
  'SGRepairTime',
];

const _coeffListZero: string[] = [
  'buoyancyRudderResetTimeCoeff',
  'damagedEngineCoeff',
  'lastChanceReloadCoefficient',
  'reloadBoostCoeff',
  'burnChanceFactorBig',
  'burnChanceFactorSmall',
  'rocketBurnChanceBonus',
  'regenerationRate',
  'boostCoeff',
  'artilleryBurnChanceBonus',
];

const _numberPercent: string[] = [
  'uwCoeffBonus',
];

const _rawPercent: string[] = [
  'regenerationHPSpeed',
];

const _additionalList: string[] = [
  'AAExtraBubbles',
  'AAInnerExtraBubbles',
  'additionalConsumables',
  'torpedoBomberAimingTime',
  'fighterAimingTime',
  'skipBomberAimingTime',
  'dcNumPacksBonus',
];

const _distList: string[] = [
  'radius',
];

const _rawDistList: string[] = [
  'acousticWaveRadius',
  'visionXRayTorpedoDist',
];

const _timeList: string[] = [
  'workTime',
  'torpedoReloadTime',
  'reloadTime',
  'preparationTime',
  'lifeTime',
];

interface ModifiersProps {
  raw: number;
  aaAuraDamage?: number;
  aaAuraReceiveDamageCoeff?: number;
  aaBubbleDamage?: number;
  aaExtraBubbles?: number;
  aaInnerExtraBubbles?: number;
  aaMaxHp?: number;
  consumableReloadTime?: number;
  consumablesWorkTime?: number;
  gmapDamageCoeff?: number;
  gmBigGunVisibilityCoeff?: number;
  gmCritProb?: number;
  gmhecsDamageCoeff?: number;
  gmHeavyCruiserCaliberDamageCoeff?: number;
  gmIdealRadius?: number;
  gmMaxDist?: number;
  gmMaxHp?: number;
  gmRepairTime?: number;
  gmRotationSpeed?: number;
  gmShotDelay?: number;
  gsIdealRadius?: number;
  gsMaxDist?: number;
  gsMaxHp?: number;
  gsShotDelay?: number;
  gtCritProb?: number;
  gtMaxHp?: number;
  gtRepairTime?: number;
  gtRotationSpeed?: number;
  gtShotDelay?: number;
  pmDetonationProb?: number;
  sgCritProb?: number;
  sgCritRudderTime?: number;
  sgRepairTime?: number;
  sgRudderTime?: number;
  absoluteBuff?: number;
  acousticWaveRadius?: number;
  activationDelay?: number;
  additionalConsumables?: number;
  affectedClasses?: string[];
  afterBattleRepair?: number;
  airDefenseDispReloadCoeff?: number;
  airDefenseDispWorkTimeCoeff?: number;
  allyAuraBuff?: number;
  ammo?: number;
  areaDamageMultiplier?: number;
  artilleryAlertEnabled?: boolean;
  artilleryAlertMinDistance?: number;
  artilleryBoostersReloadCoeff?: number;
  artilleryBurnChanceBonus?: number;
  artilleryDistCoeff?: number;
  asMaxHealthCoeff?: number;
  asReloadTimeCoeff?: number;
  backwardEngineForsag?: number;
  backwardEngineForsagMaxSpeed?: number;
  batteryCapacityCoeff?: number;
  batteryRegenCoeff?: number;
  bombAlphaDamageMultiplier?: number;
  bombApAlphaDamageMultiplier?: number;
  bombBurnChanceBonus?: number;
  boostCoeff?: number;
  bubbleDamageMultiplier?: number;
  buoyancyRudderResetTimeCoeff?: number;
  buoyancyRudderTimeCoeff?: number;
  buoyancyState?: number;
  burnChanceFactorBig?: number;
  burnChanceFactorHighLevel?: number;
  burnChanceFactorLowLevel?: number;
  burnChanceFactorSmall?: number;
  burnProb?: number;
  burnTime?: number;
  callFightersAdditionalConsumables?: number;
  callFightersAirOnly?: boolean;
  callFightersAppearDelay?: number;
  callFightersRadiusCoeff?: number;
  callFightersTimeDelayAttack?: number;
  callFightersWorkTimeCoeff?: number;
  canUseOnEmpty?: boolean;
  climbAngle?: number;
  collisionDamageApply?: number;
  collisionDamageNerf?: number;
  condition?: number;
  conditionalBuff?: number;
  consumableType?: string;
  crashCrewAdditionalConsumables?: number;
  crashCrewReloadCoeff?: number;
  crashCrewWorkTimeCoeff?: number;
  creditsFactor?: number;
  crewExpFactor?: number;
  critProbCoefficient?: number;
  criticalChance?: number;
  damagedEngineCoeff?: number;
  dcAlphaDamageMultiplier?: number;
  dcNumPacksBonus?: number;
  dcSplashRadiusMultiplier?: number;
  descIDs?: string[];
  distShip?: number;
  distTorpedo?: number;
  distanceToKill?: number;
  diveBomberAccuracyIncRateCoeff?: number;
  diveBomberHealth?: number;
  diveBomberMaxSpeedMultiplier?: number;
  diveBomberMinSpeedMultiplier?: number;
  diveBomberSpeedMultiplier?: number;
  dogFightTime?: number;
  effectOnEndLongivity?: number;
  engineBackwardForsageMaxSpeed?: number;
  engineBackwardForsagePower?: number;
  engineBackwardUpTime?: number;
  engineCritProb?: number;
  engineForwardForsageMaxSpeed?: number;
  engineForwardForsagePower?: number;
  engineForwardUpTime?: number;
  engineRepairTime?: number;
  expFactor?: number;
  extraFighterCount?: number;
  fighterAccuracyIncRateCoeff?: number;
  fighterAimingTime?: number;
  fighterHealth?: number;
  fighterReloadCoeff?: number;
  fightersName?: string;
  fightersNum?: number;
  fireResistanceEnabled?: boolean;
  firstSectorTimeCoeff?: number;
  floodChanceFactor?: number;
  floodChanceFactorPlane?: number;
  floodProb?: number;
  floodTime?: number;
  flyAwayTime?: number;
  forwardEngineForsag?: number;
  forwardEngineForsagMaxSpeed?: number;
  freeExpFactor?: number;
  healForsageReloadCoeff?: number;
  healthPerLevel?: number;
  height?: number;
  hlCritTimeCoeff?: number;
  hydrophoneUpdateFrequencyCoeff?: number;
  hydrophoneWaveSpeedCoeff?: number;
  iconIDs?: string[];
  ignorePtzBonus?: boolean;
  lastChanceReloadCoefficient?: number;
  lifeTime?: number;
  logic?: number;
  maxBuoyancySpeedCoeff?: number;
  nearEnemyIntuitionEnabled?: boolean;
  nearRlsEnabled?: boolean;
  numConsumables?: number;
  penetrationCoeffHe?: number;
  pingerCritProb?: number;
  pingerRepairTime?: number;
  pingerWaveSpeedCoeff?: number;
  planeBubbleArmorCoeff?: number;
  planeConsumablesWorkTime?: number;
  planeEmptyReturnSpeed?: number;
  planeEscapeHeightCoeff?: number;
  planeExtraHangarSize?: number;
  planeForsageTimeCoeff?: number;
  planeHealth?: number;
  planeHealthPerLevel?: number;
  planeMaxSpeedMultiplier?: number;
  planeRegenerationRate?: number;
  planeSpawnTime?: number;
  planeSpeed?: number;
  planeTorpedoArmingTimeCoeff?: number;
  planeTorpedoSpeedMultiplier?: number;
  planeVisibilityFactor?: number;
  preparationTime?: number;
  prioritySectorCooldownMultiplier?: number;
  prioritySectorStrengthBonus?: number;
  priorityTargetEnabled?: boolean;
  radius?: number;
  regenCrewAdditionalConsumables?: number;
  regenCrewReloadCoeff?: number;
  regenCrewWorkTimeCoeff?: number;
  regenerateHealthAdditionalConsumables?: number;
  regenerateHealthWorkTimeCoeff?: number;
  regenerationHpSpeed?: number;
  regenerationHpSpeedUnits?: number;
  regenerationRate?: number;
  reloadBoostCoeff?: number;
  reloadTime?: number;
  restoreForsage?: number;
  rlsWorkTimeCoeff?: number;
  rocketApAlphaDamageMultiplier?: number;
  rocketBurnChanceBonus?: number;
  scoutAdditionalConsumables?: number;
  scoutReloadCoeff?: number;
  scoutWorkTimeCoeff?: number;
  secondSectorTimeCoeff?: number;
  selfAuraBuff?: number;
  shootShift?: number;
  skipBomberAccuracyIncRateCoeff?: number;
  skipBomberAimingTime?: number;
  skipBomberHealth?: number;
  skipBomberSpeedMultiplier?: number;
  smokeGeneratorLifeTime?: number;
  smokeGeneratorWorkTimeCoeff?: number;
  softCriticalEnabled?: boolean;
  sonarWorkTimeCoeff?: number;
  source?: string;
  spawnBackwardShift?: number;
  speedBoostersWorkTimeCoeff?: number;
  speedCoef?: number;
  speedLimit?: number;
  startDelayTime?: number;
  startDistance?: number;
  switchAmmoReloadCoef?: number;
  target?: string;
  targetBuff?: number;
  timeDelayAttack?: number;
  timeFromHeaven?: number;
  timeToTryingCatch?: number;
  timeWaitDelayAttack?: number;
  titleIDs?: string[];
  torpedoBomberAccuracyIncRateCoeff?: number;
  torpedoBomberAimingTime?: number;
  torpedoBomberHealth?: number;
  torpedoDamageCoeff?: number;
  torpedoDetectionCoefficient?: number;
  torpedoDetectionCoefficientByPlane?: number;
  torpedoFullPingDamageCoeff?: number;
  torpedoReloadTime?: number;
  torpedoReloaderReloadCoeff?: number;
  torpedoSpeedMultiplier?: number;
  torpedoVisibilityFactor?: number;
  underwaterMaxRudderAngleCoeff?: number;
  updateFrequency?: number;
  uwCoeffBonus?: number;
  visibilityDistCoeff?: number;
  visibilityFactor?: number;
  visibilityForSubmarineCoeff?: number;
  visionXRayTorpedoDist?: number;
  waveDistance?: number;
  waveParams?: number;
  weaponTypes?: string[];
  workTime?: number;
  zoneLifetime?: number;
  zoneRadius?: number;
}

const Modifiers: React.FC<ModifiersProps> = (props) => {
  return (
    <View>
      <Text>Modifiers Component</Text>
      {/* Render props or any other UI elements based on the props */}
    </View>
  );
};

export default Modifiers;


interface Props {
  raw: Record<string, any>;
  numConsumables?: number | null;
}

const ConsumableComponent: React.FC<Props> = ({ raw, numConsumables }) => {
  const isNotEmpty = Object.keys(raw).length > 0;

  const consumableCount = () => {
    if (numConsumables === null) return null;
    if (numConsumables === -1) return '∞';
    return numConsumables.toString();
  };

  return (
    <View>
      {isNotEmpty && (
        <Text>{consumableCount()}</Text>
      )}
    </View>
  );
};

export default ConsumableComponent;

type ModifiersRaw = { [key: string]: any };

class Modifiers {
  raw: ModifiersRaw;
  private _additionalList: string[];

  constructor(raw: ModifiersRaw) {
    this.raw = raw;
    this._additionalList = []; // Initialize with your specific keys
  }

  static fromJson(json: ModifiersRaw): Modifiers {
    return new Modifiers(json);
  }

  merge(other: Modifiers | null): Modifiers {
    if (other === null) {
      throw new Error('Modifiers.merge(null)');
    }

    const output: ModifiersRaw = { ...this.raw };
    for (const [key, value] of Object.entries(other.raw)) {
      if (output[key] != null) {
        // only add up if it is a number
        if (typeof value === 'number') {
          if (this._additionalList.includes(key)) {
            // It is rare to add up
            output[key] += value;
          } else {
            // mostly, it is multiplication
            output[key] *= value;
          }
        }
      } else {
        // add this value
        output[key] = value;
      }
    }
    return Modifiers.fromJson(output);
  }
}


class Modifiers {
  private raw: Record<string, any>;
  private _blackList: string[];
  private _timeList: string[];
  private _numberPercent: string[];
  private _additionalList: string[];
  private _coeffListZero: string[];
  private _coeffList: string[];
  private _rawDistList: string[];
  private _distList: string[];
  private _rawPercent: string[];

  constructor(raw: Record<string, any>) {
    this.raw = raw;
    this._blackList = []; // Initialize with actual blacklist
    this._timeList = []; // Initialize with actual time list
    this._numberPercent = []; // Initialize with actual number percent list
    this._additionalList = []; // Initialize with actual additional list
    this._coeffListZero = []; // Initialize with actual coeff list zero
    this._coeffList = []; // Initialize with actual coeff list
    this._rawDistList = []; // Initialize with actual raw distance list
    this._distList = []; // Initialize with actual distance list
    this._rawPercent = []; // Initialize with actual raw percent list
  }

  toString(): string {
    const logger = new Logger('Modifiers');
    let description = '';

    for (const [keyOriginal, value] of Object.entries(this.raw)) {
      if (this._blackList.includes(keyOriginal)) continue;

      logger.fine(`keyOriginal: ${keyOriginal}`);
      const key = keyOriginal.toLowerCase();

      if (value == null) continue;
      logger.fine(`${keyOriginal}: ${value}`);

      let valueMap: ModifierShipTypeHolder[];

      if (typeof value === 'object' && !Array.isArray(value)) {
        const types = ModifierShipType.fromJson(value);
        if (types.isEmpty()) continue;
        valueMap = types.generateList(key.toUpperCase());
      } else {
        valueMap = [
          new ModifierShipTypeHolder({
            key: key.toUpperCase(),
            value: value,
            type: null,
          }),
        ];
      }

      const stringSet = new Set<string>();
      valueMap.forEach((e) => {
        const valueKey = e.fullKey;
        const localizedString = Localisation.instance.stringOf(valueKey, {
          prefix: 'IDS_PARAMS_MODIFIER_',
        });
        if (localizedString) {
          stringSet.add(localizedString);
        }
      });

      const valueSet = new Set<number>();
      valueMap.forEach((e) => {
        if (e.value !== 0.0) {
          valueSet.add(e.value);
        }
      });

      valueMap.forEach((item) => {
        const valueKey = item.fullKey;
        let langString: string | null = null;

        const langKeys = Localisation.instance.findKeyWith(valueKey, {
          prefix: 'IDS_PARAMS_MODIFIER_',
        });

        if (langKeys.length === 0) return;
        if (langKeys.length === 1) {
          langString = Localisation.instance.get(langKeys[0]);
        } else if (langKeys.length === 2) {
          const longerKey = langKeys[0].length > langKeys[1].length ? langKeys[0] : langKeys[1];
          langString = Localisation.instance.get(longerKey);
        } else {
          langString = Localisation.instance.stringOf(valueKey, {
            prefix: 'IDS_PARAMS_MODIFIER_',
          });
        }

        if (langString == null) return;

        const value = item.value;
        const shipType = item.type;
        let valueString: string;

        if (value === 0) return;

        if (this._timeList.includes(keyOriginal)) {
          const time = value as number;
          valueString = `${langString}: ${time.toFixed(2)} ${Localisation.instance.second}\n`;
        } else if (this._numberPercent.includes(keyOriginal)) {
          const percent = value as number / 100;
          valueString = `${langString}: +${(percent * 100).toFixed(2)}%\n`;
        } else if (this._additionalList.includes(keyOriginal) || key.includes('additional') || key.includes('extra')) {
          const extra = value as number;
          const sign = extra >= 0 ? '+' : '-';
          const tempString = `${langString}: ${sign}${Math.abs(extra).toFixed(2)}`;
          valueString = key.includes('time') ? `${tempString} ${Localisation.instance.second}\n` : `${tempString}\n`;
        } else if (this._coeffListZero.includes(keyOriginal)) {
          const coeff = value as number;
          valueString = `${langString}: +${(coeff * 100).toFixed(2)}%\n`;
        } else if (this._coeffList.includes(keyOriginal) || key.includes('coef') || key.includes('factor') || key.includes('multiplier') || key.includes('time') || key.includes('prob')) {
          const coeff = value as number;
          const positive = coeff > 1.0;
          const percent = Math.abs(coeff - 1).toFixed(2);
          valueString = `${langString}: ${positive ? '+' : '-'}${percent}%\n`;
        } else if (this._rawDistList.includes(keyOriginal)) {
          const dist = value as number;
          valueString = `${langString}: ${(dist / 1000).toFixed(2)} ${Localisation.instance.kilometer}\n`;
        } else if (key.includes('dist') || this._distList.includes(keyOriginal)) {
          const dist = value as number;
          valueString = `${langString}: ${(dist / 33.35).toFixed(2)} ${Localisation.instance.kilometer}\n`;
        } else if (this._rawPercent.includes(keyOriginal)) {
          const percent = value as number;
          valueString = `${langString}: ${percent.toFixed(2)}%\n`;
        } else {
          logger.warning(`Unknown modifier: ${keyOriginal}`);
          valueString = value === -1 ? `${langString}: ∞\n` : `${langString}: ${value}\n`;
        }

        const sameForAll = stringSet.size === 1 && valueSet.size === 1;
        if (shipType == null || (sameForAll && !description.includes(valueString.trim()))) {
          description += valueString;
        } else if (sameForAll) {
          continue;
        } else {
          const shipTypeString = Localisation.instance.stringOf(shipType, {
            prefix: 'IDS_',
          });
          description += `${valueString.trim()} (${shipTypeString})\n`;
        }
      });
    }

    return description;
  }
}

function formatNumber(value: number): string {
    if (value === 1) return '+1';
    let adjustedValue = value;
    if (value < 0.35) {
        adjustedValue = value + 1;
    }
    const positive = adjustedValue > 1;
    const offset = Math.abs(adjustedValue - 1) * 100;
    return `${positive ? '+' : '-'}${offset.toFixed(2)}%`;
}


interface ModifierShipType {
  // Define the properties of ModifierShipType based on your requirements
}

interface ModifiersProps {
  raw: any;
  aaAuraDamage?: ModifierShipType;
  aaAuraReceiveDamageCoeff?: number;
  aaBubbleDamage?: ModifierShipType;
  aaExtraBubbles?: number;
  aaInnerExtraBubbles?: number;
  aaMaxHp?: number;
  consumableReloadTime?: ModifierShipType;
  consumablesWorkTime?: number;
  gmapDamageCoeff?: number;
  gmBigGunVisibilityCoeff?: number;
  gmCritProb?: number;
  gmhecsDamageCoeff?: number;
  gmHeavyCruiserCaliberDamageCoeff?: number;
  gmIdealRadius?: number;
  gmMaxDist?: number;
  gmMaxHp?: number;
  gmRepairTime?: number;
  gmRotationSpeed?: ModifierShipType;
  gmShotDelay?: number;
  gsIdealRadius?: number;
  gsMaxDist?: number;
  gsMaxHp?: number;
  gsShotDelay?: number;
  gtCritProb?: number;
  gtMaxHp?: number;
  gtRepairTime?: number;
  gtRotationSpeed?: number;
  gtShotDelay?: number;
  pmDetonationProb?: number;
  sgCritProb?: number;
  sgCritRudderTime?: number;
  sgRepairTime?: number;
  sgRudderTime?: number;
  absoluteBuff?: string;
  acousticWaveRadius?: number;
  activationDelay?: number;
  additionalConsumables?: number;
  affectedClasses?: string[];
  afterBattleRepair?: number;
  airDefenseDispReloadCoeff?: number;
  airDefenseDispWorkTimeCoeff?: number;
  allyAuraBuff?: string;
  ammo?: string;
  areaDamageMultiplier?: number;
  artilleryAlertEnabled?: boolean;
  artilleryAlertMinDistance?: number;
  artilleryBoostersReloadCoeff?: number;
  artilleryBurnChanceBonus?: ModifierShipType;
  artilleryDistCoeff?: number;
  asMaxHealthCoeff?: number;
  asReloadTimeCoeff?: number;
  backwardEngineForsag?: number;
  backwardEngineForsagMaxSpeed?: number;
  batteryCapacityCoeff?: number;
  batteryRegenCoeff?: number;
  bombAlphaDamageMultiplier?: number;
  bombApAlphaDamageMultiplier?: number;
  bombBurnChanceBonus?: number;
  boostCoeff?: number;
  bubbleDamageMultiplier?: number;
  buoyancyRudderResetTimeCoeff?: number;
  buoyancyRudderTimeCoeff?: number;
  buoyancyState?: number;
  burnChanceFactorBig?: number;
  burnChanceFactorHighLevel?: number;
  burnChanceFactorLowLevel?: number;
  burnChanceFactorSmall?: number;
  burnProb?: number;
  burnTime?: number;
  callFightersAdditionalConsumables?: number;
  callFightersAirOnly?: boolean;
  callFightersAppearDelay?: number;
  callFightersRadiusCoeff?: number;
  callFightersTimeDelayAttack?: number;
  callFightersWorkTimeCoeff?: number;
  canUseOnEmpty?: boolean;
  climbAngle?: number;
  collisionDamageApply?: number;
  collisionDamageNerf?: number;
  condition?: string;
  conditionalBuff?: string;
  consumableType?: string;
  crashCrewAdditionalConsumables?: number;
  crashCrewReloadCoeff?: number;
  crashCrewWorkTimeCoeff?: number;
  creditsFactor?: number;
  crewExpFactor?: number;
  critProbCoefficient?: number;
  criticalChance?: number;
  damagedEngineCoeff?: number;
  dcAlphaDamageMultiplier?: ModifierShipType;
  dcNumPacksBonus?: number;
  dcSplashRadiusMultiplier?: number;
  descIDs?: string;
  distShip?: number;
  distTorpedo?: number;
  distanceToKill?: number;
  diveBomberAccuracyIncRateCoeff?: number;
  diveBomberHealth?: number;
  diveBomberMaxSpeedMultiplier?: number;
  diveBomberMinSpeedMultiplier?: number;
  diveBomberSpeedMultiplier?: number;
  dogFightTime?: number;
  effectOnEndLongivity?: number;
  engineBackwardForsageMaxSpeed?: number;
  engineBackwardForsagePower?: number;
  engineBackwardUpTime?: number;
  engineCritProb?: number;
  engineForwardForsageMaxSpeed?: number;
  engineForwardForsagePower?: number;
  engineForwardUpTime?: number;
  engineRepairTime?: number;
  expFactor?: number;
  extraFighterCount?: number;
  fighterAccuracyIncRateCoeff?: number;
  fighterAimingTime?: number;
  fighterHealth?: number;
  fighterReloadCoeff?: number;
  fightersName?: string;
  fightersNum?: number;
  fireResistanceEnabled?: boolean;
  firstSectorTimeCoeff?: number;
  floodChanceFactor?: number;
  floodChanceFactorPlane?: number;
  floodProb?: number;
  floodTime?: number;
  flyAwayTime?: number;
  forwardEngineForsag?: number;
  forwardEngineForsagMaxSpeed?: number;
  freeExpFactor?: number;
  healForsageReloadCoeff?: number;
  healthPerLevel?: ModifierShipType;
  height?: number;
  hlCritTimeCoeff?: number;
  hydrophoneUpdateFrequencyCoeff?: number;
  hydrophoneWaveSpeedCoeff?: number;
  iconIDs?: string;
  ignorePtzBonus?: number;
  lastChanceReloadCoefficient?: number;
  lifeTime?: number;
  logic?: string;
  maxBuoyancySpeedCoeff?: number;
  nearEnemyIntuitionEnabled?: boolean;
  nearRlsEnabled?: boolean;
  numConsumables?: number;
  penetrationCoeffHe?: number;
  pingerCritProb?: number;
  pingerRepairTime?: number;
  pingerWaveSpeedCoeff?: number;
  planeBubbleArmorCoeff?: number;
  planeConsumablesWorkTime?: number;
  planeEmptyReturnSpeed?: number;
  planeEscapeHeightCoeff?: number;
  planeExtraHangarSize?: number;
  planeForsageTimeCoeff?: number;
  planeHealth?: number;
  planeHealthPerLevel?: number;
  planeMaxSpeedMultiplier?: number;
  planeRegenerationRate?: number;
  planeSpawnTime?: number;
  planeSpeed?: number;
  planeTorpedoArmingTimeCoeff?: number;
  planeTorpedoSpeedMultiplier?: number;
  planeVisibilityFactor?: number;
  preparationTime?: number;
  prioritySectorCooldownMultiplier?: number;
  prioritySectorStrengthBonus?: number;
  priorityTargetEnabled?: boolean;
  radius?: number;
  regenCrewAdditionalConsumables?: number;
  regenCrewReloadCoeff?: number;
  regenCrewWorkTimeCoeff?: number;
  regenerateHealthAdditionalConsumables?: number;
  regenerateHealthWorkTimeCoeff?: number;
  regenerationHpSpeed?: number;
  regenerationHpSpeedUnits?: number;
  regenerationRate?: number;
  reloadBoostCoeff?: number;
  reloadTime?: number;
  restoreForsage?: boolean;
  rlsWorkTimeCoeff?: number;
  rocketApAlphaDamageMultiplier?: number;
  rocketBurnChanceBonus?: number;
  scoutAdditionalConsumables?: number;
  scoutReloadCoeff?: number;
  scoutWorkTimeCoeff?: number;
  secondSectorTimeCoeff?: number;
  selfAuraBuff?: string;
  shootShift?: number;
  skipBomberAccuracyIncRateCoeff?: number;
  skipBomberAimingTime?: number;
  skipBomberHealth?: number;
  skipBomberSpeedMultiplier?: number;
  smokeGeneratorLifeTime?: number;
  smokeGeneratorWorkTimeCoeff?: number;
  softCriticalEnabled?: boolean;
  sonarWorkTimeCoeff?: number;
  source?: string[];
  spawnBackwardShift?: number;
  speedBoostersWorkTimeCoeff?: number;
  speedCoef?: number;
  speedLimit?: number;
  startDelayTime?: number;
  startDistance?: number;
  switchAmmoReloadCoef?: number;
  target?: string[];
  targetBuff?: string;
  timeDelayAttack?: number;
  timeFromHeaven?: number;
  timeToTryingCatch?: number;
  timeWaitDelayAttack?: number;
  titleIDs?: string;
  torpedoBomberAccuracyIncRateCoeff?: number;
  torpedoBomberAimingTime?: number;
  torpedoBomberHealth?: number;
  torpedoDamageCoeff?: number;
  torpedoDetectionCoefficient?: number;
  torpedoDetectionCoefficientByPlane?: number;
  torpedoFullPingDamageCoeff?: number;
  torpedoReloadTime?: number;
  torpedoReloaderReloadCoeff?: number;
  torpedoSpeedMultiplier?: number;
  torpedoVisibilityFactor?: number;
  underwaterMaxRudderAngleCoeff?: number;
  updateFrequency?: number;
  uwCoeffBonus?: number;
  visibilityDistCoeff?: ModifierShipType;
  visibilityFactor?: number;
  visibilityForSubmarineCoeff?: number;
  visionXRayTorpedoDist?: number;
  waveDistance?: number;
  waveParams?: string;
  weaponTypes?: string[];
  workTime?: number;
  zoneLifetime?: number;
  zoneRadius?: number;
}

const Modifiers: React.FC<ModifiersProps> = (props) => {
  return (
    <View>
      <Text>Modifiers Component</Text>
      {/* Render other properties as needed */}
    </View>
  );
};

export default Modifiers;


interface Modifier {
  key: string;
  type?: string;
  value: any;
  fullKey: string;
}

class ModifierImpl implements Modifier {
  key: string;
  type?: string;
  value: any;

  constructor(key: string, type?: string, value: any) {
    this.key = key;
    this.type = type;
    this.value = value;
  }

  get fullKey(): string {
    return this.type == null ? this.key : `${this.key}_${this.type}`;
  }
}

interface ModifierShipTypeProps {
  airCarrier?: any;
  auxiliary?: any;
  battleship?: any;
  cruiser?: any;
  destroyer?: any;
  submarine?: any;
}

const ModifierShipType: React.FC<ModifierShipTypeProps> = ({
  airCarrier,
  auxiliary,
  battleship,
  cruiser,
  destroyer,
  submarine,
}) => {
  return (
    <View>
      <Text>Air Carrier: {airCarrier}</Text>
      <Text>Auxiliary: {auxiliary}</Text>
      <Text>Battleship: {battleship}</Text>
      <Text>Cruiser: {cruiser}</Text>
      <Text>Destroyer: {destroyer}</Text>
      <Text>Submarine: {submarine}</Text>
    </View>
  );
};

export { ModifierImpl, ModifierShipType };

interface ModifierShipType {
  airCarrier?: number;
  auxiliary?: number;
  battleship?: number;
  cruiser?: number;
  destroyer?: number;
  submarine?: number;
}

const fromJson = (json: Record<string, any> | null): ModifierShipType => {
  if (json === null) {
    return {};
  }

  return {
    airCarrier: json['AirCarrier'],
    auxiliary: json['Auxiliary'],
    battleship: json['Battleship'],
    cruiser: json['Cruiser'],
    destroyer: json['Destroyer'],
    submarine: json['Submarine'],
  };
};

interface ModifierShipTypeHolder {
  key: string;
  type: string;
  value: any; // Replace 'any' with the appropriate type if known
}

const _validate = (value: any) => {
  // Implement your validation logic here
  return value; // Placeholder return
};

const generateList = (key: string): ModifierShipTypeHolder[] => {
  const airCarrier = {}; // Replace with actual value
  const auxiliary = {}; // Replace with actual value
  const battleship = {}; // Replace with actual value
  const cruiser = {}; // Replace with actual value
  const destroyer = {}; // Replace with actual value

  return [
    {
      key: key,
      type: 'AIRCARRIER',
      value: _validate(airCarrier),
    },
    {
      key: key,
      type: 'AUXILIARY',
      value: _validate(auxiliary),
    },
    {
      key: key,
      type: 'BATTLESHIP',
      value: _validate(battleship),
    },
    {
      key: key,
      type: 'CRUISER',
      value: _validate(cruiser),
    },
    {
      key: key,
      type: 'DESTROYER',
      value: _validate(destroyer),
    },
  ];
};

const validate = (value: number | null): number => {
  if (value === null) return 0;
  if (value === 1) return 0;
  return value;
};

class Game {
    airCarrier: any | null = null;
    auxiliary: any | null = null;
    battleship: any | null = null;
    cruiser: any | null = null;
    destroyer: any | null = null;
    submarine: any | null = null;

    isEmpty(): boolean {
        return this.airCarrier === null &&
            this.auxiliary === null &&
            this.battleship === null &&
            this.cruiser === null &&
            this.destroyer === null &&
            this.submarine === null;
    }
}


interface ModifierShipTypeProps {
  airCarrier: boolean;
  auxiliary: boolean;
  battleship: boolean;
  cruiser: boolean;
  destroyer: boolean;
  submarine: boolean;
}

const ModifierShipType: React.FC<ModifierShipTypeProps> = ({
  airCarrier,
  auxiliary,
  battleship,
  cruiser,
  destroyer,
  submarine,
}) => {
  const toString = () => {
    return `ModifierShipType{airCarrier: ${airCarrier}, auxiliary: ${auxiliary}, battleship: ${battleship}, cruiser: ${cruiser}, destroyer: ${destroyer}, submarine: ${submarine}}`;
  };

  return (
    <View>
      <Text>{toString()}</Text>
    </View>
  );
};

export default ModifierShipType;


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