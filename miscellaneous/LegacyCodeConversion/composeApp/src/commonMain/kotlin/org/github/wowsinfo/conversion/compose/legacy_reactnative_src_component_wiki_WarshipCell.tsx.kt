
@Composable
fun WarshipCell(item: Item, scale: Float = 1f, onClick: (() -> Unit)? = null) {
    val containerModifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 60.dp * scale)
        .padding(vertical = 4.dp * scale)

    Card(
        modifier = if (onClick != null) containerModifier.clickable(onClick) else containerModifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            val imageWidth = (80 * scale).toInt()
            Image(
                painter = rememberImagePainter(data = item.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(imageWidth, imageWidth / 1.7f)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.name, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp * scale)
                )
            }
        }
    }
}