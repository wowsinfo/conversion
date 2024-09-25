
class SafeStorage {
    companion object {
        private val userDefaults = NSUserDefaults.standardUserDefaults

        suspend fun get(key: String, value: Any?): Any? {
            return withContext(Dispatchers.IO) {
                val data = userDefaults.stringForKey(key)
                if (data != null) {
                    // Return parsed value
                    data.toString() // Assuming the value is a String, adapt as necessary
                } else {
                    // Save the default value
                    set(key, value)
                    value
                }
            }
        }

        suspend fun set(key: String, value: Any?) {
            withContext(Dispatchers.IO) {
                userDefaults.setObject(value, forKey = key)
            }
        }
    }
}


object Storage {
    suspend fun set(key: String, value: Any) {
        withContext(Dispatchers.Main) {
            val jsonString = Json.encodeToString(value)
            NSUserDefaults.standardUserDefaults.setObject(jsonString, key)
        }
    }
}


object Storage {
    suspend fun clear() {
        withContext(Dispatchers.Main) {
            NSUserDefaults.standardUserDefaults().removePersistentDomainForName(NSUserDefaults.standardUserDefaults().persistentDomainForName)
        }
    }
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