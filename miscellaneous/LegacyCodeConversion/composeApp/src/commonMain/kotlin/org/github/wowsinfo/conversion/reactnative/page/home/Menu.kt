
@Composable
fun Menu() {
    val coroutineScope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(true) }
    var main by remember { mutableStateOf(AppGlobalData.get(LOCAL.userInfo)) }
    val bestItemWidth = bestWidth(400)

    LaunchedEffect(Unit) {
        getData()
    }

    if (loading) {
        Loading()
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            // Add your UI components here, for example:
            Text(text = "Menu", style = MaterialTheme.typography.h4)
            // Other components like List, Buttons, etc.
        }
    }
}

suspend fun getData() {
    // Implement your data fetching logic here
}

    getData()
    val curr = AppGlobalData.get(LOCAL.userInfo)
    setLastLocation("")
    if (curr.accountId != main.accountId) {
        main = curr
    }
}

    private var loading = mutableStateOf(true)

    override fun onStart() {
        super.onStart()
        ReactNativeManager.appHasLoaded()
        QuickAction.performPendingShortcut()

        if (first) {
            val time = CompletableDeferred(false).apply {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(20000)
                    complete(false)
                }
            }

            val dn = Downloader(getCurrServer())
            val update = CompletableDeferred<UpdateResult>().apply {
                CoroutineScope(Dispatchers.Main).launch {
                    val data = dn.updateAll(true)
                    complete(data)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                when (val result = selectFirstCompletedOf(listOf(time, update))) {
                    false -> {
                        Alert.alert(lang.error_title, lang.error_timeout)
                        loading.value = false
                    }
                    is UpdateResult -> {
                        loading.value = false
                        if (result.status) {
                            setFirstLaunch(false)
                        } else {
                            Alert.alert(
                                lang.error_title,
                                "${lang.error_download_issue}\n\n${result.log}",
                                listOf(
                                    AlertAction(lang.settings_app_send_feedback_subtitle) {
                                        SimpleViewHandler.openURL("${APP.Developer}&body=${result.log}")
                                    },
                                    AlertAction("OK") {}
                                )
                            )
                            loading.value = false
                        }
                    }
                }
            }
        } else {
            if (differentMonth()) {
                loading.value = false
            } else {
                validateProVersion()
                    .onSuccess {
                        loading.value = false
                    }
                    .onFailure {
                        // Handle failure if needed
                    }
            }
        }
    }
}

    private lateinit var wiki: List<WikiItem>
    private lateinit var officialWebsites: List<Website>
    private lateinit var statsInfoWebsite: List<Website>
    private lateinit var utilityWebsites: List<Website>
    private lateinit var ingameWebsites: List<Website>
    private lateinit var links: List<Link>
    private lateinit var youtubers: List<YouTuber>

    fun getData() {
        wiki = listOf(
            WikiItem(lang.wiki_achievement, "Achievement") { SafeAction("Achievement") },
            WikiItem(lang.wiki_warships, "Warship") { SafeAction("Warship") },
            WikiItem(lang.wiki_upgrades, "Upgrade") { SafeAction("Consumable", mapOf("upgrade" to true)) },
            WikiItem(lang.wiki_flags, "Camouflage") { SafeAction("Consumable") },
            WikiItem(lang.wiki_maps, "map") { SafeAction("Map") },
            WikiItem(lang.wiki_collections, "Collection") { SafeAction("Collection") }
        )

        val domain = getCurrDomain()
        val prefix = getCurrPrefix()

        officialWebsites = listOf(
            Website(lang.website_official_site, "https://worldofwarships.$domain/"),
            Website(lang.website_premium, "https://$prefix.wargaming.net/shop/wows/"),
            Website(lang.website_global_wiki, "http://wiki.wargaming.net/en/World_of_Warships/"),
            Website(lang.website_dev_blog, "https://blog.worldofwarships.com/")
        )

        statsInfoWebsite = listOf(
            Website(lang.website_numbers, "https://$prefix.wows-numbers.com/"),
            Website(lang.website_game_models, "https://gamemodels3d.com/games/worldofwarships/")
        )

        utilityWebsites = listOf(
            Website(lang.website_wowsft, "https://wowsft.com/")
        )

        ingameWebsites = listOf(
            Website(lang.website_wargaming_login, "https://$prefix.wargaming.net/id/signin/"),
            Website(lang.website_userbonus, "https://worldofwarships.$domain/userbonus/"),
            Website(lang.website_news_ingame, "https://worldofwarships.$domain/news_ingame/"),
            Website(lang.website_ingame_armory, "https://armory.worldofwarships.$domain/"),
            Website(lang.website_ingame_clan, "https://clans.worldofwarships.$domain/clans/gateway/wows/profile/"),
            Website(lang.website_ingame_warehouse, "https://warehouse.worldofwarships.$domain/"),
            Website(lang.website_my_logbook, "https://logbook.worldofwarships.$domain/")
        )

        links = listOf(
            Link(lang.content_creator_official, lang.content_creator_official_link),
            Link(lang.content_creator_fubuki, lang.content_creator_fubuki_link)
        )

        youtubers = listOf(
            YouTuber(lang.youtuber_official, "https://www.youtube.com/user/worldofwarshipsCOM"),
            YouTuber(lang.youtuber_flambass, "https://www.youtube.com/user/Flambass"),
            YouTuber(lang.youtuber_flamu, "https://www.youtube.com/user/cheesec4t"),
            YouTuber(lang.youtuber_iChaseGaming, "https://www.youtube.com/user/ichasegaming"),
            YouTuber(lang.youtuber_jingles, "https://www.youtube.com/user/BohemianEagle"),
            YouTuber(lang.youtuber_notser, "https://www.youtube.com/user/MrNotser"),
            YouTuber(lang.youtuber_NoZoupForYou, "https://www.youtube.com/user/ZoupGaming"),
            YouTuber(lang.youtuber_panzerknacker, "https://www.youtube.com/user/pzkpasch"),
            YouTuber(lang.youtuber_Toptier, "https://www.youtube.com/channel/UCXOZ2gv_ZGomWNcQU8BBfdQ"),
            YouTuber(lang.youtuber_yuro, "https://www.youtube.com/user/spzjess")
        )
    }
}

