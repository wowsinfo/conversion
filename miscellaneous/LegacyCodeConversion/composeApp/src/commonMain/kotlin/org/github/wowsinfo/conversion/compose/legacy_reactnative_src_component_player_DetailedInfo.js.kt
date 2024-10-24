
@Composable
fun DetailedInfo(data: Data?, more: Boolean, onMorePressed: () -> Unit) {
    val container = Modifier.fillMaxSize().wrapContentHeight(Alignment.Center)
    val horizontal = Modifier.fillMaxWidth()

    if (data == null) return

    val lastBattleTime = data.last_battle_time
    val pvp = data.pvp
    var playerMode = false
    if (pvp.max_damage_dealt_ship_id != null) {
        playerMode = true
    }

    Column(modifier = container) {
        if (!playerMode) {
            InfoLabel(
                title = lang.basic_last_battle,
                info = lastBattleTime.toHumanTimeString()
            )
        }
        Info6Icon(data = pvp)
        if (more) {
            renderMore(pvp, playerMode)
        } else {
            Button(onClick = { onMorePressed() }) {
                Text(text = lang.basic_more_stat)
            }
        }

        renderAllShipRecord(data.pvp, playerMode)
    }
}

@Composable
fun renderMore(pvp: PVPData, playerMode: Boolean) {
    val horizontal = Modifier.fillMaxWidth()
    val { art_agro, torpedo_agro, battles, wins, losses, draws, survived_battles, survived_wins, damage_dealt, damage_scouting, planes_killed, ships_spotted, xp, frags, max_damage_dealt, max_damage_scouting, max_frags_battle, max_planes_killed, max_ships_spotted, max_xp } = pvp

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_win, info = wins)
            InfoLabel(title = lang.detailed_draw, info = draws)
            InfoLabel(title = lang.detailed_loss, info = losses)
        }
        Row(modifier = horizontal) {
            InfoLabel(
                title = lang.detailed_survived,
                info = survived_battles
            )
            InfoLabel(title = lang.detailed_total_xp, info = xp)
            InfoLabel(
                title = lang.detailed_survived_win,
                info = survived_wins
            )
        }
        Row(modifier = horizontal) {
            InfoLabel(
                title = lang.detailed_survival_rate,
                info = (survived_battles / battles * 100).roundToInt().toPercentString()
            )
            InfoLabel(
                title = lang.detailed_survived_win_rate,
                info =
                    (survived_wins / survived_battles * 100).roundToInt().toPercentString()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (art_agro != null) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.detailed_total_potential_damage,
                        info = art_agro
                    )
                    InfoLabel(
                        title = lang.detailed_avg_potential_damage,
                        info = (art_agro / battles).roundToInt()
                    )
                }
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.detailed_total_torp_potential_damage,
                        info = torpedo_agro
                    )
                    InfoLabel(
                        title = lang.detailed_avg_torp_potential_damage,
                        info =
                            (torpedo_agro / battles).roundToInt()
                    )
                }
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.detailed_total_scouting_damage,
                        info = damage_scouting
                    )
                    InfoLabel(
                        title = lang.detailed_avg_scouting_damage,
                        info =
                            (damage_scouting / battles).roundToInt()
                    )
                }
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.detailed_total_damage,
                        info = damage_dealt
                    )
                    InfoLabel(
                        title = lang.detailed_damage_potential_ratio,
                        info =
                            ((damage_dealt / art_agro) * 100).roundToInt().toPercentString()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.detailed_total_spotted,
                        info = ships_spotted
                    )
                    InfoLabel(
                        title = lang.detailed_avg_spotted,
                        info =
                            (ships_spotted / battles).roundToInt().toDecimalString(2)
                    )
                }
            }
        }

        Row(modifier = horizontal) {
            InfoLabel(title = lang.detailed_total_frag, info = frags)
            InfoLabel(
                title = lang.detailed_frag_spot_ratio,
                info =
                    ((frags / ships_spotted) * 100).roundToInt().toPercentString()
            )
        }
        Row(modifier = horizontal) {
            InfoLabel(
                title = lang.detailed_total_plane_killed,
                info = planes_killed
            )
            InfoLabel(
                title = lang.detailed_avg_plane_killed,
                info =
                    (planes_killed / battles).roundToInt().toDecimalString(2)
            )
        }
        if (!playerMode) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SectionTitle(title = lang.record_title)
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.record_max_damage_dealt,
                        info = max_damage_dealt
                    )
                    InfoLabel(
                        title = lang.record_max_damage_scouting,
                        info = max_damage_scouting
                    )
                }
                Row(modifier = horizontal) {
                    InfoLabel(title = lang.record_max_xp, info = max_xp)
                    InfoLabel(
                        title = lang.record_max_frags_battle,
                        info = max_frags_battle
                    )
                }
                Row(modifier = horizontal) {
                    InfoLabel(
                        title = lang.record_max_ships_spotted,
                        info = max_ships_spotted
                    )
                    InfoLabel(
                        title = lang.record_max_planes_killed,
                        info = max_planes_killed
                    )
                }
            }
        }
    }
}

@Composable
fun renderAllShipRecord(data: PVPData, playerMode: Boolean) {
    val weapons = listOf(
        Pair(lang.warship_artillery_main, data.main_battery),
        Pair(lang.warship_artillery_secondary, data.second_battery),
        Pair(lang.warship_torpedoes, data.torpedoes),
        Pair(lang.warship_aircraft, data.aircraft),
        Pair(lang.warship_ramming, data.ramming)
    )

    if (!playerMode) {
        weapons.forEach { weapon ->
            val (name, data) = weapon
            renderShipRecord(name, data)
        }
    }
}

@Composable
fun renderShipRecord(name: String, data: WeaponData?) {
    if (data?.frags == 0) return

    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = name)
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoLabel(title = lang.weapon_total_frags, info = data?.frags ?: 0)
            InfoLabel(
                title = lang.weapon_max_frags,
                info = data?.max_frags_battle ?: 0
            )
            if (data?.hits != null) {
                InfoLabel(
                    title = lang.weapon_hit_ratio,
                    info =
                        ((data.hits / data.shots * 100)).roundToInt().toPercentString()
                )
            }
        }
    }
}