
const AchievementPage: React.FC = () => {
  const achievements = GameRepository.instance.achievementList;
  const logger = new Logger('AchievementPage');

  const itemCount = Utils.getItemCount(8, 2, 100);

  const buildGridView = (count: number) => {
    return (
      <View style={styles.grid}>
        {Array.from({ length: count }).map((_, index) => (
          <View key={index} style={styles.item}>
            <Text>{achievements[index]?.name || 'Placeholder'}</Text>
          </View>
        ))}
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Achievement Page</Text>
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
  },
  item: {
    width: '48%',
    margin: '1%',
    padding: 16,
    backgroundColor: '#f0f0f0',
    borderRadius: 8,
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default AchievementPage;


interface Achievement {
  icon: string;
  added: number;
}

interface Props {
  achievements: Achievement[];
}

const buildGridView = ({ achievements }: Props) => {
  const renderItem = ({ item }: { item: Achievement }) => {
    const imageName = item.icon;

    return (
      <TouchableOpacity onPress={() => showInfo(item)}>
        <View style={{ position: 'relative', aspectRatio: 1 }}>
          <AssetImageLoader
            name={`data/live/app/assets/achievements/${imageName}.png`}
          />
          {item.added === 1 && <NewItemIndicator />}
        </View>
      </TouchableOpacity>
    );
  };

  return (
    <FlatList
      data={achievements}
      renderItem={renderItem}
      keyExtractor={(item, index) => index.toString()}
      numColumns={3} // Adjust this based on itemCount
    />
  );
};

export default buildGridView;


const showInfo = (achievement: Achievement) => {
  Alert.alert(
    Localisation.instance.stringOf(achievement.name) || '',
    Localisation.instance.stringOf(achievement.description, {
      constants: achievement.constants,
    }) || '',
    [{ text: 'OK' }],
    { cancelable: true }
  );
};

const AchievementComponent: React.FC<{ achievement: Achievement }> = ({ achievement }) => {
  return (
    <View>
      <Button title="Show Info" onPress={() => showInfo(achievement)} />
    </View>
  );
};

export default AchievementComponent;


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