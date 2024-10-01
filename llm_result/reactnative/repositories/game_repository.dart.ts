
const logger = new Logger('GameRepository');

export const useGameRepository = () => {
  const [initialised, setInitialised] = useState(false);
  const [data, setData] = useState({
    alias: {},
    abilities: {},
    achievements: {},
    aircrafts: {},
    exteriors: {},
    modernizations: {},
    projectiles: {},
    ships: {},
    skills: {},
    shipAdditionals: {},
    gameInfo: null,
    commandSkills: null,
  });

  useEffect(() => {
    const initialise = async () => {
      if (initialised) {
        logger.error('GameRepository already initialised');
        Alert.alert('Error', 'GameRepository is already initialised');
        return;
      }

      const timer = new TimeTracker();

      try {
        const jsonString = await readFile('data/live/app/data/wowsinfo.json', 'utf8');
        timer.log('Loaded wowsinfo.json');

        const dataObject = JSON.parse(jsonString);
        timer.log('Parsed wowsinfo.json');

        const alias = Object.fromEntries(
          Object.entries(dataObject.alias).map(([key, value]) => [key, Alias.fromJson(value)])
        );
        const abilities = Object.fromEntries(
          Object.entries(dataObject.abilities).map(([key, value]) => [key, Ability.fromJson(value)])
        );
        const achievements = Object.fromEntries(
          Object.entries(dataObject.achievements).map(([key, value]) => [key, Achievement.fromJson(value)])
        );
        const aircrafts = Object.fromEntries(
          Object.entries(dataObject.aircrafts).map(([key, value]) => [key, Aircraft.fromJson(value)])
        );
        const exteriors = Object.fromEntries(
          Object.entries(dataObject.exteriors).map(([key, value]) => [key, Exterior.fromJson(value)])
        );
        const modernizations = Object.fromEntries(
          Object.entries(dataObject.modernizations).map(([key, value]) => [key, Modernization.fromJson(value)])
        );
        const projectiles = Object.fromEntries(
          Object.entries(dataObject.projectiles).map(([key, value]) => [key, Projectile.fromJson(value)])
        );
        const ships = Object.fromEntries(
          Object.entries(dataObject.ships).map(([key, value]) => [key, Ship.fromJson(value)])
        );
        const skills = Object.fromEntries(
          Object.entries(dataObject.skills).map(([key, value]) => [key, CommanderSkill.fromJson(value)])
        );
        const shipAdditionals = Object.fromEntries(
          Object.entries(dataObject.number).map(([key, value]) => [key, ShipAdditional.fromJson(value)])
        );
        const gameInfo = GameInfo.fromJson(dataObject.game);
        timer.log('Decoded wowsinfo.json');

        const commanderSkillsString = await readFile('assets/skills.json', 'utf8');
        timer.log('Loaded skills.json');
        const commandSkills = CommandSkillInfo.fromJson(JSON.parse(commanderSkillsString));
        timer.log('Decoded skills.json');

        setData({
          alias,
          abilities,
          achievements,
          aircrafts,
          exteriors,
          modernizations,
          projectiles,
          ships,
          skills,
          shipAdditionals,
          gameInfo,
          commandSkills,
        });

        setInitialised(true);
        timer.log('Initialised GameRepository');
      } catch (error) {
        logger.error('Error initializing GameRepository', error);
        Alert.alert('Error', 'Failed to initialize GameRepository');
      }
    };

    initialise();
  }, [initialised]);

  return data;
};


const useGameData = (_achievements, _abilities, _exteriors, _modernizations, _ships, _gameInfo) => {
    const [achievementList, setAchievementList] = useState([]);
    const [consumableList, setConsumableList] = useState([]);
    const [exteriorList, setExteriorList] = useState([]);
    const [modernizationList, setModernizationList] = useState([]);
    const [shipList, setShipList] = useState([]);
    const [shipRegionList, setShipRegionList] = useState([]);
    const [shipTypeList, setShipTypeList] = useState([]);
    const [usefulFlagList, setUsefulFlagList] = useState([]);

    useEffect(() => {
        const generateLists = () => {
            // sort achievements by id
            const sortedAchievements = Object.values(_achievements).sort((a, b) => b.id - a.id);
            setAchievementList(sortedAchievements);

            // sort consumables by id
            const sortedConsumables = Object.values(_abilities).sort((a, b) => b.id - a.id);
            setConsumableList(sortedConsumables);

            // sort exteriors by id
            const sortedExteriors = Object.values(_exteriors).sort((a, b) => b.id - a.id);
            setExteriorList(sortedExteriors);

            // sort modernizations by id
            const sortedModernizations = Object.values(_modernizations).sort((a, b) => b.greater(a));
            setModernizationList(sortedModernizations);

            // sort ships by id
            const sortedShips = Object.values(_ships).sort((a, b) => b.greater(a));
            setShipList(sortedShips);

            // sort only to make sure they are the same
            const sortedRegions = [..._gameInfo.regions].sort();
            setShipRegionList(sortedRegions);
            const sortedTypes = [..._gameInfo.types].sort();
            setShipTypeList(sortedTypes);

            // get all flags which have a modifier
            const flagsWithModifiers = sortedExteriors.filter(e => e.isFlag && (e.modifiers?.length > 0));
            const sortedFlags = flagsWithModifiers.sort((a, b) => b.id - a.id);
            setUsefulFlagList(sortedFlags);
        };

        generateLists();
    }, [_achievements, _abilities, _exteriors, _modernizations, _ships, _gameInfo]);

    return {
        achievementList,
        consumableList,
        exteriorList,
        modernizationList,
        shipList,
        shipRegionList,
        shipTypeList,
        usefulFlagList,
    };
};

