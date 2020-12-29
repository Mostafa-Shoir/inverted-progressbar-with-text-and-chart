
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taskfordigis.models.LegendModel

class JsonParser {
	fun getFileJsonParse(response : String?) : LegendModel? {
		try {
			val gson = Gson()
			val type = object : TypeToken<LegendModel>() {
			}.type
			return gson.fromJson(response, type)
		} catch (e1 : Exception) {
			e1.printStackTrace()
			return null!!
		}
		
	}
	
}