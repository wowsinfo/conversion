
interface PlayerRecentOverviewProps {
  pvp?: any; // Replace 'any' with the appropriate type based on your data structure
}

const PlayerRecentOverview: React.FC<PlayerRecentOverviewProps> = ({ pvp }) => {
  return (
    <View>
      {pvp && (
        <Text>{JSON.stringify(pvp)}</Text> // Display pvp data, customize as needed
      )}
    </View>
  );
};

export default PlayerRecentOverview;

interface PlayerRecentPvP {
  // Define the properties of PlayerRecentPvP based on your requirements
}

interface PlayerRecentOverview {
  pvp?: Record<string, PlayerRecentPvP>;
}

const PlayerRecentOverview = {
  fromJson(json: Record<string, any>): PlayerRecentOverview {
    if (Object.keys(json).length === 0) return {};

    const player = Object.values(json)[0];
    if (typeof player === 'object' && player !== null) {
      return {
        pvp: typeof player.pvp === 'object' && player.pvp !== null
          ? Object.entries(player.pvp).reduce((acc, [key, value]) => {
              acc[key] = PlayerRecentPvP.fromJson(value);
              return acc;
            }, {} as Record<string, PlayerRecentPvP>)
          : undefined,
      };
    }

    return {};
  },
};

// Assuming PlayerRecentPvP has a fromJson method
PlayerRecentPvP.fromJson = (value: any): PlayerRecentPvP => {
  // Implement the conversion logic for PlayerRecentPvP
  return {}; // Replace with actual implementation
};

interface PlayerRecentPvP {
  capturePoints?: number;
  accountId?: string;
  maxXp?: number;
  wins?: number;
  planesKilled?: number;
  battles?: number;
  damageDealt?: number;
  battleType?: string;
  date?: string;
  xp?: number;
  frags?: number;
  survivedBattles?: number;
  droppedCapturePoints?: number;
}

const toJson = (pvp: Record<string, PlayerRecentPvP> | null): Record<string, any> | null => {
  if (pvp === null) {
    return null;
  }
  return Object.fromEntries(
    Object.entries(pvp).map(([k, v]) => [
      k,
      {
        ...v,
        date: v.date ? new Date(v.date).toISOString() : undefined,
      },
    ])
  );
};

interface PlayerRecentPvP {
  capturePoints?: number;
  accountId?: number;
  maxXp?: number;
  wins?: number;
  planesKilled?: number;
  battles?: number;
  damageDealt?: number;
  battleType?: string;
  date?: string;
  xp?: number;
  frags?: number;
  survivedBattles?: number;
  droppedCapturePoints?: number;

  winrate: number;
  avgDamage: number;
}

const rate = (wins?: number, battles?: number): number => {
  if (battles && battles > 0) {
    return (wins || 0) / battles;
  }
  return 0;
};

const average = (damageDealt?: number, battles?: number): number => {
  if (battles && battles > 0) {
    return (damageDealt || 0) / battles;
  }
  return 0;
};

const createPlayerRecentPvP = (data: any): PlayerRecentPvP => {
  return {
    capturePoints: data.capture_points,
    accountId: data.account_id,
    maxXp: data.max_xp,
    wins: data.wins,
    planesKilled: data.planes_killed,
    battles: data.battles,
    damageDealt: data.damage_dealt,
    battleType: data.battle_type,
    date: data.date,
    xp: data.xp,
    frags: data.frags,
    survivedBattles: data.survived_battles,
    droppedCapturePoints: data.dropped_capture_points,
    winrate: rate(data.wins, data.battles),
    avgDamage: average(data.damage_dealt, data.battles),
  };
};

const toJson = (player: PlayerRecentPvP): any => {
  return {
    capture_points: player.capturePoints,
    account_id: player.accountId,
    max_xp: player.maxXp,
    wins: player.wins,
    planes_killed: player.planesKilled,
    battles: player.battles,
    damage_dealt: player.damageDealt,
    battle_type: player.battleType,
    date: player.date,
    xp: player.xp,
    frags: player.frags,
    survived_battles: player.survivedBattles,
    dropped_capture_points: player.droppedCapturePoints,
  };
};