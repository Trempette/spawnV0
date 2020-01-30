package com.webedia.optimusprime.utils.livedata

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.webedia.kutil.collection.toConcurrent
import java.util.concurrent.atomic.AtomicBoolean

class LifecycleDelegate(
    private val lifecycleOwner: LifecycleOwner,
    private val lifecycle: Lifecycle
) : Lifecycle() {
    private val destroyed = AtomicBoolean(false)

    private val destroying = AtomicBoolean(false)

    private val observers = ArrayList<LifecycleObserver>().toConcurrent()

    override fun addObserver(observer: LifecycleObserver) {
        lifecycle.addObserver(observer)
        observers.add(observer)
    }

    override fun removeObserver(observer: LifecycleObserver) {
        lifecycle.removeObserver(observer)
        if (!destroying.get()) {
            observers.remove(observer)
        }
    }

    override fun getCurrentState(): State {
        return if (destroyed.get()) State.DESTROYED else lifecycle.currentState
    }

    @SuppressLint("RestrictedApi")
    fun onDestroy() {
        destroyed.set(true)
        synchronized(observers) {
            destroying.set(true)
            observers.forEach {
                if (it is LifecycleEventObserver) {
                    it.onStateChanged(lifecycleOwner,
                        Event.ON_DESTROY
                    )
                }
            }
            destroying.set(false)
            observers.clear()
        }
    }
}