
interface ShipInfoPageProps {
  ship: Ship;
}

const ShipInfoPage: React.FC<ShipInfoPageProps> = ({ ship }) => {
  const { shipInfo, setShipInfo } = useContext(ShipContext);
  const { scrollPosition, setScrollPosition } = useContext(ScrollContext);
  const { similarShips, fetchSimilarShips } = useContext(SimilarShipContext);
  
  const [modalVisible, setModalVisible] = React.useState(false);
  const [penetrationModalVisible, setPenetrationModalVisible] = React.useState(false);

  const renderShipCell = ({ item }: { item: Ship }) => (
    <ShipCell ship={item} />
  );

  const handleOpenModuleDialog = () => {
    setModalVisible(true);
  };

  const handleOpenPenetrationDialog = () => {
    setPenetrationModalVisible(true);
  };

  return (
    <View style={styles.container}>
      <AssetImageLoader source={{ uri: ship.imageUrl }} style={styles.image} />
      <Text style={styles.title}>{ship.name}</Text>
      <TextWithCaption caption="Type" text={ship.type} />
      <TextWithCaption caption="Nation" text={ship.nation} />
      <TouchableOpacity onPress={handleOpenModuleDialog}>
        <Text style={styles.link}>View Modules</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={handleOpenPenetrationDialog}>
        <Text style={styles.link}>View Penetration</Text>
      </TouchableOpacity>
      <FlatList
        data={similarShips}
        renderItem={renderShipCell}
        keyExtractor={(item) => item.id.toString()}
      />
      <ShipAdditionalBox additionalInfo={ship.additionalInfo} />
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
      >
        <ShipModuleDialog onClose={() => setModalVisible(false)} />
      </Modal>
      <Modal
        animationType="slide"
        transparent={true}
        visible={penetrationModalVisible}
        onRequestClose={() => setPenetrationModalVisible(false)}
      >
        <ShipPenetrationDialog onClose={() => setPenetrationModalVisible(false)} />
      </Modal>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#fff',
  },
  image: {
    width: '100%',
    height: 200,
    borderRadius: 8,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginVertical: 8,
  },
  link: {
    color: 'blue',
    marginVertical: 4,
  },
});

export default ShipInfoPage;


interface Ship {
  // Define the properties of Ship here
}

interface ShipInfoPageProps {
  ship: Ship;
}

const ShipInfoPage: React.FC<ShipInfoPageProps> = ({ ship }) => {
  const scrollController = useRef(new Animated.Value(0)).current;
  const slideController = useRef(new Animated.Value(0)).current;

  const provider = new ShipInfoProvider(ship);
  const similarProvider = new SimilarShipProvider(ship);
  const scrollProvider = new ScrollProvider({
    scroll: scrollController,
    animation: slideController,
    height: 130,
  });

  useEffect(() => {
    // Add any initialization logic here if needed
  }, []);

  return (
    <View>
      <ScrollView
        scrollEventThrottle={16}
        onScroll={Animated.event(
          [{ nativeEvent: { contentOffset: { y: scrollController } } }],
          { useNativeDriver: false }
        )}
      >
        {/* Render your ship information and similar ships here */}
      </ScrollView>
    </View>
  );
};

class ShipInfoProvider {
  constructor(ship: Ship) {
    // Initialize with ship data
  }
}

class SimilarShipProvider {
  constructor(ship: Ship) {
    // Initialize with ship data
  }
}

class ScrollProvider {
  constructor({ scroll, animation, height }: { scroll: Animated.Value; animation: Animated.Value; height: number }) {
    // Initialize with scroll and animation values
  }
}

export default ShipInfoPage;


const store = createStore(/* your reducer here */);

const ShipDetailScreen = ({ ship }) => {
  const { _provider, _scrollProvider } = useContext(ShipInfoContext);
  
  return (
    <Provider store={store}>
      <View style={{ flex: 1 }}>
        <View style={{ height: 56, justifyContent: 'center', alignItems: 'center', backgroundColor: '#f8f8f8' }}>
          <Text>{_provider.title}</Text>
        </View>
        <ScrollView>
          <MasonryList
            data={[
              <ShipTitleSection 
                icon={_provider.shipIcon}
                name={_provider.shipNameWithTier}
                region={_provider.region}
                type={_provider.type}
                costCR={_provider.costCR}
                costGold={_provider.costGold}
                additional={_provider.shipAdditional}
                description={_provider.description}
              />,
              _provider.canChangeModules && (
                <ShipModuleButton 
                  title="Change Ship Modules"
                  shipModules={_provider.moduleList}
                  selection={_provider.selection}
                />
              ),
              _provider.renderHull && <ShipSurvivability />,
              _provider.renderMainGun && <ShipMainBattery ship={ship} />,
              _provider.renderSpecials && <ShipSpecial />,
              _provider.renderSecondaryGun && <ShipSecondaries />,
              _provider.renderPinger && <ShipPinger />,
              _provider.renderSubmarineBattery && <ShipBattery />,
              _provider.renderTorpedo && <ShipTorpedo />,
              _provider.renderAirDefense && <ShipAirDefense />,
              _provider.renderAirSupport && <ShipAirSupport />,
              _provider.renderDepthCharge && <ShipDepthCharge />,
              _provider.renderMobility && <ShipMobility />,
              _provider.renderVisibility && <ShipVisibility />,
              _provider.hasUpgrades && <ShipUpgrades />,
              _provider.hasNextShip && <ShipNextShip />,
              _provider.hasConsumables && <ShipConsumables />,
              <View style={{ height: 32 }} />
            ]}
            keyExtractor={(item, index) => index.toString()}
            numColumns={2}
          />
        </ScrollView>
      </View>
    </Provider>
  );
};

