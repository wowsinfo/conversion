
LogBox.ignoreLogs(['Warning: ...']); // Ignore specific warnings if needed

/// This repository manages localized strings from the Game Data
export class Localisation {
  static supportedLocales = AppLocalization.supportedLocales;

  /// The wrapper of [AppLocalization].
  static of(context: React.ContextType<typeof LocalizationContext>) {
    const i18n = React.useContext(context);
    if (!i18n) {
      throw new Error('AppLocalization is not working as expected.');
    }

    return i18n;
  }
}


export const decideLang = (customLang?: string): string => {
  const lang = customLang || I18nManager.isRTL ? 'ar' : 'en'; // Replace with actual locale fetching logic
  const logger = new Logger('Localisation|decideLang');
  logger.info(`System locale is ${lang}`);
  const langCode = lang.toLowerCase();

  if (validGameLanguages.includes(langCode)) {
    return langCode;
  }

  if (langCode.includes('_')) {
    const localeCode = langCode.split('_')[0];
    if (validGameLanguages.includes(localeCode)) {
      logger.info(`Using locale \`${localeCode}\``);
      return localeCode;
    }
  }

  logger.warning(`Unsupported locale ${langCode}, falling back to en`);
  return 'en';
};


class Localisation {
  private static instance: Localisation;
  private _initialised = false;
  private _logger = new Logger('Localisation');
  private _lang: Record<string, Record<string, string>> = {};
  private _gameLang: string = '';

  static validGameLanguages = [
    'cs',
    'de',
    'en',
    'es',
    'es_mx',
    'fr',
    'it',
    'ja',
    'ko',
    'nl',
    'pl',
    'pt',
    'pt_br',
    'ru',
    'th',
    'tr',
    'uk',
    'zh', // TODO: do we need this or simply use zh_sg instead?
    'zh_sg',
    'zh_tw',
  ];

  private constructor() {}

  public static getInstance(): Localisation {
    if (!Localisation.instance) {
      Localisation.instance = new Localisation();
    }
    return Localisation.instance;
  }

  public async initialise(): Promise<void> {
    if (this._initialised) {
      this._logger.severe('Localisation already initialised');
      throw new Error('Localisation is already initialised');
    }

    const timer = new TimeTracker();

    // Load the language file
    const langString = await readFile('data/live/app/lang/lang.json', 'utf8');
    timer.log('Loaded lang.json');

    const langObject = JSON.parse(langString);
    timer.log('Parsed lang.json');

    this._lang = Object.fromEntries(
      Object.entries(langObject).map(([key, value]) => [key, value as Record<string, string>])
    );

    this._gameLang = this.decideLang();
    timer.log('Decoded lang.json');

    this._initialised = true;
    this._logger.info('Localisation initialised');
  }

  private decideLang(): string {
    // Implement your language decision logic here
    return 'en'; // Default language
  }

  public get supportedGameLanguages(): string[] {
    return Object.keys(this._lang);
  }
}

class TimeTracker {
  private startTime: number;

  constructor() {
    this.startTime = Date.now();
  }

  public log(message: string): void {
    const elapsed = Date.now() - this.startTime;
    console.log(`${message} (Elapsed time: ${elapsed}ms)`);
  }
}

// Usage example
const useLocalisation = () => {
  const [localisation, setLocalisation] = useState<Localisation | null>(null);

  useEffect(() => {
    const initLocalisation = async () => {
      const localisationInstance = Localisation.getInstance();
      await localisationInstance.initialise();
      setLocalisation(localisationInstance);
    };

    initLocalisation();
  }, []);

  return localisation;
};

export { Localisation, useLocalisation };


const validGameLanguages = ['en', 'es', 'fr', 'de']; // Example valid languages
const _lang = {
  en: {},
  es: {},
  fr: {},
  de: {},
};

const useGameLanguage = () => {
  const [gameLang, setGameLang] = useState<string>('en');

  const updateDataLanguage = (language: string) => {
    if (!validGameLanguages.includes(language)) {
      LogBox.ignoreLogs([`Invalid language ${language}`]);
      return;
    }
    if (_lang[language] == null) {
      LogBox.ignoreLogs([`Language ${language} is not supported`]);
      return;
    }

    console.info(`Updating data language to ${language}`);
    setGameLang(language);
  };

  return {
    gameLang,
    updateDataLanguage,
  };
};

