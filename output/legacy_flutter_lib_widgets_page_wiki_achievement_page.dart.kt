import 'package:flutter/material.dart'
import 'package:logging/logging.dart'
import 'package:wowsinfo/foundation/app.dart'
import 'package:wowsinfo/foundation/helpers/utils.dart'
import 'package:wowsinfo/models/gamedata/achievement.dart'
import 'package:wowsinfo/repositories/game_repository.dart'
import 'package:wowsinfo/localisation/localisation.dart'
import 'package:wowsinfo/widgets/animation/popup_box.dart'
import 'package:wowsinfo/widgets/shared/asset_image_loader.dart'
import 'package:wowsinfo/widgets/shared/max_width_box.dart'
import 'package:wowsinfo/widgets/shared/new_item_indicator.dart'
import 'package:wowsinfo/widgets/shared/placeholder.dart'

class AchievementPage: StatelessWidget {
    val achievements = GameRepository.instance.achievementList
    val logger = Logger("AchievementPage")

    override fun build(context: Context): Widget {
        val itemCount = Utils.of(context).getItemCount(8, 2, 100)
        return Scaffold(
            appBar = AppBar(title = Text("Achievement Page")),
            body = SafeArea(
                child = Scrollbar(
                    child = buildGridView(itemCount),
                ),
            ),
        )
    }

    fun buildGridView(itemCount: Int): GridView {
        // Display everything
        return GridView.builder(
            gridDelegate = SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount = itemCount,
                childAspectRatio = 1.0,
            ),
            itemCount = achievements.size,
            itemBuilder = { context, index ->
                val curr = achievements[index]
                val imageName = curr.icon
                return PopupBox(
                    child = InkWell(
                        child = Stack(
                            fit = StackFit.passthrough,
                            children = [
                                FittedBox(
                                    child = AssetImageLoader(name = "data/live/app/assets/achievements/$imageName.png"),
                                ),
                                if (curr.added == 1) NewItemIndicator(),
                            ],
                        ),
                        onTap = { showInfo(context, curr) },
                    ),
                )
            })
    }

    fun showInfo(context: Context, achievement: Achievement) {
        showDialog(
            context = context,
            builder = { context ->
                AlertDialog(
                    content = MaxWidthBox(
                        child = ListTile(
                            contentPadding = EdgeInsets.all(2),
                            title = Text(
                                Localisation.instance.stringOf(achievement.name)?.takeIf(String::isNotEmpty) ?: "",
                                maxLines = 1,
                                overflow = TextOverflow.ellipsis,
                            ),
                            subtitle = Text(Localisation.instance.stringOf(
                                    achievement.description,
                                    constants = achievement.constants
                                )?.takeIf(String::isNotEmpty) ?: ""),
                        ),
                    ),
                )
            },
        )
    }
}