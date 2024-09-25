
fun safeAction(navController: NavController, screen: String, obj: Any? = null, max: Int = 0) {
    val currentBackStackEntry = navController.currentBackStackEntry
    val routeCount = currentBackStackEntry?.destination?.route?.let { route ->
        navController.backQueue.count { it.destination.route == route }
    } ?: 0

    if (routeCount > max) {
        println("$screen rejected")
    } else {
        navController.navigate(screen) {
            launchSingleTop = true
        }
    }
}

    navController.navigate(screen) {
        launchSingleTop = true
        restoreState = true
    }
    println("$screen pushed")
}


@Composable
fun MyComponent() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $count")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                count++
            }
        }) {
            Text("Increment")
        }
    }
}