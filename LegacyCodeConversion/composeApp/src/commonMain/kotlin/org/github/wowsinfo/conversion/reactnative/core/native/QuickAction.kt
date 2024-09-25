
class QuickAction {
    private val quickActionFlow = MutableSharedFlow<String>()

    init {
        runBlocking {
            launch {
                quickActionFlow.asSharedFlow().collect { type ->
                    println("quick action - $type")
                    when (type) {
                        "search" -> SafeAction("Search")
                        "warships" -> SafeAction("Warship")
                        "account" -> SafeAction("Statistics")
                        else -> println("Unknown action - $type")
                    }
                }
            }
        }
    }

    fun emitQuickAction(type: String) {
        runBlocking {
            quickActionFlow.emit(type)
        }
    }
}

    fun addMainAccount(name: String) {
        // Implementation for adding a main account
    }
}

fun addMainAccount(name: String) {
    AccountManager.addMainAccount(name)
}

    manager.performPendingShortcut()
}


@Composable
fun QuickAction(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Quick Action")
    }
}