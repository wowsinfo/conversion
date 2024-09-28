
@Composable
fun ShipSimilarPage(ship: Ship) {
    val provider = remember { SimilarShipProvider(ship).apply { extractShipAdditional() } }
    val chartHeight = provider.chartHeight

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Localisation.warship_compare_similar) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(listOf(
                    ChartData(provider.damageSeries, Localisation.warship_avg_damage, provider.averageDamage),
                    ChartData(provider.winrateSeries, Localisation.warship_avg_winrate, provider.averageWinrate),
                    ChartData(provider.fragsSeries, Localisation.warship_avg_frag, provider.averageFrags),
                    ChartData(provider.battleSeries, Localisation.battles, provider.totalBattles)
                )) { chartData ->
                    ChartView(
                        height = chartHeight.dp,
                        data = chartData.series,
                        title = chartData.title,
                        subtitle = chartData.subtitle
                    )
                }
            }
        }
    }
}

data class ChartData(val series: List<Float>, val title: String, val subtitle: String)

@Composable
fun ChartView(height: Dp, data: List<Float>, title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 20.sp, textAlign = TextAlign.Center)
        // Implement chart rendering logic here using a suitable chart library
        Text(text = subtitle, fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShipSimilarPage() {
    ShipSimilarPage(ship = Ship()) // Provide a mock Ship object for preview
}

fun Chart(
    height: Dp,
    list: List<Series<ChartValue, String>>,
    title: String,
    subtitle: String
) {
    Column(modifier = Modifier.height(height)) {
        Text(text = title, style = MaterialTheme.typography.h6)
        Text(text = subtitle, style = MaterialTheme.typography.body2)
        // Implement chart rendering logic using the provided list
    }
}

fun ShipSimilarPage(title: String, subtitle: String, height: Dp, list: List<BarEntry>) {
    val isDark = MaterialTheme.colors.isLight.not()

    val axis = NumericAxisSpec(
        renderSpec = SmallTickRendererSpec(
            labelStyle = TextStyleSpec(
                color = if (isDark) Color.White else Color.Black
            )
        )
    )

    val dAxis = AxisSpec<String>(
        renderSpec = SmallTickRendererSpec(
            labelStyle = TextStyleSpec(
                color = if (isDark) Color.White else Color.Black
            )
        )
    )

    val maxWidth = 500.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val chartWidth = when {
        maxWidth * 4 < screenWidth -> screenWidth / 4
        maxWidth * 2 < screenWidth -> screenWidth / 2
        else -> screenWidth
    }

    val logger = Logger("ShipSimilarPage")
    logger.fine("chartWidth: $chartWidth")
    logger.fine("screenWidth: $screenWidth")

    Box(
        modifier = Modifier
            .width(chartWidth)
            .padding(16.dp)
    ) {
        Column {
            Text(text = title, style = MaterialTheme.typography.h5)
            Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(height)
            ) {
                BarChart(
                    entries = list,
                    animate = true,
                    vertical = false,
                    barRendererDecorator = BarLabelDecorator<String>(
                        labelAnchor = BarLabelAnchor.End
                    ),
                    domainAxis = dAxis,
                    primaryMeasureAxis = axis
                )
            }
        }
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Greeting("World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Hello, $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}