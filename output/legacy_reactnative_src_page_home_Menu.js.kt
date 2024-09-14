import android.net.Uri
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rzrddev.wowsinfo.core.AppGlobalData
import com.rzrddev.wowsinfo.core.NativeManager.ReactNativeManager
import com.rzrddev.wowsinfo.core.NativeManager.QuickAction
import com.rzrddev.wowsinfo.core.NativeManager.SimpleViewHandler
import com.rzrddev.wowsinfo.core.data.*
import com.rzrddev.wowsinfo.core.util.Downloader
import com.rzrddev.wowsinfo.ui.components.AppName
import com.rzrddev.wowsinfo.ui.components.SectionTitle
import com.rzrddev.wowsinfo.ui.theme.WoWsInfo
import com.rzrddev.wowsinfo.value.lang.R.*
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun Menu(
  theme: Theme,
  modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var loading by remember { mutableStateOf(true) }
    var main by remember { mutableStateOf(LOCAL.userInfo.get() ?: UserInfo()) }

    // Data for the list
    val wiki = listOf(
        WikiEntry(stringResource(R.string.wiki_achievement), "Achievement", ::SafeAction),
        WikiEntry(stringResource(R.string.wiki_warships), "Warship", ::SafeAction),
        WikiEntry(stringResource(R.string.wiki_upgrades), "Upgrade", { SafeAction("Consumable", mapOf("upgrade" to true)) }),
        WikiEntry(stringResource(R.string.wiki_flags), "Camouflage", ::SafeAction),
        // WikiEntry(stringResource(R.string.wiki_skills), "CommanderSkill", ::SafeAction),
        WikiEntry(stringResource(R.string.wiki_maps), "map", ::SafeAction),
        WikiEntry(stringResource(R.string.wiki_collections), "Collection", ::SafeAction)
    )

    val domain = getCurrDomain()
    val prefix = getCurrPrefix()

    val officalWebsites = listOf(
        WebsiteEntry(stringResource(R.string.website_official_site), "https://worldofwarships.${domain}/"),
        WebsiteEntry(stringResource(R.string.website_premium), "https://${prefix}.wargaming.net/shop/wows/"),
        WebsiteEntry(stringResource(R.string.website_global_wiki), "http://wiki.wargaming.net/en/World_of_Warships/"),
        WebsiteEntry(stringResource(R.string.website_dev_blog), "https://blog.worldofwarships.com/")
    )

    val statsInfoWebsite = listOf(
        // WebsiteEntry(stringResource(R.string.website_sea_group), "https://sea-group.org/"),
        // WebsiteEntry(stringResource(R.string.website_daily_bounce), "https://thedailybounce.net/category/world-of-warships/"),
        WebsiteEntry(stringResource(R.string.website_numbers), "https://${prefix}.wows-numbers.com/"),
        // WebsiteEntry(stringResource(R.string.website_models), "https://sketchfab.com/tags/world-of-warships"),
        WebsiteEntry(stringResource(R.string.website_game_models), "https://gamemodels3d.com/games/worldofwarships/")
    )

    val ultilityWebsites = listOf(
        // WebsiteEntry(stringResource(R.string.website_ap_calculator), "https://mustanghx.github.io/ship_ap_calculator/"),
        WebsiteEntry(stringResource(R.string.website_wowsft), "https://wowsft.com/")
    )

    val ingameWebsites = listOf(
        WebsiteEntry(stringResource(R.string.website_wargaming_login), "https://${prefix}.wargaming.net/id/signin/"),
        WebsiteEntry(stringResource(R.string.website_userbonus), "https://worldofwarships.${domain}/userbonus/"),
        WebsiteEntry(stringResource(R.string.website_news_ingame), "https://worldofwarships.${domain}/news_ingame/"),
        WebsiteEntry(stringResource(R.string.website_ingame_armory), "https://armory.worldofwarships.${domain}/"),
        WebsiteEntry(stringResource(R.string.website_ingame_clan), "https://clans.worldofwarships.${domain}/clans/gateway/wows/profile/"),
        WebsiteEntry(stringResource(R.string.website_ingame_warehouse), "https://warehouse.worldofwarships.${domain}/"),
        WebsiteEntry(stringResource(R.string.website_my_logbook), "https://logbook.worldofwarships.${domain}/")
    )

    val links = listOf(
        WebsiteEntry(stringResource(R.string.content_creator_official), lang.content_creator_official_link),
        WebsiteEntry(stringResource(R.string.content_creator_fubuki), lang.content_creator_fubuki_link)
    )

    val youtubers = listOf(
        WebsiteEntry(stringResource(R.string.youtuber_official), "https://www.youtube.com/user/worldofwarshipsCOM"),
        WebsiteEntry(stringResource(R.string.youtuber_flambass), "https://www.youtube.com/user/Flambass"),
        WebsiteEntry(stringResource(R.string.youtuber_flamu), "https://www.youtube.com/user/cheesec4t"),
        WebsiteEntry(stringResource(R.string.youtuber_iChaseGaming), "https://www.youtube.com/user/ichasegaming"),
        WebsiteEntry(stringResource(R.string.youtuber_jingles), "https://www.youtube.com/user/BohemianEagle"),
        WebsiteEntry(stringResource(R.string.youtuber_notser), "https://www.youtube.com/user/MrNotser"),
        WebsiteEntry(stringResource(R.string.youtuber_NoZoupForYou), "https://www.youtube.com/user/ZoupGaming"),
        WebsiteEntry(stringResource(R.string.youtuber_panzerknacker), "https://www.youtube.com/user/pzkpasch"),
        WebsiteEntry(stringResource(R.string.youtuber_Toptier), "https://www.youtube.com/channel/UCXOZ2gv_ZGomWNcQU8BBfdQ"),
        WebsiteEntry(stringResource(R.string.youtuber_yuro), "https://www.youtube.com/user/spzjess")
  },
  wrap: {
    flexWrap: 'wrap',
    flexDirection: 'row',
  },
});

### Usage: