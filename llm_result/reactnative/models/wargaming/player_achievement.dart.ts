
interface PlayerAchievementProps {
  battle?: number;
  progress?: number;
}

const PlayerAchievement: React.FC<PlayerAchievementProps> = ({ battle, progress }) => {
  return (
    <View>
      {battle !== undefined && <Text>Battle: {battle}</Text>}
      {progress !== undefined && <Text>Progress: {progress}</Text>}
    </View>
  );
};

export default PlayerAchievement;

interface PlayerAchievement {
  battle?: Record<string, number>;
  progress?: Record<string, number>;
}

const fromJson = (json: Record<string, any>): PlayerAchievement => {
  // this is a hidden account
  if (Object.keys(json).length === 0) return {};

  const player = Object.values(json)[0];
  if (typeof player === 'object' && player !== null) {
    return {
      battle: player.battle ? { ...player.battle } : undefined,
      progress: player.progress ? { ...player.progress } : undefined,
    };
  }

  throw new Error('Not a hidden account but data is missing');
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
    backgroundColor: '#F5FCFF',
  },
  text: {
    fontSize: 20,
    margin: 10,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
  },
});

export default App;