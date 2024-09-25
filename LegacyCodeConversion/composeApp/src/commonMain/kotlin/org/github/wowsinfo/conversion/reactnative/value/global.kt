    private var dataSource: Any? = null

    fun setupWith(data: Any?) {
        data ?: throw IllegalArgumentException(
            "The app cannot continue because there is a problem with the data. Please try again later."
        )
        dataSource = data
    }
}

    private val dataSource: MutableMap<String, Any> = mutableMapOf()

    fun get(key: String): Any? {
        return dataSource[key]
    }
}

    private val dataSource = mutableMapOf<String, Any?>()

    fun set(key: String?, value: Any?) {
        if (key == null || value == null) {
            println("AppGlobalData.set() cannot set null key or value")
            Thread.dumpStack()
            return
        }

        if (value is kotlinx.coroutines.Deferred<*>) {
            println("AppGlobalData.set() value is a Promise")
            Thread.dumpStack()
            return
        }

        dataSource[key] = value
    }
}

    println(AppGlobalData.dataSource)
}

    var lightTheme: Any = Any()
    var darkTheme: Any = Any()
    var isDarkMode: Boolean = false

    var shouldSwapButton: Boolean = false
    var canCheckForUpdate: Boolean = true
    var shouldUpdateAPI: Boolean = true

    var realtimeBattleCount: Int = 0

    var lastLocation: String = ""

    var githubVersion: Boolean = false
}

val appGlobalData = AppGlobalData