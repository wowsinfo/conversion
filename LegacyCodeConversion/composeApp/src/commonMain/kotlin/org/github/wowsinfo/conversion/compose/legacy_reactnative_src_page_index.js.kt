package com.example.gamedata.ui


@Composable
fun MenuScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home/menu") {
        composable("home/menu") { Menu(navController) }
        composable("home/setup") { Setup(navController) }
        composable("home/friend") { Friend(navController) }
        composable("home/rs") { RS(navController) }
        composable("home/search") { Search(navController) }

        composable("wiki/consumable") { Consumable() }
        composable("wiki/basicDetail/{itemId}") { route -> BasicDetail(route.arguments?.getString("itemId")) }
        composable("wiki/commanderSkill") { CommanderSkill() }
        composable("wiki/achievement") { Achievement() }
        composable("wiki/map") { Map() }
        composable("wiki/collection") { Collection() }
        composable("wiki/warship") { Warship(navController) }
        composable("wiki/warshipDetail/{itemId}") { route -> WarshipDetail(route.arguments?.getString("itemId")) }
        composable("wiki/warshipFilter") { WarshipFilter(navController) }
        composable("wiki/warshipModule") { WarshipModule() }
        composable("wiki/similarGraph") { SimilarGraph() }

        composable("player/statistics") { Statistics() }
        composable("player/playerAchievement") { PlayerAchievement(navController) }
        composable("player/rating") { Rating() }
        composable("player/graph/{itemId}") { route -> Graph(route.arguments?.getString("itemId")) }

        composable("clan/clanInfo") { ClanInfo(navController) }

        composable("settings/about") { About() }
        composable("settings/license") { License() }
        composable("settings/proVersion") { ProVersion() }
        composable("settings/settings") { Settings(navController) }

        composable("common/filter") { Filter(navController) }
        composable("common/loading") { Loading(navController) }
    }
}