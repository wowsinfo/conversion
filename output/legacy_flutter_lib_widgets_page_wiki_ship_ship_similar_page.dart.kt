import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.michaelbull.charting.core.values.ChartValue
import com.github.michaelbull.charting.line.LineChart
import com.github.michaelbull.charting.line.config.*
import com.github.michaelbull.charting.line.models.Series
import com.github.michaelbull.charting.line.renderers.BarLabelAnchor
import com.github.michaelbull.charting.line.specs.*

@Composable
fun ShipSimilarPage(ship: Ship) {
    val provider = SimilarShipProvider(ship).apply { extractShipAdditional() }
    val chartHeight = provider.chartHeight

    Scaffold(
        appBar = AppBar(title = Text(Localisation.warship_compare_similar)),
        body = Center(
            child = SingleChildScrollView(
                child = Center(
                    Wrap(
                        alignment = WrapAlignment.SpaceAround,
                        runAlignment = WrapAlignment.Center,
                        children = listOf(
                            buildChart(
                                title = Localisation.warship_avg_damage,
                                subtitle = provider.averageDamage,
                                series = provider.damageSeries,
                                chartHeight = chartHeight
                            ),
                            buildChart(
                                title = Localisation.warship_avg_winrate,
                                subtitle = provider.averageWinrate,
                                series = provider.winrateSeries,
                                chartHeight = chartHeight
                            ),
                            buildChart(
                                title = Localisation.warship_avg_frag,
                                subtitle = provider.averageFrags,
                                series = provider.fragsSeries,
                                chartHeight = chartHeight
                            ),
                            buildChart(
                                title = Localisation.battles,
                                subtitle = provider.totalBattles.toString(),
                                series = provider.battleSeries,
                                chartHeight = chartHeight
                            )
                        )
                    )
                )
            )
        )
    )
}

@Composable
fun buildChart(
    title: String,
    subtitle: String,
    series: List<Series<ChartValue, String>>,
    chartHeight: Double
) {
    val isDark = MaterialTheme.darkColorScheme.isDark

    val axis = NumericAxisSpec(
        renderSpec = SmallTickRendererSpec(
            labelStyle = TextStyleSpec(color = if (isDark) Color.White else Color.Black)
        )
    )

    val dAxis = AxisSpec<String>(
        renderSpec = SmallTickRendererSpec(
            labelStyle = TextStyleSpec(color = if (isDark) Color.White else Color.Black)
        )
    )

    val maxWidth = 500.0
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val chartWidth = when {
        maxWidth * 4 < screenWidth -> screenWidth / 4
        maxWidth * 2 < screenWidth -> screenWidth / 2
        else -> screenWidth
    }

    Column(
        modifier = Modifier.padding(16.dp),
        children = listOf(
            Text(title, style = MaterialTheme.typography.headline5),
            Text(subtitle, style = MaterialTheme.typography.subtitle1),
            LineChart(
                series,
                animate = true,
                vertical = false,
                barRendererDecorator = BarLabelDecorator(labelAnchor = BarLabelAnchor.End),
                domainAxis = dAxis,
                primaryMeasureAxis = axis
            ).apply {
                size(width = chartWidth.dp, height = chartHeight.dp)
            }
        )
    )
}