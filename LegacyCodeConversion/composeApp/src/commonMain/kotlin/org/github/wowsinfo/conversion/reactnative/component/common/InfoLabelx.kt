
data class InfoLabelProps(
    val title: String,
    val info: String,
    val left: Boolean = false,
    val right: Boolean = false,
    val style: Modifier = Modifier,
    val onPress: (() -> Unit)? = null
)

@Composable
fun InfoLabel(props: InfoLabelProps) {
    val alignment = when {
        props.left -> Alignment.CenterStart
        props.right -> Alignment.CenterEnd
        else -> Alignment.Center
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .then(props.style),
        contentAlignment = alignment
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = props.title,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = props.info,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
    }
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
            Text(text = if (state) "Active" else "Inactive")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current State: ${if (state) "Active" else "Inactive"}")
    }
}