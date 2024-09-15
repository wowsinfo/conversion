import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import coil.imageLoader.retrofit.RetryOnConnectionFailureInterceptor
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderMode
import com.google.accompanist.placeholder.material.MaterialPlaceholder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AssetImageLoader(private val name: String, private val placeholder: String? = null) {
    @Composable
    fun ImageLoader() {
        val context = LocalContext.current
        val imageBitmap = remember(name) { loadImage(context) }
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                modifier = Modifier
            )
        } else {
            MaterialPlaceholder(
                shape = androidx.compose.ui.graphics.Shape.RectangleShape,
                color = android.graphics.Color.RED,
                enabled = true,
                placeholderMode = PlaceholderMode.fade
            )
        }
    }

    private suspend fun loadImage(context: Context): Bitmap? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com/")
            .client(OkHttpClient.Builder().addInterceptor(RetryOnConnectionFailureInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val response = withContext(Dispatchers.IO) {
            service.getImage(ImageRequest.Builder(context).data(name).build())
        }
        return if (response.isSuccessful && response.body() != null) {
            BitmapFactory.decodeByteArray(response.body(), 0, response.body().size)
        } else {
            placeholder?.let { context.resources.getIdentifier(it, "drawable", context.packageName)?.let { id ->
                BitmapFactory.decodeResource(context.resources, id)
            }
            }
        }
    }

    interface ApiService {
        @GET("/image")
        suspend fun getImage(@Body request: ImageRequest): Response<ByteArray>
    }
}