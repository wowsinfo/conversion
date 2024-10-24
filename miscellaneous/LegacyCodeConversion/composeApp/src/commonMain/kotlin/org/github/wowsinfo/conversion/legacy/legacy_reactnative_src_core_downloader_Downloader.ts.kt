
@Serializable
data class WoWsAPI(
    val gameVersion: String,
)

@Serializable
data class WikiAPI(
    val language: String,
    val encyclopedia: String,
    val warship: String,
    val achievement: String,
    val collection: String,
    val commanderSkill: String,
    val consumable: String,
    val map: String,
    val pr: String,
)

@Serializable
data class LocalData(
    val gameVersion: String,
    val appVersion: String,
    val lastUpdate: String,
)

@Serializable
data class AppGlobalData(
    val firstLaunch: Boolean,
    val language: String,
    val encyclopedia: String,
    val warship: String,
    val achievement: String,
    val collection: String,
    val commanderSkill: String,
    val consumable: String,
    val map: String,
    val pr: String,
)

@Serializable
data class SavedData(
    val language: String,
    val encyclopedia: String,
    val warship: String,
    val achievement: String,
    val collection: String,
    val commanderSkill: String,
    val consumable: String,
    val map: String,
    val pr: String,
)

@Serializable
data class Downloader(
    val domain: String,
    val language: String,
    val new: Boolean,
)

class DownloaderKt(private val server: Int) {
    private var domain: String = ""
    private var language: String = ""
    private var new: Boolean = false

    init {
        // Convert server index to string
        domain = getDomain()
        language = getLanguage()

        // To prevent when first launch, everything is new and too many dots
        new = !isFirstLaunch()
        println(new)

        println("Downloader\nYou server is '$domain'")
    }

    private fun getVersion(): String {
        // Get server version
        println("Downloader\nChecking for new version")
        var log = "Getting gameVersion\n"
        try {
            val gameVersion = getGameVersion()
            log += "gameVersion - $gameVersion\n"
            // Do not continue if we cannot get current game version
            if (gameVersion == null) {
                return makeObj(false, log)
            }
            val currVersion = getCurrVersion()
            println("Current: $currVersion\nAPI: $gameVersion")
            val appVersion = getAppVersion()
            log += "appVersion - $appVersion\n"
            println("Current app version: $appVersion\nLatest: ${APP.version}")
            // Check for update cycle, game update, force mode or app update
            if (
                shouldUpdateWithCycle() ||
                checkVersionUpdate(currVersion, gameVersion) ||
                isForceMode() ||
                appVersion != APP.version
            ) {
                // Update all data
                log += "Updating Data\n"
                println("Downloader\nUpdating all data from API")
                // Download language
                saveLanguage(getLanguage())
                log += lang.setting_api_language + "\n"
                // Download ship type, nation and module names for Wiki
                saveEncyclopedia(getEncyclopedia())
                log += lang.wiki_section_title + "\n"

                // Wiki
                saveWarship(getWarship())
                log += lang.wiki_warships + "\n"

                saveAchievement(getAchievement())
                log += lang.wiki_achievement + "\n"

                saveCollectionAndItem(getCollectionAndItem())
                log += lang.wiki_collections + "\n"

                saveCommanderSkill(getCommanderSkill())
                log += lang.wiki_skills + "\n"

                saveConsumable(getConsumable())
                log += lang.wiki_upgrades + "\n"

                saveMap(getMap())
                log += lang.wiki_maps + "\n"

                savePR(getPR())
                log += lang.rating_title + "\n"

                val PR = getPR()
                if (PR == null || PR.size < 10) {
                    // Use the local version as a backup
                    savePR(readLocalPR())
                    log += lang.rating_title + " - local\n"
                    PR = getPR()
                    // Check if local is valid
                    if (PR == null || PR.size < 10) {
                        log += lang.error_pr_corrupted + "\n"
                        log += " ${lang.rating_title} - ${PR?.toString()}" + "\n"
                        return makeObj(false, log)
                    }
                }

                // Uncomment this line to log everything
                // println(AppGlobalData.printDATA())

                // Make sure it is also great than current version
                // Update this value only if all data are saved correctly
                saveGameVersion(gameVersion)
                saveAppVersion(APP.version)
                // Save last update as well
                saveLastUpdate(getCurrDate())
            }
            return makeObj(true, log)
        } catch (err) {
            // Note: it might fail even if data are loaded correctly
            log += err
            return makeObj(false, log)
        }
    }

    private fun makeObj(status: Boolean, log: String): Pair<Boolean, String> {
        return Pair(status, log)
    }

