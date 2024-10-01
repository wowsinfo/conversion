
interface ShipAdditionalProps {
  damage: number;
  frags: number;
  winrate: number;
  battles?: number;
}

const ShipAdditional: React.FC<ShipAdditionalProps> = ({ damage, frags, winrate, battles }) => {
  return (
    <View>
      <Text>Damage: {damage}</Text>
      <Text>Frags: {frags}</Text>
      <Text>Winrate: {winrate}</Text>
      {battles !== undefined && <Text>Battles: {battles}</Text>}
    </View>
  );
};

export default ShipAdditional;

interface ShipAdditional {
  damage: number;
  frags: number;
  winrate: number;
  battles?: number;
}

const fromJson = (json: any): ShipAdditional => ({
  damage: json.damage,
  frags: json.frags,
  winrate: json.winrate,
  battles: json.battles,
});