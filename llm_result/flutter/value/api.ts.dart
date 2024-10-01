
class AppKey {
  static const String key = 'YOUR_APP_KEY_HERE'; // Replace with your actual app key
}

class WoWsAPI {
  static String gameVersion(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/info/?application_id=${AppKey.key}&fields=game_version';
  
  // Player
  static String playerInfo(String region, String accountId) =>
      'https://api.worldofwarships.$region/wows/account/info/?application_id=${AppKey.key}&account_id=$accountId';
  
  static String createdAt(String region, String search) =>
      'https://api.worldoftanks.$region/wgn/account/list/?application_id=${AppKey.key}&fields=created_at&search=$search';
  
  static String playerAchievement(String region, String accountId) =>
      'https://api.worldofwarships.$region/wows/account/achievements/?application_id=${AppKey.key}&language=en&fields=battle&account_id=$accountId';
  
  static String playerSearch(String region, String search) =>
      'https://api.worldofwarships.$region/wows/account/list/?application_id=${AppKey.key}&search=$search';
  
  static String playerOnline(String region) =>
      'https://api.worldoftanks.$region/wgn/servers/info/?application_id=${AppKey.key}&fields=players_online&game=wows';
  
  // Clan
  static String playerClan(String region, String accountId) =>
      'https://api.worldofwarships.$region/wows/clans/accountinfo/?application_id=${AppKey.key}&extra=clan&fields=clan.tag&account_id=$accountId';
  
  static String clanInfo(String region, String clanId) =>
      'https://api.worldofwarships.$region/wows/clans/info/?application_id=${AppKey.key}&extra=members&fields=-members_ids&clan_id=$clanId';
  
  static String clanSearch(String region, String search) =>
      'https://api.worldofwarships.$region/wows/clans/list/?application_id=${AppKey.key}&fields=clan_id%2Ctag&search=$search';
  
  // Rank
  static String rankInfo(String region, String accountId) =>
      'https://api.worldofwarships.$region/wows/seasons/accountinfo/?application_id=${AppKey.key}&account_id=$accountId';
  
  static String rankShipInfo(String region, String accountId) =>
      'https://api.worldofwarships.$region/wows/seasons/shipstats/?application_id=${AppKey.key}&account_id=$accountId';
  
  // Ships
  static String shipInfo(String region, String accountId) =>
      'https://api.worldofwarships.$region/wows/ships/stats/?application_id=${AppKey.key}&account_id=$accountId';
  
  static String oneShipInfo(String region, String shipId, String accountId) =>
      'https://api.worldofwarships.$region/wows/ships/stats/?application_id=${AppKey.key}&ship_id=$shipId&account_id=$accountId';
  
  static String shipWiki(String region, String shipId) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/ships/?application_id=${AppKey.key}&ship_id=$shipId';
  
  static String shipModule(String region, String shipId, String artilleryId, String diveBomberId, String engineId, String fighterId, String fireControlId, String flightControlId, String hullId, String torpedoBomberId, String torpedoesId) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/shipprofile/?application_id=${AppKey.key}&ship_id=$shipId&artillery_id=$artilleryId&dive_bomber_id=$diveBomberId&engine_id=$engineId&fighter_id=$fighterId&fire_control_id=$fireControlId&flight_control_id=$flightControlId&hull_id=$hullId&torpedo_bomber_id=$torpedoBomberId&torpedoes_id=$torpedoesId';
}

class WikiAPI {
  // Personal rating and ship model
  static const String personalRating =
      'https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/personal_rating.json';
  
  static const String personalRatingMirror =
      'https://gitee.com/HenryQuan/WoWs-Info-Future/raw/API/json/personal_rating.json';
  
  static const String shipModel =
      'https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Ultra/API/json/model.json';
  
  // Wiki
  static String achievement(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/achievements/?application_id=${AppKey.key}&fields=battle.hidden%2Cbattle.achievement_id%2Cbattle.name%2Cbattle.image%2Cbattle.image_inactive%2Cbattle.description';
  
  static String warship(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/ships/?application_id=${AppKey.key}&fields=name%2Cnation%2Ctype%2Ctier%2Cship_id%2Cship_id_str%2Cimages.small%2Cis_premium%2Cis_special';
  
  static String commanderSkill(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/crewskills/?application_id=${AppKey.key}&fields=icon%2Cname%2Ctier%2Cperks.description';
  
  static String gameMap(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/battlearenas/?application_id=${AppKey.key}&fields=name%2Cicon%2Cdescription';
  
  // Flags, camouflages and upgrades
  static String consumable(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/consumables/?application_id=${AppKey.key}&fields=type%2Cconsumable_id%2Cdescription%2Cname%2Cimage%2Cprice_credit%2Cprice_gold%2Cprofile.description';
  
  static String collection(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/collections/?application_id=${AppKey.key}&fields=-card_cost%2C-tag';
  
  static String collectionItem(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/collectioncards/?application_id=${AppKey.key}&fields=images.small%2Ccard_id%2Ccollection_id%2Cdescription%2Cname';
  
  // Extra info for wiki
  static String language(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/info/?application_id=${AppKey.key}&fields=languages';
  
  static String encyclopedia(String region) =>
      'https://api.worldofwarships.$region/wows/encyclopedia/info/?application_id=${AppKey.key}&fields=ship_nations%2Cship_modules%2Cship_types';
  
  // Additional data collected by me
  // Currently support model and version
  static const String githubModel =
      'https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/model.json';
  
  static const String githubAlias =
      'https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/alias.json';
  
  static const String githubAppVersion =
      'https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/app.json';
}