import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.wowsinfo.R
import com.example.wowsinfo.core.AppGlobalData

@Composable
fun WoWsInfo(
    children: @Composable () -> Unit,
    empty: Boolean = false,
    style: Modifier = Modifier,
    title: String? = null,
    onPress: (() -> Unit)? = null,
    about: Boolean = false,
    upper: Boolean = true,
    noLeft: Boolean = false,
    noRight: Boolean = false,
    home: Boolean = false,
    navController: NavController
) {
    val context = LocalContext.current
    val luckyText = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // 80% of the time, it will be the app name
        val r = Random.nextInt(10)
        if (r < 8) {
            luckyText.value = context.getString(R.string.app_name)
        } else {
            luckyText.value = "WoWs Info ${name[Random.nextInt(name.size)]}"
        }
    }

    val renderLeft: @Composable () -> Unit = {
        if (noLeft) return@Composable

        IconButton(
            onClick = { /* Handle press event */ },
            icon = {
                Icon(
                    painter = painterResource(if (home) R.drawable.cog else R.drawable.home),
                    contentDescription = null
                )
            }
        )
    }

    val renderRight: @Composable () -> Unit = {
        if (noRight) return@Composable

        IconButton(
            onClick = { /* Handle press event */ },
            icon = {
                Icon(
                    painter = painterResource(if (home) R.drawable.search else R.drawable.arrow_left),
                    contentDescription = null
                )
            }
        )
    }

    val handlePressEvent: () -> Unit = {
        onPress?.invoke() ?: if (about) navController.navigate("About") else { /* Do nothing */ }
    }

    Scaffold(
        modifier = style.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                contentColor = AppGlobalData.isDarkMode(Color.LightGray) else Color.DarkGray,
                containerColor = AppGlobalData.themeColour
            ) {
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    if (AppGlobalData.shouldSwapButton) renderRight() else renderLeft()
                    Text(
                        text = title ?: luckyText.value,
                        style = TextStyle(
                            fontSize = 17,
                            fontWeight = if (isAndroid()) FontWeight.Bold else FontWeight.Light,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    if (!AppGlobalData.shouldSwapButton) renderRight() else renderLeft()
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            children()
        }
    }
}