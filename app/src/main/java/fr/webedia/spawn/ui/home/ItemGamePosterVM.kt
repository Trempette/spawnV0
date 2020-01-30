package fr.webedia.spawn.ui.home

import android.app.Application
import androidx.lifecycle.*
import fr.webedia.spawn.model.Game
import fr.webedia.spawn.model.GameListItem
import fr.webedia.spawn.ui.common.BaseViewModel
import fr.webedia.spawn.utils.DateUtil
import fr.webedia.spawn.utils.InjectorUtils
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList


class ItemGamePosterVM(application: Application, game: Game) : BaseViewModel(application) {

    var url = game.imageUrl


}