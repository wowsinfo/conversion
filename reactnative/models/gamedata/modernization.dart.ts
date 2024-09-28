
interface ModernizationProps {
  slot: string;
  id: string;
  costCR: number;
  name: string;
  icon: string;
  description: string;
  level?: number;
  type?: string;
  nation?: string;
  modifiers: Modifier[];
  ships?: Ship[];
  excludes?: string[];
  special?: boolean;
  unique?: boolean;
}

const Modernization: React.FC<ModernizationProps> = ({
  slot,
  id,
  costCR,
  name,
  icon,
  description,
  level,
  type,
  nation,
  modifiers,
  ships,
  excludes,
  special,
  unique,
}) => {
  return (
    <View>
      <Text>{name}</Text>
      <Image source={{ uri: icon }} style={{ width: 50, height: 50 }} />
      <Text>{description}</Text>
      <Text>Cost: {costCR} CR</Text>
      {level && <Text>Level: {level}</Text>}
      {type && <Text>Type: {type}</Text>}
      {nation && <Text>Nation: {nation}</Text>}
      {modifiers && modifiers.map((modifier, index) => (
        <Text key={index}>{modifier.name}</Text>
      ))}
      {ships && ships.map((ship, index) => (
        <Text key={index}>{ship.name}</Text>
      ))}
      {excludes && excludes.map((exclude, index) => (
        <Text key={index}>{exclude}</Text>
      ))}
      {special && <Text>Special: Yes</Text>}
      {unique && <Text>Unique: Yes</Text>}
    </View>
  );
};

export default observer(Modernization);

interface Modifiers {
  // Define the structure of Modifiers based on your requirements
}

class GameRepository {
  static instance = new GameRepository();

  shipNameOf(id: string): string | null {
    // Implement the logic to get the ship name by id
    return null; // Placeholder return
  }
}

class Upgrade {
  slot: number;
  id: number;
  costCR: number;
  name: string;
  icon: string;
  description: string;
  level?: number[];
  type?: string[];
  nation?: string[];
  modifiers: Modifiers;
  ships?: number[];
  excludes?: number[];
  special?: boolean;
  unique?: boolean;

  constructor(
    slot: number,
    id: number,
    costCR: number,
    name: string,
    icon: string,
    description: string,
    modifiers: Modifiers,
    level?: number[],
    type?: string[],
    nation?: string[],
    ships?: number[],
    excludes?: number[],
    special?: boolean,
    unique?: boolean
  ) {
    this.slot = slot;
    this.id = id;
    this.costCR = costCR;
    this.name = name;
    this.icon = icon;
    this.description = description;
    this.modifiers = modifiers;
    this.level = level;
    this.type = type;
    this.nation = nation;
    this.ships = ships;
    this.excludes = excludes;
    this.special = special;
    this.unique = unique;
  }

  toString(): string {
    const description = JSON.stringify(this.modifiers); // Adjust based on Modifiers structure
    if (this.unique && this.ships) {
      const shipName = GameRepository.instance.shipNameOf(this.ships[0].toString());
      if (shipName) {
        return `${description}\n${shipName}`;
      }
    }
    return description;
  }
}


interface Props {
    name: string;
    slot: number;
}

const UpgradeTitle: React.FC<Props> = ({ name, slot }) => {
    const upgradeName = Localisation.instance.stringOf(name);
    if (upgradeName == null) return <Text></Text>;
    return <Text>{`${upgradeName} [${slot + 1}]`}</Text>;
};

export default UpgradeTitle;

    special: any;
    unique: any;
    id: number;

    constructor(special: any, unique: any, id: number) {
        this.special = special;
        this.unique = unique;
        this.id = id;
    }

    greater(other: Modernization): number {
        if (this.special === other.special && this.unique === other.unique) {
            return this.id - other.id;
        }

        if (this.special === null && this.unique === null) return 2;
        if (this.unique === null && other.unique !== null) return 1;
        return -1;
    }
}

type Ship = {
    id: string;
    region: string;
    type: string;
    tier: string;
};

class ShipFilter {
    private ships?: string[];
    private excludes?: string[];
    private nation?: string[];
    private type?: string[];
    private level?: string[];

    constructor(ships?: string[], excludes?: string[], nation?: string[], type?: string[], level?: string[]) {
        this.ships = ships;
        this.excludes = excludes;
        this.nation = nation;
        this.type = type;
        this.level = level;
    }

    isFor(ship: Ship): boolean {
        const shipId = ship.id;

        if (this.ships && this.ships.includes(shipId)) {
            return true;
        }

        if (this.excludes && this.excludes.includes(shipId)) {
            return false;
        }

        const region = this.nation || [];
        const type = this.type || [];
        const tier = this.level || [];

        if (!region.includes(ship.region)) return false;
        if (!type.includes(ship.type)) return false;
        if (!tier.includes(ship.tier)) return false;

        return true;
    }
}

interface Modifiers {
    // Define the structure of Modifiers based on your requirements
}

interface Modernization {
    slot: string;
    id: string;
    costCR: number;
    name: string;
    icon: string;
    description: string;
    level: number[] | null;
    type: string[] | null;
    nation: string[] | null;
    modifiers: Modifiers;
    ships: number[] | null;
    excludes: number[] | null;
    special: any; // Define the type based on your requirements
    unique: any; // Define the type based on your requirements
}

const fromJson = (json: any): Modernization => {
    return {
        slot: json.slot,
        id: json.id,
        costCR: json.costCR,
        name: json.name,
        icon: json.icon,
        description: json.description,
        level: json.level ? Array.from(json.level) : null,
        type: json.type ? Array.from(json.type) : null,
        nation: json.nation ? Array.from(json.nation) : null,
        modifiers: fromModifiersJson(json.modifiers),
        ships: json.ships ? Array.from(json.ships) : null,
        excludes: json.excludes ? Array.from(json.excludes) : null,
        special: json.special,
        unique: json.unique,
    };
};

const fromModifiersJson = (json: any): Modifiers => {
    // Implement the conversion logic for Modifiers based on your requirements
    return {} as Modifiers; // Replace with actual implementation
};