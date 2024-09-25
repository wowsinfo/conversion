
@Composable
fun FloatingButton(navController: NavController) {
    val (menu, setMenu) = remember { mutableStateOf(false) }
    val (icon, setIcon) = remember { mutableStateOf(painterResource(id = R.drawable.ship)) }

    LaunchedEffect(Unit) {
        val hasMenu = navController.graph.findNode("Menu") != null
        setMenu(hasMenu)
        setIcon(if (hasMenu) painterResource(id = R.drawable.home) else painterResource(id = R.drawable.ship))
    }

    Box {
        FloatingActionButton(onClick = { /* Handle click */ }) {
            Icon(painter = icon, contentDescription = null)
        }
    }
}

    if (menu) {
        navController.popBackStack("Menu", false)
    } else if (navController.currentDestination?.route != "Menu") {
        navController.navigate("Menu")
    }
}

fun MyComponent(navigate: () -> Unit, icon: ImageVector) {
    val containerModifier = Modifier
        .absolutePadding(right = 16.dp, bottom = 16.dp)
        .align(Alignment.BottomEnd)

    Box(modifier = containerModifier) {
        FloatingActionButton(onClick = navigate) {
            Icon(imageVector = icon, contentDescription = null)
        }
    }
}


@Composable
fun MyComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello, Kotlin Multiplatform!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text(text = "Click Me")
        }
    }
}