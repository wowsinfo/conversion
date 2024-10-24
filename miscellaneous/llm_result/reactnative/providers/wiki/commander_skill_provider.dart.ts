
const _maxPoints = 21;

const CommanderSkillContext = createContext<any>(null);

export const CommanderSkillProvider: React.FC<{ type?: CommanderSkillType }> = ({ type, children }) => {
  const logger = new Logger('CommanderSkillProvider');
  const [skills, setSkills] = useState<List<List<ShipSkill>>>([]);

  useEffect(() => {
    const loadSkills = async () => {
      const loadedSkills = await GameRepository.instance.commandSkillsOf(type ?? CommanderSkillType.destroyer);
      setSkills(loadedSkills);
    };
    loadSkills();
  }, [type]);

  return (
    <CommanderSkillContext.Provider value={{ skills }}>
      {children}
    </CommanderSkillContext.Provider>
  );
};

export const useCommanderSkill = () => {
  const context = useContext(CommanderSkillContext);
  if (!context) {
    throw new Error('useCommanderSkill must be used within a CommanderSkillProvider');
  }
  return context;
};

type ShipSkill = any; // Define the ShipSkill type according to your needs
enum CommanderSkillType {
    airCarrier,
    battleship,
    cruiser,
    destroyer,
    submarine
}

const airCarrier = 'airCarrier';
const battleship = 'battleship';
const cruiser = 'cruiser';
const destroyer = 'destroyer';
const submarine = 'submarine';

function _getSkillsFrom(type: CommanderSkillType): Array<Array<ShipSkill>> {
    // Implement the logic to get skills based on the CommanderSkillType
    return []; // Replace with actual implementation
}

function _getSkillsFromTab(tab: string): Array<Array<ShipSkill>> {
    if (tab === airCarrier) return _getSkillsFrom(CommanderSkillType.airCarrier);
    if (tab === battleship) return _getSkillsFrom(CommanderSkillType.battleship);
    if (tab === cruiser) return _getSkillsFrom(CommanderSkillType.cruiser);
    if (tab === destroyer) return _getSkillsFrom(CommanderSkillType.destroyer);
    if (tab === submarine) return _getSkillsFrom(CommanderSkillType.submarine);
    throw new Error(`Unknown tab: ${tab}`);
}


const MyComponent = () => {
  const airCarrier = Localisation.instance.airCarrier;
  const destroyer = Localisation.instance.destroyer;
  const cruiser = Localisation.instance.cruiser;
  const battleship = Localisation.instance.battleship;
  const submarine = Localisation.instance.submarine;

  const [selectedTab, setSelectedTab] = useState(destroyer);
  const [selectedPoints, setSelectedPoints] = useState(0);
  const [selectedSkills, setSelectedSkills] = useState<string[]>([]);
  const [skills, setSkills] = useState<string[]>([]);

  const select = (tab: string) => {
    console.log(`Selected tab: ${tab}`);
    setSelectedTab(tab);
    setSelectedPoints(0);
    setSelectedSkills([]);
    setSkills(getSkillsFromTab(tab));
  };

  const getSkillsFromTab = (tab: string) => {
    // Implement your logic to get skills based on the tab
    return []; // Replace with actual skills fetching logic
  };

  useEffect(() => {
    // Any side effects or additional logic can be handled here
  }, [selectedTab]);

  return (
    <View>
      <Text>Selected Tab: {selectedTab}</Text>
      {/* Render your UI components here */}
    </View>
  );
};

export default MyComponent;


interface ShipSkill {
  name: string;
}

const YourComponent: React.FC = () => {
  const [selectedPoints, setSelectedPoints] = useState(0);
  const [selectedSkills, setSelectedSkills] = useState<ShipSkill[]>([]);

  const selectedDescriptions = () => {
    if (selectedSkills.length === 0) return '';

    let additionalDescription = '';
    const skillDescription = selectedSkills
      .map((skill) => {
        const commanderSkill = GameRepository.instance.skillOf(skill.name);
        const passiveSkill = commanderSkill?.skillDescription;
        if (passiveSkill) {
          additionalDescription += `\n${passiveSkill}`;
        }
        if (!commanderSkill) return null;
        return commanderSkill.modifiers.merge(
          commanderSkill.logicTrigger.modifiers,
        );
      })
      .filter((skill) => skill !== null)
      .reduce((prev, curr) => prev?.merge(curr), null)
      ?.toString()
      .split('\n')
      .filter((line) =>
        !line.includes(')') || (line.includes(')') && line.includes(selectedTab))
      )
      .join('\n');

    additionalDescription = additionalDescription.replace(/\n\n/g, '\n').trim();
    return `${additionalDescription}\n\n${skillDescription}`;
  };

  return (
    <div>
      {/* Render your component UI here */}
    </div>
  );
};

