import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result
import timber.log.Timber
import wowsinfo.localisation.Localisation
import wowsinfo.repositories.UserRepository
import wowsinfo.widgets.page.AppSettingsPage

class SettingsProvider(private val context: Context) : ChangeNotifier() {
    private val logger = Timber.tag("SettingsProvider")
    private val userRepository = UserRepository.instance

    var serverValue by userRepository::gameServer
        set(value) {
            field = value
            notifyListeners()
        }

    val servers: List<DropdownValue<Int>> by lazy {
        val localised = Localisation.of(context)
        listOf(
            DropdownValue(0, localised.server_russia),
            DropdownValue(1, localised.server_europe),
            DropdownValue(2, localised.server_north_america),
            DropdownValue(3, localised.server_asia),
        )
    }

    val colours: List<AppThemeColour> = AppThemeColour.values().toList()

    fun updateServer(index: Int) {
        logger.info("Updating server to ${servers[index].title}")
        serverValue = index
        userRepository.gameServer = serverValue
        notifyListeners()
    }
}