import androidx.compose.animation.core.Curve
import androidx.compose.animation.core.Curves
import androidx.compose.animation.core.FadeIn
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composition.LocalAnimationClock
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.delay

@Composable
fun DebutEffect(
    child: @Composable () -> Unit,
    intervalStart: Double = 0.0,
    duration: Int = 500,
    curve: Curve = Curves.easeInOut,
    keepAlive: Boolean = true
) {
    val animationClock = LocalAnimationClock.current
    val density = LocalDensity.current
    var offset by remember { mutableStateOf(30f) }
    val fade by animateFloatAsState(
        targetValue = if (animationClock.currentTime >= intervalStart * duration) 1f else 0f,
        animationSpec = tween(durationMillis = duration, easing = curve)
    )

    LaunchedEffect(Unit) {
        delay((intervalStart * duration).toInt())
        offset = 0f
    }

    Box(
        modifier = Modifier.alpha(fade),
        contentAlignment = Alignment.BottomCenter
    ) {
        child()
    }
}