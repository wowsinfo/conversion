
const UpgradePage: React.FC = () => {
  const modernization = GameRepository.instance.modernizationList;
  const logger = new Logger('UpgradePage');

  const itemCount = Utils.getItemCount(8, 2, 100);

  const buildGridView = (count: number) => {
    return (
      <View style={styles.grid}>
        {Array.from({ length: count }).map((_, index) => (
          <View key={index} style={styles.item}>
            <Text>Item {index + 1}</Text>
          </View>
        ))}
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Upgrade Page</Text>
      <ScrollView>
        {buildGridView(itemCount)}
      </ScrollView>
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
  grid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  item: {
    width: '48%',
    height: 100,
    backgroundColor: '#f0f0f0',
    marginBottom: 16,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default UpgradePage;


const { width } = Dimensions.get('window');

const buildGridView = (itemCount: number, modernization: Array<{ icon: string }>, showInfo: (context: any, curr: any) => void) => {
  const numColumns = itemCount;

  const renderItem = ({ item }: { item: { icon: string } }) => {
    const imageName = item.icon;
    return (
      <TouchableOpacity onPress={() => showInfo(null, item)}>
        <Image
          source={{ uri: `data/live/app/assets/upgrades/${imageName}.png` }}
          style={{ width: width / numColumns, height: width / numColumns }}
          resizeMode="contain"
        />
      </TouchableOpacity>
    );
  };

  return (
    <FlatList
      data={modernization}
      renderItem={renderItem}
      keyExtractor={(item, index) => index.toString()}
      numColumns={numColumns}
    />
  );
};

export default buildGridView;


interface Modernization {
  icon: string; // Adjust type as necessary
  title: string;
  description: string;
}

const showInfo = (upgrade: Modernization) => {
  const additionalString = upgrade.toString();

  Alert.alert(
    upgrade.title,
    `${upgrade.description}\n\n${additionalString}`,
    [
      { text: 'OK', onPress: () => console.log('OK Pressed') },
    ],
    { cancelable: true }
  );
};

const InfoComponent: React.FC<{ upgrade: Modernization }> = ({ upgrade }) => {
  return (
    <View style={styles.container}>
      <Icon name={upgrade.icon} />
      <Text style={styles.title}>{upgrade.title}</Text>
      <Text style={styles.description}>{upgrade.description}</Text>
      <Text onPress={() => showInfo(upgrade)} style={styles.infoButton}>
        Show Info
      </Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  description: {
    fontSize: 14,
    marginVertical: 5,
  },
  infoButton: {
    color: 'blue',
    textDecorationLine: 'underline',
  },
});

export default InfoComponent;


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