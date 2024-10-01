
class PriceLabel extends StatelessWidget {
  final Map<String, dynamic> item;

  const PriceLabel({Key? key, required this.item}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final priceCredit = item['price_credit'] ?? 0;
    final priceGold = item['price_gold'] ?? 0;

    var price = priceGold;
    var color = Colors.orange;

    if (priceGold == 0) {
      price = priceCredit;
      color = Colors.grey;
    }

    return Text(
      price.toString(),
      style: TextStyle(color: color),
    );
  }
}


class PriceText extends StatelessWidget {
  final String price;
  final Color colour;

  PriceText({required this.price, required this.colour});

  @override
  Widget build(BuildContext context) {
    return Text(
      price,
      style: TextStyle(color: colour, textAlign: TextAlign.center),
    );
  }
}