
class LocalizedStrings {
  static const List<String> supportedLanguages = ['en'];

  static Map<String, Map<String, String>> localizedValues = {
    'en': {
      // Setup section
      'setup_title': 'Set up WoWs Info',
      'setup_done_button': 'Done',
      'setup_loading': 'Loading...',
      // Home section
      'friend_clan_title': 'Clan',
      'friend_player_title': 'Player',
      // Update section
      'update_version_title': 'Version',
      'update_app_version': 'App →',
      'update_game_version': 'Game →',
      // Button section
      'button_settings_label': 'Settings',
      'button_menu_label': 'Menu',
      'button_back_label': 'Go Back',
      'button_home_label': 'Home',
      // Wiki section
      'wiki_warship_footer': 'Filter',
      'wiki_warship_filter_btn': 'Apply Filters',
      'wiki_warship_reset_btn': 'Reset',
      'wiki_warship_filter_placeholder': 'Enter a Ship Name',
      'wiki_warship_filter_tier': 'Tier',
      'wiki_warship_filter_nation': 'Nation / Region',
      'wiki_warship_filter_type': 'Type',
      'wiki_warship_filter_premium': 'Premium Ship',
      // Warship Detail
      'warship_unknown': 'Unknown',
      'warship_model': '3D Model',
      'warship_update_module': 'Update Ship Modules',
      'warship_default_module': 'Default',
      'warship_apply_module': 'Apply Modules',
      'warship_survivability': 'Survivability',
      'warship_survivability_health': 'Health',
      'warship_survivability_armour': 'Armour',
      'warship_survivability_protection': 'Protection',
      'warship_artillery': 'Artillery',
      'warship_artillery_main': 'Main Battery',
      'warship_avg_damage': 'Average Damage',
      'warship_avg_winrate': 'Average Winrate',
      'warship_avg_frag': 'Average Frags',
      'warship_weapon_reload': 'Reload Time',
      'warship_weapon_range': 'Range',
      'warship_weapon_configuration': 'Configuration',
      'warship_weapon_dispersion': 'Max Dispersion',
      'warship_weapon_rotation': 'Rotation Time',
      'warship_weapon_fire_chance': 'Fire Chance',
      'warship_artillery_main_weight': 'Weight',
      'warship_weapon_damage': 'Max Damage',
      'warship_weapon_potential_damage': 'Potential Damage',
      'warship_weapon_ap_overmatch': 'Overmatch',
      'warship_weapon_he_penetration': 'Penetration',
      'warship_weapon_speed': 'Speed',
      'warship_ramming': 'Ramming',
      'warship_artillery_secondary': 'Secondary',
      'warship_torpedoes': 'Torpedoes',
      'warship_torpedoes_visible_distance': 'Detection',
      'warship_antiaircraft': 'AA Defense',
      'warship_maneuverability': 'Maneuverability',
      'warship_maneuverability_turning': 'Turning Radius',
      'warship_maneuverability_speed': 'Max Speed',
      'warship_maneuverability_rudder_time': 'Rudder Time',
      'warship_aircraft': 'Aircraft',
      'warship_concealment': 'Concealment',
      'warship_concealment_detect_by_ship': 'Detection by Ship',
      'warship_concealment_detect_by_plane': 'Detection by Plane',
      'warship_upgrades': 'Upgrades',
      'warship_next_ship': 'Next Ship(s)',
      'warship_compare_similar': 'Compare Similar Ships',
      // Menu section
      // Settings section
      'server_name': 'Russia, Europe, North America, Asia',
      'setting_game_server': 'Game Server',
      'setting_api_language': 'API Language',
      'setting_api_update_data': 'Update data from server',
      'setting_api_update_data_title': 'Please only use this when data is missing',
      'setting_api_update_data_update': 'Update',
      'setting_api_update_data_cancel': 'Cancel',
      'setting_app_language': 'WoWs Info Language',
      'settings_api_settings': 'API Settings',
      'settings_app_settings': 'App Settings',
      'settings_app_dark_mode': 'Dark Mode',
      'settings_app_theme_colour': 'Theme Colour',
      'settings_app_no_image_mode': 'No Image Mode',
      'settings_app_swap_buttons': 'Swap Bottom Buttons',
      'settings_app_send_feedback': 'Send Feedback',
      'settings_app_send_feedback_subtitle': 'Send an Email to the developer',
      'settings_app_report_issues': 'Report an Issue',
      'settings_app_write_review': 'Write a Review',
      'settings_app_write_review_title': 'About Review and Feedback',
      'settings_app_write_review_message': 'If you have any questions, issues, or suggestions, please send an email to the developer so that we can discuss and solve the problem together.',
      'settings_app_write_review_yes': 'Send an Email',
      'settings_app_write_review_no': 'Write a Review',
      'settings_app_share': 'Share with Friends',
      'settings_app_share_subtitle': 'Share WoWs Info if you enjoy using it!',
      'settings_app_check_for_update': 'Check for Update',
      'settings_app_no_update': 'You are using the latest version.',
      'settings_app_has_update': 'Update {0} is available',
      'settings_open_source': 'Open Source',
      'settings_open_source_github': 'GitHub',
      'settings_open_source_licence': 'Licences',
      'settings_open_source_licence_subtitle': 'Great modules that are used by WoWs Info',
      // Footer
      'menu_footer': 'Search',
      'menu_search_player': 'Player',
      'menu_search_clan': 'Clan',
      // Pro version
      'pro_upgrade_button': 'Upgrade to Pro',
      'pro_title': 'WoWs Info Pro',
      'pro_rs': 'RS Beta',
      'pro_rs_subtitle': 'Get realtime statistics in your battles',
      'pro_more_stats': 'More Detailed Statistics',
      'pro_more_stats_subtitle': 'Show even more statistics in your profile',
      'pro_quick_access_main': 'Quick Access to Main Account',
      'pro_quick_access_main_subtitle': 'Check statistics for your main account with just a tap',
      'pro_support_development': 'Support Development',
      'pro_support_development_subtitle': 'More features are currently under development',
      'pro_50_off_until_re': '50% Off for the First Purchase',
      'pro_per_year': 'Year',
      'pro_restore_pro': 'Restore Pro Version',
      'iap_thx_for_support': 'Thank you for your support!',
      'iap_no_purchase_history': 'No Payment History Found',
      'iap_pro_expired': 'WoWs Info Pro has expired',
      // Search bar
      'search_player_online': 'Online',
      // Wiki
      'wiki_section_title': 'Encyclopedia',
      'wiki_achievement': 'Achievement',
      'wiki_warships': 'Warships',
      'wiki_upgrades': 'Upgrades',
      'wiki_flags': 'Flags / Camouflages',
      'wiki_skills': 'Commander Skills',
      'wiki_skills_tier': 'Tier',
      'wiki_skills_reset': 'Reset Skills',
      'wiki_skills_point': 'Point(s)',
      'wiki_maps': 'Maps',
      'wiki_collections': 'Collections',
      // Extra
      'extra_section_title': 'Extra',
      'extra_support_wowsinfo': 'Support WoWs Info',
      'extra_wowsinfo_re': 'WoWs Info Re',
      'extra_wowsinfo_re_subtitle': 'Try out the new version currently under development',
      'extra_rs_beta': 'Realtime Statistics Beta',
      'extra_rs_beta_download': 'Download RS on your computer',
      'extra_support_wowsinfo_subtitle': 'Donations and Ads',
      'support_paypal': 'PayPal',
      'support_patreon': 'Patreon',
      'support_wechat': 'WeChat',
      // Websites
      'website_title': 'Websites',
      'website_official_site': 'World of Warships',
      'website_premium': 'Premium Shop',
      'website_global_wiki': 'Global Wiki',
      'website_dev_blog': 'Developer Blog',
      'website_sea_group': 'SEA Group',
      'website_daily_bounce': 'The Daily Bounce',
      'website_numbers': 'WoWS Stats & Numbers',
      'website_today': 'Warships.Today',
      'website_ranking': 'Player Ranking',
      'website_models': 'Warships Models',
      'website_game_models': 'GameModels3D',
      'website_stats_news_title': 'Statistics & News',
      'website_official_title': 'Official Websites',
      'website_utility_title': 'Utilities',
      'website_ap_calculator': 'Ship AP Calculator',
      'website_wowsft': 'WoWs Fitting Tool',
      'website_ingame_title': 'In-game Websites',
      'website_wargaming_login': 'Wargaming.net Login',
      'website_wargaming_login_subtitle': 'You have to log in first to use websites below. It works better on tablets.',
      'website_userbonus': 'My Bonus',
      'website_news_ingame': 'In-game News',
      'website_ingame_armory': 'My Armory',
      'website_ingame_clan': 'My Clan',
      'website_ingame_warehouse': 'My Warehouse',
      'website_my_logbook': 'My Logbook',
      // CC
      'content_creator_title': 'Content Creator',
      'content_creator_official': 'WoWs Official',
      'content_creator_official_link': 'https://www.youtube.com/user/worldofwarshipscom',
      'content_creator_fubuki': 'AozoraFubuki',
      'content_creator_fubuki_link': 'https://www.youtube.com/@SYC-HANQ/videos',
      // Tools
      'tool_title': 'Tools',
      // Player section
      // Tabs
      'tab_achievement_title': 'Achievement',
      'tab_graph_title': 'Graph',
      'tab_ship_title': 'Ship',
      'tab_rank_title': 'Rank',
      // Rating
      'rating_title': 'Personal Rating',
      'rating_description': "This rating reflects a player's skill level.",
      'rating_read_more': '- Read More -',
      'rating_warning': 'Please refrain from using this rating to judge other players',
      'rating_author': 'By Wiochi',
      'rating_scale': 'Colour Scale',
      'rating_bad': 'Improvement Needed',
      'rating_below_average': 'Below Average',
      'rating_average': 'Average',
      'rating_good': 'Good',
      'rating_very_good': 'Very Good',
      'rating_great': 'Great',
      'rating_unicum': 'Unicum',
      'rating_super_unicum': 'SUPER Unicum',
      'rating_unknown': 'Unknown',
      // Basic
      'basic_data_unknown': 'Unknown',
      'basic_level_tier': 'Level',
      'basic_register_date': 'Registration Date',
      'basic_last_battle': 'Last Battle',
      'basic_more_stat': '- Show More -',
      'basic_add_friend': 'Add to List',
      'basic_set_main': 'Set as Main Account',
      // Detailed
      'detailed_win': 'Wins',
      'detailed_draw': 'Draws',
      'detailed_loss': 'Losses',
      'detailed_survived': 'Survived Battles',
      'detailed_total_xp': 'Total XP',
      'detailed_survived_win': 'Survived Wins',
      'detailed_survival_rate': 'Survival Rate',
      'detailed_survived_win_rate': 'Survived Win Rate',
      'detailed_total_potential_damage': 'Total Potential Damage',
      'detailed_avg_potential_damage': 'Avg Potential Damage',
      'detailed_total_torp_potential_damage': 'Total Torpedo Potential Damage',
      'detailed_avg_torp_potential_damage': 'Avg Torpedo Potential Damage',
      'detailed_total_scouting_damage': 'Total Scouting Damage',
      'detailed_avg_scouting_damage': 'Avg Scouting Damage',
      'detailed_total_damage': 'Total Damage Dealt',
      'detailed_damage_potential_ratio': 'Damage Dealt / Potential',
      'detailed_total_spotted': 'Total Spotted',
      'detailed_avg_spotted': 'Avg Spotted',
      'detailed_total_frag': 'Total Frags',
      'detailed_frag_spot_ratio': 'Frag / Spotted',
      'detailed_total_plane_killed': 'Total Planes Destroyed',
      'detailed_avg_plane_killed': 'Avg Planes Destroyed',
      // Ship detail
      'ship_detail_damage': 'Damage',
      'ship_detail_winrate': 'Winrate',
      'ship_detail_frag': 'Frags',
      'ship_sort_battle': 'Battle',
      'ship_sort_colour': 'Colour',
      // Rank
      'rank_season_title': 'Season',
      // Clan
      'clan_created_date': 'Creation Date',
      'clan_creator_name': 'Creator',
      'clan_leader_name': 'Leader',
      'clan_member_title': 'Member',
      // Record
      'record_title': 'Records',
      'record_best_ship': 'Best Ship',
      'record_max_damage_dealt': 'Max Damage Dealt',
      'record_max_frags_battle': 'Max Frags',
      'record_max_planes_killed': 'Max Planes Destroyed',
      'record_max_xp': 'Max XP',
      'record_max_ships_spotted': 'Max Ships Spotted',
      'record_max_total_agro': 'Max Potential Damage Dealt',
      'record_max_damage_scouting': 'Max Scouting Damage',
      // Weapon record
      'weapon_max_frags': 'Max Frags',
      'weapon_total_frags': 'Total Frags',
      'weapon_hit_ratio': 'Hit Ratio',
      // App name section
      'app_name': 'WoWs Info Seven',
      'wowsinfo_black': 'Black',
      'wowsinfo_white': 'White',
      'wowsinfo_ultra': 'Ultra',
      'wowsinfo_ultimate': 'Ultimate',
      'wowsinfo_pro': 'Pro',
      'wowsinfo_new': 'New',
      'wowsinfo_go': 'Go',
      'wowsinfo_origin': 'Origin',
      'wowsinfo_free': 'Free',
      // RS section
      // Tips
      'rs_tip_download': '1. Download',
      'rs_tip_one': 'Please visit this GitHub repository and download the latest release. This program does not use a lot of RAM and storage. Currently, it is in its beta stage and has no GUI.\n',
      'rs_tip_setup': '2. Setup',
      'rs_tip_two': "Copy and paste the path of your game folder into this program, and you have to run it with admin or it will not work. It won't do anything harmful to your computer.\n",
      'rs_tip_enjoy': '3. Enjoy',
      'rs_tip_three': 'Enter the IP address you see on the screen, and WoWs Info will start giving you real-time statistics for both teams.\n',
      // Error section
      // General errors
      'error_title': 'Error',
      'error_download_issue': 'Failed to update data.\nPlease try again later.\n\nIf the issue persists, please try using a VPN or changing your DNS to "8.8.8.8".\nIf the problem continues, it indicates that the server is unavailable',
      'error_timeout': 'Timeout',
      'error_pr_corrupted': 'Personal rating data cannot be downloaded',
      // About WoWs Info section
      // GitHub link
      'about_github_link': 'https://github.com/HenryQuan/WoWs-Info-Origin/blob/master/README.md',
    },
  };

