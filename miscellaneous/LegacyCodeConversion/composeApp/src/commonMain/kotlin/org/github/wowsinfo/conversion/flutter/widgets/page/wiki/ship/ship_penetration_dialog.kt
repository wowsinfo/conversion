
@Composable
fun showShipPenetrationDialog(ship: Ship) {
    val provider = ShipPenetrationProvider(ship)
    AlertDialog(
        onDismissRequest = { /* Handle dismiss */ },
        title = { Text("Ship Penetration") },
        text = {
            LineChart(
                data = provider.penetrationSeries,
                behaviors = listOf(
                    SeriesLegend(position = BehaviorPosition.Bottom)
                ),
                primaryMeasureAxis = NumericAxisSpec(
                    tickProviderSpec = provider.buildPenetrationSpec(12),
                    tickFormatterSpec = BasicNumericTickFormatterSpec { mm -> "${mm?.toInt()}${Localisation.instance.millimeter}" },
                    renderSpec = GridlineRendererSpec(
                        labelStyle = TextStyle(color = provider.getThemePalette())
                    )
                ),
                secondaryMeasureAxis = NumericAxisSpec(
                    tickFormatterSpec = BasicNumericTickFormatterSpec { sec -> "${sec?.toInt()}${Localisation.instance.second}" },
                    tickProviderSpec = BasicNumericTickProviderSpec(desiredTickCount = 12),
                    renderSpec = GridlineRendererSpec(
                        labelStyle = TextStyle(color = provider.getThemePalette())
                    )
                ),
                domainAxis = NumericAxisSpec(
                    tickFormatterSpec = BasicNumericTickFormatterSpec { m -> "${(m ?: 0) / 1000}${Localisation.instance.kilometer}" },
                    tickProviderSpec = provider.buildDistanceSpec(),
                    renderSpec = GridlineRendererSpec(
                        lineStyle = LineStyleSpec(color = Color.Transparent),
                        labelStyle = TextStyle(color = provider.getThemePalette())
                    )
                )
            )
        },
        confirmButton = {
            TextButton(onClick = { /* Handle confirm */ }) {
                Text("OK")
            }
        },
        modifier = Modifier.padding(8.dp)
    )
}