export default useGameLanguage;


const findKeyWith = (search: string, prefix?: string): string[] => {
  const keys: string[] = []; // This should be populated with your keys

  return keys.filter(key => {
    const matchesSearch = key.includes(search);
    const matchesPrefix = prefix ? key.startsWith(prefix) : true;
    return matchesSearch && matchesPrefix;
  });
};

const App: React.FC = () => {
  const searchKey = 'example';
  const prefixKey = 'pre';

  const foundKeys = findKeyWith(searchKey, { prefix: prefixKey });

  return (
    <View>
      {foundKeys.map((key, index) => (
        <Text key={index}>{key}</Text>
      ))}
    </View>
  );
};

export default App;

const searchKeys = (search: string, prefix: string | null, _lang: Record<string, Record<string, any>>, _gameLang: string): string[] => {
    let searchUpper = search.toUpperCase();
    if (prefix !== null) {
        searchUpper = prefix + searchUpper;
    }

    const result: string[] = [];
    const langKeys = _lang[_gameLang]?.keys;
    if (langKeys == null) return [];
    for (const key of langKeys) {
        if (key.includes(searchUpper)) result.push(key);
    }
    return result;
};


interface LanguageData {
  [key: string]: string;
}

interface Localization {
  [lang: string]: LanguageData;
}

const localization: Localization = {
  // Add your localization data here
};

let _initialised = false;
let _gameLang: string = 'en'; // Default language
const _logger = {
  severe: (message: string) => console.error(message),
  warning: (message: string) => console.warn(message),
};

const useLocalization = () => {
  const [lang, setLang] = useState<Localization>(localization);

  useEffect(() => {
    // Initialize localization here if needed
    _initialised = true;
  }, []);

  const get = (key: string): string | null => {
    if (!_initialised) {
      _logger.severe('Localisation not initialised');
      return null;
    }
    if (!lang[_gameLang]) {
      _logger.warning(`Language ${_gameLang} is not supported`);
      return null;
    }
    if (!lang[_gameLang][key]) {
      _logger.warning(`Key ${key} is not supported`);
      return null;
    }
    return lang[_gameLang][key];
  };

  return { get };
};

export default useLocalization;


const useLocalizedString = () => {
  const { t } = useTranslation();

  const stringOf = (key: string | null, constants?: Record<string, any>, prefix?: string) => {
    if (!key) return null;
    const fullKey = prefix ? `${prefix}.${key}` : key;
    return t(fullKey, constants);
  };

  return { stringOf };
};

export default useLocalizedString;


const _logger = new Logger();
const _lang: { [key: string]: { [key: string]: string } } = {}; // Define your language object
let _gameLang: string = 'en'; // Set your default language

const translate = (key: string | null, prefix: string | null, constants: { [key: string]: any } | null): string | null => {
  if (key == null) {
    _logger.severe('Key is null');
    return null;
  }

  if (_lang[_gameLang] == null) {
    _logger.severe(`Language ${_gameLang} not found`);
    return null;
  }

  let langKey = key.toUpperCase();
  if (prefix != null) {
    langKey = prefix + langKey;
  }
  const rawString = _lang[_gameLang][langKey];
  if (rawString == null || rawString.trim().isEmpty) {
    _logger.severe(`Language key ${langKey} not found or empty`);
    return null;
  }

  if (constants == null || Object.keys(constants).length === 0) {
    return rawString;
  }

  let formattedString = rawString;

  const regex = /%\((.*?)\)[sd]/g;
  const matches = [...rawString.matchAll(regex)];
  for (const match of matches) {
    const key = match[1];
    const originalString = match[0];
    if (key == null || originalString == null) {
      _logger.severe('Invalid match, key is null');
      continue;
    }
    const constantKey = key.replace(/_percent$/, '');
    let constantValue = constants[constantKey];
    _logger.fine(`Constant ${key} = ${constantValue}`);
    if (key.endsWith('_percent')) {
      const number = constantValue as number;
      constantValue = `${(number * 100).toFixed(2)}%`;
    }

    _logger.info(`Replacing ${originalString} with ${constantValue}`);
    formattedString = formattedString.replaceAll(originalString, constantValue.toString());

    if (formattedString.includes('%(')) {
      throw new Error('Still have %(key) in the string');
    }
  }

  return formattedString;
};


