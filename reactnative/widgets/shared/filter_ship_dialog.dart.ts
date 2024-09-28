
interface ShipFilterDialogProps {
  visible: boolean;
  onFilter: (filter: ShipFilter) => void;
  onClose: () => void;
}

const ShipFilterDialog: React.FC<ShipFilterDialogProps> = ({ visible, onFilter, onClose }) => {
  const { t } = useContext(LocalizationContext);
  
  return (
    <Modal
      transparent={true}
      visible={visible}
      animationType="slide"
    >
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <MaxWidthBox>
          <View style={{ padding: 20, backgroundColor: 'white', borderRadius: 10 }}>
            {/* Add your filter UI components here */}
            <Button title={t('applyFilter')} onPress={() => {
              const filter: ShipFilter = {}; // Construct your filter object here
              onFilter(filter);
              onClose();
            }} />
            <Button title={t('cancel')} onPress={onClose} />
          </View>
        </MaxWidthBox>
      </View>
    </Modal>
  );
};

export const showFilterShipDialog = (
  onFilter: (filter: ShipFilter) => void
) => {
  const [visible, setVisible] = React.useState(false);

  const openDialog = () => setVisible(true);
  const closeDialog = () => setVisible(false);

  return (
    <>
      <Button title="Filter Ships" onPress={openDialog} />
      <FilterShipProvider>
        <ShipFilterDialog visible={visible} onFilter={onFilter} onClose={closeDialog} />
      </FilterShipProvider>
    </>
  );
};


const ShipFilterDialog = ({ onFilter, visible, onClose }) => {
  const { resetAll, onFilter: filterProvider } = useContext(FilterShipContext);
  const [name, setName] = useState('');

  const handleFilter = () => {
    onFilter(filterProvider(name));
    onClose();
  };

  return (
    <Modal transparent={true} visible={visible} animationType="slide">
      <View style={styles.container}>
        <View style={styles.dialog}>
          <ScrollView>
            <Text style={styles.title}>{Localisation.instance.shipNameFilterName}</Text>
            <TextInput
              style={styles.input}
              value={name}
              onChangeText={setName}
              placeholder="..."
            />
            <View style={styles.divider} />
            <Text style={styles.title}>{Localisation.instance.tierFilterName}</Text>
            {/* Implement renderTierList here */}
            <View style={styles.divider} />
            <Text style={styles.title}>{Localisation.instance.shipTypeFilterName}</Text>
            {/* Implement renderTypeList here */}
            <View style={styles.divider} />
            <Text style={styles.title}>{filterProvider.regionFilterName}</Text>
            {/* Implement renderRegionList here */}
          </ScrollView>
          <View style={styles.buttonContainer}>
            <Button title="Reset" onPress={resetAll} />
            <Button title="Apply" onPress={handleFilter} />
          </View>
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
    width: '80%',
    backgroundColor: 'white',
    borderRadius: 10,
    padding: 20,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  input: {
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    marginBottom: 16,
    padding: 8,
  },
  divider: {
    height: 1,
    backgroundColor: '#ccc',
    marginVertical: 10,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 10,
  },
});

export default ShipFilterDialog;


interface RenderListTitleProps {
  title: string;
}

const RenderListTitle: React.FC<RenderListTitleProps> = ({ title }) => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    paddingLeft: 8,
  },
  title: {
    color: '#007AFF', // Replace with your primary color
    fontSize: 20, // Adjust font size according to your theme
    fontWeight: 'bold', // Adjust font weight according to your theme
  },
});

export default RenderListTitle;


const renderRegionList = () => {
    const provider = useContext(FilterShipContext);
    return (
        <FilterList
            filterList={provider.regionList}
            isSelected={provider.isRegionSelected}
            onSelected={provider.updateRegion}
        />
    );
};

export default renderRegionList;


const RenderTypeList: React.FC = () => {
    const { typeList, isTypeSelected, updateType } = useContext(FilterShipContext);

    return (
        <View>
            <FilterList
                filterList={typeList}
                isSelected={isTypeSelected}
                onSelected={updateType}
            />
        </View>
    );
};

export default RenderTypeList;


const renderTierList = () => {
    const provider = useContext(FilterShipContext);
    return (
        <FilterList
            filterList={provider.tierList}
            isSelected={provider.isTierSelected}
            onSelected={provider.updateTier}
        />
    );
};

export default renderTierList;


interface FilterListProps {
  filterList: string[];
  isSelected: (filter: string) => boolean;
  onSelected: (filter: string) => void;
}

const FilterList: React.FC<FilterListProps> = ({ filterList, isSelected, onSelected }) => {
  return (
    <View style={styles.container}>
      {filterList.map((filter) => (
        <TouchableOpacity
          key={filter}
          style={[styles.chip, isSelected(filter) && styles.selectedChip]}
          onPress={() => onSelected(filter)}
        >
          <Text style={styles.chipText}>{filter}</Text>
        </TouchableOpacity>
      ))}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  chip: {
    padding: 10,
    margin: 5,
    backgroundColor: '#e0e0e0',
    borderRadius: 20,
  },
  selectedChip: {
    backgroundColor: '#6200ee',
  },
  chipText: {
    color: '#000',
  },
});

export default FilterList;


interface FilterListProps {
  filterList: string[];
  isSelected: (index: number) => boolean;
  onSelected: (value: string) => void;
}

const FilterList: React.FC<FilterListProps> = ({ filterList, isSelected, onSelected }) => {
  return (
    <View style={styles.container}>
      {filterList.map((filter, index) => (
        <TouchableOpacity key={index} onPress={() => onSelected(filter)} style={styles.row}>
          <CheckBox value={isSelected(index)} onValueChange={() => onSelected(filter)} />
          <Text style={styles.text}>{filter}</Text>
        </TouchableOpacity>
      ))}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'flex-start',
    padding: 4,
  },
  row: {
    flexDirection: 'row',
    alignItems: 'center',
    marginVertical: 4,
  },
  text: {
    fontWeight: '500',
    marginHorizontal: 16,
  },
});

export default FilterList;


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