
const ClanContext = createContext<any>(null);

export const ClanProvider: React.FC<{ clan: ClanResult }> = ({ clan, children }) => {
  const logger = new Logger('ClanProvider');
  const [info, setInfo] = useState<ClanInformation | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    logger.info('Started loading clan info');
    const clanId = clan.clanId;

    if (clanId) {
      getClanInformation(clanId.toString()).then((clanData) => {
        if (clanData.data) {
          setInfo(clanData.data);
        }
        setLoading(false);
      });
    } else {
      setLoading(false);
    }
  }, [clan, logger]);

  return (
    <ClanContext.Provider value={{ info, loading }}>
      {children}
    </ClanContext.Provider>
  );
};

export const useClan = () => {
  return useContext(ClanContext);
};


const ClanInfoComponent: React.FC = () => {
  const [info, setInfo] = useState<ClanInformation | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  const service = new WargamingService(GameServer.fromIndex(UserRepository.instance.gameServer));

  useEffect(() => {
    const fetchClanInfo = async () => {
      setLoading(true);
      const clanInfo = await service.getClanInfo(); // Assuming this method exists
      setInfo(clanInfo);
      setLoading(false);
    };

    fetchClanInfo();
  }, [service]);

  const tag = info?.tag ?? '-';
  const tagDescription = info?.name ?? '-';
  const createdDate = info?.createdAt ? info.createdAt.dateTimeString : '-';

  if (loading) {
    return <div>Loading...</div>; // Replace with your loading component
  }

  return (
    <div>
      <h1>{tag}</h1>
      <p>{tagDescription}</p>
      <p>{createdDate}</p>
    </div>
  );
};

export default ClanInfoComponent;

  private _info?: {
    creatorName?: string;
    leaderName?: string;
    description?: string;
    membersCount?: number;
    members?: { [key: string]: ClanMember };
  };

  constructor(info?: {
    creatorName?: string;
    leaderName?: string;
    description?: string;
    membersCount?: number;
    members?: { [key: string]: ClanMember };
  }) {
    this._info = info;
  }

  get creatorName(): string {
    return this._info?.creatorName ?? '-';
  }

  get leaderName(): string {
    return this._info?.leaderName ?? '-';
  }

  get description(): string | undefined {
    return this._info?.description;
  }

  get memberCount(): number {
    return this._info?.membersCount ?? 0;
  }

  get members(): Iterable<ClanMember> | undefined {
    return this._info?.members ? Object.values(this._info.members) : undefined;
  }
}

interface ClanMember {
  // Define the properties of ClanMember here
}