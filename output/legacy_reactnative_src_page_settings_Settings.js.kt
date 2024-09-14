@Composable
fun Settings(
    theme: androidx.compose.material3.MaterialTheme,
    state: SettingsState = remember {
        SettingsState()
    }
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (AppGlobalData.shouldUpdateAPI) {
            AppGlobalData.canCheckForUpdate = false
            WikiAPIService.fetchGithubAppVersion().also { v ->
                val version = v.getOrNull("version") ?: return@LaunchedEffect
                if (version > APP.Version) {
                    displayUpdate(true, version)
                } else {
                    displayUpdate(false)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        WoWsInfo(about = true) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    SectionTitle(title = stringResource(R.string.settings_api_settings))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        SectionTitle(
                            title = "${
                                stringResource(
                                    R.string.setting_game_server
                                )
                            } - ${
                                stringResource(
                                    R.string.server_name_values[
                                        state.server
                                    ]
                                )
                            }"
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {
                            SERVER.forEachIndexed { index, key ->
                                Button(
                                    modifier = Modifier.weight(1f),
                                    onClick = {
                                        updateServer(index)
                                    },
                                    contentPadding = PaddingValues(0.dp),
                                    shape = RoundedCornerShape(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = getColor(state.server, index)
                                    ),
                                    enabled = state.server != index
                                ) {
                                    Text(
                                        text = stringResource(
                                            R.string.server_name_values[
                                                index
                                            ]
                                        )
                                    )
                                }
                            }
                        }

                        val langData: List<String> =
                            getAPIList().keys.toList()
                        SectionTitle(title = "${
                            stringResource(
                                R.string.setting_api_language
                            )
                        } - ${getAPIList()[state.APILanguage]}")
                        if (AppGlobalData.shouldUpdateAPI) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    langData.forEach { item ->
                                        Button(
                                            modifier = Modifier.weight(1f),
                                            onClick = {
                                                updateApiLanguage(item)
                                            },
                                            contentPadding = PaddingValues(0.dp),
                                            shape = RoundedCornerShape(0.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor =
                                                    getColor(state.APILanguage, item)
                                            ),
                                            enabled = state.APILanguage != item
                                        ) {
                                            Text(text = getAPIList()[item])
                                        }
                                    }
                                }

                                val format = String.format
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor =
                                            getColor(state.APILanguage, -1)
                                    ),
                                    onClick = {
                                        context?.let { it ->
                                            AlertDialog(
                                                onDismissRequest = {},
                                                title = {
                                                    Text(
                                                        text = stringResource(
                                                            R.string.app_name
                                                        )
                                                    )
                                                },
                                                text = {
                                                    Column {
                                                        Text(
                                                            text =
                                                                format(
                                                                    stringResource(
                                                                        R.string.setting_api_update_data_title
                                                                    ),
                                                                    getAPIList()[
                                                                        state.APILanguage
                                                                    ]
                                                                )
                                                        )

                                                        Text(
                                                            text =
                                                                stringResource(
                                                                    R.string.setting_api_update_data_subtitle
                                                                )
                                                        )
                                                    }
                                                },
                                                confirmButton = {
                                                    Button(
                                                        onClick = {
                                                            updateApiLanguage(
                                                                state.APILanguage,
                                                                true
                                                            )
                                                        }
                                                    ) {
                                                        Text(
                                                            text =
                                                                stringResource(
                                                                    R.string.setting_api_update_data_update
                                                                )
                                                        )
                                                    }
                                                },
                                                dismissButton = {
                                                    Button(
                                                        onClick = {
                                                            it.dismiss()
                                                        }
                                                    ) {
                                                        Text(
                                                            text =
                                                                stringResource(
                                                                    R.string.setting_api_update_data_cancel
                                                                )
                                                        )
                                                    }
                                                }
                                            ).show(it)
                                        }
                                    }
                                ) {
                                    Text(text = stringResource(R.string.setting_api_update_data))
                                }
                            }
                        } else {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    langData.forEach { item ->
                                        Button(
                                            modifier = Modifier.weight(1f),
                                            onClick = {
                                                updateApiLanguage(item)
                                            },
                                            contentPadding = PaddingValues(0.dp),
                                            shape = RoundedCornerShape(0.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor =
                                                    getColor(state.APILanguage, item)
                                            ),
                                            enabled = state.APILanguage != item
                                        ) {
                                            Text(text = getAPIList()[item])
                                        }
                                    }
                                }
                            }
                        }

                        val appLang: Map<String, String> = mapOf(
                            "en" to stringResource(R.string.app_lang_en),
                            "ja" to stringResource(R.string.app_lang_ja),
                            "zh" to stringResource(R.string.app_lang_zh),
                            "zh-hant" to stringResource(R.string.app_lang_zh_hant)
                        )
                        val appLangList: List<Map.Entry<String, String>> =
                            appLang.entries.toList()
                        SectionTitle(title = "${
                            stringResource(
                                R.string.setting_app_language
                            )
                        } - ${appLang[state.userLanguage]}"
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {
                            appLangList.forEach { item ->
                                Button(
                                    modifier = Modifier.weight(1f),
                                    onClick = { updateUserLang(item.key) },
                                    contentPadding = PaddingValues(0.dp),
                                    shape = RoundedCornerShape(4.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor =
                                            getColor(state.userLanguage, -1)
                                    ),
                                ) {
                                    Text(text = item.value)
                                }
                            }
                        }

                        /* <FlatList
                        data={appLangList}
                        renderItem={({item}) => {
                            return <Button onPress={() => updateUserLang(item.key)}>{item.value}</Button>
                        }}
                        keyExtractor={i => i.key} numColumns={3}/>
                    </SectionTitle> */
                    List.Section()
                }
              ]
            )
        })
    } else {
      this.displayUpdate(false)
    }
  }

  /**
   * Swap bottom buttons
   */
  swapButton(curr) {
    setSwapButton(curr)
    this.setState({swapButton: getSwapButton()})
  }

  /**
   * Update app theme real time
   */
  updateTheme() {
    val tintColour = state.tintColour

    // Switch mode
    AppGlobalData.darkMode = !AppGlobalData.isDarkMode
    this.setState { State -> State.copy(darkMode = AppGlobalData.isDarkMode) }
    props.theme.dark = AppGlobalData.darkMode
    if (AppGlobalData.darkMode) {
      val darkTheme = DarkTheme.toBuilder().colors(
          DarkTheme.colors.copy(
              View = Color.Black,
              text = GREY.shade50,
              primary = tintColour[500],
              accent = tintColour[300]
          )
      ).build()
      AppGlobalData.darkTheme = darkTheme
      props.theme.colors = darkTheme.colors
    } else {
      // Setup global light theme
      val lightTheme = DefaultTheme.toBuilder().colors(
          DefaultTheme.colors.copy(
              View = Color.White,
              text = GREY.shade900,
              primary = tintColour[500],
              accent = tintColour[300]
          )
      ).build()
      AppGlobalData.lightTheme = lightTheme
      props.theme.colors = lightTheme.colors
    }
  }

  /**
   * UPdate app tint colour
   * @param {*} tint
   */
  updateTint(tint: Colour) {
    UpdateTintColour(tint)

    val theme = props.theme
    theme.colors.primary = tint[500]
    theme.colors.accent = tint[300]

    this.setState { State -> State.copy(showColour = false, tintColour = tint) }
  }

  /**
   * Update server that's being used
   */
  updateServer(index: Int) {
    setCurrServer(index)
    this.setState { State -> State.copy(server = index) }
  }

  /**
   * Update apiLanguage that's being used
   * @param {String} language
   * @param {Boolean} force foce update
   */
  updateApiLanguage(language: String, force: Boolean) {
    if (!force && state.APILanguage == language) {
      return
    }

    setAPILanguage(language)
    this.setState { State -> State.copy(APILanguage = language) }

    setFirstLaunch(true)
    AppGlobalData.shouldUpdateAPI = false
    Actions.reset('Menu')
  }

  updateUserLang(code: String) {
    setUserLang(code)
    lang.setLanguage(code)
    this.setState { State -> State.copy(userLanguage = code) }
  }

}

