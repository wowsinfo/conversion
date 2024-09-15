
class DataLoader(private val context: Context) {
    /**
     * Load all data from storage
     */
    fun loadAll(): Map<String, Any> {
        // For debugging only
        // SafeStorage.clear()

        val local = loadLocal()
        val saved = loadSaved()
        return local + saved
    }

    private fun loadLocal(): Map<String, Any> {
        val data: MutableMap<String, Any> = mutableMapOf()

        // Manually setting up SAVED section (they are all different)
        loadEntry(data, "apiLanguage", "en")
        loadEntry(data, "userLanguage", lang.getLanguage())
        loadEntry(data, "swapButton", false)
        loadEntry(data, "appVersion", APP.Version)
        loadEntry(data, "gameVersion", APP.GameVersion)
        loadEntry(data, "firstLaunch", true)
        loadEntry(data, "rsIP", "")
        loadEntry(data, "lastLocation", "")
        loadEntry(data, "showBanner", true)
        loadEntry(data, "showFullscreen", false)
        loadEntry(data, "proVersion", if (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.M) true else false)

        // Add support to save clans as well
        val list = mapOf(
            "clan" to mapOf(2000020641 to mapOf("tag" to "ICBC", "clan_id" to "2000020641", "server" to 3),
                2000008934 to mapOf("tag" to "FFD", "clan_id" to "2000008934", "server" to 3)),
            "player" to mapOf(2011774448 to mapOf(
                "nickname" to "HenryQuan",
                "account_id" to "2011774448",
                "server" to 3))
        )
        loadEntry(data, "friendList", list).also { info ->
            if (info["player"] == null) {
                // Previously, it was all players
                val saved = mapOf(
                    "clan" to emptyMap(),
                    "player" to mapOf()
                )
                data["friendList"] = saved
                SafeStorage.set(context, "friendList", saved)
            }

            if ((info["clan"] as Map<*, *>).containsKey(2000008934)) {
                (info["clan"] as MutableMap<Int, Map<String, Any>>).remove(2000008934)
                info["clan"] = info["clan"] as Map<Int, Map<String, Any>>
                    .plus(2000020641 to mapOf("tag" to "ICBC", "clan_id" to "2000020641", "server" to 3))
                SafeStorage.set(context, "friendList", info)
            }
        }

        loadEntry(data, "userData", emptyMap())
        loadEntry(data, "userInfo", mapOf(
            "nickname" to "",
            "account_id" to "",
            "server" to 3
        )).also { info ->
            if (info["name"] != null) {
                val formatted = formatConverter(info as Map<String, Any>)
                data["userInfo"] = formatted
                SafeStorage.set(context, "userInfo", formatted)
            }
        }
        loadEntry(data, "userServer", 3)
        loadEntry(data, "lastUpdate", android.text.format.DateUtils.getRelativeTimeSpanString(
            System.currentTimeMillis(),
            System.currentTimeMillis(), 0L))
        loadEntry(data, "theme", RED)
        loadEntry(data, "darkMode", false)
        loadEntry(data, "date", android.text.format.DateUtils.getRelativeTimeSpanString(
            System.currentTimeMillis(),
            System.currentTimeMillis(), 0L))
        return data
    }

    private fun formatConverter(obj: Map<String, Any>): Map<String, Any> {
        if (obj["name"] != null) {
            obj["nickname"] = obj["name"]
            obj.remove("name")
        }
        if (obj["id"] != null) {
            obj["account_id"] = obj["id"]
            obj.remove("id")
        }

        return obj
    }

    private fun loadSaved(): Map<String, Any> {
        val data: MutableMap<String, Any> = mutableMapOf()
        SAVED.forEach { (key, value) ->
            // Get it from storage
            data[value] = SafeStorage.get(context, value, emptyMap())
        }
        return data
    }

    private fun loadEntry(data: MutableMap<String, Any>, key: String, value: Any) {
        data[key] = SafeStorage.get(context, key, value)
    }
}