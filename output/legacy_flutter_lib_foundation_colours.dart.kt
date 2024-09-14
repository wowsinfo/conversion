import androidx.compose.runtime.Composable

class WoWsColours {
    // price colours
    companion object {
        @Composable val creditPrice = com.google.accompanist.systemuicontroller.SystemUiController(
            statusBarColor = Color(android.graphics.Color.blueGrey),
            navigationBarColor = Color(android.graphics.Color.blueGrey)
        )
        
        @Composable val goldPrice = com.google.accompanist.systemuicontroller.SystemUiController(
            statusBarColor = Color(android.graphics.Color.orange),
            navigationBarColor = Color(android.graphics.Color.orange)
        )

        // ship name colours
        @Composable val premiumShip = com.google.accompanist.systemuicontroller.SystemUiController(
            statusBarColor = Color(android.graphics.Color.orange),
            navigationBarColor = Color(android.graphics.Color.orange)
        )
        
        @Composable val specialShip = com.google.accompanist.systemuicontroller.SystemUiController(
            statusBarColor = Color(android.graphics.Color.deepOrangeAccent),
            navigationBarColor = Color(android.graphics.Color.deepOrangeAccent)
        )
    }
}