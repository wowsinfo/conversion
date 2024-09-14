import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Setup() {
    val serverHeight: MutableState<Double?> = remember { mutableStateOf(null) }
    val logoOpacity: MutableState<Float> = remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        logoOpacity.value = 1f
        delay(2000)
        serverHeight.value = null
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            children = listOf(
                AnimatedOpacity(
                    opacity = logoOpacity.value,
                    durationMillis = 500,
                    content = { _appLogoWithText() },
                ),
                AnimatedSize(
                    durationMillis = 300,
                    content = {
                        if (serverHeight.value != null) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                children = listOf(
                                    Text(
                                        "Please select your server",
                                        style = MaterialTheme.typography.titleMedium
                                    ),
                                    _buildServerButton("Russia"),
                                    _buildServerButton("Europe"),
                                    _buildServerButton("North America"),
                                    _buildServerButton("Asia")
                                )
                            )
                        }
                    },
                )
            )
        )
    }
}

@Composable
fun _appLogoWithText() {
    Surface(
        modifier = Modifier.padding(16.dp),
        color = Color.Blue,
        shape = CircleShape,
        elevation = 4.dp,
    ) {
        Column {
            Text(text = "WoWs Info", style = MaterialTheme.typography.headline5)
        }
    }
}

@Composable
fun _buildServerButton(title: String) {
    Button(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .size(148.dp, 32.dp),
        colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        onClick = { /* No-op */ },
    ) {
        Text(text = title)
    }
}