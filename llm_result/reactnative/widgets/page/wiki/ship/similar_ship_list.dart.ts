
interface SimilarShipListProps {
  source: any;
  ships: Ship[];
}

const SimilarShipList: React.FC<SimilarShipListProps> = ({ source, ships }) => {
  const renderItem = ({ item }: { item: Ship }) => (
    <TouchableOpacity onPress={() => {/* Navigate to ShipInfoPage or ShipSimilarPage */}}>
      <ShipCell ship={item} />
    </TouchableOpacity>
  );

  return (
    <View>
      <Text>{source.title}</Text>
      <FlatList
        data={ships}
        renderItem={renderItem}
        keyExtractor={(item) => item.id.toString()}
      />
    </View>
  );
};

export default SimilarShipList;


interface Ship {
  index: number;
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
}

interface ShipSimilarProps {
  source: Ship;
  ships: Ship[];
}

const ShipSimilar: React.FC<ShipSimilarProps> = ({ source, ships }) => {
  const navigation = useNavigation();

  const handleCompareSimilar = () => {
    navigation.navigate('ShipSimilarPage', { ship: source });
  };

  const handleShipInfo = (ship: Ship) => {
    navigation.navigate('ShipInfoPage', { ship });
  };

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.buttonContainer}>
        <Button title="Compare Similar" onPress={handleCompareSimilar} />
      </View>
      <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={styles.scrollView}>
        {ships.map((ship) => {
          const name = ship.name; // Assuming Localisation is handled elsewhere
          return (
            <View key={ship.index} style={styles.shipCell}>
              <Text>{name}</Text>
              <Button title="Info" onPress={() => handleShipInfo(ship)} />
            </View>
          );
        })}
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 16,
  },
  buttonContainer: {
    marginBottom: 16,
  },
  scrollView: {
    flexDirection: 'row',
  },
  shipCell: {
    marginRight: 16,
    alignItems: 'center',
  },
});

export default ShipSimilar;


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