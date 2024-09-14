package com.example.gamedata.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamedata.ui.common.Filter
import com.example.gamedata.ui.common.Loading
import com.example.gamedata.ui.home.Menu
import com.example.gamedata.ui.home.Setup
import com.example.gamedata.ui.home.Friend
import com.example.gamedata.ui.home.RS
import com.example.gamedata.ui.home.Search
import com.example.gamedata.ui.wiki.Consumable
import com.example.gamedata.ui.wiki.BasicDetail
import com.example.gamedata.ui.wiki.CommanderSkill
import com.example.gamedata.ui.wiki.Achievement
import com.example.gamedata.ui.wiki.Map
import com.example.gamedata.ui.wiki.Collection
import com.example.gamedata.ui.wiki.Warship
import com.example.gamedata.ui.wiki.WarshipDetail
import com.example.gamedata.ui.wiki.WarshipFilter
import com.example.gamedata.ui.wiki.WarshipModule
import com.example.gamedata.ui.wiki.SimilarGraph
import com.example.gamedata.ui.player.Statistics
import com.example.gamedata.ui.player.PlayerAchievement
import com.example.gamedata.ui.player.Rating
import com.example.gamedata.ui.player.Graph
import com.example.gamedata.ui.clan.ClanInfo
import com.example.gamedata.ui.settings.About
import com.example.gamedata.ui.settings.License
import com.example.gamedata.ui.settings.ProVersion
import com.example.gamedata.ui.settings.Settings

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