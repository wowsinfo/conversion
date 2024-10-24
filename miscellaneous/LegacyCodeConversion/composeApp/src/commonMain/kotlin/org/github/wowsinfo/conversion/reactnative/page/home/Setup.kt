
@Composable
fun Setup() {
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }
    var langList by remember { mutableStateOf(emptyMap<String, String>()) }
    var langData by remember { mutableStateOf(emptyList<String>()) }
    var selectedLang by remember { mutableStateOf("en") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val downloader = Downloader(getCurrServer())
            val data = downloader.getLanguage()
            if (data != null) {
                langList = data
                langData = langList.keys.sorted()
                loading = false
            } else {
                error = true
            }
        }
    }

    if (loading) {
        LoadingIndicator()
    } else if (error) {
        Text("Error loading language data")
    } else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Select Language", style = MaterialTheme.typography.h6)
            langData.forEach { lang ->
                Button(onClick = { selectedLang = lang }) {
                    Text(lang)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { 
                setAPILanguage(selectedLang)
                Actions.navigateToNextScreen() 
            }) {
                Text("Continue")
            }
        }
    }
}

fun SettingsScreen(viewModel: SettingsViewModel) {
    val state = viewModel.state.collectAsState()
    val loading = state.value.loading
    val server = state.value.server
    val selectedServer = state.value.selectedServer
    val langList = state.value.langList
    val selectedLang = state.value.selectedLang

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        SectionTitle(title = lang.settings_api_settings, center = true, bold = true)
        Subheading(text = "${lang.setting_game_server}: ${lang.server_name[selectedServer]}")
        LazyColumn {
            items(server.size) { index ->
                Button(onClick = { viewModel.updateServer(index) }) {
                    Text(text = lang.server_name[index])
                }
            }
        }
        Subheading(text = "${lang.setting_api_language}: ${langList[selectedLang] ?: ""}")
        renderAPILanguage()
    }
    if (!loading) {
        FloatingActionButton(
            onClick = { viewModel.finishSetup() },
            content = {
                Text(text = lang.setup_done_button)
            }
        )
    }
}

fun RenderAPILanguage(
    loading: Boolean,
    error: Boolean,
    langData: List<String>,
    langList: Map<String, String>,
    onUpdateApiLanguage: (String) -> Unit,
    onSendFeedback: () -> Unit,
    titleStyle: TextStyle,
    wrapView: Modifier
) {
    if (loading) {
        LoadingIndicator()
    } else if (error) {
        Column {
            Text(text = lang.error_download_issue, style = titleStyle)
            ListItem(
                modifier = Modifier.clickable(onClick = onSendFeedback),
                headlineContent = { Text(text = lang.settings_app_send_feedback) },
                supportingContent = { Text(text = lang.settings_app_send_feedback_subtitle) }
            )
        }
    } else {
        Column(modifier = wrapView) {
            langData.forEach { item ->
                Button(onClick = { onUpdateApiLanguage(item) }) {
                    Text(text = langList[item] ?: "")
                }
            }
        }
    }
}

    setCurrServer(index)
    selectedServer = index
}

    setAPILanguage(lang)
    selectedLang = lang
}

    // Assuming you have a navigation component set up
    navController.navigate("Menu") {
        popUpTo(navController.graph.startDestinationId) { inclusive = true }
    }
}


@Composable
fun Container() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Content goes here
    }
}


@Composable
fun ScrollableContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ) {
        // Your content goes here
    }
}


@Composable
fun TopComponent() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Your content here
    }
}

    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
) {
    FloatingActionButton(
        onClick = { /* Handle click */ },
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        // Content of the FAB
    }
}


@Composable
fun Title() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Your Title",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun WrapView(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}


@Composable
fun Setup() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello, Kotlin Multiplatform!")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text("Click Me")
        }
    }
}