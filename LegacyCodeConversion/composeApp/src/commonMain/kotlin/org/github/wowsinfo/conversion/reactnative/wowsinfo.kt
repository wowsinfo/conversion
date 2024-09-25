
@Composable
fun App() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController, startDestination = "loading") {
        composable("loading") { LoadingScreen(navController) }
        composable("menu") { MenuScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("about") { AboutScreen(navController) }
        composable("setup") { SetupScreen(navController) }
        composable("consumable") { ConsumableScreen(navController) }
        composable("commanderSkill") { CommanderSkillScreen(navController) }
        composable("basicDetail") { BasicDetailScreen(navController) }
        composable("achievement") { AchievementScreen(navController) }
        composable("gameMap") { GameMapScreen(navController) }
        composable("collection") { CollectionScreen(navController) }
        composable("warship") { WarshipScreen(navController) }
        composable("warshipDetail") { WarshipDetailScreen(navController) }
        composable("warshipFilter") { WarshipFilterScreen(navController) }
        composable("warshipModule") { WarshipModuleScreen(navController) }
        composable("statistics") { StatisticsScreen(navController) }
        composable("clanInfo") { ClanInfoScreen(navController) }
        composable("playerAchievement") { PlayerAchievementScreen(navController) }
        composable("rating") { RatingScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("graph") { GraphScreen(navController) }
        composable("similarGraph") { SimilarGraphScreen(navController) }
        composable("license") { LicenseScreen(navController) }
        composable("rs") { RSPage(navController) }
        composable("proVersion") { ProVersionScreen(navController) }
    }

    setJSExceptionHandler { e, fatal ->
        if (fatal) {
            showAlert("${e.name}\n${e.message}", "JS", context)
        }
    }
}

    // Your code logic here
} catch (e: Exception) {
    println("KotlinException\n$e")
}


@Composable
fun App() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(true) }
    var dark by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val data = DataLoader.loadAll()
        AppGlobalData.setupWith(data)
        AppGlobalData.shouldSwapButton = AppGlobalData.get(LOCAL.swapButton)
        AppGlobalData.lastLocation = AppGlobalData.get(LOCAL.lastLocation)
        AppGlobalData.isDarkMode = AppGlobalData.get(LOCAL.darkMode)

        val userLang = AppGlobalData.get(LOCAL.userLanguage)
        if (userLang.isNotEmpty()) {
            lang.setLanguage(userLang)
        }

        var tint = TintColour()
        if (tint[50] == null) {
            tint = RED
        }

        AppGlobalData.darkTheme = DarkTheme(colors = DarkTheme.colors.copy(
            surface = Color.Black,
            onSurface = GREY[50],
            primary = tint[500],
            secondary = tint[300]
        ))

        AppGlobalData.lightTheme = LightTheme(colors = LightTheme.colors.copy(
            surface = Color.White,
            onSurface = GREY[900],
            primary = tint[500],
            secondary = tint[300]
        ))

        props.theme.roundness = 32
        props.theme.dark = AppGlobalData.isDarkMode
        props.theme.colors = if (AppGlobalData.isDarkMode) {
            AppGlobalData.darkTheme.colors
        } else {
            AppGlobalData.lightTheme.colors
        }

        val first = getFirstLaunch()
        if (!first) {
            val dn = Downloader(getCurrServer())
            val obj = dn.updateAll(false)
            loading = false
            dark = AppGlobalData.isDarkMode
            if (!obj.status) {
                AlertDialog.Builder(context)
                    .setTitle(lang.error_title)
                    .setMessage("${lang.error_download_issue}\n\n${obj.log}")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        } else {
            loading = false
            dark = AppGlobalData.isDarkMode
        }
    }

    if (loading) {
        CircularProgressIndicator()
    } else {
        // Your main UI goes here
    }
}

fun showAlert(msg: String, mode: String) {
    val context = LocalContext.current
    AlertDialog.Builder(context)
        .setTitle("FATAL $mode ERROR")
        .setMessage("$msg\n\nPlease contact developer")
        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        .setNegativeButton("E-mail") { dialog, _ ->
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:development.henryquan@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "[WoWs Info ${APP.Version}]")
                putExtra(Intent.EXTRA_TEXT, msg)
            }
            context.startActivity(intent)
        }
        .show()
}

fun App() {
    val loading by remember { mutableStateOf(false) }
    val dark by remember { mutableStateOf(false) }

    if (loading) {
        Loading()
    } else {
        NavHost(navController = rememberNavController(), startDestination = "Menu") {
            composable("Menu") { Menu() }
            composable("Setup") { Setup(initial = getFirstLaunch()) }
            composable("Search") { Search() }
            composable("RS") { RS() }
            composable("Rating") { Rating() }
            composable("Statistics") { Statistics() }
            composable("Graph") { Graph() }
            composable("PlayerAchievement") { PlayerAchievement() }
            composable("PlayerShip") { PlayerShip() }
            composable("PlayerShipDetail") { Detailed() }
            composable("Rank") { Rank() }
            composable("ClanInfo") { ClanInfo() }
            composable("Consumable") { Consumable() }
            composable("CommanderSkill") { CommanderSkill() }
            composable("Achievement") { Achievement() }
            composable("Map") { GameMap() }
            composable("Collection") { Collection() }
            composable("Warship") { Warship() }
            composable("WarshipFilter") { WarshipFilter() }
            composable("SimilarGraph") { SimilarGraph() }
            composable("WarshipDetail") { WarshipDetail() }
            composable("WarshipModule") { WarshipModule() }
            composable("BasicDetail") { BasicDetail() }
            composable("Settings") { Settings() }
            composable("License") { License() }
            composable("About") { About() }
            composable("ProVersion") { ProVersion() }
        }
    }
}

    if (navController.backQueue.size == 1) {
        exitProcess(0)
    }
}


@Composable
fun App() {
    MaterialTheme {
        // Your app content goes here
    }
}

@Preview
@Composable
fun PreviewApp() {
    App()
}

fun main() = singleWindowApplication {
    App()
}