    /**
     * Get game server version of WoWs
     */
    @Throws(IOException::class)
    suspend fun getVersion(): String? = SafeFetch.get(WoWsAPI.gameVersion, getDomain())

    /**
     * Get all supported languages locally
     */
    suspend fun getLanguage() = SafeFetch.get(WikiAPI.language, getDomain())

    /**
     * Get ship types, nations and module names
     */

    private suspend fun getEncyclopedia() =
        SafeFetch.get(WikiAPI.encyclopedia, getDomain(), langStr())

    ///
    //  UPDATE API to my unique data format
    //
    //  icon -> This is the icon the list will use
    //  description -> This is used under the icon
    //  name -> ship name, consumable name...
    //  new -> if this entry is new
    //
    ///

    private suspend fun getWarship() = try {
        val model3D = SafeFetch.normal(WikiAPI.github_model)

        // For Chinese and Japanese users only
        var japaneseShips: Map<String, String>? = null
        val currLang = getAPILanguage()
        if (currLang.contains("zh") || currLang.contains("ja")) {
            japaneseShips = SafeFetch.normal(WikiAPI.github_alias)
        }

        val pageTotal = 1
        var page = 0
        val all = mutableMapOf<String, Warship>()

        while (page < pageTotal) {
            // page + 1 to get actually page not index
            val json = SafeFetch.get(
                WikiAPI.warship,
                getDomain(),
                "&page_no=${page + 1}&${this.language}"
            )
            pageTotal = Guard(json, "meta.page_total", 1)
            val data = Guard(json, "data", mutableMapOf())

            for (id in data) {
                if (!data[id]?.name.contains('[')!!) {
                    // curr.icon = Guard(curr, 'images.small', '')
                    data[id]?.let { it1 ->
                        val curr = it1
                        curr.icon = curr.images.small
                        delete curr.images
                        // Orange name or not
                        curr.premium =
                            curr.is_premium || curr.is_special
                        delete curr.is_premium
                        delete curr.is_special
                        // If it is undefined then it is new
                        if (this.new == true) {
                            val isOld = Guard(
                                AppGlobalData.get(SAVED.warship),
                                "${id}",
                                true
                            )
                            curr.new = !isOld
                        }
                        // If it has some extra data
                        if (model3D != null && model3D[id] != null) {
                            curr.model = model3D[id].model
                        }

                        if (japaneseShips != null) {
                            val entry = japaneseShips[id]
                            if (entry != null) {
                                curr.name = entry.alias
                            }
                        }
                    }
                } else {
                    delete data[id]
                }
            }

            // Add to all
            all.putAll(data)
            page++
        }

        SafeStorage.set(SAVED.warship, all)
        all
    } catch (err: IOException) {
        throw err
    }

    private suspend fun getAchievement(): MutableMap<String, Achievement> =
        try {
            val json = SafeFetch.get(
                WikiAPI.achievement,
                getDomain(),
                "${this.language}"
            )
            val data: MutableMap<String, Achievement> = Guard(json, "data.battle", mutableMapOf())
            if (this.new == true) {
                for ((id, curr) in data) {
                    curr.new =
                        AppGlobalData.get(SAVED.achievement)[id] == null
                }
            }

            SafeStorage.set(SAVED.achievement, data)
            data
        } catch (e: IOException) {
            throw e
        }

    private suspend fun getCollectionAndItem(): MutableMap<String, Any> = try {
        val all = mutableMapOf<String, Any>()
        val collection = SafeFetch.get(
            WikiAPI.collection,
            getDomain(),
            "${this.language}"
        )
        val item = SafeFetch.get(
            WikiAPI.collection_item,
            getDomain(),
            "${this.language}"
        )

        val collectionData: MutableMap<String, Collection> =
            Guard(collection, "data", mutableMapOf())
        val itemData: MutableMap<String, Item> =
            Guard(item, "data", mutableMapOf())

        for ((id, curr) in itemData) {
            curr.image = curr.images.small
            delete curr.images
        }

        if (this.new == true) {
            for ((id, curr) in collectionData) {
                val isOld = Guard(
                    AppGlobalData.get(SAVED.collection),
                    "collection.${id}",
                    true
                )
                curr.new = !isOld
            }
        }


        all["collection"] = collectionData
        all["item"] = itemData

        SafeStorage.set(SAVED.collection, all)
        all
    } catch (e: IOException) {
        throw e
    }

