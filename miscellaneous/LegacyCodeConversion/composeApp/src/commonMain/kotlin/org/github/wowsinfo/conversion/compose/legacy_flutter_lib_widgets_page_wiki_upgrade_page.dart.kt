
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