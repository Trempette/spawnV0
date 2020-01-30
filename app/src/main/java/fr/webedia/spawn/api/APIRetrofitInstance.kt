package fr.webedia.spawn.api

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.webedia.spawn.api.deserializer.DataDeserializer
import fr.webedia.spawn.api.feed.GameFeed
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object APIRetrofitInstance {

    val service = instance.create<APIService>()
    private var retrofit: Retrofit? = null
    val CONNECT_TIMEOUT_MILLIS = 30 * 1000 // 30s
    val READ_TIMEOUT_MILLIS = 20 * 1000 // 20s

    private fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(CONNECT_TIMEOUT_MILLIS.toLong(), TimeUnit.MILLISECONDS)
        httpClient.readTimeout(READ_TIMEOUT_MILLIS.toLong(), TimeUnit.MILLISECONDS)

        httpClient.addInterceptor(APIInterceptor())
        return httpClient.build()
    }

    val instance: Retrofit
        get() {
            if (retrofit == null) {
                val gsonBuilder = GsonBuilder()
                gsonBuilder.registerTypeAdapter(
                    object : TypeToken<DataNetworkFeed<GameFeed>>() {}.type,
                    DataDeserializer(GameFeed::class.java)
                )

                val gson = gsonBuilder.create()

                retrofit = Retrofit.Builder()
                    .baseUrl("http://endpoint")
                    .client(createHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }
            return retrofit!!
        }

}