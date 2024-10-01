
interface UserRepositoryInterface {
  appLanguage: string;
  dataLanguage: string;
  username: string;
  accountID: string;
  gameServer: string;
  darkMode: boolean;
  themeColour: string;
}

class UserRepository {
  // Key values
  private static readonly appLanguageKey = 'wowsinfo@app_language';
  private static readonly dataLanguageKey = 'wowsinfo@data_language';
  private static readonly usernameKey = 'wowsinfo@username';
  private static readonly accountIDKey = 'wowsinfo@account_id';
  private static readonly gameServerKey = 'wowsinfo@game_server';
  private static readonly darkModeKey = 'wowsinfo@dark_mode';
  private static readonly themeColourKey = 'wowsinfo@theme_colour';

  // Singleton instance
  private static instance: UserRepository;

  private constructor() {}

  public static getInstance(): UserRepository {
    if (!UserRepository.instance) {
      UserRepository.instance = new UserRepository();
    }
    return UserRepository.instance;
  }

  public async inject(store: any): Promise<void> {
    // Store logic can be implemented here
  }

  public async getAppLanguage(): Promise<string | null> {
    return await AsyncStorage.getItem(UserRepository.appLanguageKey);
  }

  public async setAppLanguage(language: string): Promise<void> {
    await AsyncStorage.setItem(UserRepository.appLanguageKey, language);
  }

  public async getDataLanguage(): Promise<string | null> {
    return await AsyncStorage.getItem(UserRepository.dataLanguageKey);
  }

  public async setDataLanguage(language: string): Promise<void> {
    await AsyncStorage.setItem(UserRepository.dataLanguageKey, language);
  }

  public async getUsername(): Promise<string | null> {
    return await AsyncStorage.getItem(UserRepository.usernameKey);
  }

  public async setUsername(username: string): Promise<void> {
    await AsyncStorage.setItem(UserRepository.usernameKey, username);
  }

  public async getAccountID(): Promise<string | null> {
    return await AsyncStorage.getItem(UserRepository.accountIDKey);
  }

  public async setAccountID(accountID: string): Promise<void> {
    await AsyncStorage.setItem(UserRepository.accountIDKey, accountID);
  }

  public async getGameServer(): Promise<string | null> {
    return await AsyncStorage.getItem(UserRepository.gameServerKey);
  }

  public async setGameServer(server: string): Promise<void> {
    await AsyncStorage.setItem(UserRepository.gameServerKey, server);
  }

  public async getDarkMode(): Promise<boolean | null> {
    const value = await AsyncStorage.getItem(UserRepository.darkModeKey);
    return value !== null ? JSON.parse(value) : null;
  }

  public async setDarkMode(isDarkMode: boolean): Promise<void> {
    await AsyncStorage.setItem(UserRepository.darkModeKey, JSON.stringify(isDarkMode));
  }

  public async getThemeColour(): Promise<string | null> {
    return await AsyncStorage.getItem(UserRepository.themeColourKey);
  }

  public async setThemeColour(colour: string): Promise<void> {
    await AsyncStorage.setItem(UserRepository.themeColourKey, colour);
  }
}

export default UserRepository;


const _defaultLanguage = Localisation.decideLang();

class AppSettings {
  private _appLanguageKey = '@appLanguage';
  private _dataLanguageKey = '@dataLanguage';
  private _accountIDKey = '@accountID';
  private _usernameKey = '@username';
  private _gameServerKey = '@gameServer';
  private _themeColourKey = '@themeColour';
  private _darkModeKey = '@darkMode';

  private async getItem(key: string): Promise<any> {
    const value = await AsyncStorage.getItem(key);
    return value ? JSON.parse(value) : null;
  }

  private async setItem(key: string, value: any): Promise<void> {
    await AsyncStorage.setItem(key, JSON.stringify(value));
  }

  public async get appLanguage(): Promise<string> {
    const lang = await this.getItem(this._appLanguageKey);
    return lang ?? _defaultLanguage;
  }

  public async set appLanguage(value: string) {
    await this.setItem(this._appLanguageKey, value);
  }

  public async get dataLanguage(): Promise<string> {
    const lang = await this.getItem(this._dataLanguageKey);
    return lang ?? _defaultLanguage;
  }

  public async set dataLanguage(value: string) {
    await this.setItem(this._dataLanguageKey, value);
  }

  public async get isFavourite(): Promise<boolean> {
    const accountID = await this.getItem(this._accountIDKey);
    return accountID !== null;
  }

  public async get username(): Promise<string> {
    const name = await this.getItem(this._usernameKey);
    return name ?? '';
  }

  public async set username(value: string) {
    await this.setItem(this._usernameKey, value);
  }

  public async get accountID(): Promise<string> {
    const id = await this.getItem(this._accountIDKey);
    return id ?? '';
  }

  public async set accountID(value: string) {
    await this.setItem(this._accountIDKey, value);
  }

  public async get gameServer(): Promise<number> {
    const server = await this.getItem(this._gameServerKey);
    return server ?? 3;
  }

  public async set gameServer(value: number) {
    await this.setItem(this._gameServerKey, value);
  }

  public async get themeColour(): Promise<number> {
    const colour = await this.getItem(this._themeColourKey);
    return colour ?? AppThemeColour.defaultIndex;
  }

  public async set themeColour(index: number) {
    await this.setItem(this._themeColourKey, index);
  }

  public async get darkMode(): Promise<boolean> {
    const mode = await this.getItem(this._darkModeKey);
    return mode ?? false;
  }

  public async set darkMode(value: boolean) {
    await this.setItem(this._darkModeKey, value);
  }
}

class AppThemeColour {
  public static colourList = [
    Colors.red,
    Colors.pink,
    Colors.purple,
    Colors.deepPurple,
    Colors.indigo,
    Colors.blue,
    Colors.lightBlue,
    Colors.cyan,
    Colors.teal,
    Colors.green,
    Colors.lightGreen,
    Colors.lime,
    Colors.deepOrange,
    Colors.brown,
    Colors.grey,
    Colors.blueGrey,
  ];

  public static defaultIndex = 0;

  private _colour: typeof Colors;

  public get colour(): typeof Colors {
    return this._colour;
  }

  public set colour(c: typeof Colors) {
    if (c === this._colour) return;
    this._colour = c;
  }
}


const colourList = ['red', 'green', 'blue', 'yellow']; // Example colour list
const defaultIndex = 0;

const AppThemeColour: React.FC<{ index?: number }> = ({ index = defaultIndex }) => {
  const [colour, setColour] = useState(colourList[index]);

  const getIndex = () => colourList.indexOf(colour);

  return (
    <div style={{ backgroundColor: colour }}>
      <p>Current Colour: {colour}</p>
      <p>Colour Index: {getIndex()}</p>
    </div>
  );
};

export default AppThemeColour;


class AppThemeColour {
    index: number;

    constructor(index: number) {
        this.index = index;
    }

    static fromColour(colour: MaterialColor): AppThemeColour {
        return new AppThemeColour(colorList.indexOf(colour));
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