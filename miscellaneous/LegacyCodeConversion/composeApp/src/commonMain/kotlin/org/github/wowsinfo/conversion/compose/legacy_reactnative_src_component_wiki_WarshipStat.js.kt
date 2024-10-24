
@Composable
fun WarshipStat(profile: Profile) {
    Column(modifier = Modifier.fillMaxWidth()) {
        renderProgress(armour.total, lang.warship_survivability)
        renderProgress(artillery, lang.warship_artillery)
        renderProgress(torpedoes, lang.warship_torpedoes)
        renderProgress(anti_aircraft, lang.warship_antiaircraft)
        renderProgress(mobility.total, lang.warship_maneuverability)
        renderProgress(aircraft, lang.warship_aircraft)
        renderProgress(concealment.total, lang.warship_concealment)
    }
}

@Composable
fun renderProgress(value: Int, title: String) {
    if (value > 0) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = value.toString())
        }
        Spacer(modifier = Modifier.height(8.dp))
        ProgressBar(
            progress = value / 100f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}