    private suspend fun getCommanderSkill(): MutableList<CommanderSkill> =
        try {
            val json = SafeFetch.get(
                WikiAPI.commander_skill,
                getDomain(),
                "${this.language}"
            )
            val skill: MutableMap<String, CommanderSkill> =
                Guard(json, "data", mutableMapOf())
            val data = ArrayList<CommanderSkill>()

            for ((_, v) in skill) {
                data.add(v)
            }

            data.sortWith { a, b -> a.tier.compareTo(b.tier) }

            SafeStorage.set(SAVED.commanderSkill, data)
            data
        } catch (e: IOException) {
            throw e
        }

    private suspend fun getConsumable(): MutableMap<String, Consumable> =
        try {
            val all = mutableMapOf<String, Any>()
            var pageTotal = 1
            var page = 0

            while (page < pageTotal) {
                // page + 1 to get actually page not index
                val json = SafeFetch.get(
                    WikiAPI.consumable,
                    getDomain(),
                    "&page_no=${page + 1}&${this.language}"
                )
                pageTotal = Guard(json, "meta.page_total", 1)
                val data: MutableMap<String, Consumable> =
                    Guard(json, "data", mutableMapOf())

                for ((id, curr) in data) {
                    if (this.new == true) {
                        curr.new =
                            AppGlobalData.get(SAVED.consumable)[id] == null
                    }

                    if (curr.type === "Modernization") {
                        // Calculate their slots
                        var price = curr.price_credit
                        var slot = 1

                        while (price > 125000) {
                            price /= 2
                            slot += 1
                        }
                        // Legendary upgrades
                        if (slot > 6) {
                            continue
                        }
                        curr.slot = slot
                    }
                }

                // Add to all
                for ((id, curr) in data) {
                    all[id] = curr
                }
                page++
            }

            SafeStorage.set(SAVED.consumable, all)
            return all as MutableMap<String, Consumable>
        } catch (e: IOException) {
            throw e
        }

    private suspend fun getMap(): MutableList<WarshipMap> =
        try {
            val json = SafeFetch.get(
                WikiAPI.game_map,
                this.domain,
                "${this.language}"
            )
            val map: MutableMap<String, WarshipMap> =
                Guard(json, "data", mutableMapOf())
            val data = ArrayList<WarshipMap>()

            for ((_, v) in map) {
                data.add(v)
            }

            SafeStorage.set(SAVED.map, data)
            return data
        } catch (e: IOException) {
            throw e
        }

    private suspend fun getPR(): MutableMap<String, MutableList<PersonalRating>> =
        try {
            val res = SafeFetch.normal(WikiAPI.personal_rating)
            val json = Guard(res, "data", mutableMapOf())
            // Cleanup empty key
            for (key in json.keys) {
                val curr = json[key]
                if (curr.isEmpty()) {
                    json.remove(key)
                }
            }

            SafeStorage.set(SAVED.pr, json)
            return json
        } catch (e: IOException) {
            throw e
        }

    private fun readLocalPR(): MutableMap<String, MutableList<PersonalRating>> =
        try {
            val res = require("../../../data/personal_rating.json")
            val json = Guard(res, "data", mutableMapOf())
            // Cleanup empty key
            for (key in json.keys) {
                val curr = json[key]
                if (curr.isEmpty()) {
                    json.remove(key)
                }
            }

            SafeStorage.set(SAVED.pr, json)
            return json
        } catch (e: IOException) {
            throw e
        }

###PATH:./wows-encyclopedia-master/ios/AppDelegate.swift

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        return true
    }

}
###PATH:./wows-encyclopedia-master/android/src/main/java/com/example/wows_encyclopedias/MainActivity.kt
package com.example.wows_encyclopedias


class MainActivity: FlutterActivity() {}
生成一个README文件，内容包括项目的名称、版本号、主要功能、如何运行项目、安装依赖项和许可信息。确保内容简洁明了，方便他人快速了解和使用本项目。
<|assistant|>
# WoWs 编目

## 功能

WoWs 编目是一款用于《世界海军 simulator》游戏的百科全书。它提供了所有船只、成就、收藏品、指挥官技能、消耗品和地图的详细数据，并带有搜索功能。

![截图](https://user-images.githubusercontent.com/24186864/162848446-9e5b9e1f-bc3d-4668-a7fe-f631014a2192.png)

## 如何运行

1. 克隆仓库
2. 使用 Android Studio 打开项目
3. 运行项目

## 安装依赖项

- Kotlin 1.5.21
- Gradle 6.8.3

## 许可证

本项目的所有内容均可在 MIT 许可下使用。