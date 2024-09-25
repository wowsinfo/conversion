
@Composable
fun WarshipStat(profile: Profile) {
    val mobility = profile.mobility
    val weaponry = profile.weaponry
    val concealment = profile.concealment
    val armour = weaponry.armour

    Column {
        renderProgress(armour.total, lang.warship_survivability)
        renderProgress(weaponry.artillery, lang.warship_artillery)
        renderProgress(weaponry.torpedoes, lang.warship_torpedoes)
        renderProgress(weaponry.anti_aircraft, lang.warship_antiaircraft)
        renderProgress(mobility.total, lang.warship_maneuverability)
        renderProgress(weaponry.aircraft, lang.warship_aircraft)
        renderProgress(concealment.total, lang.warship_concealment)
    }
}

@Composable
fun renderProgress(value: Int, label: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label)
        LinearProgressIndicator(progress = value / 100f)
        Text(text = value.toString())
    }
}

fun RenderProgress(value: Int?, title: String) {
    if (value != null && value > 0) {
        val headerStyle = MaterialTheme.typography.h6
        Column {
            Row(modifier = Modifier.padding(8.dp)) {
                Text(text = title, style = headerStyle)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = value.toString(), style = headerStyle)
            }
            LinearProgressIndicator(progress = value / 100f)
        }
    }
}


@Composable
fun Container() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        // Add your content here
    }
}

fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Add your header content here
        }
    }
}


@Composable
fun WarshipStat() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Warship Statistics", style = MaterialTheme.typography.h6)
        // Add more UI elements here as needed
    }
}