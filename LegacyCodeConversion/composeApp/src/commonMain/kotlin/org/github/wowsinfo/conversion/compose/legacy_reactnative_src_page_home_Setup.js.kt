import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.wowsinfo.R
import com.example.wowsinfo.core.Downloader
import com.example.wowsinfo.core.NativeSimpleViewHandler
import com.example.wowsinfo.value.APP
import com.example.wowsinfo.value.data.getCurrServer
import com.example.wowsinfo.value.data.setAPILanguage
import com.example.wowsinfo.value.data.setCurrServer
import com.example.wowsinfo.value.lang
import com.example.wowsinfo.value.server.SERVER

@Composable
fun Setup() {
    val context = LocalContext.current
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }
    var server by remember { mutableStateOf(SERVER) }
    var selectedServer by remember { mutableStateOf(getCurrServer()) }
    var langList by remember { mutableStateOf(emptyMap<String, String>()) }
    var selectedLang by remember { mutableStateOf("") }

    val d = Downloader(selectedServer)
    LaunchedEffect(Unit) {
        d.getLanguage().let { data ->
            if (data != null) {
                val newLangList = data
                val newLangData = mutableListOf<String>()
                for (key in newLangList.keys.sorted()) {
                    newLangData.add(key)
                }
                langList = newLangList
                selectedLang = "en"
                loading = false
            } else {
                error = true
            }
        }
    }

    Scaffold(
        topBar = { WoWsInfo(title = stringResource(R.string.settings_api_settings)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Do something on button click */ },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16)
                .verticalScroll(rememberScrollState())
        ) {
            SectionTitle(title = stringResource(R.string.settings_api_settings))
            Text(text = "${stringResource(R.string.setting_game_server)}: ${lang.server_name[selectedServer]}")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                server.forEachIndexed { index, _ ->
                    Button(onClick = { updateServer(index) }) {
                        Text(text = lang.server_name[index])
                    }
                }
            }

            Text(text = "${stringResource(R.string.setting_api_language)}: ${langList[selectedLang]?.ifEmpty { "" }}")
            if (loading) {
                CircularProgressIndicator()
            } else if (error) {
                Text(text = stringResource(R.string.error_download_issue))
                List.Item(
                    onClick = { NativeSimpleViewHandler.openURL(APP.Developer, context) },
                    modifier = Modifier.padding(16),
                    title = stringResource(R.string.settings_app_send_feedback)
                ) {
                    Text(text = stringResource(R.string.settings_app_send_feedback_subtitle))
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    langList.keys.forEach { key ->
                        Button(onClick = { updateApiLanguage(key) }) {
                            Text(text = langList[key] ?: "")
                        }
                    }
                }
            }

            FAB(
                onClick = { /* Do something on button click */ },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                label = stringResource(R.string.setup_done_button)
            )
        }
    }
}

private fun updateServer(index: Int) {
    setCurrServer(index)
    selectedServer = index
}

private fun updateApiLanguage(lang: String) {
    setAPILanguage(lang)
    selectedLang = lang
}