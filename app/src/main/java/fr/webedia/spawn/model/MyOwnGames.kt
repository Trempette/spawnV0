package fr.webedia.spawn.model

import androidx.room.Entity
import java.util.*

@Entity(
        tableName = "myOwnGames",
        primaryKeys = ["id"]
)
data class MyOwnGames(
        var game: Game,
        var id: String = game.id,
        var iHaveToDoIt: Boolean,
        var inProgress: Boolean,
        var finished: Boolean,
        var stopped: Boolean
)
