
data class PriceLabelProps(val item: Item)

data class Item(val price_credit: Int?, val price_gold: Int?)

@Composable
fun PriceLabel(props: PriceLabelProps) {
    val item = props.item
    val price: Int?
    val colour: Color

    if (item.price_gold == null || item.price_gold == 0) {
        price = item.price_credit
        colour = Color.Gray
    } else {
        price = item.price_gold
        colour = Color(1.0f, 0.647f, 0.0f) // Orange
    }

    Text(text = price?.toString() ?: "N/A", color = colour)
}

    text = price,
    color = Color(color),
    textAlign = TextAlign.Center
)