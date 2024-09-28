
interface AppContextType {
  darkMode: boolean;
  themeColour: any; // Replace with actual type
  themeData: any; // Replace with actual type
  locale: string;
  setDarkMode: (value: boolean) => void;
  setThemeColour: (value: any) => void; // Replace with actual type
  setLocale: (value: string) => void;
}

const AppContext = createContext<AppContextType | undefined>(undefined);

export const AppProvider: React.FC = ({ children }) => {
  const userRepository = UserRepository.instance;

  const [darkMode, setDarkMode] = useState<boolean>(userRepository.darkMode);
  const [themeColour, setThemeColour] = useState<AppThemeColour>(new AppThemeColour(userRepository.themeColour));
  const [locale, setLocale] = useState<string>(userRepository.appLanguage);

  useEffect(() => {
    const theme = darkMode ? 'dark' : 'light';
    Appearance.setColorScheme(theme);
    I18nManager.forceRTL(locale === 'ar'); // Example for RTL support
  }, [darkMode, locale]);

  const themeData = generateThemeData(themeColour); // Implement this function based on your theme logic

  return (
    <AppContext.Provider value={{ darkMode, themeColour, themeData, locale, setDarkMode, setThemeColour, setLocale }}>
      {children}
    </AppContext.Provider>
  );
};

export const useAppContext = (): AppContextType => {
  const context = useContext(AppContext);
  if (!context) {
    throw new Error('useAppContext must be used within an AppProvider');
  }
  return context;
};

const generateThemeData = (themeColour: AppThemeColour) => {
  // Implement your theme data generation logic here
  return {}; // Return the generated theme data
};


const generateThemeData = (themeColour: { colour: string }, darkMode: boolean) => {
  const color = themeColour.colour;
  const theme = createTheme({
    colors: {
      primary: color,
      background: darkMode ? '#000' : '#fff',
      text: darkMode ? '#fff' : '#000',
    },
    dark: darkMode,
    roundness: 2,
  });

  return theme;
};

const App = () => {
  const themeColour = { colour: '#6200ee' }; // Example color
  const darkMode = false; // Example mode

  const theme = generateThemeData(themeColour, darkMode);

  return (
    <ThemeProvider theme={theme}>
      <Checkbox
        status={checked ? 'checked' : 'unchecked'}
        onPress={() => {
          setChecked(!checked);
        }}
        color={theme.colors.primary}
      />
    </ThemeProvider>
  );
};

export default App;


const ThemeContext = createContext<any>(null);

export const ThemeProviderComponent: React.FC = ({ children }) => {
    const [darkMode, setDarkMode] = useState<boolean>(false);
    const [themeData, setThemeData] = useState<any>(generateThemeData(darkMode));
    const logger = new Logger();
    const userRepository = new UserRepository();

    useEffect(() => {
        setThemeData(generateThemeData(darkMode));
    }, [darkMode]);

    const updateDarkMode = (newDarkMode: boolean) => {
        logger.info(`updated DarkMode to ${newDarkMode}`);
        userRepository.darkMode = newDarkMode;
        setDarkMode(newDarkMode);
    };

    return (
        <ThemeContext.Provider value={{ darkMode, updateDarkMode }}>
            <ThemeProvider theme={themeData}>
                {children}
            </ThemeProvider>
        </ThemeContext.Provider>
    );
};

export const useTheme = () => {
    return useContext(ThemeContext);
};


interface Locale {
    languageCode: string;
}

interface LocaleContextType {
    locale: Locale;
    updateLocale: (locale: Locale) => void;
}

const LocaleContext = createContext<LocaleContextType | undefined>(undefined);

export const LocaleProvider: React.FC = ({ children }) => {
    const [locale, setLocale] = useState<Locale>({ languageCode: 'en' });

    const updateLocale = (newLocale: Locale) => {
        console.info(`updated Locale to ${newLocale.languageCode}`);
        setLocale(newLocale);
        I18nManager.forceRTL(newLocale.languageCode === 'ar'); // Example for RTL support
    };

    return (
        <LocaleContext.Provider value={{ locale, updateLocale }}>
            {children}
        </LocaleContext.Provider>
    );
};

export const useLocale = () => {
    const context = useContext(LocaleContext);
    if (context === undefined) {
        throw new Error('useLocale must be used within a LocaleProvider');
    }
    return context;
};


const ThemeContext = createContext<any>(null);

export const ThemeProvider: React.FC = ({ children }) => {
    const [themeColour, setThemeColour] = useState<AppThemeColour>(AppThemeColour.DEFAULT);
    const [themeData, setThemeData] = useState<any>(generateThemeData(themeColour));
    const logger = new Logger();
    const userRepository = new UserRepository();

    const updateThemeColour = (colour: ColorValue) => {
        logger.info(`updated ThemeColour to ${colour}`);
        const newThemeColour = AppThemeColour.fromColour(colour);
        setThemeColour(newThemeColour);
        userRepository.themeColour = newThemeColour.index;

        const newThemeData = generateThemeData(newThemeColour);
        setThemeData(newThemeData);
    };

    useEffect(() => {
        // Any side effects or subscriptions can be handled here
    }, [themeColour]);

    return (
        <ThemeContext.Provider value={{ themeColour, themeData, updateThemeColour }}>
            {children}
        </ThemeContext.Provider>
    );
};

export const useTheme = () => {
    return useContext(ThemeContext);
};


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