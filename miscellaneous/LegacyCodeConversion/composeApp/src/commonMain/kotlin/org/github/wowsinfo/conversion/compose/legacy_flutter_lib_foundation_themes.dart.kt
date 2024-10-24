@Composable
fun MyView() {
    val (count, setCount) = remember { mutableStateOf(0) }
    
    Column {
        Text(text = "Count: $count")
        Button(onClick = { setCount(count + 1) }) {
            Text("Increment")
        }
    }
}