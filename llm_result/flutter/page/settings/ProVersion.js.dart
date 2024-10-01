
class ProVersion extends StatefulWidget {
  @override
  _ProVersionState createState() => _ProVersionState();
}

class _ProVersionState extends State<ProVersion> {
  final String sku = 'wowsinfo.proversion';
  bool loading = true;
  bool error = false;
  String price = '';
  String discountPrice = '';

  @override
  void initState() {
    super.initState();
    initConnection();
    getSubscriptions();
    purchaseUpdatedListener();
    purchaseErrorListener();
  }

  Future<void> initConnection() async {
    final bool available = await InAppPurchase.instance.isAvailable();
    if (!available) {
      setState(() {
        error = true;
        loading = false;
      });
    }
  }

  Future<void> getSubscriptions() async {
    // Fetch the subscriptions from the store
    // Update the state with the fetched prices
  }

  void purchaseUpdatedListener() {
    InAppPurchase.instance.purchaseStream.listen((List<PurchaseDetails> purchaseDetailsList) {
      for (var purchaseDetails in purchaseDetailsList) {
        if (purchaseDetails.status == PurchaseStatus.purchased) {
          // Handle successful purchase
          finishTransaction(purchaseDetails);
        } else if (purchaseDetails.status == PurchaseStatus.error) {
          // Handle error
          showError(purchaseDetails);
        }
      }
    });
  }

  void purchaseErrorListener() {
    // Handle purchase errors
  }

  Future<void> requestSubscription() async {
    final PurchaseParam purchaseParam = PurchaseParam(productDetails: /* your product details */);
    await InAppPurchase.instance.buyConsumable(purchaseParam: purchaseParam);
  }

  Future<void> finishTransaction(PurchaseDetails purchaseDetails) async {
    // Finish the transaction
  }

  void showError(PurchaseDetails purchaseDetails) {
    // Show error message
    AlertDialog alert = AlertDialog(
      title: Text("Error"),
      content: Text("Purchase failed: ${purchaseDetails.error?.message}"),
      actions: [
        TextButton(
          child: Text("OK"),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
      ],
    );
    showDialog(context: context, builder: (BuildContext context) => alert);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Pro Version'),
      ),
      body: loading
          ? Center(child: CircularProgressIndicator())
          : error
              ? Center(child: Text('Error loading subscriptions'))
              : ScrollView(
                  child: Column(
                    children: [
                      Text('Price: $price'),
                      Text('Discount Price: $discountPrice'),
                      ElevatedButton(
                        onPressed: requestSubscription,
                        child: Text('Purchase Pro Version'),
                      ),
                    ],
                  ),
                ),
    );
  }
}


class YourWidget extends StatefulWidget {
  @override
  _YourWidgetState createState() => _YourWidgetState();
}

class _YourWidgetState extends State<YourWidget> {
  bool loading = true;
  bool error = false;
  String price = '';
  String discountPrice = '';
  String sku = 'your_sku'; // Replace with your actual SKU

  @override
  void initState() {
    super.initState();
    setupListeners();
    initConnectionAndFetchData();
  }

  void setupListeners() {
    purchaseUpdatedListener((purchase) async {
      print('purchaseUpdatedListener: $purchase');
      final receipt = purchase.transactionReceipt;
      if (receipt != null) {
        await finishTransaction(purchase, false);
        setProVersion(true);
        Navigator.pop(context);
        showDialog(
          context: context,
          builder: (context) => AlertDialog(
            title: Text(lang.pro_title),
            content: Text(lang.iap_thx_for_support),
            actions: [
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop();
                },
                child: Text('OK'),
              ),
            ],
          ),
        );
        Future.delayed(Duration(milliseconds: 500), () {
          setState(() {});
        });
      }
    });

    purchaseErrorListener((error) {
      print('purchaseErrorListener: $error');
    });
  }

  Future<void> initConnectionAndFetchData() async {
    final allGood = await initConnection();
    print(allGood);
    setState(() {
      error = !allGood;
    });

    if (allGood) {
      print('This device can make purchases');
      final items = await getSubscriptions([sku]);
      print(items);
      if (items.length == 1) {
        final pro = items[0];
        setState(() {
          price = pro.localizedPrice;
          discountPrice = pro.introductoryPrice;
          loading = false;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    if (loading) {
      return Center(child: CircularProgressIndicator());
    }

    if (error) {
      return Center(child: Text('Error initializing purchase'));
    }

    return Column(
      children: [
        Text('Price: $price'),
        Text('Discount Price: $discountPrice'),
        // Add your UI components here
      ],
    );
  }
}

void dispose() {
  if (purchaseUpdateSubscription != null) {
    purchaseUpdateSubscription.cancel();
    purchaseUpdateSubscription = null;
  }
  if (purchaseErrorSubscription != null) {
    purchaseErrorSubscription.cancel();
    purchaseErrorSubscription = null;
  }
  super.dispose();
}


class MyWidget extends StatelessWidget {
  final TextStyle titleStyle;
  final BoxDecoration viewStyle;

  MyWidget({required this.titleStyle, required this.viewStyle});

  @override
  Widget build(BuildContext context) {
    return WoWsInfo(hideAds: true, child: Column(
      children: [
        Expanded(
          child: SingleChildScrollView(
            child: Container(
              decoration: viewStyle,
              child: Column(
                children: [
                  Text(lang.pro_title, style: titleStyle),
                  ListTile(
                    title: Text(lang.pro_rs),
                    subtitle: Text(lang.pro_rs_subtitle),
                  ),
                  ListTile(
                    title: Text(lang.pro_more_stats),
                    subtitle: Text(lang.pro_more_stats_subtitle),
                  ),
                  ListTile(
                    title: Text(lang.pro_support_development),
                    subtitle: Text(lang.pro_support_development_subtitle),
                  ),
                ],
              ),
            ),
          ),
        ),
        renderPurchaseView(),
        renderPolicies(),
      ],
    ));
  }

  Widget renderPurchaseView() {
    // Implement your purchase view here
    return Container(); // Placeholder for purchase view
  }

  Widget renderPolicies() {
    // Implement your policies view here
    return Container(); // Placeholder for policies view
  }
}


class PolicyScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          ElevatedButton(
            onPressed: () async {
              const url = 'https://github.com/HenryQuan/WoWs-Info-Future/blob/legacy_version/Privacy%20Policy.md';
              if (await canLaunch(url)) {
                await launch(url);
              } else {
                throw 'Could not launch $url';
              }
            },
            child: Text('Privacy policy'),
          ),
          ElevatedButton(
            onPressed: () async {
              const url = 'https://github.com/HenryQuan/WoWs-Info-Future/blob/legacy_version/Term%20of%20Use.md';
              if (await canLaunch(url)) {
                await launch(url);
              } else {
                throw 'Could not launch $url';
              }
            },
            child: Text('Term of use'),
          ),
        ],
      ),
    );
  }
}


