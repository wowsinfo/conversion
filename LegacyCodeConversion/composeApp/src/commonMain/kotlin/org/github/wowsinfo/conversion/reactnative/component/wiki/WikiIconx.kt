
data class WikiIconProps(
    val item: Any? = null,
    val scale: Float? = null,
    val warship: Boolean? = null,
    val selected: Boolean? = null,
    val themeIcon: Boolean? = null,
    val otherProps: Any? = null
)

@Composable
fun WikiIcon(props: WikiIconProps) {
    val width = 80 * (props.scale ?: 1f)
    Box(modifier = Modifier.size(width.dp)) {
        Image(
            painter = painterResource(id = R.drawable.icon), // Replace with actual icon resource
            contentDescription = null,
            modifier = Modifier.size(width.dp)
        )
        if (props.item != null) {
            Text(
                text = "New",
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}


val imageSrc: ImageBitmap = if (item.image != null) {
    loadImage(item.image)
} else {
    loadImage(item.icon)
}

fun WarshipView(warship: Warship?, item: Item?, imageSrc: Painter, theme: ColorPalette, themeIcon: Boolean, width: Dp) {
    if (warship != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (item != null && item.new) {
                Box(modifier = Modifier
                    .size(24.dp)
                    .background(theme[500]))
            }
            Image(
                painter = imageSrc,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(width)
                    .height(width / 1.7f)
                    .let { if (themeIcon) it.tint(theme[500]) else it }
            )
        }
    }
}

fun CustomTouchable(
    selected: Boolean,
    item: Item,
    imageSrc: Painter,
    theme: Color,
    themeIcon: Boolean,
    width: Dp,
    otherProps: Modifier = Modifier
) {
    Box(
        modifier = otherProps
            .border(1.dp, if (selected) theme else Color.Transparent)
            .clickable { /* Handle click */ }
    ) {
        if (item.new) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(AppGlobalData.get(LOCAL.theme)[500])
            )
        }
        Image(
            painter = imageSrc,
            contentDescription = null,
            modifier = Modifier
                .size(width)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun Container() {
    Surface(
        modifier = Modifier
            .size(100.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Content goes here
        }
    }
}

    modifier = Modifier
        .size(8.dp)
        .background(Color.Transparent, shape = CircleShape)
        .align(Alignment.BottomCenter)
        .zIndex(1f)
) {
    // Additional content can be added here if needed
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { state = !state }) {
            Text(text = if (state) "ON" else "OFF")
        }
        if (state) {
            Text("The state is ON")
        } else {
            Text("The state is OFF")
        }
    }
}