
type ModelCreator<T> = (data: Record<string, any>) => T | null;

class ServiceResult<T> {
  data: T | null;
  errorMessage: string | null;

  constructor(data: T | null = null, errorMessage: string | null = null) {
    this.data = data;
    this.errorMessage = errorMessage;
  }

  get hasError(): boolean {
    return this.errorMessage !== null;
  }

  get isNotEmpty(): boolean {
    return this.data !== null;
  }

  static copyWith<T>(other: ServiceResult<T>): ServiceResult<T> {
    return new ServiceResult(other.data, other.errorMessage);
  }
}

async function fetchData<T>(url: string, modelCreator: ModelCreator<T>): Promise<ServiceResult<T>> {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const data = await response.json();
    const modelData = modelCreator(data);
    return new ServiceResult(modelData);
  } catch (error) {
    return new ServiceResult(null, error.message);
  }
}

// Example usage
const ExampleComponent = () => {
  const [result, setResult] = useState<ServiceResult<any>>(new ServiceResult());

  const loadData = async () => {
    const serviceResult = await fetchData('https://api.example.com/data', (data) => data);
    setResult(serviceResult);
    if (serviceResult.hasError) {
      Alert.alert('Error', serviceResult.errorMessage || 'Unknown error');
    }
  };

  return (
    // Your component JSX here
  );
};

class ServiceResult<T> {
  data: T | null;
  errorMessage: string | null;

  constructor(data: T | null, errorMessage: string | null) {
    this.data = data;
    this.errorMessage = errorMessage;
  }

  toString(): string {
    return `ServiceResult{data: ${this.data}, errorMessage: ${this.errorMessage}}`;
  }
}


abstract class BaseService {
  abstract baseUrl: string;

  timeout: number = 30;
  private logger: Logger = new Logger('BaseService');

  protected async getObject(url: string): Promise<ServiceResult<Object | null>> {
    try {
      const response = await axios.get(url, { timeout: this.timeout * 1000 });
      if (response.status === 200) {
        const json = response.data;
        if (json) {
          this.logger.info(`fetched data from ${url} successfully`);
          return new ServiceResult({ data: json });
        } else {
          this.logger.error('JSON decoding returned null');
          return new ServiceResult({ errorMessage: 'JSON decoding error' });
        }
      } else {
        const errorCode = response.status;
        this.logger.warn(`getObject failed with code: ${errorCode}`);
        return new ServiceResult({ errorMessage: `HTTP Error: ${errorCode}` });
      }
    } catch (e) {
      if (e instanceof TimeoutError) {
        this.logger.error('Request timed out');
        return new ServiceResult({ errorMessage: 'Request timed out' });
      }
      this.logger.error(`getObject exception\n${e}`);
      return new ServiceResult({ errorMessage: e.toString() });
    }
  }
}


type ModelCreator<T> = (data: Record<string, any>) => T;

class YourClass {
  private logger: Logger;

  constructor() {
    this.logger = new Logger();
  }

  protected decodeObject<T>(
    json: ServiceResult<Object | null>,
    creator: ModelCreator<T>
  ): ServiceResult<T> {
    if (json.hasError) return ServiceResult.copyWith(json);

    if (json.isNotEmpty) {
      const jsonData = json.data;
      if (typeof jsonData === 'object' && jsonData !== null) {
        const data = jsonData['data'];
        if (typeof data === 'object' && data !== null) {
          const result = creator(data);
          this.logger.info(`decoded json successfully as ${result}`);
          return new ServiceResult<T>({ data: result });
        } else {
          this.logger.severe('data is not a Record<string, any>');
        }
      }
    } else {
      this.logger.severe('json.data is null, API failure');
    }

    this.logger.severe(`failed to decode ${T}`, json);
    return new ServiceResult<T>({ errorMessage: `Decoding failure in ${T}` });
  }
}


type ModelCreator<T> = (data: any) => T;

class Decoder {
  private logger: Logger;

  constructor() {
    this.logger = new Logger();
  }

  // Decode List<dynamic> to a list of [T] using [creator] and return as [ServiceResult].
  protected decodeList<T>(
    json: ServiceResult<any>,
    creator: ModelCreator<T>
  ): ServiceResult<T[]> {
    if (json.hasError) return ServiceResult.copyWith(json);

    if (json.isNotEmpty) {
      const jsonData = json.data;
      if (typeof jsonData === 'object' && jsonData !== null) {
        const data = jsonData['data'];
        if (Array.isArray(data)) {
          const list = data.map((e) => creator(e)) as T[];
          this.logger.info(`decoded json successfully as ${JSON.stringify(list)}`);
          return new ServiceResult({ data: list });
        } else {
          this.logger.severe('data is not a List');
        }
      }
    } else {
      this.logger.severe('json.data is null, API failure');
    }

    this.logger.severe(`failed to decode ${T}`, json);
    return new ServiceResult({ errorMessage: `Decoding failure in ${T}` });
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