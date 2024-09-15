import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.models.ClanResult
import com.example.domain.models.PlayerResult
import com.example.domain.repositories.UserRepository
import com.example.presentation.components.SearchTextField
import com.example.presentation.utils.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SearchProvider(
    searchController: MutableState<TextFieldValue>,
    onTextChanged: (String) -> Unit,
    numOfPlayers: MutableState<Int>,
    numOfClans: MutableState<Int>,
    clans: MutableState<List<ClanResult>>,
    players: MutableState<List<PlayerResult>>,
    onSearchResultSelected: (SearchResult) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            SearchProviderImpl(
                searchController.value.text,
                onTextChanged,
                numOfPlayers,
                numOfClans,
                clans,
                players,
                onSearchResultSelected
            )
        }
    ) {
        it
    }

    SearchTextField(
        text = searchController.value,
        onValueChange = { value ->
            searchController.value = value
            onTextChanged(value.text)
        },
        modifier = Modifier.fillMaxWidth()
    )

    if (searchController.value.text.isNotEmpty) {
        Text(
            text = "Players: ${numOfPlayers.value}",
            style = AppTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = "Clans: ${numOfClans.value}",
            style = AppTheme.typography.body1,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
    }
}

class SearchProviderImpl(private val searchController: String) : SearchProviderInterface {
    private var numOfPlayers by mutableStateOf(0)
    private var numOfClans by mutableStateOf(0)
    private var clans by mutableStateOf(emptyList<ClanResult>())
    private var players by mutableStateOf(emptyList<PlayerResult>())

    override fun onTextChanged(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            search(query)
        }
    }

    override fun search(query: String) {
        if (query.length <= 1) {
            resetAll()
            return
        }

        if (query.length > 1 && query.length <= 5) {
            searchClan(query)
        } else {
            resetClans()
        }

        if (query.length > 2) {
            searchPlayer(query)
        }
    }

    override fun onSearchResultSelected(result: SearchResult) {
        // Implementation for handling the selected search result
    }

    private fun searchClan(query: String) {
        // Implementation for searching clans
    }

    private fun searchPlayer(query: String) {
        // Implementation for searching players
    }

    private fun resetPlayers() {
        numOfPlayers = 0
        players = emptyList()
    }

    private fun resetClans() {
        numOfClans = 0
        clans = emptyList()
    }

    private fun resetAll() {
        resetPlayers()
        resetClans()
        searchController.value = ""
    }
}