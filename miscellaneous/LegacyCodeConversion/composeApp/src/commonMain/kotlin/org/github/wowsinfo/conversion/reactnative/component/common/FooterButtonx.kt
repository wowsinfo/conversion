
@Composable
fun FooterButton(icon: String, left: Boolean) {
    val al = if (icon == "cog") stringResource(id = R.string.button_settings_label) else ""

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = { /* Handle click */ }, modifier = Modifier) {
            Text(text = al, color = Color.Black)
        }
    }
}

@Preview
@Composable
fun PreviewFooterButton() {
    FooterButton(icon = "cog", left = true)
}

    al = lang.button_back_label
}

    al = lang.button_home_label
}

    al = lang.button_menu_label
}

    when (icon) {
        "cog" -> SafeAction("Settings")
        "arrow-left" -> {
            Actions.pop()
            if (Actions.state.routes.size == 2) {
                GlobalScope.launch {
                    delay(1000)
                    Actions.refresh()
                }
            }
        }
        "home" -> {
            Actions.popTo("Menu")
            setLastLocation("")
            GlobalScope.launch {
                delay(1000)
                Actions.refresh()
            }
        }
        else -> SafeAction("Search")
    }
}

fun CustomButton(left: Boolean, icon: Painter, al: String, pressEvent: () -> Unit) {
    Box(
        modifier = Modifier
            .absoluteOffset(x = if (left) 8.dp else 0.dp, y = 0.dp)
            .padding(end = if (left) 0.dp else 8.dp)
    ) {
        IconButton(
            onClick = { pressEvent() },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            Icon(painter = icon, contentDescription = al)
        }
    }
}