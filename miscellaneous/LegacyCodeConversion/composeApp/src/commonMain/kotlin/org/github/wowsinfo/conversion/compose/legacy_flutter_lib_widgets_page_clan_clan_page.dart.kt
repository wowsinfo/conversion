
@Composable
fun ClanPage(clanId: String, viewModel: ClanPageViewModel = hiltViewModelFactory()) {
    val clan by viewModel.clan.collectAsState()
    val members by viewModel.members.collectAsState()
    WowsInfoTheme {
        Scaffold(
            topBar = TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    IconButton(
                        icon = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        onClick = { viewModel.navigateUp() }
                    )
                },
                actions = {}
            ),
            bottomBar = null,
            drawerContent = {},
            floatingActionButton = null,
            floatingActionButtonPosition = null,
            backgroundColor = MaterialTheme.colorScheme.surface,
            content = {
                if (clan.isLoading) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            AsyncImage(
                                model = clan.imageUrl ?: "",
                                error = R.drawable.ic_launcher_background,
                                placeholder = R.drawable.ic_launcher_background,
                                contentDescription = null
                            )
                        }
                        item {
                            Text(clan.tag, style = MaterialTheme.typography.headlineLarge)
                        }

                        item {
                            Text(clan.description ?: "", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            })
    }
}

@Composable
fun ClanMemberItem(member: ClanMember) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(member.accountName ?: "", style = MaterialTheme.typography.bodyMedium)
        Text(member.role ?: "", style = MaterialTheme.typography.bodySmall)
        AsyncImage(
            model = member.avatarUrl ?: "",
            placeholder = R.drawable.ic_launcher_background,
            error = R.drawable.ic_launcher_background,
            contentDescription = null
        )
    }
}