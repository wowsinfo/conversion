import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AppProvider(
    private val userRepository: UserRepository,
    private val dataStore: DataStore<AppTheme>
) : AndroidViewModel(dataStore.context) {

    private var _darkMode = false
    val darkMode: Boolean get() = _darkMode

    private var _themeColour = AppThemeColour()
    val themeColour: MaterialColor get() = _themeColour.colour

    private var _themeData = MaterialTheme()
    val themeData: MaterialTheme get() = _themeData

    fun updateDarkMode(darkMode: Boolean) {
        _darkMode = darkMode
        userRepository.darkMode = darkMode

        _themeData = generateThemeData()
        viewModelScope.launch {
            dataStore.updateData { currentTheme ->
                currentTheme.copy(
                    darkMode = darkMode,
                    themeColour = AppThemeColour.fromColour(themeColour)
                )
            }
        }
    }

    private fun generateThemeData(): MaterialTheme {
        return if (_darkMode) {
            MaterialTheme(colorScheme = darkColorScheme())
        } else {
            MaterialTheme(colorScheme = lightColorScheme())
        }
    }

    init {
        viewModelScope.launch {
            val appTheme = dataStore.data.first()
            _darkMode = appTheme.darkMode
            _themeColour = AppThemeColour.fromColour(appTheme.themeColour.colour)
            _themeData = generateThemeData()
        }
    }
}