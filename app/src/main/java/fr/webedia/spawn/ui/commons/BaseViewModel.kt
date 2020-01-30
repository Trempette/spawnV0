package fr.webedia.spawn.ui.commons

import android.app.Application
import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application), Observable {

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    fun getString(context: Context, @StringRes res: Int): String {
        return context.resources.getString(res)
    }

    fun getString(context: Context, @StringRes res: Int, vararg formatArgs: Any): String {
        return context.resources.getString(res, *formatArgs)
    }

    fun getPlurals(context: Context, @PluralsRes res: Int, quantity: Int): String {
        return context.resources.getQuantityString(res, quantity)
    }

    fun getPlurals(context: Context, @PluralsRes res: Int, quantity: Int, vararg formatArgs: Any): String {
        return context.resources.getQuantityString(res, quantity, *formatArgs)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    fun notifyChange() {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with @Bindable to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, fieldId, null)
    }
    //</editor-fold>
}