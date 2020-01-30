package fr.webedia.spawn.utils

import android.content.Context
import fr.webedia.spawn.db.MyDatabase
import fr.webedia.spawn.api.APIRetrofitInstance
import fr.webedia.spawn.api.SpawnRepository

object InjectorUtils {
    fun provideRepository(context: Context): SpawnRepository {
        val database = MyDatabase.get(context.applicationContext)
        return SpawnRepository.get(APIRetrofitInstance.service, database)
    }
}