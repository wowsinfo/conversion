
@Composable
fun AppName() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { /* Handle click event here */ },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(64.dp).clickable { /* Handle click event here */ }
        )
    }
}