const ShipTitleSection = ({ icon, name, region, type, costCR, costGold, additional, description }) => {
  return (
    <View>
      <Text>{name}</Text>
      <Text>{region}</Text>
      <Text>{type}</Text>
      <Text>{costCR}</Text>
      <Text>{costGold}</Text>
      <Text>{additional}</Text>
      <Text>{description}</Text>
    </View>
  );
};

// Define other components like ShipModuleButton, ShipSurvivability, etc. similarly

export default ShipDetailScreen;


const buildSimilarShips = (props: { ship: any }) => {
  const { similarProvider } = useContext(SimilarShipContext); // Assuming you have a context for similar ships
  const { display } = useContext(ScrollProvider);

  if (!similarProvider.hasSimilarShips) return null;

  const animatedValue = new Animated.Value(0);

  const transition = () => {
    LayoutAnimation.configureNext(LayoutAnimation.Presets.easeInEaseOut);
    animatedValue.setValue(display ? 1 : 0);
  };

  React.useEffect(() => {
    transition();
  }, [display]);

  return (
    <Animated.View style={{ ...styles.container, height: animatedValue.interpolate({
      inputRange: [0, 1],
      outputRange: [0, 'auto'],
    }) }}>
      {display ? (
        <SimilarShipList
          source={props.ship}
          ships={similarProvider.similarShips}
        />
      ) : null}
    </Animated.View>
  );
};

const styles = StyleSheet.create({
  container: {
    overflow: 'hidden',
  },
});

export default buildSimilarShips;


interface ShipTitleSectionProps {
  icon: string;
  name: string;
  region: string;
  type: string;
  costCR?: number;
  costGold?: number;
  additional?: string;
  description: string;
}

const ShipTitleSection: React.FC<ShipTitleSectionProps> = ({
  icon,
  name,
  region,
  type,
  costCR,
  costGold,
  additional,
  description,
}) => {
  return (
    <View style={styles.container}>
      <Image source={{ uri: icon }} style={styles.icon} />
      <Text style={styles.name}>{name}</Text>
      <Text style={styles.region}>{region}</Text>
      <Text style={styles.type}>{type}</Text>
      {costCR && <Text style={styles.cost}>Cost CR: {costCR}</Text>}
      {costGold && <Text style={styles.cost}>Cost Gold: {costGold}</Text>}
      {additional && <Text style={styles.additional}>{additional}</Text>}
      <Text style={styles.description}>{description}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
    backgroundColor: '#fff',
    borderRadius: 8,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
  },
  icon: {
    width: 50,
    height: 50,
    marginBottom: 8,
  },
  name: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  region: {
    fontSize: 16,
    color: '#666',
  },
  type: {
    fontSize: 16,
    color: '#666',
  },
  cost: {
    fontSize: 14,
    color: '#333',
  },
  additional: {
    fontSize: 14,
    color: '#333',
  },
  description: {
    fontSize: 14,
    color: '#333',
    marginTop: 8,
  },
});

export default ShipTitleSection;


interface ShipProps {
  icon: string;
  name: string;
  region: string;
  type: string;
  costCR?: string;
  costGold?: string;
  description: string;
  additional?: ShipAdditional; // Define ShipAdditional type accordingly
}

const Ship: React.FC<ShipProps> = ({
  icon,
  name,
  region,
  type,
  costCR,
  costGold,
  description,
  additional,
}) => {
  return (
    <Card style={styles.card}>
      <View style={styles.container}>
        <ShipIcon icon={icon} height={128} hero={true} />
        <Text style={styles.title}>{name}</Text>
        <Text style={styles.region}>{region}</Text>
        <Text>{type}</Text>
        {costCR && (
          <Text style={[styles.cost, { color: WoWsColours.creditPrice }]}>
            {costCR}
          </Text>
        )}
        {costGold && (
          <Text style={[styles.cost, { color: WoWsColours.goldPrice }]}>
            {costGold}
          </Text>
        )}
        {additional && <ShipAdditionalBox shipAdditional={additional} />}
        <Text style={styles.description}>{description}</Text>
      </View>
    </Card>
  );
};

