import 'dart:convert'
import 'package:flutter/material.dart'
import 'package:flutter/services.dart' show rootBundle
import 'package:flutter_gen/gen_l10n/app_localizations.dart'
import 'package:intl/intl.dart'
import 'package:logging/logging.dart'
import 'package:wowsinfo/extensions/number'
import 'package:wowsinfo/foundation/helpers/time_tracker'
import 'java.util.logging.Logger'

/// This repository manages localised strings from the Game Data
class Localisation {
    companion object {
        const val localizationsDelegates = AppLocalizations.localizationsDelegates.toTypedArray()
        const val supportedLocales = AppLocalizations.supportedLocales.toTypedArray()

        /// The wrapper of [AppLocalizations].
        fun of(context: Context) = AppLocalizations.of(context)

        /// Decide the data language based on the device language.
        fun decideLang(customLang: String? = null): String {
            val lang = customLang ?: Intl.getCurrentLocale()
            logger.info("System locale is $lang")
            val langCode = lang.toLowerCase()
            return if (validGameLanguages.contains(langCode)) {
                langCode
            } else if (langCode.contains('_')) {
                val localeCode = langCode.split('_')[0]
                if (validGameLanguages.contains(localeCode)) {
                    logger.info("Using locale `$localeCode`")
                    localeCode
                } else {
                    logger.warning("Unsupported locale $langCode, falling back to en")
                    "en"
                }
            } else {
                logger.warning("Unsupported locale $langCode, falling back to en")
                "en"
            }
        }

        /// This is a list of game languages World of Warships supports.
        private val validGameLanguages = listOf(
            "cs", "de", "en", "es", "es_mx", "fr", "it", "ja", "ko",
            "nl", "pl", "pt", "pt_br", "ru", "th", "tr", "uk", "zh",
            "zh_sg", "zh_tw"
        )

        /// This is a list of supported game languages in the app.
        /// TODO: we can add more languages, but it will dramatically increase the size of the app.
        private val supportedGameLanguages = _lang.keys.toList()

        /// The shared instance of [Localisation].
        internal var _initialised = false
        lateinit var _logger: Logger
        lateinit var _lang: Map<String, Map<String, String>>
        lateinit var _gameLang: String

        fun initialise() {
            if (_initialised) {
                logger.severe("Localisation already initialised")
                throw Exception("Localisation is already initialised")
            }
            val timer = TimeTracker()

            // load the language file
            val langString = rootBundle.loadString("data/live/app/lang/lang.json", cache = false)
            timer.log("Loaded lang.json")

            val langObject = Json.decodeFromString<Map<String, Any>>(langString)
            timer.log("Parsed lang.json")

            _lang = langObject.mapValues { (_, value) ->
                (value as Map).cast<String, String>()
            }
            _gameLang = decideLang()
            timer.log("Decoded lang.json")
            _initialised = true
        }

        fun updateDataLanguage(language: String) {
            if (!validGameLanguages.contains(language)) {
                logger.severe("Invalid language $language")
                return
            } else if (_lang[language] == null) {
                logger.warning("Language $language is not supported")
                return
            }
            logger.info("Updating data language to $language")
            _gameLang = language
        }

        fun findKeyWith(search: String, prefix: String?): List<String> {
            val searchUpper = search.toUpperCase()
            if (prefix != null) {
                searchUpper = prefix + searchUpper
            } else {
                logger.severe("Prefix is null")
            }
            val result = mutableListOf<String>()
            val langKeys = _lang[_gameLang]?.keys
            if (langKeys == null) return result.toList(growable = false)
            for (key in langKeys) {
                if (key.contains(searchUpper)) result.add(key)
            }

            return result.toList()
        }

        fun get(key: String): String? {
            if (!_initialised) {
                logger.severe("Localisation not initialised")
                return null
            } else if (_lang[_gameLang] == null) {
                logger.severe("Language $_gameLang not found")
                return null
            }
            val rawString = _lang[_gameLang]?.get(key)
            // logger.fine("raw - $rawString")
            if (rawString == null || rawString.trim().isEmpty) {
                logger.severe("Language key $key not found or empty")
                return null
            }

            return rawString
        }


/**
 * This method