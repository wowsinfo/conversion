
type ModeStatisticsProps = {
  maxFragsBattle?: number;
  draws?: number;
  maxXp?: number;
  wins?: number;
  planesKilled?: number;
  losses?: number;
  battles?: number;
  maxDamageDealt?: number;
  damageDealt?: number;
  maxPlanesKilled?: number;
  torpedoes?: number;
  aircraft?: number;
  ramming?: number;
  mainBattery?: number;
  secondaries?: number;
  survivedWins?: number;
  frags?: number;
  xp?: number;
  survivedBattles?: number;
  damageToBuildings?: number;
  maxShipsSpottedShipId?: number;
  maxDamageScouting?: number;
  artAgro?: number;
  maxXpShipId?: number;
  shipsSpotted?: number;
  maxFragsShipId?: number;
  droppedCapturePoints?: number;
  maxDamageDealtToBuildings?: number;
  torpedoAgro?: number;
  controlCapturedPoints?: number;
  battlesSince510?: number;
  maxTotalAgroShipId?: number;
  maxShipsSpotted?: number;
  maxSuppressionsShipId?: number;
  damageScouting?: number;
  maxTotalAgro?: number;
  capturePoints?: number;
  suppressionsCount?: number;
  maxSuppressionsCount?: number;
  maxPlanesKilledShipId?: number;
  teamCapturePoints?: number;
  controlDroppedPoints?: number;
  maxDamageDealtToBuildingsShipId?: number;
  maxDamageDealtShipId?: number;
  maxScoutingDamageShipId?: number;
  teamDroppedCapturePoint?: number;
  battlesSince512?: number;
};

const ModeStatistics: React.FC<ModeStatisticsProps> = ({
  maxFragsBattle,
  draws,
  maxXp,
  wins,
  planesKilled,
  losses,
  battles,
  maxDamageDealt,
  damageDealt,
  maxPlanesKilled,
  torpedoes,
  aircraft,
  ramming,
  mainBattery,
  secondaries,
  survivedWins,
  frags,
  xp,
  survivedBattles,
  damageToBuildings,
  maxShipsSpottedShipId,
  maxDamageScouting,
  artAgro,
  maxXpShipId,
  shipsSpotted,
  maxFragsShipId,
  droppedCapturePoints,
  maxDamageDealtToBuildings,
  torpedoAgro,
  controlCapturedPoints,
  battlesSince510,
  maxTotalAgroShipId,
  maxShipsSpotted,
  maxSuppressionsShipId,
  damageScouting,
  maxTotalAgro,
  capturePoints,
  suppressionsCount,
  maxSuppressionsCount,
  maxPlanesKilledShipId,
  teamCapturePoints,
  controlDroppedPoints,
  maxDamageDealtToBuildingsShipId,
  maxDamageDealtShipId,
  maxScoutingDamageShipId,
  teamDroppedCapturePoint,
  battlesSince512,
}) => {
  return (
    <View>
      <Text>Max Frags Battle: {maxFragsBattle}</Text>
      <Text>Draws: {draws}</Text>
      <Text>Max XP: {maxXp}</Text>
      <Text>Wins: {wins}</Text>
      <Text>Planes Killed: {planesKilled}</Text>
      <Text>Losses: {losses}</Text>
      <Text>Battles: {battles}</Text>
      <Text>Max Damage Dealt: {maxDamageDealt}</Text>
      <Text>Damage Dealt: {damageDealt}</Text>
      <Text>Max Planes Killed: {maxPlanesKilled}</Text>
      <Text>Torpedoes: {torpedoes}</Text>
      <Text>Aircraft: {aircraft}</Text>
      <Text>Ramming: {ramming}</Text>
      <Text>Main Battery: {mainBattery}</Text>
      <Text>Secondaries: {secondaries}</Text>
      <Text>Survived Wins: {survivedWins}</Text>
      <Text>Frags: {frags}</Text>
      <Text>XP: {xp}</Text>
      <Text>Survived Battles: {survivedBattles}</Text>
      <Text>Damage To Buildings: {damageToBuildings}</Text>
      <Text>Max Ships Spotted Ship ID: {maxShipsSpottedShipId}</Text>
      <Text>Max Damage Scouting: {maxDamageScouting}</Text>
      <Text>Art Agro: {artAgro}</Text>
      <Text>Max XP Ship ID: {maxXpShipId}</Text>
      <Text>Ships Spotted: {shipsSpotted}</Text>
      <Text>Max Frags Ship ID: {maxFragsShipId}</Text>
      <Text>Dropped Capture Points: {droppedCapturePoints}</Text>
      <Text>Max Damage Dealt To Buildings: {maxDamageDealtToBuildings}</Text>
      <Text>Torpedo Agro: {torpedoAgro}</Text>
      <Text>Control Captured Points: {controlCapturedPoints}</Text>
      <Text>Battles Since 510: {battlesSince510}</Text>
      <Text>Max Total Agro Ship ID: {maxTotalAgroShipId}</Text>
      <Text>Max Ships Spotted: {maxShipsSpotted}</Text>
      <Text>Max Suppressions Ship ID: {maxSuppressionsShipId}</Text>
      <Text>Damage Scouting: {damageScouting}</Text>
      <Text>Max Total Agro: {maxTotalAgro}</Text>
      <Text>Capture Points: {capturePoints}</Text>
      <Text>Suppressions Count: {suppressionsCount}</Text>
      <Text>Max Suppressions Count: {maxSuppressionsCount}</Text>
      <Text>Max Planes Killed Ship ID: {maxPlanesKilledShipId}</Text>
      <Text>Team Capture Points: {teamCapturePoints}</Text>
      <Text>Control Dropped Points: {controlDroppedPoints}</Text>
      <Text>Max Damage Dealt To Buildings Ship ID: {maxDamageDealtToBuildingsShipId}</Text>
      <Text>Max Damage Dealt Ship ID: {maxDamageDealtShipId}</Text>
      <Text>Max Scouting Damage Ship ID: {maxScoutingDamageShipId}</Text>
      <Text>Team Dropped Capture Point: {teamDroppedCapturePoint}</Text>
      <Text>Battles Since 512: {battlesSince512}</Text>
    </View>
  );
};

