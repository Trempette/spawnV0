package fr.webedia.spawn.api

import com.webedia.optimusprime.db.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class SpawnRepository(private val apiService: APIService, private val database: MyDatabase) :
    CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun killJob() {
        job.cancel()
    }

    companion object {

        @Volatile
        private var instance: SpawnRepository? = null

        fun get(apiService: APIService, database: MyDatabase) = instance ?: synchronized(SpawnRepository::class) {
            instance ?: SpawnRepository(apiService, database).also { instance = it }
        }

    }
}