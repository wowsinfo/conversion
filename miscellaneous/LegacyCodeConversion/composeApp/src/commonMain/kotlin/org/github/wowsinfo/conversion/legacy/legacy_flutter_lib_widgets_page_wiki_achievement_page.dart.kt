
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