
@Composable
fun App() {
    MaterialTheme {
        Surface {
            // Your app content goes here
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    App()
}

fun main() = singleWindowApplication {
    App()
}