
class AdmobBanner extends StatefulWidget {
  @override
  _AdmobBannerState createState() => _AdmobBannerState();
}

class _AdmobBannerState extends State<AdmobBanner> {
  bool success = true;

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text('Admob Banner'),
      subtitle: Text(success ? 'Success' : 'Failed'),
    );
  }
}


class SupportMeWidget extends StatelessWidget {
  final String title = 'Support Me'; // Replace with actual translation
  final String subtitle = 'Your support helps us!'; // Replace with actual translation

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(title),
      subtitle: Text(subtitle),
      onTap: () {
        // Implement the action to support me
        SafeAction('SupportMe');
      },
    );
  }
}

void SafeAction(String action) {
  // Implement the action handling logic here
}


class MyWidget extends StatefulWidget {
  @override
  _MyWidgetState createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  bool success = true;

  void hideAds() {
    setState(() {
      success = false;
    });
  }

  void logError(dynamic err) {
    print('err: $err');
    setState(() {
      success = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Widget'),
      ),
      body: Center(
        child: success
            ? Text('Ads are visible')
            : Text('Ads are hidden'),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          hideAds();
          logError('An error occurred');
        },
        child: Icon(Icons.error),
      ),
    );
  }
}


class AdmobBanner extends StatefulWidget {
  @override
  _AdmobBannerState createState() => _AdmobBannerState();
}

class _AdmobBannerState extends State<AdmobBanner> {
  BannerAd? _bannerAd;
  bool _isBannerAdReady = false;

  @override
  void initState() {
    super.initState();
    _loadBannerAd();
  }

  void _loadBannerAd() {
    _bannerAd = BannerAd(
      adUnitId: 'YOUR_AD_UNIT_ID',
      request: AdRequest(),
      listener: BannerAdListener(
        onAdLoaded: (_) {
          setState(() {
            _isBannerAdReady = true;
          });
        },
        onAdFailedToLoad: (ad, error) {
          ad.dispose();
          print('BannerAd failed to load: $error');
        },
      ),
    )..load();
  }

  @override
  void dispose() {
    _bannerAd?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: _isBannerAdReady
          ? AdWidget(ad: _bannerAd!)
          : SizedBox(height: 50), // Placeholder for ad loading
    );
  }
}