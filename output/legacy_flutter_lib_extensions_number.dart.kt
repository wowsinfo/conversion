import android.os.Build

@Target(AnnotationTarget.CLASS)
annotation class Screen(
  val route: String,
  val title: String = "",
  val navIconResId: Int = -1,
  val showBackButton: Boolean = true,
  val theme: ThemeMode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) ThemeMode.SYSTEM else ThemeMode.FOLLOW_SYSTEM
)

enum class ThemeMode {
  SYSTEM, FOLLOW_SYSTEM, DARK, LIGHT
}