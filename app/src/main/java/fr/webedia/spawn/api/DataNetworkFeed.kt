package fr.webedia.spawn.api

sealed class DataNetworkFeed<T> {

    data class NetworkError<T>(val code: Int, val message: String) : DataNetworkFeed<T>()

    data class Data<T>(val data: List<T>) : DataNetworkFeed<T>()

    data class SingleData<T>(val singleData: T) : DataNetworkFeed<T>()

    fun isError() = this is NetworkError

    fun isSuccess() = !isError()

    fun data() = (this as Data<T>).data

    fun error() = this as NetworkError
}