const styles = StyleSheet.create({
  card: {
    margin: 8,
  },
  container: {
    padding: 8,
    alignItems: 'center',
  },
  title: {
    fontSize: 20, // Adjust according to your theme
    fontWeight: 'bold',
  },
  region: {
    marginTop: 8,
  },
  cost: {
    textAlign: 'center',
  },
  description: {
    marginTop: 8,
    textAlign: 'center',
  },
});

export default Ship;


interface ShipModuleButtonProps {
  title: string;
  shipModules: any; // Replace 'any' with the appropriate type for shipModules
  selection: any; // Replace 'any' with the appropriate type for selection
}

const ShipModuleButton: React.FC<ShipModuleButtonProps> = ({ title, shipModules, selection }) => {
  return (
    <TouchableOpacity style={styles.button}>
      <Text style={styles.buttonText}>{title}</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    backgroundColor: '#007BFF',
    padding: 10,
    borderRadius: 5,
    alignItems: 'center',
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 16,
  },
});

export default ShipModuleButton;


interface ShipModuleMap {
  // Define the structure of ShipModuleMap
}

interface ShipModuleSelection {
  // Define the structure of ShipModuleSelection
}

interface ShipModuleButtonProps {
  title: string;
  shipModules: ShipModuleMap;
  selection: ShipModuleSelection;
}

const ShipModuleButton: React.FC<ShipModuleButtonProps> = ({ title, shipModules, selection }) => {
  const provider = useContext(ShipInfoContext);

  const showShipModuleDialog = (shipModules: ShipModuleMap, selection: ShipModuleSelection) => {
    // Implement your dialog logic here
    Alert.alert('Ship Module Dialog', 'Implement your dialog here', [
      { text: 'OK', onPress: () => provider.updateSelection(selection) },
    ]);
  };

  return (
    <View style={{ paddingHorizontal: 16 }}>
      <Button
        title={title}
        onPress={() => showShipModuleDialog(shipModules, selection)}
      />
    </View>
  );
};

export default ShipModuleButton;


const ShipSurvivability: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.durability}</Text>
        <View style={styles.row}>
          <TextWithCaption
            title={Localisation.instance.health}
            value={provider.health}
          />
          <TextWithCaption
            title={Localisation.instance.torpedoProtection}
            value={provider.torpedoProtection}
          />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    backgroundColor: '#fff',
    margin: 8,
  },
  padding: {
    padding: 8,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
});

export default ShipSurvivability;


interface Ship {
  // Define the properties of the Ship interface based on your requirements
}

interface ShipMainBatteryProps {
  ship: Ship;
}

const ShipMainBattery: React.FC<ShipMainBatteryProps> = ({ ship }) => {
  return (
    <View>
      <Text>{/* Render ship details here */}</Text>
    </View>
  );
};

export default ShipMainBattery;


const ShipCard = ({ ship }) => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.artillery}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.gunReloadTime}
            value={provider.gunReloadTime}
          />
          <TextWithCaption
            title={Localisation.instance.gunRange}
            value={provider.gunRange}
          />
          <TextWithCaption
            title={Localisation.of(context).warship_weapon_configuration}
            value={provider.gunConfiguration}
          />
          <TextWithCaption
            title={Localisation.instance.gunDispersion}
            value=""
          />
          <TextWithCaption
            title={Localisation.instance.gunRotationTime}
            value={provider.gunRotationTime}
          />
          <TextWithCaption
            title="Sigma"
            value={provider.gunSigma}
          />
        </View>
        {provider.hasBurstFire && _renderBurstFire(provider.burstFireHolder)}
        <Text style={styles.gunName}>{provider.gunName}</Text>
        <Button
          title="Show AP Penetration Curve"
          onPress={() => showShipPenetrationDialog(ship)}
        />
        <View style={styles.wrap}>
          {provider.shells.map((shell, index) => (
            _renderShell(shell, index)
          ))}
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    margin: 10,
    backgroundColor: '#fff',
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    marginVertical: 8,
  },
  gunName: {
    textAlign: 'center',
    fontSize: 18,
    fontWeight: '600',
  },
});

const TextWithCaption = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text>{title}</Text>
    <Text>{value}</Text>
  </View>
);

const _renderBurstFire = (burstFireHolder) => {
  // Implement burst fire rendering logic here
};

const _renderShell = (shell, index) => {
  // Implement shell rendering logic here
};

export default ShipCard;


interface ShellHolder {
  name: string;
  burnChance?: number;
  weight?: number;
  damage?: number;
  velocity?: number;
  penetration?: number;
  overmatch?: number;
}

