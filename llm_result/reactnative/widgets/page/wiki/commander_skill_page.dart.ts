
const CommanderSkillPage: React.FC = () => {
  const { skills, reset } = useContext(CommanderSkillContext);
  const [selectedSegment, setSelectedSegment] = useState(0);

  const buildSegmentedControl = () => {
    return (
      <View style={styles.segmentedControl}>
        {['Segment 1', 'Segment 2'].map((segment, index) => (
          <TouchableOpacity
            key={index}
            style={[styles.segment, selectedSegment === index && styles.selectedSegment]}
            onPress={() => setSelectedSegment(index)}
          >
            <Text style={styles.segmentText}>{segment}</Text>
          </TouchableOpacity>
        ))}
      </View>
    );
  };

  const buildPointInfo = () => {
    return (
      <View style={styles.pointInfo}>
        <Text>{Localisation.get('point_info')}</Text>
      </View>
    );
  };

  const buildCommanderSkills = () => {
    return (
      <FlatList
        data={skills}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.skillItem}>
            <Text>{item.name}</Text>
          </View>
        )}
      />
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{Localisation.get('wiki_skills')}</Text>
      <View style={styles.content}>
        {buildSegmentedControl()}
        {buildPointInfo()}
        {buildCommanderSkills()}
      </View>
      <Button title="Refresh" onPress={reset} />
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
  content: {
    flex: 1,
  },
  segmentedControl: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginBottom: 8,
  },
  segment: {
    padding: 10,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 5,
  },
  selectedSegment: {
    backgroundColor: '#007BFF',
  },
  segmentText: {
    color: '#000',
  },
  pointInfo: {
    marginBottom: 8,
  },
  skillItem: {
    padding: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
});

export default () => (
  <CommanderSkillProvider>
    <CommanderSkillPage />
  </CommanderSkillProvider>
);


const buildTitle = (title: string) => {
  return title; // Customize this function as needed
};

const buildSegmentedControl = () => {
  const { selectedTab, select, submarine, destroyer, cruiser, battleship, airCarrier } = useContext(CommanderSkillContext);

  return (
    <View>
      <SegmentedControl
        values={[
          buildTitle(submarine),
          buildTitle(destroyer),
          buildTitle(cruiser),
          buildTitle(battleship),
          buildTitle(airCarrier),
        ]}
        selectedIndex={selectedTab}
        onChange={(event) => {
          select(event.nativeEvent.selectedSegmentIndex);
        }}
      />
    </View>
  );
};

export default buildSegmentedControl;


const buildTitle = (title: string) => {
    return (
        <Text style={{ textAlign: 'center' }}>
            {title}
        </Text>
    );
};

export default buildTitle;


const PointInfo: React.FC = () => {
  const provider = useContext(CommanderSkillContext);

  return (
    <View>
      <Text style={{ textAlign: 'center', fontSize: 24 }}>
        {provider.pointInfo}
      </Text>
    </View>
  );
};

export default PointInfo;


const buildItem = (skill, selectSkill, onLongPress, isSkillSelected) => {
  return (
    <TouchableOpacity
      onPress={() => selectSkill(skill)}
      onLongPress={() => onLongPress(skill)}
      style={[styles.skillItem, isSkillSelected(skill) && styles.selectedSkill]}
    >
      <Text>{skill.name}</Text>
    </TouchableOpacity>
  );
};

const buildCommanderSkills = () => {
  const { skills, selectSkill, onLongPress, isSkillSelected, selectedDescriptions } = useContext(CommanderSkillContext);

  return (
    <ScrollView>
      <View style={styles.container}>
        {skills.map((skillGroup, index) => (
          <View key={index} style={styles.skillGroup}>
            <View style={styles.wrap}>
              {skillGroup.map(skill => (
                <View key={skill.id} style={styles.skillPadding}>
                  {buildItem(skill, selectSkill, onLongPress, isSkillSelected)}
                </View>
              ))}
            </View>
            {index < 3 && <Divider />}
          </View>
        ))}
        <Text style={styles.description}>{selectedDescriptions}</Text>
        <View style={styles.spacer} />
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
  },
  skillGroup: {
    marginBottom: 10,
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'center',
    flexWrap: 'wrap',
  },
  skillPadding: {
    padding: 4,
  },
  skillItem: {
    padding: 10,
    backgroundColor: '#f0f0f0',
    borderRadius: 5,
  },
  selectedSkill: {
    backgroundColor: '#d0d0ff',
  },
  description: {
    textAlign: 'center',
    marginVertical: 10,
  },
  spacer: {
    height: 70,
  },
});

export default buildCommanderSkills;


interface ShipSkill {
  name: string;
}

interface BuildItemProps {
  skill: ShipSkill;
  onTap: (skill: ShipSkill) => void;
  onLongPress: (skill: ShipSkill) => void;
  isSelected: (skill: ShipSkill) => boolean;
}

const BuildItem: React.FC<BuildItemProps> = ({ skill, onTap, onLongPress, isSelected }) => {
  const icon = skill.name;
  const selected = isSelected(skill);
  
  const color = selected ? '#FFFFFF' : '#000000'; // Default to black for light mode

  return (
    <TouchableOpacity
      onPress={() => onTap(skill)}
      onLongPress={() => onLongPress(skill)}
      style={[styles.container, selected && { borderColor: color }]}>
      <View style={styles.box}>
        <Image
          source={{ uri: `data/live/app/assets/skills/${icon}.png` }}
          style={[styles.image, { tintColor: color }]}
        />
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    borderWidth: 1,
    borderRadius: 8,
    overflow: 'hidden',
  },
  box: {
    width: 80,
    alignItems: 'center',
    justifyContent: 'center',
  },
  image: {
    width: 40,
    height: 40,
  },
});

export default BuildItem;


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