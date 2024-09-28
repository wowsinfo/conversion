
interface ClanInformationProps {
  membersCount?: number;
  name?: string;
  creatorName?: string;
  createdAt?: Date;
  tag?: string;
  updatedAt?: Date;
  leaderName?: string;
  membersIds?: string[];
  creatorId?: string;
  clanId?: string;
  members?: any[]; // Replace 'any' with the appropriate type for members
  oldName?: string;
  isClanDisbanded?: boolean;
  renamedAt?: Date;
  oldTag?: string;
  leaderId?: string;
  description?: string;
}

const ClanInformation: React.FC<ClanInformationProps> = ({
  membersCount,
  name,
  creatorName,
  createdAt,
  tag,
  updatedAt,
  leaderName,
  membersIds,
  creatorId,
  clanId,
  members,
  oldName,
  isClanDisbanded,
  renamedAt,
  oldTag,
  leaderId,
  description,
}) => {
  return (
    <div>
      <h1>{name}</h1>
      <p>Tag: {tag}</p>
      <p>Members Count: {membersCount}</p>
      <p>Creator: {creatorName}</p>
      <p>Created At: {createdAt?.toString()}</p>
      <p>Updated At: {updatedAt?.toString()}</p>
      <p>Leader: {leaderName}</p>
      <p>Description: {description}</p>
      <p>Is Clan Disbanded: {isClanDisbanded ? 'Yes' : 'No'}</p>
      <p>Old Name: {oldName}</p>
      <p>Old Tag: {oldTag}</p>
      <p>Renamed At: {renamedAt?.toString()}</p>
      <p>Members IDs: {membersIds?.join(', ')}</p>
      <p>Creator ID: {creatorId}</p>
      <p>Clan ID: {clanId}</p>
      {/* Render members if needed */}
    </div>
  );
};

export default ClanInformation;

interface TimeStampDate {
  // Define the structure of TimeStampDate based on your requirements
}

interface ClanMember {
  // Define the structure of ClanMember based on your requirements
}

interface ClanInformation {
  membersCount?: number;
  name?: string;
  creatorName?: string;
  createdAt?: TimeStampDate;
  tag?: string;
  updatedAt?: TimeStampDate;
  leaderName?: string;
  membersIds?: number[];
  creatorId?: number;
  clanId?: number;
  members?: Record<string, ClanMember>;
  oldName?: string;
  isClanDisbanded?: boolean;
  renamedAt?: TimeStampDate;
  oldTag?: string;
  leaderId?: number;
  description?: string;
}

const fromJson = (json: Record<string, any>): ClanInformation => {
  if (Object.keys(json).length === 0) return {};

  const clan = Object.values(json)[0];
  if (typeof clan === 'object' && clan !== null) {
    return {
      membersCount: clan.members_count,
      name: clan.name,
      creatorName: clan.creator_name,
      createdAt: clan.created_at ? new TimeStampDate(clan.created_at) : undefined,
      tag: clan.tag,
      updatedAt: clan.updated_at ? new TimeStampDate(clan.updated_at) : undefined,
      leaderName: clan.leader_name,
      membersIds: clan.members_ids ? Array.from(clan.members_ids) : undefined,
      creatorId: clan.creator_id,
      clanId: clan.clan_id,
      members: clan.members ? Object.fromEntries(
        Object.entries(clan.members).map(([k, v]) => [k, ClanMember.fromJson(v)])
      ) : undefined,
      oldName: clan.old_name,
      isClanDisbanded: clan.is_clan_disbanded,
      renamedAt: clan.renamed_at ? new TimeStampDate(clan.renamed_at) : undefined,
      oldTag: clan.old_tag,
      leaderId: clan.leader_id,
      description: clan.description,
    };
  }

  throw new Error('Clan is valid, but there is no information');
};