interface Props {
  shell: ShellHolder;
}

const RenderShell: React.FC<Props> = ({ shell }) => {
  return (
    <View>
      <Text style={{ fontSize: 24, fontWeight: 'bold' }}>{shell.name}</Text>
      {shell.burnChance !== undefined && (
        <TextWithCaption
          title={Localisation.instance.shellFireChance}
          value={shell.burnChance}
        />
      )}
      {shell.weight !== undefined && (
        <TextWithCaption
          title={Localisation.instance.shellWeight}
          value={shell.weight}
        />
      )}
      {shell.damage !== undefined && (
        <TextWithCaption
          title={Localisation.instance.gunDamage}
          value={shell.damage}
        />
      )}
      {shell.velocity !== undefined && (
        <TextWithCaption
          title={Localisation.instance.shellVelocity}
          value={shell.velocity}
        />
      )}
      {shell.penetration !== undefined && (
        <TextWithCaption
          title={Localisation.instance.shellPenetration}
          value={shell.penetration}
        />
      )}
      {shell.overmatch !== undefined && (
        <TextWithCaption
          title={Localisation.instance.warship_weapon_ap_overmatch}
          value={shell.overmatch}
        />
      )}
    </View>
  );
};

const TextWithCaption: React.FC<{ title: string; value: number }> = ({ title, value }) => {
  return (
    <View>
      <Text>{title}: {value}</Text>
    </View>
  );
};

export default RenderShell;


interface BurstFireHolder {
  shots: number;
  interval: string;
  reload: string;
  modifiers?: string;
}

interface Props {
  holder: BurstFireHolder | null;
}

const RenderBurstFire: React.FC<Props> = ({ holder }) => {
  if (holder === null) {
    console.assert(false, "Burst fire holder shouldn't be null here, not expected");
    return <View />;
  }

  return (
    <View style={styles.container}>
      <View style={styles.center}>
        <Text style={styles.title}>{Localisation.instance.burstFire}</Text>
      </View>
      <View style={styles.wrap}>
        <TextWithCaption title={Localisation.instance.burstFireCount} value={holder.shots.toString()} />
        <TextWithCaption title={Localisation.instance.burstFireInterval} value={holder.interval} />
        <TextWithCaption title={Localisation.instance.burstFireReload} value={holder.reload} />
      </View>
      {holder.modifiers && (
        <View style={styles.center}>
          <Text style={styles.modifiers} numberOfLines={1} adjustsFontSizeToFit>
            {holder.modifiers}
          </Text>
        </View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'stretch',
  },
  center: {
    alignItems: 'center',
  },
  title: {
    fontSize: 24, // Adjust according to your theme
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    flexWrap: 'wrap',
  },
  modifiers: {
    textAlign: 'center',
  },
});

export default RenderBurstFire;


const ShipSecondaries: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>
          {`${Localisation.instance.secondaryBattery} (${provider.secondaryRange})`}
        </Text>
        {provider.secondaryGuns.map((gun, index) => (
          <View key={index}>
            {renderSecondaries(gun)}
          </View>
        ))}
      </View>
    </View>
  );
};

const renderSecondaries = (gun: any) => {
  // Implement your rendering logic for each secondary gun here
  return (
    <Text>{gun.name}</Text> // Example rendering, replace with actual implementation
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    backgroundColor: 'white',
    margin: 8,
  },
  padding: {
    padding: 8,
  },
  title: {
    textAlign: 'center',
    fontSize: 20, // Adjust according to your theme
  },
});

export default ShipSecondaries;


interface SecondaryGunHolder {
  name: string;
  reloadTime?: string;
  velocity?: string;
  burnChance?: string;
  damage?: string;
  penetration?: string;
}

const Localisation = {
  instance: {
    gunReloadTime: 'Reload Time',
    shellVelocity: 'Shell Velocity',
    shellFireChance: 'Shell Fire Chance',
    gunDamage: 'Gun Damage',
    shellPenetration: 'Shell Penetration',
  },
};

const TextWithCaption: React.FC<{ title: string; value: string }> = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text style={styles.caption}>{title}</Text>
    <Text style={styles.value}>{value}</Text>
  </View>
);

