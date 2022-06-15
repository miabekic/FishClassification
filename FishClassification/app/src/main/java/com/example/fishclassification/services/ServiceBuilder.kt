package com.example.fishclassification.services
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private val client = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.MINUTES)
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES);

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://16006ff4-0c62-4c01-8009-49c38acea85b.westeurope.azurecontainer.io")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}