import 'package:flutter/foundation.dart'
import 'package:flutter/material.dart'
import 'package:logging/logging.dart'
import 'package:wowsinfo/repositories/app_repository.dart'
import 'package:wowsinfo/repositories/game_repository.dart'
import 'package:wowsinfo/localisation/localisation.dart'
import 'package:wowsinfo/repositories/stores/shared_store.dart'
import 'package:wowsinfo/repositories/user_repository.dart'
import 'package:wowsinfo/widgets/wowsinfo.dart'

fun setup() = runBlocking {
    // setup logger and make sure it only prints in debug mode
    Logger.root.level = Level.ALL // defaults to Level.INFO
    Logger.root.onRecord.listen { record ->
        if (kDebugMode) {
            val message = "${record.loggerName}: ${record.message}"
            println("${record.level.name}|$message")
        }
    }

    // setup the store
    val store = SharedStore()
    store.load()

    // inject to repositories
    AppRepository.instance.inject(store)
    UserRepository.instance.inject(store)
}

// Load all local app data from gamedata folder
fun loadAppData() = runBlocking {
    // load the repositories
    Localisation.instance.initialise()
    GameRepository.instance.initialise()
}

@Composable
fun WoWsInfoApp() {
    MaterialTheme(
        colors = darkColors,
        content = {
            WoWsInfo()
        }
    )
}

fun main() = runBlocking {
    WidgetsFlutterBinding.ensureInitialized()
    setup()

    // debug
    // debugRepaintRainbowEnabled = true

    ApplicationProviderCompat.createScope(MaterialTheme()) {
        WoWsInfoApp()
    }
}