const renderSecondaries = (info: SecondaryGunHolder) => {
  return (
    <View>
      <View style={styles.center}>
        <Text style={styles.title}>{info.name}</Text>
      </View>
      <View style={styles.wrap}>
        <TextWithCaption title={Localisation.instance.gunReloadTime} value={info.reloadTime ?? '-'} />
        <TextWithCaption title={Localisation.instance.shellVelocity} value={info.velocity ?? '-'} />
        <TextWithCaption title={Localisation.instance.shellFireChance} value={info.burnChance ?? '-'} />
        <TextWithCaption title={Localisation.instance.gunDamage} value={info.damage ?? '-'} />
        <TextWithCaption title={Localisation.instance.shellPenetration} value={info.penetration ?? '-'} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  center: {
    alignItems: 'center',
  },
  title: {
    fontSize: 24, // Adjust according to your theme
    fontWeight: 'bold', // Adjust according to your theme
  },
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    marginVertical: 16,
  },
  textWithCaption: {
    alignItems: 'center',
    margin: 8,
  },
  caption: {
    fontSize: 16, // Adjust according to your theme
  },
  value: {
    fontSize: 16, // Adjust according to your theme
  },
});

export default renderSecondaries;


const ShipPinger: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.sonar}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.reloadTime}
            value={provider.pingerReloadTime}
          />
          <TextWithCaption
            title={Localisation.instance.pingerDuration}
            value={provider.pingerDuration}
          />
          <TextWithCaption
            title={Localisation.instance.shellVelocity}
            value={provider.pingerSpeed}
          />
          <TextWithCaption
            title={Localisation.instance.maximumRange}
            value={provider.pingerRange}
          />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    margin: 8,
    backgroundColor: '#fff',
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    flexWrap: 'wrap',
    marginTop: 16,
  },
});

export default ShipPinger;


const ShipBattery: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.diveCapacity}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.batteryMaxCapacity}
            value={provider.submarineBatteryCapacity}
          />
          <TextWithCaption
            title={Localisation.instance.batteryConsumption}
            value={provider.submarineBatteryUseRate}
          />
          <TextWithCaption
            title={Localisation.instance.bateryRegen}
            value={provider.submarineBatteryRegen}
          />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    backgroundColor: '#fff',
    margin: 8,
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 16,
  },
});

export default ShipBattery;


const ShipTorpedo: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.torpedoes}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.torpedoReloadTime}
            value={provider.torpedoReloadTime}
          />
          <TextWithCaption
            title={Localisation.instance.warship_weapon_configuration}
            value={provider.torpedoConfiguration}
          />
          <TextWithCaption
            title={Localisation.instance.torpedoRotationTime}
            value={provider.torpedoRotationTime}
          />
        </View>
        {provider.torpedoes.map((torp, index) => (
          <View key={index}>
            {renderTorpedoInfo(torp)}
          </View>
        ))}
      </View>
    </View>
  );
};

const renderTorpedoInfo = (torp: any) => {
  // Implement the rendering logic for torpedo info here
  return (
    <Text key={torp.id}>{torp.name}</Text>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    margin: 8,
    backgroundColor: '#fff',
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginVertical: 16,
  },
});

export default ShipTorpedo;


interface TorpedoHolder {
  name: string;
  reactionTime?: string;
  damage?: string;
  visibility?: string;
  range?: string;
  speed?: string;
  floodChance?: string;
}

const Localisation = {
  instance: {
    torpedoDamage: 'Damage',
    torpedoDetection: 'Detection',
    torpedoRange: 'Range',
    torpedoSpeed: 'Speed',
    floodChance: 'Flood Chance',
  },
};

const TextWithCaption: React.FC<{ title: string; value: string }> = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text style={styles.caption}>{title}</Text>
    <Text style={styles.value}>{value}</Text>
  </View>
);

const renderTorpedoInfo = (info: TorpedoHolder) => {
  return (
    <View>
      <View style={styles.center}>
        <Text style={styles.title}>
          {`${info.name} (${info.reactionTime ?? '-'})`}
        </Text>
      </View>
      <View style={styles.wrap}>
        <TextWithCaption
          title={Localisation.instance.torpedoDamage}
          value={info.damage ?? '-'}
        />
        <TextWithCaption
          title={Localisation.instance.torpedoDetection}
          value={info.visibility ?? '-'}
        />
        <TextWithCaption
          title={Localisation.instance.torpedoRange}
          value={info.range ?? '-'}
        />
        <TextWithCaption
          title={Localisation.instance.torpedoSpeed}
          value={info.speed ?? '-'}
        />
        <TextWithCaption
          title={Localisation.instance.floodChance}
          value={info.floodChance ?? '-'}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  center: {
    alignItems: 'center',
  },
  title: {
    fontSize: 24, // Adjust according to your theme
    fontWeight: 'bold', // Adjust according to your theme
  },
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    marginTop: 16,
  },
  textWithCaption: {
    alignItems: 'center',
    margin: 8,
  },
  caption: {
    fontSize: 16, // Adjust according to your theme
  },
  value: {
    fontSize: 16, // Adjust according to your theme
    fontWeight: '600', // Adjust according to your theme
  },
});

export default renderTorpedoInfo;


const ShipAirDefense: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <Card style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.airDefense}</Text>
        {provider.airBubbles.map((bubble, index) => renderAirBubble(bubble, index))}
        {provider.airDefenses.map((aa, index) => renderAirDefense(aa, index))}
      </View>
    </Card>
  );
};

