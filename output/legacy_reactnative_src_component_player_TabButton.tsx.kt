@Composable
fun TabButton(
    icon: ImageVector,
    disabled: Boolean = false,
    onPress: () -> Unit = {},
    otherProps: Map<String, Any> = mapOf(),
) {
    val colors = LocalColors.current

    Touchable(
        fill = true,
        style = styles.container,
        onClick = { if (!disabled) onPress() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colors.primary
        )
    }

    private fun Map<String, Any>.toAttrMap(): AttrMap {
        return mapOfEntries(this.entries.map { (key, value) ->
            key to when (value) {
                is Int -> Attribute(key, value)
                else -> Attribute(key, value.toString())
            }
        })
    }
}

private val styles = Stylesheet.of {
    val container = style {
        padding(4.dp)
        alignContent(Alignment.Center)
        justifyContent(Alignment.Center)
    }

    Container by container
}