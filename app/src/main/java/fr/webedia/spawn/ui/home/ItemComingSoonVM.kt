package fr.webedia.spawn.ui.home

import android.app.Application
import fr.webedia.spawn.model.GameListItem
import fr.webedia.spawn.ui.common.BaseViewModel

class ItemComingSoonVM(application: Application, var gameListItem: GameListItem) : BaseViewModel(application) {

    var title = gameListItem.title

    var listGames = gameListItem.listGames

}