const renderAirBubble = (bubble: any, key: number) => {
  // Implement your rendering logic for air bubbles here
  return (
    <View key={key}>
      <Text>{bubble}</Text>
    </View>
  );
};

const renderAirDefense = (aa: any, key: number) => {
  // Implement your rendering logic for air defenses here
  return (
    <View key={key}>
      <Text>{aa}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    margin: 8,
    borderRadius: 8,
    elevation: 2,
  },
  padding: {
    padding: 8,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
});

export default ShipAirDefense;


interface AirBubbleHolder {
  explosions: number;
  damage: number;
  hitChance: number;
  range: number;
}

interface TextWithCaptionProps {
  title: string;
  value: number;
}

const TextWithCaption: React.FC<TextWithCaptionProps> = ({ title, value }) => {
  return (
    <View style={styles.captionContainer}>
      <Text style={styles.title}>{title}</Text>
      <Text style={styles.value}>{value}</Text>
    </View>
  );
};

const renderAirBubble = (info: AirBubbleHolder) => {
  return (
    <View style={styles.wrap}>
      <TextWithCaption
        title="Bubble Explosion"
        value={info.explosions}
      />
      <TextWithCaption
        title="Gun Damage"
        value={info.damage}
      />
      <TextWithCaption
        title="Hit Chance"
        value={info.hitChance}
      />
      <TextWithCaption
        title="AA Range"
        value={info.range}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    margin: 16,
  },
  captionContainer: {
    alignItems: 'center',
    margin: 8,
  },
  title: {
    fontWeight: 'bold',
  },
  value: {
    marginTop: 4,
  },
});

export default renderAirBubble;


interface AirDefenseHolder {
  name: string;
  damage: number;
  hitChance: number;
  range: number;
}

const TextWithCaption: React.FC<{ title: string; value: number }> = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text style={styles.caption}>{title}</Text>
    <Text style={styles.value}>{value}</Text>
  </View>
);

const renderAirDefense = (info: AirDefenseHolder) => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>{info.name}</Text>
      <View style={styles.wrap}>
        <TextWithCaption title={Localisation.instance.gunDamage} value={info.damage} />
        <TextWithCaption title={Localisation.instance.hitChance} value={info.hitChance} />
        <TextWithCaption title={Localisation.instance.aaRange} value={info.range} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
  },
  title: {
    fontSize: 24, // Adjust based on your theme
    fontWeight: 'bold', // Adjust based on your theme
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 16,
    flexWrap: 'wrap',
  },
  textWithCaption: {
    alignItems: 'center',
  },
  caption: {
    fontSize: 16, // Adjust based on your theme
  },
  value: {
    fontSize: 16, // Adjust based on your theme
    fontWeight: 'bold', // Adjust based on your theme
  },
});

export default renderAirDefense;


const ShipAirSupport: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.airSupport}</Text>
        <Text style={styles.title}>{provider.airSupportName}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.reloadTime}
            value={provider.airSupportReload}
          />
          <TextWithCaption
            title={Localisation.instance.availableFlights}
            value={provider.airSupportCharges}
          />
          <TextWithCaption
            title={Localisation.instance.airSupportTotalPlanes}
            value={provider.airSupportTotalPlanes}
          />
          <TextWithCaption
            title={Localisation.instance.planeHealth}
            value={provider.airSupportPlaneHealth}
          />
          <TextWithCaption
            title={Localisation.instance.maximumRange}
            value={provider.airSupportRange}
          />
        </View>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.numberOfBombs}
            value={provider.airSupportBombs}
          />
          <TextWithCaption
            title={Localisation.instance.bombDamage}
            value={provider.airSupportBombDamage}
          />
          {provider.airSupportBombPeneration !== '-' && (
            <TextWithCaption
              title={Localisation.instance.shellPenetration}
              value={provider.airSupportBombPeneration}
            />
          )}
          {provider.airSupportBombBurnChance !== '-' && (
            <TextWithCaption
              title={Localisation.instance.shellFireChance}
              value={provider.airSupportBombBurnChance}
            />
          )}
          {provider.airSupportBombFloodChance !== '-' && (
            <TextWithCaption
              title={Localisation.instance.floodChance}
              value={provider.airSupportBombFloodChance}
            />
          )}
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    margin: 8,
    backgroundColor: '#fff',
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    flexWrap: 'wrap',
    marginVertical: 8,
  },
});

export default ShipAirSupport;


