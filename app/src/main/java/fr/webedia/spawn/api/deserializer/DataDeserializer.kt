package fr.webedia.spawn.api.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import fr.webedia.spawn.api.DataNetworkFeed
import java.lang.reflect.Type

class DataDeserializer<T>(private val clazz : Class<T>) : JsonDeserializer<DataNetworkFeed<T>> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): DataNetworkFeed<T> {
        return if (json.isJsonObject) {
            val obj = json.asJsonObject
            val array = obj.getAsJsonArray("data").asJsonArray
            val data: List<T> = array.map { context.deserialize<T>(it, clazz) }
            DataNetworkFeed.Data(data)
        } else {
            DataNetworkFeed.NetworkError(0, "Erreur inconnue")
        }
    }

}