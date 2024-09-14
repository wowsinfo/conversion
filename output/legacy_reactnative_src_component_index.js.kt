import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FloatingButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        elevation = null,
        contentColor = MaterialTheme.colorScheme.primaryContainer,
        backgroundColor = MaterialTheme.colorScheme.primary,
        modifier = modifier.clickable { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = "Floating Button")
    }
}

@Composable
fun FooterButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick) {
        Text(text)
    }.let { it ->
        if (modifier != Modifier) {
            it.modifier.clickable { onClick() }
        } else {
            it
        }
    }
}

@Composable
fun Touchable(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier.clickable { onClick() })
}

@Composable
fun AdmobBanner(
    bannerAdUnitId: String,
    adSize: Int, // Replace with appropriate type and value
    modifier: Modifier = Modifier
) {
    // Implement Admob Banner Composable
}

// Common

// Wiki
@Composable
fun WikiIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Icon(imageVector = icon, contentDescription = "Wiki Icon")
}

@Composable
fun WarshipCell(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick) {
        Text(text = name)
    }.let { it ->
        if (modifier != Modifier) {
            it.modifier.clickable { onClick() }
        } else {
            it
        }
    }
}

@Composable
fun WarshipLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(text)
}

@Composable
fun PriceLabel(
    price: Int, // Replace with appropriate type and value
    modifier: Modifier = Modifier
) {
    // Implement Price Label Composable
}

@Composable
fun WarshipStat(
    name: String,
    value: Int, // Replace with appropriate type and value
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = name)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "$value")
    }
}

// Player
@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick, selected = isSelected) {
        Text(text)
    }.let { it ->
        if (modifier != Modifier) {
            it.modifier.clickable { onClick() }
        } else {
            it
        }
    }
}

@Composable
fun DetailedInfo(
    modifier: Modifier = Modifier
) {
    // Implement Detailed Info Composable
}

@Composable
fun Info6Icon(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Icon(imageVector = icon, contentDescription = "Info 6 Icon")
}

@Composable
fun PlayerRecord(
    name: String,
    value: Int, // Replace with appropriate type and value
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = name)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "$value")
    }
}

@Composable
fun RatingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick) {
        Text(text)
    }.let { it ->
        if (modifier != Modifier) {
            it.modifier.clickable { onClick() }
        } else {
            it
        }
    }
}

@Composable
fun SimpleRating(
    rating: Int, // Replace with appropriate type and value
    modifier: Modifier = Modifier
) {
    Text(text = "$rating")
}