const ShipDepthCharge: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{Localisation.instance.depthCharge}</Text>
      <View style={styles.wrap}>
        <TextWithCaption
          title={Localisation.instance.bombDamage}
          value={provider.depthChargeDamage}
        />
        <TextWithCaption
          title={Localisation.instance.warship_weapon_configuration}
          value={provider.depthChargeConfig}
        />
        <TextWithCaption
          title={Localisation.instance.reloadTime}
          value={provider.depthChargeReload}
        />
        <TextWithCaption
          title={Localisation.instance.shellFireChance}
          value={provider.depthChargeBurnChance}
        />
        <TextWithCaption
          title={Localisation.instance.floodChance}
          value={provider.depthChargeFloodChance}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 8,
    flex: 1,
  },
  title: {
    textAlign: 'center',
    fontSize: 24,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    marginTop: 16,
  },
});

export default ShipDepthCharge;


const ShipSpecial: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{provider.specialName}</Text>
        <Text style={styles.description}>{provider.specialDescription}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.actionTime}
            value={provider.specialDuration}
          />
          <TextWithCaption
            title={Localisation.instance.requiredHits}
            value={provider.specialHitsRequired}
          />
        </View>
        <Text style={styles.modifier}>{provider.specialModifier}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    backgroundColor: 'white',
    margin: 8,
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  description: {
    textAlign: 'center',
    marginVertical: 8,
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginVertical: 8,
  },
  modifier: {
    textAlign: 'center',
    marginTop: 8,
  },
});

export default ShipSpecial;


const ShipMobility: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.mobility}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.rudderTime}
            value={provider.rudderTime}
          />
          <TextWithCaption
            title={Localisation.instance.maxSpeed}
            value={provider.maxSpeed}
          />
          <TextWithCaption
            title={Localisation.instance.turningRadius}
            value={provider.turningRadius}
          />
        </View>
      </View>
    </View>
  );
};

const TextWithCaption: React.FC<{ title: string; value: string }> = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text style={styles.caption}>{title}</Text>
    <Text style={styles.value}>{value}</Text>
  </View>
);

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    margin: 8,
    backgroundColor: '#fff',
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 16,
  },
  textWithCaption: {
    alignItems: 'center',
  },
  caption: {
    fontSize: 14,
    color: '#555',
  },
  value: {
    fontSize: 16,
    fontWeight: '600',
  },
});

export default ShipMobility;


const ShipVisibility: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <View style={styles.padding}>
        <Text style={styles.title}>{Localisation.instance.visibility}</Text>
        <View style={styles.wrap}>
          <TextWithCaption
            title={Localisation.instance.airDetection}
            value={provider.planeVisibility}
          />
          <TextWithCaption
            title={Localisation.instance.seaDetection}
            value={provider.seaVisibility}
          />
        </View>
      </View>
    </View>
  );
};

const TextWithCaption: React.FC<{ title: string; value: string }> = ({ title, value }) => (
  <View style={styles.textWithCaption}>
    <Text style={styles.caption}>{title}</Text>
    <Text style={styles.value}>{value}</Text>
  </View>
);

const styles = StyleSheet.create({
  card: {
    borderRadius: 8,
    elevation: 2,
    margin: 8,
    backgroundColor: '#fff',
  },
  padding: {
    padding: 16,
  },
  title: {
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  wrap: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 16,
  },
  textWithCaption: {
    alignItems: 'center',
  },
  caption: {
    fontSize: 16,
    color: '#555',
  },
  value: {
    fontSize: 18,
    fontWeight: '600',
  },
});

export default ShipVisibility;


const ShipUpgrades: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <Text style={styles.title}>{Localisation.instance.upgrades}</Text>
      <ScrollView horizontal>
        <View style={styles.row}>
          {provider.upgrades.map((slots, index) => {
            if (slots.length === 0) {
              throw new Error('There should be at least one slot');
            }
            const slotNumber = slots[0].slot + 1;
            return (
              <View key={index} style={styles.column}>
                <Text>{slotNumber.toString()}</Text>
                {slots.map((upgrade, idx) => renderUpgrade(upgrade, idx))}
              </View>
            );
          })}
        </View>
      </ScrollView>
    </View>
  );
};

const renderUpgrade = (upgrade: any, key: number) => {
  // Implement your upgrade rendering logic here
  return <Text key={key}>{upgrade.name}</Text>; // Example rendering
};

