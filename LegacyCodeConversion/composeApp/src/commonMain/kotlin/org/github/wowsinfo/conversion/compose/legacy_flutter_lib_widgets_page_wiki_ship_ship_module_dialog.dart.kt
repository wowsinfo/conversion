import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:wowsinfo/extensions/list.dart'
import 'package:wowsinfo/foundation/colours.kt'
import 'package:wowsinfo/models/wowsinfo/ship_module_selection.kt'
import 'package:wowsinfo/models/wowsinfo/ship_modules.kt'
import 'package:wowsinfo/providers/wiki/ship_module_provider.kt'
import 'package:wowsinfo/localisation/localisation.kt'
import 'package:wowsinfo/widgets/shared/max_width_box.kt'

void showShipModuleDialog(
  BuildContext context,
  ShipModuleMap modules,
  ShipModuleSelection selection,
  void Function(ShipModuleSelection) onUpdate,
) {
  showDialog(
    context: context,
    builder: (_) => ChangeNotifierProvider<ShipModuleProvider>(
      create: (_) => ShipModuleProvider(
        modules: modules,
        // make a deep copy of the selection
        selection: ShipModuleSelection.fromSelection(selection),
      ),
      builder: (context, child) => _ShipModuleDialog(
        modules: modules,
        onUpdate: onUpdate,
      ),
    ),
  );
}

class _ShipModuleDialog : StatelessWidget {
  const _ShipModuleDialog({
    Key? key,
    required this.modules,
    required this.onUpdate,
  }) : super(key = key);

  final ShipModuleMap modules;
  final void Function(ShipModuleSelection) onUpdate;

  @Composable
  override fun build(context: Context): Unit {
    Dialog(
      child = SingleChildScrollView(
        child = MaxWidthBox(
          child = Column(
            children = renderModuleMap(context),
          ),
        ),
      ),
    )
  }

  List<Widget> renderModuleMap(BuildContext context) {
    final provider = Provider.of<ShipModuleProvider>(context);
    val entries = modules.entries;
    return entries.map { entry ->
      val moduleType = entry.key
      val list = entry.value
      Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children = [
          Container(height = 8),
          Padding(
            padding = const EdgeInsets.symmetric(horizontal = 16, vertical = 4),
            child = Text(
              moduleType.name ?: 'X',
              style = Theme.of(context).textTheme.headline6,
            ),
          ),
          for (val module in list.enumerate())
            buildModuleListTile(
              context,
              module.key,
              provider.isSelected(moduleType, module.key),
              module.value,
              provider.updateSelection,
            ),
          // add divider if this is not the last module
          if (entry.key != entries.last.key) const Divider(),
          // add the update button to dimiss the dialog
          if (entry.key == entries.last.key)
            Padding(
              padding = const EdgeInsets.symmetric(horizontal = 16, vertical = 8),
              child = ElevatedButton(
                onPressed = {
                  Navigator.of(context).pop()
                  onUpdate(provider.selection)
                },
                child = const Text('Update'),
              ),
            ),
        ],
      )
    }.toList(growable = false)
  }

  ListTile buildModuleListTile(
    BuildContext context,
    Int index,
    Boolean selected,
    ShipModuleHolder module,
    Void Function(ShipModuleType, Int) onChange,
  ) {
    val info = module.module!!
    val type = module.type
    return ListTile(
      leading = Checkbox(
        value = selected,
        onChanged = { _ -> onChange(type, index) },
      ),
      title = Text(Localisation.instance.stringOf(info.name) ?: ''),
      subtitle = Text(
        info.cost.costCr.toString(),
        style = const TextStyle(color = WoWsColours.creditPrice),
      ),
      trailing = Text('${info.cost.costXp} XP'),
      onTap = { _ -> onChange(type, index) },
    )
  }
}