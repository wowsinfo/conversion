package com.example.wowsinfo


@Composable
fun License() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            LazyColumn {
                items(libraries) { item ->
                    LibraryItem(item)
                }
            }
        }
    }
}

@Composable
private fun LibraryItem(library: LibraryInfo) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                val context = LocalContext.current
                SimpleViewHandler.openURL(context, library.link)
            },
        elevation = MaterialTheme.typography.elevation
    ) {
        Column {
            Text(text = stringResource(id = R.string.app_name))
            Text(text = library.name)
            Text(text = library.link)
        }
    }
}

data class LibraryInfo(
    val name: String,
    val link: String
)

val libraries = listOf(
    LibraryInfo("react", "https://github.com/facebook/react"),
    LibraryInfo("react-native", "https://github.com/facebook/react-native"),
    LibraryInfo("react-native-animatable", "https://github.com/oblador/react-native-animatable"),
    LibraryInfo("react-native-device-detection", "https://github.com/m0ngr31/react-native-device-detection"),
    LibraryInfo(
        "react-native-exception-handler",
        "https://github.com/master-atul/react-native-exception-handler"
    ),
    LibraryInfo("react-native-iap", "https://github.com/dooboolab/react-native-iap"),
    LibraryInfo("react-native-keep-awake", "https://github.com/corbt/react-native-keep-awake"),
    LibraryInfo(
        "react-native-localization",
        "https://github.com/stefalda/ReactNativeLocalization"
    ),
    LibraryInfo("react-native-material-color", "https://github.com/DerayGa/react-native-material-color"),
    LibraryInfo("react-native-paper", "https://github.com/callstack/react-native-paper"),
    LibraryInfo(
        "react-native-router-flux",
        "https://github.com/aksonov/react-native-router-flux"
    ),
    LibraryInfo("react-native-super-grid", "https://github.com/saleel/react-native-super-grid"),
    LibraryInfo(
        "react-native-vector-icons",
        "https://github.com/oblador/react-native-vector-icons"
    ),
    LibraryInfo("string-format", "https://github.com/davidchambers/string-format"),
    LibraryInfo(
        "native-chart-experiment",
        "https://github.com/HenryQuan/native-chart-experiment"
    ),
    LibraryInfo(lang.app_name, "https://github.com/HenryQuan/WoWs-Info")
)