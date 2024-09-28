
@Composable
fun IconInkWell(
    icon: ImageVector,
    onTap: (() -> Unit)? = null,
    size: Int? = null
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier
            .size(size?.dp ?: 24.dp)
            .clickable(onClick = { onTap?.invoke() })
    )
}

fun IconButton(
    icon: ImageVector,
    size: Dp? = 48.dp,
    onTap: (() -> Unit)? = null
) {
    val iconSize = size ?: 48.dp
    Box(
        modifier = Modifier
            .size(iconSize)
            .clip(CircleShape)
            .clickable(onClick = { onTap?.invoke() })
            .background(MaterialTheme.colors.surface)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface
        )
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click Me")
        }
    }
}