data class WikiItem(val title: String, val uri: String, val action: () -> Unit)
data class Website(val title: String, val url: String)
data class Link(val title: String, val url: String)
data class YouTuber(val title: String, val url: String)

    val goodWidth = event.layout.width
    setState { bestItemWidth = bestWidth(400, goodWidth) }
}

fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    val loading = state.loading
    val main = state.main

    if (loading) {
        Loading()
        return
    }

    val enabled = main.accountId.isNotEmpty()
    var title = "- ${main.nickname} -"
    if (title == "-  -") {
        title = "- ??? -"
    }

    WoWsInfo(
        noRight = true,
        title = title,
        onPress = if (enabled) { { SafeAction("Statistics", main) } } else null,
        home = true,
        upper = false
    ) {
        ScrollableColumn(
            verticalScroll = rememberScrollState(),
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(visible = true) {
                AppName()
            }
            renderProButton()
            AnimatedVisibility(visible = true) {
                renderContent()
            }
        }
        FloatingActionButton(
            onClick = { SafeAction("Search") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Search, contentDescription = null)
        }
    }
}

fun RenderProButton() {
    if (isProVersion()) {
        return
    }
    Button(
        onClick = { navigateToProVersion() },
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(text = lang.pro_upgrade_button)
    }
}

fun RenderContent() {
    val icon = styles.icon
    val wrap = styles.wrap
    val bestItemWidth = state.bestItemWidth
    val store = if (isAndroid) APP.GooglePlay else APP.AppStore

    Column(modifier = Modifier.padding(bottom = 80.dp)) {
        SectionTitle(title = lang.wiki_section_title)
        Column(modifier = wrap) {
            wiki.forEach { item ->
                ListItem(
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(start = 8.dp)
                        .width(bestItemWidth.dp),
                    text = { Text(item.t) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.i),
                            contentDescription = null,
                            modifier = Modifier
                                .then(icon)
                                .background(ThemeBackColour())
                        )
                    },
                    trailing = {
                        if (!isAndroid) {
                            Icon(
                                painter = painterResource(id = R.drawable.chevron_right),
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    },
                    onClick = { item.p() }
                )
            }
        }
        SectionTitle(title = lang.extra_section_title)
        Column(modifier = wrap) {
            ListItem(
                modifier = Modifier.width(bestItemWidth.dp),
                text = { Text("RS Beta") },
                description = { Text(lang.extra_rs_beta) },
                onClick = { if (onlyProVersion()) SafeAction("RS") }
            )
            ListItem(
                modifier = Modifier.width(bestItemWidth.dp),
                text = { Text(lang.settings_app_write_review) },
                description = { Text(store) },
                onClick = {
                    AlertDialog(
                        onDismissRequest = {},
                        title = { Text(lang.settings_app_write_review_title) },
                        text = { Text(lang.settings_app_write_review_message) },
                        confirmButton = {
                            Button(onClick = { Linking.openURL(APP.Developer) }) {
                                Text(lang.settings_app_write_review_yes)
                            }
                        },
                        dismissButton = {
                            Button(onClick = { Linking.openURL(store) }) {
                                Text(lang.settings_app_write_review_no)
                            }
                        }
                    )
                }
            )
            ListItem(
                modifier = Modifier.width(bestItemWidth.dp),
                text = { Text(lang.settings_app_share) },
                description = { Text(lang.settings_app_share_subtitle) },
                onClick = { shareApp(store) }
            )
        }
        SectionTitle(title = lang.website_title)
        ListSection(title = lang.website_official_title, expanded = true) {
            Column(modifier = wrap) {
                official_websites.forEach { item ->
                    ListItem(
                        modifier = Modifier.width(bestItemWidth.dp),
                        text = { Text(item.t) },
                        description = { Text(item.d) },
                        onClick = { SimpleViewHandler.openURL(item.d) }
                    )
                }
            }
        }
        ListSection(title = lang.content_creator_title, expanded = true) {
            Column(modifier = wrap) {
                links.forEach { item ->
                    ListItem(
                        modifier = Modifier.width(bestItemWidth.dp),
                        text = { Text(item.t) },
                        description = { Text(item.d) },
                        onClick = { SimpleViewHandler.openExternalURL(item.d) }
                    )
                }
            }
        }
        ListSection(title = lang.website_stats_news_title, expanded = true) {
            Column(modifier = wrap) {
                stats_info_website.forEach { item ->
                    ListItem(
                        modifier = Modifier.width(bestItemWidth.dp),
                        text = { Text(item.t) },
                        description = { Text(item.d) },
                        onClick = { SimpleViewHandler.openURL(item.d) }
                    )
                }
            }
        }
        ListSection(title = lang.website_utility_title, expanded = true) {
            Column(modifier = wrap) {
                utility_websites.forEach { item ->
                    ListItem(
                        modifier = Modifier.width(bestItemWidth.dp),
                        text = { Text(item.t) },
                        description = { Text(item.d) },
                        onClick = { SimpleViewHandler.openURL(item.d) }
                    )
                }
            }
        }
        ListSection(
            title = lang.website_ingame_title,
            description = lang.website_wargaming_login_subtitle
        ) {
            Column(modifier = wrap) {
                ingame_websites.forEach { item ->
                    ListItem(
                        modifier = Modifier.width(bestItemWidth.dp),
                        text = { Text(item.t) },
                        description = { Text(item.d) },
                        onClick = { SimpleViewHandler.openURL(item.d) }
                    )
                }
            }
        }
    }
}

    if (isIos()) {
        Share.share(url = store)
    } else {
        Share.share(message = "${lang.app_name}\n$store")
    }
}


@Composable
fun Container() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Your content goes here
    }
}


@Composable
fun CustomIcon() {
    Icon(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
            .size(100.dp),
        contentDescription = null
    )
}


@Composable
fun Fab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = { /* Handle click */ }) {
            // FAB content
        }
    }
}

    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .verticalScroll(rememberScrollState())
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Add your content here
    }
}


@Composable
fun Menu() {
    // Your menu implementation here
}

@Preview
@Composable
fun PreviewMenu() {
    Menu()
}