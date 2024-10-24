
data class Library(val name: String, val link: String)

val libraries = listOf(
    Library("react", "https://github.com/facebook/react"),
    Library("react-native", "https://github.com/facebook/react-native"),
    Library("react-native-animatable", "https://github.com/oblador/react-native-animatable")
)

@Composable
fun LicenseScreen() {
    Column {
        Text("Libraries Used in WoWs Info")
        LazyColumn {
            items(libraries) { library ->
                Text("${library.name}: ${library.link}")
            }
        }
    }
}

@Preview
@Composable
fun PreviewLicenseScreen() {
    LicenseScreen()
}


@Composable
fun DeviceDetection() {
    val context = LocalContext.current
    val deviceInfo = getDeviceInfo()

    // Use deviceInfo to render UI
}

fun getDeviceInfo(): DeviceInfo {
    val device = UIDevice.currentDevice
    val model = device.model
    val systemName = device.systemName
    val systemVersion = device.systemVersion

    return DeviceInfo(model, systemName, systemVersion)
}

data class DeviceInfo(val model: String, val systemName: String, val systemVersion: String)


class ExceptionHandler {
    fun setHandler() {
        // Set up your exception handler here
    }

    fun handleException(exception: Throwable) {
        CoroutineScope(Dispatchers.Main).launch {
            // Handle the exception
        }
    }
}

@Composable
fun ExceptionScreen() {
    Text("An error occurred. Please try again.")
}


@Composable
fun IapComponent() {
    val scope = remember { CoroutineScope(coroutineContext) }
    val iapManager = remember { IapManager() }

    Button(onClick = {
        scope.launch {
            val products = iapManager.getProducts()
            // Handle products
        }
    }) {
        Text("Fetch Products")
    }
}


@Composable
fun KeepAwake() {
    val context = LocalContext.current
    val scope = CoroutineScope(Dispatchers.Main)

    val notificationCenter = NSNotificationCenter.defaultCenter

    val keepAwakeKey = "keepAwake"

    val userDefaults = NSUserDefaults.standardUserDefaults
    userDefaults.setBool(true, forKey = keepAwakeKey)

    val observer = notificationCenter.addObserverForName(
        UIApplicationWillResignActiveNotification,
        null
    ) { _: NSNotification ->
        userDefaults.setBool(false, forKey = keepAwakeKey)
    }

    notificationCenter.addObserverForName(
        UIApplicationDidBecomeActiveNotification,
        null
    ) { _: NSNotification ->
        userDefaults.setBool(true, forKey = keepAwakeKey)
    }

    scope.launch {
        // Logic to keep the device awake can be implemented here
    }

    // Clean up observer when no longer needed
    onDispose {
        notificationCenter.removeObserver(observer)
    }
}


class Localization {
    private val currentLanguage: String = "en" // Example language

    fun getString(key: String): String {
        val localizedStrings = mapOf(
            "hello" to mapOf("en" to "Hello", "es" to "Hola"),
            "goodbye" to mapOf("en" to "Goodbye", "es" to "Adiós")
        )
        return localizedStrings[key]?.get(currentLanguage) ?: key
    }
}

@Composable
fun Greeting() {
    val localization = Localization()
    Text(text = localization.getString("hello"))
}

@Composable
fun Farewell() {
    val localization = Localization()
    Text(text = localization.getString("goodbye"))
}

fun fetchLocalization(): Flow<String> = flow {
    emit(Localization().getString("hello"))
}.flowOn(Dispatchers.IO)


@Composable
fun MaterialColorExample() {
    val color = Color(0xFF6200EE) // Example color from Material Design
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    )
}

@Preview
@Composable
fun PreviewMaterialColorExample() {
    MaterialTheme {
        MaterialColorExample()
    }
}


@Composable
fun MyComponent() {
    Surface {
        Text("Hello, Kotlin Multiplatform!")
    }
}

@Preview
@Composable
fun PreviewMyComponent() {
    MyComponent()
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailsScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
        }
    ) {
        Button(onClick = { navController.navigate("details") }) {
            Text("Go to Details")
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Details") })
        }
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Home")
        }
    }
}


@Composable
fun SuperGrid(items: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items.size) { index ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = items[index])
                }
            }
        }
    }
}


@Composable
fun VectorIconsExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
        Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
    }
}


@Composable
fun MyComponent() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hello, Kotlin Multiplatform!")
            Button(onClick = { /* Handle click */ }) {
                Text(text = "Click Me")
            }
        }
    }
}


@Composable
fun NativeChartExperiment() {
    val coroutineScope = rememberCoroutineScope()
    var data by remember { mutableStateOf(emptyList<Float>()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // Fetch or generate data here
            data = listOf(1f, 2f, 3f, 4f, 5f)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Native Chart Experiment") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            ChartView(data)
        }
    }
}

@Composable
fun ChartView(data: List<Float>) {
    // Implement your chart drawing logic here
    Box(modifier = Modifier.fillMaxSize()) {
        // Example placeholder for chart
        Text(text = "Chart Data: ${data.joinToString(", ")}")
    }
}


data class Library(val name: String, val link: String)

val libraries = listOf(
    Library("WoWs Info", "https://github.com/HenryQuan/WoWs-Info")
)

@Composable
fun License() {
    WoWsInfo {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(libraries) { item ->
                ListItem(
                    modifier = Modifier.fillMaxWidth(),
                    headlineContent = { Text(item.name) },
                    supportingContent = { Text(item.link) },
                    modifier = Modifier.clickable { SimpleViewHandler.openURL(item.link) }
                )
            }
        }
    }
}

@Composable
fun WoWsInfo(content: @Composable () -> Unit) {
    Surface {
        content()
    }
}

object SimpleViewHandler {
    fun openURL(url: String) {
        // Implementation to open URL
    }
}