export default ModeStatistics;

interface Torpedoe {
  // Define properties and methods for Torpedoe
}

interface AttackAircraft {
  // Define properties and methods for AttackAircraft
}

interface RamAttack {
  // Define properties and methods for RamAttack
}

interface MainBattery {
  shots: number;
  // Define other properties and methods for MainBattery
}

interface SecondaryBattery {
  // Define properties and methods for SecondaryBattery
}

interface ModeStatistics {
  maxFragsBattle?: number;
  draws?: number;
  maxXp?: number;
  wins?: number;
  planesKilled?: number;
  losses?: number;
  battles?: number;
  maxDamageDealt?: number;
  damageDealt?: number;
  maxPlanesKilled?: number;
  torpedoes?: Torpedoe;
  aircraft?: AttackAircraft;
  ramming?: RamAttack;
  mainBattery?: MainBattery;
  secondaries?: SecondaryBattery;
  survivedWins?: number;
  frags?: number;
  xp?: number;
  survivedBattles?: number;
  damageToBuildings?: number;
  maxShipsSpottedShipId?: number;
  maxDamageScouting?: number;
  artAgro?: number;
  maxXpShipId?: number;
  shipsSpotted?: number;
  maxFragsShipId?: number;
  droppedCapturePoints?: number;
  maxDamageDealtToBuildings?: number;
  torpedoAgro?: number;
  controlCapturedPoints?: number;
  battlesSince510?: number;
  maxTotalAgroShipId?: number;
  maxShipsSpotted?: number;
  maxSuppressionsShipId?: number;
  damageScouting?: number;
  maxTotalAgro?: number;
  capturePoints?: number;
  suppressionsCount?: number;
  maxSuppressionsCount?: number;
  maxPlanesKilledShipId?: number;
  teamCapturePoints?: number;
  controlDroppedPoints?: number;
  maxDamageDealtToBuildingsShipId?: number;
  maxDamageDealtShipId?: number;
  maxScoutingDamageShipId?: number;
  teamDroppedCapturePoint?: number;
  battlesSince512?: number;
}

