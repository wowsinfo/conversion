
const store = createStore((state = { ships: [] }, action) => {
  switch (action.type) {
    // Define your actions here
    default:
      return state;
  }
});

const CompareShipPage: React.FC = () => {
  const { ships, filter, setFilter } = useContext(CompareShipContext);
  const [isDialogVisible, setDialogVisible] = useState(false);

  const renderItem = ({ item }: { item: any }) => (
    <View style={styles.row}>
      <Text style={styles.cell}>{item.shipName || ''}</Text>
      <Text style={styles.cell}>{item.health}</Text>
      <Text style={styles.cell}>{item.gunReloadTime}</Text>
      <Text style={styles.cell}>{item.gunRange}</Text>
      <Text style={styles.cell}>{item.gunConfiguration}</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Compare Ship</Text>
      <FlatList
        data={ships}
        renderItem={renderItem}
        keyExtractor={(item) => item.id.toString()}
        style={styles.table}
      />
      <Button title="Filter" onPress={() => setDialogVisible(true)} />
      {isDialogVisible && (
        <FilterShipDialog
          visible={isDialogVisible}
          onClose={() => setDialogVisible(false)}
          onFilterChange={(newFilter) => {
            setFilter(newFilter);
            setDialogVisible(false);
          }}
        />
      )}
    </View>
  );
};

const App: React.FC = () => (
  <Provider store={store}>
    <CompareShipProvider>
      <CompareShipPage />
    </CompareShipProvider>
  </Provider>
);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  title: {
    fontSize: 24,
    marginBottom: 16,
  },
  table: {
    marginBottom: 16,
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: 8,
  },
  cell: {
    flex: 1,
    textAlign: 'center',
  },
});

export default App;


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