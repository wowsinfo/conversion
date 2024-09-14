import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModelFactory
import coil.compose.AsyncImage
import com.example.wowsinfo.R
import com.example.wowsinfo.core.utils.Utils
import com.example.wowsinfo.data.local.model.ClanMember
import com.example.wowsinfo.databinding.ActivityMainBinding
import com.example.wowsinfo.ui.screens.clan.ClanPageViewModel
import com.example.wowsinfo.ui.theme.WowsInfoTheme

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