const ModeStatistics = {
  fromJson(json: any): ModeStatistics {
    return {
      maxFragsBattle: json.max_frags_battle,
      draws: json.draws,
      maxXp: json.max_xp,
      wins: json.wins,
      planesKilled: json.planes_killed,
      losses: json.losses,
      battles: json.battles,
      maxDamageDealt: json.max_damage_dealt,
      damageDealt: json.damage_dealt,
      maxPlanesKilled: json.max_planes_killed,
      torpedoes: json.torpedoes ? Torpedoe.fromJson(json.torpedoes) : undefined,
      aircraft: json.aircraft ? AttackAircraft.fromJson(json.aircraft) : undefined,
      ramming: json.ramming ? RamAttack.fromJson(json.ramming) : undefined,
      mainBattery: json.main_battery ? MainBattery.fromJson(json.main_battery) : undefined,
      secondaries: json.second_battery ? SecondaryBattery.fromJson(json.second_battery) : undefined,
      survivedWins: json.survived_wins,
      frags: json.frags,
      xp: json.xp,
      survivedBattles: json.survived_battles,
      damageToBuildings: json.damage_to_buildings,
      maxShipsSpottedShipId: json.max_ships_spotted_ship_id,
      maxDamageScouting: json.max_damage_scouting,
      artAgro: json.art_agro,
      maxXpShipId: json.max_xp_ship_id,
      shipsSpotted: json.ships_spotted,
      maxFragsShipId: json.max_frags_ship_id,
      droppedCapturePoints: json.dropped_capture_points,
      maxDamageDealtToBuildings: json.max_damage_dealt_to_buildings,
      torpedoAgro: json.torpedo_agro,
      controlCapturedPoints: json.control_captured_points,
      battlesSince510: json.battles_since_510,
      maxTotalAgroShipId: json.max_total_agro_ship_id,
      maxShipsSpotted: json.max_ships_spotted,
      maxSuppressionsShipId: json.max_suppressions_ship_id,
      damageScouting: json.damage_scouting,
      maxTotalAgro: json.max_total_agro,
      capturePoints: json.capture_points,
      suppressionsCount: json.suppressions_count,
      maxSuppressionsCount: json.max_suppressions_count,
      maxPlanesKilledShipId: json.max_planes_killed_ship_id,
      teamCapturePoints: json.team_capture_points,
      controlDroppedPoints: json.control_dropped_points,
      maxDamageDealtToBuildingsShipId: json.max_damage_dealt_to_buildings_ship_id,
      maxDamageDealtShipId: json.max_damage_dealt_ship_id,
      maxScoutingDamageShipId: json.max_scouting_damage_ship_id,
      teamDroppedCapturePoint: json.team_dropped_capture_points,
      battlesSince512: json.battles_since_512,
    };
  },

  hasBattle(statistics: ModeStatistics): boolean {
    return statistics.battles !== undefined && statistics.battles !== 0;
  },

  hasHit(statistics: ModeStatistics): boolean {
    return (statistics.mainBattery?.shots ?? 0) > 0;
  },

  sunkBattle(statistics: ModeStatistics): number {
    if (statistics.survivedBattles === undefined) return 0;
    if (statistics.battles === undefined) return 0;
    return statistics.battles - statistics.survivedBattles;
  }
};

    if (frags === null) return NaN;
    return frags / sunkBattle;
}


