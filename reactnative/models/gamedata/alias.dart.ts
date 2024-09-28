
interface AliasProps {
  alias: string;
}

const Alias: React.FC<AliasProps> = ({ alias }) => {
  return (
    <View>
      <Text>{alias}</Text>
    </View>
  );
};

export default Alias;

interface Alias {
  alias: string;
}

const fromJson = (json: Record<string, any>): Alias => {
  return {
    alias: json.alias,
  };
};