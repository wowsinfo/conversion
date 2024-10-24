
@Composable
fun IconLabel(info: Any, iconResId: Int, style: Modifier = Modifier) {
    Row(
        modifier = style.padding(4.dp).align(Alignment.Center),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /* Do something when clicked */ }) {
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        setImageResource(iconResId)
                    }
                },
                modifier = Modifier.size(36.dp),
                update = { view ->
                    view.setImageResource(iconResId)
                }
            )
        }
        Text(text = info.toString(), style = androidx.compose.ui.text.style.TextStyle(fontSize = 14.sp))
    }
}