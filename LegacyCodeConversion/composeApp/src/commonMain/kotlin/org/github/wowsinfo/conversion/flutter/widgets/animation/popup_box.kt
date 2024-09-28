
@Composable
fun PopupBox(
    modifier: Modifier = Modifier,
    child: @Composable () -> Unit,
    duration: Int = 300,
    curve: (Float) -> Float = { it },
    keepAlive: Boolean = false
) {
    var scale by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scale = 1f
    }

    Box(
        modifier = modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
    ) {
        child()
    }

    if (keepAlive) {
        DisposableEffect(Unit) {
            onDispose {
                scale = 0f
            }
        }
    }
}


@Composable
fun PopupBox(
    modifier: Modifier = Modifier,
    duration: Duration = Duration.seconds(1),
    curve: Easing = LinearEasing,
    keepAlive: Boolean = true,
    content: @Composable () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val transition = rememberInfiniteTransition()

    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(duration.inWholeMilliseconds.toInt(), easing = curve),
            repeatMode = RepeatMode.Reverse
        )
    )

    if (isVisible) {
        Box(modifier = modifier.graphicsLayer(alpha = alpha)) {
            content()
        }
    }
}

fun ScaleTransitionExample(animation: Float, child: @Composable () -> Unit) {
    val scale = remember { Animatable(animation) }
    LaunchedEffect(animation) {
        scale.animateTo(animation)
    }
    Box(
        modifier = Modifier.scale(scale.value)
    ) {
        child()
    }
}

    controller.dispose()
    super.onCleared()
}

    super.onSaveInstanceState(outState)
    outState.putBoolean("keepAlive", widget.keepAlive)
}

override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    widget.keepAlive = savedInstanceState.getBoolean("keepAlive")
}