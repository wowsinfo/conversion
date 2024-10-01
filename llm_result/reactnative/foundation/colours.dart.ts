
const WoWsColours = {
  // price colours
  creditPrice: '#607D8B', // blueGrey
  goldPrice: '#FFA726', // orange

  // ship name colours
  premiumShip: '#FFA726', // orange
  specialShip: '#FF5722', // deepOrangeAccent
};

const App = () => {
  return (
    <View style={styles.container}>
      <Text style={[styles.price, { color: WoWsColours.creditPrice }]}>Credit Price</Text>
      <Text style={[styles.price, { color: WoWsColours.goldPrice }]}>Gold Price</Text>
      <Text style={[styles.shipName, { color: WoWsColours.premiumShip }]}>Premium Ship</Text>
      <Text style={[styles.shipName, { color: WoWsColours.specialShip }]}>Special Ship</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  price: {
    fontSize: 20,
    margin: 10,
  },
  shipName: {
    fontSize: 20,
    margin: 10,
  },
});

export default App;