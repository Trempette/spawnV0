package fr.webedia.spawn.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import fr.webedia.spawn.model.Game
import fr.webedia.spawn.utils.DateUtil
import java.util.*

class Converters {

    @TypeConverter
    fun stringToDateShowtime(value: String): Date {
        return DateUtil.parseStringToDateInMillis(value)
    }

    @TypeConverter
    fun dateToStringShowtime(value: Date): String {
        return DateUtil.parseDateToStringApi(value)
    }

    @TypeConverter
    fun gameToJson(value: Game?): String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToGame(value: String): Game{
        return Gson().fromJson(value, Game::class.java) as Game
    }
}