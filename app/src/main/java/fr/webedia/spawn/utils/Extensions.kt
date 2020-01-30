package fr.webedia.spawn.utils

inline fun <K, V> MutableMap<K, V>.removeFirst(predicate: (key: K, value: V) -> Boolean) {
    iterator().let { iterator ->
        while (iterator.hasNext()) {
            iterator.next().let { (key, value) ->
                if (predicate(key, value)) {
                    iterator.remove()
                    return
                }
            }
        }
    }
}