class PurchaseView extends StatefulWidget {
  @override
  _PurchaseViewState createState() => _PurchaseViewState();
}

class _PurchaseViewState extends State<PurchaseView> {
  bool loading = false;
  String? error;
  String price = '99.99'; // Example price
  final TextStyle discountStyle = TextStyle(fontSize: 16, color: Colors.red);
  final ButtonStyle buttonStyle = ElevatedButton.styleFrom(
    shape: RoundedRectangleBorder(borderRadius: BorderRadius.zero),
  );
  final ButtonStyle restoreButtonStyle = OutlinedButton.styleFrom(
    shape: RoundedRectangleBorder(borderRadius: BorderRadius.zero),
  );

  void buy() {
    // Implement buy functionality
  }

  void restore() {
    // Implement restore functionality
  }

  @override
  Widget build(BuildContext context) {
    if (loading) {
      return Center(child: CircularProgressIndicator());
    } else if (error != null) {
      return Container(); // Return an empty container on error
    } else {
      return Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text('50% off until renewal', style: discountStyle),
          ElevatedButton(
            style: buttonStyle,
            onPressed: buy,
            child: Text('$price / per year'),
          ),
          OutlinedButton(
            style: restoreButtonStyle,
            onPressed: restore,
            child: Text('Restore Pro'),
          ),
        ],
      );
    }
  }
}


class SubscriptionService {
  final String sku;

  SubscriptionService(this.sku);

  Future<void> buy() async {
    final bool available = await InAppPurchase.instance.isAvailable();
    if (!available) {
      print('In-app purchases are not available');
      return;
    }

    final ProductDetailsResponse response = await InAppPurchase.instance.queryProductDetails({sku}.toSet());
    if (response.notFoundIDs.isNotEmpty) {
      print('Product not found: ${response.notFoundIDs}');
      return;
    }

    final ProductDetails productDetails = response.productDetails.first;

    final PurchaseParam purchaseParam = PurchaseParam(productDetails: productDetails);
    try {
      await InAppPurchase.instance.buyConsumable(purchaseParam: purchaseParam);
    } catch (err) {
      print('Error: ${err.toString()}');
    }
  }
}


class PurchaseHistory {
  Future<void> validateProVersion(bool isPro) async {
    // Implement your validation logic here
  }

  Future<void> restore() async {
    await validateProVersion(true);
    // Add logic to get all purchases history and check for the last one
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: MyView(),
      ),
    );
  }
}

class MyView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              // Add your widgets here
            ],
          ),
        ),
      ),
    );
  }
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 32, horizontal: 16),
      child: Text(
        'Your Title Here',
        style: TextStyle(
          fontSize: 32,
          fontWeight: FontWeight.bold,
          color: Colors.orange[500],
        ),
      ),
    );
  }
}


class MyButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: ElevatedButton(
        onPressed: () {
          // Add your onPressed logic here
        },
        child: Text('Button'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      appBar: AppBar(title: Text('Flutter Button Example')),
      body: Center(child: MyButton()),
    ),
  ));
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // Implement your restore functionality here
          },
          child: Text('Restore'),
          style: ElevatedButton.styleFrom(
            margin: EdgeInsets.only(top: 8),
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MyWidget(),
  ));
}


class DiscountWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      margin: EdgeInsets.only(bottom: 4),
      child: Text(
        'Discount',
        textAlign: TextAlign.center,
      ),
    );
  }
}


class LoaderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: CircularProgressIndicator(),
      ),
    );
  }
}


class ProVersion extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Pro Version'),
      ),
      body: Center(
        child: Text('Welcome to the Pro Version!'),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: ProVersion(),
  ));
}