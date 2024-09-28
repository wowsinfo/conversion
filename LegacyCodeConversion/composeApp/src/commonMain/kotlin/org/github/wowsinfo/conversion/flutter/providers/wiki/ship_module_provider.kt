
class ShipModuleProvider(
    private val modules: List<ShipModules>,
    private val selection: ShipModuleSelection
) : ViewModel() {

    private val _selectedModules = MutableStateFlow(selection)
    val selectedModules: StateFlow<ShipModuleSelection> get() = _selectedModules

    fun updateSelection(newSelection: ShipModuleSelection) {
        _selectedModules.value = newSelection
        notifyChange()
    }

    private fun notifyChange() {
        // Notify listeners about the change
    }
}


class ShipModuleSelection {
    fun isSelected(type: ShipModuleType, index: Int): Boolean {
        // Implement selection logic here
        return false
    }
}

enum class ShipModuleType

class ShipModuleHandler(val modules: ShipModuleMap) {
    var selection: ShipModuleSelection = ShipModuleSelection()

    fun isSelected(type: ShipModuleType, index: Int): Boolean {
        return selection.isSelected(type, index)
    }
}

    selection.updateSelection(type, index)
    selection = selection
    notifyListeners()
}


@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Greeting("World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Hello, $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}