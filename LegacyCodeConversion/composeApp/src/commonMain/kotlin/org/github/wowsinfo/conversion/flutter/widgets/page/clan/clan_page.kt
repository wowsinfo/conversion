
@Composable
fun ClanPage(clan: ClanInformation) {
    val clanViewModel: ClanProvider = getViewModel()
    val state = remember { clanViewModel.state }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Clan Information") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextWithCaption(text = clan.name, caption = "Clan Name")
            Spacer(modifier = Modifier.height(8.dp))
            TextWithCaption(text = clan.tag, caption = "Clan Tag")
            Spacer(modifier = Modifier.height(8.dp))
            TextWithCaption(text = clan.membersCount.toString(), caption = "Members Count")
            Spacer(modifier = Modifier.height(8.dp))
            // Add more UI elements as needed based on clan information
        }
    }
}

@Composable
fun TextWithCaption(text: String, caption: String) {
    Column {
        Text(text = text, style = MaterialTheme.typography.h6)
        Text(text = caption, style = MaterialTheme.typography.body2)
    }
}


@Composable
fun ClanPage(clan: ClanResult) {
    val provider: ClanProvider = getViewModel { parametersOf(clan) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(clan.clanId.toString()) }
            )
        }
    ) {
        renderBody(provider)
    }
}

@Composable
fun renderBody(provider: ClanProvider) {
    // Implement the body rendering logic here
}

fun RenderBody(provider: ClanProvider) {
    if (provider.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = provider.tag,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            Text(
                text = provider.tagDescription,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextWithCaption(
                    title = Localisation.clan_created_date,
                    value = provider.createdDate
                )
                TextWithCaption(
                    title = Localisation.clan_creator_name,
                    value = provider.creatorName
                )
                TextWithCaption(
                    title = Localisation.clan_leader_name,
                    value = provider.leaderName
                )
            }
            RenderClanDescription(provider.description)
            Text(
                text = "${Localisation.clan_member_title} (${provider.memberCount})",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            RenderMembers(provider.members)
        }
    }
}

fun RenderClanDescription(description: String?) {
    if (description == null) {
        Spacer(modifier = Modifier.size(0.dp))
    } else {
        PaddingValues(16.dp) {
            Text(text = description)
        }
    }
}

fun RenderMembers(members: List<ClanMember>?) {
    if (members == null) {
        Spacer(modifier = Modifier.size(0.dp))
        return
    }

    val count = Utils().getItemCount(6, 1, 300)
    val width = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = width / count

    Wrap(
        verticalAlignment = Alignment.CenterVertically
    ) {
        members.forEach { member ->
            Box(modifier = Modifier.width(itemWidth)) {
                ListItem(
                    modifier = Modifier.clickable { },
                    text = { Text(member.accountName ?: "-") },
                    secondaryText = { Text(member.role ?: "-") },
                    trailing = { Text(member.joinedAt?.dateString ?: "-") }
                )
            }
        }
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $count", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }) {
            Text("Increment")
        }
    }
}