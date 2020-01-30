package fr.webedia.spawn.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import fr.webedia.spawn.model.Game
import fr.webedia.spawn.model.MyOwnGames

interface IDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(objects: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}

@Dao
interface GamesDAO : IDao<Game> {

    @Query("SELECT * FROM games")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id: String): LiveData<Game>

}

@Dao
interface MyOwnGamesDAO : IDao<MyOwnGames> {

    @Query("SELECT * FROM myOwnGames")
    fun getAllMyGames(): LiveData<List<MyOwnGames>>

}

@Database(
    entities = [Game::class, MyOwnGames::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDAO
    abstract fun myOwnGamesDao(): MyOwnGamesDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun get(context: Context) =
            INSTANCE ?: synchronized(MyDatabase::class.java) {
                INSTANCE ?: Room
                    .databaseBuilder(context, MyDatabase::class.java, "spawn")
                    .build()
                    .also { INSTANCE = it }
            }

    }


}
