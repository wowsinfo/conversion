@Composable
fun ProVersionScreen() {
    val (loading, error, price, discountPrice) = remember { mutableTriple(true, false, "", "") }
    LaunchedEffect(Unit) {
        // Setup listeners
        purchaseUpdatedSubscription = purchaseUpdatedListener { purchase ->
            Log.d("purchaseUpdatedListener", purchase.toString())
            val receipt = purchase.transactionReceipt
            if (receipt != null) {
                finishTransaction(purchase, false)
                setProVersion(true)
                // Go back automatically
                Actions.pop()
                Toast.makeText(
                    context,
                    "Thank you for your support",
                    Toast.LENGTH_LONG
                ).show()
                delay(500)
                Actions.refresh()
            }
        }

        purchaseErrorSubscription = purchaseErrorListener { error ->
            Log.w("purchaseErrorListener", error.toString())
        }

        // Init connection
        val allGood = initConnection()
        Log.i(allGood.toString())
        error = !allGood

        if (allGood) {
            Log.i("This device can make purchases")
            val items = getSubscriptions(arrayOf(sku))
            Log.d(items.toString())
            if (items.size == 1) {
                // There should only be one item which is wows info pro
                val pro = items[0]
                price.set(pro.localizedPrice)
                discountPrice.set(pro.introductoryPrice)
                loading.set(false)
            }
        }
    }

    WoWsInfoScreenContent(
        title = "Pro version",
        content = {
            listOf(
                ListItem(title = "Respect the creator", subtitle = "Support the development"),
                ListItem(
                    title = "More statistics",
                    subtitle = "See more data from your account"
                ),
                ListItem(
                    title = "Support the development",
                    subtitle = "All donations will be used to support the project development"
                )
            ).forEach { listItem ->
                ListItemCard(item = listItem)
            }
        },
        actions = {
            if (loading) {
                CircularProgressIndicator()
            } else if (error) {
                // Error view
            } else {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "50% off until $re", style = MaterialTheme.typography.subtitle2)
                    Button(onClick = { buy() }) {
                        Text(text = "$price / Per year")
                    }
                    OutlinedButton(onClick = { restore() }) {
                        Text(text = "Restore pro version")
                    }
                }
            }
        }
    )
}

@Composable
fun WoWsInfoScreenContent(
    title: String,
    content: @Composable () -> Unit,
    actions: @Composable () -> Unit,
) {
    val theme = MaterialTheme

    WoWsInfoScreenBackground() {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(theme.colors.orange500)
                ),
                modifier = Modifier.padding(16.dp)
            )
            content()
            Spacer(modifier = Modifier.weight(1f))
            actions()
        }
    }

    LaunchedEffect(Unit) {
        purchaseUpdatedSubscription?.remove()
        purchaseErrorSubscription?.remove()
    }
}

private fun buy() {
    try {
        requestSubscription(sku, false)
    } catch (e: Exception) {
        Log.w(e.message ?: "Unknown error", e)
    }
}

private fun restore() {
    validateProVersion(true)
}