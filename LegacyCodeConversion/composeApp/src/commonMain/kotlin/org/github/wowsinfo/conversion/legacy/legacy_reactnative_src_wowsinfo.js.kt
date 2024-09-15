import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wowsinfo.core.AppGlobalData
import com.example.wowsinfo.core.DataLoader
import com.example.wowsinfo.core.Downloader
import com.example.wowsinfo.page.MainScreen
import com.example.wowsinfo.ui.theme.WoWsInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoWsInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }

        val dataLoader = DataLoader()
        dataLoader.loadAll().then { data ->
            AppGlobalData.setupWith(data)
            AppGlobalData.shouldSwapButton = AppGlobalData.get(LOCAL.swapButton)
            AppGlobalData.lastLocation = AppGlobalData.get(LOCAL.lastLocation)
            AppGlobalData.isDarkMode = AppGlobalData.get(LOCAL.darkMode)

            val firstLaunch = dataLoader.getFirstLaunch()
            if (!firstLaunch) {
                val downloader = Downloader(dataLoader.getCurrentServer())
                downloader.updateAll(false).then { obj ->
                    val success = obj.status
                    // Display message if it is not success
                    if (!success) {
                        Alert.show(
                            this,
                            lang.error_title,
                            lang.error_download_issue + "\n\n" + obj.log
                        )
                    }
                }
            }

            val tintColour = TintColour()
            val tint = if (tintColour[50] != null) tintColour else RED
            AppGlobalData.darkTheme = MaterialTheme.colorScheme.copy(
                surface = "black",
                text = GREY[50],
                primary = tint[500],
                accent = tint[300]
            )
            AppGlobalData.lightTheme = MaterialTheme.colorScheme.copy(
                surface = "white",
                text = GREY[900],
                primary = tint[500],
                accent = tint[300]
            )

            val theme = if (AppGlobalData.isDarkMode) {
                AppGlobalData.darkTheme
            } else {
                AppGlobalData.lightTheme
            }
        }
    }

    fun handleBack() {
        if (Actions.state.routes.size == 1) {
            finishAffinity()
        }
    }
}