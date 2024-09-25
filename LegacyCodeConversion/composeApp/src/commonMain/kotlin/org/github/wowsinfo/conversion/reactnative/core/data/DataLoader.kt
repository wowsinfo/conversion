
class DataLoader {
    companion object {
        suspend fun loadAll(): Map<String, Any> {
            val local = loadLocal()
            val saved = loadSaved()
            return local + saved
        }

        private suspend fun loadLocal(): Map<String, Any> {
            return withContext(Dispatchers.IO) {
                // Load local data logic here
                emptyMap()
            }
        }

        private suspend fun loadSaved(): Map<String, Any> {
            return withContext(Dispatchers.IO) {
                // Load saved data logic here
                emptyMap()
            }
        }
    }
}


object LocalDataLoader {
    suspend fun loadLocal(): Map<String, Any> {
        val data = mutableMapOf<String, Any>()

        loadEntry(data, LOCAL.apiLanguage, "en")
        loadEntry(data, LOCAL.userLanguage, getLanguage())
        loadEntry(data, LOCAL.swapButton, false)
        loadEntry(data, LOCAL.appVersion, APP.Version)
        loadEntry(data, LOCAL.gameVersion, APP.GameVersion)
        loadEntry(data, LOCAL.firstLaunch, true)
        loadEntry(data, LOCAL.rsIP, "")
        loadEntry(data, LOCAL.lastLocation, "")
        loadEntry(data, LOCAL.showBanner, true)
        loadEntry(data, LOCAL.showFullscreen, false)

        if (Platform.OS == "macos") {
            loadEntry(data, LOCAL.proVersion, true)
        }
        loadEntry(data, LOCAL.proVersion, false)

        val list = mapOf(
            "clan" to mapOf("2000020641" to Clan("ICBC", "2000020641", 3)),
            "player" to mapOf("2011774448" to Player("HenryQuan", "2011774448", 3))
        )

        loadEntry(data, LOCAL.friendList, list).let {
            val info = data[LOCAL.friendList] as Map<*, *>
            if (info["player"] == null) {
                val saved = mutableMapOf("clan" to mutableMapOf<String, Clan>(), "player" to mutableMapOf<String, Player>())
                info.forEach { v -> saved["player"]?.put(v["id"] as String, formatConverter(v)) }
                data[LOCAL.friendList] = saved
                SafeStorage.set(LOCAL.friendList, saved)
            }

            if (info["clan"]?.get("2000008934") != null) {
                (info["clan"] as MutableMap<String, Clan>).remove("2000008934")
                (info["clan"] as MutableMap<String, Clan>)["2000020641"] = Clan("ICBC", "2000020641", 3)
                SafeStorage.set(LOCAL.friendList, info)
            }
        }

        loadEntry(data, LOCAL.userData, emptyMap<String, Any>())
        loadEntry(data, LOCAL.userInfo, UserInfo("", "", 3)).let {
            val info = data[LOCAL.userInfo] as UserInfo
            if (info.nickname.isEmpty()) {
                val formatted = formatConverter(info)
                data[LOCAL.userInfo] = formatted
                SafeStorage.set(LOCAL.userInfo, formatted)
            }
        }
        loadEntry(data, LOCAL.userServer, 3)
        loadEntry(data, LOCAL.lastUpdate, Clock.System.now().toJavaLocalDate().toString())
        loadEntry(data, LOCAL.theme, RED)
        loadEntry(data, LOCAL.darkMode, false)
        loadEntry(data, LOCAL.date, Clock.System.now().toJavaLocalDate().toString())
        return data
    }

    private suspend fun loadEntry(data: MutableMap<String, Any>, key: String, value: Any) {
        withContext(Dispatchers.IO) {
            data[key] = value
        }
    }

    private fun getLanguage(): String {
        // Implement language retrieval logic
        return "en"
    }

    private fun formatConverter(value: Any): Any {
        // Implement format conversion logic
        return value
    }

    data class Clan(val tag: String, val clanId: String, val server: Int)
    data class Player(val nickname: String, val accountId: String, val server: Int)
    data class UserInfo(val nickname: String, val accountId: String, val server: Int)
}

    fun formatConverter(obj: MutableMap<String, Any?>): MutableMap<String, Any?> {
        obj["nickname"] = obj["name"]
        obj.remove("name")

        obj["account_id"] = obj["id"]
        obj.remove("id")

        return obj
    }
}


object DataLoader {
    suspend fun loadSaved(): Map<String, Any> {
        val data = mutableMapOf<String, Any>()
        for (key in SAVED.keys) {
            val curr = SAVED[key]
            data[curr] = withContext(Dispatchers.IO) { SafeStorage.get(curr, emptyMap()) }
        }
        return data
    }
}

    data[key] = SafeStorage.get(key, value)
}


class DataLoader {
    fun loadData(): Flow<List<String>> = flow {
        emit(fetchDataFromSource())
    }

    private fun fetchDataFromSource(): List<String> {
        // Simulate data fetching
        return listOf("Data 1", "Data 2", "Data 3")
    }
}