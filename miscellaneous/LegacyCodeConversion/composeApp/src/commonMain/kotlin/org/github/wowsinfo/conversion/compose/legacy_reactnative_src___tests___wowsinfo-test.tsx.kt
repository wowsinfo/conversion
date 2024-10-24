
class WowsInfoRenderTest {
    private val rule = createComposeRule<ComposeContentTestRule>()

    @Composable
    fun WowsInfo() {
        // Add the necessary UI components/screens here
    }

    @Test
    fun wowsInfoRendersCorrectly() {
        rule.setContent { WowsInfo() }
        // Assert the rendered content as needed
    }
}