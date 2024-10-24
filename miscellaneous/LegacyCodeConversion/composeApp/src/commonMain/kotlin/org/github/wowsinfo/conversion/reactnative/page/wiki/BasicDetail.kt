
@Composable
fun BasicDetail(item: Item) {
    val ID = remember {
        when {
            item.consumableId != null -> item.consumableId
            item.achievementId != null -> item.achievementId
            item.collectionId != null -> item.cardId
            else -> ""
        }
    }

    WoWsInfo(title = ID) {
        renderDetail()
    }
}

@Composable
fun renderDetail() {
    // Implement the detail rendering logic here
}

fun RenderDetail(item: Item) {
    val container = styles.container
    val label = styles.label
    val titleStyle = label + TintTextColour()

    if (item.profile != null) {
        val name = item.name
        val description = item.description
        val profile = item.profile
        val bonus = profile.entries.joinToString("\n") { it.value.description }

        ScrollableColumn(modifier = container) {
            AnimatedVisibility(visible = true) {
                WikiIcon(scale = 1.6f, item = item)
            }
            Text(text = name, style = titleStyle)
            PriceLabel(item = item)
            Text(text = description, style = label)
            Text(text = bonus, style = label)
        }
    } else if (item.perks != null) {
        val name = item.name
        val perks = item.perks
        val bonus = perks.entries.joinToString("\n") { it.value.description }

        ScrollableColumn(modifier = container) {
            AnimatedVisibility(visible = true) {
                WikiIcon(scale = 1.6f, item = item)
            }
            Text(text = name, style = titleStyle)
            Text(text = bonus, style = label)
        }
    } else if (item.imageInactive != null || item.cardId != null) {
        val description = item.description
        val name = item.name

        ScrollableColumn(modifier = container) {
            AnimatedVisibility(visible = true) {
                WikiIcon(scale = 1.6f, item = item)
            }
            Text(text = name, style = titleStyle)
            Text(text = description, style = label)
        }
    }
}


@Composable
fun Container() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        // Your content goes here
    }
}

    text = "Your Text Here",
    modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    textAlign = TextAlign.Center
)


@Composable
fun BasicDetail() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Basic Detail", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        // Add more UI elements here as needed
    }
}