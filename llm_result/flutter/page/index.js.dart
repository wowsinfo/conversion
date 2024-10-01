
export 'common/filter.dart';
export 'common/loading.dart';
export 'home/setup.dart';
export 'home/friend.dart';
export 'home/search.dart';
export 'wiki/consumable.dart';
export 'wiki/basic_detail.dart';
export 'wiki/commander_skill.dart';
export 'wiki/achievement.dart';
export 'wiki/map.dart';
export 'wiki/collection.dart';
export 'wiki/warship.dart';
export 'wiki/warship_detail.dart';
export 'wiki/warship_filter.dart';
export 'wiki/warship_module.dart';
export 'wiki/similar_graph.dart';
export 'player/player_achievement.dart';
export 'player/rating.dart';
export 'player/graph.dart';
export 'clan/clan_info.dart';
export 'settings/about.dart';
export 'settings/license.dart';
export 'settings/pro_version.dart';

class App {
  static Widget getMenu() {
    return Menu();
  }

  static Widget getRS() {
    return RS();
  }

  static Widget getStatistics() {
    return Statistics();
  }

  static Widget getSettings() {
    return Settings();
  }
}