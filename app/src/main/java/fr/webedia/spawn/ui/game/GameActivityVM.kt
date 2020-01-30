package fr.webedia.spawn.ui.game

import android.app.Application
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.facebook.ads.internal.id
import com.webedia.optimusprime.utils.livedata.SingleLiveEvent
import fr.webedia.spawn.ui.common.BaseViewModel
import fr.webedia.spawn.utils.DateUtil
import fr.webedia.spawn.utils.InjectorUtils

class GameActivityVMFactory(val application: Application,val id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameActivityVM(application, id) as T
    }
}

class GameActivityVM(application: Application,  id: String) : BaseViewModel(application) {

    val game = InjectorUtils.provideRepository(application.applicationContext).getGameById(id)

    val name = game.map {
        it.name
    }

    val releaseDate = game.map {
        DateUtil.getYearMonthandDay(it.releaseDate)
    }

    val posterUrl = game.map {
        it.imageUrl
    }

    val genre = game.map {
        it.genre
    }

    val editor = game.map {
        it.editor
    }
}