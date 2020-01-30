package com.webedia.optimusprime.utils.livedata

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

@Suppress("NOTHING_TO_INLINE")
inline fun <VM : ViewModel> FragmentActivity.obtainViewModel(
    clazz: KClass<VM>,
    factory: ViewModelProvider.Factory? = null
): VM {
    return if (factory == null) {
        ViewModelProviders.of(this).get(clazz.java)
    } else {
        ViewModelProviders.of(this, factory).get(clazz.java)
    }
}

inline fun <reified VM : ViewModel> FragmentActivity.obtainViewModel(
    key: String? = null,
    factory: ViewModelProvider.Factory? = null
): VM {
    return when {
        key == null -> obtainViewModel(factory)
        factory == null -> ViewModelProviders.of(this).get(key, VM::class.java)
        else -> ViewModelProviders.of(this, factory).get(key, VM::class.java)
    }
}

inline fun <reified VM : ViewModel> FragmentActivity.obtainViewModel(factory: ViewModelProvider.Factory? = null): VM {
    return obtainViewModel(VM::class, factory)
}