const styles = StyleSheet.create({
  card: {
    padding: 16,
    borderRadius: 8,
    backgroundColor: '#fff',
    elevation: 2,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  row: {
    flexDirection: 'row',
  },
  column: {
    marginRight: 16,
  },
});

export default ShipUpgrades;


interface Modernization {
    icon: string;
}

const renderUpgrade = (upgrade: Modernization) => {
    const name = upgrade.icon;
    return (
        <Image 
            source={{ uri: `data/live/app/assets/upgrades/${name}.png` }} 
            style={{ width: 100, height: 100 }} // Adjust the size as needed
        />
    );
};

export default renderUpgrade;


const ShipNextShip: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  const renderNextShip = (ship: any) => {
    // Implement your ship rendering logic here
    return (
      <View key={ship.id} style={styles.shipContainer}>
        <Text>{ship.name}</Text>
      </View>
    );
  };

  return (
    <View style={styles.card}>
      <Text style={styles.title}>{Localisation.instance.nextShip}</Text>
      <ScrollView horizontal>
        <View style={styles.row}>
          {provider.nextShips.map((ship) => renderNextShip(ship))}
        </View>
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    padding: 16,
    borderRadius: 8,
    backgroundColor: '#fff',
    elevation: 2,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  row: {
    flexDirection: 'row',
  },
  shipContainer: {
    marginRight: 16,
  },
});

export default ShipNextShip;


interface Ship {
  index: number;
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
}

interface ShipCellProps {
  icon: number;
  name: string;
  isPremium: boolean;
  isSpecial: boolean;
  onTap: () => void;
}

const ShipCell: React.FC<ShipCellProps> = ({ icon, name, isPremium, isSpecial, onTap }) => {
  return (
    <TouchableOpacity onPress={onTap}>
      <View>
        <Text>{icon}</Text>
        <Text>{name}</Text>
        <Text>{isPremium ? 'Premium' : 'Regular'}</Text>
        <Text>{isSpecial ? 'Special' : 'Normal'}</Text>
      </View>
    </TouchableOpacity>
  );
};

const renderNextShip = (nextShip: Ship | null) => {
  if (!nextShip) return <View />;
  
  const navigation = useNavigation();

  return (
    <ShipCell
      icon={nextShip.index}
      name={Localisation.instance.stringOf(nextShip.name) || '-'}
      isPremium={nextShip.isPremium}
      isSpecial={nextShip.isSpecial}
      onTap={() => {
        navigation.navigate('ShipInfoPage', { ship: nextShip });
      }}
    />
  );
};

export default renderNextShip;


const ShipConsumables: React.FC = () => {
  const provider = useContext(ShipInfoContext);

  return (
    <View style={styles.card}>
      <Text style={styles.title}>{Localisation.instance.consumables}</Text>
      <ScrollView horizontal>
        <View style={styles.row}>
          {provider.consumables.map((consumables, index) => {
            if (consumables.length === 0) {
              throw new Error('There should be at least one consumable');
            }

            return (
              <View key={index} style={styles.column}>
                {consumables.map((consumable, idx) => renderConsumable(consumable, idx))}
              </View>
            );
          })}
        </View>
      </ScrollView>
    </View>
  );
};

const renderConsumable = (consumable: any, index: number) => {
  // Implement your consumable rendering logic here
  return (
    <View key={index} style={styles.consumable}>
      <Text>{consumable.name}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    padding: 16,
    borderRadius: 8,
    backgroundColor: '#fff',
    elevation: 2,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  row: {
    flexDirection: 'row',
  },
  column: {
    marginRight: 16,
  },
  consumable: {
    marginBottom: 8,
  },
});

export default ShipConsumables;


interface Consumable {
  name: string;
  type: string;
}

interface Ability {
  iconIDs?: string;
  consumableCount?: number;
  toString: () => string;
}

interface Info {
  abilities: { [key: string]: Ability };
  description: string;
  name: string;
  alter?: { [key: string]: { description: string; name: string } };
}

const renderConsumable = (consumable: Consumable) => {
  const name = consumable.name;
  const type = consumable.type;
  const info: Info | null = GameRepository.instance.abilityOf(name);
  
  if (info === null) {
    console.assert(false, 'Consumable is not found');
    return null;
  }

  const ability = info.abilities[type];

  let icon: string;
  let description: string;
  let displayName: string;

  const iconID = ability?.iconIDs;
  if (iconID == null) {
    icon = name;
    description = Localisation.instance.stringOf(info.description) ?? '';
    displayName = Localisation.instance.stringOf(info.name) ?? '';
  } else {
    icon = iconID;
    const alter = info.alter?.[iconID];
    if (alter == null) {
      console.assert(false, "Alter isn't found in the ability");
      return null;
    }
    description = Localisation.instance.stringOf(alter.description) ?? '';
    displayName = Localisation.instance.stringOf(alter.name) ?? '';
  }

  const abilityModifier = ability.toString();
  const consumableCount = ability?.consumableCount;

  return (
    <TouchableOpacity
      onPress={() => {
        Alert.alert(displayName, `${description}\n\n${abilityModifier}`);
      }}
    >
      <View>
        <Image
          source={{ uri: `data/live/app/assets/consumables/${icon}.png` }}
          style={{ width: 50, height: 50 }} // Adjust size as needed
        />
        {consumableCount != null && <Text>{consumableCount}</Text>}
      </View>
    </TouchableOpacity>
  );
};

export default renderConsumable;


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