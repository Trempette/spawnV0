package fr.webedia.spawn.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.webedia.spawn.ui.common.BaseViewModel

class SearchFragmentVMFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchFragmentVM(application) as T
    }
}

class SearchFragmentVM(application: Application) : BaseViewModel(application) {



}