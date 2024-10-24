
@Composable
fun MapScreen() {
    val coroutineScope = rememberCoroutineScope()
    var data by remember { mutableStateOf(AppGlobalData.get(SAVED.map)) }
    var shown by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        setLastLocation("Map")
        println("WIKI - Map")
        println(data)
        loading = false
    }

    if (loading) {
        LoadingIndicator()
    } else {
        // Your grid or map representation here
        FlatGrid(data = data) { item ->
            // Item representation
        }
    }

    if (shown) {
        Dialog(onDismissRequest = { shown = false }) {
            // Dialog content
        }
    }
}

fun WoWsInfoScreen(viewModel: YourViewModel) {
    val data by viewModel.data.collectAsState()
    val shown by viewModel.shown.collectAsState()
    val map by viewModel.map.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val indicator = styles.indicator

    val context = LocalContext.current
    val configuration = context.resources.configuration
    val width = configuration.screenWidthDp.dp.toPx()
    val height = configuration.screenHeightDp.dp.toPx()
    var imageWidth = if (width > height) height else width
    imageWidth -= 20.dp.toPx()

    Column {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            content = {
                items(data) { item ->
                    ListItem(
                        modifier = Modifier.clickable {
                            viewModel.setShown(true)
                            viewModel.setMap(item.icon)
                        },
                        text = { Text(item.name) },
                        secondaryText = { Text(item.description) }
                    )
                }
            }
        )
        if (shown) {
            Dialog(onDismissRequest = { viewModel.setShown(false); viewModel.setLoading(true) }) {
                Box(
                    modifier = Modifier
                        .size(imageWidth.dp)
                        .align(Alignment.Center)
                ) {
                    Image(
                        painter = rememberImagePainter(map),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .onGloballyPositioned { layoutCoordinates ->
                                if (loading) {
                                    viewModel.setLoading(false)
                                }
                            }
                    )
                    if (loading) {
                        LoadingIndicator(modifier = indicator)
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        // Your loading indicator implementation here
    }
}


@Composable
fun Map() {
    Box(modifier = Modifier.fillMaxSize()) {
        val mapView = MapView(context).apply {
            onCreate(null)
            getMapAsync(OnMapReadyCallback { googleMap ->
                // Your map setup code here
            })
        }
        AndroidView({ mapView }, modifier = Modifier.fillMaxSize())
    }
}