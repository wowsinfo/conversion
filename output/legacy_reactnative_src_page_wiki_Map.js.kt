import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun MapScreen(navController: NavController) {
    val context = LocalContext.current
    val mapData = remember { mutableStateOf(listOf<MapItem>()) }
    val showDialog = remember { mutableStateOf(false) }
    val selectedMap = remember { mutableStateOf("") }

    // Get the map data from shared preferences
    val savedMap = SAVED.map
    val map = AppGlobalData.get(savedMap)
    Log.d("WIKI - Map", "Map: $map")
    mapData.value = map

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Map Screen")
        Button(
            onClick = { navController.navigate("Wiki") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Back to Wiki")
        }
    }

    if (showDialog.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = selectedMap.value,
                contentDescription = "Selected Map",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center),
                onLoading = { painter ->
                    when (painter) {
                        AsyncImagePainter.State.Loading -> LoadingIndicator()
                        else -> {}
                    }
                },
            )
        }
    }

    if (mapData.value.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "No map data available")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            for (mapItem in mapData.value) {
                MapListItem(mapItem.name, mapItem.description, mapItem.icon) {
                    selectedMap.value = mapItem.icon
                    showDialog.value = true
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Map Details") },
            text = { Text("Description: ${selectedMap.value}") },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text("OK")
                }
            },
        )
    }
}

@Composable
fun MapListItem(name: String, description: String, icon: String, onClick: () -> Unit) {
    ListItem(
        text = { Text(text = name) },
        secondaryText = { Text(text = description) },
        trailing = {
            IconButton(onClick = onClick) {
                Image(painterResource(id = R.drawable.ic_arrow_forward), contentDescription = "Icon")
            }
        },
    )
}