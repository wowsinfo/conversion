
@Composable
fun AssetImageLoader(name: String, placeholder: Int? = null) {
    val imageBitmap = remember(name) {
        loadImage(name)
    }

    Box {
        if (imageBitmap != null) {
            Image(bitmap = imageBitmap.asImageBitmap(), contentDescription = null)
        } else if (placeholder != null) {
            Image(bitmap = imageResource(id = placeholder), contentDescription = null)
        }
    }
}

suspend fun loadImage(name: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            // Load your image here
        } catch (e: IOException) {
            null
        }
    }
}

@Preview
@Composable
fun PreviewAssetImageLoader() {
    AssetImageLoader(name = "example_image", placeholder = R.drawable.placeholder)
}

fun ImageWithPlaceholder(name: String, placeholder: String? = null) {
    val painter = rememberImagePainter(name) {
        error(R.drawable.ic_close) // Assuming you have a close icon in your drawable resources
    }

    if (painter.state is ImagePainter.State.Error) {
        if (placeholder == null) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.Red
            )
        } else {
            Image(
                painter = rememberImagePainter(placeholder),
                contentDescription = null
            )
            Image(
                painter = painter,
                contentDescription = null
            )
        }
    } else {
        Image(
            painter = painter,
            contentDescription = null
        )
    }
}


@Composable
fun AssetImageLoader(name: String, placeholder: String? = null) {
    var image by remember { mutableStateOf<BitmapPainter?>(null) }
    var error by remember { mutableStateOf(false) }

    LaunchedEffect(name) {
        image = loadAssetImage(name) ?: run {
            error = true
            null
        }
    }

    Box(modifier = Modifier.size(100.dp)) {
        if (image != null) {
            Image(painter = image!!, contentDescription = null)
        } else if (error) {
            if (placeholder != null) {
                Image(painter = BitmapPainter(loadImageFromAssets(placeholder)), contentDescription = null)
            } else {
                Icon(imageVector = Icons.Filled.Close, contentDescription = null, tint = Color.Red)
            }
        }
    }
}

suspend fun loadAssetImage(name: String): BitmapPainter? {
    return withContext(Dispatchers.IO) {
        try {
            val inputStream = // Load your asset image here
            val bitmap = BitmapFactory.decodeStream(inputStream)
            BitmapPainter(bitmap.asImageBitmap())
        } catch (e: Exception) {
            null
        }
    }
}

fun loadImageFromAssets(name: String): Bitmap {
    // Implement your logic to load image from assets
}