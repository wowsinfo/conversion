
class Touchable extends StatelessWidget {
  final BoxDecoration? decoration;
  final Widget child;
  final bool fill;
  final VoidCallback? onPress;

  const Touchable({
    Key? key,
    this.decoration,
    required this.child,
    this.fill = false,
    this.onPress,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onPress,
      child: Container(
        decoration: fill ? BoxDecoration(color: Colors.transparent) : decoration,
        child: child,
      ),
    );
  }
}