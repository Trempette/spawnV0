package com.webedia.optimusprime.utils.livedata

import android.os.Looper
import androidx.lifecycle.LiveData
import com.webedia.kutil.collection.toConcurrent

class MutableLiveList<T>(_initValues: Collection<T> = mutableListOf()) : LiveData<List<T>>(),
    MutableList<T> {

    private val list = (_initValues as? MutableList<T> ?: _initValues.toMutableList())
        .toConcurrent()
        .apply { update(this) }

    private fun update(list: List<T> = this.list) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            value = list.toMutableList()
        } else {
            postValue(list.toMutableList())
        }
    }

    private inline fun <R> doAndUpdate(action: MutableList<T>.() -> R): R {
        val result = list.action()
        if (result !is Boolean || result) {
            // If the result is boolean, it indicates if the list changed. Only update in this case
            update(list)
        }
        return result
    }

    override val size: Int
        get() = list.size

    override fun contains(element: T) = list.contains(element)

    override fun containsAll(elements: Collection<T>) = list.containsAll(elements)

    override fun get(index: Int) = list[index]

    override fun indexOf(element: T) = list.indexOf(element)

    override fun isEmpty() = list.isEmpty()

    override fun iterator() = list.iterator()

    override fun lastIndexOf(element: T) = list.lastIndexOf(element)

    override fun add(element: T) = doAndUpdate { add(element) }

    override fun add(index: Int, element: T) = doAndUpdate { add(index, element) }

    override fun addAll(index: Int, elements: Collection<T>) =
        doAndUpdate { addAll(index, elements) }

    override fun addAll(elements: Collection<T>) = doAndUpdate { addAll(elements) }

    override fun clear() = doAndUpdate { clear() }

    override fun listIterator() = list.listIterator()

    override fun listIterator(index: Int) = list.listIterator(index)

    override fun remove(element: T) = doAndUpdate { remove(element) }

    override fun removeAll(elements: Collection<T>) = doAndUpdate { removeAll(elements) }

    override fun removeAt(index: Int) = doAndUpdate { removeAt(index) }

    fun removeAny(predicate: (T) -> Boolean) = doAndUpdate {
        var modified = false
        with(iterator()) {
            while (hasNext()) {
                if (predicate(next())) {
                    remove()
                    modified = true
                }
            }
        }
        modified
    }

    override fun retainAll(elements: Collection<T>) = doAndUpdate { retainAll(elements) }

    override fun set(index: Int, element: T) = doAndUpdate { set(index, element) }

    override fun subList(fromIndex: Int, toIndex: Int) =
        MutableLiveList(list.subList(fromIndex, toIndex))

    fun replace(list: List<T>) {
        doAndUpdate {
            clear()
            addAll(list)
            true
        }
    }

    fun refresh() = update()

    fun snapshot() = toList()
}