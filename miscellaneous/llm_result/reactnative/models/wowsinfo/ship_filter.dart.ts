
interface ShipFilterInterface {
  name: string;
  tier: number;
  region: string;
  type: string;
}

class ShipFilter implements ShipFilterInterface {
  name: string;
  tiers: number[];
  regions: string[];
  types: string[];

  constructor(name: string, tiers: number[], regions: string[], types: string[]) {
    if (tiers.length === 0 || regions.length === 0 || types.length === 0) {
      throw new Error("Empty lists are not allowed");
    }
    this.name = name;
    this.tiers = tiers;
    this.regions = regions;
    this.types = types;
    makeAutoObservable(this);
  }

  get tier(): number {
    return Math.min(...this.tiers);
  }

  get region(): string {
    return this.regions.join(", ");
  }

  get type(): string {
    return this.types.join(", ");
  }

  @action
  updateName(newName: string) {
    this.name = newName;
  }

  @action
  updateTiers(newTiers: number[]) {
    if (newTiers.length === 0) {
      throw new Error("Empty lists are not allowed");
    }
    this.tiers = newTiers;
  }

  @action
  updateRegions(newRegions: string[]) {
    if (newRegions.length === 0) {
      throw new Error("Empty lists are not allowed");
    }
    this.regions = newRegions;
  }

  @action
  updateTypes(newTypes: string[]) {
    if (newTypes.length === 0) {
      throw new Error("Empty lists are not allowed");
    }
    this.types = newTypes;
  }
}

export default ShipFilter;


interface ShipFilterProps {
  name: string;
  tiers: number[];
  regions: string[];
  types: string[];
}

class ShipFilter extends React.Component<ShipFilterProps> {
  private logger = new Logger('ShipFilter');

  constructor(props: ShipFilterProps) {
    super(props);
  }

  get isEmpty(): boolean {
    return this.props.name.trim().length === 0 &&
           this.props.tiers.length === 0 &&
           this.props.regions.length === 0 &&
           this.props.types.length === 0;
  }

  render() {
    // Render logic here
    return null; // Replace with actual rendering logic
  }
}

export default ShipFilter;


interface ShipFilterInterface {
  name: string;
  tier: string;
  region: string;
  type: string;
}

class ShipFilter {
  private name: string;
  private tiers: string[];
  private regions: string[];
  private types: string[];
  private logger: Logger;

  constructor(name: string, tiers: string[], regions: string[], types: string[], logger: Logger) {
    this.name = name;
    this.tiers = tiers;
    this.regions = regions;
    this.types = types;
    this.logger = logger;
  }

  shouldDisplay(ship: ShipFilterInterface): boolean {
    if (this.name.trim().length > 0) {
      const shipName = Localisation.instance.stringOf(ship.name);
      if (shipName === null) {
        this.logger.severe(`${ship.name} is invalid!`);
        throw new Error(`Failed to get ship name: ${ship.name}`);
      }

      if (!shipName.toLowerCase().includes(this.name.toLowerCase())) {
        return false;
      }
    }

    if (this.tiers.length > 0 && !this.tiers.includes(ship.tier)) {
      return false;
    }
    if (this.regions.length > 0 && !this.regions.includes(ship.region)) {
      return false;
    }
    if (this.types.length > 0 && !this.types.includes(ship.type)) {
      return false;
    }

    return true;
  }
}

  name: string;
  tiers: string[];
  regions: string[];
  types: string[];

  constructor(name: string, tiers: string[], regions: string[], types: string[]) {
    this.name = name;
    this.tiers = tiers;
    this.regions = regions;
    this.types = types;
  }

  toString(): string {
    return `ShipFilter{shipName: ${this.name}, tierList: ${this.tiers}, regionList: ${this.regions}, typeList: ${this.types}}`;
  }
}


interface MyComponentProps {
  name: string;
  tiers: any[];
  regions: any[];
  types: any[];
}

const MyComponent: React.FC<PropsWithChildren<MyComponentProps>> = ({ name, tiers, regions, types }) => {
  const props = useMemo(() => [name, tiers, regions, types], [name, tiers, regions, types]);

  return (
    <View>
      <Text>{name}</Text>
      {/* Render tiers, regions, and types as needed */}
    </View>
  );
};

export default MyComponent;