interface StatsProps {
  artAgro?: number;
  torpedoAgro?: number;
  wins: number;
  battles: number;
  survivedWins: number;
  survivedBattles: number;
  xp: number;
  damageDealt: number;
  frags: number;
  planesKilled: number;
  damageScouting: number;
  shipsSpotted: number;
  totalPotentialDamage: number;
  maxDamageDealt: number;
  maxXp: number;
  maxFragsBattle: number;
  maxPlanesKilled: number;
  maxDamageDealtToBuildings: number;
  maxDamageScouting: number;
  maxShipsSpotted: number;
  maxSuppressionsCount: number;
  maxTotalAgro: number;
  killDeath: number;
  mainBattery?: {
    hitRatio?: number;
  };
}

const Stats: React.FC<StatsProps> = ({
  artAgro,
  torpedoAgro,
  wins,
  battles,
  survivedWins,
  survivedBattles,
  xp,
  damageDealt,
  frags,
  planesKilled,
  damageScouting,
  shipsSpotted,
  totalPotentialDamage,
  maxDamageDealt,
  maxXp,
  maxFragsBattle,
  maxPlanesKilled,
  maxDamageDealtToBuildings,
  maxDamageScouting,
  maxShipsSpotted,
  maxSuppressionsCount,
  maxTotalAgro,
  killDeath,
  mainBattery,
}) => {
  const getTotalPotentialDamage = () => (artAgro ?? 0) + (torpedoAgro ?? 0);

  const rate = (numerator: number, denominator: number) => (denominator > 0 ? (numerator / denominator) * 100 : 0);
  const average = (total: number, count: number) => (count > 0 ? total / count : 0);

  const winrate = rate(wins, battles);
  const survivedWinrate = rate(survivedWins, battles);
  const survivedRate = rate(survivedBattles, battles);

  const avgExp = average(xp, battles);
  const avgDamage = average(damageDealt, battles);
  const avgFrag = average(frags, battles);
  const avgPlaneDestroyed = average(planesKilled, battles);

  const avgSpottingDamage = average(damageScouting, battles);
  const avgSpottedShips = average(shipsSpotted, battles);
  const avgPotentialDamage = average(getTotalPotentialDamage(), battles);

  const battleString = battles.toString();
  const winrateString = `${winrate.toFixed(1)}%`;
  const avgDamageString = avgDamage.toFixed(0);
  const avgExpString = avgExp.toFixed(0);
  const killDeathString = killDeath.toFixed(2);
  const mainHitRatio = mainBattery?.hitRatio ?? NaN;
  const mainHitRatioString = `${mainHitRatio.toFixed(2)}%`;

  const mostDamage = maxDamageDealt.toString();
  const mostExp = maxXp.toString();
  const mostFrag = maxFragsBattle.toString();
  const mostPlane = maxPlanesKilled.toString();

  const mostDamageToBuilding = maxDamageDealtToBuildings.toString();
  const mostSpottingDamage = maxDamageScouting.toString();
  const mostSpotted = maxShipsSpotted.toString();
  const mostSupression = maxSuppressionsCount.toString();
  const mostPotential = maxTotalAgro.toString();

  return (
    <div>
      <p>Battle Count: {battleString}</p>
      <p>Win Rate: {winrateString}</p>
      <p>Average Damage: {avgDamageString}</p>
      <p>Average Experience: {avgExpString}</p>
      <p>K/D Ratio: {killDeathString}</p>
      <p>Main Hit Ratio: {mainHitRatioString}</p>
      <p>Most Damage: {mostDamage}</p>
      <p>Most Experience: {mostExp}</p>
      <p>Most Frags: {mostFrag}</p>
      <p>Most Planes Destroyed: {mostPlane}</p>
      <p>Most Damage to Buildings: {mostDamageToBuilding}</p>
      <p>Most Spotting Damage: {mostSpottingDamage}</p>
      <p>Most Spotted Ships: {mostSpotted}</p>
      <p>Most Suppressions: {mostSupression}</p>
      <p>Most Potential Damage: {mostPotential}</p>
    </div>
  );
};

export default Stats;