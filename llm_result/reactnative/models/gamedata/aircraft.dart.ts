
interface Consumable {
  // Define the properties of Consumable here
}

interface AircraftProps {
  type: string;
  nation: string;
  name: string;
  health: number;
  totalPlanes: number;
  visibility: number;
  speed: number;
  aircraft: Consumable;
}

const Aircraft: React.FC<AircraftProps> = ({
  type,
  nation,
  name,
  health,
  totalPlanes,
  visibility,
  speed,
  aircraft,
}) => {
  return (
    <div>
      <h1>{name}</h1>
      <p>Type: {type}</p>
      <p>Nation: {nation}</p>
      <p>Health: {health}</p>
      <p>Total Planes: {totalPlanes}</p>
      <p>Visibility: {visibility}</p>
      <p>Speed: {speed}</p>
      {/* Render aircraft details here */}
    </div>
  );
};

export default Aircraft;


interface AircraftInfo {
  restoreTime: number;
  maxAircraft: number;
  attacker: boolean;
  attackCount: number;
  cooldown: number;
  minSpeed: number;
  maxSpeed: number;
  boostTime: number;
  boostReload: number;
  bombName: string;
  consumables: any; // Define the type based on your requirements
}

interface Aircraft {
  type: string;
  nation: string;
  name: string;
  health?: number;
  totalPlanes?: number;
  visibility?: number;
  speed?: number;
  aircraft?: AircraftInfo | null;
}

const AircraftComponent: React.FC<{ aircraft: Aircraft }> = ({ aircraft }) => {
  return (
    <View>
      <Text>Type: {aircraft.type}</Text>
      <Text>Nation: {aircraft.nation}</Text>
      <Text>Name: {aircraft.name}</Text>
      {aircraft.health !== undefined && <Text>Health: {aircraft.health}</Text>}
      {aircraft.totalPlanes !== undefined && <Text>Total Planes: {aircraft.totalPlanes}</Text>}
      {aircraft.visibility !== undefined && <Text>Visibility: {aircraft.visibility}</Text>}
      {aircraft.speed !== undefined && <Text>Speed: {aircraft.speed}</Text>}
      {aircraft.aircraft && (
        <View>
          <Text>Restore Time: {aircraft.aircraft.restoreTime}</Text>
          <Text>Max Aircraft: {aircraft.aircraft.maxAircraft}</Text>
          <Text>Attacker: {aircraft.aircraft.attacker ? 'Yes' : 'No'}</Text>
          <Text>Attack Count: {aircraft.aircraft.attackCount}</Text>
          <Text>Cooldown: {aircraft.aircraft.cooldown}</Text>
          <Text>Min Speed: {aircraft.aircraft.minSpeed}</Text>
          <Text>Max Speed: {aircraft.aircraft.maxSpeed}</Text>
          <Text>Boost Time: {aircraft.aircraft.boostTime}</Text>
          <Text>Boost Reload: {aircraft.aircraft.boostReload}</Text>
          <Text>Bomb Name: {aircraft.aircraft.bombName}</Text>
          <Text>Consumables: {JSON.stringify(aircraft.aircraft.consumables)}</Text>
        </View>
      )}
    </View>
  );
};

const fromJson = (json: any): Aircraft => ({
  type: json.type,
  nation: json.nation,
  name: json.name,
  health: json.health,
  totalPlanes: json.totalPlanes,
  visibility: json.visibility,
  speed: json.speed,
  aircraft: json.aircraft ? {
    restoreTime: json.aircraft.restoreTime,
    maxAircraft: json.aircraft.maxAircraft,
    attacker: json.aircraft.attacker,
    attackCount: json.aircraft.attackCount,
    cooldown: json.aircraft.cooldown,
    minSpeed: json.aircraft.minSpeed,
    maxSpeed: json.aircraft.maxSpeed,
    boostTime: json.aircraft.boostTime,
    boostReload: json.aircraft.boostReload,
    bombName: json.aircraft.bombName,
    consumables: json.aircraft.consumables,
  } : null,
});

export { AircraftComponent, fromJson };

interface Consumable {
  // Define the properties of Consumable based on your requirements
}

interface AircraftInfo {
  restoreTime: number;
  maxAircraft: number;
  attacker: number;
  attackCount: number;
  cooldown: number;
  minSpeed: number;
  maxSpeed: number;
  boostTime: number;
  boostReload?: number;
  bombName: string;
  consumables?: Consumable[][];

  static fromJson(json: any): AircraftInfo {
    return {
      restoreTime: json.restoreTime,
      maxAircraft: json.maxAircraft,
      attacker: json.attacker,
      attackCount: json.attackCount,
      cooldown: Number(json.cooldown),
      minSpeed: Number(json.minSpeed),
      maxSpeed: Number(json.maxSpeed),
      boostTime: json.boostTime,
      boostReload: json.boostReload,
      bombName: json.bombName,
      consumables: json.consumables == null
        ? undefined
        : json.consumables.map((x: any) => 
            x.map((y: any) => Consumable.fromJson(y))
          ),
    };
  }
}