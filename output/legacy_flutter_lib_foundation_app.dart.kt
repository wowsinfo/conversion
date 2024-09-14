import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.android.gms.common.GoogleApiAvailability
import io.flutter.embedding.android.FlutterActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App {
    companion object {
        const val appVersion = "1.7.0"
        const val githubLink = "https://github.com/WoWs-Info/WoWs-Info-Seven"
        const val appstoreLink = "https://itunes.apple.com/app/id1202750166"
        const val playstoreLink =
            "https://play.google.com/store/apps/details?id=com.yihengquan.wowsinfo"
        const val latestRelease = "$githubLink/releases/latest"
        const val newIssueLink = "$githubLink/issues/new"
        const val emailToLink =
            "mailto:development.henryquan@gmail.com?subject=[WoWs Info $appVersion] "

        fun isWeb() = BuildConfig.BUILD_TYPE == "web"

        fun isIOS() = BuildConfig.BUILD_TYPE == "ios"

        fun isAndroid() = BuildConfig.BUILD_TYPE == "android"

        fun isMobile() = !isWeb() && (isIOS() || isAndroid())

        fun isDesktop() = !isWeb() && !isMobile()

        fun isApple() = !isWeb() && (isIOS() || Build.VERSION.SDK_INT >= 28)

        fun platformPageRoute(
            builder: @Composable () -> Unit,
            settings: RouteSettings? = null,
            maintainState: Boolean = true,
            fullscreenDialog: Boolean = false
        ): androidx.navigation.NavType {
            return if (isMobile()) {
                androidx.navigation.ModalBottomSheetNavigationBuilder(settings)
                    .setEnterExitTransition(
                        { context, animatable, animation ->
                            SlideTransition(
                                animation = animation,
                                child = animatable.animateTo(1f)
                            )
                        },
                        { context, animatable, animation ->
                            SlideTransition(
                                animation = animation,
                                child = animatable.animateTo(0f)
                            )
                        }
                    ).build()
            } else {
                androidx.navigation.ModalBottomSheetNavigationBuilder(settings)
                    .setEnterExitTransition(
                        { context, animatable, animation ->
                            FadeTransition(
                                animation = animation,
                                child = animatable.animateTo(1f)
                            )
                        },
                        { context, animatable, animation ->
                            FadeTransition(
                                animation = animation,
                                child = animatable.animateTo(0f)
                            )
                        }
                    ).build()
            }
        }

        fun launch(url: String) {
            val canLaunch = ContextCompat.getSystemService(GlobalScope.launch {
                url.toUri().let { Uri.parse(it.toString()) }.also { uri ->
                    try {
                        GlobalScope.launch {
                            FlutterActivity.launchUrl(context, uri)
                        }
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            })
        }

        fun checkGoogleServices(
            context: Context,
            callback: (Boolean) -> Unit
        ) {
            GoogleApiAvailability.getInstance().run {
                if (isGooglePlayServicesAvailable(context)) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
        }
    }

    private val _context by lazy { LocalContext.current }
}