/**
 * UPdate app tint colour
 * @param {*} tint
 */
fun updateTint(tint: Colour) {
  UpdateTintColour(tint)

  val theme = props.theme
  theme.colors.primary = tint[500]
  theme.colors.accent = tint[300]

  this.setState { State -> State.copy(showColour = false, tintColour = tint) }
}

/**
 * Update server that's being used
 */
fun updateServer(index: Int) {
  setCurrServer(index)
  this.setState { State -> State.copy(server = index) }
}

/**
 * Update apiLanguage that's being used
 * @param {String} language
 * @param {Boolean} force foce update
 */
fun updateApiLanguage(language: String, force: Boolean) {
  if (!force && state.APILanguage == language) {
    return
  }

  setAPILanguage(language)
  this.setState { State -> State.copy(APILanguage = language) }

  setFirstLaunch(true)
  AppGlobalData.shouldUpdateAPI = false
  Actions.reset('Menu')
}

fun updateUserLang(code: String) {
  setUserLang(code)
  lang.setLanguage(code)
  this.setState { State -> State.copy(userLanguage = code) }
}

/**
 * Update app theme real time
 */
fun updateTheme() {
  val tintColour = state.tintColour

  // Switch mode
  AppGlobalData.darkMode = !AppGlobalData.isDarkMode
  this.setState { State -> State.copy(darkMode = AppGlobalData.isDarkMode) }
  props.theme.dark = AppGlobalData.darkMode
  if (AppGlobalData.darkMode) {
    val darkTheme = DarkTheme.toBuilder().colors(
        DarkTheme.colors.copy(
            View = Color.Black,
            text = GREY.shade50,
            primary = tintColour[500],
            accent = tintColour[300]
        )
    ).build()
    AppGlobalData.darkTheme = darkTheme
    props.theme.colors = darkTheme.colors
  } else {
    // Setup global light theme
    val lightTheme = DefaultTheme.toBuilder().colors(
        DefaultTheme.colors.copy(
            View = Color.White,
            text = GREY.shade900,
            primary = tintColour[500],
            accent = tintColour[300]
        )
    ).build()
    AppGlobalData.lightTheme = lightTheme
    props.theme.colors = lightTheme.colors
  }
}

/**
 * Swap bottom buttons
 */
fun swapButton(curr: Boolean) {
  setSwapButton(curr)
  this.setState { State -> State.copy(swapButton = getSwapButton()) }
}