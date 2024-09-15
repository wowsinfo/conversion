@Composable
fun Filter(
    input: String,
    onInputChange: (String) -> Unit,
    premiumChecked: Boolean,
    onPremiumToggle: () -> Unit,
    tierList: List<String>,
    onSelectTier: (String) -> Unit,
    nationList: List<String>,
    onSelectNation: (String) -> Unit,
    typeList: List<String>,
    onSelectType: (String) -> Unit,
    resetFunc: () -> Unit,
    applyFunc: () -> Unit
) {
    val filter = true
    val tier = "Tier"
    val nation = "Nation"
    val type = "Type"

    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        TextField(
            value = input,
            onValueChange = onInputChange,
            placeholder = "Placeholder",
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Premium")
            Switch(
                checked = premiumChecked,
                onCheckedChange = onPremiumToggle
            )
        }
        Collapsible(
            title = tier,
            expanded = true,
            onExpand = { onSelectTier("Tier 1") },
            content = {
                for (item in tierList) {
                    Button(
                        onClick = { onSelectTier(item) },
                        text = item
                    )
                }
            }
        )
        Collapsible(
            title = nation,
            expanded = true,
            onExpand = { onSelectNation("Nation 1") },
            content = {
                for (item in nationList) {
                    Button(
                        onClick = { onSelectNation(item) },
                        text = item
                    )
                }
            }
        )
        Collapsible(
            title = type,
            expanded = true,
            onExpand = { onSelectType("Type 1") },
            content = {
                for (item in typeList) {
                    Button(
                        onClick = { onSelectType(item) },
                        text = item
                    )
                }
            }
        )
        Button(
            onClick = resetFunc,
            text = "Reset"
        )
        Button(
            onClick = applyFunc,
            text = "Apply"
        )
    }
}