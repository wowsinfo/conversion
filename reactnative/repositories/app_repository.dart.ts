
/// This repository manages all cached & embedded data from the API.
///
/// It provides important data across the entire app.
/// In case the cached data (e.g. Personal Rating) is not available,
/// it will use the embedded json instead.
class AppRepository {
  /// The shared instance of the AppRepository.
  static instance: AppRepository = new AppRepository();

  private store!: StoreInterface;

  private constructor() {}

  inject(store: StoreInterface) {
    this.store = store;
  }
}

export default AppRepository;


const App = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  const fetchData = async () => {
    try {
      const response = await fetch('YOUR_API_ENDPOINT');
      const result = await response.json();
      setData(result);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
    const interval = setInterval(() => {
      fetchData();
    }, 60000); // Cache PR every minute

    return () => clearInterval(interval);
  }, []);

  if (loading) {
    return (
      <View>
        <Text>Loading...</Text>
      </View>
    );
  }

  return (
    <View>
      <Text>{JSON.stringify(data)}</Text>
    </View>
  );
};

export default App;