export default useGameData;

  private _alias: { [key: string]: { alias: string } } = {};

  // Get an alias string by its key.
  // If the alias is not found, it will return null
  aliasOf(key: string): string | null {
    return this._alias[key]?.alias || null;
  }
}

type Ability = {
  // Define the properties of Ability here
};

class AbilityManager {
  private abilities: { [key: string]: Ability | undefined } = {};

  // Get an ability by its key.
  // If the ability is not found, it will return null
  abilityOf(key: string): Ability | null {
    return this.abilities[key] || null;
  }
}

type Achievement = {
  // Define the properties of Achievement here
};

class AchievementManager {
  private achievements: { [key: string]: Achievement | undefined } = {};

  // Get an achievement by its key.
  // If the achievement is not found, it will return null
  achievementOf(key: string): Achievement | null {
    return this.achievements[key] || null;
  }
}

interface Aircraft {
  // Define the properties of the Aircraft interface here
}

class AircraftManager {
  private aircrafts: { [key: string]: Aircraft | undefined } = {};

  // Get an aircraft by its key.
  // If the aircraft is not found, it will return null
  aircraftOf(key: string): Aircraft | null {
    return this.aircrafts[key] || null;
  }
}

type Flag = {
  type: string;
  // other properties of Flag can be defined here
};

class FlagManager {
  private exteriors: { [key: string]: Flag | undefined } = {};

  // Get a flag by its key.
  // If the flag is not found, it will return null
  flagOf(key: string): Flag | null {
    const flag = this.exteriors[key];
    if (flag == null) return null;
    // make sure this is a flag not a camouflage
    if (flag.type !== 'Flags') {
      throw new Error(`${key} is not a flag`);
    }

    return flag;
  }
}

type Camouflage = {
  type: string;
  // other properties can be added as needed
};

class CamouflageManager {
  private exteriors: { [key: string]: Camouflage } = {};

  constructor(exteriors: { [key: string]: Camouflage }) {
    this.exteriors = exteriors;
  }

  camouflageOf(key: string): Camouflage | null {
    const camouflage = this.exteriors[key];
    if (camouflage == null) return null;
    // make sure this is a camouflage not a flag
    if (camouflage.type === 'Flags') {
      throw new Error(`${key} is not a camouflage`);
    }

    return camouflage;
  }
}

  // Define the properties of the Modernization type here
};

class ModernizationManager {
  private modernizations: { [key: string]: Modernization | undefined } = {};

  // Get a modernization by its key.
  // If the modernization is not found, it will return null
  modernizationOf(key: string): Modernization | null {
    return this.modernizations[key] || null;
  }
}


interface Projectile {
  // Define the properties of the Projectile interface here
}

class ProjectileManager extends React.Component {
  private _projectiles: { [key: string]: Projectile | undefined } = {};

  // Get a projectile by its key.
  // If the projectile is not found, it will return null
  projectileOf(key: string): Projectile | null {
    return this._projectiles[key] || null;
  }
}

interface Ship {
  // Define the properties of the Ship interface here
}

class ShipManager {
  private ships: Record<string, Ship> = {};

  // Get a ship by its id.
  // If the ship is not found, it will return null
  shipOf(id: string): Ship | null {
    // TODO: we can check if this id is numeric
    return this.ships[id] || null;
  }
}


function shipOf(id: string): Ship | null {
    // Implement the logic to retrieve the ship based on the id
    // This is a placeholder for the actual implementation
    return null; // Replace with actual ship retrieval logic
}

interface Ship {
    name: string;
}

function shipNameOf(id: string): string | null {
    const ship = shipOf(id);
    if (ship === null) return null;
    return Localisation.instance.stringOf(ship.name);
}

interface ShipAdditional {
    // Define the properties of ShipAdditional here
}

class ShipAdditionalService {
    private shipAdditionals: { [key: string]: ShipAdditional } = {};

    constructor() {
        // Initialize shipAdditionals if needed
    }

    shipAdditionalOf(id: string): ShipAdditional | undefined {
        return this.shipAdditionals[id];
    }
}


type ShipSkill = any; // Define the ShipSkill type according to your needs
type CommanderSkillType = {
  rawName: string;
};

interface CommandSkills {
  shipTypes: Record<string, Array<Array<ShipSkill>>>;
}

class CommanderSkills {
  private _commandSkills: CommandSkills;

  constructor(commandSkills: CommandSkills) {
    this._commandSkills = commandSkills;
  }

  get commanderSkills(): Record<string, Array<Array<ShipSkill>>> {
    return this._commandSkills.shipTypes;
  }

  commandSkillsOf(type: CommanderSkillType): Array<Array<ShipSkill>> {
    const skills = this._commandSkills.shipTypes[type.rawName];
    if (!skills) throw new Error(`No skills for ${type.rawName}`);
    return skills;
  }
}

    // Define the properties of CommanderSkill here
};

class SkillManager {
    private skills: { [key: string]: CommanderSkill };

    constructor() {
        this.skills = {};
    }

    skillOf(name: string): CommanderSkill | undefined {
        return this.skills[name];
    }

    // Additional methods to manage skills can be added here
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