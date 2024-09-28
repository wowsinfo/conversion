
interface ShipPageProps {
  special?: boolean;
}

const ShipPage: React.FC<ShipPageProps> = ({ special = false }) => {
  const navigation = useNavigation();
  const [itemCount, setItemCount] = useState(0);
  const shipProvider = new ShipProvider(special);

  useEffect(() => {
    const count = Utils.getItemCount(8, 2, 119);
    setItemCount(count);
  }, []);

  const buildGridView = (count: number) => {
    const ships = shipProvider.getShips(); // Assuming this method exists
    return (
      <View style={styles.grid}>
        {ships.slice(0, count).map((ship, index) => (
          <ShipCell key={index} ship={ship} onPress={() => navigation.navigate('ShipInfoPage', { ship })} />
        ))}
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{Localisation.wiki_warships}</Text>
      <ScrollView style={styles.scrollView}>
        {buildGridView(itemCount)}
      </ScrollView>
      <FloatingActionButton onPress={() => {/* Handle FAB action */}} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 16,
  },
  scrollView: {
    flex: 1,
  },
  grid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
});

export default ShipPage;


const BuildFAB: React.FC = () => {
  const provider = useContext(ShipContext);

  return (
    <View style={{ position: 'absolute', bottom: 20, right: 20 }}>
      <TouchableOpacity
        onPress={provider.showFilter}
        style={{
          backgroundColor: '#6200ee',
          padding: 10,
          borderRadius: 30,
          flexDirection: 'row',
          alignItems: 'center',
        }}
      >
        <Text style={{ color: '#fff', marginRight: 5 }}>Filter</Text>
        <Text style={{ color: '#fff' }}>{provider.filterString}</Text>
      </TouchableOpacity>
    </View>
  );
};

export default BuildFAB;


const GridView: React.FC<{ itemCount: number }> = observer(({ itemCount }) => {
  const { shipStore } = useStore();

  const renderItem = ({ item }: { item: any }) => {
    const imageName = item.index;
    const shipName = item.name; // Assuming you have a localization function

    return (
      <PopupBox>
        <TouchableOpacity onPress={() => navigation.navigate('ShipInfoPage', { ship: item })}>
          <View style={styles.cell}>
            <Image source={{ uri: imageName }} style={styles.image} />
            <Text style={styles.text}>{`${item.tierString} ${shipName}`}</Text>
          </View>
        </TouchableOpacity>
      </PopupBox>
    );
  };

  return (
    <FlatList
      data={shipStore.shipList}
      renderItem={renderItem}
      keyExtractor={(item) => item.id.toString()} // Assuming each ship has a unique id
      numColumns={itemCount}
      contentContainerStyle={styles.container}
      showsVerticalScrollIndicator={false}
    />
  );
});

const styles = StyleSheet.create({
  container: {
    paddingBottom: 64,
  },
  cell: {
    flex: 1,
    aspectRatio: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: '100%',
    height: '100%',
    resizeMode: 'contain',
  },
  text: {
    textAlign: 'center',
  },
});

export default GridView;


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