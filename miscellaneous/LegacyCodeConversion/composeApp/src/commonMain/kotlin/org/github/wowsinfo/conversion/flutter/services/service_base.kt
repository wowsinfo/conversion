
typealias ModelCreator<T> = (Map<String, Any>) -> T?

@Serializable
data class ServiceResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    val hasError: Boolean get() = errorMessage != null
    val isNotEmpty: Boolean get() = data != null

    companion object {
        fun <T> copyWith(other: ServiceResult<T>): ServiceResult<T> {
            return ServiceResult(data = other.data, errorMessage = other.errorMessage)
        }
    }
}

suspend fun <T> fetchData(url: String, modelCreator: ModelCreator<T>): ServiceResult<T> {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    return withContext(Dispatchers.IO) {
        try {
            val response: String = client.get(url)
            val jsonData: Map<String, Any> = Json.decodeFromString(response)
            val data = modelCreator(jsonData)
            ServiceResult(data = data)
        } catch (e: Exception) {
            ServiceResult<T>(errorMessage = e.message)
        } finally {
            client.close()
        }
    }
}

    val data: T? = null,
    val errorMessage: String? = null
) {
    override fun toString(): String {
        return "ServiceResult{data: $data, errorMessage: $errorMessage}"
    }
}


abstract class BaseService {
    abstract val baseUrl: String

    private val timeout = 30L
    private val client = OkHttpClient.Builder()
        .callTimeout(timeout, TimeUnit.SECONDS)
        .build()

    protected suspend fun getObject(url: String): ServiceResult<Any?> {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url(url)
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val json = body?.let { Json.decodeFromString<Any?>(it) }
                    if (json != null) {
                        ServiceResult(data = json)
                    } else {
                        ServiceResult(errorMessage = "JSON decoding error")
                    }
                } else {
                    val errorCode = response.code
                    ServiceResult(errorMessage = "HTTP Error: $errorCode")
                }
            } catch (e: Exception) {
                ServiceResult(errorMessage = e.toString())
            }
        }
    }
}

data class ServiceResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
)


class ServiceResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    val hasError: Boolean get() = errorMessage != null
    val isNotEmpty: Boolean get() = data != null
}

typealias ModelCreator<T> = (Map<String, Any>) -> T

class YourClass {
    private val logger = Logger()

    fun <T> decodeObject(
        json: ServiceResult<Any?>,
        creator: ModelCreator<T>
    ): ServiceResult<T> {
        if (json.hasError) return ServiceResult(json.errorMessage)

        if (json.isNotEmpty) {
            val jsonData = json.data
            if (jsonData is Map<*, *>) {
                val data = jsonData["data"]
                if (data is Map<*, *>) {
                    val result = creator(data as Map<String, Any>)
                    logger.info("decoded json successfully as $result")
                    return ServiceResult(data = result)
                } else {
                    logger.severe("data is not a Map<String, dynamic>")
                }
            }
        } else {
            logger.severe("json.data is null, API failure")
        }

        logger.severe("failed to decode $T", json)
        return ServiceResult(errorMessage = "Decoding failure in $T")
    }
}

class Logger {
    fun info(message: String) {
        println("INFO: $message")
    }

    fun severe(message: String, json: ServiceResult<Any?>? = null) {
        println("SEVERE: $message ${json?.errorMessage}")
    }
}


data class ServiceResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    val hasError: Boolean get() = errorMessage != null
    val isNotEmpty: Boolean get() = data != null
}

typealias ModelCreator<T> = (JsonElement) -> T

fun <T> decodeList(
    json: ServiceResult<JsonElement>,
    creator: ModelCreator<T>
): ServiceResult<List<T>> {
    if (json.hasError) return ServiceResult(errorMessage = json.errorMessage)

    if (json.isNotEmpty) {
        val jsonData = json.data
        if (jsonData is JsonObject) {
            val data = jsonData["data"]
            if (data is JsonArray) {
                val list = data.map { creator(it) }
                return ServiceResult(data = list)
            } else {
                println("data is not a List")
            }
        }
    } else {
        println("json.data is null, API failure")
    }

    println("failed to decode $T")
    return ServiceResult(errorMessage = "Decoding failure in $T")
}


@Composable
fun MyScreen(viewModel: MyViewModel) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is MyState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is MyState.Success -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Data Loaded: ${state.data}")
                        Button(onClick = { viewModel.onButtonClick() }) {
                            Text("Click Me")
                        }
                    }
                }
                is MyState.Error -> {
                    Text(text = "Error: ${state.message}", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

class MyViewModel(private val coroutineScope: CoroutineScope) {
    var state: MyState = MyState.Loading
        private set

    init {
        loadData()
    }

    private fun loadData() {
        coroutineScope.launch {
            try {
                // Simulate network call
                state = MyState.Success("Sample Data")
            } catch (e: Exception) {
                state = MyState.Error("Failed to load data")
            }
        }
    }

    fun onButtonClick() {
        // Handle button click
    }
}

sealed class MyState {
    object Loading : MyState()
    data class Success(val data: String) : MyState()
    data class Error(val message: String) : MyState()
}