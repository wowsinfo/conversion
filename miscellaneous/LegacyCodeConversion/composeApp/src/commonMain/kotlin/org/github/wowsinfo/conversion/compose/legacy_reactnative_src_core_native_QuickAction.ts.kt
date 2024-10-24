
class QuickActionManager(private val navController: NavController) {

    companion object {
        const val ACTION_SEARCH = "com.example.app.ACTION_SEARCH"
        const val ACTION_WARSHIPS = "com.example.app.ACTION_WARSHIPS"
        const val ACTION_ACCOUNT = "com.example.app.ACTION_ACCOUNT"

        fun addMainAccount(name: String) {
            // Add main account to QuickActionManager
            QuickAction.addMainAccount(name)
        }

        fun performPendingShortcut() {
            QuickAction.performPendingShortcut()
        }
    }

    @Composable
    fun DisplayQuickAction(type: String) {
        Text(text = "Received Quick Action: $type")
    }

    fun handleQuickActions(intent: Intent?) {
        intent?.let { intent ->
            when (intent.action) {
                ACTION_SEARCH -> navController.navigate("SearchScreen") {}
                ACTION_WARSHIPS -> navController.navigate("WarshipScreen") {}
                ACTION_ACCOUNT -> navController.navigate("AccountScreen") {}
                else -> Log.d("QuickAction", "Unknown action")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewDisplayQuickAction() {
        DisplayQuickAction("Search")
    }
}