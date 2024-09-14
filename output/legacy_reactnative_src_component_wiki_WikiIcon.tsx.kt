@Composable
fun WikiIcon(
    item: Any? = null,
    scale: Float = 1f,
    warship: Boolean = false,
    selected: Boolean = false,
    themeIcon: Boolean = false,
    otherProps: Map<String, Any> = mapOf()
) {
    val containerStyle = if (selected) {
        ContainerWithBorder(
            borderColor = AppGlobalData.get(LOCAL.theme)[500],
            borderRadius = 8f
        )
    } else {
        ContainerWithoutBorder
    }
    val newLabelStyle = if (item?.new == true && !warship) {
        NewLabelTheme(
            backgroundColor = AppGlobalData.get(LOCAL.theme)[500]
        )
    } else {
        NoNewLabel
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = containerStyle
    ) {
        if (item?.new == true && warship) {
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = newLabelStyle
            )
        }
        val imageSrc = ImageSource(item.image ?: item.icon)
        Image(
            src = imageSrc,
            tintColor = if (themeIcon) AppGlobalData.get(LOCAL.theme)[500] else null,
            contentScale = ContentScale.Fill,
            modifier = Modifier.size(width = 80f * scale, height = if (warship) width / 1.7f else width)
        )
    }
}