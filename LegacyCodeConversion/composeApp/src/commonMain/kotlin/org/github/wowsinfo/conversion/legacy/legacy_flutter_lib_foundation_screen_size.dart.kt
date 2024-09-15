import androidx.compose.ui.unit.dp

data class ScreenSize private constructor(
    val context: Context,
    val screenSize: Size,
    val isPhone: Boolean,
    val isTablet: Boolean,
    val isSmallTablet: Boolean,
    val isLargeTablet: Boolean,
    val isPhone8: Boolean,
    val isPhoneSE: Boolean
) {

    companion object {
        fun of(context: Context): ScreenSize {
            val screenSize = Size(
                context.resources.displayMetrics.widthPixels.dp.toPx(),
                context.resources.displayMetrics.heightPixels.dp.toPx()
            )
            return ScreenSize(
                context = context,
                screenSize = screenSize,
                isPhone = screenSize.width < 600.dp.toPx(),
                isTablet = screenSize.width >= 600.dp.toPx(),
                isSmallTablet = screenSize.width in 600.dp.toPx()..840.dp.toPx(),
                isLargeTablet = screenSize.width >= 840.dp.toPx(),
                isPhone8 = screenSize.width <= 375.dp.toPx(),
                isPhoneSE = screenSize.width <= 320.dp.toPx()
            )
        }

        val maxDialogWidth = 500.dp.toPx()
    }
}