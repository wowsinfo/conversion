
@Composable
fun PopupColumn(
    children: @Composable () -> Unit,
    duration: Int = 500,
    curve: (Float) -> Float = { it },
    keepAlive: Boolean = true,
    textDirection: androidx.compose.ui.text.TextDirection? = null,
    textBaseline: androidx.compose.ui.text.TextBaseline? = null,
    mainAxisAlignment: Arrangement.Vertical = Arrangement.Top,
    mainAxisSize: Size = Size.Max,
    crossAxisAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalDirection: LayoutDirection = LayoutDirection.Ltr
) {
    var offsetY by remember { mutableStateOf(0.dp) }
    val animatedOffsetY: Dp by animateDpAsState(
        targetValue = offsetY,
        animationSpec = tween(duration)
    )

    LaunchedEffect(Unit) {
        offsetY = 0.dp
    }

    Column(
        modifier = Modifier
            .offset(y = animatedOffsetY)
            .fillMaxSize(),
        verticalArrangement = mainAxisAlignment,
        horizontalAlignment = crossAxisAlignment
    ) {
        children()
    }
}


@Composable
fun PopupColumn(
    children: List<@Composable () -> Unit>,
    duration: Dp = 300.dp,
    curve: (Float) -> Float = { it },
    keepAlive: Boolean = false,
    mainAxisAlignment: Alignment.Vertical = Alignment.Top,
    mainAxisSize: Size = Size.Expand,
    crossAxisAlignment: Alignment.Horizontal = Alignment.Start,
    verticalDirection: LayoutDirection = LayoutDirection.Ltr
) {
    val count = children.size.toFloat()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = when (mainAxisAlignment) {
            Alignment.Top -> Arrangement.Top
            Alignment.CenterVertically -> Arrangement.Center
            Alignment.Bottom -> Arrangement.Bottom
            else -> Arrangement.Top
        },
        horizontalAlignment = when (crossAxisAlignment) {
            Alignment.Start -> Alignment.Start
            Alignment.CenterHorizontally -> Alignment.CenterHorizontally
            Alignment.End -> Alignment.End
            else -> Alignment.Start
        }
    ) {
        children.forEachIndexed { index, child ->
            DebutEffect(
                intervalStart = index / count,
                keepAlive = keepAlive,
                duration = duration,
                curve = curve,
                content = child
            )
        }
    }
}

@Composable
fun DebutEffect(
    intervalStart: Float,
    keepAlive: Boolean,
    duration: Dp,
    curve: (Float) -> Float,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        delay((intervalStart * duration.value).toLong())
        coroutineScope.launch {
            // Implement the effect here
        }
    }
    content()
}

    get() = widget.keepAlive