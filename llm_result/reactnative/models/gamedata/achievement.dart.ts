
interface AchievementProps {
  icon: ImageSourcePropType;
  name: string;
  description: string;
  type: string;
  id: string;
  constants: any; // Replace 'any' with the appropriate type if known
  added?: Date; // Optional property
}

const Achievement: React.FC<AchievementProps> = ({
  icon,
  name,
  description,
  type,
  id,
  constants,
  added,
}) => {
  return (
    <div>
      <img src={icon} alt={name} />
      <h2>{name}</h2>
      <p>{description}</p>
      <p>Type: {type}</p>
      <p>ID: {id}</p>
      <p>Constants: {JSON.stringify(constants)}</p>
      {added && <p>Added on: {added.toDateString()}</p>}
    </div>
  );
};

export default Achievement;

interface Achievement {
  icon: string;
  name: string;
  description: string;
  type: string[];
  id: number;
  added?: number;
  constants: Record<string, any>;
}

const fromJson = (json: any): Achievement => {
  return {
    icon: json.icon,
    name: json.name,
    description: json.description,
    type: json.type,
    id: json.id,
    constants: { ...json.constants },
    added: json.added,
  };
};

const achievementToString = (achievement: Achievement): string => {
  return `Achievement{icon: ${achievement.icon}, name: ${achievement.name}, description: ${achievement.description}, type: ${achievement.type}, id: ${achievement.id}, constants: ${JSON.stringify(achievement.constants)}}`;
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