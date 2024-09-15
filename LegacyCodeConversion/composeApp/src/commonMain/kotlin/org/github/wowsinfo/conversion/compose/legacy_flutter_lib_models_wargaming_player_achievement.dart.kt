
@Composable
fun PlayerAchievementCard(battle: Map<String, Int>?, progress: Map<String, Int>?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Player's Achievements",
                style = MaterialTheme.typography.titleLarge
            )
            if (battle != null) {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource("assets/images/battle_achievement.png"),
                        contentDescription = "Battle Achievement",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Battle Achievements:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                for ((key, value) in battle.entries) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painterResource("assets/images/checkmark.png"),
                            contentDescription = "Checkmark",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$key: $value",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            if (progress != null) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource("assets/images/progress_achievement.png"),
                        contentDescription = "Progress Achievement",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Progress Achievements:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                for ((key, value) in progress.entries) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painterResource("assets/images/checkmark.png"),
                            contentDescription = "Checkmark",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$key: $value",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

        }
    }
}