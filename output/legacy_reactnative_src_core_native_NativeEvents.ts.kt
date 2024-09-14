import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.HandlerCompat

class NativeEvents {
    private val eventEmitter = ReactNativeEventModule()
    private var dataLoading: Boolean by mutableStateOf(false)

    init {
        val emitter = NativeEventEmitter(eventEmitter)
        emitter.addListener("dummy", HandlerCompat.create(Looper.getMainLooper())) { type ->
            dataLoading = true
            println("dummy event received from native $type")
        }
    }

    private fun dataLoading() {
        // Implementation of the data loading logic
    }
}