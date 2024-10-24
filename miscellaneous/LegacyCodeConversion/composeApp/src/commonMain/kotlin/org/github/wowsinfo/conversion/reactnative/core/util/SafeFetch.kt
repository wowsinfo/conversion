
object SafeFetch {
    suspend fun get(api: String, vararg extra: Any): JsonObject? {
        var lang = ""
        val params = extra.toMutableList()
        if (params.size > 1) {
            lang = params.removeAt(params.size - 1).toString()
        }
        val link = String.format(api, *params.toTypedArray()) + lang
        println("SafeFetch\n$link")
        
        return withContext(Dispatchers.IO) {
            val url = URL(link)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            return@withContext if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                Json.decodeFromStream<JsonObject>(connection.inputStream)
            } else {
                null
            }
        }
    }
}


object ApiClient {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun normal(api: String): SafeValue {
        println("NormalFetch\n$api")
        return withContext(Dispatchers.IO) {
            try {
                val response: String = client.get(api)
                SafeValue(Json.decodeFromString(response), SafeValue())
            } catch (e: Exception) {
                println(e)
                SafeValue()
            }
        }
    }
}


@Serializable
data class ApiResponse(val data: String)

class SafeFetch {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun fetchData(url: String): ApiResponse? {
        return withContext(Dispatchers.IO) {
            try {
                client.get<ApiResponse>(url)
            } catch (e: Exception) {
                null
            }
        }
    }
}