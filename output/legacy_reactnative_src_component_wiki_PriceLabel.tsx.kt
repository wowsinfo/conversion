@Composable
fun PriceLabel(item: Any) {
  val priceCredit = item.price_credit
  val priceGold = item.price_gold

  var price = priceGold
  var colour = "orange"
  if (priceGold == null || priceGold == 0) {
    price = priceCredit
    colour = "grey"
  }

  Text(
    text = "$price",
    color = Color(color),
    textAlign = TextAlign.Center,
  )
}