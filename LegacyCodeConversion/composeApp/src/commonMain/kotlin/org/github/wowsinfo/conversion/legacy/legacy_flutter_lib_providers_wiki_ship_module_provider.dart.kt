
/// The provider for `ShipModuleDialog`
class ShipModuleProvider with ChangeNotifier {
  ShipModuleProvider({
    required this.modules,
    required this.selection,
  });

  final ShipModuleMap modules;
  ShipModuleSelection selection;

  bool isSelected(ShipModuleType type, int index) {
    return selection.isSelected(type, index);
  }

  void updateSelection(ShipModuleType type, int index) {
    selection.updateSelection(type, index);
    selection = selection;
    notifyListeners();
  }
}