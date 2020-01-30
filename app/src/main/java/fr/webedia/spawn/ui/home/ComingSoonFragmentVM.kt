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

class ComingSoonFragmentVMFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ComingSoonFragmentVM(application) as T
    }
}

class ComingSoonFragmentVM(application: Application) : BaseViewModel(application) {

    var list = InjectorUtils.provideRepository(application.applicationContext).getAllGames()

    var listMostWaited = list.map {
        it.filter { it.releaseDate.time > System.currentTimeMillis()}
    }

    var listSortieDuMois = list.map {
        it.filter { it.releaseDate.month == Date(System.currentTimeMillis()).month }
    }

    var mesSortie = list.map {
        listOf<Game>()
    }

    var listGameListItem =
        listMostWaited.switchMap {listMostWaited ->
            listSortieDuMois.switchMap {sortiesDuMois ->
                mesSortie.map { mesSortie ->
                    val list = arrayListOf<GameListItem>()
                    list.add(GameListItem("Les plus attendus", listMostWaited))
                    list.add(GameListItem("Sorties du mois", sortiesDuMois))
                    list.add(GameListItem("Mes sorties", mesSortie))
                    return@map list.toList()
                }
            }
        }

}