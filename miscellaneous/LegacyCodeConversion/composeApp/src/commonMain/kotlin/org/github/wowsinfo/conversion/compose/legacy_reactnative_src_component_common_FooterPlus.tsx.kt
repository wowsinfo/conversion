
@Composable
fun FooterPlus(context: Context, children: @Composable () -> Unit) {
  val similarView = remember {
    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
  }
  with(MaterialTheme.colorScheme) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(background, shape = similarView),
    ) { children() }
  }
}