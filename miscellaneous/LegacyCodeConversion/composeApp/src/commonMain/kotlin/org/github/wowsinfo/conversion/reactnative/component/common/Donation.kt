
val itemSkus = listOf(
    "com.yihengquan.wowsinfo.support1",
    "com.yihengquan.wowsinfo.support3",
    "com.yihengquan.wowsinfo.support5",
    "com.yihengquan.wowsinfo.support10"
)

@Composable
fun Donation() {
    val products = remember { mutableStateOf<List<SkuDetails>?>(null) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val billingClient = BillingClient.newBuilder(context).enablePendingPurchases().build()
            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        val params = SkuDetailsParams.newBuilder()
                            .setSkusList(itemSkus)
                            .setType(BillingClient.SkuType.INAPP)
                            .build()
                        billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
                            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                                products.value = skuDetailsList
                            }
                        }
                    }
                }

                override fun onBillingServiceDisconnected() {
                    // Handle disconnection
                }
            })
        }
    }

    Box {
        products.value?.let { productList ->
            productList.forEach { product ->
                Text(text = product.sku)
            }
        }
    }
}

    if (AppGlobalData.githubVersion == null) {
        try {
            val products = RNIap.getProducts(itemSkus)
            RNIap.consumeAllItems()
            val sortedProducts = products.sortedBy { it.price }
            setState { this.products = sortedProducts }
        } catch (e: Exception) {
            println(e) 
        }
    }
}

fun SupportScreen(products: List<Product>?, appGlobalData: AppGlobalData) {
    val support = remember { 
        if (!appGlobalData.githubVersion) {
            listOf(
                SupportItem("GitHub", "https://github.com/HenryQuan/WoWs-Info-Origin", Color.Black)
            )
        } else {
            listOf(
                SupportItem(lang.support_patreon, APP.Patreon, Color(0xFFFFA500)),
                SupportItem(lang.support_paypal, APP.PayPal, Color(0xFF0000FF)),
                SupportItem(lang.support_wechat, APP.WeChat, Color(0xFF008000))
            )
        }
    }

    Column {
        if (appGlobalData.githubVersion || products == null) {
            // Do nothing
        } else {
            LazyRow {
                items(products) { item ->
                    Button(
                        onClick = { supportWoWsInfo(item) },
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Text(item.localizedPrice)
                    }
                }
            }
        }

        support.forEach { item ->
            ListItem(
                modifier = Modifier.clickable { SimpleViewHandler.openURL(item.url) },
                text = { Text(item.title) },
                secondaryText = { Text(item.description) }
            )
        }
    }
}

data class SupportItem(val title: String, val url: String, val color: Color)

    try {
        val purchase = RNIap.buyProduct(item.productId)
        RNIap.consumePurchase(purchase.purchaseToken)
        setState { 
            receipt = purchase.transactionReceipt 
        }
    } catch (err: Exception) {
        println("${err.code} ${err.message}")
        val subscription = RNIap.addAdditionalSuccessPurchaseListenerIOS { purchase ->
            setState { 
                receipt = purchase.transactionReceipt 
            }
            goToNext()
            subscription.remove()
        }
    }
}


@Composable
fun DonationScreen() {
    var amount by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Make a Donation", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Enter Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Handle donation logic here
            isSuccess = true
        }) {
            Text("Donate")
        }

        if (isSuccess) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Thank you for your donation!", style = MaterialTheme.typography.body1)
        }
    }
}