class ShipStrings {
  private static _shipFilter = 'IDS_CAROUSEL_APPLIED_FILTER_HINT_';
  private static _moduleType = 'IDS_MODULE_TYPE_';
  private static _shipParam = 'IDS_SHIP_PARAM_';
  private static _paramModifier = 'IDS_PARAMS_MODIFIER_';
  private static _airSupportParams = 'IDS_SHIP_PARAM_AIR_SUPPORT_';

  private static _get(key: string, prefix?: string): string {
    const string = this.stringOf(key, prefix);
    if (!string) {
      console.error(`Failed to get string for ${key}`);
      throw new Error(`Make sure ${key} is correct`);
    }
    return string;
  }

  private static stringOf(key: string, prefix?: string): string | null {
    // Implement your string retrieval logic here
    return null; // Placeholder for actual implementation
  }

  // Predefined strings
  public static battles = this._get('IDS_BATTLES');

  // Ship type names
  public static airCarrier = this._get('IDS_AIRCARRIER');
  public static battleship = this._get('IDS_BATTLESHIP');
  public static cruiser = this._get('IDS_CRUISER');
  public static destroyer = this._get('IDS_DESTROYER');
  public static submarine = this._get('IDS_SUBMARINE');

  // Units
  public static second = this._get('SECOND', 'IDS_');
  public static kilometer = this._get('KILOMETER', 'IDS_');
  public static meter = this._get('METER', 'IDS_');
  public static millimeter = this._get('MILLIMETER', 'IDS_');
  public static kilogram = this._get('KILOGRAMM', 'IDS_');
  public static knot = this._get('KNOT', 'IDS_');
  public static meterPerSecond = this._get('METER_SECOND', 'IDS_');
  public static unit = this._get('UNITS', 'IDS_');
  public static unitPerSecond = this._get('UNITS_SECOND', 'IDS_');

  public static regionFilterName = this._get('nation', this._shipFilter);
  public static shipNameFilterName = this._get('shipname', this._shipFilter);
  public static shipTypeFilterName = this._get('shiptype', this._shipFilter);
  public static tierFilterName = this._get('level', this._shipFilter);

  public static hull = this._get('HULL', this._moduleType);
  public static artillery = this._get('ARTILLERY', this._moduleType);
  public static secondaries = this._get('SECONDARYWEAPONS', this._moduleType);
  public static torpedoes = this._get('TORPEDOES', this._moduleType);
  public static sonar = this._get('SONAR', this._moduleType);
  public static fireControl = this._get('SUO', this._moduleType);
  public static engine = this._get('ENGINE', this._moduleType);
  public static fighter = this._get('FIGHTER', this._moduleType);
  public static skipBomber = this._get('SKIPBOMBER', this._moduleType);
  public static torpedoBomber = this._get('TORPEDOBOMBER', this._moduleType);
  public static diveBomber = this._get('DIVEBOMBER', this._moduleType);

  public static maximumRange = this._get('MAX_DIST', this._shipParam);
  public static reloadTime = this._get('SHOT_DELAY', this._shipParam);
  public static rotationTime = this._get('ROTATION_TIME', this._shipParam);
  public static shellWeight = this._get('AMMO_BULLET_MASS', this._shipParam);
  public static diveCapacity = this._get('BATTERY', this._shipParam);

  // Durability
  public static durability = this._get('DURABILITY', this._shipParam);
  public static health = this._get('HEALTH', this._shipParam);
  public static torpedoProtection = this._get('PTZDAMAGEPROB', this._shipParam);

  // Main battery
  public static shipArtillery = this._get('ARTILLERY', this._shipParam);
  public static gunReloadTime = this.reloadTime;
  public static gunRotationTime = this.rotationTime;
  public static gunDamage = this._get('ARTILLERY_MAX_DAMAGE', this._shipParam);
  public static gunRange = this.maximumRange;
  public static gunDispersion = this._get('DISPERSION', this._shipParam);
  public static shellVelocity = this._get('ARTILLERY_AMMO_SPEED', this._shipParam);
  public static shellPenetration = this._get('ARTILLERY_ALPHA_PIERCING', this._shipParam);
  public static shellFireChance = this._get('ARTILLERY_BURN_PROB', this._shipParam);
  
