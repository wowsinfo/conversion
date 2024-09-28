
interface ShipPenetrationDialogProps {
  visible: boolean;
  onClose: () => void;
  ship: Ship;
}

const ShipPenetrationDialog: React.FC<ShipPenetrationDialogProps> = ({ visible, onClose, ship }) => {
  const provider = new ShipPenetrationProvider(ship);

  return (
    <Modal
      transparent={true}
      animationType="slide"
      visible={visible}
      onRequestClose={onClose}
    >
      <View style={styles.container}>
        <View style={styles.dialog}>
          <LineChart
            data={provider.penetrationSeries}
            width={300}
            height={220}
            chartConfig={{
              backgroundColor: '#ffffff',
              backgroundGradientFrom: '#ffffff',
              backgroundGradientTo: '#ffffff',
              decimalPlaces: 0,
              color: (opacity = 1) => provider.getThemePalette(),
              labelColor: (opacity = 1) => provider.getThemePalette(),
              style: {
                borderRadius: 16,
              },
              propsForDots: {
                r: '6',
                strokeWidth: '2',
                stroke: provider.getThemePalette(),
              },
            }}
            bezier
            style={styles.chart}
          />
          <Text style={styles.closeButton} onPress={onClose}>
            {Localisation.instance.close}
          </Text>
        </View>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  dialog: {
    width: 320,
    padding: 20,
    backgroundColor: '#fff',
    borderRadius: 10,
    alignItems: 'center',
  },
  chart: {
    marginVertical: 8,
    borderRadius: 16,
  },
  closeButton: {
    marginTop: 10,
    color: 'blue',
    textDecorationLine: 'underline',
  },
});

export default ShipPenetrationDialog;