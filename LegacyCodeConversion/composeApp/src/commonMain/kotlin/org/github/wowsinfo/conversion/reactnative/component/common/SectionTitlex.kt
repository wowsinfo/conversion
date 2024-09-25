
data class SectionTitleProps(
    val title: String,
    val back: Boolean = false,
    val center: Boolean = false,
    val style: Modifier = Modifier,
    val bold: Boolean = false
)

@Composable
fun SectionTitle(props: SectionTitleProps) {
    val textColor = MaterialTheme.colorScheme.primary

    Text(
        text = props.title,
        color = textColor,
        fontSize = if (props.bold) 32.sp else MaterialTheme.typography.titleMedium.fontSize,
        fontWeight = if (props.bold) FontWeight.Bold else FontWeight.Normal,
        modifier = props.style
            .padding(start = 16.dp, top = 8.dp)
            .then(if (props.center) Modifier.align(Alignment.CenterHorizontally) else Modifier)
            .then(if (props.back) Modifier.background(ThemeBackColour()) else Modifier)
    )
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
        Text(text = "Current state: ${if (state) "Active" else "Inactive"}")
    }
}