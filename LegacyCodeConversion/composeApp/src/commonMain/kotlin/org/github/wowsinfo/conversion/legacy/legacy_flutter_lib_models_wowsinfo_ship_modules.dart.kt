import 'dart:io'

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NetworkManager {
    suspend fun <T> performNetworkRequest(request: Request<T>, onSuccess: (response: T) -> Unit, onError: (error: Exception) -> Unit)
}

class HttpNetworkManager : NetworkManager {

    override suspend fun <T> performNetworkRequest(request: Request<T>, onSuccess: (response: T) -> Unit, onError: (error: Exception) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                val response = HttpClient().send(request).await()
                if (response.statusCode == HttpStatus.OK) {
                    val responseBody = String(response.readBytes().await())
                    val deserializedResponse = request.responseType.fromJson(responseBody)
                    onSuccess(deserializedResponse)
                } else {
                    onError(Exception("Request failed with status code ${response.statusCode}"))
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

}

data class Request<T>(
    val url: String,
    val method: RequestMethod = RequestMethod.GET,
    val headers: Map<String, String> = emptyMap(),
    val body: String? = null,
    val responseType: ResponseType<T>
)

enum class RequestMethod {
    GET, POST, PUT, DELETE
}

interface ResponseType<T> {
    fun fromJson(json: String): T
}