    companion object {
        val instance: AppRepository by lazy { AppRepository() }
    }

    private lateinit var store: StoreInterface

    fun inject(store: StoreInterface) {
        this.store = store
    }
}


@Composable
fun CachePR() {
    val coroutineScope = remember { CoroutineScope(Dispatchers.IO) }

    coroutineScope.launch {
        // TODO: maybe cache PR once a while in case the app is not updated
    }
}