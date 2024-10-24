    private val domain: String = getCurrDomain()
    private val language: String = langStr()
    private val new: Boolean = !AppGlobalData.get(LOCAL.firstLaunch)

    init {
        println("Downloader\nYour server is '$domain'")
    }
}

    return previous != current
}


fun checkUpdateCycle(): Boolean {
    updateCurrData()
    val shouldUpdate = shouldUpdateWithCycle()
    println(shouldUpdate)
    return shouldUpdate
}

    var log = "Downloader\nChecking for new version\n"
    return try {
        val gameVersion = getVersion()
        log += "gameVersion - $gameVersion\n"
        if (gameVersion == null) {
            return makeObj(false, log)
        }
        val currVersion = AppGlobalData.get(LOCAL.gameVersion)
        println("Current: $currVersion\nAPI: $gameVersion")
        val appVersion = SafeStorage.get(LOCAL.appVersion, "1.0.4.2")
        log += "appVersion - $appVersion\n"
        println("Current app version: $appVersion\nLatest: ${APP.Version}")

        if (checkUpdateCycle() || checkVersionUpdate(currVersion, gameVersion) || force || appVersion != APP.Version) {
            log += "Updating Data\n"
            println("Downloader\nUpdating all data from API")
            AppGlobalData.set(SAVED.language, getLanguage())
            log += "${lang.setting_api_language}\n"
            AppGlobalData.set(SAVED.encyclopedia, getEncyclopedia())
            log += "${lang.wiki_section_title}\n"
            AppGlobalData.set(SAVED.warship, getWarship())
            log += "${lang.wiki_warships}\n"
            AppGlobalData.set(SAVED.achievement, getAchievement())
            log += "${lang.wiki_achievement}\n"
            AppGlobalData.set(SAVED.collection, getCollectionAndItem())
            log += "${lang.wiki_collections}\n"
            AppGlobalData.set(SAVED.commanderSkill, getCommanderSkill())
            log += "${lang.wiki_skills}\n"
            AppGlobalData.set(SAVED.consumable, getConsumable())
            log += "${lang.wiki_upgrades}\n"
            AppGlobalData.set(SAVED.map, getMap())
            log += "${lang.wiki_maps}\n"
            AppGlobalData.set(SAVED.pr, getPR())
            log += "${lang.rating_title}\n"

            var PR = AppGlobalData.get(SAVED.pr)
            if (PR == null || PR.keys.size < 10) {
                AppGlobalData.set(SAVED.pr, readLocalPR())
                log += "${lang.rating_title} - local\n"
                PR = AppGlobalData.get(SAVED.pr)
                if (PR == null || PR.keys.size < 10) {
                    log += "${lang.error_pr_corrupted}\n"
                    log += " ${lang.rating_title} - ${PR}\n"
                    return makeObj(false, log)
                }
            }

            SafeStorage.set(LOCAL.gameVersion, gameVersion)
            SafeStorage.set(LOCAL.appVersion, APP.Version)
            SafeStorage.set(LOCAL.lastUpdate, getCurrDate())
        }
        makeObj(true, log)
    } catch (err: Exception) {
        log += err.toString()
        makeObj(false, log)
    }
}

    return mapOf("status" to status, "log" to log)
}

    val json = SafeFetch.get(WoWsAPI.GameVersion, domain)
    return Guard(json, "data.game_version", APP.GameVersion)
}

    val json = SafeFetch.get(WikiAPI.Language, domain)
    val data = Guard(json, "data.languages", emptyMap())
    SafeStorage.set(SAVED.language, data)
    return data
}

    val json = SafeFetch.get(WikiAPI.Encyclopedia, domain, language)
    val data = Guard(json, "data", {})
    SafeStorage.set(SAVED.encyclopedia, data)
    return data
}

    var pageTotal = 1
    var page = 0
    val all = mutableMapOf<String, Warship>()

    val model3D = SafeFetch.normal(WikiAPI.Github_Model)

    val JapaneseShips: Map<String, JapaneseShip>? = if (getAPILanguage().contains("zh") || getAPILanguage().contains("ja")) {
        SafeFetch.normal(WikiAPI.Github_Alias)
    } else {
        null
    }

    while (page < pageTotal) {
        val json = SafeFetch.get(WikiAPI.Warship, domain, "&page_no=${page + 1}&$language")
        pageTotal = Guard(json, "meta.page_total", 1)
        val data = Guard(json, "data", emptyMap<String, Warship>())

        for ((id, curr) in data) {
            if (curr.name.contains("[")) {
                continue
            } else {
                curr.icon = curr.images.small
                curr.images = null
                curr.premium = curr.isPremium || curr.isSpecial
                curr.isPremium = null
                curr.isSpecial = null

                if (this.new) {
                    val isOld = Guard(AppGlobalData.get(SAVED.warship), id, true)
                    curr.new = !isOld
                }

                model3D?.let {
                    curr.model = it[id]?.model
                }

                JapaneseShips?.let {
                    it[id]?.let { entry ->
                        curr.name = entry.alias
                    }
                }
            }
        }

        all.putAll(data)
        page++
    }

    SafeStorage.set(SAVED.warship, all)
    return all
}

    val json = SafeFetch.get(WikiAPI.Achievement, domain, language)
    val data = Guard(json, "data.battle", emptyMap())
    if (new) {
        for ((id, curr) in data) {
            curr.new = AppGlobalData.get(SAVED.achievement)[id] == null
        }
    }
    SafeStorage.set(SAVED.achievement, data)
    return data
}

    val all = AllData()

    val collectionResponse = SafeFetch.get(WikiAPI.Collection, domain, language)
    val itemResponse = SafeFetch.get(WikiAPI.CollectionItem, domain, language)

    var collection = Guard(collectionResponse, "data", emptyMap())
    var item = Guard(itemResponse, "data", emptyMap())

    item.forEach { (id, curr) ->
        curr.image = curr.images["small"]
        curr.images = null
    }

    if (new) {
        collection.forEach { (id, curr) ->
            val isOld = Guard(AppGlobalData.get(SAVED.collection), "collection.$id", true)
            curr.new = !isOld
        }
    }

    all.collection = collection
    all.item = item

    SafeStorage.set(SAVED.collection, all)
    return all
}

    val json = SafeFetch.get(WikiAPI.CommanderSkill, domain, language)

    val skill = Guard(json, "data", emptyList<CommanderSkill>())
    val data = skill.map { it }.sortedBy { it.tier }

    SafeStorage.set(SAVED.commanderSkill, data)
    return data
}

    var pageTotal = 1
    var page = 0
    val all = mutableMapOf<String, Consumable>()

    while (page < pageTotal) {
        val json = SafeFetch.get(
            WikiAPI.Consumable,
            domain,
            "&page_no=${page + 1}&$language"
        )
        pageTotal = Guard(json, "meta.page_total", 1)
        val data = Guard(json, "data", emptyMap<String, Consumable>())

        for ((id, curr) in data) {
            if (new) {
                curr.new = AppGlobalData.get(SAVED.consumable)[id] == null
            }

            if (curr.type == "Modernization") {
                var price = curr.price_credit
                var slot = 1
                while (price > 125000) {
                    price /= 2
                    slot += 1
                }

                if (slot > 6) {
                    continue
                }
                curr.slot = slot
            }
        }

        all.putAll(data)
        page++
    }

    SafeStorage.set(SAVED.consumable, all)
    return all
}

    val json = SafeFetch.get(WikiAPI.GameMap, domain, language)

    val map = Guard(json, "data", emptyList<YourDataType>())
    val data = map.map { it }

    SafeStorage.set(SAVED.map, data)
    return data
}

    val res = SafeFetch.normal(WikiAPI.PersonalRating)
    val json = Guard(res, "data", emptyMap<String, Any>())
    // Cleanup empty key
    json.keys.filter { json[it]?.let { it is List<*> && it.isEmpty() } == true }.forEach { json.remove(it) }

    SafeStorage.set(SAVED.pr, json)
    return json
}

    println("Reading from local")
    val res = loadJsonFromFile("data/personal_rating.json")
    println(res)
    val json = guard(res, "data", emptyMap<String, Any>())
    
    // Cleanup empty key
    json.keys.filter { json[it]?.isEmpty() == true }.forEach { json.remove(it) }

    safeStorage.set(SAVED.pr, json)
    return json
}

fun loadJsonFromFile(filePath: String): String {
    // Implementation to read JSON from local file
}

fun guard(res: Any, key: String, default: Map<String, Any>): Map<String, Any> {
    // Implementation of Guard function
}

object safeStorage {
    fun set(key: String, value: Map<String, Any>) {
        // Implementation to save data
    }
}

object SAVED {
    const val pr = "personal_rating"
}


class Downloader {
    suspend fun downloadFile(url: String) {
        withContext(Dispatchers.IO) {
            // Implement file download logic here
        }
    }
}

@Composable
fun DownloadButton(downloader: Downloader) {
    Column {
        Button(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                downloader.downloadFile("https://example.com/file")
            }
        }) {
            Text("Download File")
        }
    }
}