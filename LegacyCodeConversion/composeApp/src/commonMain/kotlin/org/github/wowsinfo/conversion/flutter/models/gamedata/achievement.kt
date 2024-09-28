
@Serializable
data class Achievement(
    val icon: String,
    val name: String,
    val description: String,
    val type: String,
    val id: String,
    val constants: Map<String, Any>,
    val added: Long? = null
)

    val icon: String,
    val name: String,
    val description: String,
    val type: List<String>,
    val id: Int,
    val added: Int? = null,
    val constants: Map<String, Any>
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Achievement {
            return Achievement(
                icon = json["icon"] as String,
                name = json["name"] as String,
                description = json["description"] as String,
                type = (json["type"] as List<*>).map { it as String },
                id = json["id"] as Int,
                constants = json["constants"] as Map<String, Any>,
                added = json["added"] as? Int
            )
        }
    }

    override fun toString(): String {
        return "Achievement{icon: $icon, name: $name, description: $description, type: $type, id: $id, constants: $constants}"
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
            coroutineScope.launch {
                count++
            }) {
            Text("Click me")
        }
    }
}