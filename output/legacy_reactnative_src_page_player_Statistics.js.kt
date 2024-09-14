import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wowsnumberstest.R
import com.example.wowsnumberstest.core.AppGlobalData
import com.example.wowsnumberstest.data.getDomain
import com.example.wowsnumberstest.data.getLocalString
import com.example.wowsnumberstest.data.setLastLocation
import com.example.wowsnumberstest.data.toColorResource
import com.example.wowsnumberstest.ui.theme.WoWsNumberSTheme
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun StatisticsScreen(navController: NavController) {
    val context = LocalContext.current
    val info = getLocalString("info")

    if (info == null || !setLastLocation("Statistics")) return

    Surface(
        color = WoWsNumberSTheme.colors.background,
        contentColor = WoWsNumberSTheme.colors.onBackground
    ) {
        Column(modifier = Modifier.systemBarsPadding()) {
            PlayerProfile(info, navController)
        }
    }

}

@Composable
fun PlayerProfile(
    info: Map<String, Any>,
    navController: NavController
) {
    val domain = getDomain(info["server"] as String?)
    if (domain == null) return

    val id = info["account_id"] as String?
    var name by remember { mutableStateOf("") }
    var valid by remember { mutableStateOf(true) }
    var hidden by remember { mutableStateOf(false) }

    // Load basic player data
    LaunchedEffect(Unit) {
        val result = domain.getBasicPlayerData(id)
        if (result == null || result.hidden) {
            valid = false
            hidden = true
        } else {
            name = result.nickname
        }
    }

    if (!valid || id == null || id.isEmpty()) {
        ErrorView()
    } else if (!hidden) {
        val clanTag = remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            val tagResult = domain.getClanTag(id)
            if (tagResult != null && !tagResult.tag.isNullOrEmpty()) {
                clanTag.value = tagResult.tag
            }
        }

        val rating = remember { mutableStateOf(0f) }
        LaunchedEffect(Unit) {
            val ratingResult = domain.getPlayerRating(id)
            rating.value = ratingResult ?: 0f
        }

        PlayerInfo(
            id,
            name,
            clanTag.value,
            rating.value.toColorResource(),
            navController
        )
    } else {
        HiddenAccountView()
    }
}

@Composable
fun ErrorView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(getLocalString("error") ?: "Error")
    }
}

@Composable
fun HiddenAccountView() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(getLocalString("hidden_account") ?: "Hidden Account")
        if (context != null) {
            Button(onClick = { context.toast("Add friend") }) {
                Text(getLocalString("add_friend") ?: "Add Friend")
            }
        }
    }
}

@Composable
fun PlayerInfo(
    id: String,
    name: String,
    clanTag: String,
    ratingColor: Int,
    navController: NavController<Nothing>
) {
    val context = LocalContext.current
    val uiState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(id)
        Text(name)
        if (!clanTag.isEmpty()) {
            Text(clanTag)
        }
        RatingButton(ratingColor)

        if (context != null) {
            Button(onClick = { context.toast("Add friend") }) {
                Text(getLocalString("add_friend") ?: "Add Friend")
            }

            // Set as main account button
            Button(onClick = { context.toast("Set main account") }) {
                Text(getLocalString("set_main_account") ?: "Set Main Account")
            }
        }

        DetailedInfo()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { uiState.value = !uiState.value }) {
                Text(getLocalString("show_more") ?: "Show More")
            }
            IconButton(
                icon = Icons.Filled.ExpandMore,
                onClick = { uiState.value = !uiState.value },
                modifier = Modifier.size(48.dp)
            )
        }

        if (uiState.value) {
            // TODO: Add UI components to display more player data
        }
    }
}

@Composable
fun RatingButton(ratingColor: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Rating", color = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(ratingColor)),
            contentAlignment = Alignment.Center,
        ) {
            Text("100")
        }
    }
}

@Composable
fun DetailedInfo() {
    // TODO: Add UI components to display detailed player data
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewStatistics() {
    StatisticsTheme {
        Surface(color = Color.Black) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Statistics()
            }
        }
    }
}