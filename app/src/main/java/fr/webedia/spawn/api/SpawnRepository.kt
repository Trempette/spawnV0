package fr.webedia.spawn.api

import fr.webedia.spawn.db.MyDatabase
import fr.webedia.spawn.model.Game
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SpawnRepository(private val apiService: APIService, private val database: MyDatabase) :
    CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun killJob() {
        job.cancel()
    }

    suspend fun insertListOfGameInDB(list: List<Game>) {
        coroutineScope {
            withContext(Dispatchers.IO) {
                database.gamesDao().insert(list)
            }
        }
    }

    companion object {

        @Volatile
        private var instance: SpawnRepository? = null

        fun get(apiService: APIService, database: MyDatabase) =
            instance ?: synchronized(SpawnRepository::class) {
                instance ?: SpawnRepository(apiService, database).also { instance = it }
            }

    }
}