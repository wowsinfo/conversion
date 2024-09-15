
class Achievement : Component() {
    private val achievement by lazy { AppGlobalData.get(SAVED.achievement) }
    private val sorted = achievement.entries.sortedBy { (key, value) ->
        if (value.hidden == value.hidden) key else value.hidden
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLastLocation("Achievement")
        Log.d(TAG, "WIKI - Achievement")
        sorted.forEachIndexed { index, entry ->
            sorted[index] = entry.value
        }
        Log.d(TAG, sorted.toString())
    }

    @Composable
    override fun Content() {
        WoWsInfo {
            FlatGrid(
                itemDimension = 80.dp,
                data = sorted,
                onItemPressed = { item -> SafeAction("BasicDetail", intent = Intent().apply { putExtra("item", item) }) },
                showsVerticalScrollIndicator = false
            )
        }
    }

    companion object {
        const val TAG = "Achievement"
    }
}