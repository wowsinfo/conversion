import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.application.R
import com.example.application.di.ApplicationComponentHolder
import com.example.application.model.Modernization
import com.example.application.repository.GameRepository
import com.example.application.viewmodels.UpgradeViewModel
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@ExperimentalCoilApi
@Composable
fun UpgradePage() {
    val viewModel: UpgradeViewModel = viewModel(factory = ApplicationComponentHolder.component.viewModelFactory)
    val modernizationList by viewModel.modernizationList.collectAsLazyPagingItems()

    Scaffold(
        topBar = androidx.compose.material3.TopAppBar(
            title = { Text("Upgrade Page") }
        ),
        content = {
            androidx.compose.foundation.lazy.LazyColumn {
                items(modernizationList) { item ->
                    if (item != null) {
                        UpgradeCard(item)
                    } else {
                        CircularProgressIndicator()
                    }
                }

                when (modernizationList.loadState.refresh) {
                    is androidx.paging.LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    is androidx.paging.LoadState.Error -> {
                        val error = modernizationList.loadState.refresh.error
                        item {
                            Text(error.toString())
                        }
                    }
                    else -> {}
                }

            }
        }
    )
}

@ExperimentalCoilApi
@Composable
fun UpgradeCard(modernization: Modernization) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://example.com/data/live/app/assets/upgrades/${modernization.icon}.png",
                placeholder = painterResource(R.drawable.placeholder_image),
                error = painterResource(R.drawable.error_image)
            ),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modernization.title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            overflow = androidx.compose.ui.text.TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            context.getString(modernization.description),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun showErrorToast() {
    val context = LocalContext.current

    Toast.makeText(context, "Error occurred", Toast.LENGTH_SHORT).show()
}