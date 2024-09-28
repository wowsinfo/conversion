
@Composable
fun AppSettingsPage() {
    val settings = remember { SettingsProvider() }
    val app = remember { AppProvider() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(Localisation.app_name) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            DropdownListTile(
                options = settings.servers,
                title = Localisation.setting_game_server,
                value = settings.serverValue,
                onChanged = { value -> settings.updateServer(value) }
            )
            Divider()
            Column {
                Checkbox(
                    checked = app.darkMode,
                    onCheckedChange = { checked ->
                        app.updateDarkMode(checked)
                    }
                )
                Text(Localisation.settings_app_dark_mode)
                ListItem(
                    modifier = Modifier.clickable { showThemeColours() },
                    text = { Text(Localisation.settings_app_theme_colour) },
                    trailing = {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(app.themeColour)
                        )
                    }
                )
            }
            Divider()
            ListItem(
                modifier = Modifier.clickable { App.launch(App.emailToLink) },
                text = { Text(Localisation.settings_app_send_feedback) },
                secondaryText = { Text(Localisation.settings_app_send_feedback_subtitle) }
            )
            ListItem(
                modifier = Modifier.clickable { App.launch(App.newIssueLink) },
                text = { Text(Localisation.settings_app_report_issues) },
                secondaryText = { Text(App.newIssueLink) }
            )
            Divider()
            ListItem(
                modifier = Modifier.clickable { App.launch(App.githubLink) },
                text = { Text(Localisation.settings_open_source_github) },
                secondaryText = { Text(App.githubLink) }
            )
            ListItem(
                modifier = Modifier.clickable { showAboutDialog() },
                text = { Text(Localisation.settings_open_source_licence) }
            )
        }
    }
}

@Composable
fun DropdownListTile(options: List<String>, title: String, value: String, onChanged: (String) -> Unit) {
    // Implementation of DropdownListTile
}

fun showThemeColours() {
    // Implementation of showThemeColours
}

fun showAboutDialog() {
    // Implementation of showAboutDialog
}

    val colours = settings.colours
    val count = Utils(context).getItemCount(4, 2, 300)

    val dialog = AlertDialog.Builder(context)
        .setView(
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(count),
                    content = {
                        items(colours) { colour ->
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(colour)
                                    .clickable {
                                        appProvider.updateThemeColour(colour)
                                        dialog.dismiss()
                                    }
                            )
                        }
                    }
                )
            }
        )
        .create()

    dialog.show()
}

    val value: T,
    val title: String
)


data class DropdownListTile<T>(
    val title: String,
    val options: List<T>,
    val value: T,
    val onChanged: (T) -> Unit
)

@Composable
fun DropdownListTileView(tile: DropdownListTile<String>) {
    val expanded = remember { mutableStateOf(false) }

    Column {
        ListItem(
            headlineContent = { Text(tile.title) },
            supportingContent = {
                Text(tile.value)
            },
            modifier = Modifier.clickable { expanded.value = true }
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            tile.options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        tile.onChanged(option)
                        expanded.value = false
                    }
                ) {
                    Text(option)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDropdownListTile() {
    DropdownListTileView(
        tile = DropdownListTile(
            title = "Select an option",
            options = listOf("Option 1", "Option 2", "Option 3"),
            value = "Option 1",
            onChanged = {}
        )
    )
}

fun CustomDropdown<T>(
    options: List<DropdownValue<T>>,
    value: T,
    title: @Composable () -> Unit,
    onChanged: (T?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(onClick = { expanded = true }) {
            title()
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onChanged(option.value)
                    expanded = false
                }) {
                    Text(option.title)
                }
            }
        }
    }
}

data class DropdownValue<T>(val value: T, val title: String)


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}