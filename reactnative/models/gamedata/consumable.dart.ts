
interface ConsumableProps {
  name: string;
  type: string;
}

const Consumable: React.FC<ConsumableProps> = ({ name, type }) => {
  return (
    <View>
      <Text>Name: {name}</Text>
      <Text>Type: {type}</Text>
    </View>
  );
};

export default Consumable;

interface Consumable {
  name: string;
  type: string;
}

const consumableFromJson = (json: any): Consumable => {
  return {
    name: json.name,
    type: json.type,
  };
};