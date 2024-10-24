
interface Encodable {
  encode(): string;
}

interface SearchResult extends Encodable {
  displayName: string;
  id: string;
}

class PlayerResult implements SearchResult {
  nickname?: string;
  accountId?: string;
  createdAt?: Date;

  constructor(nickname?: string, accountId?: string, createdAt?: Date) {
    this.nickname = nickname;
    this.accountId = accountId;
    this.createdAt = createdAt;
  }

  get displayName(): string {
    return this.nickname || 'Unknown Player';
  }

  get id(): string {
    return this.accountId || 'Unknown ID';
  }

  encode(): string {
    return JSON.stringify({
      nickname: this.nickname,
      accountId: this.accountId,
      createdAt: this.createdAt,
    });
  }
}

interface TimeStampDate {
  timeStamp: string;
}

interface PlayerResult {
  nickname?: string;
  accountId?: number;
  createdAt?: TimeStampDate | null;
}

const PlayerResult = {
  fromJson(json: any): PlayerResult {
    return {
      nickname: json.nickname,
      accountId: json.account_id,
      createdAt: json.created_at ? { timeStamp: json.created_at } : null,
    };
  },

  toJson(player: PlayerResult): any {
    return {
      nickname: player.nickname,
      account_id: player.accountId,
      created_at: player.createdAt?.timeStamp,
    };
  },

  toString(player: PlayerResult): string {
    return `PlayerResult(nickname: ${player.nickname}, accountId: ${player.accountId}, createdAt: ${player.createdAt})`;
  },
};

export default PlayerResult;


interface SearchResult {
  displayName: string;
  id: string;
}

interface ClanResultProps {
  membersCount?: number;
  createdAt?: Date;
  clanId?: string;
  tag?: string;
  name?: string;
}

const ClanResult: React.FC<ClanResultProps> = ({
  membersCount,
  createdAt,
  clanId,
  tag,
  name,
}) => {
  const displayName = name || '-';
  const id = clanId || '-';

  return (
    <View>
      <Text>Display Name: {displayName}</Text>
      <Text>ID: {id}</Text>
      <Text>Members Count: {membersCount}</Text>
      <Text>Created At: {createdAt?.toString()}</Text>
      <Text>Tag: {tag}</Text>
    </View>
  );
};

export default ClanResult;

interface TimeStampDate {
  timeStamp: number;
}

interface ClanResult {
  membersCount?: number;
  createdAt?: TimeStampDate | null;
  clanId?: number;
  tag?: string;
  name?: string;
}

const fromJson = (json: any): ClanResult => ({
  membersCount: json.members_count,
  createdAt: json.created_at ? { timeStamp: json.created_at } : null,
  clanId: json.clan_id,
  tag: json.tag,
  name: json.name,
});

const toJson = (clanResult: ClanResult): any => ({
  members_count: clanResult.membersCount,
  created_at: clanResult.createdAt?.timeStamp,
  clan_id: clanResult.clanId,
  tag: clanResult.tag,
  name: clanResult.name,
});

const toString = (clanResult: ClanResult): string => {
  return `ClanResult(membersCount: ${clanResult.membersCount}, createdAt: ${clanResult.createdAt}, clanId: ${clanResult.clanId}, tag: ${clanResult.tag}, name: ${clanResult.name})`;
};

  tag?: string;
  clanId?: number;

  get displayName(): string {
    return this.tag ?? '-';
  }

  get id(): string {
    return this.clanId?.toString() ?? '-';
  }
}