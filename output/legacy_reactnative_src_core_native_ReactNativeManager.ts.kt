import io.ktor.client.*
import io.ktor.client.plugins.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiClient {
    companion object {
        private val client = HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }

        suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> = withContext(Dispatchers.IO) {
            try {
                Result.Success(call.invoke())
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(e)
            }
        }
    }

    data class Result<out T>(val success: Boolean, val data: T?, val error: String?) {
        companion object {
            fun <T> Success(data: T): Result<T> = Result(true, data, null)

            fun <T> Error(error: Throwable): Result<T> = Result(false, null, error.message)
        }
    }

    suspend fun getApiData(): Result<List<String>> = safeApiCall {
        val url = "https://api.example.com/data"
        client.get(url) {
            contentType(ContentType.Application.Json)
        }.map { it as List<String> }
    }
}

// Usage
val apiClient = ApiClient()
val result: ApiClient.Result<List<String>> = apiClient.getApiData()

when (result) {
    is ApiClient.Result.Success -> {
        val data = result.data
        // Process the success data here
    }
    is ApiClient.Result.Error -> {
        val error = result.error
        // Handle the error here
    }
}