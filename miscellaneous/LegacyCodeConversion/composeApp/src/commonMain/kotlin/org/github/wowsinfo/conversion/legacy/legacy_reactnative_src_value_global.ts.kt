package com.example.myapp


class AppGlobalData {
    companion object {
        private var dataSource: Any? = null

        fun setupWith(data: Any) {
            if (data == null) {
                throw Error("The app cannot continue because there is a problem with the data. Please try again later.")
            }

            dataSource = data
        }

        fun get(key: String): Any? {
            return dataSource?.get(key)
        }

        fun set(key: String, value: Any?) {
            if (key == null || value == null) {
                println("AppGlobalData.set() cannot set null key or value")
                return
            }
            if (value is Promise<*>) {
                println("AppGlobalData.set() value is a Promise")
                return
            }

            dataSource?.set(key, value)
        }

        fun printDataSource() {
            println(dataSource)
        }

        // Theme
        var lightTheme: Map<String, Any> = emptyMap()
        var darkTheme: Map<String, Any> = emptyMap()
        var isDarkMode: Boolean = false

        // App Settings
        var shouldSwapButton: Boolean = false
        // var useNoImageMode: Boolean = false // not used
        // var useCleanMode: Boolean = false // not used

        // You can only check one time
        var canCheckForUpdate: Boolean = true
        // Only update api once as well
        var shouldUpdateAPI: Boolean = true

        // Trace how many battles
        var realtimeBattleCount: Int = 0

        // Trace last known location
        var lastLocation: String = ""

        var githubVersion: Boolean = false
    }
}

val appGlobalDataFlow = MutableStateFlow<AppGlobalData>(AppGlobalData)

fun getAppGlobalData(): StateFlow<AppGlobalData> {
    return appGlobalDataFlow
}