  // Burst fire mode
  public static burstFire = this._get('BURST_FIRE_NAME', this._shipParam);
  public static burstFireCount = this._get('BURST_FIRE_SALVO_COUNT', this._shipParam);
  public static burstFireInterval = this._get('BURST_FIRE_SALVO_SHOT_DELAY', this._shipParam);
  public static burstFireReload = this._get('BURST_FIRE_RELOAD_TIME', this._shipParam);

  // Secondary battery
  public static secondaryBattery = this._get('ATBA_SHORT', this._shipParam);

  // Sonar
  public static pingerDuration = this._get('WAVE_DURATION', this._shipParam);

  // Battery
  public static diveCapacity = this._get('BATTERY', this._shipParam);
  public static batteryMaxCapacity = this._get('BATTERY_MAX_CAPACITY', this._shipParam);
  public static batteryConsumption = this._get('BATTERY_CONSUMPTION_RATE', this._shipParam);
  public static batteryRegen = this._get('BATTERY_REGEN_RATE', this._shipParam);

  // Torpedo
  public static torpedoRange = this._get('TORPEDO_MAX_DIST', this._shipParam);
  public static torpedoSpeed = this._get('TORPEDO_SPEED', this._shipParam);
  public static torpedoReloadTime = this.reloadTime;
  public static torpedoRotationTime = this.rotationTime;
  public static torpedoDamage = this._get('TORPEDO_DAMAGE', this._shipParam);
  public static torpedoDetection = this._get('TORPEDO_VISIBILITY_DIST', this._shipParam);
  public static floodChance = this._get('FLOODCHANCEFACTOR', this._paramModifier);

  // AA
  public static airDefense = this._get('AIR_DEFENSE', this._shipParam);
  public static continuousDamage = this._get('AA_AVERAGE_CONST_DAMAGE', this._shipParam);
  public static airBubbleDamage = this._get('AA_BUBBLE_DAMAGE_IN_A_SECOND', this._shipParam);
  public static aaRange = this._get('AA_RANGE', this._shipParam);
  public static hitChance = this._get('AA_BUBBLE_HITCHANCE', this._shipParam);
  public static bubbleExplosion = this._get('AA_EXPL_COUNT', this._shipParam);

  // Air support
  public static numberOfBombs = this._get('NUM_BOMBS_ON_PLANE', this._airSupportParams);
  public static availableFlights = this._get('NUM_SQUADRONS', this._airSupportParams);
  public static airSupportTotalPlanes = this._get('NUM_PLANES_IN_CHARGE', this._airSupportParams);
  public static bombDamage = this._get('BOMB_DAMAGE', this._airSupportParams);
  public static planeHealth = this._get('PLANEHEALTH', this._paramModifier);

  // Mobility
  public static mobility = this._get('MOBILITY', this._shipParam);
  public static maxSpeed = this._get('MAXSPEED', this._shipParam);
  public static turningRadius = this._get('TURNINGRADIUS', this._shipParam);
  public static rudderTime = this._get('RUDDER_TIME', this._shipParam);

  // Visibility
  public static visibility = this._get('VISIBILITY', this._shipParam);
  public static seaDetection = this._get('VISIBILITY_DIST_BY_SHIP', this._shipParam);
  public static airDetection = this._get('VISIBILITY_DIST_BY_PLANE', this._shipParam);

  public static airSupport = this._get('AIRSUPPORT', this._shipParam);
  public static depthCharge = this._get('DEPTH_CHARGE', this._shipParam);
  public static actionTime = this._get('WORK_TIME', this._shipParam);
  public static requiredHits = this._get('REQUIREDHITS_RAGEMODE', this._paramModifier);

  // Upgrades
  public static upgrades = this._get('MODERNIZATIONS', 'IDS_');
  public static nextShip = this._get('SPECTATE_SWITCH_SHIP', 'IDS_');
  public static consumables = this._get('ABILITIES', 'IDS_MODULE_TYPE_');
}

export default ShipStrings;


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