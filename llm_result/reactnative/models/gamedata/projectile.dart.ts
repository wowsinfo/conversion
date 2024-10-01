
interface ProjectileProps {
  type: string;
  nation: string;
  name: string;
  weight?: number;
  ammoType?: string;
  speed?: number;
  damage?: number;
  ricochetAngle?: number;
  ricochetAlways?: boolean;
  diameter?: number;
  ap?: number;
  overmatch?: number;
  fuseTime?: number;
  penHe?: number;
  burnChance?: number;
  visibility?: number;
  range?: number;
  floodChance?: number;
  alphaDamage?: number;
  deepWater?: boolean;
  penSAP?: number;
  ignoreClasses?: string[];
}

const Projectile: React.FC<ProjectileProps> = ({
  type,
  nation,
  name,
  weight,
  ammoType,
  speed,
  damage,
  ricochetAngle,
  ricochetAlways,
  diameter,
  ap,
  overmatch,
  fuseTime,
  penHe,
  burnChance,
  visibility,
  range,
  floodChance,
  alphaDamage,
  deepWater,
  penSAP,
  ignoreClasses,
}) => {
  return (
    <View>
      <Text>Type: {type}</Text>
      <Text>Nation: {nation}</Text>
      <Text>Name: {name}</Text>
      {weight && <Text>Weight: {weight}</Text>}
      {ammoType && <Text>Ammo Type: {ammoType}</Text>}
      {speed && <Text>Speed: {speed}</Text>}
      {damage && <Text>Damage: {damage}</Text>}
      {ricochetAngle && <Text>Ricochet Angle: {ricochetAngle}</Text>}
      {ricochetAlways !== undefined && <Text>Ricochet Always: {ricochetAlways ? 'Yes' : 'No'}</Text>}
      {diameter && <Text>Diameter: {diameter}</Text>}
      {ap && <Text>AP: {ap}</Text>}
      {overmatch && <Text>Overmatch: {overmatch}</Text>}
      {fuseTime && <Text>Fuse Time: {fuseTime}</Text>}
      {penHe && <Text>PEN HE: {penHe}</Text>}
      {burnChance && <Text>Burn Chance: {burnChance}</Text>}
      {visibility && <Text>Visibility: {visibility}</Text>}
      {range && <Text>Range: {range}</Text>}
      {floodChance && <Text>Flood Chance: {floodChance}</Text>}
      {alphaDamage && <Text>Alpha Damage: {alphaDamage}</Text>}
      {deepWater !== undefined && <Text>Deep Water: {deepWater ? 'Yes' : 'No'}</Text>}
      {penSAP && <Text>PEN SAP: {penSAP}</Text>}
      {ignoreClasses && <Text>Ignore Classes: {ignoreClasses.join(', ')}</Text>}
    </View>
  );
};

export default Projectile;


interface ArmorPiecingInfo {
  diameter: number;
  weight: number;
  drag: number;
  velocity: number;
  krupp: number;
}

interface Projectile {
  type: string;
  nation: string;
  name: string;
  ammoType?: string;
  weight?: number;
  speed?: number;
  damage?: number;
  ricochetAngle?: number;
  ricochetAlways?: number;
  diameter?: number;
  ap?: ArmorPiecingInfo;
  overmatch?: number;
  fuseTime?: number;
  penHe?: number;
  penSap?: number;
  burnChance?: number;
  visibility?: number;
  range?: number;
  floodChance?: number;
  alphaDamage?: number;
  deepWater?: boolean;
  ignoreClasses?: string[];
}

const fromJson = (json: any): Projectile => ({
  type: json.type,
  nation: json.nation,
  name: json.name,
  ammoType: json.ammoType,
  weight: json.weight,
  speed: json.speed,
  damage: json.damage,
  ricochetAngle: json.ricochetAngle,
  ricochetAlways: json.ricochetAlways,
  diameter: json.diameter,
  ap: json.ap ? fromArmorPiecingInfoJson(json.ap) : undefined,
  overmatch: json.overmatch,
  fuseTime: json.fuseTime,
  penHe: json.penHE,
  penSap: json.penSAP,
  burnChance: json.burnChance,
  visibility: json.visibility,
  range: json.range,
  floodChance: json.floodChance,
  alphaDamage: json.alphaDamage,
  deepWater: json.deepWater,
  ignoreClasses: json.ignoreClasses ? Array.from(json.ignoreClasses) : undefined,
});

const fromArmorPiecingInfoJson = (json: any): ArmorPiecingInfo => ({
  diameter: json.diameter,
  weight: json.weight,
  drag: json.drag,
  velocity: json.velocity,
  krupp: json.krupp,
});

// Example usage
const App = () => {
  const exampleJson = {
    type: "shell",
    nation: "USA",
    name: "AP Shell",
    ammoType: "AP",
    weight: 10,
    speed: 900,
    damage: 100,
    ricochetAngle: 45,
    ricochetAlways: 0.5,
    diameter: 76.2,
    ap: {
      diameter: 76.2,
      weight: 10,
      drag: 0.1,
      velocity: 900,
      krupp: 200,
    },
    overmatch: 30,
    fuseTime: 0.1,
    penHE: 50,
    penSAP: 70,
    burnChance: 0.2,
    visibility: 0.5,
    range: 2000,
    floodChance: 0.1,
    alphaDamage: 300,
    deepWater: true,
    ignoreClasses: ["class1", "class2"],
  };

  const projectile = fromJson(exampleJson);

  return (
    <View>
      <Text>{`Projectile Name: ${projectile.name}`}</Text>
    </View>
  );
};

export default App;

interface ArmorPiecingInfo {
  diameter: number;
  weight: number;
  drag: number;
  velocity: number;
  krupp: number;
}

const fromJson = (json: any): ArmorPiecingInfo => ({
  diameter: parseFloat(json.diameter),
  weight: json.weight,
  drag: parseFloat(json.drag),
  velocity: json.velocity,
  krupp: json.krupp,
});