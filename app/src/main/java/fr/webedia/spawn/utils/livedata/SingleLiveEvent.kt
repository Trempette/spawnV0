/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.webedia.optimusprime.utils.livedata

import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import fr.webedia.spawn.utils.removeFirst
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 */
open class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pendingObservers =
        ConcurrentHashMap<Observer<T>, Pair<Observer<in T>, AtomicBoolean>>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val interceptor = object : Observer<T> {
            override fun onChanged(t: T?) {
                synchronized(pendingObservers) {
                    pendingObservers[this]
                        ?.takeIf { it.second.compareAndSet(true, false) }
                        ?.first
                        ?.onChanged(t)
                }
            }
        }

        synchronized(pendingObservers) {
            pendingObservers[interceptor] = observer to AtomicBoolean(false)
        }

        super.observe(owner, interceptor)
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observer)
        synchronized(pendingObservers) {
            pendingObservers.removeFirst { key, value ->
                observer == key || observer == value.first
            }
        }
    }

    @MainThread
    override fun setValue(value: T?) {
        synchronized(pendingObservers) {
            pendingObservers.values.forEach { it.second.set(true) }
        }
        super.setValue(value)
    }
}

fun SingleLiveEvent<Unit>.call() {
    if (Looper.getMainLooper() == Looper.myLooper()) {
        value = Unit
    } else {
        postValue(Unit)
    }
}
