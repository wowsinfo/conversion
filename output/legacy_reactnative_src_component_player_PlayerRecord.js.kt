import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.example.app.core.AppGlobalData
import com.example.app.data.SAVED
import com.example.app.extensions.bestWidth
import com.example.app.value.lang
import com.example.app.value.R

@Composable
fun PlayerRecord(data: Data) {
    val context = LocalContext.current
    val { goodWidth } = remember {
        mutableStateOf(bestWidth(400))
    }

    Column(modifier = Modifier.padding(16.dp)) {
        SectionTitle(title = lang.record_title)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.aircraft?.let { aircraftRecord(it) }
            data.mainBattery?.let { mainBatteryRecord(it) }
            data.secondBattery?.let { secondBatteryRecord(it) }
            data.torpedoes?.let { torpedoesRecord(it) }
            data.ramming?.let { rammingRecord(it) }
        }

        record(name = lang.record_max_damage_dealt, info = data.maxDamageDealt, id = data.maxDamageDealtShipId)
        record(name = lang.record_max_frags_battle, info = data.maxFragsBattle, id = data.maxFragsShipId)
        record(name = lang.record_max_planes_killed, info = data.maxPlanesKilled, id = data.maxPlanesKilledShipId)
        record(name = lang.record_max_xp, info = data.maxXp, id = data.maxXpShipId)
        record(name = lang.record_max_ships_spotted, info = data.maxShipsSpotted, id = data.maxShipsSpottedShipId)
        record(name = lang.record_max_total_agro, info = data.maxTotalAgro, id = data.maxTotalAgroShipId)
        record(name = lang.record_max_damage_scouting, info = data.maxDamageScouting, id = data.maxScoutingDamageShipId)

        SectionTitle(title = lang.record_best_ships_title)
        Column(modifier = Modifier.fillMaxWidth()) {
            data.mainBattery?.let { bestMainBattery(it) }
            data.secondBattery?.let { bestSecondaryBattery(it) }
            data.torpedoes?.let { bestTorpedoes(it) }
            data.aircraft?.let { bestAircraft(it) }
            data.ramming?.let { bestRamming(it) }
        }

        // Add any additional records here
    }
}

@Composable
fun record(name: String, info: Int?, id: Long?) {
    val shipData = remember(id) { AppGlobalData.get(SAVED.warship)[id] }
    if (shipData == null || info == null) return

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(text = name)
            Text(text = info.toString(), style = SpanStyle(color = Color.Black))
        }
    }
}

@Composable
fun aircraftRecord(data: AircraftData) {
    val shipData = remember(data.maxPlanesKilledShipId) { AppGlobalData.get(SAVED.warship)[data.maxPlanesKilledShipId] }
    if (shipData == null || data.frags == null || data.hits == null || data.shots == null || data.maxFragsBattle == null) return

    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = lang.warship_aircraft)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp)
            )
            Column {
                Text(text = lang.record_best_ship)
                WarshipCell(item = shipData, scale = 2) {
                    // Handle onPress event
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = lang.weapon_total_frags)
            Text(text = data.frags.toString())
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = lang.weapon_max_frags)
            Text(text = data.maxFragsBattle.toString())
        }
        if (data.hits != null && data.shots != null) {
            val hitRatio = (data.hits / data.shots) * 100
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = lang.weapon_hit_ratio)
                Text(text = "${String.format("%.2f", hitRatio)}%")
            }
        }
    }
}

// Similar functions for other weapon records...