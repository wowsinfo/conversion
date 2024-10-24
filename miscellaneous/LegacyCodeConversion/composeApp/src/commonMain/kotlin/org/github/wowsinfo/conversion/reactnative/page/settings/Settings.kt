
@Composable
fun SettingsScreen() {
    val coroutineScope = rememberCoroutineScope()
    var darkMode by remember { mutableStateOf(AppGlobalData.isDarkMode) }
    var showColour by remember { mutableStateOf(false) }
    var server by remember { mutableStateOf(getCurrServer()) }
    var apiLanguage by remember { mutableStateOf(getAPILanguage()) }
    var userLanguage by remember { mutableStateOf(getUserLang()) }
    var swapButton by remember { mutableStateOf(getSwapButton()) }

    val colourList = listOf(
        RED, PINK, PURPLE, DEEPPRUPLE, INDIGO, BLUE, LIGHTBLUE, CYAN,
        TEAL, GREEN, LIGHTGREEN, LIME, YELLOW, AMBER, DEEPORANGE, BROWN, GREY, BLUEGREY
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            // Dark Mode Toggle
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Dark Mode")
                Switch(checked = darkMode, onCheckedChange = { darkMode = it })
            }

            // Server Selection
            Text("Current Server: $server", modifier = Modifier.padding(16.dp))
            Button(onClick = { /* Handle server change */ }) {
                Text("Change Server")
            }

            // API Language Selection
            Text("API Language: $apiLanguage", modifier = Modifier.padding(16.dp))
            Button(onClick = { /* Handle language change */ }) {
                Text("Change Language")
            }

            // User Language Selection
            Text("User Language: $userLanguage", modifier = Modifier.padding(16.dp))
            Button(onClick = { /* Handle user language change */ }) {
                Text("Change User Language")
            }

            // Swap Button Toggle
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Swap Button")
                Switch(checked = swapButton, onCheckedChange = { swapButton = it })
            }

            // Colour Selection
            Button(onClick = { showColour = !showColour }) {
                Text("Select Colour")
            }

            if (showColour) {
                LazyColumn {
                    items(colourList) { colour ->
                        Row(modifier = Modifier.padding(8.dp)) {
                            Text(colour.toString())
                            Checkbox(checked = false, onCheckedChange = { /* Handle colour selection */ })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}

    CoroutineScope(Dispatchers.Main).launch {
        delay(300)
        // Refresh logic here
    }
}

fun MyComponent(viewModel: MyViewModel) {
    val showColour by viewModel.showColour.collectAsState()
    val colourList = viewModel.colourList

    Column {
        ScrollableColumn {
            renderAPISettings()
            renderAppSettings()
            renderWoWsInfo()
            renderOpenSource()
        }
        if (showColour) {
            Dialog(onDismissRequest = { viewModel.setShowColour(false) }) {
                Surface(shape = RoundedCornerShape(16.dp), modifier = Modifier.height(0.618.dp)) {
                    LazyColumn {
                        items(colourList) { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .background(Color(item[500]))
                                    .clickable { viewModel.updateTint(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun RenderAPISettings(state: YourStateType, updateServer: (Int) -> Unit, updateUserLang: (String) -> Unit) {
    val langList = getAPIList()
    val appLang = mapOf(
        "en" to "English",
        "ja" to "日本語",
        "zh" to "简体中文",
        "zh-hant" to "繁体中文"
    )
    val appLangList = appLang.map { (code, lang) -> LanguageItem(code, lang) }

    val display = appLang[state.userLanguage] ?: "???"

    Column {
        SectionTitle(title = lang.settings_api_settings)
        Section(title = "${lang.setting_game_server} - ${lang.server_name[state.server]}") {
            Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                SERVER.forEachIndexed { index, key ->
                    Button(onClick = { updateServer(index) }) {
                        Text(text = lang.server_name[index])
                    }
                }
            }
        }
        Section(title = "${lang.setting_api_language} - ${langList[state.APILanguage]}") {
            if (AppGlobalData.shouldUpdateAPI) {
                RenderAPILanguage(langList)
            }
        }
        Section(title = "${lang.setting_app_language} - $display") {
            Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                appLangList.forEach { item ->
                    Button(onClick = { updateUserLang(item.code) }) {
                        Text(text = item.lang)
                    }
                }
            }
        }
    }
}

data class LanguageItem(val code: String, val lang: String)

@Composable
fun Section(title: String, content: @Composable () -> Unit) {
    Column {
        Text(text = title, style = MaterialTheme.typography.h6)
        content()
    }
}

fun RenderAPILanguage(langList: Map<String, String>, updateApiLanguage: (String, Boolean) -> Unit) {
    val langData = langList.keys.sorted()

    Column {
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            langData.forEach { item ->
                Button(onClick = { updateApiLanguage(item, false) }) {
                    Text(langList[item] ?: "")
                }
            }
        }
        Button(
            onClick = {
                // Show AlertDialog
                AlertDialog(
                    onDismissRequest = { /* Do nothing */ },
                    title = { Text(lang.app_name) },
                    text = { Text(lang.setting_api_update_data_title) },
                    confirmButton = {
                        Button(
                            onClick = { 
                                updateApiLanguage(currentAPILanguage, true) 
                            }
                        ) {
                            Text(lang.setting_api_update_data_update)
                        }
                    },
                    dismissButton = {
                        Button(onClick = { /* Do nothing */ }) {
                            Text(lang.setting_api_update_data_cancel)
                        }
                    }
                )
            }
        ) {
            Text(lang.setting_api_update_data)
        }
    }
}

fun RenderAppSettings(
    tintColour: Color,
    swapButton: Boolean,
    darkMode: Boolean,
    onUpdateTheme: () -> Unit,
    onSwapButton: (Boolean) -> Unit,
    onShowColour: () -> Unit
) {
    Column {
        SectionTitle(title = lang.settings_app_settings)
        ListItem(
            modifier = Modifier.clickable { onUpdateTheme() },
            text = { Text(lang.settings_app_dark_mode) },
            trailing = {
                Checkbox(checked = darkMode, onCheckedChange = null)
            }
        )
        ListItem(
            modifier = Modifier.clickable { onShowColour() },
            text = { Text(lang.settings_app_theme_colour) },
            trailing = {
                Box(modifier = Modifier.size(24.dp).background(tintColour))
            }
        )
        ListItem(
            modifier = Modifier.clickable { onSwapButton(!swapButton) },
            text = { Text(lang.settings_app_swap_buttons) },
            trailing = {
                Checkbox(checked = swapButton, onCheckedChange = null)
            }
        )
    }
}

fun WoWsInfo() {
    val issueLink = "${APP.Github}/issues/new"

    Column {
        SectionTitle(title = lang.app_name)
        ListItem(
            modifier = Modifier.clickable { SimpleViewHandler.openURL(APP.Developer) },
            text = { Text(lang.settings_app_send_feedback) },
            secondaryText = { Text(lang.settings_app_send_feedback_subtitle) }
        )
        ListItem(
            modifier = Modifier.clickable { SimpleViewHandler.openURL(issueLink) },
            text = { Text(lang.settings_app_report_issues) },
            secondaryText = { Text(issueLink) }
        )
        if (isAndroid) {
            ListItem(
                modifier = Modifier.clickable { checkAppUpdate() },
                text = { Text(lang.settings_app_check_for_update) },
                secondaryText = { Text("v${APP.Version}") }
            )
        }
    }
}

fun RenderOpenSource() {
    Column {
        SectionTitle(title = lang.settings_open_source)
        ListItem(
            modifier = Modifier.clickable { SimpleViewHandler.openURL(APP.Github) },
            headlineContent = { Text(text = lang.settings_open_source_github) },
            supportingContent = { Text(text = APP.Github) }
        )
        ListItem(
            modifier = Modifier.clickable { SafeAction("License") },
            headlineContent = { Text(text = lang.settings_open_source_licence) },
            supportingContent = { Text(text = lang.settings_open_source_licence_subtitle) }
        )
    }
}

    if (AppGlobalData.canCheckForUpdate) {
        AppGlobalData.canCheckForUpdate = false
        SafeFetch.normal(WikiAPI.Github_AppVersion).onSuccess { v ->
            val version = v["version"] as? String
            if (version != null) {
                if (version > APP.Version) {
                    displayUpdate(true, version)
                } else {
                    displayUpdate(false)
                }
            }
        }.onFailure {
            displayUpdate(false)
        }
    } else {
        displayUpdate(false)
    }
}

    if (result) {
        val context = LocalContext.current
        val message = String.format(lang.settings_app_has_update, version)
        AlertDialog.Builder(context)
            .setTitle(lang.app_name)
            .setMessage(message)
            .setPositiveButton("Google Play") { _, _ -> 
                SimpleViewHandler.openURL(APP.GooglePlay)
            }
            .setNegativeButton("Github") { _, _ -> 
                SimpleViewHandler.openURL(APP.LatestRelease)
            }
            .show()
    } else {
        AlertDialog.Builder(context)
            .setTitle(lang.app_name)
            .setMessage(lang.settings_app_no_update)
            .setPositiveButton("OK", null)
            .show()
    }
}

    setSwapButton(curr)
    swapButtonState.value = getSwapButton()
}

    val tintColour = state.tintColour
    UpdateDarkMode()
    state.darkMode = AppGlobalData.isDarkMode
    props.theme.dark = AppGlobalData.isDarkMode

    if (AppGlobalData.isDarkMode) {
        AppGlobalData.darkTheme = Theme(
            colors = DarkTheme.colors.copy(
                View = Color.Black,
                text = Color(GREY[50]),
                primary = tintColour[500],
                accent = tintColour[300]
            )
        )
        props.theme.colors = AppGlobalData.darkTheme.colors
    } else {
        AppGlobalData.lightTheme = Theme(
            colors = DefaultTheme.colors.copy(
                View = Color.White,
                text = Color(GREY[900]),
                primary = tintColour[500],
                accent = tintColour[300]
            )
        )
        props.theme.colors = AppGlobalData.lightTheme.colors
    }
    println(props.theme)
}

    updateTintColour(tint)

    theme.colors.primary = tint["500"] ?: Color.Unspecified
    theme.colors.accent = tint["300"] ?: Color.Unspecified

    setState { 
        showColour = false 
        tintColour = tint 
    }
}

    setCurrServer(index)
    server = index
}

    if (!force && language == state.apiLanguage) {
        return
    }

    setApiLanguage(language)
    state = state.copy(apiLanguage = language)

    setFirstLaunch(true)
    AppGlobalData.shouldUpdateAPI = false
    navigateToMenu()
}

    setUserLang(code)
    lang.setLanguage(code)
    userLanguage.value = code
}


@Composable
fun Container() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Add your content here
    }
}

    modifier = Modifier
        .fillMaxSize()
        .align(Alignment.BottomCenter)
) {
    // Your content here
}

    .size(36.dp)
    .clip(CircleShape)


@Composable
fun Settings() {
    MaterialTheme {
        // Your settings UI components go here
    }
}