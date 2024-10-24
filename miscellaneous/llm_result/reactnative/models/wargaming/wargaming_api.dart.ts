class WargamingAPIError {
  field?: string;
  message?: string;
  code?: number;
  value?: string;

  constructor(json: Record<string, any>) {
    this.field = json.field as string | undefined;
    this.message = json.message as string | undefined;
    this.code = json.code as number | undefined;
    this.value = json.value as string | undefined;
  }
}

class WargamingAPIError extends Error {
  field: string;
  message: string;
  code: number;
  value: any;

  constructor(field: string, message: string, code: number, value: any) {
    super(message);
    this.field = field;
    this.message = message;
    this.code = code;
    this.value = value;
  }

  toString(): string {
    return `WargamingAPIError{field: ${this.field}, message: ${this.message}, code: ${this.code}, value: ${this.value}}`;
  }
}

class WargamingAPIMeta {
  count?: number;
  pageTotal?: number;
  total?: number;
  limit?: number; // by default 100
  page?: number;

  constructor(count?: number, pageTotal?: number, total?: number, limit: number = 100, page?: number) {
    this.count = count;
    this.pageTotal = pageTotal;
    this.total = total;
    this.limit = limit;
    this.page = page;
  }

  // Check if there are multiple pages available.
  get requirePagination(): boolean {
    return this.pageTotal !== undefined && this.pageTotal > 1;
  }

  // Check if there are more pages available.
  get hasMorePages(): boolean {
    return this.pageTotal !== undefined && this.page !== undefined && this.page < this.pageTotal;
  }
}

interface WargamingAPIMeta {
    count?: number;
    pageTotal?: number;
    total?: number;
    limit?: number;
    page?: number;
}

function fromJson(json: Record<string, any>): WargamingAPIMeta {
    return {
        count: json.count as number | undefined,
        pageTotal: json.page_total as number | undefined,
        total: json.total as number | undefined,
        limit: json.limit as number | undefined,
        page: json.page as number | undefined,
    };
}

class WargamingAPIMeta {
  count: number;
  pageTotal: number;
  total: number;
  limit: number;
  page: number;

  constructor(count: number, pageTotal: number, total: number, limit: number, page: number) {
    this.count = count;
    this.pageTotal = pageTotal;
    this.total = total;
    this.limit = limit;
    this.page = page;
  }

  toString(): string {
    return `WargamingAPIMeta{count: ${this.count}, pageTotal: ${this.pageTotal}, total: ${this.total}, limit: ${this.limit}, page: ${this.page}}`;
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