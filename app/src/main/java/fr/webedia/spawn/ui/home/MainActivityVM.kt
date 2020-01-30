package fr.webedia.spawn.ui.home

import android.app.Application
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.webedia.optimusprime.utils.livedata.SingleLiveEvent
import fr.webedia.spawn.ui.common.BaseViewModel

class MainActivityVMFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityVM(application) as T
    }
}

class MainActivityVM(application: Application) : BaseViewModel(application) {

    val onNavigationClick = SingleLiveEvent<Int>()

    fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
        onNavigationClick.value = item.itemId
        return true
    }
}