class ClanInformation {
  membersCount: number;
  name: string;
  creatorName: string;
  createdAt: Date;
  tag: string;
  updatedAt: Date;
  leaderName: string;
  membersIds: string[];
  creatorId: string;
  clanId: string;
  members: any[];
  oldName: string;
  isClanDisbanded: boolean;
  renamedAt: Date;
  oldTag: string;
  leaderId: string;
  description: string;

  constructor(
    membersCount: number,
    name: string,
    creatorName: string,
    createdAt: Date,
    tag: string,
    updatedAt: Date,
    leaderName: string,
    membersIds: string[],
    creatorId: string,
    clanId: string,
    members: any[],
    oldName: string,
    isClanDisbanded: boolean,
    renamedAt: Date,
    oldTag: string,
    leaderId: string,
    description: string
  ) {
    this.membersCount = membersCount;
    this.name = name;
    this.creatorName = creatorName;
    this.createdAt = createdAt;
    this.tag = tag;
    this.updatedAt = updatedAt;
    this.leaderName = leaderName;
    this.membersIds = membersIds;
    this.creatorId = creatorId;
    this.clanId = clanId;
    this.members = members;
    this.oldName = oldName;
    this.isClanDisbanded = isClanDisbanded;
    this.renamedAt = renamedAt;
    this.oldTag = oldTag;
    this.leaderId = leaderId;
    this.description = description;
  }

  toString(): string {
    return `ClanInformation(membersCount: ${this.membersCount}, name: ${this.name}, creatorName: ${this.creatorName}, createdAt: ${this.createdAt}, tag: ${this.tag}, updatedAt: ${this.updatedAt}, leaderName: ${this.leaderName}, membersIds: ${this.membersIds}, creatorId: ${this.creatorId}, clanId: ${this.clanId}, members: ${this.members}, oldName: ${this.oldName}, isClanDisbanded: ${this.isClanDisbanded}, renamedAt: ${this.renamedAt}, oldTag: ${this.oldTag}, leaderId: ${this.leaderId}, description: ${this.description})`;
  }
}


interface ClanMemberProps {
  role?: string;
  joinedAt?: Date;
  accountId?: string;
  accountName?: string;
}

const ClanMember: React.FC<ClanMemberProps> = ({
  role,
  joinedAt,
  accountId,
  accountName,
}) => {
  return (
    <View>
      <Text>Role: {role}</Text>
      <Text>Joined At: {joinedAt?.toString()}</Text>
      <Text>Account ID: {accountId}</Text>
      <Text>Account Name: {accountName}</Text>
    </View>
  );
};

export default ClanMember;


interface TimeStampDate {
  // Define the structure of TimeStampDate based on your requirements
  // For example:
  date: Date;
}

enum Role {
  COMMANDER = 'commander',
  COMMISSIONED_OFFICER = 'commissioned_officer',
  EXECUTIVE_OFFICER = 'executive_officer',
  OFFICER = 'officer',
  PRIVATE = 'private',
  RECRUITMENT_OFFICER = 'recruitment_officer',
}

interface ClanMember {
  role?: Role;
  joinedAt?: TimeStampDate;
  accountId?: number;
  accountName?: string;
}

const mapRole = (role: string | null): Role | undefined => {
  if (!role) return undefined;
  switch (role) {
    case 'commander':
      return Role.COMMANDER;
    case 'commissioned_officer':
      return Role.COMMISSIONED_OFFICER;
    case 'executive_officer':
      return Role.EXECUTIVE_OFFICER;
    case 'officer':
      return Role.OFFICER;
    case 'private':
      return Role.PRIVATE;
    case 'recruitment_officer':
      return Role.RECRUITMENT_OFFICER;
    default:
      return undefined;
  }
};

const fromJson = (json: any): ClanMember => {
  return {
    role: mapRole(json.role),
    joinedAt: json.joined_at ? { date: new Date(json.joined_at) } : undefined,
    accountId: json.account_id,
    accountName: json.account_name,
  };
};

export { ClanMember, fromJson, Role };