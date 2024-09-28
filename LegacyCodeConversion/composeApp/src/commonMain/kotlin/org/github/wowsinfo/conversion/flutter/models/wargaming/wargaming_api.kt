    val field: String? = null,
    val message: String? = null,
    val code: Int? = null,
    val value: String? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): WargamingAPIError {
            return WargamingAPIError(
                field = json["field"] as? String,
                message = json["message"] as? String,
                code = json["code"] as? Int,
                value = json["value"] as? String
            )
        }
    }
}

    val field: String?,
    val message: String?,
    val code: Int?,
    val value: String?
) {
    override fun toString(): String {
        return "WargamingAPIError{field: $field, message: $message, code: $code, value: $value}"
    }
}

    val count: Int? = null,
    val pageTotal: Int? = null,
    val total: Int? = null,
    val limit: Int? = 100,
    val page: Int? = null
) {
    val requirePagination: Boolean
        get() = pageTotal != null && pageTotal!! > 1

    val hasMorePages: Boolean
        get() = pageTotal != null && page != null && page!! < pageTotal!!
}

    var count: Int? = null,
    var pageTotal: Int? = null,
    var total: Int? = null,
    var limit: Int? = null,
    var page: Int? = null
) {
    companion object {
        fun fromJson(json: Map<String, Any?>): WargamingAPIMeta {
            return WargamingAPIMeta(
                count = json["count"] as? Int,
                pageTotal = json["page_total"] as? Int,
                total = json["total"] as? Int,
                limit = json["limit"] as? Int,
                page = json["page"] as? Int
            )
        }
    }
}

    val count: Int,
    val pageTotal: Int,
    val total: Int,
    val limit: Int,
    val page: Int
) {
    override fun toString(): String {
        return "WargamingAPIMeta{count: $count, pageTotal: $pageTotal, total: $total, limit: $limit, page: $page}"
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "You have clicked the button $count times")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            count++
        }) {
            Text("Click me")
        }
    }
}