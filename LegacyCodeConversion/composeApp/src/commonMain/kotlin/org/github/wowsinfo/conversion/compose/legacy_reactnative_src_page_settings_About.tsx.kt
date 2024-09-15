@Composable
fun About() {
    val animation = remember { mutableStateOf("pulse") }
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            animation.value = getRandomAnimation()
        }
    }

    WoWsInfo {
        Touchable(
            onClick = {
                SimpleViewHandler.openURL(lang.about_github_link)
            },
            modifier = Modifier.fillMaxHeight().align(Alignment.CenterHorizontally)
        ) {
            AnimatedBox(
                animation = animation.value,
                iterations = AnimationIterationCount.Infinite,
                easing = FastOutSlowInEasing,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    modifier = Modifier.size(imageSize),
                    tint = TintColour()[500]
                )
            }
        }
    }
}

@Composable
fun AnimatedBox(
    animation: String,
    iterations: AnimationIterationCount,
    easing: Easing,
    contentAlignment: Alignment,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = contentAlignment
    ) {
        AnimatedContentScope.animateContentAsState(targetValue = animation)
            .collect { animation ->
                val animatedVisibility =
                    animateVisibility(targetState = if (animation == "visible") true else false)

                AnimatedVisibility(
                    visible = animatedVisibility,
                    enter = fadeIn(easing = easing),
                    exit = fadeOut(easing = easing)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = contentAlignment) {
                        content()
                    }
                }

                LaunchedEffect(animation) {
                    when (animation) {
                        "pulse" -> animatePulse(iterations, easing)
                        "bounceIn" -> animateBounceIn(iterations, easing)
                        // Add other animations here
                    }
                }
            }
    }
}

// Implement the individual animation functions for pulse, bounceIn and others as needed