import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.yizhenwind.aurora.R
import com.yizhenwind.aurora.core.*
import com.yizhenwind.aurora.data.local.model.CollectionItem
import com.yizhenwind.aurora.data.remote.response.WikiCardDetailResponse
import com.yizhenwind.aurora.ui.common.PreviewProvider
import com.yizhenwind.aurora.ui.wiki.component.*

@Composable
fun Collection(
    modifier: Modifier = Modifier,
    item: List<CollectionItem>?,
    navController: NavController? = null,
    onItemClick: (WikiCardDetailResponse) -> Unit = {}
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        setLastLocation("Collection")
        Log.i(TAG, "WIKI - Collection")
    }

    val data = remember { mutableStateOf<List<CollectionItem>>(if (item != null) item else listOf()) }
    val collection by remember { derivedStateOf { data.value.isNotEmpty() } }
    val header by remember { derivedStateOf { if (collection) data.value.firstOrNull() else null } }
    val saved = AppGlobalData.get(SAVED.collection).collection
    val ID = remember(data) { if (data.value.isNotEmpty() && data.value[0].card_id != null) data.value[0].collection_id else "" }

    LaunchedEffect(key1 = collection, key2 = header) {
        if (collection) {
            val id = header?.collection_id ?: return@LaunchedEffect
            val items = AppGlobalData.get(SAVED.collection).item

            val collectionItems = mutableListOf<CollectionItem>()
            collectionItems.add(saved[id]!!)
            for (one in items.keys) {
                val curr = items[one]
                if (curr.collection_id == id) {
                    collectionItems.add(curr)
                }
            }

            data.value = collectionItems
        } else {
            Object.keys(saved).forEach { k ->
                data.value += saved[k]!!
            }
        }
    }

    WoWsInfo(
        modifier = modifier,
        title = ID
    ) {
        FlatGrid(
            itemDimension = 80.dp,
            data = data.value,
            renderItem = { item ->
                WikiIcon(
                    themeIcon = !collection,
                    item = item,
                    onItemClick = { onItemClick(it) }
                )
            },
            ListHeaderComponent = {
                if (collection) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        WikiIcon(item = header, scale = 1.6f)
                        Text(text = stringResource(id = R.string.wiki_collection), style = MaterialTheme.typography.titleMedium)
                        Text(text = stringResource(id = R.string.wiki_collection_description), style = MaterialTheme.typography.bodySmall)
                    }
                } else {
                    null
                }
            },
            showsVerticalScrollIndicator = false
        )
    }

    LaunchedEffect(key1 = true) {
        navController?.let {
            if (context is CollectionActivity) {
                context.onBackPressedCallback = {
                    it.navigateUp()
                }
            }
        }
    }
}

@PreviewProvider
@Composable
private fun Preview() {
    val navController = rememberNavController()
    Collection(
        modifier = Modifier.fillMaxSize(),
        item = listOf<CollectionItem>(),
        navController = navController,
        onItemClick = { wikiCardDetailResponse ->
            // Handle item click
        }
    )
}