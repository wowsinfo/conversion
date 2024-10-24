
interface GameInfoProps {
  regions: string[];
  types: string[];
}

const GameInfo: React.FC<GameInfoProps> = ({ regions, types }) => {
  return (
    <View>
      <Text>Regions:</Text>
      {regions.map((region, index) => (
        <Text key={index}>{region}</Text>
      ))}
      <Text>Types:</Text>
      {types.map((type, index) => (
        <Text key={index}>{type}</Text>
      ))}
    </View>
  );
};

export default GameInfo;

interface GameInfo {
  regions: string[];
  types: string[];
}

const preservedTiers: string[] = [
  '✱',
  '✸',
  '✹',
  '✺',
];

const tiers: string[] = [
  'I',
  'II',
  'III',
  'IV',
  'V',
  'VI',
  'VII',
  'VIII',
  'IX',
  'X',
  '★',
];

const fromJson = (json: any): GameInfo => {
  return {
    regions: json.regions,
    types: json.types,
  };
};

export { GameInfo, preservedTiers, tiers, fromJson };