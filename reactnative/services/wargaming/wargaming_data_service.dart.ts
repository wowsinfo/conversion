
interface WargamingDataServiceProps {
  server: GameServer;
  language?: string;
}

class WargamingDataService extends BaseService {
  private server: string;
  private language: string;

  constructor({ server, language = 'en' }: WargamingDataServiceProps) {
    super();
    this.server = server.domain;
    this.language = language;
  }

  // Additional methods for data downloading can be implemented here
}

export default WargamingDataService;

class ApiService {
  private server: string;
  private language: string;
  private applicationId: string;

  constructor(server: string, language: string, apiKey: string) {
    this.server = server;
    this.language = language;
    this.applicationId = `?application_id=${apiKey}`;
  }

  get baseUrl(): string {
    return `https://api.worldofwarships.${this.server}/wows`;
  }

  get wgnUrl(): string {
    return `https://api.worldoftanks.${this.server}/wgn`;
  }
}