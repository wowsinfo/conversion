
interface ShipModuleDialogProps {
  visible: boolean;
  modules: ShipModuleMap;
  selection: ShipModuleSelection;
  onUpdate: (selection: ShipModuleSelection) => void;
  onClose: () => void;
}

const ShipModuleDialog: React.FC<ShipModuleDialogProps> = ({
  visible,
  modules,
  selection,
  onUpdate,
  onClose,
}) => {
  const providerValue = new ShipModuleProvider(modules, ShipModuleSelection.fromSelection(selection));

  return (
    <Modal
      transparent={true}
      visible={visible}
      animationType="slide"
      onRequestClose={onClose}
    >
      <ShipModuleContext.Provider value={providerValue}>
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <MaxWidthBox>
            {/* Add your dialog content here */}
            <Button title="Close" onPress={onClose} />
          </MaxWidthBox>
        </View>
      </ShipModuleContext.Provider>
    </Modal>
  );
};

export const showShipModuleDialog = (
  context: React.Context<any>,
  modules: ShipModuleMap,
  selection: ShipModuleSelection,
  onUpdate: (selection: ShipModuleSelection) => void
) => {
  const { setVisible } = useContext(context);
  setVisible(true);
  
  return (
    <ShipModuleDialog
      visible={true}
      modules={modules}
      selection={selection}
      onUpdate={onUpdate}
      onClose={() => setVisible(false)}
    />
  );
};


interface Props {
  modules: ShipModuleMap;
  onUpdate: (selection: ShipModuleSelection) => void;
  visible: boolean;
  onClose: () => void;
}

const ModuleDialog: React.FC<Props> = ({ modules, onUpdate, visible, onClose }) => {
  const renderModuleMap = (context: any) => {
    // Implement your rendering logic based on the modules
    return Object.keys(modules).map((key) => (
      <View key={key}>
        {/* Render your module component here */}
      </View>
    ));
  };

  return (
    <Modal
      transparent={true}
      visible={visible}
      onRequestClose={onClose}
    >
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ScrollView>
          <MaxWidthBox>
            <View>
              {renderModuleMap(null)}
            </View>
          </MaxWidthBox>
        </ScrollView>
      </View>
    </Modal>
  );
};

export default ModuleDialog;


const renderModuleMap = (onUpdate: (selection: any) => void) => {
  const provider = useContext(ShipModuleProvider);
  const entries = Object.entries(modules);

  return (
    <>
      {entries.map(([moduleType, list], index) => (
        <View key={moduleType} style={styles.container}>
          <View style={styles.spacing} />
          <View style={styles.padding}>
            <Text style={styles.title}>{moduleType.name || 'X'}</Text>
          </View>
          {list.map((module, moduleIndex) => (
            <ModuleListTile
              key={moduleIndex}
              moduleType={moduleType}
              isSelected={provider.isSelected(moduleType, moduleIndex)}
              module={module}
              updateSelection={provider.updateSelection}
            />
          ))}
          {index !== entries.length - 1 && <View style={styles.divider} />}
          {index === entries.length - 1 && (
            <View style={styles.padding}>
              <Button
                title="Update"
                onPress={() => {
                  Alert.alert('Update', 'Updating selection...', [
                    { text: 'OK', onPress: () => onUpdate(provider.selection) },
                  ]);
                }}
              />
            </View>
          )}
        </View>
      ))}
    </>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'column',
    alignItems: 'stretch',
  },
  spacing: {
    height: 8,
  },
  padding: {
    paddingHorizontal: 16,
    paddingVertical: 4,
  },
  title: {
    fontSize: 20, // Adjust based on your theme
    fontWeight: 'bold', // Adjust based on your theme
  },
  divider: {
    height: 1,
    backgroundColor: '#ccc', // Adjust based on your theme
  },
});

export default renderModuleMap;


interface ShipModuleHolder {
  module: {
    name: string;
    cost: {
      costCr: number;
      costXp: number;
    };
  } | null;
  type: ShipModuleType;
}

type ShipModuleType = any; // Define your ShipModuleType here

interface BuildModuleListTileProps {
  index: number;
  selected: boolean;
  module: ShipModuleHolder;
  onChange: (type: ShipModuleType, index: number) => void;
}

const BuildModuleListTile: React.FC<BuildModuleListTileProps> = ({
  index,
  selected,
  module,
  onChange,
}) => {
  const info = module.module!;
  const type = module.type;

  return (
    <TouchableOpacity onPress={() => onChange(type, index)}>
      <View style={{ flexDirection: 'row', alignItems: 'center' }}>
        <CheckBox
          value={selected}
          onValueChange={() => onChange(type, index)}
        />
        <View style={{ flex: 1 }}>
          <Text>{info.name}</Text>
          <Text style={{ color: 'green' }}>{info.cost.costCr}</Text>
        </View>
        <Text>{`${info.cost.costXp} XP`}</Text>
      </View>
    </TouchableOpacity>
  );
};

export default BuildModuleListTile;


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
  },
  text: {
    fontSize: 24,
    marginBottom: 20,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
  },
});

export default App;