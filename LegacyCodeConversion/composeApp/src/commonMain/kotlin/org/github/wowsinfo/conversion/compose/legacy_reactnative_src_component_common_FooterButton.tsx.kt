
@Composable
fun FooterButton(icon: String, left: Boolean) {
    val iconRes = when (icon) {
        "cog" -> Icons.Filled.Cog
        "arrow-left" -> Icons.Filled.ArrowLeft
        "home" -> Icons.Filled.Home
        else -> null
    }

    IconButton(
        onClick = { /* Handle button press here */ },
        modifier = Modifier.padding(8)
    ) {
        if (iconRes != null) {
            Icon(imageVector = iconRes, contentDescription = "Icon")
        } else {
            Icon(painterResource(id = R.drawable.ic_default_icon), contentDescription = "Icon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFooterButton() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FooterButton("cog", true)
        Spacer(modifier = Modifier.height(16.dp))
        FooterButton("arrow-left", false)
        Spacer(modifier = Modifier.height(16.dp))
        FooterButton("home", true)
    }
}