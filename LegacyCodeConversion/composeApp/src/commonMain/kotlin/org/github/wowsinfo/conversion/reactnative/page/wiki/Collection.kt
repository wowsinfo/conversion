
@Composable
fun CollectionScreen(item: CollectionItem?) {
    val collection = remember { mutableStateListOf<CollectionItem>() }
    var isCollection by remember { mutableStateOf(false) }
    var header by remember { mutableStateOf<CollectionItem?>(null) }

    LaunchedEffect(item) {
        if (item != null) {
            collection.add(item)
            isCollection = true
            header = collection.firstOrNull()
        } else {
            val saved = AppGlobalData.get(SAVED.collection).collection
            collection.addAll(saved.values)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        header?.let {
            Text(text = it.title, modifier = Modifier.padding(16.dp))
        }
        LazyColumn {
            items(collection) { collectionItem ->
                CollectionItemView(collectionItem)
            }
        }
    }
}

@Composable
fun CollectionItemView(item: CollectionItem) {
    // Implementation of the item view
    Text(text = item.title)
}

fun MyComponent(viewModel: MyViewModel) {
    val state = viewModel.state.collectAsState()
    val data = state.value.data
    val collection = state.value.collection
    val header = state.value.header

    val ID = if (data.isNotEmpty() && data[0].cardId != null) {
        data[0].collectionId
    } else {
        ""
    }

    WoWsInfo(title = ID) {
        FlatGrid(
            itemDimension = 80.dp,
            data = data,
            renderItem = { item ->
                WikiIcon(
                    themeIcon = !collection,
                    item = item,
                    onPress = { viewModel.itemOrCollection(item) }
                )
            },
            listHeaderComponent = {
                if (collection) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        WikiIcon(item = header, scale = 1.6f)
                        Title(
                            text = header.name,
                            style = labelStyle
                        )
                        Paragraph(
                            text = header.description,
                            style = labelStyle
                        )
                    }
                } else {
                    null
                }
            },
            showsVerticalScrollIndicator = false
        )
    }
}

    if (item.cardId != null) {
        SafeAction("BasicDetail", mapOf("item" to item))
    } else {
        val id = item.collectionId
        val items = AppGlobalData.get(SAVED.collection).item

        val collectionItems = mutableListOf<AppItem>()
        collectionItems.add(AppGlobalData.get(SAVED.collection).collection[id]!!)
        for (curr in items) {
            if (curr.collectionId == id) {
                collectionItems.add(curr)
            }
        }
        SafeAction("Collection", mapOf("item" to collectionItems), 1)
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

    contentAlignment = Alignment.Center
) {
    Text(
        text = "Your Text Here",
        textAlign = TextAlign.Center
    )
}


@Composable
fun Collection() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Your UI components go here
    }
}