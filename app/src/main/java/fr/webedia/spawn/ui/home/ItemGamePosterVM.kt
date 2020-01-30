package fr.webedia.spawn.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.webedia.optimusprime.utils.livedata.SingleLiveEvent
import fr.webedia.spawn.model.Game
import fr.webedia.spawn.model.GameListItem
import fr.webedia.spawn.ui.common.BaseViewModel
import fr.webedia.spawn.utils.DateUtil
import fr.webedia.spawn.utils.InjectorUtils
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList


class ItemGamePosterVM(val game: Game) : ViewModel() {

    var url = game.imageUrl

    var date = DateUtil.getWordedYearMonthAndDay(game.releaseDate)

    val onClickEvent = SingleLiveEvent<String>()

    fun displayGameDetails() {
        onClickEvent.value = game.id
    }

}