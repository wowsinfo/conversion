
@Composable
fun IconPlaceholder(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Image(
        painter = painterResource(id = R.drawable.placeholder_icon),
        contentDescription = null, // Provide a description for accessibility
        modifier = modifier.size(size),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(Color.Blue)
    )
}

@Preview(showBackground = true)
@Composable
fun IconPlaceholderPreview() {
    IconPlaceholder()
}