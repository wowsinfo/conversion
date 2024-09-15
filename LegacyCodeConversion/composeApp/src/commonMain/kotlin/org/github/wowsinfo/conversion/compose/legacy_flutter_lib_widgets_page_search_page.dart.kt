import android.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.wowsinfo.R
import com.example.wowsinfo.data.models.search.SearchResult
import com.example.wowsinfo.helpers.utils.Utils
import com.example.wowsinfo.ui.theme.WoWsInfoTheme
import com.example.wowsinfo.viewmodels.SearchViewModel
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun SearchPage() {
    val searchController = remember { mutableStateOf("") }
    val viewModel: SearchViewModel = getViewModel()
    WoWsInfoTheme {
        Scaffold(
            body = Column {
                renderSearchBar(
                    context = LocalContext.current,
                    searchController = searchController,
                    onSubmitted = viewModel::search
                )
                Expanded(
                    modifier = Modifier.fillMaxHeight(),
                    content = {
                        LazyColumn(
                            state = rememberScrollState(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                            item {
                                renderTitle(
                                    title = stringResource(R.string.menu_search_clan),
                                    count = viewModel.numOfClans
                                )
                                renderWrap(viewModel.clans)
                            }
                            item {
                                renderTitle(
                                    title = stringResource(R.string.menu_search_player),
                                    count = viewModel.numOfPlayers
                                )
                                renderWrap(viewModel.players)
                            }
                        }
                    }
                )
            }
        )
    }
}

@Composable
fun renderSearchBar(
    context: Context,
    searchController: MutableState<String>,
    onSubmitted: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(100))
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            crossAxisAlignment = CrossAxisAlignment.stretch,
            mainAxisSize = MainAxisSize.Max,
            children = listOf(
                IconButton(
                    onClick = { context.finish() },
                    icon = Icons.Filled.ArrowBack
                ),
                Expanded(
                    modifier = Modifier.weight(1f),
                    child = SearchView(context).apply {
                        queryHint = stringResource(R.string.search)
                        onQueryTextSubmit = onSubmitted
                        isIconified = false
                        maxWidth = Float.MAX_VALUE
                        setQuery(searchController.value, true)
                        searchController.observe { text ->
                            query = text
                        }
                    }
                ),
                if (searchController.value.isNotEmpty) {
                    IconButton(
                        onClick = {
                            searchController.value = ""
                            onSubmitted("")
                        },
                        icon = Icons.Filled.Close
                    )
                }
            )
        )
    }
}

@Composable
fun renderTitle(title: String, count: Int) {
    Text(
        "${title} (${count})",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun renderWrap(result: List<SearchResult>) {
    val itemCount = Utils.getItemCount(6, 1, 300)
    val context = LocalContext.current
    val itemSize = result.size.toFloat()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        itemsIndexed(result) { index, value ->
            Box(
                modifier = Modifier
                    .clickable(onClick = { viewModel.onResultSelected(context, value) })
                    .width((context.resources.displayMetrics.widthPixels / itemCount).dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
                content = {
                    Text(value.displayName, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(value.id, color = Color.Gray)
                }
            )
        }
    }
}