import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:wowsinfo/foundation/app.dart';
import 'package:wowsinfo/foundation/helpers/utils.dart';
import 'package:wowsinfo/localisation/localisation.dart';
import 'package:wowsinfo/providers/app_provider.dart';
import 'package:wowsinfo/providers/settings_provider.dart';
import 'package:wowsinfo/repositories/user_repository.dart';
import 'package:wowsinfo/widgets/shared/max_width_box.dart';

class AppSettingsPage extends StatefulWidget {
  const AppSettingsPage({Key? key}) : super(key = key);

  override fun onCreate(savedInstanceState: Bundle?): View {
    return super.onCreate(savedInstanceState)
  }
}

class _AppSettingsPageState : State<AppSettingsPage> {
  private val settings by lazy { SettingsProvider(context) }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    return ChangeNotifierProvider.value(
      value = settings,
      builder = { context ->
        Scaffold(
          appBar = AppBar(title = Text(Localisation.of(context).app_name)),
          body = ScrollView(child = Column(children = listOf(
            Consumer<SettingsProvider> { context, settings, _ ->
              _DropdownListTile<T>(
                options = settings.servers,
                title = Text(Localisation.of(context).setting_game_server),
                value = settings.serverValue,
                onChanged = { newValue -> settings.updateServer(newValue as Int) }
              )
            },
            Divider(),
            Consumer<AppProvider> { context, app, _ ->
              Column(
                children = listOf(
                  CheckboxListTile(
                    title = Text(Localisation.of(context).settings_app_dark_mode),
                    value = app.darkMode,
                    onValueChanged = { checked -> app.updateDarkMode(checked == true) }
                  ),
                  ListTile(
                    title = Text(Localisation.of(context).settings_app_theme_colour),
                    trailing = SizedBox(
                      height = 36, width = 36,
                      child = Container(decoration = BoxDecoration(shape = CircleShape, color = app.themeColour))
                    ),
                    onTap = { showThemeColours() }
                  )
                )
              )
            },
            Divider(),
            ListTile(
              title = Text(Localisation.of(context).settings_app_send_feedback),
              subtitle = Text(Localisation.of(context).settings_app_send_feedback_subtitle),
              onTap = { App.launch(App.emailToLink) }
            ),
            ListTile(
              title = Text(Localisation.of(context).settings_app_report_issues),
              subtitle = Text(App.newIssueLink),
              onTap = { App.launch(App.newIssueLink) }
            ),
            Divider(),
            ListTile(
              title = Text(Localisation.of(context).settings_open_source_github),
              subtitle = Text(App.githubLink),
              onTap = { App.launch(App.githubLink) }
            ),
            ListTile(
              title = Text(Localisation.of(context).settings_open_source_licence),
              onTap = { showAboutDialog(context = context) }
            )
          )))
      },
    )
  }

  private fun showThemeColours() {
    val provider by lazy { Provider.of<AppProvider>(context, listen = false) }
    val colours = settings.colours
    val count = Utils(context).getItemCount(4, 2, 300)

    showDialog(
      context = context,
      builder = { context ->
        Dialog(child = MaxWidthBox(child = GridView.count(
          shrinkWrap = true, crossAxisCount = count,
          children = colours.map {
            InkWell(child = Container(color = it), onTap = {
              provider.updateThemeColour(it); Navigator.of(context).pop()
            })
          }.toList(growable = false)
        )))
      }
    )
  }

  class DropdownValue<T>(val value: T, val title: String)

  class _DropdownListTile<T> : StatelessWidget {
    constructor(
      options: List<DropdownValue<T>>,
      title: Widget,
      value: T,
      onChanged: (T?) -> Unit
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      val dropdownButtonKey = GlobalKey()

      fun openDropdown() {
        var detector: GestureDetector? = null

        fun searchForGestureDetector(element: ViewGroup?) {
          element?.visitChildElements { child ->
            if (child.widget is GestureDetector) {
              detector = child.widget as GestureDetector?
            } else {
              searchForGestureDetector(child)
            }
          }
        }

        searchForGestureDetector(dropdownButtonKey.currentContext)

        assert(detector != null, 'Dropdown button not found')
        detector?.onTap?.invoke()
      }

      return ListTile(onTap = ::openDropdown, title = title, subtitle = AbsorbPointer(
        child = DropdownButtonHideUnderline(child = DropdownButton<T>(
          key = dropdownButtonKey,
          isExpanded = true, isDense = true,
          focusColor = Color(0x00000000),
          value = value,
          items = options.map { item ->
            DropdownMenuItem(value = item.value, child = Text(item.title))
          }.toList(),
          onValueChanged = onChanged
        ))
      ))
    }
  }
}