
class SharedStore : StoreInterface {
    private val prefs = NSUserDefaults.standardUserDefaults()

    override suspend fun load(): Boolean {
        return withContext(Dispatchers.IO) {
            // Load preferences or perform any initialization if needed
            true
        }
    }
}

    return preferences.remove(key)
}

    throw AssertionError("""
      Calling clear() on SharedStore will erase all data.
      This is extremely dangerous.
      Only uncomment this if you know what you are doing.
      """.trimIndent())
    
    return prefs.clear()
}

    fun get(key: String): Any? {
        return prefs.all[key]
    }
}


class Preferences(private val prefs: NSUserDefaults) {

    suspend fun set(key: String, value: Any): Boolean {
        return withContext(Dispatchers.IO) {
            when (value) {
                is String -> {
                    prefs.setObject(key, value)
                    true
                }
                is Boolean -> {
                    prefs.setBool(key, value)
                    true
                }
                is Int -> {
                    prefs.setInteger(key, value)
                    true
                }
                is Double -> {
                    prefs.setDouble(key, value)
                    true
                }
                is List<*> -> {
                    if (value.all { it is String }) {
                        prefs.setObject(key, value as List<String>)
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
    }
}

    fun has(key: String): Boolean {
        return prefs.containsKey(key)
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
            Text("Click Me")
        }
    }
}