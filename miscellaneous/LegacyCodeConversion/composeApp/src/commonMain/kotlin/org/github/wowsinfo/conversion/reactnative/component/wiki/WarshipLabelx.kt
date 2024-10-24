
data class WarshipItem(val tier: Int, val name: String, val premium: Boolean)

@Composable
fun WarshipLabel(item: WarshipItem?, style: androidx.compose.ui.text.TextStyle? = null) {
    if (item != null) {
        val tierLabel = getTierLabel(item.tier)
        val textColor = if (item.premium) Color(0xFFFF9800) else MaterialTheme.colorScheme.onBackground
        BasicText(
            text = "$tierLabel ${item.name}",
            style = style?.copy(color = textColor, fontSize = 16.sp) ?: MaterialTheme.typography.bodyLarge.copy(color = textColor, fontSize = 16.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

    return Text(
        text = lang.warship_unknown,
        modifier = Modifier
            .then(style)
            .then(label),
        maxLines = 1
    )
}


@Composable
fun Label(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun MyComponent() {
    var state by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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