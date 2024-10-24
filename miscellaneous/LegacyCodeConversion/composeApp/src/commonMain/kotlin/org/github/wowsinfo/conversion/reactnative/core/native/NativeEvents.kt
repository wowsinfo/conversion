
class NativeEvents {
    private val _eventFlow = MutableSharedFlow<String>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        // Simulate receiving a native event
        receiveNativeEvent()
    }

    private fun receiveNativeEvent() {
        // This function would be called from native code to emit events
        // For demonstration, we emit a dummy event
        emitEvent("dummy")
    }

    private fun emitEvent(type: String) {
        _eventFlow.tryEmit(type)
        dataLoading()
        println("dummy event received from native: $type")
    }

    private fun dataLoading() {
        // Your data loading logic here
    }
}

    // Implementation goes here
}