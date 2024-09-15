import kotlinx.coroutines.Deferred
import org.jetbrains.kotlinx.serialization.KSerializer

sealed interface ServiceResult<T> {
    data class Success<T>(
        val data: T,
        val requestTime: Long = System.currentTimeMillis()
    ) : ServiceResult<T>

    data class Error<T>(
        val errorMessage: String,
        val errorCode: Int? = null
    ) : ServiceResult<T>

    data class ExceptionError<T>(val exception: Throwable) : ServiceResult<T>
}

interface ApiResult<T> {
    fun <T> deferred(apiCall: () -> Deferred<ServiceResult<T>>): ApiResult<T>
}