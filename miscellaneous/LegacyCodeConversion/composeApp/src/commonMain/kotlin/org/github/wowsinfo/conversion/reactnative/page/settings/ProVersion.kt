
@Composable
fun ProVersionScreen() {
    val coroutineScope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }
    var price by remember { mutableStateOf("") }
    var discountPrice by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        initConnection()
        getSubscriptions()
    }

    if (loading) {
        CircularProgressIndicator()
    } else if (error) {
        Text("Error loading data")
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Pro Version", style = MaterialTheme.typography.h5)
            Text("Price: $price")
            Text("Discount Price: $discountPrice")
            Button(onClick = {
                coroutineScope.launch {
                    requestSubscription("wowsinfo.proversion")
                }
            }) {
                Text("Purchase")
            }
        }
    }
}

suspend fun initConnection() {
    // Initialize connection logic
}

suspend fun getSubscriptions() {
    // Fetch subscriptions logic
}

suspend fun requestSubscription(sku: String) {
    // Request subscription logic
}

fun purchaseUpdatedListener() {
    // Handle purchase updates
}

fun purchaseErrorListener() {
    // Handle purchase errors
}

    private lateinit var purchaseUpdateSubscription: Job
    private lateinit var purchaseErrorSubscription: Job

    override fun onStart() {
        super.onStart()
        purchaseUpdateSubscription = purchaseUpdatedListener { purchase ->
            val receipt = purchase.transactionReceipt
            if (receipt != null) {
                finishTransaction(purchase, false)
                setProVersion(true)
                Actions.pop()
                Alert.alert(lang.pro_title, lang.iap_thx_for_support)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(500)
                    Actions.refresh()
                }
            }
        }

        purchaseErrorSubscription = purchaseErrorListener { error ->
            println("purchaseErrorListener $error")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val allgood = initConnection()
            println(allgood)
            withContext(Dispatchers.Main) {
                setState {
                    error = !allgood
                }
            }

            if (allgood) {
                println("This device can make purchases")
                val items = getSubscriptions(listOf(sku))
                println(items)
                if (items.size == 1) {
                    val pro = items[0]
                    withContext(Dispatchers.Main) {
                        setState {
                            price = pro.localizedPrice
                            discountPrice = pro.introductoryPrice
                            loading = false
                        }
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        purchaseUpdateSubscription.cancel()
        purchaseErrorSubscription.cancel()
    }
}

    purchaseUpdateSubscription?.let {
        it.remove()
        purchaseUpdateSubscription = null
    }
    purchaseErrorSubscription?.let {
        it.remove()
        purchaseErrorSubscription = null
    }
}

fun MyComponent() {
    val titleStyle = styles.titleStyle
    val viewStyle = styles.viewStyle

    WoWsInfo(hideAds = true) {
        ScrollableColumn(modifier = viewStyle) {
            Text(text = lang.pro_title, style = titleStyle)
            ListItem(
                headlineContent = { Text(text = lang.pro_rs) },
                supportingContent = { Text(text = lang.pro_rs_subtitle) }
            )
            ListItem(
                headlineContent = { Text(text = lang.pro_more_stats) },
                supportingContent = { Text(text = lang.pro_more_stats_subtitle) }
            )
            ListItem(
                headlineContent = { Text(text = lang.pro_support_development) },
                supportingContent = { Text(text = lang.pro_support_development_subtitle) }
            )
        }
        renderPurchaseView()
        renderPolicies()
    }
}

fun RenderPolicies() {
    val horizontalModifier = Modifier.fillMaxWidth().padding(16.dp)
    Row(modifier = horizontalModifier) {
        Button(onClick = {
            SimpleViewHandler.openURL("https://github.com/HenryQuan/WoWs-Info-Future/blob/legacy_version/Privacy%20Policy.md")
        }) {
            Text("Privacy policy")
        }
        Button(onClick = {
            SimpleViewHandler.openURL("https://github.com/HenryQuan/WoWs-Info-Future/blob/legacy_version/Term%20of%20Use.md")
        }) {
            Text("Term of use")
        }
    }
}

fun PurchaseView(viewModel: PurchaseViewModel) {
    val state = viewModel.state.collectAsState()
    val loading = state.value.loading
    val error = state.value.error
    val price = state.value.price

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LoadingIndicator()
        }
    } else if (error != null) {
        // Handle error state if needed
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "50% off until renewal", style = MaterialTheme.typography.body1)
            Button(
                onClick = { viewModel.buy() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "$price / per year")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { viewModel.restore() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Restore Pro")
            }
        }
    }
}

    try {
        requestSubscription(sku, false)
    } catch (err: Exception) {
        println("${err::class.simpleName} ${err.message}")
    }
}

    validateProVersion(true)
}


@Composable
fun MyView() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Your content goes here
    }
}


@Composable
fun HorizontalLayout() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Add your content here
    }
}

    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    color = Color(0xFFFFA500), // Assuming Colors.orange500 is equivalent to this hex value
    padding = PaddingValues(16.dp, 32.dp, 16.dp, 16.dp)
)

fun ButtonView() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Gray)
            .clickable { /* Handle click */ }
    ) {
        Text("Button", color = Color.White)
    }
}


@Composable
fun RestoreButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        // Button content goes here
    }
}

    text = "Discount",
    textAlign = TextAlign.Center,
    modifier = Modifier
        .padding(bottom = 4.dp)
)

    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .wrapContentSize(Alignment.Center)
) {
    // Your loader content goes here
}


@Composable
fun ProVersion() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Pro Version", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle button click */ }) {
            Text("Get Pro Version")
        }
    }
}