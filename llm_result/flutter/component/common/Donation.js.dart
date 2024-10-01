
const List<String> itemSkus = [
  'com.yihengquan.wowsinfo.support1',
  'com.yihengquan.wowsinfo.support3',
  'com.yihengquan.wowsinfo.support5',
  'com.yihengquan.wowsinfo.support10',
];

class Donation extends StatefulWidget {
  @override
  _DonationState createState() => _DonationState();
}

class _DonationState extends State<Donation> {
  List<ProductDetails>? products;

  @override
  void initState() {
    super.initState();
    _loadProducts();
  }

  Future<void> _loadProducts() async {
    final bool available = await InAppPurchase.instance.isAvailable();
    if (!available) {
      // Handle the case when the store is not available
      return;
    }

    ProductDetailsResponse response = await InAppPurchase.instance.queryProductDetails(itemSkus.toSet());
    if (response.notFoundIDs.isNotEmpty) {
      // Handle the case when some products are not found
    }

    setState(() {
      products = response.productDetails;
    });
  }

  void _buyProduct(ProductDetails productDetails) {
    final PurchaseParam purchaseParam = PurchaseParam(productDetails: productDetails);
    InAppPurchase.instance.buyConsumable(purchaseParam: purchaseParam);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Donation'),
      ),
      body: products == null
          ? Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: products!.length,
              itemBuilder: (context, index) {
                final product = products![index];
                return ListTile(
                  title: Text(product.title),
                  subtitle: Text(product.description),
                  trailing: Text(product.price),
                  onTap: () => _buyProduct(product),
                );
              },
            ),
    );
  }
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<ProductDetails> products = [];
  final List<String> itemSkus = ['your_product_id_1', 'your_product_id_2']; // Add your product IDs here

  @override
  void initState() {
    super.initState();
    _getProducts();
  }

  Future<void> _getProducts() async {
    if (AppGlobalData.githubVersion == null) {
      try {
        final ProductDetailsResponse response = await InAppPurchase.instance.queryProductDetails(itemSkus.toSet());
        if (response.notFoundIDs.isEmpty) {
          products = response.productDetails;
          products.sort((a, b) => a.price.compareTo(b.price));
          setState(() {});
        }
      } catch (err) {
        print(err); // Handle error
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('In-App Purchase Example')),
        body: ListView.builder(
          itemCount: products.length,
          itemBuilder: (context, index) {
            return ListTile(
              title: Text(products[index].title),
              subtitle: Text(products[index].description),
              trailing: Text(products[index].price),
            );
          },
        ),
      ),
    );
  }
}

class AppGlobalData {
  static String? githubVersion;
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  List<Product> products = []; // Assume this is populated elsewhere
  List<SupportItem> support = [];

  @override
  void initState() {
    super.initState();
    initializeSupport();
  }

  void initializeSupport() {
    support = [
      SupportItem(t: lang.support_patreon, d: APP.Patreon, c: Colors.orange),
      SupportItem(t: lang.support_paypal, d: APP.PayPal, c: Colors.blue),
      SupportItem(t: lang.support_wechat, d: APP.WeChat, c: Colors.green),
    ];

    // They won't allow wechat and paypal
    if (!AppGlobalData.githubVersion) {
      support = [
        SupportItem(
          t: 'GitHub',
          d: 'https://github.com/HenryQuan/WoWs-Info-Origin',
          c: Colors.black,
        ),
      ];
    }
  }

  void supportWoWsInfo(Product item) {
    // Implement your support logic here
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // Uncomment the following code if you want to display products
        // if (!AppGlobalData.GITHUB_VERSION && products != null)
        //   SingleChildScrollView(
        //     scrollDirection: Axis.horizontal,
        //     child: Row(
        //       children: products.map((item) {
        //         return ElevatedButton(
        //           style: ElevatedButton.styleFrom(
        //             margin: EdgeInsets.only(left: 4),
        //             primary: Colors.red,
        //           ),
        //           onPressed: () => supportWoWsInfo(item),
        //           child: Text(item.localizedPrice),
        //         );
        //       }).toList(),
        //     ),
        //   ),
        ...support.map((item) {
          return ListTile(
            title: Text(item.t),
            subtitle: Text(item.d),
            onTap: () => _launchURL(item.d),
          );
        }).toList(),
      ],
    );
  }

  Future<void> _launchURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}

class SupportItem {
  final String t;
  final String d;
  final Color c;

  SupportItem({required this.t, required this.d, required this.c});
}

class Product {
  final String localizedPrice;

  Product({required this.localizedPrice});
}


class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String? receipt;

  Future<void> supportWoWsInfo(String productId) async {
    try {
      // Will return a purchase object with a receipt which can be used to validate on your server.
      List<IAPItem> items = await FlutterInappPurchase.getProducts([productId]);
      if (items.isNotEmpty) {
        var purchase = await FlutterInappPurchase.requestPurchase(items[0].productId);
        // Consume it right away to buy multiple times
        await FlutterInappPurchase.consumePurchase(purchase.transactionId!);
        setState(() {
          receipt = purchase.transactionReceipt; // save the receipt if you need it, whether locally, or to your server.
        });
      }
    } catch (err) {
      print(err);
      FlutterInappPurchase.purchaseUpdated.listen((purchase) {
        setState(() {
          receipt = purchase.transactionReceipt;
        });
        goToNext();
      });
    }
  }

  void goToNext() {
    // Implement your navigation logic here
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('In-App Purchase Example')),
        body: Center(
          child: ElevatedButton(
            onPressed: () => supportWoWsInfo('your_product_id'),
            child: Text('Buy Product'),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MyApp());
}


class Donation extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Donation'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Make a Donation',
              style: TextStyle(fontSize: 24),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // Implement donation logic here
              },
              child: Text('Donate Now'),
            ),
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Donation(),
  ));
}