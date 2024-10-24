    suspend fun load(): Boolean
    suspend fun remove(key: String): Boolean
    suspend fun clear(): Boolean
    fun has(key: String): Boolean
    fun get(key: String): Any?
    suspend fun set(key: String, value: Any): Boolean
}