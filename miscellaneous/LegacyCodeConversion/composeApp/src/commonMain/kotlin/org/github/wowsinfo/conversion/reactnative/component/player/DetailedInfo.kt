
@Composable
fun DetailedInfo(more: Boolean) {
    var width by remember { mutableStateOf(currDeviceWidth()) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        InfoLabel("Player Information")
        Spacer(modifier = Modifier.height(8.dp))
        Info6Icon()
        Spacer(modifier = Modifier.height(8.dp))
        SectionTitle("Details")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text("More Info")
        }
    }
}

@Composable
fun InfoLabel(text: String) {
    Text(text)
}

@Composable
fun Info6Icon() {
    // Implementation for Info6Icon
}

@Composable
fun SectionTitle(text: String) {
    Text(text)
}

fun currDeviceWidth(): Int {
    // Implementation to get current device width
    return 0 // Placeholder
}

    val newWidth = event.layout.width
    state.width = newWidth
}

fun PlayerInfo(data: PlayerData?, more: Boolean, onMoreClick: () -> Unit) {
    if (data == null) {
        return
    }

    val lastBattleTime = data.lastBattleTime
    val pvp = data.pvp
    val playerMode = pvp.maxDamageDealtShipId != null

    Column(modifier = Modifier.fillMaxSize()) {
        if (!playerMode) {
            InfoLabel(
                title = stringResource(R.string.basic_last_battle),
                info = humanTimeString(lastBattleTime)
            )
        }
        Info6Icon(data = pvp)
        if (more) {
            renderMore(playerMode)
        } else {
            Button(onClick = { if (onlyProVersion()) onMoreClick() }) {
                Text(text = stringResource(R.string.basic_more_stat))
            }
        }
        renderAllShipRecord(data.pvp, playerMode)
    }
}

    val pvp = data.pvp
    renderInfo(pvp, playerMode)
}

fun RenderInfo(data: Data, playerMode: Boolean) {
    val container = styles.container
    val horizontal = styles.horizontal
    val lang = LocalLang.current

    val (artAgro, torpedoAgro, battles, wins, losses, draws, survivedBattles, survivedWins, damageDealt, damageScouting, planesKilled, shipsSpotted, xp, frags, maxDamageDealt, maxDamageScouting, maxFragsBattle, maxPlanesKilled, maxShipsSpotted, maxXp) = data

    Column(modifier = container) {
        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_win, info = wins)
            InfoLabel(title = lang.detailed_draw, info = draws)
            InfoLabel(title = lang.detailed_loss, info = losses)
        }
        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_survived, info = survivedBattles)
            InfoLabel(title = lang.detailed_total_xp, info = xp)
            InfoLabel(title = lang.detailed_survived_win, info = survivedWins)
        }
        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_survival_rate, info = "${roundTo((survivedBattles.toDouble() / battles) * 100, 2)}%")
            InfoLabel(title = lang.detailed_survived_win_rate, info = "${roundTo((survivedWins.toDouble() / survivedBattles) * 100, 2)}%")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (artAgro != null) {
            Column(modifier = container) {
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.detailed_total_potential_damage, info = artAgro)
                    InfoLabel(title = lang.detailed_avg_potential_damage, info = roundTo(artAgro / battles))
                }
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.detailed_total_torp_potential_damage, info = torpedoAgro)
                    InfoLabel(title = lang.detailed_avg_torp_potential_damage, info = roundTo(torpedoAgro / battles))
                }
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.detailed_total_scouting_damage, info = damageScouting)
                    InfoLabel(title = lang.detailed_avg_scouting_damage, info = roundTo(damageScouting / battles))
                }
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.detailed_total_damage, info = damageDealt)
                    InfoLabel(title = lang.detailed_damage_potential_ratio, info = "${roundTo((damageDealt.toDouble() / artAgro) * 100, 2)}%")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.detailed_total_spotted, info = shipsSpotted)
                    InfoLabel(title = lang.detailed_avg_spotted, info = roundTo(shipsSpotted.toDouble() / battles, 2))
                }
            }
        }
        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_total_frag, info = frags)
            InfoLabel(title = lang.detailed_frag_spot_ratio, info = "${roundTo((frags.toDouble() / shipsSpotted) * 100, 2)}%")
        }
        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_total_plane_killed, info = planesKilled)
            InfoLabel(title = lang.detailed_avg_plane_killed, info = roundTo(planesKilled.toDouble() / battles, 2))
        }
        if (!playerMode) {
            Column(modifier = container) {
                SectionTitle(title = lang.record_title)
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.record_max_damage_dealt, info = maxDamageDealt)
                    InfoLabel(title = lang.record_max_damage_scouting, info = maxDamageScouting)
                }
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.record_max_xp, info = maxXp)
                    InfoLabel(title = lang.record_max_frags_battle, info = maxFragsBattle)
                }
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.record_max_ships_spotted, info = maxShipsSpotted)
                    InfoLabel(title = lang.record_max_planes_killed, info = maxPlanesKilled)
                }
            }
        }
    }
}

    val (aircraft, mainBattery, ramming, secondBattery, torpedoes) = data

    val weapons = listOf(
        Weapon(lang.warship_artillery_main, mainBattery),
        Weapon(lang.warship_artillery_secondary, secondBattery),
        Weapon(lang.warship_torpedoes, torpedoes),
        Weapon(lang.warship_aircraft, aircraft),
        Weapon(lang.warship_ramming, ramming)
    )

    return if (!playerMode) {
        weapons.map { weapon -> { renderShipRecord(weapon) } }
    } else {
        null
    }
}

fun RenderShipRecord(weapon: Weapon) {
    val data = weapon.data
    val name = weapon.name
    if (data == null) {
        return
    }
    val frags = data.frags
    val maxFragsBattle = data.max_frags_battle
    val hits = data.hits
    val shots = data.shots

    if (frags == 0) {
        return
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = name)
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoLabel(title = lang.weapon_total_frags, info = frags.toString())
            InfoLabel(title = lang.weapon_max_frags, info = maxFragsBattle.toString())
            if (hits != null) {
                InfoLabel(
                    title = lang.weapon_hit_ratio,
                    info = "${roundTo((hits.toDouble() / shots) * 100, 1)}%"
                )
            }
        }
    }
}


@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}

    // Add your composable elements here
}


@Composable
fun DetailedInfo() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Detailed Information", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        // Add more UI elements here as needed
    }
}