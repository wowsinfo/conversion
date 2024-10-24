
abstract class StoreInterface {
    abstract suspend fun load(): Boolean
    abstract suspend fun remove(key: String): Boolean
    abstract suspend fun clear(): Boolean
    abstract suspend fun has(key: String): Boolean
    abstract suspend fun get(key: String): Serializable?
    abstract suspend fun set(key: String, value: Serializable?): Boolean
}

suspend inline fun <reified T : Serializable> StoreInterface.get(key: String): T? = withContext(Dispatchers.IO) {
    val obj = get(key)
    if (obj == null || obj !is T) return@withContext null
    obj
}