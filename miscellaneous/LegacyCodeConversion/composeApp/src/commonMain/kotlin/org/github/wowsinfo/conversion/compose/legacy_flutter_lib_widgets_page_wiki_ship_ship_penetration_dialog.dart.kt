
@Composable
fun ShipPenetrationDialog(
  ship: Ship,
) {
  val context = LocalContext.current
  val provider = ShipPenetrationProvider(context, ship)

  Dialog(
    onDismissRequest = { dismiss() },
    backgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = .8f),
    contentPadding = PaddingValues(0.dp),
    shape = RoundedCornerShape(12.dp),
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      LineChart(
        data = provider.penetrationSeries,
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Transparent),
        xAxis = XAxis().apply {
          position = XAxis.XAxisPosition.BOTTOM_INSIDE
          valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float) =
              "${(value / 1000).toInt()}${stringResource(id = R.string.kilometer)}"
          }
        },
        yAxis = provider.buildPenetrationSpec(),
      )
    }
  }
}

private fun ShipPenetrationProvider.buildPenetrationSpec(): YAxis {
  return YAxis().apply {
    position = YAxis.YAxisPosition.LEFT_INSIDE
    valueFormatter = object : ValueFormatter() {
      override fun getFormattedValue(value: Float) =
        "${value.toInt()}${stringResource(id = R.string.millimeter)}"
    }
    setLabelCount(12, true)
  }
}

private fun ShipPenetrationProvider.buildDistanceSpec(): XAxis {
  return XAxis().apply {
    position = XAxis.XAxisPosition.BOTTOM_INSIDE
    valueFormatter = object : ValueFormatter() {
      override fun getFormattedValue(value: Float) =
        "${value.toInt()}${stringResource(id = R.string.millimeter)}"
    }
    setLabelCount(12, true)
  }
}

private fun ShipPenetrationProvider.getThemePalette(): Int {
  return ColorTemplate.MATERIAL_COLORS[0]
}