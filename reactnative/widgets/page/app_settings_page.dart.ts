
const AppSettingsPage: React.FC = () => {
  const { state: appState, updateDarkMode } = useContext(AppContext);
  const { state: settingsState, updateServer } = useContext(SettingsContext);

  const showThemeColours = () => {
    // Implement theme color selection logic here
  };

  const handleFeedback = () => {
    App.launch(App.emailToLink);
  };

  const handleReportIssues = () => {
    App.launch(App.newIssueLink);
  };

  const handleOpenSourceGithub = () => {
    App.launch(App.githubLink);
  };

  const handleOpenSourceLicense = () => {
    Alert.alert(Localisation.of(context).settings_open_source_licence, '', [{ text: 'OK' }]);
  };

  return (
    <View style={{ flex: 1 }}>
      <Text style={{ fontSize: 20, fontWeight: 'bold', padding: 16 }}>{Localisation.of(context).app_name}</Text>
      <ScrollView>
        <View>
          <Text>{Localisation.of(context).setting_game_server}</Text>
          {/* Replace with a dropdown component */}
          {/* Example: <Dropdown options={settingsState.servers} value={settingsState.serverValue} onChange={updateServer} /> */}
        </View>
        <View style={{ borderBottomWidth: 1, borderColor: '#ccc' }} />
        <View>
          <Text>{Localisation.of(context).settings_app_dark_mode}</Text>
          <Switch
            value={appState.darkMode}
            onValueChange={updateDarkMode}
          />
          <TouchableOpacity onPress={showThemeColours}>
            <Text>{Localisation.of(context).settings_app_theme_colour}</Text>
          </TouchableOpacity>
        </View>
        <View style={{ borderBottomWidth: 1, borderColor: '#ccc' }} />
        <TouchableOpacity onPress={handleFeedback}>
          <Text>{Localisation.of(context).settings_app_send_feedback}</Text>
          <Text>{Localisation.of(context).settings_app_send_feedback_subtitle}</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={handleReportIssues}>
          <Text>{Localisation.of(context).settings_app_report_issues}</Text>
          <Text>{App.newIssueLink}</Text>
        </TouchableOpacity>
        <View style={{ borderBottomWidth: 1, borderColor: '#ccc' }} />
        <TouchableOpacity onPress={handleOpenSourceGithub}>
          <Text>{Localisation.of(context).settings_open_source_github}</Text>
          <Text>{App.githubLink}</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={handleOpenSourceLicense}>
          <Text>{Localisation.of(context).settings_open_source_licence}</Text>
        </TouchableOpacity>
      </ScrollView>
    </View>
  );
};

export default AppSettingsPage;


const showThemeColours = (colours: string[], context: any) => {
    const provider = useContext(AppContext);
    const count = Utils(context).getItemCount(4, 2, 300);

    const renderItem = ({ item }: { item: string }) => (
        <TouchableOpacity
            style={[styles.colorBox, { backgroundColor: item }]}
            onPress={() => {
                provider.updateThemeColour(item);
                context.setModalVisible(false);
            }}
        />
    );

    return (
        <Modal
            transparent={true}
            animationType="slide"
            visible={context.modalVisible}
            onRequestClose={() => context.setModalVisible(false)}
        >
            <View style={styles.modalContainer}>
                <FlatList
                    data={colours}
                    renderItem={renderItem}
                    keyExtractor={(item) => item}
                    numColumns={count}
                    showsVerticalScrollIndicator={false}
                />
            </View>
        </Modal>
    );
};

const styles = StyleSheet.create({
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
    },
    colorBox: {
        width: 50,
        height: 50,
        margin: 5,
    },
});

export default showThemeColours;


interface DropdownValue<T> {
  value: T;
  title: string;
}

const Dropdown: React.FC<{ items: DropdownValue<any>[]; onSelect: (value: any) => void }> = ({ items, onSelect }) => {
  return (
    <View>
      {items.map((item, index) => (
        <TouchableOpacity key={index} onPress={() => onSelect(item.value)}>
          <Text>{item.title}</Text>
        </TouchableOpacity>
      ))}
    </View>
  );
};

export default Dropdown;


interface DropdownListTileProps<T> {
  options: T[];
  value: T;
  title: string;
  onChanged: (value: T) => void;
}

const DropdownListTile = <T,>({
  options,
  value,
  title,
  onChanged,
}: DropdownListTileProps<T>) => {
  const [modalVisible, setModalVisible] = React.useState(false);

  const handleSelect = (item: T) => {
    onChanged(item);
    setModalVisible(false);
  };

  return (
    <View>
      <TouchableOpacity onPress={() => setModalVisible(true)}>
        <Text>{title}</Text>
        <Text>{String(value)}</Text>
      </TouchableOpacity>
      <Modal
        transparent={true}
        visible={modalVisible}
        animationType="slide"
      >
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <View style={{ width: '80%', backgroundColor: 'white', borderRadius: 10 }}>
            <FlatList
              data={options}
              keyExtractor={(item, index) => index.toString()}
              renderItem={({ item }) => (
                <TouchableOpacity onPress={() => handleSelect(item)}>
                  <Text style={{ padding: 10 }}>{String(item)}</Text>
                </TouchableOpacity>
              )}
            />
            <TouchableOpacity onPress={() => setModalVisible(false)}>
              <Text style={{ padding: 10, textAlign: 'center' }}>Close</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
  );
};

export default DropdownListTile;


interface DropdownValue<T> {
  value: T;
  title: string;
}

interface DropdownProps<T> {
  options: DropdownValue<T>[];
  value: T;
  title: React.ReactNode;
  onChanged: (value: T | null) => void;
}

const Dropdown = <T,>({ options, value, title, onChanged }: DropdownProps<T>) => {
  const [isVisible, setIsVisible] = React.useState(false);
  const dropdownButtonRef = useRef<TouchableOpacity>(null);

  const openDropdown = () => {
    setIsVisible(true);
  };

  const closeDropdown = () => {
    setIsVisible(false);
  };

  const handleSelect = (item: DropdownValue<T>) => {
    onChanged(item.value);
    closeDropdown();
  };

  return (
    <View>
      <TouchableOpacity ref={dropdownButtonRef} onPress={openDropdown}>
        <View>
          <Text>{title}</Text>
          <Text>{options.find(option => option.value === value)?.title}</Text>
        </View>
      </TouchableOpacity>
      {isVisible && (
        <Modal transparent={true} animationType="fade" visible={isVisible} onRequestClose={closeDropdown}>
          <TouchableOpacity style={{ flex: 1 }} onPress={closeDropdown}>
            <View style={{ backgroundColor: 'white', margin: 50, borderRadius: 10 }}>
              <FlatList
                data={options}
                keyExtractor={(item) => item.value.toString()}
                renderItem={({ item }) => (
                  <TouchableOpacity onPress={() => handleSelect(item)}>
                    <Text>{item.title}</Text>
                  </TouchableOpacity>
                )}
              />
            </View>
          </TouchableOpacity>
        </Modal>
      )}
    </View>
  );
};

export default Dropdown;


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