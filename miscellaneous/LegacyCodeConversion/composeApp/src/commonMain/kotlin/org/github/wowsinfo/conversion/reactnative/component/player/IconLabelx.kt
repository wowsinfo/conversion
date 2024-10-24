
data class IconLabelProps(
    val info: Any,
    val icon: @Composable () -> Unit,
    val modifier: Modifier = Modifier
)

@Composable
fun IconLabel(
    info: Any,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(4.dp)
    ) {
        IconButton(onClick = { /* Handle click */ }) {
            icon()
        }
        Text(text = info.toString(), fontSize = 16.sp)
    }
}

    text = "Your Text Here",
    fontSize = 14.sp
)


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { state = !state }) {
            Text(text = if (state) "Active" else "Inactive")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current State: ${if (state) "Active" else "Inactive"}")
    }
}