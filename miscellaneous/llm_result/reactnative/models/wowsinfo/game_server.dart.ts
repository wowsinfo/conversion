enum WoWsServer {
  russia,
  europe,
  northAmerica,
  asia,
}

const domain = ['ru', 'eu', 'com', 'asia'];
const prefix = ['ru', 'eu', 'na', 'asia'];
const numberDomain = ['ru.', '', 'na.', 'asia.'];

class GameServer {
  private server: WoWsServer;

  constructor(server: WoWsServer) {
    this.server = server;
  }

  getDomain() {
    return domain[this.server];
  }

  getPrefix() {
    return prefix[this.server];
  }

  getNumberDomain() {
    return numberDomain[this.server];
  }

  getIndex() {
    return this.server;
  }

  static fromIndex(index: number): GameServer {
    if (index < 0 || index > 3) {
      throw new Error('index must be between 0 and 3');
    }
    return new GameServer(index as WoWsServer);
  }
}


enum WoWsServer {
  asia = 'Asia',
  na = 'North America',
  eu = 'Europe',
  ru = 'Russia',
}

const defaultServer: WoWsServer = WoWsServer.asia;

const App: React.FC = () => {
  return (
    <View>
      <Text>Default Server: {defaultServer}</Text>
    </View>
  );
};

export default App;