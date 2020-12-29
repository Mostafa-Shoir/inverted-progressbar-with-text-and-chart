import android.content.Context
import java.io.IOException

class Utils {
	fun getJsonFromAssets(context: Context, fileName: String?): String? {
		var jsonString = ""
            try {
                val stream = context.assets.open(fileName!!)
                val size = stream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
				jsonString = String(buffer, charset("UTF-8"))
            } catch (e: IOException) {
                // Handle exceptions here
                e.printStackTrace()
            }
		return jsonString
	}
}