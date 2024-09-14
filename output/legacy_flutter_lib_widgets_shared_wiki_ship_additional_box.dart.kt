@Composable
fun ShipAdditionalBox(shipAdditional: ShipAdditional) {
    val battles = shipAdditional.battles
    val localised = Localisation.of(context)
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        TextWithCaption(
            title = localised.warship_avg_damage,
            value = shipAdditional.damage.toDecimalString()
        )
        TextWithCaption(
            title = localised.warship_avg_winrate,
            value = shipAdditional.winrate.asPercentString()
        )
        TextWithCaption(
            title = localised.warship_avg_frag,
            value = shipAdditional.frags.toDecimalString()
        )
        if (battles != null)
            TextWithCaption(
                title = Localisation.instance.battles,
                value = battles.toDecimalString()
            )
    }
}