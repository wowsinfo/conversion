import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.wowsfanapp.core.AppGlobalData
import com.example.wowsfanapp.core.WoWsInfo
import com.example.wowsfanapp.data.SAVED
import com.example.wowsfanapp.model.getTierList
import com.example.wowsfanapp.ui.theme.WoWsFanAppTheme
import com.example.wowsfanapp.value.lang

@Composable
fun WarshipFilter() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val name = remember { mutableStateOf("") }
    val premium = remember { mutableStateOf(false) }
    val nation = remember { mutableStateListOf<String>() }
    val type = remember { mutableStateListOf<String>() }
    val tier = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        scrollState.scrollTo(Offset(0f, 128f))
    }

    WoWsInfo(
        hideAds = true,
        title = stringResource(id = R.string.wiki_warship_filter_placeholder),
        onPress = { name.value = "" }
    ) {
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(stringResource(id = R.string.wiki_warship_filter_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            imeAction = ImeAction.Done,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            trailingIcon = {
                if (name.value.isNotEmpty) {
                    IconButton(onClick = { name.value = "" }) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            },
        )
    }
    
    ScrollableColumn(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.wiki_warship_filter_tier))
        tier.forEach { t ->
            Text(text = t)
        }
        tierList().forEach { item ->
            Button(onClick = { addData(item, MODE.TIER) }, modifier = Modifier.padding(4.dp)) {
                Text(text = item)
            }
        }

        Text(text = stringResource(id = R.string.wiki_warship_filter_nation))
        nation.forEach { n ->
            Text(text = n)
        }
        nations(context).forEach { (k, v) ->
            Button(onClick = { addData(v, MODE.NATION) }, modifier = Modifier.padding(4.dp)) {
                Text(text = v)
            }
        }

        Text(text = stringResource(id = R.string.wiki_warship_filter_type))
        type.forEach { t ->
            Text(text = t)
        }
        types(context).forEach { (k, v) ->
            Button(onClick = { addData(v, MODE.TYPE) }, modifier = Modifier.padding(4.dp)) {
                Text(text = v)
            }
        }

    }

    FooterPlus(
        contentAlignment = Alignment.CenterHorizontally
    ) {
        ListItem(
            leadingIcon = {
                Checkbox(
                    checked = premium.value,
                    onCheckedChange = { value -> premium.value = value }
                )
            },
            title = { Text(text = stringResource(id = R.string.wiki_warship_filter_premium)) }
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = ::resetAll,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.wiki_warship_reset_btn))
            }

            Button(
                onClick = ::applyAll,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.wiki_warship_filter_btn))
            }
        }
    }
}

@Composable
fun tierList(): List<String> {
    return getTierList()
}

@Composable
fun nations(context: Context): Map<String, String> {
    return AppGlobalData.get(SAVED.ENCYCLOPEDIA).shipNations
}

@Composable
fun types(context: Context): Map<String, String> {
    return AppGlobalData.get(SAVED.ENCYCLOPEDIA).shipTypes
}