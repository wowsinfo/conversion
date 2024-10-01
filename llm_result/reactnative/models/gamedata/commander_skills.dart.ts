enum CommanderSkillType {
  AirCarrier = 'airCarrier',
  Battleship = 'battleship',
  Cruiser = 'cruiser',
  Destroyer = 'destroyer',
  Submarine = 'submarine',
}

class CommandSkillInfo {
  airCarrier: boolean;
  battleship: boolean;
  cruiser: boolean;
  destroyer: boolean;
  submarine: boolean;
  shipTypes: CommanderSkillType[];

  constructor(
    airCarrier: boolean,
    battleship: boolean,
    cruiser: boolean,
    destroyer: boolean,
    submarine: boolean,
    shipTypes: CommanderSkillType[],
  ) {
    this.airCarrier = airCarrier;
    this.battleship = battleship;
    this.cruiser = cruiser;
    this.destroyer = destroyer;
    this.submarine = submarine;
    this.shipTypes = shipTypes;
  }

  get rawName(): string {
    return this.constructor.name.charAt(0).toUpperCase() + this.constructor.name.slice(1);
  }

  get langName(): string {
    return this.constructor.name.toUpperCase();
  }
}


interface ShipSkill {
  name: string;
  tier: number;
  column: number;
}

interface CommandSkillInfo {
  airCarrier: ShipSkill[][];
  battleship: ShipSkill[][];
  cruiser: ShipSkill[][];
  destroyer: ShipSkill[][];
  submarine: ShipSkill[][];
  shipTypes: Record<string, ShipSkill[][]>;
}

const CommandSkillInfo = {
  fromJson: (json: any): CommandSkillInfo => ({
    airCarrier: json.AirCarrier.map((x: any) => x.map((y: any) => ShipSkill.fromJson(y))),
    battleship: json.Battleship.map((x: any) => x.map((y: any) => ShipSkill.fromJson(y))),
    cruiser: json.Cruiser.map((x: any) => x.map((y: any) => ShipSkill.fromJson(y))),
    destroyer: json.Destroyer.map((x: any) => x.map((y: any) => ShipSkill.fromJson(y))),
    submarine: json.Submarine.map((x: any) => x.map((y: any) => ShipSkill.fromJson(y))),
    shipTypes: Object.fromEntries(
      Object.entries(json).map(([key, value]) => [
        key,
        value.map((x: any) => x.map((y: any) => ShipSkill.fromJson(y))),
      ])
    ),
  }),
};

const ShipSkill = {
  fromJson: (json: any): ShipSkill => ({
    name: json.name,
    tier: json.tier,
    column: json.column,
  }),
};

const App = () => {
  return (
    <View>
      <Text>Command Skill Info</Text>
    </View>
  );
};

export default App;

interface ShipSkill {
  name: string;
  tier: number;
  column: number;
}

const ShipSkill = {
  fromJson(json: any): ShipSkill {
    return {
      name: json.name,
      tier: json.tier,
      column: json.column,
    };
  },

  toJson(shipSkill: ShipSkill): any {
    return {
      name: shipSkill.name,
      tier: shipSkill.tier,
      column: shipSkill.column,
    };
  },

  toString(shipSkill: ShipSkill): string {
    return `ShipSkill{name: ${shipSkill.name}, tier: ${shipSkill.tier}, column: ${shipSkill.column}}`;
  },
};

export default ShipSkill;