
class LoadingIndicator extends StatelessWidget {
  final BoxDecoration? style;

  const LoadingIndicator({Key? key, this.style}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Color appTheme = Theme.of(context).primaryColor;
    if (appTheme == Colors.transparent) {
      appTheme = Colors.blue; // Default to blue if no theme is set
    }

    return Container(
      decoration: style,
      child: Center(
        child: CircularProgressIndicator(
          valueColor: AlwaysStoppedAnimation<Color>(appTheme),
        ),
      ),
    );
  }
}


class LoadingIndicator extends StatelessWidget {
  final bool isIos;
  final Color appThemeColor;
  final Color greyColor;
  final EdgeInsetsGeometry style;

  LoadingIndicator({
    required this.isIos,
    required this.appThemeColor,
    required this.greyColor,
    this.style = const EdgeInsets.all(0),
  });

  @override
  Widget build(BuildContext context) {
    return Center(
      child: CircularProgressIndicator(
        strokeWidth: isIos ? 2.0 : 4.0,
        valueColor: AlwaysStoppedAnimation<Color>(isIos ? greyColor : appThemeColor),
      ),
    );
  }
}