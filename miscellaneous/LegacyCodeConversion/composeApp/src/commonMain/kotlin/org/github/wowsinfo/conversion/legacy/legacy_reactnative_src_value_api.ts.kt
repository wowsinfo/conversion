
@Suppress("unused")
internal object WoWsAPI {
    const val GameVersion = "https://api.worldofwarships.{0}/wows/encyclopedia/info/?application_id=${AppKey}&fields=game_version"
    // Player
    const val PlayerInfo = "https://api.worldofwarships.{0}/wows/account/info/?application_id=${AppKey}&account_id="
    const val CreatedAt = "https://api.worldoftanks.{0}/wgn/account/list/?application_id=${AppKey}&fields=created_at&search="
    const val PlayerAchievement = "https://api.worldofwarships.{0}/wows/account/achievements/?application_id=${AppKey}&language=en&fields=battle&account_id="
    const val PlayerSearch = "https://api.worldofwarships.{0}/wows/account/list/?application_id=${AppKey}&search="
    const val PlayerOnline = "https://api.worldoftanks.{0}/wgn/servers/info/?application_id=${AppKey}&fields=players_online&game=wows"
    // Clan
    const val PlayerClan = "https://api.worldofwarships.{0}/wows/clans/accountinfo/?application_id=${AppKey}&extra=clan&fields=clan.tag&account_id="
    const val ClanInfo = "https://api.worldofwarships.{0}/wows/clans/info/?application_id=${AppKey}&extra=members&fields=-members_ids&clan_id="
    const val ClanSearch = "https://api.worldofwarships.{0}/wows/clans/list/?application_id=${AppKey}&fields=clan_id%2Ctag&search="
    // Rank
    const val RankInfo = "https://api.worldofwarships.{0}/wows/seasons/accountinfo/?application_id=${AppKey}&account_id="
    const val RankShipInfo = "https://api.worldofwarships.{0}/wows/seasons/shipstats/?application_id=${AppKey}&account_id="
    // Ships
    const val ShipInfo = "https://api.worldofwarships.{0}/wows/ships/stats/?application_id=${AppKey}&account_id="
    const val OneShipInfo = "https://api.worldofwarships.{0}/wows/ships/stats/?application_id=${AppKey}&ship_id={1}&account_id="
    const val ShipWiki = "https://api.worldofwarships.{0}/wows/encyclopedia/ships/?application_id=${AppKey}&ship_id={1}"
    const val ShipModule = "https://api.worldofwarships.{0}/wows/encyclopedia/shipprofile/?application_id=${AppKey}&ship_id={1}&artillery_id={2}&dive_bomber_id={3}&engine_id={4}&fighter_id={5}&fire_control_id={6}&flight_control_id={7}&hull_id={8}&torpedo_bomber_id={9}&torpedoes_id={10}"
}

@Suppress("unused")
internal object WikiAPI {
    // Personal rating and ship model
    const val PersonalRating = "https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/personal_rating.json"
    const val PersonalRatingMirror = "https://gitee.com/HenryQuan/WoWs-Info-Future/raw/API/json/personal_rating.json"
    // TODO: update this link when I remove this branch
    const val ShipModel = "https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Ultra/API/json/model.json"
    // Wiki
    const val Achievement = "https://api.worldofwarships.{0}/wows/encyclopedia/achievements/?application_id=${AppKey}&fields=battle.hidden%2Cbattle.achievement_id%2Cbattle.name%2Cbattle.image%2Cbattle.image_inactive%2Cbattle.description"
    const val Warship = "https://api.worldofwarships.{0}/wows/encyclopedia/ships/?application_id=${AppKey}&fields=name%2Cnation%2Ctype%2Ctier%2Cship_id%2Cship_id_str%2Cimages.small%2Cis_premium%2Cis_special"
    const val CommanderSkill = "https://api.worldofwarships.{0}/wows/encyclopedia/crewskills/?application_id=${AppKey}&fields=icon%2Cname%2Ctier%2Cperks.description"
    const val GameMap = "https://api.worldofwarships.{0}/wows/encyclopedia/battlearenas/?application_id=${AppKey}&fields=name%2Cicon%2Cdescription"
    // Flags, camouflages and upgrades
    const val Consumable = "https://api.worldofwarships.{0}/wows/encyclopedia/consumables/?application_id=${AppKey}&fields=type%2Cconsumable_id%2Cdescription%2Cname%2Cimage%2Cprice_credit%2Cprice_gold%2Cprofile.description"
    const val Collection = "https://api.worldofwarships.{0}/wows/encyclopedia/collections/?application_id=${AppKey}&fields=-card_cost%2C-tag"
    const val CollectionItem = "https://api.worldofwarships.{0}/wows/encyclopedia/collectioncards/?application_id=${AppKey}&fields=images.small%2Ccard_id%2Ccollection_id%2Cdescription%2Cname"
    // Extra info for wiki
    const val Language = "https://api.worldofwarships.{0}/wows/encyclopedia/info/?application_id=${AppKey}&fields=languages"
    const val Encyclopedia = "https://api.worldofwarships.{0}/wows/encyclopedia/info/?application_id=${AppKey}&fields=ship_nations%2Cship_modules%2Cship_types"
    // Additional data collected by me
    // Currently support model and version
    const val Github_Model = "https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/model.json"
    const val Github_Alias = "https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/alias.json"
    const val Github_AppVersion = "https://raw.githubusercontent.com/HenryQuan/WoWs-Info-Origin/API/json/app.json"
}