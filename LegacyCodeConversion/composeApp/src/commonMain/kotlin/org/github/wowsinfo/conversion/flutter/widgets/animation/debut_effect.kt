
@Composable
fun DebutEffect(
    child: @Composable () -> Unit,
    intervalStart: Long = 0,
    duration: Int = 500,
    curve: Easing = FastOutSlowInEasing,
    keepAlive: Boolean = true
) {
    var isVisible by remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition()

    LaunchedEffect(Unit) {
        delay(intervalStart)
        isVisible = true
    }

    val animatedValue by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = tween(durationMillis = duration, easing = curve)
    )

    Box(
        modifier = Modifier.graphicsLayer(
            alpha = if (isVisible) animatedValue else 0f
        )
    ) {
        child()
    }
}


@Composable
fun DebutEffect(
    child: @Composable () -> Unit,
    duration: Int,
    intervalStart: Float,
    curve: Easing = LinearEasing,
    keepAlive: Boolean = false
) {
    var isVisible by remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition()

    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    val offsetAnimation = transition.animateOffset(
        initialOffset = Offset(0f, 30f),
        targetOffset = Offset(0f, 0f),
        durationMillis = duration,
        delayMillis = (duration * intervalStart).toInt(),
        easing = curve
    )

    val fadeAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        durationMillis = duration,
        delayMillis = (duration * intervalStart).toInt(),
        easing = curve
    )

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetAnimation.value.x.toInt(), offsetAnimation.value.y.toInt()) }
            .graphicsLayer(alpha = fadeAnimation.value)
            .padding(16.dp)
    ) {
        if (isVisible) {
            child()
        }
    }
}

fun AnimatedView(controller: AnimationController, child: @Composable () -> Unit) {
    val offset by animateDpAsState(targetValue = controller.offset.value)
    val alpha by animateFloatAsState(targetValue = controller.fade.value)

    Box(modifier = Modifier.offset(x = offset.x.dp, y = offset.y.dp).graphicsLayer(alpha = alpha)) {
        child()
    }
}

    controller.dispose()
    super.onCleared()
}

    get() = widget.keepAlive