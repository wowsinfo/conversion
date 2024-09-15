import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class LoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadingScreen()
        }
    }

    @Composable
    private fun LoadingScreen() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFE53935)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedLogo()
                Text(
                    text = "Loading...",
                    style = TextStyle(color = Color.White),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    @Composable
    private fun AnimatedLogo() {
        Image(
            painterResource(id = R.drawable.logo), // Replace with your actual logo resource ID
            contentDescription = "Logo",
            modifier = Modifier.size(128.dp).animateContentSize()
        )
    }
}