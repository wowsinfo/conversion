@Composable
fun PopupBox(
    modifier: Modifier = Modifier,
    child: @Composable () -> Unit,
    durationMillis: Int = 300,
    curve: AnimationCurve = AnimationCurve.EaseInOut,
    keepAlive: Boolean = false
) {
    val animationController = rememberInfiniteTransition()
    val animation = animateFloatAsState(1f)
    LaunchedEffect(Unit) {
        animationController.startAnimationBy(durationMillis.toLong())
    }
    Scale(
        scale = animation.value,
        modifier = modifier.animate(together = true),
        child = if (keepAlive) KeepAlive(child = child()) else child()
    )
}