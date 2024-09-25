
data class WarshipCellProps(
    val item: Any,
    val scale: Float? = null,
    val onPress: (() -> Unit)? = null
)

@Composable
fun WarshipCell(props: WarshipCellProps) {
    val width = (80 * (props.scale ?: 1f)).dp

    Column(
        modifier = Modifier
            .size(width)
            .clickable { props.onPress?.invoke() }
    ) {
        Image(
            painter = /* Your image painter here */,
            contentDescription = null,
            modifier = Modifier.size(width)
        )
        Text(
            text = /* Your label text here */,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

fun MyComponent(item: Item?, scale: Float, onPress: () -> Unit) {
    val containerModifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable(enabled = item != null) { item?.let { onPress() } }

    Box(modifier = containerModifier) {
        if (item != null) {
            WikiIcon(warship = item, scale = scale)
        } else {
            Image(
                painter = rememberImagePainter("Unknown"),
                contentDescription = null,
                modifier = Modifier
                    .height(LocalConfiguration.current.screenWidthDp.dp / 1.7f)
                    .fillMaxWidth()
                    .colorFilter(ColorFilter.tint(TintColour()[500]))
            )
        }
        WarshipLabel(item = item)
    }
}


@Composable
fun MyComponent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello, Kotlin Multiplatform!")
        Button(onClick = { /* Handle button click */ }) {
            Text("Click Me")
        }
    }
}