  String getString(String key) {
    return localizedValues['en']![key] ?? key;
  }
}

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'WoWs Info',
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: [
        const Locale('en', ''), // English
      ],
      home: Scaffold(
        appBar: AppBar(
          title: Text(LocalizedStrings().getString('app_name')),
        ),
        body: Center(
          child: Text(LocalizedStrings().getString('setup_title')),
        ),
      ),
    );
  }
}


void main() {
  runApp(WoWsInfoApp());
}

class WoWsInfoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'WoWs Info',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('WoWs Info'),
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text('设置 WoWs Info'),
            onTap: () {
              // Navigate to setup page
            },
          ),
          ListTile(
            title: Text('版本信息'),
            onTap: () {
              // Navigate to update page
            },
          ),
          ListTile(
            title: Text('战舰百科'),
            onTap: () {
              // Navigate to wiki page
            },
          ),
          ListTile(
            title: Text('其他'),
            onTap: () {
              // Navigate to extra page
            },
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: '主页面',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.settings),
            label: '设置',
          ),
        ],
      ),
    );
  }
}

class SetupPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('设置 WoWs Info'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('加载中...'),
            ElevatedButton(
              onPressed: () {
                // Complete setup
              },
              child: Text('完成'),
            ),
          ],
        ),
      ),
    );
  }
}

class UpdatePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('版本信息'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('APP版本 ->'),
            Text('游戏版本 ->'),
          ],
        ),
      ),
    );
  }
}

class WikiPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('战舰百科'),
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text('成就'),
            onTap: () {
              // Navigate to achievements
            },
          ),
          ListTile(
            title: Text('战舰'),
            onTap: () {
              // Navigate to warships
            },
          ),
          ListTile(
            title: Text('升级品'),
            onTap: () {
              // Navigate to upgrades
            },
          ),
        ],
      ),
    );
  }
}

class ExtraPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('其他'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('支持 WoWs Info'),
            ElevatedButton(
              onPressed: () {
                // Support options
              },
              child: Text('捐助'),
            ),
          ],
        ),
      ),
    );
  }
}


void main() {
  runApp(WoWsInfoApp());
}

class WoWsInfoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'WoWs Info 七號',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('WoWs Info'),
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text('設定 WoWs Info'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => SetupPage()),
              );
            },
          ),
          ListTile(
            title: Text('戰艦百科'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => WikiPage()),
              );
            },
          ),
          ListTile(
            title: Text('工具'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ToolsPage()),
              );
            },
          ),
        ],
      ),
    );
  }
}

class SetupPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('設定 WoWs Info'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('載入中...'),
            ElevatedButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: Text('完成'),
            ),
          ],
        ),
      ),
    );
  }
}

class WikiPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('戰艦百科'),
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text('艦船'),
            onTap: () {
              // Navigate to warships section
            },
          ),
          ListTile(
            title: Text('成就'),
            onTap: () {
              // Navigate to achievements section
            },
          ),
          ListTile(
            title: Text('地圖'),
            onTap: () {
              // Navigate to maps section
            },
          ),
        ],
      ),
    );
  }
}

class ToolsPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('工具'),
      ),
      body: Center(
        child: Text('工具頁面內容'),
      ),
    );
  }
}


void main() {
  runApp(WoWsInfoApp());
}

class WoWsInfoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'WoWs Info セブン',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('WoWs Infoの初期設定'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('読み込み中...'),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // Handle setup done
              },
              child: Text('完了'),
            ),
            SizedBox(height: 20),
            Text('バージョン: アプリ ->, ゲーム ->'),
            SizedBox(height: 20),
            Text('設定'),
            Text('メニュー'),
            Text('戻る'),
            Text('ホーム'),
          ],
        ),
      ),
    );
  }
}

class WikiSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('百科事典'),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(title: Text('実績')),
          ListTile(title: Text('艦艇')),
          ListTile(title: Text('アップグレード')),
          ListTile(title: Text('信号旗')),
          ListTile(title: Text('艦長スキル')),
          ListTile(title: Text('地図')),
          ListTile(title: Text('コレクション')),
        ],
      ),
    );
  }
}

class SettingsSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('設定'),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(title: Text('ゲームサーバー')),
          ListTile(title: Text('API言語')),
          ListTile(title: Text('データ更新')),
          ListTile(title: Text('WoWs Info言語')),
          ListTile(title: Text('ダークモード')),
          ListTile(title: Text('テーマ色')),
          ListTile(title: Text('省データモード')),
          ListTile(title: Text('ボタン入れ替え')),
          ListTile(title: Text('フィードバック')),
          ListTile(title: Text('レビューを書く')),
          ListTile(title: Text('シェア')),
          ListTile(title: Text('アップデートを確認')),
        ],
      ),
    );
  }
}

class WarshipDetail extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('艦艇詳細'),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(title: Text('不明')),
          ListTile(title: Text('3Dモデル')),
          ListTile(title: Text('モジュール変更')),
          ListTile(title: Text('初期モジュール')),
          ListTile(title: Text('生存力')),
          ListTile(title: Text('HP')),
          ListTile(title: Text('装甲')),
          ListTile(title: Text('対水雷防御')),
          ListTile(title: Text('火砲')),
          ListTile(title: Text('主砲')),
          ListTile(title: Text('平均ダメージ')),
          ListTile(title: Text('勝率')),
          ListTile(title: Text('艦船撃沈')),
          ListTile(title: Text('装填時間')),
          ListTile(title: Text('射程')),
          ListTile(title: Text('配置')),
          ListTile(title: Text('最大散布')),
          ListTile(title: Text('旋回時間')),
          ListTile(title: Text('火災発生率')),
          ListTile(title: Text('最大ダメージ')),
          ListTile(title: Text('潜在ダメージ')),
          ListTile(title: Text('オーバーマッチ')),
          ListTile(title: Text('装甲貫通力')),
          ListTile(title: Text('速度')),
          ListTile(title: Text('体当たり')),
          ListTile(title: Text('副砲')),
          ListTile(title: Text('魚雷')),
          ListTile(title: Text('対空火力')),
          ListTile(title: Text('機動性')),
          ListTile(title: Text('旋回半径')),
          ListTile(title: Text('最大速度')),
          ListTile(title: Text('転舵時間')),
          ListTile(title: Text('航空機')),
          ListTile(title: Text('隠蔽性')),
          ListTile(title: Text('海上探知距離')),
          ListTile(title: Text('航空探知距離')),
          ListTile(title: Text('アップグレード')),
          ListTile(title: Text('次の艦')),
          ListTile(title: Text('類似艦の比較')),
        ],
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
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Home Page'),
      ),
      body: Center(
        child: Text('Hello, Flutter!'),
      ),
    );
  }
}