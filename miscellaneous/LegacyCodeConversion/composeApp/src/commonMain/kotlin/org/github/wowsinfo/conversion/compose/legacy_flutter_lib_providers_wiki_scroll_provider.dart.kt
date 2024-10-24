@Composable
fun ScrollProvider(
    scroll: ScrollState,
    animation: AnimationController,
    height: Dp,
) {
    val display by remember { mutableStateOf(true) }
    val offset by remember { mutableStateOf((-height.value).toFloat()) }

    LaunchedEffect(scroll) {
        val direction = when {
            scroll.isScrollInProgress -> if (scroll.position.userScrollDirection == ScrollDirection.reverse) {
                if (display) {
                    animation.forward()
                    display = false
                    true
                } else {
                    false
                }
            } else {
                if (!display) {
                    animation.reverse()
                    display = true
                    true
                } else {
                    false
                }
            }
            else -> null
        }

        if (direction != null) {
            log("Hiding widget", "ScrollProvider")
            scrollListener?.invoke(display)
        }
    }
}