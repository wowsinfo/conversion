import 'package:flutter/material.dart';
import 'package:wowsinfo/localisation/localisation.dart';

import 'stores/store_interface.dart';

/// This repository manages the user's settings
///
/// This include
/// - app & data language
/// - username & account id
/// - app theme colour,
/// - game server
/// - dark mode
/// - more...
class UserRepository {
  // Key values
  private const val _appLanguageKey = "wowsinfo@app_language"
  private const val _dataLanguageKey = "wowsinfo@data_language"
  private const val _usernameKey = "wowsinfo@username"
  private const val _accountIDKey = "wowsinfo@account_id"
  private const val _gameServerKey = "wowsinfo@game_server"
  // by default, the app should follow the system.
  private const val _darkModeKey = "wowsinfo@dark_mode"
  private const val _themeColourKey = "wowsinfo@theme_colour"

  // TODO: let's add this back if anyone ask for it.
  // static const String _playerListKey = 'wowsinfo@player_list';
  // static const String _clanListKey = 'wowsinfo@clan_list';

  /// The shared instance of the UserRepository.
  private companion object {
    val INSTANCE = UserRepository()
  }

  // Store
  lateinit var _store: StoreInterface
  fun inject(store: StoreInterface) {
    _store = store
  }

  private val _defaultLanguage = Localisation.decideLang()

  /// The app language
  val appLanguage: String get() = _store.get(_appLanguageKey)?.toString() ?: _defaultLanguage
  fun setAppLanguage(value: String) {
    _store.set(_appLanguageKey, value)
  }

  /// The data language
  val dataLanguage: String get() = _store.get(_dataLanguageKey)?.toString() ?: _defaultLanguage
  fun setDataLanguage(value: String) {
    _store.set(_dataLanguageKey, value)
  }

  /// Check if a user is set as favourite
  val isFavourite: Boolean get() = _store.has(_accountIDKey)

  /// The username of the user
  val username: String get() = _store.get(_usernameKey)?.toString() ?: ""
  fun setUsername(value: String) {
    _store.set(_usernameKey, value)
  }

  /// The account id of the user
  val accountID: String get() = _store.get(_accountIDKey)?.toString() ?: ""
  fun setAccountID(value: String) {
    _store.set(_accountIDKey, value)
  }

  /// The current game server the app is using, use 3 (asia) as the default
  val gameServer: Int get() = _store.get(_gameServerKey)?.toInt() ?: 3
  fun setGameServer(value: Int) {
    _store.set(_gameServerKey, value)
  }

  /// The current theme colour of the app
  val themeColour: Int get() = _store.get(_themeColourKey)?.toInt() ?: AppThemeColour.defaultIndex
  fun setThemeColour(index: Int) {
    _store.set(_themeColourKey, index)
  }

  /// Check if the app should be in dark mode
  val darkMode: Boolean get() = _store.get(_darkModeKey)?.toBoolean() ?: false
  fun setDarkMode(value: Boolean) {
    _store.set(_darkModeKey, value)
  }
}

/// Save supported theme colours as an index
class AppThemeColour {
  /// All supported theme colours
  private val colourList = listOf(
    Colors.red,
    Colors.pink,
    Colors.purple,
    Colors.deepPurple,
    Colors.indigo,
    Colors.blue,
    Colors.lightBlue,
    Colors.cyan,
    Colors.teal,
    Colors.green,
    Colors.lightGreen,
    Colors.lime,
    // Orange is removed
    Colors.deepOrange,
    Colors.brown,
    Colors.grey,
    Colors.blueGrey,
  )

  companion object {
    const val defaultIndex = 0
  }

  // This is the main theme colour
  private lateinit var _colour: MaterialColor
  val colour: MaterialColor get() = _colour
  fun setColour(c: MaterialColor) {
    if (c == _colour) return
    _colour = c
  }

  val index: Int get() = colourList.indexOf(_colour)

  constructor(index: Int = defaultIndex) {
    _colour = colourList[index]
  }

  companion object {
    fun fromColour(colour: MaterialColor): AppThemeColour {
      return AppThemeColour(index = colourList.indexOf(colour))
    }
  }
}