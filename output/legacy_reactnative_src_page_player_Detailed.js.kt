import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wowsstats.core.TintColour
import com.example.wowsstats.core.roundTo
import com.example.wowsstats.data.AppGlobalData
import com.example.wowsstats.data.SAVED
import com.example.wowsstats.ui.component.*
import com.example.wowsstats.value.lang

@Composable
fun DetailedScreen(data: Any, navController: NavController?) {
    val detailed = data as? DetailedItem ?: return
    val ship = AppGlobalData.get(SAVED.WARSHIP)[detailed.ship_id] ?: return

    MaterialTheme {
        WoWsInfo(
            modifier = Modifier.fillMaxSize(),
            title = lang.wiki_section_title,
            onBack = { navController?.popBackStack() },
            onPress = { SafeAction("WarshipDetail", ship) }
        ) {
            Column(modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)) {
                WarshipCell(ship, scale = 3)
                DetailedInfo(detailed)

                val overall = AppGlobalData.get(SAVED.PR)[detailed.ship_id]
                if (overall != null && detailed.pvp != null) {
                    horizontalItem(
                        damage_dealt = detailed.pvp.damage_dealt,
                        average_damage_dealt = overall.average_damage_dealt,
                        wins = detailed.pvp.wins,
                        battles = detailed.pvp.battles,
                        win_rate = overall.win_rate,
                        frags = detailed.pvp.frags,
                        average_frags = overall.average_frags
                    )
                }
            }

            RatingButton(
                modifier = Modifier.align(Alignment.Center),
                rating = detailed.rating
            )
        }.apply {
            TintColour(getColour(detailed.rating))
        }
    }
}

@Composable
fun horizontalItem(
    damage_dealt: Int,
    average_damage_dealt: Double,
    wins: Int,
    battles: Int,
    win_rate: Double,
    frags: Int,
    average_frags: Double
) {
    val dmgDiff = normalise(damageDealt / battles - averageDamageDealt)
    val winRateDiff =
        normalise((wins.toDouble() / battles) * 100 - win_rate, 2)
    val fragDiff = normalise(frags.toDouble() / battles - averageFrags, 2)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        InfoLabel(info = dmgDiff.toString(), title = lang.ship_detail_damage)
        InfoLabel(
            info = "${winRateDiff}%",
            title = lang.ship_detail_winrate
        )
        InfoLabel(info = fragDiff.toString(), title = lang.ship_detail_frag)
    }
}

fun normalise(value: Double, digit: Int = 0): String {
    val rounded = roundTo(value, digit).toInt()
    return if (rounded <= 0) rounded.toString() else "+$rounded"
}

@Preview
@Composable
private fun PreviewDetailedScreen() {
    val navController = rememberNavController()

    DetailedScreen(
        data = DetailedItem(
            ship_id = 1,
            pvp = PVPStats(
                battles = 100, wins = 50, damage_dealt = 5000, frags = 10
            ),
            rating = 9.5f
        ), navController = navController
    )
}