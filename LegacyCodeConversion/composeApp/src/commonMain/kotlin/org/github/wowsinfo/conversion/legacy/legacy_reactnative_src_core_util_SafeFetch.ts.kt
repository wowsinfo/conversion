
data class SafeFetch(
    var api: String,
    val extra: Array<String>,
    private val lang: String = ""
) {

    private suspend fun makeRequest(): Result<Map<String, Any>, Error> {
        return withContext(Dispatchers.IO) {
            try {
                val formatApi = extra.fold(api) { acc, str -> acc.format(str) }
                val link = if (lang.isEmpty()) formatApi else "$formatApi$lang"
                Log.d("SafeFetch", "Fetching data from $link")
                val response = fetch(link)
                if (response.status == 200) {
                    val json = response.json()
                    if (json["status"] == "ok") {
                        return@withContext Result.success(json)
                    } else {
                        return@withContext Result.success(emptyMap())
                    }
                } else {
                    return@withContext Result.failure(Error("Invalid request"))
                }
            } catch (e: Exception) {
                Log.e("SafeFetch", "Error making request", e)
                return@withContext Result.failure(e)
            }
        }
    }

    fun fetch(api: String, vararg extra: String): Response = with(extra) {
        val formatApi = fold(api) { acc, str -> acc.format(str) }
        val langParam = if (isEmpty()) "" else "$formatApi$extra"
        Log.d("SafeFetch", "Fetching data from $langParam")
        // Replace the following line with your actual fetch implementation
        return@with(extra) Response()
    }

    fun Response.json(): Map<String, Any> {
        // Replace the following line with your actual json parsing logic
        return mapOf()
    }

    suspend fun executeSafeFetch(): Result<Map<String, Any>, Error> = makeRequest()

    data class Error(
        override val message: String,
    ) : Throwable(message)
}