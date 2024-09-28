
const setup = async () => {
  // setup logger and make sure it only prints in debug mode
  const logger = createLogger({
    level: 'debug',
    format: format.combine(
      format.colorize(),
      format.timestamp(),
      format.printf(({ timestamp, level, message }) => {
        return `${timestamp} ${level}: ${message}`;
      })
    ),
    transports: [
      new transports.Console()
    ],
  });

  if (__DEV__) {
    LogBox.ignoreAllLogs(); // Ignore all log notifications in development mode
    logger.info('Logger is set up and running in development mode');
  }
};

export default setup;


const setup = async () => {
  // setup the store
  const store = new SharedStore();
  await store.load();

  // inject to repositories
  AppRepository.instance.inject(store);
  UserRepository.instance.inject(store);
};

const loadAppData = async () => {
  // load the repositories
  await Localisation.instance.initialise();
  await GameRepository.instance.initialise();
};

const Main = () => {
  useEffect(() => {
    const initializeApp = async () => {
      await setup();
      await loadAppData();
    };

    initializeApp();
  }, []);

  return <WoWsInfoApp />;
};

AppRegistry.registerComponent('YourAppName', () => Main);