export default YourComponent;


interface ShipSkill {
  tier: number;
}

const useSkillSelection = (maxPoints: number) => {
  const [selectedSkills, setSelectedSkills] = useState<ShipSkill[]>([]);
  const [selectedPoints, setSelectedPoints] = useState<number>(0);
  const logger = useLogger();

  const isSkillSelected = (skill: ShipSkill): boolean => {
    return selectedSkills.includes(skill);
  };

  const selectSkill = (skill: ShipSkill) => {
    if (selectedSkills.includes(skill)) {
      logger.fine(`Skill already selected: ${skill}`);
      // remove from the selection
      const updatedSkills = selectedSkills.filter(s => s !== skill);
      // if this is the only skill selected for the tier, remove everything below it
      if (!updatedSkills.some(s => s.tier === skill.tier)) {
        setSelectedSkills(updatedSkills.filter(s => s.tier <= skill.tier));
      } else {
        setSelectedSkills(updatedSkills);
      }
      // update the points
      const points = updatedSkills.reduce((prev, curr) => prev + curr.tier, 0);
      setSelectedPoints(points);
      return;
    }

    const tier = skill.tier;
    if (tier + selectedPoints > maxPoints) {
      logger.fine('More than max points');
      return;
    }

    if (selectedPoints === 0 && tier > 1) {
      logger.fine('Tier too high');
      return;
    }

    // tier 1 can always be selected
    if (selectedPoints > 0 && tier > 1) {
      // only select higher tiers if there is at least one lower tier selected
      if (!selectedSkills.some(s => s.tier === tier - 1)) {
        logger.fine(`${skill} cannot be selected`);
        return;
      }
    }

    logger.fine(`Selected skill: ${skill}`);
    setSelectedSkills([...selectedSkills, skill]);
    setSelectedPoints(prevPoints => prevPoints + skill.tier);
    logger.fine(`Selected points: ${selectedPoints + skill.tier}`);
  };

  return {
    selectedSkills,
    selectedPoints,
    isSkillSelected,
    selectSkill,
  };
};

export default useSkillSelection;


const MyComponent: React.FC = () => {
    const [selectedPoints, setSelectedPoints] = useState(0);
    const [selectedSkills, setSelectedSkills] = useState<string[]>([]);

    const reset = () => {
        setSelectedPoints(0);
        setSelectedSkills([]);
    };

    return (
        <div>
            {/* Your component UI goes here */}
            <button onClick={reset}>Reset</button>
        </div>
    );
};

export default MyComponent;


interface ShipSkill {
  name: string;
}

const onLongPress = (skill: ShipSkill) => {
  // Show the information of the skill
  const commanderSkill = GameRepository.instance.skillOf(skill.name);
  if (!commanderSkill) {
    console.error(`Skill not found: ${skill.name}`);
    return;
  }

  const skillName = Localisation.instance.stringOf(commanderSkill.name);
  
  Alert.alert(
    skillName || '-',
    commanderSkill.fullDescriptions,
    [
      {
        text: 'Close',
        onPress: () => console.log('Close Pressed'),
      },
    ],
    { cancelable: true }
  );
};

const SkillComponent = ({ skill }: { skill: ShipSkill }) => {
  return (
    <View>
      <Button title="Long Press Me" onPress={() => onLongPress(skill)} />
    </View>
  );
};

export default SkillComponent;


interface PointsProps {
  selectedPoints: number;
  maxPoints: number;
}

const PointsInfo: React.FC<PointsProps> = ({ selectedPoints, maxPoints }) => {
  const getSelectedPoints = () => selectedPoints.toString();
  const getTotalPoints = () => maxPoints.toString();
  const getPointInfo = () => `${getSelectedPoints()} / ${getTotalPoints()}`;

  return (
    <View>
      <Text>{getPointInfo()}</Text>
    </View>
  );
};

export default PointsInfo;