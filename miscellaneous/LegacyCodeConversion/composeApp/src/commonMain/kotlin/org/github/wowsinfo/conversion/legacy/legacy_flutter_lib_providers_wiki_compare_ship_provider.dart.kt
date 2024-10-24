
class CompareShipProvider with ChangeNotifier {
  final _logger = Logger('CompareShipProvider');

  ShipFilter? _filter;
  set filter(ShipFilter value) {
    if (_filter == value) return;
    _compareShip(value);
  }

  Iterable<ShipInfoProvider> _ships = [];
  Iterable<ShipInfoProvider> get ships => _ships;

  void _compareShip(ShipFilter filter) {
    _filter = filter;
    final ships = GameRepository.instance.shipList;
    _ships = ships
        .where((s) => filter.shouldDisplay(s))
        .map((s) => ShipInfoProvider(s));
    _logger.info('CompareShipProvider updated with ${_ships.length} ships');

    notifyListeners();
  }
}