
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