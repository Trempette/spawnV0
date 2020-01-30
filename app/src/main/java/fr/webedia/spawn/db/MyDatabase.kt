package com.webedia.optimusprime.db

import android.content.Context
import androidx.room.*
import fr.webedia.spawn.model.Game

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

@Database(
    entities = [Game::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

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
