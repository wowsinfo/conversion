import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import timber.log.Timber

class TimeTracker : ViewModel() {
    private val _timer = mutableStateOf(DateTime.now())
    val timer: DateTime get() = _timer.value

    fun log(message: String) {
        Timber.i("$message ${DateTime.now().difference(_timer.value).inMilliseconds} ms")
    }
}