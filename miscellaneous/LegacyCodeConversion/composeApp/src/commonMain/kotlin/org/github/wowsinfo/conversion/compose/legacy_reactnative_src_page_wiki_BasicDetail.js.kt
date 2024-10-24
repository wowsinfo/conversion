
@Composable
fun BasicDetail(item: Any) {
    val id = when (item) {
        is Consumable -> item.consumable_id.toString()
        is Achievement -> item.achievement_id.toString()
        is CollectionItem -> item.card_id.toString()
        else -> ""
    }

    WoWsInfo(title = id) {
        DetailContent(item)
    }
}

@Composable
private fun DetailContent(item: Any) {
    val containerModifier = Modifier.fillMaxSize().padding(16.dp)

    when (item) {
        is Consumable -> {
            val { name, description, profile } = item

            Box(
                contentAlignment = Alignment.Center,
                modifier = containerModifier
            ) {
                WikiIcon(item = item)
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleLarge)
                    PriceLabel(item = item)
                    Text(text = description, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = profile.entries.joinToString("\n") { entry ->
                            "${entry.value.description}"
                        },
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }

        is CommanderSkill -> {
            val { name, perks } = item

            Box(
                contentAlignment = Alignment.Center,
                modifier = containerModifier
            ) {
                WikiIcon(item = item)
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = perks.entries.joinToString("\n") { entry ->
                            "${entry.value.description}"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }

        is Achievement, is CollectionItem -> {
            val { description, name } = item

            Box(
                contentAlignment = Alignment.Center,
                modifier = containerModifier
            ) {
                WikiIcon(item = item)
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleLarge)
                    Text(text = description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        else -> {}
    }
}