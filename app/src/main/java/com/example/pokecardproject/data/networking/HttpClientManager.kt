package com.example.pokecardproject.data.networking

import com.example.pokecardproject.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Implementation of [HttpClientManager]
 */
private class HttpClientManagerImpl : HttpClientManager {


    /**
     * Http Client, Here we just construct a client with a logger to see entire
     * request and response
     */
    private val client: OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG)
                    this.addInterceptor(HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    })
            }
            .build()

    override val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseUrlHolder.baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

/**
 * Manager use to access to Retrofit resource
 */
interface HttpClientManager {

    /**
     * Instance of Retrofit used to create Api
     */
    val retrofit: Retrofit

    companion object Instance {
        /**
         * Singleton for the interface
         */
        val instance: HttpClientManager = HttpClientManagerImpl()
    }
}

inline fun <reified T> HttpClientManager.createApi(): T {
    BaseUrlHolder.retrofitCreated = true
    return this.retrofit.create()
}

object BaseUrlHolder {

    // Default Value
    var baseUrl: String = BuildConfig.BASE_URL_API
    var retrofitCreated: Boolean = false
}
