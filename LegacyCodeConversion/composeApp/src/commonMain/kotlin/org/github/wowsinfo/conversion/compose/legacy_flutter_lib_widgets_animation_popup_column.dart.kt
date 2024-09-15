
@Composable
fun PopupColumn(
    children: List<Content>,
    duration: Int = 500,
    curve: Curve = Curves.easeInOut,
    keepAlive: Boolean = true,
    textDirection: TextDirection? = null,
    textAlign: TextAlign? = null,
    mainAxisSize: MainAxisSize = MainAxisSize.Expand,
    mainAxisAlignment: MainAxisAlignment = MainAxisAlignment.Start,
    crossAxisAlignment: CrossAxisAlignment = CrossAxisAlignment.Center,
    verticalAlignment: VerticalAlignment = VerticalAlignment.Top,
    verticalGravity: Alignment.VerticalGravity = Alignment.VerticalGravity.Top
) {
    val count = children.size.toFloat()
    Column(
        modifier = Modifier.popupColumn(duration, curve, keepAlive),
        textDirection = textDirection,
        textAlign = textAlign,
        mainAxisSize = mainAxisSize,
        mainAxisAlignment = mainAxisAlignment,
        crossAxisAlignment = crossAxisAlignment,
        verticalAlignment = verticalAlignment,
        verticalGravity = verticalGravity
    ) {
        children.forEachIndexed { index, child ->
            DebutEffect(
                intervalStart = index / count,
                keepAlive = keepAlive,
                duration = duration,
                curve = curve
            ) { child }
        }
    }
}

fun Modifier.popupColumn(duration: Int, curve: Curve, keepAlive: Boolean): Modifier {
    return composed {
        val animationState = animateFloatAsState(
            targetValue = if (keepAlive) 1f else 0f,
            durationMillis = duration.toLong(),
            animationSpec = AnimatableSpec(initialValue = 1f),
            repeatMode = RepeatMode.Restart
        )
        Modifier.alpha(animationState.value)
    }
}

fun <T> List<T>.forEachIndexed(action: (index: Int, T) -> Unit) {
    for ((index, value) in indices.zip(this)) {
        action(index, value)
    }
}