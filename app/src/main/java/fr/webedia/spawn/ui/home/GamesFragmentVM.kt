package fr.webedia.spawn.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.webedia.spawn.ui.common.BaseViewModel

class GamesFragmentVMFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GamesFragmentVM(application) as T
    }
}

class GamesFragmentVM(application: Application) : BaseViewModel(application) {



}