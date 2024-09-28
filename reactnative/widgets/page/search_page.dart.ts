
const SearchPage: React.FC = () => {
  const [searchText, setSearchText] = useState('');
  const provider = useContext(SearchContext);

  const handleSearch = (text: string) => {
    setSearchText(text);
    provider.search(text);
  };

  return (
    <SearchProvider>
      <View style={styles.container}>
        <TextInput
          style={styles.searchBar}
          placeholder="Search..."
          value={searchText}
          onChangeText={handleSearch}
        />
        <ScrollView style={styles.scrollView}>
          {renderClan()}
          {renderPlayer()}
        </ScrollView>
      </View>
    </SearchProvider>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  searchBar: {
    height: 50,
    borderColor: 'gray',
    borderWidth: 1,
    paddingHorizontal: 10,
    margin: 10,
  },
  scrollView: {
    flex: 1,
  },
});

export default SearchPage;


const SearchBar: React.FC<{ onSubmitted?: (text: string) => void }> = ({ onSubmitted }) => {
  const [searchText, setSearchText] = useState('');
  const { canClear, resetAll } = useContext(SearchContext);

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={() => {/* Navigate back */}} style={styles.backButton}>
        <Text style={styles.backButtonText}>←</Text>
      </TouchableOpacity>
      <TextInput
        style={styles.input}
        autoFocus
        autoCorrect={false}
        value={searchText}
        onChangeText={setSearchText}
        onSubmitEditing={() => onSubmitted && onSubmitted(searchText)}
        placeholder="Search"
      />
      {canClear && (
        <TouchableOpacity onPress={resetAll} style={styles.clearButton}>
          <Text style={styles.clearButtonText}>✖</Text>
        </TouchableOpacity>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 16,
    paddingVertical: 8,
    elevation: 4,
    borderRadius: 100,
    backgroundColor: 'white',
  },
  backButton: {
    padding: 10,
  },
  backButtonText: {
    fontSize: 20,
  },
  input: {
    flex: 1,
    padding: 10,
    borderWidth: 0,
  },
  clearButton: {
    padding: 10,
  },
  clearButtonText: {
    fontSize: 20,
  },
});

export default SearchBar;


interface RenderTitleProps {
  title: string;
  count: number;
}

const renderTitle = ({ title, count }: RenderTitleProps) => {
  const textStyle: TextStyle = {
    fontSize: 24, // Adjust the font size as per your theme
    fontWeight: 'bold', // Adjust the font weight as per your theme
  };

  return (
    <Text style={textStyle}>
      {`${title} (${count})`}
    </Text>
  );
};

export default renderTitle;


const renderTitle = (context: any, title: string, numOfClans: number) => (
  <View style={styles.titleContainer}>
    <Text style={styles.title}>{title} ({numOfClans})</Text>
  </View>
);

const renderWrap = (context: any, clans: any[]) => (
  <ScrollView horizontal>
    {clans.map((clan, index) => (
      <View key={index} style={styles.clanItem}>
        <Text>{clan.name}</Text>
      </View>
    ))}
  </ScrollView>
);

const RenderClan: React.FC = () => {
  const { numOfClans, clans } = useContext(SearchContext);

  return (
    <View style={styles.container}>
      <View style={styles.padding}>
        {renderTitle(null, 'Search Clan', numOfClans)}
      </View>
      {renderWrap(null, clans)}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  padding: {
    paddingHorizontal: 16,
    paddingVertical: 8,
  },
  titleContainer: {
    // Add your title container styles here
  },
  title: {
    // Add your title text styles here
  },
  clanItem: {
    // Add your clan item styles here
  },
});

export default RenderClan;


const renderTitle = (title: string, numOfPlayers: number) => (
  <Text style={styles.title}>
    {title} ({numOfPlayers})
  </Text>
);

const renderWrap = (players: Array<string>) => (
  <View style={styles.wrap}>
    {players.map((player, index) => (
      <Text key={index} style={styles.player}>
        {player}
      </Text>
    ))}
  </View>
);

const RenderPlayer: React.FC = () => {
  const { numOfPlayers, players } = useContext(SearchContext);

  return (
    <View style={styles.container}>
      <View style={styles.padding}>
        {renderTitle('Search Player', numOfPlayers)}
      </View>
      {renderWrap(players)}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  padding: {
    paddingHorizontal: 16,
    paddingVertical: 8,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  player: {
    margin: 4,
    padding: 8,
    backgroundColor: '#f0f0f0',
    borderRadius: 4,
  },
});

export default RenderPlayer;


const { width } = Dimensions.get('window');

const renderWrap = (results: SearchResult[]) => {
  const count = Utils.getItemCount(6, 1, 300); // Assuming Utils is a utility class
  const itemSize = results.length;

  return (
    <View style={{ flexDirection: 'row', flexWrap: 'wrap', alignItems: 'center' }}>
      {results.map((value, index) => (
        <DebutEffect
          key={value.id}
          keepAlive={false}
          intervalStart={index / itemSize}
        >
          <View style={{ width: width / count }}>
            <TouchableOpacity onPress={() => provider.onResultSelected(value)}>
              <Text>{value.displayName}</Text>
              <Text>{value.id}</Text>
            </TouchableOpacity>
          </View>
        </DebutEffect>
      ))}
    </View>
  );
};

export default renderWrap;


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