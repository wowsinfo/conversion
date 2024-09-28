
interface ShipAdditionalBoxProps {
  shipAdditional: ShipAdditional;
}

const ShipAdditionalBox: React.FC<ShipAdditionalBoxProps> = ({ shipAdditional }) => {
  return (
    <View style={styles.container}>
      <Text style={styles.caption}>{localize('additionalInfo')}</Text>
      <Text style={styles.value}>{formatNumber(shipAdditional.value)}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
    backgroundColor: '#f8f8f8',
    borderRadius: 8,
    margin: 8,
  },
  caption: {
    fontSize: 14,
    color: '#666',
  },
  value: {
    fontSize: 18,
    fontWeight: 'bold',
  },
});

export default ShipAdditionalBox;


interface ShipAdditional {
  damage: number;
  winrate: number;
  frags: number;
  battles?: number;
}

interface Props {
  shipAdditional: ShipAdditional;
}

const Localisation = {
  instance: {
    battles: 'Battles',
  },
  of: (context: any) => ({
    warship_avg_damage: 'Average Damage',
    warship_avg_winrate: 'Average Winrate',
    warship_avg_frag: 'Average Frags',
  }),
};

const TextWithCaption: React.FC<{ title: string; value: string }> = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text style={styles.title}>{title}</Text>
    <Text style={styles.value}>{value}</Text>
  </View>
);

const ShipStats: React.FC<Props> = ({ shipAdditional }) => {
  const battles = shipAdditional.battles;
  const localised = Localisation.of(null);

  const toDecimalString = (num: number) => num.toFixed(2);
  const asPercentString = (num: number) => `${(num * 100).toFixed(2)}%`;

  return (
    <View style={styles.container}>
      <View style={styles.wrap}>
        <TextWithCaption
          title={localised.warship_avg_damage}
          value={toDecimalString(shipAdditional.damage)}
        />
        <TextWithCaption
          title={localised.warship_avg_winrate}
          value={asPercentString(shipAdditional.winrate)}
        />
        <TextWithCaption
          title={localised.warship_avg_frag}
          value={toDecimalString(shipAdditional.frags)}
        />
        {battles !== undefined && (
          <TextWithCaption
            title={Localisation.instance.battles}
            value={toDecimalString(battles)}
          />
        )}
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
  },
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
  },
  textWithCaption: {
    margin: 8,
  },
  title: {
    fontWeight: 'bold',
  },
  value: {
    color: 'gray',
  },
});

export default ShipStats;


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