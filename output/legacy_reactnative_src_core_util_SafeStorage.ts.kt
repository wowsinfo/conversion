import android.content.Context

class SafeStorage(private val context: Context) {
    /**
     * Get a key from async storage and return a default value if not defined
     * It will also save the default value if it is null
     *
     * @param key The key to get the value for
     * @param defaultValue The default value to return if the key does not exist in async storage
     */
    suspend fun <T> get(key: String, defaultValue: T): T {
        val data = context.dataStore.data.map { preferences ->
            preferences[key]?.let {
                it.asJson<T>()
            }
        }.first()
        if (data != null) {
            // Return parsed value
            return data
        } else {
            // Save the default value
            set(key, defaultValue)
            return defaultValue
        }
    }

    /**
     * Set any values to a key
     *
     * @param key The key to store the value under
     * @param value The value to store
     */
    suspend fun <T> set(key: String, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = Json.encodeToString(value)
        }
    }

    /**
     * Clear everything (debug only)
     */
    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}